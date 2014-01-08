import isaacloud.Cache;

import java.util.HashMap;
import java.util.Map;

public class MainApp {

	public static void main(String[] args) throws Exception {
		Map<String,String> config = new HashMap<String,String>();
		
		config.put("secret", "98bb5d47c897b8aef0ce2b5af38f5af7");
		config.put("clientId", "2");
		
		Map<String,Object > params = new HashMap<String,Object>();
		params.put("id", 1);
		params.put("fields", "updateAt,email");
		
		Cache connector = new Cache(config);
		String result = connector.getUsers(null, 0l, null, null, null, 10l);
		System.out.println(result);
	}

}
