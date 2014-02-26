package pl.sointeractive.isaacloud.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(AuthenticationTest.class);
		suite.addTestSuite(ConnectorTest.class);
		suite.addTestSuite(WrapperTest.class);
		//$JUnit-END$
		return suite;
	}

}
