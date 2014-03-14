package generator

import java.io.File
import java.io.PrintWriter
import java.io.StringWriter

import org.fusesource.scalate.DefaultRenderContext
import org.fusesource.scalate.TemplateEngine
import org.raml.model.Raml

import analyser.Analyser
import analyser.Method

class JavaScriptSDKGenerator extends Generator {

	protected var fileName = "isaacloud-sdk.js"
	val engine: TemplateEngine = new TemplateEngine
	val pc = "Package.ssp"
	val bl = "Block.ssp"
	val fc = "Function.ssp"
	val me = "Method.ssp"
		
	def generate(raml: Raml, resourcePath: String, baseUrl: String, tempDirectory: String): Boolean = {
		val pack = Analyser.analyseRaml(raml)

		val templ = engine.load(resourcePath + "/" + pc)

		val buffer = new PrintWriter(new File(tempDirectory + "/" + fileName))
		val context = new DefaultRenderContext("/", engine, buffer)

		context.attributes("content") = pack.clazzes.flatMap {
			ms =>
				createBlocks(ms.methods, 1, resourcePath)
		}

		templ.render(context)

		buffer.flush()
		true
	}

	def createBlocks(methods: List[Method], depth: Int, resourcePath: String): List[String] = {

		val toCreate = methods.filter {
			m =>
//				println(m.url)
//				println(m.url.split("/").length + " " + (depth+1))
				m.url.split("/").length == depth +1
		}

		val created = toCreate.map{
			m => createMethod(m, resourcePath)
		}

		val currentBlock = methods(0).url.split("/")(depth)

		// create blocks

		if (!currentBlock.contains("{")) {
			createBasicBlocks(methods, depth, resourcePath, currentBlock,created)

		} else {
			createFunctionBlocks(methods, depth, resourcePath, currentBlock,created)
		}
	}

	def createBasicBlocks(methods: List[Method], depth: Int, resourcePath: String, currentBlock: String, created : List[String]): List[String] = {
		val templ = engine.load(resourcePath + "/" + bl)

		val res = new StringWriter()
		val buffer = new PrintWriter(res)
		val context = new DefaultRenderContext("/", engine, buffer)
		context.attributes("name") = currentBlock
		context.attributes("blocks") =  created ::: methods
			.filter(a => a.url.split("/").length > depth + 1)
			.groupBy(a => a.url.split("/")(depth + 1))
			.flatMap {
				ms =>
					createBlocks(ms._2, depth + 1, resourcePath)
			}.toList
		templ.render(context)

		buffer.flush()

		List(res.toString())
		// join and return 
	}

	def createFunctionBlocks(methods: List[Method], depth: Int, resourcePath: String, currentBlock: String, created : List[String]): List[String] = {
		val ohne = currentBlock.replace("{", "").replace("}", "")
		val templ = engine.load(resourcePath + "/" + fc)
		val res = new StringWriter()
		val buffer = new PrintWriter(res)
		val context = new DefaultRenderContext("/", engine, buffer)

		context.attributes("name") = ohne
		context.attributes("idName") = ohne
		context.attributes("blocks") = created ::: methods
			.filter(a => a.url.split("/").length > depth + 1)
			.groupBy(a => a.url.split("/")(depth + 1))
			.flatMap {
				ms =>
					createBlocks(ms._2, depth + 1, resourcePath)
			}.toList
			
		templ.render(context)

		buffer.flush()

		// join and return 

		List(res.toString())
	}
	
	def createMethod(m : Method, resourcePath : String) : String = {
		
		var url = "'" + m.url.replace("{", "' +").replace("}", "+ '")
		
		if (url.endsWith("+ '")){
			url = url.dropRight(3)
			
		} else {
			url = url + "'"
		}
			
		val templ = engine.load(resourcePath + "/" + me)
		val res = new StringWriter()
		val buffer = new PrintWriter(res)
		val context = new DefaultRenderContext("/", engine, buffer)

		context.attributes("rtype") = m.restType.toString()
		context.attributes("url") = url
		
			
		templ.render(context)

		buffer.flush()

		// join and return 

		res.toString()
	}
}