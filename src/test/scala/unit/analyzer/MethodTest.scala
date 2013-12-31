package unit.analyzer

import org.scalatest.FunSuite
import analyser.Method
import analyser.RestType
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class MethodTest extends FunSuite {

	test("test creating a name") {
		val method = new Method(RestType.GET, "/queue/events", null, List())
		assert(method.name.equals("getQueueEvents"))
	}

	test("extract id from url and create a correct name") {
		var method = new Method(RestType.GET, "/queues/{queueId}/events/{eventId}", null, List())
		assert(method.query.contains("queueId"))
		assert(method.query.contains("eventId"))
		assert(method.name.equals("getOneQueueEvent"))

		method = new Method(RestType.GET, "/queues/{queueId}/events", null, List())
		assert(method.query.contains("queueId"))
		assert(method.name.equals("getQueueEvents"))

		method = new Method(RestType.GET, "/queues/{queueId}", null, List())
		assert(method.query.contains("queueId"))
		assert(method.name.equals("getOneQueue"))

		method = new Method(RestType.GET, "/queues", null, List())
		assert(method.name.equals("getQueues"))

	}
}