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

	protected var fileName = "documentation.php"
	val engine: TemplateEngine = new TemplateEngine
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

		var all = Map[String, Map[String, List[Method]]]()
		for (clazz <- pack.clazzes) {
			{
				val mapName = clazz.methods(0).url.split("/")(1)
				// group by the second part of url
				val nameMap = clazz.methods.groupBy(m => m.url.split("/")(2))
				all += (mapName -> nameMap)

			}
		}

		val pages = all.keys.map {
			key =>
				{
					var headerId = -1
					val sortedKeys = all(key).keys.toList //.sortBy( x => x)						
					val headers = sortedKeys.map {
						mkey =>
							{
								var methodId = -1
								headerId += 1
								val methods = all(key)(mkey).map {
									m => methodId += 1; generateMethod(m, resourcePath + "/Method.ssp", methodId, headerId)
								}
								generateHeaders(methods, resourcePath + "/Header.ssp", headerId, mkey)
							}
					}
					(key, generatePage(pack, resourcePath + "/Page.ssp", headers))

				}
		}.toMap

		// write to file

		val dest = new PrintWriter(new File(tempDirectory + "/" + fileName))
		dest.print(pages("admin"))
		dest.flush()

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
		//context.attributes("docs") = pack.docs

		templ.render(context)

		buffer.flush()
		result.toString()
	}

	def generateHeaders(methods: List[String], pageFile: String, headerId: Int, name: String): String = {
		val templ = engine.load(pageFile)

		val result = new StringWriter()
		val buffer = new PrintWriter(result)
		val context = new DefaultRenderContext("/", engine, buffer)

		context.attributes("methods") = methods
		context.attributes("name") = name.capitalize
		context.attributes("id") = headerId

		templ.render(context)

		buffer.flush()
		result.toString()
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

		val name = method.name.capitalize
		name.split("")
		context.attributes("methodName") = method.name.capitalize
		context.attributes("desc") = method.docs("")._2
		context.attributes("headerId") = headerId
		context.attributes("methodId") = methodId
		context.attributes("queryParameters") = method.query.toList.filter(tpl => tpl._1 != "body").map {
			tpl => (tpl._1, tpl._2.toLowerCase(), method.docs(tpl._1)._2)
		}

		context.attributes("url") = method.restType.toString().toUpperCase() + " " + method.url

		if (method.docs.contains("body")) {
			val obj = JSON.parseFull(method.docs("body")._2) match {
				case Some(v) => v
				case None =>
			}
			var bodypar = obj.asInstanceOf[Map[String, Any]]
			if (bodypar.contains("properties"))
				bodypar = bodypar("properties").asInstanceOf[Map[String, Any]]

			val bodyTable = bodypar.map {
				tupl =>
					{
						(tupl._1, tupl._2.asInstanceOf[Map[String, Any]]("type"), "")
					}
			}.toList
			context.attributes("bodyTable") = bodyTable
		} else context.attributes("bodyTable") = List[(String, String, String)]()

		if (method.docs.contains("example"))
			context.attributes("response") = method.docs("example")._2
		else
			context.attributes("response") = "example"

		if (method.docs.contains("example_body"))
			context.attributes("request") = method.docs("example_body")._2
		else
			context.attributes("request") = "example_body"

		templ.render(context)

		buffer.flush()
		result.toString()
	}
}