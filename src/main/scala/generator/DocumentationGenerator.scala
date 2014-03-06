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
	 * Checks how the method begins and uses order list to determine the order of Methods
	 * @param a first method
	 * @param b second method
	 * @return correct order of methods
	 */
	def methodSorter(a: Method, b: Method): Boolean = {				
		
		val order = List("post","get","put","patch","delete")
		val ia = order.indexOf(order.filter( beg => a.name.startsWith(beg))(0))
		val ib = order.indexOf(order.filter( beg => b.name.startsWith(beg))(0))
		ia <= ib
	}

	/**
	 * Groups method to segregate them
	 * @param m method we are going to group
	 * @return group it belongs to
	 */
	def methodGrouper(m: Method): String = {
		val res = m.url.split("/")
		if (res(2).equals("users") && res.length > 5 && res(3).equals("groups")) {
			"users group " + res(5)
		} else if (res.length > 3 && !res(3).equals("doc") && !res(3).contains("{")) {
			res(2) + " " + res(3)
		} else if (res(2).equals("users") && res.length > 4) {

			"user " + res(4)
		} else {

			res(2)
		}
	}

	/**
	 * Get the title for resource.
	 */
	def title(s: String) = s match {
		case "client_scripts" => "Client scripts"
		case "events error" => "Error events"
		case "notifications error" => "Error notifications"
		case "clientscripts" => "Client scripts"
		case "transaction_sources" => "Transaction sources"
		case "transactionsources" => "Transaction sources"
		case "transaction_types" => "Transaction types"
		case "transactiontypes" => "Transaction types"	
		case "user_groups" => "User groups"
		case "user customfields" => "User custom fields"
		case "user externalids" => "User external ids"
		case "user gainedachievements" => "User gained achievements"
		case "user countervalues" => "User counter values"
		case "user wongames" => "User won games"
		case "users group customfields" => "User group custom fields"
		case "users group externalids" => "User group external ids"
		case "users group gainedachievements" => "User group gained achievements"
		case "users group countervalues" => "User group counter values"
		case "users group wongames" => "User group won games"
		case other => other.capitalize
	}

	/**
	 * Create all the pages to write to output
	 */
	private def createPages(all: Map[String, Map[String, List[Method]]], resourcePath: String, pack: Package) = {
		all.keys.map {
			key =>
				{

					val sortedKeys = all(key).keys.toList
					val headers = sortedKeys.map {
						mkey =>
							{
								//generate methods
								var methodId = -1
								var headerId = -1

								val groupedMethods = all(key)(mkey).groupBy(methodGrouper).toList.sortBy( a => a._1.length())

								val generatedMethods = groupedMethods.map {
									g =>
										headerId += 1; (g._1, g._2.sortWith(methodSorter).map {
											m => methodId += 1; generateMethod(m, resourcePath + "/Method.ssp", methodId, headerId)
										})
								}.toList

								//generate headers
								headerId = -1
								val header_list = generatedMethods.map {
									ms =>
										{
											headerId += 1
											generateHeaders(ms._2, resourcePath + "/Header.ssp", headerId, ms._1)

										}
								}
								//generate page
								(key + "_" + mkey) -> generatePage(pack, resourcePath + "/Page.ssp", header_list, mkey)
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

		def mapper(s: String) = s match {
			case "clientscripts" => "client_scripts"
			case "transactionsources" => "transaction_sources"
			case "transactiontypes" => "transaction_types"
			case other => other
		}

		var all = Map[String, Map[String, List[Method]]]()
		for (clazz <- pack.clazzes) {
			{
				val mapName = clazz.methods(0).url.split("/")(1)
				// group by the second part of url
				val nameMap = clazz.methods.groupBy {
					m =>
						{
							val mets = m.url.split("/")
							if (mets.length > 3 && mets(2).equals("users") && mets(3).equals("groups"))
								"user_groups"
							else
								mapper(mets(2))
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
	def generatePage(pack: Package, pageFile: String, headers: List[String], name: String): String = {
		val templ = engine.load(pageFile)

		val result = new StringWriter()
		val buffer = new PrintWriter(result)
		val context = new DefaultRenderContext("/", engine, buffer)

		context.attributes("headers") = headers
		context.attributes("name") = title(name)

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

		context.attributes("methods") = methods
		context.attributes("name") = title(name)

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
				case None => throw new Exception("Wrong body:\n"+m.docs("body")._2)
			}

			var bodypar = obj.asInstanceOf[Map[String, Any]]

			if (bodypar.contains("properties"))
				bodypar = bodypar("properties").asInstanceOf[Map[String, Any]]

			
			val bodyTable = bodypar.map {
				tupl =>
					{
						val props = tupl._2.asInstanceOf[Map[String, Any]]
						if(!props.contains("description")) {
							throw new NoSuchElementException("No description found for "+tupl._1+" in "+m.name)
						}
						
						if (props.contains("allowed")){
								val all =props("allowed").asInstanceOf[Map[String, Any]]
								val add = "<br>allowed values are:<br>" + all.map( tpl => tpl._1 + " : " + tpl._2 ).mkString(", ")						
								(tupl._1, props("type").toString, props("description").toString + add)
							}
						else
							(tupl._1, props("type").toString, props("description").toString)
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