package isaacloud;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;


public class CacheTest {

	static Cache cache;
	
	@BeforeClass 
	public static void setup(){
		Map<String, String> config = new HashMap<String, String>();

		config.put("secret", "98bb5d47c897b8aef0ce2b5af38f5af7");
		config.put("clientId", "2");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", 1);
		params.put("fields", "updateAt,email");

		cache = new Cache(config);
	}
	
	@Test
	public void testGetUsers() {
		
		String result = cache.getUsers("1,2", 0l, "id:DESC", "firstName,lastName,gender","12,23,22", 10l);

		System.out.println("Got list "+result);
	}
	
	@Test
	public void testGetOneUser() {
		
		String result = cache.getOneUser("1",  "firstName,lastName,gender");

		System.out.println("Got one "+result);
	}
	
	
}
