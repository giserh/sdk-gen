package pl.sointeractive.isaacloud.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import pl.sointeractive.isaacloud.Connector;
import pl.sointeractive.isaacloud.exceptions.InvalidConfigException;
import android.test.AndroidTestCase;

public class ConnectorTest extends AndroidTestCase {

	static final String TAG = "ConnectorTest";

	Connector con;

	public void basicSetup() {
		String baseUrl = "http://api.isaacloud.com";
		String oauthUrl = "http://oauth.isaacloud.com";
		String version = "version1";
		Map<String, String> config = new HashMap<String, String>();
		config.put("clientId", "86");
		config.put("secret", "c777bffe0d377a54e5d46a21cace834");
		try {
			con = new Connector(baseUrl, oauthUrl, version, config);
		} catch (InvalidConfigException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testConstructor() {
		basicSetup();
		assertNotNull(con);
	}

	public void testConstructorForConfigExceptionsNoClientId() {
		String baseUrl = "http://api.isaacloud.com";
		String oauthUrl = "http://oauth.isaacloud.com";
		String version = "version1";
		Map<String, String> config = new HashMap<String, String>();
		config.put("secret", "c777bffe0d377a54e5d46a21cace834");
		try {
			new Connector(baseUrl, oauthUrl, version, config);
			assertTrue("No exception", false);
		} catch (InvalidConfigException e) {
			assertEquals("clientId", e.getMissingConfig());
		}
	}

	public void testConstructorForConfigExceptionsNoSecret() {
		String baseUrl = "http://api.isaacloud.com";
		String oauthUrl = "http://oauth.isaacloud.com";
		String version = "version1";
		Map<String, String> config = new HashMap<String, String>();
		config.put("clientId", "86");
		try {
			new Connector(baseUrl, oauthUrl, version, config);
			assertTrue("No exception", false);
		} catch (InvalidConfigException e) {
			assertEquals("secret", e.getMissingConfig());
		}
	}

	public void testCallServiceInvalidUri() {
		basicSetup();
		String uri = "fakeUri";
		String methodName = "GET";
		final Map<String, Object> parameters = new HashMap<String, Object>();
		String body = null;
		try {
			con.callService(uri, methodName, parameters, body);
			assertTrue("No exception", false);
		} catch (IOException e) {
			assertTrue(true);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testCallServiceInvalidParameters() {
		basicSetup();
		String uri = "/admin/users/{userId}/gainedachievements";
		String methodName = "GET";
		final Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("fakeParementer", 0);
		String body = null;
		try {
			con.callService(uri, methodName, parameters, body);
			assertTrue("No exception", false);
		} catch (IOException e) {
			assertTrue(true);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testCallServiceInvalidBody() {
		basicSetup();
		JSONObject json = new JSONObject();
		try {
			json.put("fakeKey1", "fakeValue1");
			json.put("fakeKey2", "fakeValue2");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String uri = "/admin/achievements";
		String methodName = "POST";
		final Map<String, Object> parameters = new HashMap<String, Object>();
		String body = json.toString();
		try {
			con.callService(uri, methodName, parameters, body);
			assertTrue("No exception", false);
		} catch (IOException e) {
			assertTrue(true);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testCallServiceInvalidMethod() {
		basicSetup();
		String uri = "/admin/users";
		String methodName = "POST";
		final Map<String, Object> parameters = new HashMap<String, Object>();
		String body = null;
		try {
			con.callService(uri, methodName, parameters, body);
			assertTrue("No exception", false);
		} catch (IOException e) {
			assertTrue(true);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}