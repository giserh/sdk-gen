package generator

import java.io.PrintWriter
import gensdk.Clazz
import org.fusesource.scalate.DefaultRenderContext
import org.fusesource.scalate.TemplateEngine
import java.io.File
import gensdk.Package
import gensdk.Method

class PhpSDKGenerator(val baseUrl : String) extends SDKGenerator{
	

	override def generateClass(clazz : Clazz){
		val file = "./resources/php/Class.ssp"

			
		val templ = engine.load(file)
		val buffer = new PrintWriter(".\\generated\\php\\" +clazz.name+".php")
		val context = new DefaultRenderContext("/", engine, buffer)
		
		val tmp = clazz.getMethods.map{m => (m.name -> ("$" + m.query.map{ tuple => tuple._1 }.mkString(", $")) )}.toMap
		
		context.attributes("className") = clazz.name
		context.attributes("methodNames") = clazz.getMethods.map{m => m.name }
		context.attributes("parameters") = tmp
		val out = templ.render(context)
		
		buffer.flush()
	}
	
	override def generateMethod(method : Method){
		
	}
}