package composer.generator

import org.raml.model.Raml
import composer.Generator
import composer.GeneratorException

class PhpGeneratorException(message: String = null, cause: Throwable = null) extends GeneratorException(message, cause)
class PhpGenerator extends Generator{
  private val name = "php"
  override def generate(raml:Raml,resourcePath: String, baseUrl: String, tempDirectory: String): Boolean = {
    true
  }
  
}