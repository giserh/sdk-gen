package generator

import org.raml.model.Raml
import org.fusesource.scalate.TemplateEngine

/**
 * Exception only thrown by the generator
 */
class GeneratorException(message: String = null, cause: Throwable = null) extends Exception(message, cause)

/**
 * Base trait representing code generators
 */
trait Generator {

	val engine: TemplateEngine = new TemplateEngine
	val sdkFileName : String = ""
	def generate(raml: Raml, resourcePath: String, baseUrl: String, tempDirectory: String): Boolean
}