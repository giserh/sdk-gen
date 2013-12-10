import scala.io.BufferedSource
import scala.io.Source
import org.raml.model.Raml
import org.raml.parser.visitor.RamlDocumentBuilder

import analyser.Package

import generator.Context
import generator.CodeContext
import generator.Generator
import generator.PhpSDKGenerator

object Main {

	def main(args: Array[String]): Unit = {
		/**@TODO add command line user interace*/	
		var buf: Option[BufferedSource] = None

		buf = Some(Source.fromFile("data/example.raml"))
		val source: String = buf.get.getLines mkString "\n"


		/**from configuration*/
		val baseUrl = "https://api.isaacloud/v1/api"
		val resourcePath = "./resources/php"
		val tempDirectory = "/tmp/gen_tmp/"

		/**from command line -g --generator, -o --output*/
		val outputDirectory = "./generated"

		val codeGenerator: Generator = new PhpSDKGenerator
		var raml: Raml = new RamlDocumentBuilder().build(source)

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