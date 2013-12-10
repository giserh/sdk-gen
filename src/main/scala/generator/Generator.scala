package generator

import org.raml.model.Raml

/**
 * Exception only thrown by the generator
 */
class GeneratorException(message: String = null, cause: Throwable = null) extends Exception(message, cause)

/**
 * Base trait representing code generators
 */
trait Generator {
  def generate(raml:Raml,resourcePath: String, baseUrl: String, tempDirectory: String): Boolean
}