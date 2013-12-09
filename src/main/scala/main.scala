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

object Main {

	def main(args: Array[String]): Unit = {

		var buf: Option[BufferedSource] = None

		buf = Some(Source.fromFile("data/example.raml"))
		val source: String = buf.get.getLines mkString "\n"

		val pack = new Package
		
		val raml: Raml = new RamlDocumentBuilder().build(source)
		
		//get package
		pack.parse(raml)
		
		val generator = new JavaSDKGenerator
		
		//generate code
		generator.generate(pack)
		
		
		

	}
}