package pl.sointeractive.isaacloud.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import pl.sointeractive.isaacloud.Connector;
import pl.sointeractive.isaacloud.FakeWrapper;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class TestActivity extends Activity{
	private static final String TAG = "TestActivity";
	
	FakeWrapper wrapper;
	Connector connector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String baseUrl = "http://api.isaacloud.com";
		String oauthUrl = "http://oauth.isaacloud.com";
		String version = "version1";
		Map<String, String> config = new HashMap<String, String>();
		config.put("clientId", "86");
		config.put("secret", "c777bffe0d377a54e5d46a21cace834");
		wrapper = new FakeWrapper(getApplicationContext(), baseUrl, oauthUrl, version, config);
		
		
		runTestAsyncTask();
	}
	
	public void runTestAsyncTask(){
		new AsyncTask<Object, Object, Object>(){
			@Override
			protected Object doInBackground(Object... params) {
				test();
				return null;
			}
		}.execute();
	}
	
	
	
	public void test(){
		try {
			String result;
			
			result = wrapper.getAdminUsers().toString();
			Log.d(TAG, result);
			
			result = wrapper.getAdminUsersIdGainedachievements(1).toString();
			Log.d(TAG, result);
			
			JSONObject json = new JSONObject();
			json.put("name", "Example name");
			json.put("label", "Example label");
			result = wrapper.postAdminAchievements(json).toString();
			Log.d(TAG, result);
			
		} catch (IOException e) {
			e.printStackTrace();
			Log.e(TAG, "IOException");
		} catch (JSONException e) {
			e.printStackTrace();
			Log.e(TAG, "JSONException");
		} 
	}
	
	
}
