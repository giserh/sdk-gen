package unit

import collection.mutable.Stack
import org.scalatest._

class SampleScalaTest extends FunSuite with Matchers {

	test("pop values in last-in-first-out order") {
    val stack = new Stack[Int]
    stack.push(1)
    stack.push(2)
    stack.pop() should be (2)
    stack.pop() should be (1)
  }

  test("throw NoSuchElementException if an empty stack is popped"){
    val emptyStack = new Stack[Int]
    a [NoSuchElementException] should be thrownBy {
      emptyStack.pop()
    } 
  }
  
}