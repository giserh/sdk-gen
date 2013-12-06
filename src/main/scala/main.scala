import org.raml.parser.visitor.RamlDocumentBuilder
import scala.io.BufferedSource
import scala.io.Source
import org.raml.model.Raml
import collection.JavaConverters._

object Main {
  
  def main(args: Array[String]):Unit = {
    var buf: Option[BufferedSource] = None
    
    buf = Some(Source.fromFile("data/example.raml"))
    val source: String = buf.get.getLines mkString "\n"
    
    var raml:Raml = new RamlDocumentBuilder().build(source);
    
    var version = raml.getVersion()
    println(raml)
    println(s"test $version")
    val res=raml.getResources().asScala
//    println(res)
    
    res.foreach{
    	a => println(a._2.getResources())
    }
    
  } 
}