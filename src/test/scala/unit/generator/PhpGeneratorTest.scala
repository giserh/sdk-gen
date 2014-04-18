package unit.generator

import org.scalatest.FunSuite

import analyser.Clazz
import analyser.Method
import analyser.Package
import analyser.RestType
import generator.PhpSDKGenerator

import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class PhpGeneratorTest extends FunSuite {
	test("generate methods") {
		val generator = new PhpSDKGenerator
		val ret = generator.generateMethod(new Method(RestType.GET, "/events/{id}"), "resources/php/Method.ssp")
		assert(ret.contains("id"))
		assert(ret.contains("/events/{id}"))
		assert(ret.contains("getOneEvent"))
	}

	test("generate classes") {
		val generator = new PhpSDKGenerator
		val ret = generator.generateClass(new Clazz("/events", "api.isaacloud.com", "oauth.isaacloud.com", "0.1"), "resources/php/Class.ssp", List())
		assert(ret.contains("public function __construct($config)"))
		assert(ret.contains("""parent::__construct( "http://api.isaacloud.com", "http://oauth.isaacloud.com", "0.1", $config);"""))
	}

	test("generate package") {
		val generator = new PhpSDKGenerator
		val ret = generator.generatePackage(new Package("api.isaacloud.com", "application/json", null, "oauth.isaacloud.com"), "resources/php/Package.ssp", List())
		assert(ret.contains("<?php"))
		assert(ret.contains("include 'Connector.php';"))
		assert(ret.contains("namespace IsaaCloud;"))
	}
}