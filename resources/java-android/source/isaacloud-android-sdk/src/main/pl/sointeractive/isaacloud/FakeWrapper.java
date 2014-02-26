package pl.sointeractive.isaacloud;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import pl.sointeractive.isaacloud.connection.HttpResponse;
import pl.sointeractive.isaacloud.exceptions.InvalidConfigException;

/**
 * This class imitates the wrapper class, that is created from the SDK generator.
 * @author Mateusz Renes
 *
 */
public class FakeWrapper {
	
	private Connector con;
	
	public FakeWrapper(Context appContext, String baseUrl,String oauthUrl,String version,Map<String, String> config){
		try {
			this.con = new Connector(appContext,baseUrl, oauthUrl, version, config);
		} catch (InvalidConfigException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public HttpResponse getAdminUsers() throws IOException, JSONException{
		String uri = "/admin/users";
		String methodName = "GET";
		final Map<String, Object> parameters = new HashMap<String, Object>();
		String body = null;
		return con.callService(uri, methodName, parameters, body);
	}
	
	public HttpResponse getAdminUsersIdGainedachievements(int userId) throws IOException, JSONException{
		String uri = "/admin/users/{userId}/gainedachievements";
		String methodName = "GET";
		final Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("userId", userId);
		String body = null;
		return con.callService(uri, methodName, parameters, body);
	}
	
	public HttpResponse postAdminAchievements(JSONObject jsonBody) throws IOException, JSONException{
		String uri = "/admin/achievements";
		String methodName = "POST";
		final Map<String, Object> parameters = new HashMap<String, Object>();
		String body = jsonBody.toString();
		return con.callService(uri, methodName, parameters, body);
	}
	
	public HttpResponse postEvent(JSONObject jsonBody) throws IOException, JSONException{
		String uri = "/queues/events";
		String methodName = "POST";
		final Map<String, Object> parameters = new HashMap<String, Object>();
		String body = jsonBody.toString();
		return con.callService(uri, methodName, parameters, body);
	}
	
	public HttpResponse getNotifications() throws IOException, JSONException{
		String uri = "/queues/notifications";
		String methodName = "GET";
		final Map<String, Object> parameters = new HashMap<String, Object>();
		String body = null;
		return con.callService(uri, methodName, parameters, body);
	}
	
	public HttpResponse getLeaderboard(int leaderboardId) throws IOException, JSONException{
		String uri = "/cache/leaderboards/{leaderboardId}";
		String methodName = "GET";
		final Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("leaderboardId", leaderboardId);
		String body = null;
		return con.callService(uri, methodName, parameters, body);
	}
	
	public HttpResponse getUserAchievements(int userId) throws IOException, JSONException{
		String uri = " /cache/users/{userId}/achievements";
		String methodName = "GET";
		final Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("userId", userId);
		String body = null;
		return con.callService(uri, methodName, parameters, body);
	}
	
	public HttpResponse getUser(int userId) throws IOException, JSONException{
		String uri = " /cache/users/{userId}";
		String methodName = "GET";
		final Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("userId", userId);
		String body = null;
		return con.callService(uri, methodName, parameters, body);
	}
	
	
}
