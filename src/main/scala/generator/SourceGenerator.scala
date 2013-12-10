package generator

import org.fusesource.scalate.TemplateEngine
import analyser.Package
import analyser.Clazz
import java.io.File
import analyser.Method
import org.raml.model.Raml
import java.io.PrintWriter

/**
 * Exception that may occur during code generation
 */
class SourceGeneratorException(message: String = null, cause: Throwable = null) extends Exception(message, cause)

/**
 * Generator for source code (PHP, Javascript etc)   
 */
trait SourceGenerator extends Generator{
	
	/**
	 * Creates the sdk based on Raml
	 * @param raml - output from raml parser
	 * @param resourcePath - path to templates
	 * @param baseUrl - url to api
	 * @param tempDirectory - temporary directory
	 * @return whether SDK was generated
	 */
	override def generate(raml:Raml,resourcePath: String, baseUrl: String, tempDirectory: String): Boolean = {
		val pack : Package = new Package
		pack.parse(raml)
		
		val classes : List[String] = pack.getClazzes.map{
			clazz => generateClass(clazz, resourcePath + "/Class.ssp", clazz.getMethods.map{
				m => generateMethod(m, resourcePath + "/Method.ssp")
				})
			}
		
		val packageString = generatePackage(pack, resourcePath + "/Package.ssp", classes)
	
		val dest = new PrintWriter(new File(tempDirectory + "/isaacloud.php"))
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
	def generatePackage(pack: Package, packageFile : String, classes : List[String]) : String = ???
	
	/**
	 * Generates Class string based on template for class in a language and previously generated methods.
	 * @param clazz - Clazz object representing a class for SDK
	 * @param classFile - path to class template
	 * @param methods - list of previously generated methods
	 * @return generated class in a string
	 */
	def generateClass(clazz : Clazz, classFile : String, methods : List[String]) : String = ???

	/**
	 * Generates method based on method template
	 * @param method - Method object representing the sdk method
	 * @param methodFile - path to method template
	 * @return generated method in a string
	 */
	def generateMethod(method: Method, methodFile : String) : String = ???
}