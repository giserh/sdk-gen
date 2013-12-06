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
import org.slf4j.LoggerFactory;
import gensdk.Package

object Main {

	def main(args: Array[String]): Unit = {

		var buf: Option[BufferedSource] = None

		buf = Some(Source.fromFile("data/example.raml"))
		val source: String = buf.get.getLines mkString "\n"

		val generator = new Package
		
		val raml: Raml = new RamlDocumentBuilder().build(source)
		
		//get package
		generator.parse(raml)
		
		//generate code
		
		//		var raml: Raml = new RamlDocumentBuilder().build(source);
		//		println(raml.getBasePath())
		//		var version = raml.getVersion()
		//		println(s"test $version")
		//		val res = raml.getResources().asScala
		//		//    println(res)
		//
		//		args.foreach(println)
		//
		//		val engine = new TemplateEngine		
		//
		//		//  val output = engine.layout("/path/to/template.ssp")
		//		//	val text = """ <%@ var name:(String,String) %> <%@ var city:String %> <p> Hello ${name._1} ${name._2}, from ${city}. </p>"""
		//		val file = "./resources/java/default.ssp"	
		//			
		//		//		val output = engine.compileScaml(text, Nil) //engine.layout("""C:/Users/Tomasz Godzik/Documents/work_projects/sdk-gen/resources/java/default.scaml""", Map("name" -> ("Hiram", "Chirino"), "city" -> "Tampa"))
		//		val valueMap=Map("name" -> ("Hiram", "Chirino"), "city" -> "Tampa")
		//		
		//		val templ=engine.load(file)
		//		
		//		
		//		val buffer = new PrintWriter("SampleClass.scala")
		//		val context = new DefaultRenderContext("/",engine,buffer)
		//		context.attributes("name") = ("Hiram", "Chirino")
		//		context.attributes("city") = "Tampa"
		//		val out=templ.render(context)		
		//		
		//		buffer.flush()
		//		
		//		// println(output.)
		//		//    res.foreach{
		//		//    	a => println(a._2.getResources())
		//		//    }

	}
}