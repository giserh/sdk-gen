package pl.sointeractive.isaacloud.test;

import java.util.HashMap;
import java.util.Map;

import pl.sointeractive.isaacloud.Connector;
import android.test.AndroidTestCase;

public class ConnectorTest extends AndroidTestCase{

	static final String TAG = "ConnectorTest";
	
	public void testConstructor(){
		String baseUrl = "http://api.isaacloud.com";
		String oauthUrl = "http://oauth.isaacloud.com";
		String version = "version1";
		Map<String, String> config = new HashMap<String, String>();
		config.put("clientId", "86");
		config.put("secret", "c777bffe0d377a54e5d46a21cace834");
		Connector con = new Connector(baseUrl, oauthUrl, version, config);
		
		assertNotNull(con);
	}
	
}