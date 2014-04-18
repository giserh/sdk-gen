package unit.generator

import org.scalatest.FunSuite
import analyser.Clazz
import analyser.Method
import analyser.Package
import analyser.RestType
import generator.PhpSDKGenerator
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import generator.JavaSDKGenerator

@RunWith(classOf[JUnitRunner])
class JavaSDKGeneratorTest extends FunSuite{

	test("generate methods") {
		val generator = new JavaSDKGenerator
		val ret = generator.generateMethod(new Method(RestType.GET, "/events/{id}"), "resources/java/Method.ssp")
		assert(ret.contains("id"))
		assert(ret.contains("/events/{id}"))
		assert(ret.contains("getOneEvent"))
		assert(ret.contains("public String"))
	}

	test("generate classes") {
		val generator = new PhpSDKGenerator
		val base = "api.isaacloud.com"
		val oauth = "oauth.isaacloud.com"
		val version = "0.1"
		val ret = generator.generateClass(new Clazz("/events", base, oauth, version), "resources/java/Class.ssp", List())
		assert(ret.contains("public class Events extends Connector{"))
		assert(ret.contains(s"""super( "http://${base}", "http://${oauth}", "${version}", config);"""))
	}
	
}