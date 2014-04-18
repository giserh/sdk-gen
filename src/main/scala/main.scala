import java.nio.file.Files
import java.nio.file.Paths
import java.security.InvalidParameterException
import org.raml.parser.rule.ValidationResult
import org.raml.validation.ValidationLogger
import scala.io.BufferedSource
import scala.io.Source
import org.raml.model.Raml
import org.raml.parser.visitor.{RamlValidationService, RamlDocumentBuilder}
import com.typesafe.config.ConfigFactory
import generator.CodeContext
import generator.Generator
import generator.PhpSDKGenerator
import java.io.File
import generator.JavaSDKGenerator
import generator.DocumentationGenerator
import org.raml.emitter.RamlEmitter
import java.io.PrintWriter
import generator.JavaScriptSDKGenerator
import scala.util.{Failure, Success, Try}

object Main {

  val config = ConfigFactory.load()

  /** Get runner option */
  val usage = """ Usage: sdkgen [--generator | -g string] [--output -o directory] [--include -i directory]  raml_filename """

  def nextOption(list: List[String]): Map[String, Any] = {
    list match {
      case ("--generator" | "-g") :: value :: tail => value match {
        /** @todo add new generators */
        case "php" => Map("generator" -> new PhpSDKGenerator) ++ nextOption(tail)
        case "java" => Map("generator" -> new JavaSDKGenerator) ++ nextOption(tail)
        case "js" => Map("generator" -> new JavaScriptSDKGenerator) ++ nextOption(tail)
        case "doc" => Map("generator" -> new DocumentationGenerator) ++ nextOption(tail)
        case _ => throw new InvalidParameterException(s"Not found generator: $value")
      }
      case ("--output" | "-o") :: value :: tail => {
        Map("output" -> value) ++ nextOption(tail)
      }
      case ("--save" | "-s") :: value :: tail => {
        Map("save" -> value) ++ nextOption(tail)
      }
      case ("--resources" | "-r") :: value :: tail => {
        Map("resources" -> value) ++ nextOption(tail)
      }
      case ("--include" | "-i") :: value :: tail => {
        Map("include" -> value) ++ nextOption(tail)
      }
      case ("--validate" | "-v") :: tail => {
        Map("validate" -> true) ++ nextOption(tail)
      }

      /** get last parameter - path to RAML file */
      case ramlFile :: Nil => {
        /** @todo in future get file from remote resource */
        if (!Files.exists(Paths.get(ramlFile))) throw new InvalidParameterException(s"File does not exists: $ramlFile")
        else Map("raml" -> ramlFile)
      }
      case Nil => {
        println("Not enough parameters ")
        sys.exit(1)
      }
      case other => {
        println("Unknown option " + other.head)
        sys.exit(1)
      }
    }
  }

  def main(args: Array[String]): Unit = {

    //    if (args.contains("validate")){
    //     val validation = RamlValidationService.createDefault().validate(ramlLocation)
    //    }
    /** Check whether we have any parameters */
    if (args.length != 0) {

      /** Create options map */
      val options: Map[String, Any] = nextOption(args.toList)

      if (!options.contains("generator")) throw new InvalidParameterException("Generator is not defined!")
      if (!options.contains("output")) throw new InvalidParameterException("Output directory is not defined!")
      if (!options.contains("raml")) throw new InvalidParameterException("RAML files are not defined!")

      /** from command line -g --generator, -o --output raml */
      val ramlFile: String = options("raml").asInstanceOf[String]
      val outputDirectory: String = options("output").asInstanceOf[String]
      val codeGenerator: Generator = options("generator").asInstanceOf[Generator]

      /** Get configuration for application */
      val application = config.getConfig("application")

      /** Load RAML file */
      var buf: Option[BufferedSource] = None
      buf = Some(Source.fromFile(ramlFile))
      val source: String = buf.get.getLines mkString "\n"

      /** Get data from configuration */
      val baseUrl = application.getString("baseUrl")
      var resourcePath = application.getString("resourcePath")

      if (options.contains("resources"))
        resourcePath = options("resources").asInstanceOf[String]

      val tempDirectory = application.getString("tempDirectory")

      //could change it to val you know
      var includePath: String = null
      if (options.contains("include"))
        includePath = options("include").asInstanceOf[String]

      if (options.contains("validate")) {
        val validation = RamlValidationService.createDefault().validate(ramlFile)
        //        println(ValidationLogger.history.head)
      }

      Try(new RamlDocumentBuilder().build(new File(ramlFile))) match {
        case Success(raml) => {
          if (options.contains("save")) {
            val emitter = new RamlEmitter
            val writer = new PrintWriter(options("save").asInstanceOf[String])
            writer.write(emitter.dump(raml))
            writer.flush()
          }
          /** Invoke code generator */
          val composer: CodeContext = new CodeContext()
          val worker = composer.withBaseUrl(baseUrl)
            .withGenerator(codeGenerator)
            .withOutputDirectory(outputDirectory)
            .withRaml(raml)
            .withResourcePath(resourcePath)
            .withTempDirectory(tempDirectory)
            .withIncludePath(includePath)
            .compose

          worker.run()
        }
        case Failure(e) => {
          println("Failed in parsing your raml")
          println(ValidationLogger.history.head)
          println("Exception message : \n" + e.getMessage)
        }
      }


    } else {
      /** there are no parameters */
      println(usage)
    }

  }
}