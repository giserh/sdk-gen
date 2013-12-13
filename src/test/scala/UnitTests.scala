import org.junit.runner.RunWith
import org.junit.BeforeClass
import org.junit.runners.Suite.SuiteClasses



@RunWith(value = classOf[org.junit.runners.Suite])
@SuiteClasses(value = Array()) 
class UnitTests() {}

object UnitTests {

	@BeforeClass
	def startApp() {
		println("Starting unit tests");
	}
}

