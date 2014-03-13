package pl.sointeractive.isaacloud;

import java.io.IOException;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import pl.sointeractive.isaacloud.connection.HttpResponse;
import pl.sointeractive.isaacloud.exceptions.InvalidConfigException;
import android.content.Context;

/**
 * This class imitates the wrapper class, that is created from the SDK
 * generator.
 * 
 * @author Mateusz Renes
 * 
 */
public class FakeWrapper {

	private Connector con;

	public FakeWrapper(Context appContext, String baseUrl, String oauthUrl,
			String version, Map<String, String> config) {
		try {
			this.con = new Connector(appContext, baseUrl, oauthUrl, version,
					config);
		} catch (InvalidConfigException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public HttpResponse getAdminUsers(Map<String, Object> parameters)
			throws IOException, JSONException {
		String uri = "/admin/users";
		String methodName = "GET";
		String body = null;
		return con.callService(uri, methodName, parameters, body);
	}

	public HttpResponse getAdminUsersGainedAchievements(int userId,
			Map<String, Object> parameters) throws IOException, JSONException {
		String uri = "/admin/users/" + userId + "/gainedachievements";
		String methodName = "GET";
		String body = null;
		return con.callService(uri, methodName, parameters, body);
	}

	public HttpResponse postAdminAchievements(JSONObject jsonBody,
			Map<String, Object> parameters) throws IOException, JSONException {
		String uri = "/admin/achievements";
		String methodName = "POST";
		String body = jsonBody.toString();
		return con.callService(uri, methodName, parameters, body);
	}

	public HttpResponse postQueuesEvent(JSONObject jsonBody,
			Map<String, Object> parameters) throws IOException, JSONException {
		String uri = "/queues/events";
		String methodName = "POST";
		String body = jsonBody.toString();
		return con.callService(uri, methodName, parameters, body);
	}

	public HttpResponse getQueuesNotifications(Map<String, Object> parameters)
			throws IOException, JSONException {
		String uri = "/queues/notifications";
		String methodName = "GET";
		String body = null;
		return con.callService(uri, methodName, parameters, body);
	}

	public HttpResponse getCacheLeaderboard(int leaderboardId,
			Map<String, Object> parameters) throws IOException, JSONException {
		String uri = "/cache/leaderboards/" + leaderboardId;
		String methodName = "GET";
		String body = null;
		return con.callService(uri, methodName, parameters, body);
	}

	public HttpResponse getAdminUserAchievements(int userId,
			Map<String, Object> parameters) throws IOException, JSONException {
		String uri = "/admin/users/" + userId + "/gainedachievements";
		String methodName = "GET";
		String body = null;
		return con.callService(uri, methodName, parameters, body);
	}

	public HttpResponse getAdminAchievements(Map<String, Object> parameters)
			throws IOException, JSONException {
		String uri = "/admin/achievements";
		String methodName = "GET";
		String body = null;
		return con.callService(uri, methodName, parameters, body);
	}

	public HttpResponse getAdminUser(int userId, Map<String, Object> parameters)
			throws IOException, JSONException {
		String uri = "/admin/users/" + userId;
		String methodName = "GET";
		String body = null;
		return con.callService(uri, methodName, parameters, body);
	}

	public HttpResponse postAdminUser(JSONObject jsonBody,
			Map<String, Object> parameters) throws IOException, JSONException {
		String uri = "/admin/users";
		String methodName = "POST";
		String body = jsonBody.toString();
		return con.callService(uri, methodName, parameters, body);
	}

	public HttpResponse getAdminLeaderboardRecalculate(int leaderboardId,
			Map<String, Object> parameters) throws IOException, JSONException {
		String uri = "/admin/leaderboards/" + leaderboardId + "/recalculate";
		String methodName = "GET";
		String body = null;
		return con.callService(uri, methodName, parameters, body);
	}

}
