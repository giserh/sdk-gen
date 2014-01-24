package generator

import java.io.PrintWriter
import java.io.StringWriter
import analyser.Clazz
import org.fusesource.scalate.DefaultRenderContext
import org.fusesource.scalate.TemplateEngine
import analyser.Method
import analyser.Package
import org.raml.model.Raml
import analyser.Analyser
import java.io.File
import analyser.DocType

class JavaSDKGenerator extends SourceGenerator(".java"){
	

	val engine : TemplateEngine = new TemplateEngine
	
	/**
	 * Creates the sdk based on Raml
	 * @param raml - output from raml parser
	 * @param resourcePath - path to templates
	 * @param baseUrl - url to api
	 * @param tempDirectory - temporary directory
	 * @return whether SDK was generated
	 */
	override def generate(raml:Raml,resourcePath: String, baseUrl: String, tempDirectory: String): Boolean = {
		val pack : Package = Analyser.analyseRaml(raml)
		
		val classes : List[(String,String)] = pack.clazzes.map{
			clazz => (clazz.name,generateClass(clazz, resourcePath + "/Class.ssp", clazz.methods.map{
				m => generateMethod(m, resourcePath + "/Method.ssp")
				}))
			}
		
		classes.foreach{
			tpl => {
				val dest = new PrintWriter(new File(tempDirectory + "/" + tpl._1 +".java"))
				dest.print(tpl._2)
				dest.flush()
			}
		}
	
		
		
		true
	}
	
	/**
	 * Generates the package for sdk based on a template for package in a language and previously generated classes 
	 * @param pack - Package object representing the entire SDK
	 * @param packageFile - path to package template
	 * @param classes - list of generated classes
	 * @return generated package in a string
	 */
	override def generatePackage(pack : Package, packageFile : String, clazzes : List[String]) : String ={
		""
	}
	
	/**
	 * Generates Class string based on template for class in a language and previously generated methods.
	 * @param clazz - Clazz object representing a class for SDK
	 * @param classFile - path to class template
	 * @param methods - list of previously generated methods
	 * @return generated class in a string
	 */
	override def generateClass(clazz : Clazz, classFile : String, methods : List[String]) : String ={
			
		val templ = engine.load(classFile)
		
		val result = new StringWriter()
		val buffer = new PrintWriter(result)
		val context = new DefaultRenderContext("/", engine, buffer)
		
		context.attributes("className") = clazz.name
		context.attributes("methods") = methods
		context.attributes("docs") = clazz.docs
		context.attributes("version") = clazz.version
		context.attributes("baseUrl") = clazz.baseUrl
		context.attributes("baseOauthUrl") = clazz.oauthUrl
		templ.render(context)
		
		buffer.flush()
		result.toString()	
	}
	
	
	/**
	 * Generates method based on method template
	 * @param method - Method object representing the sdk method
	 * @param methodFile - path to method template
	 * @return generated method in a string
	 */
	override def generateMethod(method : Method, methodFile : String): String = {
		
		def nameChanger( name : String) : String = name match{
			case "STRING" => "String"
			case "NUMBER" => "Long"	
			case other => other
		}
		
		val templ = engine.load(methodFile)
		
		val result = new StringWriter()
		val buffer = new PrintWriter(result)
		val context = new DefaultRenderContext("/", engine, buffer)
		
		val tmp=method.query.map{tuple => tuple._1}.toList
		context.attributes("parameters") = method.query.map{
			tpl => (tpl._1,nameChanger(tpl._2))
		}
		context.attributes("docs") = method.docs.filter( tpl => tpl._2._1 != DocType.OTHER).map{
			tpl =>{
				val attr = tpl._2
				if (attr._1 == DocType.DESCRIPTION){
					(tpl._1,attr._2)
				}else {
					("@"+attr._1+" "+tpl._1,attr._2)
				}
			}
		}
		context.attributes("methodName") = method.name
		context.attributes("url") = method.url
		context.attributes("rtype") = method.restType.toString()
		
		templ.render(context)
		buffer.flush()
		result.toString()		
		
	}
}