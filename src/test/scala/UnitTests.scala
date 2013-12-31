import org.junit.runner.RunWith
import org.junit.BeforeClass
import org.junit.runners.Suite.SuiteClasses
import unit.analyzer.AnalyzerTest
import unit.analyzer.ClazzTest
import unit.analyzer.MethodTest
import unit.generator.PhpGeneratorTest



@RunWith(value = classOf[org.junit.runners.Suite])
@SuiteClasses(value = Array(classOf[AnalyzerTest],classOf[ClazzTest],classOf[MethodTest], classOf[PhpGeneratorTest])) 
class UnitTests() {}

object UnitTests {

	@BeforeClass
	def startApp() {
		println("Starting unit tests");
	}
}

