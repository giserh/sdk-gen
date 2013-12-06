package gensdk

import org.raml.model.Raml
import org.raml.parser.visitor.RamlDocumentBuilder
import collection.JavaConverters._
class Package {

	
	
	def parse (raml : Raml){
		
		
		val version = raml.getVersion()
		println(s"Generating SDK for API version $version")
		
		val traits = raml.getTraits()
		
		analyseTraits(traits)
	}
	
	def analyseTraits( traits : java.util.List[java.util.Map[String,org.raml.model.Template]]){
		
		
		traits.asScala.foreach(println)
	}
}