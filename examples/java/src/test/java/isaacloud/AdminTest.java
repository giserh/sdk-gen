package isaacloud;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;

/*
 * These tests are not always reproducible, DELETE, PUT, PATCH methods depend on certain entities existing in application.
 */
public class AdminTest {

	static Admin admin;

	/**
	 * Setup Admin class, credentials are static for the entire library in order to reduce the number of oauth calls.
	 */
	@BeforeClass
	public static void setup() {
		Map<String, String> config = new HashMap<String, String>();

		config.put("secret", "98bb5d47c897b8aef0ce2b5af38f5af7");
		config.put("clientId", "2");

		admin = new Admin(config);
	}

	/**
	 * Create new User
	 */
	@Test
	public void testPostUsers() {

		String body = "{" + "\"email\": \"" + new Random().nextLong()
				+ "zbyszek@gmail.com\"," + "\"password\": \"abcd\"" + "}";

		String result = admin.postUsers(body);

		System.out.println("Posted " + result);
	}
	
	/**
	 * Get one user.
	 */
	@Test
	public void testGetOneUser() {

		String result = admin.getOneUser("1");

		System.out.println("Got " + result);
	}
	
	/**
	 * Delete one user
	 */
	@Test
	public void testDeleteOneUser() {

		String result = admin.deleteOneUser("2");

		System.out.println("Deleted " + result);
	}

	/**
	 * Get a list of users
	 */
	@Test
	public void testGetUsers() {

		String result = admin.getUsers("1,2", 0l, "id:DESC", "firstName,lastName,gender","12,23,22", 10l);

		System.out.println("Got list "+result);
	}
	
	/**
	 * Delete a friend from user.
	 */
	@Test
	public void testDeleteUserFriend() {

		String result = admin.deleteOneUserFriend("1", "4");

		System.out.println("Deleted friend "+result);
	}
	
	/**
	 * Add a friend to user.
	 */
	@Test
	public void testPutUserFriend() {

		String result = admin.putOneUserFriend("1", "4", "{}");

		System.out.println("Added friend "+result);
	}

}
