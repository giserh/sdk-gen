import scala.io.BufferedSource
import scala.io.Source
import org.raml.model.Raml
import org.raml.parser.visitor.RamlDocumentBuilder
import analyser.Package
import generator.Context
import generator.CodeContext
import generator.Generator
import generator.PhpSDKGenerator


import com.typesafe.config._

object Main {

  val config = ConfigFactory.load()

  def main(args: Array[String]): Unit = {

    /**from command line -g --generator, -o --output, -r --raml */
    val ramlPath = "data/example.raml"
    val outputDirectory = "./generated"
      
    /** Get configuration for application*/
    var application = config.getConfig("application")

    /**@TODO add command line user interace*/
    
    /**Load RAML file*/
    var buf: Option[BufferedSource] = None
    buf = Some(Source.fromFile("data/example.raml"))
    val source: String = buf.get.getLines mkString "\n"

    /** Get data from configuration*/
    val baseUrl = application.getString("baseUrl")
    val resourcePath = application.getString("resourcePath")
    val tempDirectory = application.getString("tempDirectory")

    

    val codeGenerator: Generator = new PhpSDKGenerator
    var raml: Raml = new RamlDocumentBuilder().build(source)

    /**Invoke code generator*/
    var composer: CodeContext = new CodeContext()
    composer.withBaseUrl(baseUrl)
      .withGenerator(codeGenerator)
      .withOutputDirectory(outputDirectory)
      .withRaml(raml)
      .withResourcePath(resourcePath)
      .withTempDirectory(tempDirectory)
      .compose

  }
}