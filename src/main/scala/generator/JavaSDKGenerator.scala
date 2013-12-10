package generator

import java.io.PrintWriter
import analyser.Clazz
import org.fusesource.scalate.DefaultRenderContext
import org.fusesource.scalate.TemplateEngine
import java.io.File
import analyser.Package
import analyser.Method


//class JavaSDKGenerator extends SourceGenerator {
//	
//	val engine : TemplateEngine = new TemplateEngine()
//	
//	override def generatePackage(pack : Package){
//		
//	}
//
//	def generateClass(clazz : Clazz){
//		val file = "./resources/java/Class.ssp"
//
//			
//		val templ = engine.load(file)
//		val buffer = new PrintWriter("./generated/java/" +clazz.name+".java")
//		val context = new DefaultRenderContext("/", engine, buffer)
//		context.attributes("className") = clazz.name
//		context.attributes("methodNames") = clazz.getMethods.map{m => m.name }
//		val out = templ.render(context)
//		
//		buffer.flush()
//	}
//	
//	override def generateMethod(method : Method){
//		
//	}
//}