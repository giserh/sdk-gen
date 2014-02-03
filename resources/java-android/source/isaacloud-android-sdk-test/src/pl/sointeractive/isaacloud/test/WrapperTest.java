package pl.sointeractive.isaacloud.test;

import java.io.IOException;

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
		wrapper = new FakeWrapper();
	}

	public void testGetAdminUsers() {
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
		assertTrue(response.getResponseCode() >= 200
				&& response.getResponseCode() <= 206);
	}

	public void testPostAdminAchievements() {
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
		assertTrue(response.getResponseCode() >= 200
				&& response.getResponseCode() <= 206);
	}
}