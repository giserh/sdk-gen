import org.junit.runner.RunWith
import org.junit.BeforeClass
import org.junit.runners.Suite.SuiteClasses
import unit.SimpleTest


@RunWith(value = classOf[org.junit.runners.Suite])
@SuiteClasses(value = Array( classOf[SimpleTest]))
class UnitTests() {}

object UnitTests {

	@BeforeClass
	def startApp() {
		println("Starting unit tests");
	}
}

