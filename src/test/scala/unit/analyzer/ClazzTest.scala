package unit.analyzer

import org.scalatest.FunSuite
import analyser.Method
import analyser.RestType
import analyser.Clazz

import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class ClazzTest extends FunSuite {
	test("extract id from url and create a correct name") {
		var clazz = new Clazz("/events", "", "", "", null)
		assert(clazz.name.equals("Events"))

		clazz = new Clazz("/events/{id}/errors", "", "", "", null)
		assert(clazz.name.equals("EventErrors"))
	}
}