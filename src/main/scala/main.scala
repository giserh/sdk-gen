import org.raml.parser.visitor.RamlDocumentBuilder
import scala.io.BufferedSource
import scala.io.Source
import org.raml.model.Raml
import collection.JavaConverters._
import org.fusesource.scalate._
import java.io.PrintWriter
import java.io.StringWriter
import java.io.File
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import gensdk.Package
import generator.SDKGenerator
import generator.PhpSDKGenerator
import generator.JsSDKGenerator
import generator.JavaSDKGenerator
import scala.util.Try

object Main {

	def main(args: Array[String]): Unit = {
				
		/*Raml file we are going to use */
		var buf = Try(Source.fromFile("data/example.raml"))
		val source: String = buf.get.getLines mkString "\n"

		/*Package object representing the SDK to be created*/
		val pack = new Package
		
		/*Result of parsing the raml object */
		val raml: Raml = new RamlDocumentBuilder().build(source)
		
		/*Create package*/
		pack.parse(raml)
		
		/*Generator representing the type of sdk to use*/
		val generator = new PhpSDKGenerator("localhost:9000/api")
		
		/*Generate sdk code*/
		generator.generate(pack,"php")
		
	}
}