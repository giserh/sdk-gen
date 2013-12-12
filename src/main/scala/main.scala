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
import com.typesafe.config._

object Main {
  

  val config = ConfigFactory.load()

  /**Get runner option*/
  val usage = """ Usage: sdkgen [--generator | -g string] [--output -o directory] filename """
  def nextOption(map: Map[String,_], list: List[String]): Map[String,_] = {
    list match {
      case Nil => map
      case ("--generator" | "-g") :: value :: tail => value match {
        /** @TODO add new generators */
        case "php" => nextOption(map ++ Map("generator" -> new PhpSDKGenerator), tail)
        case _ => throw new InvalidParameterException(s"Not found generator: $value")
      }
      case ("--output" | "-o") :: value :: tail => {
        /** @TODO validate output*/
        nextOption(map ++ Map("output" -> value.toString), tail)
      }
      /**get last parameter - path to RAML file */
      case string :: Nil => {
        /** @TODO check file is exist , in future get file from remote resource*/
        nextOption(map ++ Map("raml" -> string), list.tail)
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
      val options: Map[String,Any] = nextOption(Map(), args.toList)
      

      if(!options.contains("generator")) throw new InvalidParameterException("Generator are not definded!")
      if(!options.contains("output")) throw new InvalidParameterException("Output directory are not definded!")
      if(!options.contains("raml")) throw new InvalidParameterException("RAML file are not definded!")
      
      /**from command line -g --generator, -o --output raml */
      val ramlFile = options("raml")
      val outputDirectory = options("output")
      val codeGenerator = options("generator")
      
      /** Get configuration for application*/
      var application = config.getConfig("application")

      /**Load RAML file*/
      var buf: Option[BufferedSource] = None
      buf = Some(Source.fromFile(ramlFile.asInstanceOf[String]))
      val source: String = buf.get.getLines mkString "\n"

      /** Get data from configuration*/
      val baseUrl = application.getString("baseUrl")
      val resourcePath = application.getString("resourcePath")
      val tempDirectory = application.getString("tempDirectory")

      
      var raml: Raml = new RamlDocumentBuilder().build(source)

      /**Invoke code generator*/
      var composer: CodeContext = new CodeContext()
      composer.withBaseUrl(baseUrl)
        .withGenerator(codeGenerator.asInstanceOf[Generator])
        .withOutputDirectory(outputDirectory.toString)
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