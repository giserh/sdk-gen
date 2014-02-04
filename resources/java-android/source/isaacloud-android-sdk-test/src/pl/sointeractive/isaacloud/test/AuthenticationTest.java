package pl.sointeractive.isaacloud.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;

import pl.sointeractive.isaacloud.Connector;
import pl.sointeractive.isaacloud.exceptions.InvalidConfigException;
import android.test.AndroidTestCase;
import android.util.Log;

public class AuthenticationTest extends AndroidTestCase{

	static final String TAG = "AuthenticationTest";
	
	public void testAunthentication(){
		String baseUrl = "http://api.isaacloud.com";
		String oauthUrl = "http://oauth.isaacloud.com";
		String version = "version1";
		Map<String, String> config = new HashMap<String, String>();
		config.put("clientId", "86");
		config.put("secret", "c777bffe0d377a54e5d46a21cace834");
		Connector con = null;
		try {
			con = new Connector(baseUrl, oauthUrl, version, config);
		} catch (InvalidConfigException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String authentication = null;
		try {
			authentication = con.getAuthentication();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.d(TAG, authentication);
		assertNotNull(authentication);
	}

}
