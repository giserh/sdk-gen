package generator

import java.io.PrintWriter
import analyser.Clazz
import org.fusesource.scalate.DefaultRenderContext
import org.fusesource.scalate.TemplateEngine
import java.io.File
import analyser.Package
import analyser.Method
import java.io.StringWriter
import org.raml.model.Raml
import analyser.Analyser

/**
 * 
 */
class PhpSDKGenerator extends Generator{	

	override val sdkFileName = "isaacloud.php"
	
	override def generate(raml:Raml,resourcePath: String, baseUrl: String, tempDirectory: String): Boolean = {
		val pack : Package = Analyser.analyseRaml(raml)
		
		val classes : List[String] = pack.clazzes.map{
			clazz => generateClass(clazz, resourcePath + "/Class.ssp", clazz.methods.map{
				m => generateMethod(m, resourcePath + "/Method.ssp")
				})
			}
		
		val packageString = generatePackage(pack, resourcePath + "/Package.ssp", classes)
	
		val dest = new PrintWriter(new File(tempDirectory + "/" + sdkFileName))
		dest.print(packageString)
		dest.flush()
		
		true
	}
	/**
	 * Generates the package for sdk based on a template for package in a language and previously generated classes 
	 * @param pack - Package object representing the entire SDK
	 * @param packageFile - path to package template
	 * @param classes - list of generated classes
	 * @return generated package in a string
	 */
	def generatePackage(pack : Package, packageFile : String, clazzes : List[String]) : String ={
		val templ = engine.load(packageFile)
		
		val result = new StringWriter()
		val buffer = new PrintWriter(result)
		val context = new DefaultRenderContext("/", engine, buffer)
		
		context.attributes("classes") = clazzes
		context.attributes("docs") = pack.docs
		
		templ.render(context)
		
		buffer.flush()
		result.toString()	
	}
	
	/**
	 * Generates Class string based on template for class in a language and previously generated methods.
	 * @param clazz - Clazz object representing a class for SDK
	 * @param classFile - path to class template
	 * @param methods - list of previously generated methods
	 * @return generated class in a string
	 */
	def generateClass(clazz : Clazz, classFile : String, methods : List[String]) : String ={
			
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
	def generateMethod(method : Method, methodFile : String): String = {
		val templ = engine.load(methodFile)
		
		val result = new StringWriter()
		val buffer = new PrintWriter(result)
		val context = new DefaultRenderContext("/", engine, buffer)
		
		val tmp=method.query.map{tuple => tuple._1}.toList
		context.attributes("parameters") = method.query
		context.attributes("docs") = method.docs.map{tpl => (tpl._1,tpl._2._2)}
		context.attributes("methodName") = method.name
		context.attributes("url") = method.url
		context.attributes("rtype") = method.restType.toString()
		
		templ.render(context)
		buffer.flush()
		result.toString()		
		
	}
}