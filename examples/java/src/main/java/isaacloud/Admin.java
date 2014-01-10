package isaacloud;

import java.util.Map;
import java.util.HashMap;

/**
 */
public class Admin extends Connector {

	public Admin(Map<String, String> config) {
		super("http://api.isaacloud.com", "http://oauth.isaacloud.com", "0.1",
				config);
	}

	/**
	 * Access to url : /admin/functions/doc. Get a pseudo schema showing quickly
	 * how to make a proper json for functions.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String getFunctionsDoc() {
		Map<String, Object> array = new HashMap<String, Object>();

		return this.callService("/admin/functions/doc", "get", array, null);
	}

	/**
	 * Access to url : /admin/functions/{id}. Delete a function.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteOneFunction(String id) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/functions/{id}", "delete", array, null);
	}

	/**
	 * Access to url : /admin/functions/{id}. Replace a function.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a counter",
	 *            "type":"object", "properties":{ "name":{ "required":false,
	 *            "type":"string" }, "description":{ "required":false,
	 *            "type":"string" }, "className":{ "required":false,
	 *            "type":"string" }, "method":{ "required":false,
	 *            "type":"string" }, "returnType":{ "required":false,
	 *            "type":"string", "allowed":{ "0":"BOOLEAN", "1":"COMPARE" },
	 *            "default":"BOOLEAN" }, "functionType":{ "required":false,
	 *            "type":"string", "allowed":{ "0":"NATIVE", "1":"SCRIPT" },
	 *            "default":"NATIVE" }, "scriptType":{ "required":false,
	 *            "type":"string", "allowed":{ "0":"SYSTEM", "1":"CLIENT" },
	 *            "default":"CLIENT" }, "scriptId":{ "required":false,
	 *            "type":"long", "default":"0" }, "manifesto":{
	 *            "required":false, "type":"string" } } }
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putOneFunction(String id, String body) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/functions/{id}", "put", array, body);
	}

	/**
	 * Access to url : /admin/functions/{id}.
	 * 
	 * @param fields
	 *            The fields we want to show in our result. Get one function.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a counter", "type":"object",
	 *         "properties":{ "name":{ "required":false, "type":"string" },
	 *         "description":{ "required":false, "type":"string" },
	 *         "className":{ "required":false, "type":"string" }, "method":{
	 *         "required":false, "type":"string" }, "returnType":{
	 *         "required":false, "type":"string", "allowed":{ "0":"BOOLEAN",
	 *         "1":"COMPARE" }, "default":"BOOLEAN" }, "functionType":{
	 *         "required":false, "type":"string", "allowed":{ "0":"NATIVE",
	 *         "1":"SCRIPT" }, "default":"NATIVE" }, "scriptType":{
	 *         "required":false, "type":"string", "allowed":{ "0":"SYSTEM",
	 *         "1":"CLIENT" }, "default":"CLIENT" }, "scriptId":{
	 *         "required":false, "type":"long", "default":"0" }, "manifesto":{
	 *         "required":false, "type":"string" } } }
	 */
	public String getOneFunction(String id, String fields) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("fields", fields);

		return this.callService("/admin/functions/{id}", "get", array, null);
	}

	/**
	 * Access to url : /admin/functions. Add a new function.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a counter",
	 *            "type":"object", "properties":{ "name":{ "required":false,
	 *            "type":"string" }, "description":{ "required":false,
	 *            "type":"string" }, "className":{ "required":false,
	 *            "type":"string" }, "method":{ "required":false,
	 *            "type":"string" }, "returnType":{ "required":false,
	 *            "type":"string", "allowed":{ "0":"BOOLEAN", "1":"COMPARE" },
	 *            "default":"BOOLEAN" }, "functionType":{ "required":false,
	 *            "type":"string", "allowed":{ "0":"NATIVE", "1":"SCRIPT" },
	 *            "default":"NATIVE" }, "scriptType":{ "required":false,
	 *            "type":"string", "allowed":{ "0":"SYSTEM", "1":"CLIENT" },
	 *            "default":"CLIENT" }, "scriptId":{ "required":false,
	 *            "type":"long", "default":"0" }, "manifesto":{
	 *            "required":false, "type":"string" } } }
	 * @return return value 400 : or 500 : or 200 :
	 */
	public String postFunctions(String body) {
		Map<String, Object> array = new HashMap<String, Object>();

		return this.callService("/admin/functions", "post", array, body);
	}

	/**
	 * Access to url : /admin/functions. Get a list of functions.
	 * 
	 * @param offset
	 *            Number of returned items.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a counter", "type":"object",
	 *         "properties":{ "name":{ "required":false, "type":"string" },
	 *         "description":{ "required":false, "type":"string" },
	 *         "className":{ "required":false, "type":"string" }, "method":{
	 *         "required":false, "type":"string" }, "returnType":{
	 *         "required":false, "type":"string", "allowed":{ "0":"BOOLEAN",
	 *         "1":"COMPARE" }, "default":"BOOLEAN" }, "functionType":{
	 *         "required":false, "type":"string", "allowed":{ "0":"NATIVE",
	 *         "1":"SCRIPT" }, "default":"NATIVE" }, "scriptType":{
	 *         "required":false, "type":"string", "allowed":{ "0":"SYSTEM",
	 *         "1":"CLIENT" }, "default":"CLIENT" }, "scriptId":{
	 *         "required":false, "type":"long", "default":"0" }, "manifesto":{
	 *         "required":false, "type":"string" } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param limit
	 *            Starting position.
	 * @param fields
	 *            The fields we want to show in our result.
	 */
	public String getFunctions(String fields, Long offset, Long limit,
			String order) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("fields", fields);
		array.put("offset", offset);
		array.put("limit", limit);
		array.put("order", order);

		return this.callService("/admin/functions", "get", array, null);
	}

	/**
	 * Access to url : /admin/notifications/doc. Get a pseudo schema showing
	 * quickly how to make a proper json for notifications.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String getNotificationsDocs() {
		Map<String, Object> array = new HashMap<String, Object>();

		return this.callService("/admin/notifications/doc", "get", array, null);
	}

	/**
	 * Access to url : /admin/notifications/types/doc. Get a pseudo schema
	 * showing quickly how to make a proper json for notification types.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String getNotificationTypesDocs() {
		Map<String, Object> array = new HashMap<String, Object>();

		return this.callService("/admin/notifications/types/doc", "get", array,
				null);
	}

	/**
	 * Access to url : /admin/notifications/types/{id}. Delete a notification
	 * type.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteOneNotificationType(String id) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/notifications/types/{id}", "delete",
				array, null);
	}

	/**
	 * Access to url : /admin/notifications/types/{id}. Update a notification
	 * type
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a notification type",
	 *            "type":"object", "properties":{ "name":{ "required":false,
	 *            "type":"string" }, "action":{ "required":false,
	 *            "type":"string", "allowed":{ "0":"INTERNAL_EVENT",
	 *            "1":"INTERNAL_GROUP_EVENT", "2":"COUNTER_INC",
	 *            "3":"COUNTER_MODIFY", "4":"ACHIEVEMENT", "5":"EMAIL",
	 *            "6":"SOAP", "7":"REST", "8":"CUSTOM" } }, "config":{
	 *            "required":false, "type":"string" }, "priority":{
	 *            "required":false, "type":"string", "allowed":{
	 *            "0":"PRIORITY_LOW", "1":"PRIORITY_MEDIUM",
	 *            "2":"PRIORITY_NORMAL", "3":"PRIORITY_HIGH",
	 *            "4":"PRIORITY_CRITICAL", "5":"PRIORITY_BLOCKER" },
	 *            "default":"PRIORITY_MEDIUM" } } }
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putOneNotificationType(String id, String body) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/notifications/types/{id}", "put",
				array, body);
	}

	/**
	 * Access to url : /admin/notifications/types/{id}.
	 * 
	 * @param fields
	 *            The fields we want to show in our result. Get a notification
	 *            type.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a notification type",
	 *         "type":"object", "properties":{ "name":{ "required":false,
	 *         "type":"string" }, "action":{ "required":false, "type":"string",
	 *         "allowed":{ "0":"INTERNAL_EVENT", "1":"INTERNAL_GROUP_EVENT",
	 *         "2":"COUNTER_INC", "3":"COUNTER_MODIFY", "4":"ACHIEVEMENT",
	 *         "5":"EMAIL", "6":"SOAP", "7":"REST", "8":"CUSTOM" } }, "config":{
	 *         "required":false, "type":"string" }, "priority":{
	 *         "required":false, "type":"string", "allowed":{
	 *         "0":"PRIORITY_LOW", "1":"PRIORITY_MEDIUM", "2":"PRIORITY_NORMAL",
	 *         "3":"PRIORITY_HIGH", "4":"PRIORITY_CRITICAL",
	 *         "5":"PRIORITY_BLOCKER" }, "default":"PRIORITY_MEDIUM" } } }
	 */
	public String getOneNotificationType(String id, String fields) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("fields", fields);

		return this.callService("/admin/notifications/types/{id}", "get",
				array, null);
	}

	/**
	 * Access to url : /admin/notifications/types. Add a notification type.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a notification type",
	 *            "type":"object", "properties":{ "name":{ "required":false,
	 *            "type":"string" }, "action":{ "required":false,
	 *            "type":"string", "allowed":{ "0":"INTERNAL_EVENT",
	 *            "1":"INTERNAL_GROUP_EVENT", "2":"COUNTER_INC",
	 *            "3":"COUNTER_MODIFY", "4":"ACHIEVEMENT", "5":"EMAIL",
	 *            "6":"SOAP", "7":"REST", "8":"CUSTOM" } }, "config":{
	 *            "required":false, "type":"string" }, "priority":{
	 *            "required":false, "type":"string", "allowed":{
	 *            "0":"PRIORITY_LOW", "1":"PRIORITY_MEDIUM",
	 *            "2":"PRIORITY_NORMAL", "3":"PRIORITY_HIGH",
	 *            "4":"PRIORITY_CRITICAL", "5":"PRIORITY_BLOCKER" },
	 *            "default":"PRIORITY_MEDIUM" } } }
	 * @return return value 400 : or 500 : or 200 :
	 */
	public String postNotificationTypes(String body) {
		Map<String, Object> array = new HashMap<String, Object>();

		return this.callService("/admin/notifications/types", "post", array,
				body);
	}

	/**
	 * Access to url : /admin/notifications/types. Get a list of notification
	 * types.
	 * 
	 * @param offset
	 *            Number of returned items.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a notification type",
	 *         "type":"object", "properties":{ "name":{ "required":false,
	 *         "type":"string" }, "action":{ "required":false, "type":"string",
	 *         "allowed":{ "0":"INTERNAL_EVENT", "1":"INTERNAL_GROUP_EVENT",
	 *         "2":"COUNTER_INC", "3":"COUNTER_MODIFY", "4":"ACHIEVEMENT",
	 *         "5":"EMAIL", "6":"SOAP", "7":"REST", "8":"CUSTOM" } }, "config":{
	 *         "required":false, "type":"string" }, "priority":{
	 *         "required":false, "type":"string", "allowed":{
	 *         "0":"PRIORITY_LOW", "1":"PRIORITY_MEDIUM", "2":"PRIORITY_NORMAL",
	 *         "3":"PRIORITY_HIGH", "4":"PRIORITY_CRITICAL",
	 *         "5":"PRIORITY_BLOCKER" }, "default":"PRIORITY_MEDIUM" } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param limit
	 *            Starting position.
	 * @param fields
	 *            The fields we want to show in our result.
	 */
	public String getNotificationTypes(String fields, Long offset, Long limit,
			String order) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("fields", fields);
		array.put("offset", offset);
		array.put("limit", limit);
		array.put("order", order);

		return this.callService("/admin/notifications/types", "get", array,
				null);
	}

	/**
	 * Access to url : /admin/notifications/{id}. Delete a notification.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteOneNotification(String id) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/notifications/{id}", "delete", array,
				null);
	}

	/**
	 * Access to url : /admin/notifications/{id}. Update a notification.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a notification",
	 *            "type":"object", "properties":{ "notificationType":{
	 *            "required":true, "type":"long" }, "segment":{
	 *            "required":false, "type":"long" }, "game":{ "required":false,
	 *            "type":"long" }, "data":{ "required":true, "type":"string" } }
	 *            }
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putOneNotification(String id, String body) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this
				.callService("/admin/notifications/{id}", "put", array, body);
	}

	/**
	 * Access to url : /admin/notifications/{id}.
	 * 
	 * @param fields
	 *            The fields we want to show in our result. Get one
	 *            notification.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a notification",
	 *         "type":"object", "properties":{ "notificationType":{
	 *         "required":true, "type":"long" }, "segment":{ "required":false,
	 *         "type":"long" }, "game":{ "required":false, "type":"long" },
	 *         "data":{ "required":true, "type":"string" } } }
	 */
	public String getOneNotification(String id, String fields) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("fields", fields);

		return this
				.callService("/admin/notifications/{id}", "get", array, null);
	}

	/**
	 * Access to url : /admin/notifications. Add a notification.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a notification",
	 *            "type":"object", "properties":{ "notificationType":{
	 *            "required":true, "type":"long" }, "segment":{
	 *            "required":false, "type":"long" }, "game":{ "required":false,
	 *            "type":"long" }, "data":{ "required":true, "type":"string" } }
	 *            }
	 * @return return value 400 : or 500 : or 200 :
	 */
	public String postNotifications(String body) {
		Map<String, Object> array = new HashMap<String, Object>();

		return this.callService("/admin/notifications", "post", array, body);
	}

	/**
	 * Access to url : /admin/notifications. Get a list of notifications.
	 * 
	 * @param offset
	 *            Number of returned items.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a notification",
	 *         "type":"object", "properties":{ "notificationType":{
	 *         "required":true, "type":"long" }, "segment":{ "required":false,
	 *         "type":"long" }, "game":{ "required":false, "type":"long" },
	 *         "data":{ "required":true, "type":"string" } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param limit
	 *            Starting position.
	 * @param fields
	 *            The fields we want to show in our result.
	 */
	public String getNotifications(String fields, Long offset, Long limit,
			String order) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("fields", fields);
		array.put("offset", offset);
		array.put("limit", limit);
		array.put("order", order);

		return this.callService("/admin/notifications", "get", array, null);
	}

	/**
	 * Access to url : /admin/transactiontypes/doc. Get a pseudo schema showing
	 * quickly how to make a proper json for transaction types.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String getTransactionTypesDocs() {
		Map<String, Object> array = new HashMap<String, Object>();

		return this.callService("/admin/transactiontypes/doc", "get", array,
				null);
	}

	/**
	 * Access to url : /admin/transactiontypes/{id}/conditions/{childId}. Delete
	 * a condition for transaction type.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteOneTransactionTypesCondition(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService(
				"/admin/transactiontypes/{id}/conditions/{childId}", "delete",
				array, null);
	}

	/**
	 * Access to url : /admin/transactiontypes/{id}/conditions/{childId}. Add a
	 * condition for a transaction type.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putOneTransactionTypesCondition(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService(
				"/admin/transactiontypes/{id}/conditions/{childId}", "put",
				array, null);
	}

	/**
	 * Access to url : /admin/transactiontypes/{id}. Remove a transaction type.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteOneTransactionType(String id) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/transactiontypes/{id}", "delete",
				array, null);
	}

	/**
	 * Access to url : /admin/transactiontypes/{id}. Update a transaction type.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a transaction source",
	 *            "type":"object", "properties":{ "name":{ "required":false,
	 *            "type":"string" }, "transactionSource":{ "required":false,
	 *            "type":"long" }, "conditions":{ "required":false,
	 *            "type":"id_array" } } }
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putOneTransactionType(String id, String body) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/transactiontypes/{id}", "put", array,
				body);
	}

	/**
	 * Access to url : /admin/transactiontypes/{id}.
	 * 
	 * @param fields
	 *            The fields we want to show in our result. Get a transaction
	 *            type.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a transaction source",
	 *         "type":"object", "properties":{ "name":{ "required":false,
	 *         "type":"string" }, "transactionSource":{ "required":false,
	 *         "type":"long" }, "conditions":{ "required":false,
	 *         "type":"id_array" } } }
	 */
	public String getOneTransactionType(String id, String fields) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("fields", fields);

		return this.callService("/admin/transactiontypes/{id}", "get", array,
				null);
	}

	/**
	 * Access to url : /admin/transactiontypes. Add a transaction type.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a transaction source",
	 *            "type":"object", "properties":{ "name":{ "required":false,
	 *            "type":"string" }, "transactionSource":{ "required":false,
	 *            "type":"long" }, "conditions":{ "required":false,
	 *            "type":"id_array" } } }
	 * @return return value 400 : or 500 : or 200 :
	 */
	public String postTransactionTypes(String body) {
		Map<String, Object> array = new HashMap<String, Object>();

		return this.callService("/admin/transactiontypes", "post", array, body);
	}

	/**
	 * Access to url : /admin/transactiontypes. Get a list of transaction types.
	 * 
	 * @param offset
	 *            Number of returned items.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a transaction source",
	 *         "type":"object", "properties":{ "name":{ "required":false,
	 *         "type":"string" }, "transactionSource":{ "required":false,
	 *         "type":"long" }, "conditions":{ "required":false,
	 *         "type":"id_array" } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param limit
	 *            Starting position.
	 * @param fields
	 *            The fields we want to show in our result.
	 */
	public String getTransactionTypes(String fields, Long offset, Long limit,
			String order) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("fields", fields);
		array.put("offset", offset);
		array.put("limit", limit);
		array.put("order", order);

		return this.callService("/admin/transactiontypes", "get", array, null);
	}

	/**
	 * Access to url : /admin/achievements/groups/doc. Get a pseudo schema for
	 * group.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String getAchievementsGroupsDoc() {
		Map<String, Object> array = new HashMap<String, Object>();

		return this.callService("/admin/achievements/groups/doc", "get", array,
				null);
	}

	/**
	 * Access to url : /admin/achievements/groups/{id}/achievements/{childId}.
	 * Delete one achievement from achievements group.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteOneGroupAchievement(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService(
				"/admin/achievements/groups/{id}/achievements/{childId}",
				"delete", array, null);
	}

	/**
	 * Access to url : /admin/achievements/groups/{id}/achievements/{childId}.
	 * Add one achievement to achievements group.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putOneGroupAchievement(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService(
				"/admin/achievements/groups/{id}/achievements/{childId}",
				"put", array, null);
	}

	/**
	 * Access to url : /admin/achievements/groups/{id}. Delete an achievement
	 * group.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteOneAchievementsGroup(String id) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/achievements/groups/{id}", "delete",
				array, null);
	}

	/**
	 * Access to url : /admin/achievements/groups/{id}. Update an achievement
	 * group.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of an achievement group",
	 *            "type":"object", "properties":{ "name":{ "required":false,
	 *            "type":"integer" }, "members":{ "required":false,
	 *            "type":"array", "items":{ "type":"integer" } } } }
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putOneAchievementsGroup(String id, String body) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/achievements/groups/{id}", "put",
				array, body);
	}

	/**
	 * Access to url : /admin/achievements/groups/{id}.
	 * 
	 * @param fields
	 *            The fields we want to show in our result. Get one achievement
	 *            group.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of an achievement group",
	 *         "type":"object", "properties":{ "name":{ "required":false,
	 *         "type":"integer" }, "members":{ "required":false, "type":"array",
	 *         "items":{ "type":"integer" } } } }
	 */
	public String getOneAchievementsGroup(String id, String fields) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("fields", fields);

		return this.callService("/admin/achievements/groups/{id}", "get",
				array, null);
	}

	/**
	 * Access to url : /admin/achievements/groups. Create new achievement group.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of an achievement group",
	 *            "type":"object", "properties":{ "name":{ "required":false,
	 *            "type":"integer" }, "members":{ "required":false,
	 *            "type":"array", "items":{ "type":"integer" } } } }
	 * @return return value 400 : or 500 : or 200 :
	 */
	public String postAchievementsGroups(String body) {
		Map<String, Object> array = new HashMap<String, Object>();

		return this.callService("/admin/achievements/groups", "post", array,
				body);
	}

	/**
	 * Access to url : /admin/achievements/groups. Get a list achievement
	 * groups.
	 * 
	 * @param offset
	 *            Number of returned items.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of an achievement group",
	 *         "type":"object", "properties":{ "name":{ "required":false,
	 *         "type":"integer" }, "members":{ "required":false, "type":"array",
	 *         "items":{ "type":"integer" } } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param limit
	 *            Starting position.
	 * @param fields
	 *            The fields we want to show in our result.
	 */
	public String getAchievementsGroups(String fields, String order,
			Long offset, Long limit) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("fields", fields);
		array.put("order", order);
		array.put("offset", offset);
		array.put("limit", limit);

		return this.callService("/admin/achievements/groups", "get", array,
				null);
	}

	/**
	 * Access to url : /admin/achievements/progress/doc. Get a pseudo schema for
	 * progress.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String getAchievementsProgressesDoc() {
		Map<String, Object> array = new HashMap<String, Object>();

		return this.callService("/admin/achievements/progress/doc", "get",
				array, null);
	}

	/**
	 * Access to url : /admin/achievements/progress/{id}. Delete an achievement
	 * progress.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteOneAchievementsProgress(String id) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/achievements/progress/{id}", "delete",
				array, null);
	}

	/**
	 * Access to url : /admin/achievements/progress/{id}. Update an achievement
	 * progress.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a segment",
	 *            "type":"object", "properties":{ "start":{ "required":false,
	 *            "type":"integer" }, "stop":{ "required":false,
	 *            "type":"integer" }, "achievement":{ "required":false,
	 *            "type":"integer" }, "counter":{ "required":false,
	 *            "type":"integer" } } }
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putOneAchievementsProgress(String id, String body) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/achievements/progress/{id}", "put",
				array, body);
	}

	/**
	 * Access to url : /admin/achievements/progress/{id}.
	 * 
	 * @param fields
	 *            The fields we want to show in our result. Get one achievement
	 *            progress.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a segment", "type":"object",
	 *         "properties":{ "start":{ "required":false, "type":"integer" },
	 *         "stop":{ "required":false, "type":"integer" }, "achievement":{
	 *         "required":false, "type":"integer" }, "counter":{
	 *         "required":false, "type":"integer" } } }
	 */
	public String getOneAchievementsProgress(String id, String fields) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("fields", fields);

		return this.callService("/admin/achievements/progress/{id}", "get",
				array, null);
	}

	/**
	 * Access to url : /admin/achievements/progress. Create new achievement
	 * progress.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a segment",
	 *            "type":"object", "properties":{ "start":{ "required":false,
	 *            "type":"integer" }, "stop":{ "required":false,
	 *            "type":"integer" }, "achievement":{ "required":false,
	 *            "type":"integer" }, "counter":{ "required":false,
	 *            "type":"integer" } } }
	 * @return return value 400 : or 500 : or 200 :
	 */
	public String postAchievementsProgresses(String body) {
		Map<String, Object> array = new HashMap<String, Object>();

		return this.callService("/admin/achievements/progress", "post", array,
				body);
	}

	/**
	 * Access to url : /admin/achievements/progress. Get achievement progress
	 * list.
	 * 
	 * @param offset
	 *            Number of returned items.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a segment", "type":"object",
	 *         "properties":{ "start":{ "required":false, "type":"integer" },
	 *         "stop":{ "required":false, "type":"integer" }, "achievement":{
	 *         "required":false, "type":"integer" }, "counter":{
	 *         "required":false, "type":"integer" } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param limit
	 *            Starting position.
	 * @param fields
	 *            The fields we want to show in our result.
	 */
	public String getAchievementsProgresses(String fields, String order,
			Long offset, Long limit) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("fields", fields);
		array.put("order", order);
		array.put("offset", offset);
		array.put("limit", limit);

		return this.callService("/admin/achievements/progress", "get", array,
				null);
	}

	/**
	 * Access to url : /admin/achievements/categories/doc. Get a pseudo schema
	 * for category.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String getAchievementsCategoriesDoc() {
		Map<String, Object> array = new HashMap<String, Object>();

		return this.callService("/admin/achievements/categories/doc", "get",
				array, null);
	}

	/**
	 * Access to url :
	 * /admin/achievements/categories/{id}/achievements/{childId}. Delete one
	 * achievement from achievements category.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteOneCategoryAchievement(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService(
				"/admin/achievements/categories/{id}/achievements/{childId}",
				"delete", array, null);
	}

	/**
	 * Access to url :
	 * /admin/achievements/categories/{id}/achievements/{childId}. Add one
	 * achievement to achievements category.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putOneCategoryAchievement(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService(
				"/admin/achievements/categories/{id}/achievements/{childId}",
				"put", array, null);
	}

	/**
	 * Access to url : /admin/achievements/categories/{id}. Delete one
	 * achievements category.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteAchievementsCategory(String id) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/achievements/categories/{id}",
				"delete", array, null);
	}

	/**
	 * Access to url : /admin/achievements/categories/{id}. Update achievement
	 * category.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a category",
	 *            "type":"object", "properties":{ "name":{ "required":false,
	 *            "type":"integer" }, "members":{ "required":false,
	 *            "type":"array", "items":{ "type":"integer" } } } }
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putAchievementsCategory(String id, String body) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/achievements/categories/{id}", "put",
				array, body);
	}

	/**
	 * Access to url : /admin/achievements/categories/{id}. Get one category.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a category", "type":"object",
	 *         "properties":{ "name":{ "required":false, "type":"integer" },
	 *         "members":{ "required":false, "type":"array", "items":{
	 *         "type":"integer" } } } }
	 */
	public String getAchievementsCategory(String id) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/achievements/categories/{id}", "get",
				array, null);
	}

	/**
	 * Access to url : /admin/achievements/categories. Create new category.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a category",
	 *            "type":"object", "properties":{ "name":{ "required":false,
	 *            "type":"integer" }, "members":{ "required":false,
	 *            "type":"array", "items":{ "type":"integer" } } } }
	 * @return return value 400 : or 500 : or 200 :
	 */
	public String postAchievementsCategories(String body) {
		Map<String, Object> array = new HashMap<String, Object>();

		return this.callService("/admin/achievements/categories", "post",
				array, body);
	}

	/**
	 * Access to url : /admin/achievements/categories. Get list of categories.
	 * 
	 * @param offset
	 *            Number of returned items.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a category", "type":"object",
	 *         "properties":{ "name":{ "required":false, "type":"integer" },
	 *         "members":{ "required":false, "type":"array", "items":{
	 *         "type":"integer" } } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param limit
	 *            Starting position.
	 * @param fields
	 *            The fields we want to show in our result.
	 */
	public String getAchievementsCategories(String fields, String order,
			Long offset, Long limit) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("fields", fields);
		array.put("order", order);
		array.put("offset", offset);
		array.put("limit", limit);

		return this.callService("/admin/achievements/categories", "get", array,
				null);
	}

	/**
	 * Access to url : /admin/achievements/{achievementId}/segments/{childId}.
	 * Delete segment from achievement.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteOneAchievementSegment(String achievementId,
			String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("achievementId", achievementId);
		array.put("childId", childId);

		return this.callService(
				"/admin/achievements/{achievementId}/segments/{childId}",
				"delete", array, null);
	}

	/**
	 * Access to url : /admin/achievements/{achievementId}/segments/{childId}.
	 * Add segment to achievement.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putOneAchievementSegment(String achievementId, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("achievementId", achievementId);
		array.put("childId", childId);

		return this.callService(
				"/admin/achievements/{achievementId}/segments/{childId}",
				"put", array, null);
	}

	/**
	 * Access to url : /admin/achievements/{achievementId}. Delete one
	 * achievement.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteOneAchievement(String achievementId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("achievementId", achievementId);

		return this.callService("/admin/achievements/{achievementId}",
				"delete", array, null);
	}

	/**
	 * Access to url : /admin/achievements/{achievementId}. Update one
	 * achievement.
	 * 
	 * @param body
	 *            application/json schema : { "name": { "required": true,
	 *            "type": "string", "constraint": "minimum 5, maximum 30 chars",
	 *            "description": "name of achievement" }, "label": { "required":
	 *            true, "type": "string" }, "description": { "required": false,
	 *            "type": "string" }, "active": { "required": false, "type":
	 *            "boolean", "default": "false" }, "level": { "required": false,
	 *            "type": "int", "default": "1" }, "displayRank": { "required":
	 *            false, "type": "int", "default": "1" }, "importanceRank": {
	 *            "required": false, "type": "int", "default": "1" }, "data": {
	 *            "required": false, "type": "string" }, "achievementType": {
	 *            "required": false, "type": "string", "allowed": { "0":
	 *            "NORMAL", "1": "PROGRESSIVE" }, "default": "NORMAL" },
	 *            "achievementGroup": { "required": false, "type": "long" },
	 *            "progress": { "required": false, "type": "long" }, "counter":
	 *            { "required": false, "type": "long" }, "segments": {
	 *            "required": false, "type": "id_array" } }
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putOneAchievement(String achievementId, String body) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("achievementId", achievementId);

		return this.callService("/admin/achievements/{achievementId}", "put",
				array, body);
	}

	/**
	 * Access to url : /admin/achievements/{achievementId}.
	 * 
	 * @param fields
	 *            The fields we want to show in our result. Get one achievement.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "name": { "required": true, "type": "string", "constraint":
	 *         "minimum 5, maximum 30 chars", "description":
	 *         "name of achievement" }, "label": { "required": true, "type":
	 *         "string" }, "description": { "required": false, "type": "string"
	 *         }, "active": { "required": false, "type": "boolean", "default":
	 *         "false" }, "level": { "required": false, "type": "int",
	 *         "default": "1" }, "displayRank": { "required": false, "type":
	 *         "int", "default": "1" }, "importanceRank": { "required": false,
	 *         "type": "int", "default": "1" }, "data": { "required": false,
	 *         "type": "string" }, "achievementType": { "required": false,
	 *         "type": "string", "allowed": { "0": "NORMAL", "1": "PROGRESSIVE"
	 *         }, "default": "NORMAL" }, "achievementGroup": { "required":
	 *         false, "type": "long" }, "progress": { "required": false, "type":
	 *         "long" }, "counter": { "required": false, "type": "long" },
	 *         "segments": { "required": false, "type": "id_array" } }
	 */
	public String getOneAchievement(String achievementId, String fields) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("achievementId", achievementId);
		array.put("fields", fields);

		return this.callService("/admin/achievements/{achievementId}", "get",
				array, null);
	}

	/**
	 * Access to url : /admin/achievements. Create new achievement.
	 * 
	 * @param body
	 *            application/json schema : { "name": { "required": true,
	 *            "type": "string", "constraint": "minimum 5, maximum 30 chars",
	 *            "description": "name of achievement" }, "label": { "required":
	 *            true, "type": "string" }, "description": { "required": false,
	 *            "type": "string" }, "active": { "required": false, "type":
	 *            "boolean", "default": "false" }, "level": { "required": false,
	 *            "type": "int", "default": "1" }, "displayRank": { "required":
	 *            false, "type": "int", "default": "1" }, "importanceRank": {
	 *            "required": false, "type": "int", "default": "1" }, "data": {
	 *            "required": false, "type": "string" }, "achievementType": {
	 *            "required": false, "type": "string", "allowed": { "0":
	 *            "NORMAL", "1": "PROGRESSIVE" }, "default": "NORMAL" },
	 *            "achievementGroup": { "required": false, "type": "long" },
	 *            "progress": { "required": false, "type": "long" }, "counter":
	 *            { "required": false, "type": "long" }, "segments": {
	 *            "required": false, "type": "id_array" } }
	 * @return return value 400 : or 500 : or 200 :
	 */
	public String postAchievements(String body) {
		Map<String, Object> array = new HashMap<String, Object>();

		return this.callService("/admin/achievements", "post", array, body);
	}

	/**
	 * Access to url : /admin/achievements. Get a list of achievements.
	 * 
	 * @param groups
	 *            The groups we want to narrow down our search to.
	 * @param offset
	 *            Number of returned items.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "name": { "required": true, "type": "string", "constraint":
	 *         "minimum 5, maximum 30 chars", "description":
	 *         "name of achievement" }, "label": { "required": true, "type":
	 *         "string" }, "description": { "required": false, "type": "string"
	 *         }, "active": { "required": false, "type": "boolean", "default":
	 *         "false" }, "level": { "required": false, "type": "int",
	 *         "default": "1" }, "displayRank": { "required": false, "type":
	 *         "int", "default": "1" }, "importanceRank": { "required": false,
	 *         "type": "int", "default": "1" }, "data": { "required": false,
	 *         "type": "string" }, "achievementType": { "required": false,
	 *         "type": "string", "allowed": { "0": "NORMAL", "1": "PROGRESSIVE"
	 *         }, "default": "NORMAL" }, "achievementGroup": { "required":
	 *         false, "type": "long" }, "progress": { "required": false, "type":
	 *         "long" }, "counter": { "required": false, "type": "long" },
	 *         "segments": { "required": false, "type": "id_array" } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param segments
	 *            The segments we want to narrow down our search to.
	 * @param limit
	 *            Starting position.
	 * @param fields
	 *            The fields we want to show in our result.
	 */
	public String getAchievements(String groups, Long offset, String order,
			String fields, String segments, Long limit) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("groups", groups);
		array.put("offset", offset);
		array.put("order", order);
		array.put("fields", fields);
		array.put("segments", segments);
		array.put("limit", limit);

		return this.callService("/admin/achievements", "get", array, null);
	}

	/**
	 * Access to url : /admin/games/doc. Get a pseudo schema showing quickly how
	 * to make a proper json for games.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String getGamesDoc() {
		Map<String, Object> array = new HashMap<String, Object>();

		return this.callService("/admin/games/doc", "get", array, null);
	}

	/**
	 * Access to url : /admin/games/{id}/notifications/{childId}. Remove a
	 * notification from a game.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteOneGameNotification(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService("/admin/games/{id}/notifications/{childId}",
				"delete", array, null);
	}

	/**
	 * Access to url : /admin/games/{id}/notifications/{childId}. Add a
	 * notification to a game.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putOneGameNotification(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService("/admin/games/{id}/notifications/{childId}",
				"put", array, null);
	}

	/**
	 * Access to url : /admin/games/{id}/conditions/{childId}. Remove a
	 * condition from a game.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteOneGameCondition(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService("/admin/games/{id}/conditions/{childId}",
				"delete", array, null);
	}

	/**
	 * Access to url : /admin/games/{id}/conditions/{childId}. Add a condition
	 * to a game.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putOneGameCondition(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService("/admin/games/{id}/conditions/{childId}",
				"put", array, null);
	}

	/**
	 * Access to url : /admin/games/{id}. Delete a game.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteOneGame(String id) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/games/{id}", "delete", array, null);
	}

	/**
	 * Access to url : /admin/games/{id}. Update a game.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a game", "type":"object",
	 *            "properties":{ "active":{ "required":false, "type":"boolean",
	 *            "default":"false" }, "name":{ "required":true, "type":"string"
	 *            }, "description":{ "required":false, "type":"string" },
	 *            "expression":{ "required":true, "type":"string" },
	 *            "priority":{ "required":false, "type":"int", "default":"1" },
	 *            "gameType":{ "required":false, "type":"string", "allowed":{
	 *            "0":"ONE_TIME", "1":"GRADUAL" }, "default":"ONE_TIME" },
	 *            "notifications":{ "required":false, "type":"id_array" },
	 *            "conditions":{ "required":false, "type":"id_array" },
	 *            "counter":{ "required":false, "type":"id" },
	 *            "transactionSource":{ "required":false, "type":"id" } } }
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putOneGame(String id, String body) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/games/{id}", "put", array, body);
	}

	/**
	 * Access to url : /admin/games/{id}.
	 * 
	 * @param fields
	 *            The fields we want to show in our result. Get one game.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a game", "type":"object",
	 *         "properties":{ "active":{ "required":false, "type":"boolean",
	 *         "default":"false" }, "name":{ "required":true, "type":"string" },
	 *         "description":{ "required":false, "type":"string" },
	 *         "expression":{ "required":true, "type":"string" }, "priority":{
	 *         "required":false, "type":"int", "default":"1" }, "gameType":{
	 *         "required":false, "type":"string", "allowed":{ "0":"ONE_TIME",
	 *         "1":"GRADUAL" }, "default":"ONE_TIME" }, "notifications":{
	 *         "required":false, "type":"id_array" }, "conditions":{
	 *         "required":false, "type":"id_array" }, "counter":{
	 *         "required":false, "type":"id" }, "transactionSource":{
	 *         "required":false, "type":"id" } } }
	 */
	public String getOneGame(String id, String fields) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("fields", fields);

		return this.callService("/admin/games/{id}", "get", array, null);
	}

	/**
	 * Access to url : /admin/games. Create a game.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a game", "type":"object",
	 *            "properties":{ "active":{ "required":false, "type":"boolean",
	 *            "default":"false" }, "name":{ "required":true, "type":"string"
	 *            }, "description":{ "required":false, "type":"string" },
	 *            "expression":{ "required":true, "type":"string" },
	 *            "priority":{ "required":false, "type":"int", "default":"1" },
	 *            "gameType":{ "required":false, "type":"string", "allowed":{
	 *            "0":"ONE_TIME", "1":"GRADUAL" }, "default":"ONE_TIME" },
	 *            "notifications":{ "required":false, "type":"id_array" },
	 *            "conditions":{ "required":false, "type":"id_array" },
	 *            "counter":{ "required":false, "type":"id" },
	 *            "transactionSource":{ "required":false, "type":"id" } } }
	 * @return return value 400 : or 500 : or 200 :
	 */
	public String postGames(String body) {
		Map<String, Object> array = new HashMap<String, Object>();

		return this.callService("/admin/games", "post", array, body);
	}

	/**
	 * Access to url : /admin/games. Get a list of games.
	 * 
	 * @param offset
	 *            Number of returned items.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a game", "type":"object",
	 *         "properties":{ "active":{ "required":false, "type":"boolean",
	 *         "default":"false" }, "name":{ "required":true, "type":"string" },
	 *         "description":{ "required":false, "type":"string" },
	 *         "expression":{ "required":true, "type":"string" }, "priority":{
	 *         "required":false, "type":"int", "default":"1" }, "gameType":{
	 *         "required":false, "type":"string", "allowed":{ "0":"ONE_TIME",
	 *         "1":"GRADUAL" }, "default":"ONE_TIME" }, "notifications":{
	 *         "required":false, "type":"id_array" }, "conditions":{
	 *         "required":false, "type":"id_array" }, "counter":{
	 *         "required":false, "type":"id" }, "transactionSource":{
	 *         "required":false, "type":"id" } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param limit
	 *            Starting position.
	 * @param fields
	 *            The fields we want to show in our result.
	 */
	public String getGames(String fields, Long offset, Long limit, String order) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("fields", fields);
		array.put("offset", offset);
		array.put("limit", limit);
		array.put("order", order);

		return this.callService("/admin/games", "get", array, null);
	}

	/**
	 * Access to url : /admin/leaderboards/doc. Get a pseudo schema showing
	 * quickly how to make a proper json for leaderboards.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String getLeaderboardsDocs() {
		Map<String, Object> array = new HashMap<String, Object>();

		return this.callService("/admin/leaderboards/doc", "get", array, null);
	}

	/**
	 * Access to url : /admin/leaderboards/{id}/recalculate. Recalculate a
	 * leaderboard
	 * 
	 * @return return value 202 :
	 */
	public String getOneLeaderboardRecalculate(String id) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/leaderboards/{id}/recalculate", "get",
				array, null);
	}

	/**
	 * Access to url : /admin/leaderboards/{id}. Remove a leaderboard.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteOneLeaderboard(String id) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/leaderboards/{id}", "delete", array,
				null);
	}

	/**
	 * Access to url : /admin/leaderboards/{id}. Update a leaderboard.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a leaderboard",
	 *            "type":"object", "properties":{ "active":{ "required":false,
	 *            "type":"boolean", "default":"false" }, "name":{
	 *            "required":true, "type":"string" }, "description":{
	 *            "required":false, "type":"string" }, "cron":{ "required":true,
	 *            "type":"string" }, "script":{ "required":true, "type":"long" }
	 *            } }
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putOneLeaderboard(String id, String body) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/leaderboards/{id}", "put", array, body);
	}

	/**
	 * Access to url : /admin/leaderboards/{id}.
	 * 
	 * @param fields
	 *            The fields we want to show in our result. Get a leaderboard.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a leaderboard",
	 *         "type":"object", "properties":{ "active":{ "required":false,
	 *         "type":"boolean", "default":"false" }, "name":{ "required":true,
	 *         "type":"string" }, "description":{ "required":false,
	 *         "type":"string" }, "cron":{ "required":true, "type":"string" },
	 *         "script":{ "required":true, "type":"long" } } }
	 */
	public String getOneLeaderboard(String id, String fields) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("fields", fields);

		return this.callService("/admin/leaderboards/{id}", "get", array, null);
	}

	/**
	 * Access to url : /admin/leaderboards. Add a new leaderboard.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a leaderboard",
	 *            "type":"object", "properties":{ "active":{ "required":false,
	 *            "type":"boolean", "default":"false" }, "name":{
	 *            "required":true, "type":"string" }, "description":{
	 *            "required":false, "type":"string" }, "cron":{ "required":true,
	 *            "type":"string" }, "script":{ "required":true, "type":"long" }
	 *            } }
	 * @return return value 400 : or 500 : or 200 :
	 */
	public String postLeaderboards(String body) {
		Map<String, Object> array = new HashMap<String, Object>();

		return this.callService("/admin/leaderboards", "post", array, body);
	}

	/**
	 * Access to url : /admin/leaderboards. Get a leaderboard list.
	 * 
	 * @param offset
	 *            Number of returned items.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a leaderboard",
	 *         "type":"object", "properties":{ "active":{ "required":false,
	 *         "type":"boolean", "default":"false" }, "name":{ "required":true,
	 *         "type":"string" }, "description":{ "required":false,
	 *         "type":"string" }, "cron":{ "required":true, "type":"string" },
	 *         "script":{ "required":true, "type":"long" } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param limit
	 *            Starting position.
	 * @param fields
	 *            The fields we want to show in our result.
	 */
	public String getLeaderboards(String fields, Long offset, Long limit,
			String order) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("fields", fields);
		array.put("offset", offset);
		array.put("limit", limit);
		array.put("order", order);

		return this.callService("/admin/leaderboards", "get", array, null);
	}

	/**
	 * Access to url : /admin/transactionsources/doc. Get a pseudo schema
	 * showing quickly how to make a proper json for transaction source.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String getTransactionSourcesDocs() {
		Map<String, Object> array = new HashMap<String, Object>();

		return this.callService("/admin/transactionsources/doc", "get", array,
				null);
	}

	/**
	 * Access to url : /admin/transactionsources/{id}/function/{childId}. Delete
	 * a transaction source function.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteOneTransactionSourceFunction(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService(
				"/admin/transactionsources/{id}/function/{childId}", "delete",
				array, null);
	}

	/**
	 * Access to url : /admin/transactionsources/{id}/function/{childId}. Add a
	 * transaction source function.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putOneTransactionSourceFunction(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService(
				"/admin/transactionsources/{id}/function/{childId}", "put",
				array, null);
	}

	/**
	 * Access to url : /admin/transactionsources/{id}. Delete a transaction
	 * source.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteOneTransactionSource(String id) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/transactionsources/{id}", "delete",
				array, null);
	}

	/**
	 * Access to url : /admin/transactionsources/{id}. Update a transaction
	 * source.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a transaction source",
	 *            "type":"object", "properties":{ "name":{ "required":false,
	 *            "type":"string" }, "description":{ "required":false,
	 *            "type":"string" }, "endpoint":{ "required":false,
	 *            "type":"string" }, "functions":{ "required":false,
	 *            "type":"id_array" } } }
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putOneTransactionSource(String id, String body) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/transactionsources/{id}", "put", array,
				body);
	}

	/**
	 * Access to url : /admin/transactionsources/{id}.
	 * 
	 * @param fields
	 *            The fields we want to show in our result. Get a transaction
	 *            source.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a transaction source",
	 *         "type":"object", "properties":{ "name":{ "required":false,
	 *         "type":"string" }, "description":{ "required":false,
	 *         "type":"string" }, "endpoint":{ "required":false, "type":"string"
	 *         }, "functions":{ "required":false, "type":"id_array" } } }
	 */
	public String getOneTransactionSource(String id, String fields) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("fields", fields);

		return this.callService("/admin/transactionsources/{id}", "get", array,
				null);
	}

	/**
	 * Access to url : /admin/transactionsources. Add a transaction source.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a transaction source",
	 *            "type":"object", "properties":{ "name":{ "required":false,
	 *            "type":"string" }, "description":{ "required":false,
	 *            "type":"string" }, "endpoint":{ "required":false,
	 *            "type":"string" }, "functions":{ "required":false,
	 *            "type":"id_array" } } }
	 * @return return value 400 : or 500 : or 200 :
	 */
	public String postTransactionSources(String body) {
		Map<String, Object> array = new HashMap<String, Object>();

		return this.callService("/admin/transactionsources", "post", array,
				body);
	}

	/**
	 * Access to url : /admin/transactionsources. Get a list of transaction
	 * sources.
	 * 
	 * @param offset
	 *            Number of returned items.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a transaction source",
	 *         "type":"object", "properties":{ "name":{ "required":false,
	 *         "type":"string" }, "description":{ "required":false,
	 *         "type":"string" }, "endpoint":{ "required":false, "type":"string"
	 *         }, "functions":{ "required":false, "type":"id_array" } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param limit
	 *            Starting position.
	 * @param fields
	 *            The fields we want to show in our result.
	 */
	public String getTransactionSources(String fields, Long offset, Long limit,
			String order) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("fields", fields);
		array.put("offset", offset);
		array.put("limit", limit);
		array.put("order", order);

		return this
				.callService("/admin/transactionsources", "get", array, null);
	}

	/**
	 * Access to url : /admin/users/doc. Get a pseudo schema showing quickly how
	 * to make a proper json for users.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String getUsersDoc() {
		Map<String, Object> array = new HashMap<String, Object>();

		return this.callService("/admin/users/doc", "get", array, null);
	}

	/**
	 * Access to url : /admin/users/groups/doc. Get a pseudo schema showing
	 * quickly how to make a proper json for user group.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String getUserGroupsDoc() {
		Map<String, Object> array = new HashMap<String, Object>();

		return this.callService("/admin/users/groups/doc", "get", array, null);
	}

	/**
	 * Access to url : /admin/users/groups/{id}/gainedachievements/doc. Get a
	 * pseudo schema showing quickly how to make a proper json for user group
	 * gained achivements.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String getUserGroupGainedAchievementDocs(String id) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService(
				"/admin/users/groups/{id}/gainedachievements/doc", "get",
				array, null);
	}

	/**
	 * Access to url : /admin/users/groups/{id}/gainedachievements/{childId}.
	 * Delete a gained achievement for a group by id.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteOneUserGroupGainedAchievement(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService(
				"/admin/users/groups/{id}/gainedachievements/{childId}",
				"delete", array, null);
	}

	/**
	 * Access to url : /admin/users/groups/{id}/gainedachievements/{childId}.
	 * Update a gained achievement for a group by id.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a gained achievement",
	 *            "type":"object", "properties":{ "achievement":{
	 *            "required":true, "type":"id" }, "status":{ "required":false,
	 *            "type":"string", "allowed":{ "0":"INACTIVE", "1":"ACTIVE",
	 *            "2":"REVOKED" }, "default":"INACTIVE" }, "origin":{
	 *            "required":false, "type":"string", "allowed":{ "0":"AUTO",
	 *            "1":"MANUAL" }, "default":"AUTO" }, "amount":{
	 *            "required":false, "type":"long", "default":"1" } } }
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putOneUserGroupGainedAchievement(String id, String childId,
			String body) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService(
				"/admin/users/groups/{id}/gainedachievements/{childId}", "put",
				array, body);
	}

	/**
	 * Access to url : /admin/users/groups/{id}/gainedachievements/{childId}.
	 * Get a gained achievement for a group by id.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a gained achievement",
	 *         "type":"object", "properties":{ "achievement":{ "required":true,
	 *         "type":"id" }, "status":{ "required":false, "type":"string",
	 *         "allowed":{ "0":"INACTIVE", "1":"ACTIVE", "2":"REVOKED" },
	 *         "default":"INACTIVE" }, "origin":{ "required":false,
	 *         "type":"string", "allowed":{ "0":"AUTO", "1":"MANUAL" },
	 *         "default":"AUTO" }, "amount":{ "required":false, "type":"long",
	 *         "default":"1" } } }
	 */
	public String getOneUserGroupGainedAchievement(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService(
				"/admin/users/groups/{id}/gainedachievements/{childId}", "get",
				array, null);
	}

	/**
	 * Access to url : /admin/users/groups/{id}/gainedachievements. Create a
	 * gained achievement for a group.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a gained achievement",
	 *            "type":"object", "properties":{ "achievement":{
	 *            "required":true, "type":"id" }, "status":{ "required":false,
	 *            "type":"string", "allowed":{ "0":"INACTIVE", "1":"ACTIVE",
	 *            "2":"REVOKED" }, "default":"INACTIVE" }, "origin":{
	 *            "required":false, "type":"string", "allowed":{ "0":"AUTO",
	 *            "1":"MANUAL" }, "default":"AUTO" }, "amount":{
	 *            "required":false, "type":"long", "default":"1" } } }
	 * @return return value 400 : or 500 : or 200 :
	 */
	public String postUserGroupGainedAchievements(String id, String body) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/users/groups/{id}/gainedachievements",
				"post", array, body);
	}

	/**
	 * Access to url : /admin/users/groups/{id}/gainedachievements. Get a list
	 * of gained achievements for a group.
	 * 
	 * @param offset
	 *            Number of returned items.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a gained achievement",
	 *         "type":"object", "properties":{ "achievement":{ "required":true,
	 *         "type":"id" }, "status":{ "required":false, "type":"string",
	 *         "allowed":{ "0":"INACTIVE", "1":"ACTIVE", "2":"REVOKED" },
	 *         "default":"INACTIVE" }, "origin":{ "required":false,
	 *         "type":"string", "allowed":{ "0":"AUTO", "1":"MANUAL" },
	 *         "default":"AUTO" }, "amount":{ "required":false, "type":"long",
	 *         "default":"1" } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param limit
	 *            Starting position.
	 * @param fields
	 *            The fields we want to show in our result.
	 */
	public String getUserGroupGainedAchievements(String id, Long offset,
			String order, String fields, Long limit) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("offset", offset);
		array.put("order", order);
		array.put("fields", fields);
		array.put("limit", limit);

		return this.callService("/admin/users/groups/{id}/gainedachievements",
				"get", array, null);
	}

	/**
	 * Access to url : /admin/users/groups/{id}/users/{childId}. Delete a user
	 * from user group.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteOneUserGroupFriend(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService("/admin/users/groups/{id}/users/{childId}",
				"delete", array, null);
	}

	/**
	 * Access to url : /admin/users/groups/{id}/users/{childId}. Add a user to
	 * user group.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putOneUserGroupFriend(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService("/admin/users/groups/{id}/users/{childId}",
				"put", array, null);
	}

	/**
	 * Access to url : /admin/users/groups/{id}/externalids/doc. Get a pseudo
	 * schema showing quickly how to make a proper json for user group external
	 * ids.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String getUserGroupExternalIdsDocs(String id) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/users/groups/{id}/externalids/doc",
				"get", array, null);
	}

	/**
	 * Access to url : /admin/users/groups/{id}/externalids/{childId}. Remove an
	 * external group id.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteOneUserGroupExternalId(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService(
				"/admin/users/groups/{id}/externalids/{childId}", "delete",
				array, null);
	}

	/**
	 * Access to url : /admin/users/groups/{id}/externalids/{childId}. Update an
	 * external group id.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of an external id",
	 *            "type":"object", "properties":{ "name":{ "required":true,
	 *            "type":"string" }, "externalId":{ "required":true,
	 *            "type":"string" } } }
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putOneUserGroupExternalId(String id, String childId,
			String body) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService(
				"/admin/users/groups/{id}/externalids/{childId}", "put", array,
				body);
	}

	/**
	 * Access to url : /admin/users/groups/{id}/externalids/{childId}. Get an
	 * external group id.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of an external id",
	 *         "type":"object", "properties":{ "name":{ "required":true,
	 *         "type":"string" }, "externalId":{ "required":true,
	 *         "type":"string" } } }
	 */
	public String getOneUserGroupExternalId(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService(
				"/admin/users/groups/{id}/externalids/{childId}", "get", array,
				null);
	}

	/**
	 * Access to url : /admin/users/groups/{id}/externalids. Add an external
	 * group id.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of an external id",
	 *            "type":"object", "properties":{ "name":{ "required":true,
	 *            "type":"string" }, "externalId":{ "required":true,
	 *            "type":"string" } } }
	 * @return return value 400 : or 500 : or 200 :
	 */
	public String postUserGroupExternalIds(String id, String body) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/users/groups/{id}/externalids", "post",
				array, body);
	}

	/**
	 * Access to url : /admin/users/groups/{id}/externalids. Get a list of
	 * external ids.
	 * 
	 * @param offset
	 *            Number of returned items.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of an external id",
	 *         "type":"object", "properties":{ "name":{ "required":true,
	 *         "type":"string" }, "externalId":{ "required":true,
	 *         "type":"string" } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param limit
	 *            Starting position.
	 * @param fields
	 *            The fields we want to show in our result.
	 */
	public String getUserGroupExternalIds(String id, Long offset, String order,
			String fields, Long limit) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("offset", offset);
		array.put("order", order);
		array.put("fields", fields);
		array.put("limit", limit);

		return this.callService("/admin/users/groups/{id}/externalids", "get",
				array, null);
	}

	/**
	 * Access to url : /admin/users/groups/{id}/segments/{childId}. Remove a
	 * segment from a user group.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteOneUserGroupSegment(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService("/admin/users/groups/{id}/segments/{childId}",
				"delete", array, null);
	}

	/**
	 * Access to url : /admin/users/groups/{id}/segments/{childId}. Add a
	 * segment to a user group.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putOneUserGroupSegment(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService("/admin/users/groups/{id}/segments/{childId}",
				"put", array, null);
	}

	/**
	 * Access to url : /admin/users/groups/{id}/countervalues/doc. Get a pseudo
	 * schema showing quickly how to make a proper json for user group counter
	 * value.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String getOneUserGroupCounterValueDoc(String id) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/users/groups/{id}/countervalues/doc",
				"get", array, null);
	}

	/**
	 * Access to url : /admin/users/groups/{id}/countervalues/{childId}. Remove
	 * one counter value from a user group.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteOneUserGroupCounterValue(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService(
				"/admin/users/groups/{id}/countervalues/{childId}", "delete",
				array, null);
	}

	/**
	 * Access to url : /admin/users/groups/{id}/countervalues/{childId}. Update
	 * one counter value for a user group.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a counter value",
	 *            "type":"object", "properties":{ "counter":{ "required":true,
	 *            "type":"id" }, "value":{ "required":false, "type":"long",
	 *            "default":"1" } } }
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putOneUserGroupCounterValue(String id, String childId,
			String body) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService(
				"/admin/users/groups/{id}/countervalues/{childId}", "put",
				array, body);
	}

	/**
	 * Access to url : /admin/users/groups/{id}/countervalues/{childId}. Get one
	 * counter value for a user group.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a counter value",
	 *         "type":"object", "properties":{ "counter":{ "required":true,
	 *         "type":"id" }, "value":{ "required":false, "type":"long",
	 *         "default":"1" } } }
	 */
	public String getOneUserGroupCounterValue(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService(
				"/admin/users/groups/{id}/countervalues/{childId}", "get",
				array, null);
	}

	/**
	 * Access to url : /admin/users/groups/{id}/countervalues. Create a new
	 * counter value.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a counter value",
	 *            "type":"object", "properties":{ "counter":{ "required":true,
	 *            "type":"id" }, "value":{ "required":false, "type":"long",
	 *            "default":"1" } } }
	 * @return return value 400 : or 500 : or 200 :
	 */
	public String postUserGroupCounterValues(String id, String body) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/users/groups/{id}/countervalues",
				"post", array, body);
	}

	/**
	 * Access to url : /admin/users/groups/{id}/countervalues. Get a list of
	 * counter values fro a user group.
	 * 
	 * @param offset
	 *            Number of returned items.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a counter value",
	 *         "type":"object", "properties":{ "counter":{ "required":true,
	 *         "type":"id" }, "value":{ "required":false, "type":"long",
	 *         "default":"1" } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param limit
	 *            Starting position.
	 * @param fields
	 *            The fields we want to show in our result.
	 */
	public String getUserGroupCounterValues(String id, Long offset,
			String order, String fields, Long limit) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("offset", offset);
		array.put("order", order);
		array.put("fields", fields);
		array.put("limit", limit);

		return this.callService("/admin/users/groups/{id}/countervalues",
				"get", array, null);
	}

	/**
	 * Access to url : /admin/users/groups/{id}/wongames/doc. Get a pseudo
	 * schema showing quickly how to make a proper json for user group won
	 * games.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String getUserGroupWonGameDoc(String id) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/users/groups/{id}/wongames/doc", "get",
				array, null);
	}

	/**
	 * Access to url : /admin/users/groups/{id}/wongames/{childId}. Delete a won
	 * game for user group by id.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteOneUserGroupWonGame(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService("/admin/users/groups/{id}/wongames/{childId}",
				"delete", array, null);
	}

	/**
	 * Access to url : /admin/users/groups/{id}/wongames/{childId}. Update a won
	 * game for user group by id.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a won game",
	 *            "type":"object", "properties":{ "game":{ "required":true,
	 *            "type":"id" }, "amount":{ "required":false, "type":"long",
	 *            "default":"1" } } }
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putOneUserGroupWonGame(String id, String childId, String body) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService("/admin/users/groups/{id}/wongames/{childId}",
				"put", array, body);
	}

	/**
	 * Access to url : /admin/users/groups/{id}/wongames/{childId}. Get a won
	 * game for user group by id.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a won game", "type":"object",
	 *         "properties":{ "game":{ "required":true, "type":"id" },
	 *         "amount":{ "required":false, "type":"long", "default":"1" } } }
	 */
	public String getOneUserGroupWonGame(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService("/admin/users/groups/{id}/wongames/{childId}",
				"get", array, null);
	}

	/**
	 * Access to url : /admin/users/groups/{id}/wongames. Create a won game for
	 * user group.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a won game",
	 *            "type":"object", "properties":{ "game":{ "required":true,
	 *            "type":"id" }, "amount":{ "required":false, "type":"long",
	 *            "default":"1" } } }
	 * @return return value 400 : or 500 : or 200 :
	 */
	public String postUserGroupWonGames(String id, String body) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/users/groups/{id}/wongames", "post",
				array, body);
	}

	/**
	 * Access to url : /admin/users/groups/{id}/wongames. Get a list of user
	 * group won games.
	 * 
	 * @param offset
	 *            Number of returned items.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a won game", "type":"object",
	 *         "properties":{ "game":{ "required":true, "type":"id" },
	 *         "amount":{ "required":false, "type":"long", "default":"1" } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param limit
	 *            Starting position.
	 * @param fields
	 *            The fields we want to show in our result.
	 */
	public String getUserGroupWonGames(String id, Long offset, String order,
			String fields, Long limit) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("offset", offset);
		array.put("order", order);
		array.put("fields", fields);
		array.put("limit", limit);

		return this.callService("/admin/users/groups/{id}/wongames", "get",
				array, null);
	}

	/**
	 * Access to url : /admin/users/groups/{id}. Delete a user group.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteOneUserGroup(String id) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/users/groups/{id}", "delete", array,
				null);
	}

	/**
	 * Access to url : /admin/users/groups/{id}. Update a user group.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a user group",
	 *            "type":"object", "properties":{ "name":{ "required":true,
	 *            "type":"string", "constraint":"unique" }, "users":{
	 *            "required":false, "type":"id_array" }, "segments":{
	 *            "required":false, "type":"id_array" }, "gainedAchievements":{
	 *            "required":false, "type":"id_array" }, "wonGames":{
	 *            "required":false, "type":"id_array" }, "counterValues":{
	 *            "required":false, "type":"id_array" }, "externalIds":{
	 *            "required":false, "type":"id_array" }, "customFields":{
	 *            "required":false, "type":"array" } } }
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putOneUserGroup(String id, String body) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/users/groups/{id}", "put", array, body);
	}

	/**
	 * Access to url : /admin/users/groups/{id}. Get a user group.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a user group",
	 *         "type":"object", "properties":{ "name":{ "required":true,
	 *         "type":"string", "constraint":"unique" }, "users":{
	 *         "required":false, "type":"id_array" }, "segments":{
	 *         "required":false, "type":"id_array" }, "gainedAchievements":{
	 *         "required":false, "type":"id_array" }, "wonGames":{
	 *         "required":false, "type":"id_array" }, "counterValues":{
	 *         "required":false, "type":"id_array" }, "externalIds":{
	 *         "required":false, "type":"id_array" }, "customFields":{
	 *         "required":false, "type":"array" } } }
	 */
	public String getOneUserGroup(String id) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/users/groups/{id}", "get", array, null);
	}

	/**
	 * Access to url : /admin/users/groups. Add a user group.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a user group",
	 *            "type":"object", "properties":{ "name":{ "required":true,
	 *            "type":"string", "constraint":"unique" }, "users":{
	 *            "required":false, "type":"id_array" }, "segments":{
	 *            "required":false, "type":"id_array" }, "gainedAchievements":{
	 *            "required":false, "type":"id_array" }, "wonGames":{
	 *            "required":false, "type":"id_array" }, "counterValues":{
	 *            "required":false, "type":"id_array" }, "externalIds":{
	 *            "required":false, "type":"id_array" }, "customFields":{
	 *            "required":false, "type":"array" } } }
	 * @return return value 400 : or 500 : or 200 :
	 */
	public String postUserGroups(String body) {
		Map<String, Object> array = new HashMap<String, Object>();

		return this.callService("/admin/users/groups", "post", array, body);
	}

	/**
	 * Access to url : /admin/users/groups. Get a list of user groups.
	 * 
	 * @param offset
	 *            Number of returned items.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a user group",
	 *         "type":"object", "properties":{ "name":{ "required":true,
	 *         "type":"string", "constraint":"unique" }, "users":{
	 *         "required":false, "type":"id_array" }, "segments":{
	 *         "required":false, "type":"id_array" }, "gainedAchievements":{
	 *         "required":false, "type":"id_array" }, "wonGames":{
	 *         "required":false, "type":"id_array" }, "counterValues":{
	 *         "required":false, "type":"id_array" }, "externalIds":{
	 *         "required":false, "type":"id_array" }, "customFields":{
	 *         "required":false, "type":"array" } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param segments
	 *            The segments we want to narrow down our search to.
	 * @param limit
	 *            Starting position.
	 * @param fields
	 *            The fields we want to show in our result.
	 */
	public String getUserGroups(Long offset, String order, String fields,
			String segments, Long limit) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("offset", offset);
		array.put("order", order);
		array.put("fields", fields);
		array.put("segments", segments);
		array.put("limit", limit);

		return this.callService("/admin/users/groups", "get", array, null);
	}

	/**
	 * Access to url : /admin/users/{id}/gainedachievements/doc. Get a pseudo
	 * schema showing quickly how to make a proper json for user gained
	 * achievement.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String getOneUserGainedAchievementsDoc(String id) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/users/{id}/gainedachievements/doc",
				"get", array, null);
	}

	/**
	 * Access to url : /admin/users/{id}/gainedachievements/{childId}. Update
	 * one user gained achievement.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteOneUserGainedAchievement(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService(
				"/admin/users/{id}/gainedachievements/{childId}", "delete",
				array, null);
	}

	/**
	 * Access to url : /admin/users/{id}/gainedachievements/{childId}. Update
	 * one user gained achievement.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a gained achievement",
	 *            "type":"object", "properties":{ "achievement":{
	 *            "required":true, "type":"id" }, "status":{ "required":false,
	 *            "type":"string", "allowed":{ "0":"INACTIVE", "1":"ACTIVE",
	 *            "2":"REVOKED" }, "default":"INACTIVE" }, "origin":{
	 *            "required":false, "type":"string", "allowed":{ "0":"AUTO",
	 *            "1":"MANUAL" }, "default":"AUTO" }, "amount":{
	 *            "required":false, "type":"long", "default":"1" } } }
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putOneUserGainedAchievement(String id, String childId,
			String body) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService(
				"/admin/users/{id}/gainedachievements/{childId}", "put", array,
				body);
	}

	/**
	 * Access to url : /admin/users/{id}/gainedachievements/{childId}. Get one
	 * user gained achievement.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a gained achievement",
	 *         "type":"object", "properties":{ "achievement":{ "required":true,
	 *         "type":"id" }, "status":{ "required":false, "type":"string",
	 *         "allowed":{ "0":"INACTIVE", "1":"ACTIVE", "2":"REVOKED" },
	 *         "default":"INACTIVE" }, "origin":{ "required":false,
	 *         "type":"string", "allowed":{ "0":"AUTO", "1":"MANUAL" },
	 *         "default":"AUTO" }, "amount":{ "required":false, "type":"long",
	 *         "default":"1" } } }
	 */
	public String getOneUserGainedAchievement(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService(
				"/admin/users/{id}/gainedachievements/{childId}", "get", array,
				null);
	}

	/**
	 * Access to url : /admin/users/{id}/gainedachievements. Create a new gained
	 * achievement for a user.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a gained achievement",
	 *            "type":"object", "properties":{ "achievement":{
	 *            "required":true, "type":"id" }, "status":{ "required":false,
	 *            "type":"string", "allowed":{ "0":"INACTIVE", "1":"ACTIVE",
	 *            "2":"REVOKED" }, "default":"INACTIVE" }, "origin":{
	 *            "required":false, "type":"string", "allowed":{ "0":"AUTO",
	 *            "1":"MANUAL" }, "default":"AUTO" }, "amount":{
	 *            "required":false, "type":"long", "default":"1" } } }
	 * @return return value 400 : or 500 : or 200 :
	 */
	public String postUserGainedAchievements(String id, String body) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/users/{id}/gainedachievements", "post",
				array, body);
	}

	/**
	 * Access to url : /admin/users/{id}/gainedachievements. Get a list of
	 * gained achievements for a user.
	 * 
	 * @param offset
	 *            Number of returned items.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a gained achievement",
	 *         "type":"object", "properties":{ "achievement":{ "required":true,
	 *         "type":"id" }, "status":{ "required":false, "type":"string",
	 *         "allowed":{ "0":"INACTIVE", "1":"ACTIVE", "2":"REVOKED" },
	 *         "default":"INACTIVE" }, "origin":{ "required":false,
	 *         "type":"string", "allowed":{ "0":"AUTO", "1":"MANUAL" },
	 *         "default":"AUTO" }, "amount":{ "required":false, "type":"long",
	 *         "default":"1" } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param limit
	 *            Starting position.
	 * @param fields
	 *            The fields we want to show in our result.
	 */
	public String getUserGainedAchievements(String id, Long offset,
			String order, String fields, Long limit) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("offset", offset);
		array.put("order", order);
		array.put("fields", fields);
		array.put("limit", limit);

		return this.callService("/admin/users/{id}/gainedachievements", "get",
				array, null);
	}

	/**
	 * Access to url : /admin/users/{id}/customfields/{key}. Delete a custom
	 * field by key.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteUserCustomField(String id, String key) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("key", key);

		return this.callService("/admin/users/{id}/customfields/{key}",
				"delete", array, null);
	}

	/**
	 * Access to url : /admin/users/{id}/customfields. Create a new custom field
	 * for user.
	 * 
	 * @param body
	 *            application/json schema : {}
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putUserCustomFields(String id, String body) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/users/{id}/customfields", "put", array,
				body);
	}

	/**
	 * Access to url : /admin/users/{id}/groups/{childId}. Remove a group from
	 * user.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteOneGroupUser(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService("/admin/users/{id}/groups/{childId}", "delete",
				array, null);
	}

	/**
	 * Access to url : /admin/users/{id}/groups/{childId}. Add a group to user.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putOneGroupUser(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService("/admin/users/{id}/groups/{childId}", "put",
				array, null);
	}

	/**
	 * Access to url : /admin/users/{id}/externalids/doc. Get a pseudo schema
	 * showing quickly how to make a proper json for user external ids.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String getUserExternalIdsDoc(String id) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/users/{id}/externalids/doc", "get",
				array, null);
	}

	/**
	 * Access to url : /admin/users/{id}/externalids/{childId}. Delete one
	 * external id for a user.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteOneUserExternalId(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService("/admin/users/{id}/externalids/{childId}",
				"delete", array, null);
	}

	/**
	 * Access to url : /admin/users/{id}/externalids/{childId}. Update one
	 * external id for a user.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of an external id",
	 *            "type":"object", "properties":{ "name":{ "required":true,
	 *            "type":"string" }, "externalId":{ "required":true,
	 *            "type":"string" } } }
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putOneUserExternalId(String id, String childId, String body) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService("/admin/users/{id}/externalids/{childId}",
				"put", array, body);
	}

	/**
	 * Access to url : /admin/users/{id}/externalids/{childId}. Get one external
	 * id for a user.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of an external id",
	 *         "type":"object", "properties":{ "name":{ "required":true,
	 *         "type":"string" }, "externalId":{ "required":true,
	 *         "type":"string" } } }
	 */
	public String getOneUserExternalId(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService("/admin/users/{id}/externalids/{childId}",
				"get", array, null);
	}

	/**
	 * Access to url : /admin/users/{id}/externalids. Create a new external id.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of an external id",
	 *            "type":"object", "properties":{ "name":{ "required":true,
	 *            "type":"string" }, "externalId":{ "required":true,
	 *            "type":"string" } } }
	 * @return return value 400 : or 500 : or 200 :
	 */
	public String postUserExternalIds(String id, String body) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/users/{id}/externalids", "post", array,
				body);
	}

	/**
	 * Access to url : /admin/users/{id}/externalids. Get a list of external
	 * ids.
	 * 
	 * @param offset
	 *            Number of returned items.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of an external id",
	 *         "type":"object", "properties":{ "name":{ "required":true,
	 *         "type":"string" }, "externalId":{ "required":true,
	 *         "type":"string" } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param limit
	 *            Starting position.
	 * @param fields
	 *            The fields we want to show in our result.
	 */
	public String getUserExternalIds(String id, Long offset, String order,
			String fields, Long limit) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("offset", offset);
		array.put("order", order);
		array.put("fields", fields);
		array.put("limit", limit);

		return this.callService("/admin/users/{id}/externalids", "get", array,
				null);
	}

	/**
	 * Access to url : /admin/users/{id}/segments/{childId}. Delete a segment
	 * from a user.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteOneUserSegment(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService("/admin/users/{id}/segments/{childId}",
				"delete", array, null);
	}

	/**
	 * Access to url : /admin/users/{id}/segments/{childId}. Add a segment to a
	 * user.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putOneUserSegment(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService("/admin/users/{id}/segments/{childId}", "put",
				array, null);
	}

	/**
	 * Access to url : /admin/users/{id}/countervalues/doc. Get a pseudo schema
	 * showing quickly how to make a proper json for user counter values.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String getUserCounterValueDoc(String id) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/users/{id}/countervalues/doc", "get",
				array, null);
	}

	/**
	 * Access to url : /admin/users/{id}/countervalues/{childId}. Delete one
	 * user counter value.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteOneUserCounterValue(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService("/admin/users/{id}/countervalues/{childId}",
				"delete", array, null);
	}

	/**
	 * Access to url : /admin/users/{id}/countervalues/{childId}. Update one
	 * user counter value.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a counter value",
	 *            "type":"object", "properties":{ "counter":{ "required":true,
	 *            "type":"id" }, "value":{ "required":false, "type":"long",
	 *            "default":"1" } } }
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putOneUserCounterValue(String id, String childId, String body) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService("/admin/users/{id}/countervalues/{childId}",
				"put", array, body);
	}

	/**
	 * Access to url : /admin/users/{id}/countervalues/{childId}. Get one user
	 * counter value.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a counter value",
	 *         "type":"object", "properties":{ "counter":{ "required":true,
	 *         "type":"id" }, "value":{ "required":false, "type":"long",
	 *         "default":"1" } } }
	 */
	public String getOneUserCounterValue(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService("/admin/users/{id}/countervalues/{childId}",
				"get", array, null);
	}

	/**
	 * Access to url : /admin/users/{id}/countervalues. Create a new counter
	 * value for a user.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a counter value",
	 *            "type":"object", "properties":{ "counter":{ "required":true,
	 *            "type":"id" }, "value":{ "required":false, "type":"long",
	 *            "default":"1" } } }
	 * @return return value 400 : or 500 : or 200 :
	 */
	public String postUserCounterValues(String id, String body) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/users/{id}/countervalues", "post",
				array, body);
	}

	/**
	 * Access to url : /admin/users/{id}/countervalues. Get a list of counter
	 * values for a user.
	 * 
	 * @param offset
	 *            Number of returned items.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a counter value",
	 *         "type":"object", "properties":{ "counter":{ "required":true,
	 *         "type":"id" }, "value":{ "required":false, "type":"long",
	 *         "default":"1" } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param limit
	 *            Starting position.
	 * @param fields
	 *            The fields we want to show in our result.
	 */
	public String getUserCounterValues(String id, Long offset, String order,
			String fields, Long limit) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("offset", offset);
		array.put("order", order);
		array.put("fields", fields);
		array.put("limit", limit);

		return this.callService("/admin/users/{id}/countervalues", "get",
				array, null);
	}

	/**
	 * Access to url : /admin/users/{id}/wongames/doc. Get a pseudo schema
	 * showing quickly how to make a proper json for user won game.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String getUserWonGamesDoc(String id) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/users/{id}/wongames/doc", "get", array,
				null);
	}

	/**
	 * Access to url : /admin/users/{id}/wongames/{childId}. Delete one won game
	 * for a user.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteOneUserWonGame(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService("/admin/users/{id}/wongames/{childId}",
				"delete", array, null);
	}

	/**
	 * Access to url : /admin/users/{id}/wongames/{childId}. Update one won game
	 * for a user.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a won game",
	 *            "type":"object", "properties":{ "game":{ "required":true,
	 *            "type":"id" }, "amount":{ "required":false, "type":"long",
	 *            "default":"1" } } }
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putOneUserWonGame(String id, String childId, String body) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService("/admin/users/{id}/wongames/{childId}", "put",
				array, body);
	}

	/**
	 * Access to url : /admin/users/{id}/wongames/{childId}. Get one won game
	 * for a user.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a won game", "type":"object",
	 *         "properties":{ "game":{ "required":true, "type":"id" },
	 *         "amount":{ "required":false, "type":"long", "default":"1" } } }
	 */
	public String getOneUserWonGame(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService("/admin/users/{id}/wongames/{childId}", "get",
				array, null);
	}

	/**
	 * Access to url : /admin/users/{id}/wongames. Create a new won game for a
	 * user.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a won game",
	 *            "type":"object", "properties":{ "game":{ "required":true,
	 *            "type":"id" }, "amount":{ "required":false, "type":"long",
	 *            "default":"1" } } }
	 * @return return value 400 : or 500 : or 200 :
	 */
	public String postUserWonGames(String id, String body) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/users/{id}/wongames", "post", array,
				body);
	}

	/**
	 * Access to url : /admin/users/{id}/wongames. Get a list of won games for a
	 * user.
	 * 
	 * @param offset
	 *            Number of returned items.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a won game", "type":"object",
	 *         "properties":{ "game":{ "required":true, "type":"id" },
	 *         "amount":{ "required":false, "type":"long", "default":"1" } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param limit
	 *            Starting position.
	 * @param fields
	 *            The fields we want to show in our result.
	 */
	public String getUserWonGames(String id, Long offset, String order,
			String fields, Long limit) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("offset", offset);
		array.put("order", order);
		array.put("fields", fields);
		array.put("limit", limit);

		return this.callService("/admin/users/{id}/wongames", "get", array,
				null);
	}

	/**
	 * Access to url : /admin/users/{id}/friends/{childId}. Remove a friend from
	 * user.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteOneUserFriend(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService("/admin/users/{id}/friends/{childId}",
				"delete", array, null);
	}

	/**
	 * Access to url : /admin/users/{id}/friends/{childId}. Add a friend to
	 * user.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putOneUserFriend(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService("/admin/users/{id}/friends/{childId}", "put",
				array, null);
	}

	/**
	 * Access to url : /admin/users/{id}. Delete one user
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteOneUser(String id) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/users/{id}", "delete", array, null);
	}

	/**
	 * Access to url : /admin/users/{id}. Update one user by id.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a user", "type":"object",
	 *            "properties":{ "email":{ "required":true, "type":"string",
	 *            "constraint":"must be unique", "description":"email address"
	 *            }, "password":{ "required":true, "type":"string" },
	 *            "firstName":{ "required":false, "type":"string" },
	 *            "lastName":{ "required":false, "type":"string" }, "gender":{
	 *            "required":false, "type":"string", "allowed":{ "0":"UNKNOWN",
	 *            "1":"MALE", "2":"FEMALE" }, "default":"UNKNOWN" },
	 *            "birthDate":{ "required":false, "type":"string" }, "status":{
	 *            "required":false, "type":"string", "allowed":{ "0":"UNACTIVE",
	 *            "1":"ACTIVE", "2":"RESIGNED", "3":"BANNED" },
	 *            "default":"UNACTIVE" }, "role":{ "required":false,
	 *            "type":"string" }, "friends":{ "required":false,
	 *            "type":"id_array" }, "usersGroups":{ "required":false,
	 *            "type":"id_array" }, "segments":{ "required":false,
	 *            "type":"id_array" }, "gainedAchievements":{ "required":false,
	 *            "type":"id_array" }, "wonGames":{ "required":false,
	 *            "type":"id_array" }, "counterValues":{ "required":false,
	 *            "type":"id_array" }, "externalIds":{ "required":false,
	 *            "type":"id_array" }, "customFields":{ "required":false,
	 *            "type":"array" } } }
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putOneUser(String id, String body) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/users/{id}", "put", array, body);
	}

	/**
	 * Access to url : /admin/users/{id}. Get one user by id.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a user", "type":"object",
	 *         "properties":{ "email":{ "required":true, "type":"string",
	 *         "constraint":"must be unique", "description":"email address" },
	 *         "password":{ "required":true, "type":"string" }, "firstName":{
	 *         "required":false, "type":"string" }, "lastName":{
	 *         "required":false, "type":"string" }, "gender":{ "required":false,
	 *         "type":"string", "allowed":{ "0":"UNKNOWN", "1":"MALE",
	 *         "2":"FEMALE" }, "default":"UNKNOWN" }, "birthDate":{
	 *         "required":false, "type":"string" }, "status":{ "required":false,
	 *         "type":"string", "allowed":{ "0":"UNACTIVE", "1":"ACTIVE",
	 *         "2":"RESIGNED", "3":"BANNED" }, "default":"UNACTIVE" }, "role":{
	 *         "required":false, "type":"string" }, "friends":{
	 *         "required":false, "type":"id_array" }, "usersGroups":{
	 *         "required":false, "type":"id_array" }, "segments":{
	 *         "required":false, "type":"id_array" }, "gainedAchievements":{
	 *         "required":false, "type":"id_array" }, "wonGames":{
	 *         "required":false, "type":"id_array" }, "counterValues":{
	 *         "required":false, "type":"id_array" }, "externalIds":{
	 *         "required":false, "type":"id_array" }, "customFields":{
	 *         "required":false, "type":"array" } } }
	 */
	public String getOneUser(String id) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/users/{id}", "get", array, null);
	}

	/**
	 * Access to url : /admin/users. Add a user.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a user", "type":"object",
	 *            "properties":{ "email":{ "required":true, "type":"string",
	 *            "constraint":"must be unique", "description":"email address"
	 *            }, "password":{ "required":true, "type":"string" },
	 *            "firstName":{ "required":false, "type":"string" },
	 *            "lastName":{ "required":false, "type":"string" }, "gender":{
	 *            "required":false, "type":"string", "allowed":{ "0":"UNKNOWN",
	 *            "1":"MALE", "2":"FEMALE" }, "default":"UNKNOWN" },
	 *            "birthDate":{ "required":false, "type":"string" }, "status":{
	 *            "required":false, "type":"string", "allowed":{ "0":"UNACTIVE",
	 *            "1":"ACTIVE", "2":"RESIGNED", "3":"BANNED" },
	 *            "default":"UNACTIVE" }, "role":{ "required":false,
	 *            "type":"string" }, "friends":{ "required":false,
	 *            "type":"id_array" }, "usersGroups":{ "required":false,
	 *            "type":"id_array" }, "segments":{ "required":false,
	 *            "type":"id_array" }, "gainedAchievements":{ "required":false,
	 *            "type":"id_array" }, "wonGames":{ "required":false,
	 *            "type":"id_array" }, "counterValues":{ "required":false,
	 *            "type":"id_array" }, "externalIds":{ "required":false,
	 *            "type":"id_array" }, "customFields":{ "required":false,
	 *            "type":"array" } } }
	 * @return return value 400 : or 500 : or 200 :
	 */
	public String postUsers(String body) {
		Map<String, Object> array = new HashMap<String, Object>();

		return this.callService("/admin/users", "post", array, body);
	}

	/**
	 * Access to url : /admin/users. Get a list of users.
	 * 
	 * @param groups
	 *            The groups we want to narrow down our search to.
	 * @param offset
	 *            Number of returned items.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a user", "type":"object",
	 *         "properties":{ "email":{ "required":true, "type":"string",
	 *         "constraint":"must be unique", "description":"email address" },
	 *         "password":{ "required":true, "type":"string" }, "firstName":{
	 *         "required":false, "type":"string" }, "lastName":{
	 *         "required":false, "type":"string" }, "gender":{ "required":false,
	 *         "type":"string", "allowed":{ "0":"UNKNOWN", "1":"MALE",
	 *         "2":"FEMALE" }, "default":"UNKNOWN" }, "birthDate":{
	 *         "required":false, "type":"string" }, "status":{ "required":false,
	 *         "type":"string", "allowed":{ "0":"UNACTIVE", "1":"ACTIVE",
	 *         "2":"RESIGNED", "3":"BANNED" }, "default":"UNACTIVE" }, "role":{
	 *         "required":false, "type":"string" }, "friends":{
	 *         "required":false, "type":"id_array" }, "usersGroups":{
	 *         "required":false, "type":"id_array" }, "segments":{
	 *         "required":false, "type":"id_array" }, "gainedAchievements":{
	 *         "required":false, "type":"id_array" }, "wonGames":{
	 *         "required":false, "type":"id_array" }, "counterValues":{
	 *         "required":false, "type":"id_array" }, "externalIds":{
	 *         "required":false, "type":"id_array" }, "customFields":{
	 *         "required":false, "type":"array" } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param segments
	 *            The segments we want to narrow down our search to.
	 * @param limit
	 *            Starting position.
	 * @param fields
	 *            The fields we want to show in our result.
	 */
	public String getUsers(String groups, Long offset, String order,
			String fields, String segments, Long limit) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("groups", groups);
		array.put("offset", offset);
		array.put("order", order);
		array.put("fields", fields);
		array.put("segments", segments);
		array.put("limit", limit);

		return this.callService("/admin/users", "get", array, null);
	}

	/**
	 * Access to url : /admin/clientscripts/doc. Get a pseudo schema showing
	 * quickly how to make a proper json for clientscripts.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String getClientScriptsDoc() {
		Map<String, Object> array = new HashMap<String, Object>();

		return this.callService("/admin/clientscripts/doc", "get", array, null);
	}

	/**
	 * Access to url : /admin/clientscripts/{id}. Delete one client script.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteOneClientScript(String id) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/clientscripts/{id}", "delete", array,
				null);
	}

	/**
	 * Access to url : /admin/clientscripts/{id}. Change one client script.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a client script",
	 *         "type":"object", "properties":{ "name":{ "required":true,
	 *         "type":"string" }, "code":{ "required":true, "type":"string" },
	 *         "returnType":{ "required":true, "type":"string", "allowed":{
	 *         "0":"NUMBER", "1":"STRING", "2":"BOOLEAN" }, "default":"STRING"
	 *         }, "hash":{ "required":false, "type":"string" } } }
	 */
	public String putOneClientScript(String id) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this
				.callService("/admin/clientscripts/{id}", "put", array, null);
	}

	/**
	 * Access to url : /admin/clientscripts/{id}.
	 * 
	 * @param fields
	 *            The fields we want to show in our result. Get one client
	 *            script by id.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a client script",
	 *         "type":"object", "properties":{ "name":{ "required":true,
	 *         "type":"string" }, "code":{ "required":true, "type":"string" },
	 *         "returnType":{ "required":true, "type":"string", "allowed":{
	 *         "0":"NUMBER", "1":"STRING", "2":"BOOLEAN" }, "default":"STRING"
	 *         }, "hash":{ "required":false, "type":"string" } } }
	 */
	public String getOneClientScript(String id, String fields) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("fields", fields);

		return this
				.callService("/admin/clientscripts/{id}", "get", array, null);
	}

	/**
	 * Access to url : /admin/clientscripts. Post a new client script.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a client script",
	 *            "type":"object", "properties":{ "name":{ "required":true,
	 *            "type":"string" }, "code":{ "required":true, "type":"string"
	 *            }, "returnType":{ "required":true, "type":"string",
	 *            "allowed":{ "0":"NUMBER", "1":"STRING", "2":"BOOLEAN" },
	 *            "default":"STRING" }, "hash":{ "required":false,
	 *            "type":"string" } } }
	 * @return return value 400 : or 500 : or 200 :
	 */
	public String postClientScripts(String body) {
		Map<String, Object> array = new HashMap<String, Object>();

		return this.callService("/admin/clientscripts", "post", array, body);
	}

	/**
	 * Access to url : /admin/clientscripts. Get a list of client scripts.
	 * 
	 * @param offset
	 *            Number of returned items.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a client script",
	 *         "type":"object", "properties":{ "name":{ "required":true,
	 *         "type":"string" }, "code":{ "required":true, "type":"string" },
	 *         "returnType":{ "required":true, "type":"string", "allowed":{
	 *         "0":"NUMBER", "1":"STRING", "2":"BOOLEAN" }, "default":"STRING"
	 *         }, "hash":{ "required":false, "type":"string" } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param limit
	 *            Starting position.
	 * @param fields
	 *            The fields we want to show in our result.
	 */
	public String getClientScripts(String fields, String order, Long offset,
			Long limit) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("fields", fields);
		array.put("order", order);
		array.put("offset", offset);
		array.put("limit", limit);

		return this.callService("/admin/clientscripts", "get", array, null);
	}

	/**
	 * Access to url : /admin/segments/doc. Get a pseudo schema showing quickly
	 * how to make a proper json for segment.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String getSegmentsDocs() {
		Map<String, Object> array = new HashMap<String, Object>();

		return this.callService("/admin/segments/doc", "get", array, null);
	}

	/**
	 * Access to url : /admin/segments/{id}. Delete a segment.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteSegment(String id) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/segments/{id}", "delete", array, null);
	}

	/**
	 * Access to url : /admin/segments/{id}. Update a segment.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a segment",
	 *            "type":"object", "properties":{ "name":{ "required":true,
	 *            "type":"string", "constraint":"unique" } } }
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putSegment(String id, String body) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/segments/{id}", "put", array, body);
	}

	/**
	 * Access to url : /admin/segments/{id}.
	 * 
	 * @param fields
	 *            The fields we want to show in our result. Get a segment.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a segment", "type":"object",
	 *         "properties":{ "name":{ "required":true, "type":"string",
	 *         "constraint":"unique" } } }
	 */
	public String getSegment(String id, String fields) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("fields", fields);

		return this.callService("/admin/segments/{id}", "get", array, null);
	}

	/**
	 * Access to url : /admin/segments. Add a new segment.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a segment",
	 *            "type":"object", "properties":{ "name":{ "required":true,
	 *            "type":"string", "constraint":"unique" } } }
	 * @return return value 400 : or 500 : or 200 :
	 */
	public String postSegments(String body) {
		Map<String, Object> array = new HashMap<String, Object>();

		return this.callService("/admin/segments", "post", array, body);
	}

	/**
	 * Access to url : /admin/segments. Get a segment list.
	 * 
	 * @param offset
	 *            Number of returned items.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a segment", "type":"object",
	 *         "properties":{ "name":{ "required":true, "type":"string",
	 *         "constraint":"unique" } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param limit
	 *            Starting position.
	 * @param fields
	 *            The fields we want to show in our result.
	 */
	public String getSegments(String fields, Long offset, Long limit,
			String order) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("fields", fields);
		array.put("offset", offset);
		array.put("limit", limit);
		array.put("order", order);

		return this.callService("/admin/segments", "get", array, null);
	}

	/**
	 * Access to url : /admin/conditions/doc. Get a pseudo schema showing
	 * quickly how to make a proper json for clientscripts.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String getConditionsDoc() {
		Map<String, Object> array = new HashMap<String, Object>();

		return this.callService("/admin/conditions/doc", "get", array, null);
	}

	/**
	 * Access to url : /admin/conditions/{id}/counters/{childId}. Remove a
	 * counter.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteOneConditionCounter(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService("/admin/conditions/{id}/counters/{childId}",
				"delete", array, null);
	}

	/**
	 * Access to url : /admin/conditions/{id}/counters/{childId}. Add a counter.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putOneConditionCounter(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService("/admin/conditions/{id}/counters/{childId}",
				"put", array, null);
	}

	/**
	 * Access to url : /admin/conditions/{id}. Delete one condition.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteOneCondition(String id) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this
				.callService("/admin/conditions/{id}", "delete", array, null);
	}

	/**
	 * Access to url : /admin/conditions/{id}. Change one condition.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a condition",
	 *            "type":"object", "properties":{ "name":{ "required":true,
	 *            "type":"string" }, "leftSide":{ "required":false,
	 *            "type":"string" }, "rightSide":{ "required":false,
	 *            "type":"string" }, "operator":{ "required":false,
	 *            "type":"string", "allowed":{ "0":"EQ", "1":"NE", "2":"GT",
	 *            "3":"GTE", "4":"LT", "5":"LTE" }, "default":"EQ" },
	 *            "counters":{ "required":false, "type":"id_array" },
	 *            "conditionType":{ "required":false, "type":"string",
	 *            "allowed":{ "0":"NORMAL", "1":"FUNCTION" }, "default":"NORMAL"
	 *            }, "function":{ "required":false, "type":"long" },
	 *            "functionArgs":{ "required":false, "type":"string" },
	 *            "transactionSource":{ "required":false, "type":"long" } } }
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putOneCondition(String id, String body) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/conditions/{id}", "put", array, body);
	}

	/**
	 * Access to url : /admin/conditions/{id}.
	 * 
	 * @param fields
	 *            The fields we want to show in our result. Get one condition.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a condition", "type":"object",
	 *         "properties":{ "name":{ "required":true, "type":"string" },
	 *         "leftSide":{ "required":false, "type":"string" }, "rightSide":{
	 *         "required":false, "type":"string" }, "operator":{
	 *         "required":false, "type":"string", "allowed":{ "0":"EQ",
	 *         "1":"NE", "2":"GT", "3":"GTE", "4":"LT", "5":"LTE" },
	 *         "default":"EQ" }, "counters":{ "required":false,
	 *         "type":"id_array" }, "conditionType":{ "required":false,
	 *         "type":"string", "allowed":{ "0":"NORMAL", "1":"FUNCTION" },
	 *         "default":"NORMAL" }, "function":{ "required":false,
	 *         "type":"long" }, "functionArgs":{ "required":false,
	 *         "type":"string" }, "transactionSource":{ "required":false,
	 *         "type":"long" } } }
	 */
	public String getOneCondition(String id, String fields) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("fields", fields);

		return this.callService("/admin/conditions/{id}", "get", array, null);
	}

	/**
	 * Access to url : /admin/conditions. Add a new condition.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a condition",
	 *            "type":"object", "properties":{ "name":{ "required":true,
	 *            "type":"string" }, "leftSide":{ "required":false,
	 *            "type":"string" }, "rightSide":{ "required":false,
	 *            "type":"string" }, "operator":{ "required":false,
	 *            "type":"string", "allowed":{ "0":"EQ", "1":"NE", "2":"GT",
	 *            "3":"GTE", "4":"LT", "5":"LTE" }, "default":"EQ" },
	 *            "counters":{ "required":false, "type":"id_array" },
	 *            "conditionType":{ "required":false, "type":"string",
	 *            "allowed":{ "0":"NORMAL", "1":"FUNCTION" }, "default":"NORMAL"
	 *            }, "function":{ "required":false, "type":"long" },
	 *            "functionArgs":{ "required":false, "type":"string" },
	 *            "transactionSource":{ "required":false, "type":"long" } } }
	 * @return return value 400 : or 500 : or 200 :
	 */
	public String postConditions(String body) {
		Map<String, Object> array = new HashMap<String, Object>();

		return this.callService("/admin/conditions", "post", array, body);
	}

	/**
	 * Access to url : /admin/conditions. Get a list of conditions.
	 * 
	 * @param offset
	 *            Number of returned items.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a condition", "type":"object",
	 *         "properties":{ "name":{ "required":true, "type":"string" },
	 *         "leftSide":{ "required":false, "type":"string" }, "rightSide":{
	 *         "required":false, "type":"string" }, "operator":{
	 *         "required":false, "type":"string", "allowed":{ "0":"EQ",
	 *         "1":"NE", "2":"GT", "3":"GTE", "4":"LT", "5":"LTE" },
	 *         "default":"EQ" }, "counters":{ "required":false,
	 *         "type":"id_array" }, "conditionType":{ "required":false,
	 *         "type":"string", "allowed":{ "0":"NORMAL", "1":"FUNCTION" },
	 *         "default":"NORMAL" }, "function":{ "required":false,
	 *         "type":"long" }, "functionArgs":{ "required":false,
	 *         "type":"string" }, "transactionSource":{ "required":false,
	 *         "type":"long" } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param limit
	 *            Starting position.
	 * @param fields
	 *            The fields we want to show in our result.
	 */
	public String getConditions(String fields, Long offset, Long limit,
			String order) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("fields", fields);
		array.put("offset", offset);
		array.put("limit", limit);
		array.put("order", order);

		return this.callService("/admin/conditions", "get", array, null);
	}

	/**
	 * Access to url : /admin/counters/doc. Get a pseudo schema showing quickly
	 * how to make a proper json for clientscripts.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String getCountersDoc() {
		Map<String, Object> array = new HashMap<String, Object>();

		return this.callService("/admin/counters/doc", "get", array, null);
	}

	/**
	 * Access to url : /admin/counters/{id}/conditions/{childId}. Remove a
	 * condition from a counter.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteOneCounterCondition(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService("/admin/counters/{id}/conditions/{childId}",
				"delete", array, null);
	}

	/**
	 * Access to url : /admin/counters/{id}/conditions/{childId}. Add a
	 * condition to a counter.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putOneCounterCondition(String id, String childId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("childId", childId);

		return this.callService("/admin/counters/{id}/conditions/{childId}",
				"put", array, null);
	}

	/**
	 * Access to url : /admin/counters/{id}. Delete one counter.
	 * 
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String deleteOneCounter(String id) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/counters/{id}", "delete", array, null);
	}

	/**
	 * Access to url : /admin/counters/{id}. Add a new counter.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a counter",
	 *            "type":"object", "properties":{ "name":{ "required":true,
	 *            "type":"string" }, "description":{ "required":false,
	 *            "type":"string" }, "value":{ "required":false, "type":"long",
	 *            "default":"0" }, "requirement":{ "required":false,
	 *            "type":"enum", "allowed":{ "0":"ALL", "1":"ANY" },
	 *            "default":"ANY" }, "conditions":{ "required":false,
	 *            "type":"id_array" }, "achievement":{ "required":false,
	 *            "type":"id" }, "game":{ "required":false, "type":"id" },
	 *            "eventSettable":{ "required":false, "type":"boolean",
	 *            "constraint":"Allowed value true/false", "default":"false",
	 *            "description"
	 *            :"This property determines if counter can be set by event" },
	 *            "globalCounter":{ "required":false, "type":"boolean",
	 *            "default":"false" }, "transactionSource":{ "required":false,
	 *            "type":"id" } } }
	 * @return return value 400 : or 404 : or 500 : or 200 :
	 */
	public String putOneCounter(String id, String body) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);

		return this.callService("/admin/counters/{id}", "put", array, body);
	}

	/**
	 * Access to url : /admin/counters/{id}.
	 * 
	 * @param fields
	 *            The fields we want to show in our result. Get one counter.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a counter", "type":"object",
	 *         "properties":{ "name":{ "required":true, "type":"string" },
	 *         "description":{ "required":false, "type":"string" }, "value":{
	 *         "required":false, "type":"long", "default":"0" }, "requirement":{
	 *         "required":false, "type":"enum", "allowed":{ "0":"ALL", "1":"ANY"
	 *         }, "default":"ANY" }, "conditions":{ "required":false,
	 *         "type":"id_array" }, "achievement":{ "required":false,
	 *         "type":"id" }, "game":{ "required":false, "type":"id" },
	 *         "eventSettable":{ "required":false, "type":"boolean",
	 *         "constraint":"Allowed value true/false", "default":"false",
	 *         "description"
	 *         :"This property determines if counter can be set by event" },
	 *         "globalCounter":{ "required":false, "type":"boolean",
	 *         "default":"false" }, "transactionSource":{ "required":false,
	 *         "type":"id" } } }
	 */
	public String getOneCounter(String id, String fields) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("id", id);
		array.put("fields", fields);

		return this.callService("/admin/counters/{id}", "get", array, null);
	}

	/**
	 * Access to url : /admin/counters. Add a new counter.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "description":"A representation of a counter",
	 *            "type":"object", "properties":{ "name":{ "required":true,
	 *            "type":"string" }, "description":{ "required":false,
	 *            "type":"string" }, "value":{ "required":false, "type":"long",
	 *            "default":"0" }, "requirement":{ "required":false,
	 *            "type":"enum", "allowed":{ "0":"ALL", "1":"ANY" },
	 *            "default":"ANY" }, "conditions":{ "required":false,
	 *            "type":"id_array" }, "achievement":{ "required":false,
	 *            "type":"id" }, "game":{ "required":false, "type":"id" },
	 *            "eventSettable":{ "required":false, "type":"boolean",
	 *            "constraint":"Allowed value true/false", "default":"false",
	 *            "description"
	 *            :"This property determines if counter can be set by event" },
	 *            "globalCounter":{ "required":false, "type":"boolean",
	 *            "default":"false" }, "transactionSource":{ "required":false,
	 *            "type":"id" } } }
	 * @return return value 400 : or 500 : or 200 :
	 */
	public String postCounters(String body) {
		Map<String, Object> array = new HashMap<String, Object>();

		return this.callService("/admin/counters", "post", array, body);
	}

	/**
	 * Access to url : /admin/counters. Get one counter.
	 * 
	 * @param offset
	 *            Number of returned items.
	 * @return return value 400 : or 404 : or 500 : or 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a counter", "type":"object",
	 *         "properties":{ "name":{ "required":true, "type":"string" },
	 *         "description":{ "required":false, "type":"string" }, "value":{
	 *         "required":false, "type":"long", "default":"0" }, "requirement":{
	 *         "required":false, "type":"enum", "allowed":{ "0":"ALL", "1":"ANY"
	 *         }, "default":"ANY" }, "conditions":{ "required":false,
	 *         "type":"id_array" }, "achievement":{ "required":false,
	 *         "type":"id" }, "game":{ "required":false, "type":"id" },
	 *         "eventSettable":{ "required":false, "type":"boolean",
	 *         "constraint":"Allowed value true/false", "default":"false",
	 *         "description"
	 *         :"This property determines if counter can be set by event" },
	 *         "globalCounter":{ "required":false, "type":"boolean",
	 *         "default":"false" }, "transactionSource":{ "required":false,
	 *         "type":"id" } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param limit
	 *            Starting position.
	 * @param fields
	 *            The fields we want to show in our result.
	 */
	public String getCounters(Long offset, Long limit, String fields,
			String order) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("offset", offset);
		array.put("limit", limit);
		array.put("fields", fields);
		array.put("order", order);

		return this.callService("/admin/counters", "get", array, null);
	}

}