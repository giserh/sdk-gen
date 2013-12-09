package generator

import org.raml.model.Raml
import java.io.File

trait Generator {
  def generate(raml:Raml,resourcePath: String, baseUrl: String, tempDirectory: String): Boolean
}

class PhpGeneratorException(message: String = null, cause: Throwable = null) extends Exception(message, cause)
class PhpGenerator extends Generator{
  private val name = "php"
  override def generate(raml:Raml,resourcePath: String, baseUrl: String, tempDirectory: String): Boolean = {
    true
  }
  
}


class CodeGeneratorException(message: String = null, cause: Throwable = null) extends Exception(message, cause)
abstract class Composer {
  
  var generator: Generator
  var raml:Raml
  
  var resourcePath: String
  var baseUrl: String
  var tempDirectory: String
  var outputDirectory: String

  def withResourcePath(resourcePath: String): Composer
  def withBaseUrl(baseUrl: String): Composer
  def withGenerator(generator: Generator): Composer
  def withTempDirectory(tempDirectory: String): Composer
  def withOutputDirectory(outputDirectory: String): Composer
  def withRaml(raml: Raml): Composer
}

class CodeComposer extends Composer {

  
  var resourcePath: String = "/resource"
  var baseUrl: String = ""
  var tempDirectory: String = "/tmp"
  var outputDirectory: String = "tmp"

    
  var raml:Raml = null
  var generator: Generator = null

  override def withResourcePath(resourcePath: String): CodeComposer = {
    this.resourcePath = resourcePath
    this
  }

  override def withBaseUrl(baseUrl: String): CodeComposer = {
    this.baseUrl = baseUrl
    this
  }

  override def withGenerator(generator: Generator): CodeComposer = {
    this.generator = generator
    this
  }

  override def withTempDirectory(tempDirectory: String): CodeComposer = {
    this.tempDirectory = tempDirectory
    this
  }

  override def withOutputDirectory(outputDirectory: String): CodeComposer = {
    this.outputDirectory = outputDirectory
    this
  }
  
  override def withRaml(raml: Raml): CodeComposer = {
    this.raml = raml
    this
  }  

  def compose(): CodeWorker = new CodeWorker(this)

}

class CodeWorkerException(message: String = null, cause: Throwable = null) extends Exception(message, cause)
class CodeWorker(composer: Composer) {
  /**all operations need to be done */

  val generator = composer.generator
  val resourcePath = composer.resourcePath
  val raml = composer.raml

  /**Temporary directory name*/
  var tmpFolderName = "package_" + composer.hashCode()

  /**Create temporary folder*/
  var tmpFolder = composer.tempDirectory + tmpFolderName;
  var tmpDir: File = new File(composer.tempDirectory + tmpFolderName  );
  if(!tmpDir.mkdir()){
    throw new CodeWorkerException(s"Failed trying to create temporary folder in: $tmpFolder ")
  }
  
  /**Prepare output directory*/
  var outputDir: File = new File(composer.outputDirectory)
  if(!outputDir.exists()){
    if(!outputDir.mkdir()) throw new CodeWorkerException(s"Failed trying to create output folder in: $tmpFolder ")
  }
  
  /**Invoke adapter*/
  generator.generate(raml,resourcePath, composer.baseUrl, tmpDir.getAbsolutePath())
  
  /**Remove old files @TODO*/
  
  

}
