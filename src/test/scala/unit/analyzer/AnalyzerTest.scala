package unit.analyzer

import org.scalatest.Matchers
import org.scalatest.FunSuite
import java.util.Stack
import org.raml.model.Resource
import org.raml.model.Raml
import org.raml.parser.visitor.RamlDocumentBuilder
import analyser.Analyser
import analyser.Clazz
import org.scalatest.mock.MockitoSugar
import org.mockito.Mockito._
import org.mockito.Matchers._
import analyser.Method
import analyser.RestType
import collection.JavaConverters._

class AnalyzerTest extends FunSuite with Matchers  with MockitoSugar {
	
val simpleRaml = 
"""title: IsaaCloud REST API
title: IsaaCloud REST API
version: "0.1"
protocols: [ HTTPS ]
baseUri: api.isaacloud.com
mediaType:  application/json
securitySchemes:
  - oauth_2_0:
      description: |
        In authentication case we use OAuth2 serwer implementation, is an open standard provides a method for clients to access server resources on behalf of a resource owner.
      type: OAuth 2.0
      settings:
        accessTokenUri: oauth.isaacloud.com/token
        authorizationUri: oauth.isaacloud.com/authorize
        baseUri: oauth.isaacloud.com
securedBy: [oauth_2_0]
traits:
 - paginator:
    queryParameters:
     offset:
      description: Number of returning items
      type: number
      minLength: 1
     limit:
      description: Starting position
      type: number
      minLength: 1
 - groups:
    queryParameters:
     groups:
      description: The groups we want to narrow down our search to
      type: string
      example: 1,2,4,5
 - segments:
    queryParameters:
     segments:
      description: The segments we want to narrow down our search to.
      type: string
      example: 1,2,4,5
 - fields:
    queryParameters:
     fields:
      description: The fields we want to show in our result
      type: string
      example: id,name,description
 - ordering:
    queryParameters:
     order:
      description: The fields we want to order by and the type of ordering.
      type: string
      example: "id,name,description"
/queue:
 displayName: Queue
 /events:
  displayName: QueueEvents
  get:
   is: [ paginator, fields, ordering]
   description: Get events for a client.
   responses:
    200:
     body:
      schema: events
"""
	
  test("Testing analyzeRaml method") {  	  
	  
	  val raml: Raml = new RamlDocumentBuilder().build(simpleRaml)
	  val pack = Analyser.analyseRaml(raml)
	  
	  assert(pack.docs("version").contains("0.1"))
	  assert(pack.docs("title").contains("IsaaCloud REST API"))
	  assert(pack.baseOauthUri.contains("oauth.isaacloud.com"))
	  assert(pack.clazzes.length > 0)
	  assert(pack.baseUri.contains("api.isaacloud.com"))
	  assert(pack.mediaType.contains("application/json"))
  }

  test("Testing createMethods method") {
	  val raml: Raml = new RamlDocumentBuilder().build(simpleRaml)
	  val pack = Analyser.analyseRaml(raml)
	  
	  val clazz = pack.clazzes(0)
	  	  
	  assert(clazz.methods.length==1)
	  val meth = clazz.methods(0)
	  assert(meth.restType == RestType.GET)
	  assert(meth.url == "/queue/events")
	  assert(meth.docs("").contains("Get events for a client"))
	  assert(meth.query.contains("fields"))
	  assert(meth.query.contains("limit"))
	  assert(meth.query.contains("offset"))
	  assert(meth.query.contains("order"))
  }
  
  test("Testing analyseResource method"){
  	  val raml: Raml = new RamlDocumentBuilder().build(simpleRaml)
	  val pack = Analyser.analyseRaml(raml)
	  assert(pack.clazzes.length == 1)
	  
	  val clazz = pack.clazzes(0)
	  assert(clazz.baseUrl.contains("api.isaacloud.com"))
	  assert(clazz.oauthUrl.contains("oauth.isaacloud.com"))
	  assert(clazz.version.contains("0.1"))
	  assert(clazz.name.contains("Queue"))
	  
	  
  } 
  
  
}