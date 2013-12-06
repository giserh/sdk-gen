package unit

import org.junit.Test
import org.junit.Assert._

class SimpleTest {
	
	@Test
	def test1(){
		val a="a"
		assertEquals(a,"a")	
	}
	
	@Test
	def test2(){
		val b="b"
		assertEquals(b,"b")	
	}

}