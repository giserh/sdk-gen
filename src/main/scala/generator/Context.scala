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
  var includePath: String

  def withResourcePath(resourcePath: String): Context
  def withIncludePath(includePath: String): Context
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
  var includePath: String = "./resources/include"
    
  var raml:Raml = null
  var generator: Generator = null

  override def withResourcePath(resourcePath: String): CodeContext = {
    this.resourcePath = resourcePath
    this
  }
  
  override def withIncludePath(includePath: String): CodeContext = {
    this.includePath= includePath
    this
  }

  override def withBaseUrl(baseUrl: String): CodeContext = {
    this.baseUrl = baseUrl
    this
  }

  override def withGenerator(generator: Generator): CodeContext = {
    this.generator = generator
    this
  }

  override def withTempDirectory(tempDirectory: String): CodeContext = {
    this.tempDirectory = tempDirectory
    this
  }

  override def withOutputDirectory(outputDirectory: String): CodeContext = {
    this.outputDirectory = outputDirectory
    this
  }
  
  override def withRaml(raml: Raml): CodeContext = {
    this.raml = raml
    this
  }  

  def compose(): CodeWorker = new CodeWorker(CodeContext.this)

}

