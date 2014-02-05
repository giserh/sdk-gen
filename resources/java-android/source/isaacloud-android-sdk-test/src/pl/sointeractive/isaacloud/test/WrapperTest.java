package pl.sointeractive.isaacloud.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import pl.sointeractive.isaacloud.FakeWrapper;
import pl.sointeractive.isaacloud.connection.HttpResponse;
import android.test.AndroidTestCase;

public class WrapperTest extends AndroidTestCase {

	static final String TAG = "WrapperTest";
	FakeWrapper wrapper;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testGetAdminUsers() {
		String baseUrl = "http://api.isaacloud.com";
		String oauthUrl = "http://oauth.isaacloud.com";
		String version = "version1";
		Map<String, String> config = new HashMap<String, String>();
		config.put("clientId", "86");
		config.put("secret", "c777bffe0d377a54e5d46a21cace834");
		wrapper = new FakeWrapper(baseUrl, oauthUrl, version, config);

		HttpResponse response = null;
		try {
			response = wrapper.getAdminUsers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(response);
		assertTrue(response.getResponseCode() >= 200
				&& response.getResponseCode() <= 206);
	}

	public void testGetAdminUsersIdGainedachievements() {
		String baseUrl = "http://api.isaacloud.com";
		String oauthUrl = "http://oauth.isaacloud.com";
		String version = "version1";
		Map<String, String> config = new HashMap<String, String>();
		config.put("clientId", "86");
		config.put("secret", "c777bffe0d377a54e5d46a21cace834");
		wrapper = new FakeWrapper(baseUrl, oauthUrl, version, config);

		HttpResponse response = null;
		try {
			response = wrapper.getAdminUsersIdGainedachievements(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(response);
		assertNotNull(response.getMethod());
		assertNotNull(response.getResponseString());
		assertTrue(response.getResponseCode() >= 200
				&& response.getResponseCode() <= 206);
	}

	public void testPostAdminAchievements() {
		String baseUrl = "http://api.isaacloud.com";
		String oauthUrl = "http://oauth.isaacloud.com";
		String version = "version1";
		Map<String, String> config = new HashMap<String, String>();
		config.put("clientId", "86");
		config.put("secret", "c777bffe0d377a54e5d46a21cace834");
		wrapper = new FakeWrapper(baseUrl, oauthUrl, version, config);

		HttpResponse response = null;
		try {
			JSONObject jsonBody = new JSONObject();
			jsonBody.put("name", "Example name");
			jsonBody.put("label", "Example label");
			response = wrapper.postAdminAchievements(jsonBody);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(response);
		assertNotNull(response.getMethod());
		assertNotNull(response.getResponseString());
		assertTrue(response.getResponseCode() >= 200
				&& response.getResponseCode() <= 206);
	}

	public void testFakeApiUrl() {
		String baseUrl = "fakeUrl";
		String oauthUrl = "http://oauth.isaacloud.com";
		String version = "version1";
		Map<String, String> config = new HashMap<String, String>();
		config.put("clientId", "86");
		config.put("secret", "c777bffe0d377a54e5d46a21cace834");
		wrapper = new FakeWrapper(baseUrl, oauthUrl, version, config);

		try {
			wrapper.getAdminUsers();
			assertTrue("No exception", false);
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void testFakeOAuthUrl() {
		String baseUrl = "http://api.isaacloud.com";
		String oauthUrl = "fakeUrl";
		String version = "version1";
		Map<String, String> config = new HashMap<String, String>();
		config.put("clientId", "86");
		config.put("secret", "c777bffe0d377a54e5d46a21cace834");
		wrapper = new FakeWrapper(baseUrl, oauthUrl, version, config);

		try {
			wrapper.getAdminUsers();
			assertTrue("No exception", false);
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}