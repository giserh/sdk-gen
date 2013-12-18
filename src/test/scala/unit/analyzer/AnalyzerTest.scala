package unit.analyzer

import org.scalatest.Matchers
import org.scalatest.FunSuite
import java.util.Stack
import org.raml.model.Resource
import org.raml.model.Raml
import org.raml.parser.visitor.RamlDocumentBuilder
import analyser.Analyser

class AnalyzerTest extends FunSuite with Matchers {
	
val simpleRaml = 
"""title: IsaaCloud REST API
version: v 1.02
protocols: [ HTTPS ]
baseUri: https://isaacloud.com/{version}/api
mediaType:  application/json
securedBy: [oauth_2_0]
/queue:
 displayName: Queue
 /events:
  displayName: QueueEvents
  get:
   is: [ paginator, fields, ordering]
   securedBy: [oauth_2_0]
   description: Get events for a client.
   responses:
    200:
     body:
      schema: events
  post:
   is: [fields]
   securedBy: [oauth_2_0]
   description: Create a new event.
   responses:
    200:
     body:
      schema: events
"""
	
  test("analyze raml") {  	  
	  
	  val raml: Raml = new RamlDocumentBuilder().build(simpleRaml)
	  val pack = Analyser.analyseRaml(raml)
	  
	  assert(pack.docs("version").contains("v 1.02"))
	  assert(pack.clazzes.length > 0)
	  assert(pack.baseUri.contains("https://isaacloud.com/{version}/api"))
	  assert(pack.mediaType.contains("application/json"))
  }

  test("analyze subresource") {
	  val resource = new Resource()	  
  }

  
}