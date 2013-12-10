package generator

import java.io.PrintWriter
import analyser.Clazz
import org.fusesource.scalate.DefaultRenderContext
import org.fusesource.scalate.TemplateEngine
import java.io.File
import analyser.Package
import analyser.Method
import java.io.StringWriter


class PhpSDKGenerator extends SourceGenerator{
	

	val engine : TemplateEngine = new TemplateEngine
	override def generatePackage(pack : Package, packageFile : String, clazzes : List[String]) : String ={
		val templ = engine.load(packageFile)
		
		val result = new StringWriter()
		val buffer = new PrintWriter(result)
		val context = new DefaultRenderContext("/", engine, buffer)
		
		context.attributes("classes") = clazzes
		templ.render(context)
		
		buffer.flush()
		result.toString()	
	}
	
	override def generateClass(clazz : Clazz, classFile : String, methods : List[String]) : String ={
			
		val templ = engine.load(classFile)
		
		val result = new StringWriter()
		val buffer = new PrintWriter(result)
		val context = new DefaultRenderContext("/", engine, buffer)
		
		context.attributes("className") = clazz.name
		context.attributes("methods") = methods
		templ.render(context)
		
		buffer.flush()
		result.toString()	
	}
	
	override def generateMethod(method : Method, methodFile : String): String = {
		val templ = engine.load(methodFile)
		
		val result = new StringWriter()
		val buffer = new PrintWriter(result)
		val context = new DefaultRenderContext("/", engine, buffer)
		
		val tmp=method.query.map{tuple => tuple._1}.toList
		context.attributes("parameters") = method.query.map{tuple => tuple._1}
		context.attributes("body") = """ return ""; """
		context.attributes("methodName") = method.name
		
		templ.render(context)
		buffer.flush()
		result.toString()		
		
	}
}