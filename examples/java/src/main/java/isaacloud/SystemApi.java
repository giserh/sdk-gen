package isaacloud;

import java.util.Map;
import java.util.HashMap;

/**
 */
public class SystemApi extends Connector {

	public SystemApi(Map<String, String> config) {
		super("http://api.isaacloud.com", "http://oauth.isaacloud.com", "0.1",
				config);
	}

	/**
	 * Access to url : /system/clients/doc. Get a pseudo schema showing quickly
	 * how to make a proper json for clients.
	 * 
	 * @return return value 400 : OR 404 : OR 500 : OR 200 :
	 */
	public String getClientsDoc() {
		Map<String, Object> array = new HashMap<String, Object>();

		return this.callService("/system/clients/doc", "get", array, null);
	}

	/**
	 * Access to url : /system/clients/{id}/apps/doc. Get a pseudo schema
	 * showing quickly how to make a proper json for client apps.
	 * 
	 * @return return value 400 : OR 404 : OR 500 : OR 200 :
	 */
	public String getClientAppsDoc(String id) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/system/clients/{id}/apps/doc", "get", array,
				null);
	}

	/**
	 * Access to url : /system/clients/{id}/apps/{appId}. Delete one client app
	 * by id.
	 * 
	 * @return return value 400 : OR 404 : OR 500 : OR 200 :
	 */
	public String deleteOneClientApp(String id, String appId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("appId", appId);

		return this.callService("/system/clients/{id}/apps/{appId}", "delete",
				array, null);
	}

	/**
	 * Access to url : /system/clients/{id}/apps/{appId}. Update one client app
	 * by id.
	 * 
	 * @param body
	 *            application/json schema : {}
	 * @return return value 400 : OR 404 : OR 500 : OR 200 :
	 */
	public String putOneClientApp(String id, String appId, String body) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("appId", appId);

		return this.callService("/system/clients/{id}/apps/{appId}", "put",
				array, body);
	}

	/**
	 * Access to url : /system/clients/{id}/apps/{appId}. Get one client app by
	 * id.
	 * 
	 * @return return value 400 : OR 404 : OR 500 : OR 200 : application/json :
	 *         {}
	 */
	public String getOneClientApp(String id, String appId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("appId", appId);

		return this.callService("/system/clients/{id}/apps/{appId}", "get",
				array, null);
	}

	/**
	 * Access to url : /system/clients/{id}/apps. Create a new client app.
	 * 
	 * @param body
	 *            application/json schema : {}
	 * @return return value 400 : OR 500 : OR 200 :
	 */
	public String postClientApps(String id, String body) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/system/clients/{id}/apps", "post", array,
				body);
	}

	/**
	 * Access to url : /system/clients/{id}. Delete one client by id.
	 * 
	 * @return return value 400 : OR 404 : OR 500 : OR 200 :
	 */
	public String deleteOneClient(String id) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/system/clients/{id}", "delete", array, null);
	}

	/**
	 * Access to url : /system/clients/{id}. Update one client by id.
	 * 
	 * @param body
	 *            application/json schema : {}
	 * @return return value 400 : OR 404 : OR 500 : OR 200 :
	 */
	public String putOneClient(String id, String body) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/system/clients/{id}", "put", array, body);
	}

	/**
	 * Access to url : /system/clients/{id}. Get one client by id.
	 * 
	 * @return return value 400 : OR 404 : OR 500 : OR 200 : application/json :
	 *         {}
	 */
	public String getOneClient(String id) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/system/clients/{id}", "get", array, null);
	}

	/**
	 * Access to url : /system/clients. Create a new client.
	 * 
	 * @param body
	 *            application/json schema : {}
	 * @return return value 400 : OR 500 : OR 200 :
	 */
	public String postClients(String body) {
		Map<String, Object> array = new HashMap<String, Object>();

		return this.callService("/system/clients", "post", array, body);
	}

}