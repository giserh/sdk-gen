package generator

import org.raml.model.Raml
import analyser.Analyser
import java.io.PrintWriter
import analyser.Method
import analyser.Package
import java.io.File
import org.fusesource.scalate.DefaultRenderContext
import org.fusesource.scalate.TemplateEngine
import java.io.StringWriter
import scala.util.parsing.json.JSON
class DocumentationGenerator extends Generator {

	protected var fileName = "documentation.html"
	var base = ""

	val engine: TemplateEngine = new TemplateEngine

	/**
	 * Create all the pages to write to output
	 */
	private def createPages(all: Map[String, Map[String, List[Method]]], resourcePath: String, pack: Package) = {
		all.keys.map {
			key =>
			{
				var headerId = 0
				val sortedKeys = all(key).keys.toList
				val headers = sortedKeys.map {
					mkey =>
						{
							var methodId = -1
							val methods = all(key)(mkey).map {
								m => methodId += 1; generateMethod(m, resourcePath + "/Method.ssp", methodId, headerId)
							}
							val generated_header = generateHeaders(methods, resourcePath + "/Header.ssp", headerId, mkey)
							(key + "_" + mkey) -> generatePage(pack, resourcePath + "/Page.ssp", List(generated_header))
						}
				}
				headers
			}
		}
	}
	
	/**
	 * Creates the sdk based on Raml
	 * @param raml - output from raml parser
	 * @param resourcePath - path to templates
	 * @param baseUrl - url to api
	 * @param tempDirectory - temporary directory
	 * @return whether SDK was generated
	 */
	override def generate(raml: Raml, resourcePath: String, baseUrl: String, tempDirectory: String): Boolean = {

		val pack: Package = Analyser.analyseRaml(raml)
		base = pack.baseUri

		var all = Map[String, Map[String, List[Method]]]()
		for (clazz <- pack.clazzes) {
			{
				val mapName = clazz.methods(0).url.split("/")(1)
				// group by the second part of url
				val nameMap = clazz.methods.groupBy{
					m => {
						val mets = m.url.split("/")
						if (mapName.equals("admin") && mets.length > 3 && mets(2).equals("users") && mets(3).equals("groups"))
							"usergroups"					
						else
							mets(2)
					}
				}

				all += (mapName -> nameMap)

			}
		}

		//create all the pages
		var pages = createPages(all, resourcePath, pack).foldLeft(List[(String, String)]())(_ ++ _)

		// write to file
		for (p <- pages) {
			val dest = new PrintWriter(new File(tempDirectory + "/" + p._1 + "_" + fileName))
			dest.print(p._2)
			dest.flush()
		}
		true
	}

	/**
	 * Generates documentation page for chosen methods.
	 * @param pack - Package object representing the entire SDK.
	 * @param packageFile - path to package template.
	 * @param headers - a list of generated subpages.
	 * @return generated package in a string
	 */
	def generatePage(pack: Package, pageFile: String, headers: List[String]): String = {
		val templ = engine.load(pageFile)

		val result = new StringWriter()
		val buffer = new PrintWriter(result)
		val context = new DefaultRenderContext("/", engine, buffer)

		context.attributes("headers") = headers

		templ.render(context)

		buffer.flush()
		result.toString()
	}

	/**
	 * Create headers
	 */
	def generateHeaders(methods: List[String], pageFile: String, headerId: Int, name: String): String = {
		val templ = engine.load(pageFile)

		val result = new StringWriter()
		val buffer = new PrintWriter(result)
		val context = new DefaultRenderContext("/", engine, buffer)

		def mapper(s: String) = s match {
			case "clientscripts" => "Client Scripts"
			case "transactionsources" => "Transaction Sources"
			case "transactiontypes" => "Transaction Types"
			case "usergroups" => "User Groups"
			case other => other.capitalize
		}

		context.attributes("methods") = methods
		context.attributes("name") = mapper(name)
		context.attributes("id") = headerId

		templ.render(context)

		buffer.flush()
		result.toString()
	}

	/**
	 * Creating curl expression
	 */
	private def createCurl(m: Method): String = {

		val regex = """\{[a-zA-Z0-9,]+\}""".r
		val whites = """[ \t]+""".r

		if (m.docs.contains("example_body")) {

			val curl = "curl -X " + m.restType.toString().toUpperCase() +
				" -H \"Authorization: Bearer d3e5174c60c56797e4fee47f45d39\" -H \"Content-Type: application/json\" -d \\ \n" +
				m.docs("example_body")._2.replace("\n", " ") +
				regex.replaceAllIn("\\ \nhttp://" + base + m.url, "1")

			whites.replaceAllIn(curl, " ")

		} else {

			val curl = "curl -X " + m.restType.toString().toUpperCase() +
				" -H 'Authorization: Bearer d3e5174c60c56797e4fee47f45d39' http://" +
				regex.replaceAllIn(base + m.url, "1")

			whites.replaceAllIn(curl, " ")
		}

	}

	/**
	 * Creating example request body.
	 */
	private def createExampleRequest(m: Method): String = {
		if (m.docs.contains("example_body"))
			m.docs("example_body")._2
		else
			"example_body"
	}

	/**
	 * Create body parameters for json schema.
	 */
	private def createBodyParameters(m: Method): List[(String, String, String)] = {
		if (m.docs.contains("body")) {

			val obj = JSON.parseFull(m.docs("body")._2) match {
				case Some(v) => v
				case None =>
			}

			var bodypar = obj.asInstanceOf[Map[String, Any]]

			if (bodypar.contains("properties"))
				bodypar = bodypar("properties").asInstanceOf[Map[String, Any]]

			val bodyTable = bodypar.map {
				tupl =>
					{
						(tupl._1, tupl._2.asInstanceOf[Map[String, Any]]("type").toString, tupl._2.asInstanceOf[Map[String, Any]]("description").toString)
					}
			}.toList

			bodyTable.sortWith((x, y) => x._1 < y._1)

		} else List[(String, String, String)]()
	}

	/**
	 * Create name for method title
	 */
	private def createName(m: Method): String = {

		def mapper(s: String) = s match {
			case "put" => "update"
			case "post" => "create"
			case "doc" => "documentation"
			case "docs" => "documentation"
			case other => other
		}

		m.name.split("(?=[A-Z])").map {
			l => mapper(l.toLowerCase())
		}.mkString(" ") capitalize
	}

	/**
	 * Create query url parameters for the table.
	 */
	private def createQueryParameters(m: Method): List[(String, String, String)] = {
		m.query.toList.filter(tpl => tpl._1 != "body").map {
			tpl => (tpl._1, tpl._2.toLowerCase(), m.docs(tpl._1)._2)
		}
	}

	/**
	 *  Create example response
	 */
	private def createExampleResponse(m: Method): String = {

		if (m.docs.contains("example"))
			m.docs("example")._2
		else
			"example"
	}
	/**
	 * Generates method documentation based on method template
	 * @param method - Method object representing the sdk method
	 * @param methodFile - path to method template
	 * @return generated method in a string
	 */
	def generateMethod(method: Method, methodFile: String, methodId: Int, headerId: Int): String = {

		val templ = engine.load(methodFile)

		val result = new StringWriter()
		val buffer = new PrintWriter(result)
		val context = new DefaultRenderContext("/", engine, buffer)

		context.attributes("methodName") = createName(method)
		context.attributes("desc") = method.docs("")._2
		context.attributes("headerId") = headerId
		context.attributes("methodId") = methodId
		context.attributes("queryParameters") = createQueryParameters(method)
		context.attributes("url") = method.restType.toString().toUpperCase() + " " + method.url
		context.attributes("bodyTable") = createBodyParameters(method)
		context.attributes("response") = createExampleResponse(method)
		context.attributes("request") = createExampleRequest(method)
		context.attributes("curl") = createCurl(method)

		// render the entire method
		templ.render(context)

		buffer.flush()
		result.toString()
	}
}