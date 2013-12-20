package unit.analyzer

import org.scalatest.FunSuite
import analyser.Method
import analyser.RestType
import analyser.Clazz

class ClazzTest extends FunSuite  {
test("extract id from url and create a correct name"){
		var clazz = new Clazz("/events","","","",null)
		assert(clazz.name.equals("Events"))
		
		clazz = new Clazz("/events/{id}/errors","","","",null)
		assert(clazz.name.equals("EventErrors"))
	}
}