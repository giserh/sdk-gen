package isaacloud;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

public class QueueTest {
	static Queue queue;
	
	@BeforeClass 
	public static void setup(){
		Map<String, String> config = new HashMap<String, String>();

		config.put("secret", "98bb5d47c897b8aef0ce2b5af38f5af7");
		config.put("clientId", "2");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", 1);
		params.put("fields", "updateAt,email");

		queue = new Queue(config);
	}
	
	@Test
	public void testGetEvents() {
		
		String result = queue.getEvents(0l, 10l, null , null);
		System.out.println("Got list "+result);
	}
	
	@Test
	public void testPostEvent() {
		 String body="{\"subjectId\" : 12,"
                 +"\"subjectType\" : \"USER\","
                 +"\"sourceId\" : 55,"
                 +"\"body\" : {\"armenia\" : \"jerewan\"},"
                 +"\"priority\" : \"PRIORITY_HIGH\","
                 +" \"type\" : \"NORMAL\"}";
		 
		String result = queue.postEvents(body);

		System.out.println("Created one "+result);
	}
	
	@Test
	public void testGetOneEvent() {
		
		String result = queue.getOneEvent("52ceb080e4b04aa01f955dd7");

		System.out.println("Got one "+result);
	}
	
}
