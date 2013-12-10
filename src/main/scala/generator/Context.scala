package generator

import java.io.File
import org.raml.model.Raml
import java.io.FileOutputStream
import java.io.FileInputStream
import java.nio.file.Files
import java.nio.file.StandardCopyOption._
import java.nio.channels.FileChannel


abstract class Context {
  
  var generator: Generator
  var raml:Raml
  
  var resourcePath: String
  var baseUrl: String
  var tempDirectory: String
  var outputDirectory: String

  def withResourcePath(resourcePath: String): Context
  def withBaseUrl(baseUrl: String): Context
  def withGenerator(generator: Generator): Context
  def withTempDirectory(tempDirectory: String): Context
  def withOutputDirectory(outputDirectory: String): Context
  def withRaml(raml: Raml): Context
}

/**Code composer*/
class CodeContext extends Context {

  
  var resourcePath: String = "./resource/"
  var baseUrl: String = ""
  var tempDirectory: String = "/tmp/"
  var outputDirectory: String = "./output/"

    
  var raml:Raml = null
  var generator: Generator = null

  override def withResourcePath(resourcePath: String): CodeContext = {
    CodeContext.this.resourcePath = resourcePath
    CodeContext.this
  }

  override def withBaseUrl(baseUrl: String): CodeContext = {
    CodeContext.this.baseUrl = baseUrl
    CodeContext.this
  }

  override def withGenerator(generator: Generator): CodeContext = {
    CodeContext.this.generator = generator
    CodeContext.this
  }

  override def withTempDirectory(tempDirectory: String): CodeContext = {
    CodeContext.this.tempDirectory = tempDirectory
    CodeContext.this
  }

  override def withOutputDirectory(outputDirectory: String): CodeContext = {
    CodeContext.this.outputDirectory = outputDirectory
    CodeContext.this
  }
  
  override def withRaml(raml: Raml): CodeContext = {
    CodeContext.this.raml = raml
    CodeContext.this
  }  

  def compose(): CodeWorker = new CodeWorker(CodeContext.this)

}

