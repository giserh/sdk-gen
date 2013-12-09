import scala.io.BufferedSource
import scala.io.Source
import org.raml.model.Raml
import org.raml.parser.visitor.RamlDocumentBuilder
import composer.JavaSDKGenerator
import gensdk.Package
import composer.JavaSDKGenerator
import composer.Composer
import composer.CodeComposer
import composer.Generator
import composer.generator.PhpGenerator


object Main {

	def main(args: Array[String]): Unit = {

		var buf: Option[BufferedSource] = None

		buf = Some(Source.fromFile("data/example.raml"))
		val source: String = buf.get.getLines mkString "\n"

		
//		val pack = new Package
//		
//		val raml1: Raml = new RamlDocumentBuilder().build(source)
//		//get package
//		pack.parse(raml1)
//		val generator = new JavaSDKGenerator("localhost")
//		//generate code
//		generator.generate(pack,"php")
		
		
		/**from configuration*/
		val baseUrl = "https://api.isaacloud/v1/api"
		val resourcePath = "/tmp/sdk-gen/"  
		val tempDirectory = "/tmp/gen_tmp/"
 
		/**from command line*/
		val outputDirectory = "/tmp/gen_tmp/generated/"
		
		val codeGenerator:Generator = new PhpGenerator  
		var raml:Raml = new RamlDocumentBuilder().build(source)
		
		var composer:CodeComposer = new CodeComposer()
		composer.withBaseUrl(baseUrl)
				.withGenerator(codeGenerator)
				.withOutputDirectory(outputDirectory)
				.withRaml(raml)
				.withResourcePath(resourcePath)
				.withTempDirectory(tempDirectory)
				.compose
				
		

	}
}