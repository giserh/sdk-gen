import java.security.InvalidParameterException

import scala.io.BufferedSource
import scala.io.Source

import org.raml.model.Raml
import org.raml.parser.visitor.RamlDocumentBuilder

import com.typesafe.config.ConfigFactory

import generator.CodeContext
import generator.CodeWorkerException
import generator.Generator
import generator.PhpSDKGenerator

object Main {
  

  val config = ConfigFactory.load()

  /**Get runner option*/
  type OptionMap = Map[Symbol, Any]
  val usage = """ Usage: sdkgen [--generator | -g string] [--output -o directory] filename """
  def nextOption(map: OptionMap, list: List[String]): OptionMap = {
    list match {
      case Nil => map
      case ("--generator" | "-g") :: value :: tail => value match {
        /** @TODO add new generators */
        case "php" => nextOption(map ++ Map('generator -> new PhpSDKGenerator), tail)
        case _ => throw new CodeWorkerException(s"Not found generator: $value")
      }
      case ("--output" | "-o") :: value :: tail => {
        /** @TODO validate output*/
        nextOption(map ++ Map('output -> value.toString), tail)
      }
      /**get last parameter - path to RAML file */
      case string :: Nil => {
        /** @TODO check file is exist , in future get file from remote resource*/
        nextOption(map ++ Map('raml -> string), list.tail)
      }
      case option :: tail =>
        println("Unknown option " + option)
        exit(1)
    }
  }
  def main(args: Array[String]): Unit = {

    /**Check whether the parameters passed*/
    if (args.length != 0) {

      /**catch options*/
      val options: OptionMap = nextOption(Map(), args.toList)
      
      if(!options.isDefinedAt('generator)) throw new InvalidParameterException("Generator are not definded!")
      if(!options.isDefinedAt('output)) throw new InvalidParameterException("Output directory are not definded!")
      if(!options.isDefinedAt('raml)) throw new InvalidParameterException("RAML file are not definded!")
      
      
      /**from command line -g --generator, -o --output, -r --raml */
      val ramlFile = "data/example.raml"
      val outputDirectory = "./generated"
      val codeGenerator: Generator = new PhpSDKGenerator
      
      /** Get configuration for application*/
      var application = config.getConfig("application")

      /**Load RAML file*/
      var buf: Option[BufferedSource] = None
      buf = Some(Source.fromFile(ramlFile))
      val source: String = buf.get.getLines mkString "\n"

      /** Get data from configuration*/
      val baseUrl = application.getString("baseUrl")
      val resourcePath = application.getString("resourcePath")
      val tempDirectory = application.getString("tempDirectory")

      
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

    } else {
      /**there are no parameters*/
      println(usage)
    }

  }
}