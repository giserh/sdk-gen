import org.junit.runner.RunWith
import org.junit.BeforeClass
import org.junit.runners.Suite.SuiteClasses

@RunWith(value = classOf[org.junit.runners.Suite])
@SuiteClasses(value = Array(classOf[MainTest]))
object UnitTests {
@BeforeClass
	def startApp() {
		println("test");
	}
}