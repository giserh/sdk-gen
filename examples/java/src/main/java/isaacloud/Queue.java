package isaacloud;

import java.util.Map;
import java.util.HashMap;

/**
 */
public class Queue extends Connector {

	public Queue(Map<String, String> config) {
		super("http://api.isaacloud.com", "http://oauth.isaacloud.com", "0.1",
				config);
	}

	/**
	 * Access to url : /queues/notifications/error/{notificationId}. Update
	 * status for an error notification.
	 * 
	 * @return return value 400 : OR 404 : OR 500 : OR 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "properties":{ "status":{ "type":"string", "required":false } } }
	 */
	public String patchOneErrorNotification(String notificationId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("notificationId", notificationId);

		return this.callService("/queues/notifications/error/{notificationId}",
				"patch", array, null);
	}

	/**
	 * Access to url : /queues/notifications/error/{notificationId}. Get error
	 * notification for a client.
	 * 
	 * @return return value 400 : OR 404 : OR 500 : OR 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "properties":{ "clientId":{ "type":"integer", "required":false },
	 *         "priority":{ "type":"string", "required":false }, "status":{
	 *         "type":"string", "required":false }, "source":{ "type":"integer",
	 *         "required":false }, "createdAt":{ "type":"integer",
	 *         "required":false }, "updatedAt":{ "type":"integer",
	 *         "required":false }, "data":{ "type":"object", "required":false },
	 *         "subjectId":{ "type":"integer", "required":false },
	 *         "subjectType":{ "type":"string", "required":false }, "typeId":{
	 *         "type":"integer", "required":false }, "action":{ "type":"object",
	 *         "required":false }, "ttl":{ "type":"integer", "required":false }
	 *         } }
	 */
	public String getOneErrorNotification(String notificationId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("notificationId", notificationId);

		return this.callService("/queues/notifications/error/{notificationId}",
				"get", array, null);
	}

	/**
	 * Access to url : /queues/notifications/error. Get error notifications for
	 * a client.
	 * 
	 * @param offset
	 *            Number of returning items
	 * @return return value 400 : OR 404 : OR 500 : OR 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "properties":{ "clientId":{ "type":"integer", "required":false },
	 *         "priority":{ "type":"string", "required":false }, "status":{
	 *         "type":"string", "required":false }, "source":{ "type":"integer",
	 *         "required":false }, "createdAt":{ "type":"integer",
	 *         "required":false }, "updatedAt":{ "type":"integer",
	 *         "required":false }, "data":{ "type":"object", "required":false },
	 *         "subjectId":{ "type":"integer", "required":false },
	 *         "subjectType":{ "type":"string", "required":false }, "typeId":{
	 *         "type":"integer", "required":false }, "action":{ "type":"object",
	 *         "required":false }, "ttl":{ "type":"integer", "required":false }
	 *         } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param limit
	 *            Starting position
	 * @param fields
	 *            The fields we want to show in our result
	 */
	public String getErrorNotifications(Long offset, Long limit, String fields,
			String order) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("offset", offset);
		array.put("limit", limit);
		array.put("fields", fields);
		array.put("order", order);

		return this.callService("/queues/notifications/error", "get", array,
				null);
	}

	/**
	 * Access to url : /queues/notifications/{notificationId}. Update status for
	 * a notification.
	 * 
	 * @return return value 400 : OR 404 : OR 500 : OR 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "properties":{ "status":{ "type":"string", "required":false } } }
	 */
	public String patchOneNotification(String notificationId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("notificationId", notificationId);

		return this.callService("/queues/notifications/{notificationId}",
				"patch", array, null);
	}

	/**
	 * Access to url : /queues/notifications/{notificationId}. Get notification
	 * for a client and set its status to IN_PROGRESS.
	 * 
	 * @return return value 400 : OR 404 : OR 500 : OR 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "properties":{ "clientId":{ "type":"integer", "required":false },
	 *         "priority":{ "type":"string", "required":false }, "status":{
	 *         "type":"string", "required":false }, "source":{ "type":"integer",
	 *         "required":false }, "createdAt":{ "type":"integer",
	 *         "required":false }, "updatedAt":{ "type":"integer",
	 *         "required":false }, "data":{ "type":"object", "required":false },
	 *         "subjectId":{ "type":"integer", "required":false },
	 *         "subjectType":{ "type":"string", "required":false }, "typeId":{
	 *         "type":"integer", "required":false }, "action":{ "type":"object",
	 *         "required":false }, "ttl":{ "type":"integer", "required":false }
	 *         } }
	 */
	public String getOneNotification(String notificationId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("notificationId", notificationId);

		return this.callService("/queues/notifications/{notificationId}",
				"get", array, null);
	}

	/**
	 * Access to url : /queues/notifications. Get notifications for a client.
	 * 
	 * @param offset
	 *            Number of returning items
	 * @return return value 400 : OR 404 : OR 500 : OR 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "properties":{ "clientId":{ "type":"integer", "required":false },
	 *         "priority":{ "type":"string", "required":false }, "status":{
	 *         "type":"string", "required":false }, "source":{ "type":"integer",
	 *         "required":false }, "createdAt":{ "type":"integer",
	 *         "required":false }, "updatedAt":{ "type":"integer",
	 *         "required":false }, "data":{ "type":"object", "required":false },
	 *         "subjectId":{ "type":"integer", "required":false },
	 *         "subjectType":{ "type":"string", "required":false }, "typeId":{
	 *         "type":"integer", "required":false }, "action":{ "type":"object",
	 *         "required":false }, "ttl":{ "type":"integer", "required":false }
	 *         } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param limit
	 *            Starting position
	 * @param fields
	 *            The fields we want to show in our result
	 */
	public String getNotifications(Long offset, Long limit, String fields,
			String order) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("offset", offset);
		array.put("limit", limit);
		array.put("fields", fields);
		array.put("order", order);

		return this.callService("/queues/notifications", "get", array, null);
	}

	/**
	 * Access to url : /queues/events/{eventId}. Get one event by Id for a
	 * client.
	 * 
	 * @return return value 400 : OR 404 : OR 500 : OR 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "properties":{ "body":{ "type":"object", "required":false },
	 *         "subjectId":{ "type":"integer", "required":false },
	 *         "subjectType":{ "type":"string", "required":false }, "sourceId":{
	 *         "type":"integer", "required":false }, "priority":{
	 *         "type":"string", "required":false }, "type":{ "type":"string",
	 *         "required":false }, "clientId":{ "type":"integer",
	 *         "required":false }, "status":{ "type":"string", "required":false
	 *         }, "ttl":{ "type":"integer", "required":false }, "createdAt":{
	 *         "type":"integer", "required":false }, "updatedAt":{
	 *         "type":"integer", "required":false }, "actionTime":{
	 *         "type":"integer", "required":false }, "data":{ "type":"object",
	 *         "required":false } } }
	 */
	public String getOneEvent(String eventId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("eventId", eventId);

		return this.callService("/queues/events/{eventId}", "get", array, null);
	}

	/**
	 * Access to url : /queues/events/error/{eventId}. Get one error event by id
	 * for a client.
	 * 
	 * @return return value 400 : OR 404 : OR 500 : OR 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "properties":{ "body":{ "type":"object", "required":false },
	 *         "subjectId":{ "type":"integer", "required":false },
	 *         "subjectType":{ "type":"string", "required":false }, "sourceId":{
	 *         "type":"integer", "required":false }, "priority":{
	 *         "type":"string", "required":false }, "type":{ "type":"string",
	 *         "required":false }, "clientId":{ "type":"integer",
	 *         "required":false }, "status":{ "type":"string", "required":false
	 *         }, "ttl":{ "type":"integer", "required":false }, "createdAt":{
	 *         "type":"integer", "required":false }, "updatedAt":{
	 *         "type":"integer", "required":false }, "actionTime":{
	 *         "type":"integer", "required":false }, "data":{ "type":"object",
	 *         "required":false } } }
	 */
	public String getOneErrorEvent(String eventId) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("eventId", eventId);

		return this.callService("/queues/events/error/{eventId}", "get", array,
				null);
	}

	/**
	 * Access to url : /queues/events/error. Get error events for a client.
	 * 
	 * @param offset
	 *            Number of returning items
	 * @return return value 400 : OR 404 : OR 500 : OR 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "properties":{ "body":{ "type":"object", "required":false },
	 *         "subjectId":{ "type":"integer", "required":false },
	 *         "subjectType":{ "type":"string", "required":false }, "sourceId":{
	 *         "type":"integer", "required":false }, "priority":{
	 *         "type":"string", "required":false }, "type":{ "type":"string",
	 *         "required":false }, "clientId":{ "type":"integer",
	 *         "required":false }, "status":{ "type":"string", "required":false
	 *         }, "ttl":{ "type":"integer", "required":false }, "createdAt":{
	 *         "type":"integer", "required":false }, "updatedAt":{
	 *         "type":"integer", "required":false }, "actionTime":{
	 *         "type":"integer", "required":false }, "data":{ "type":"object",
	 *         "required":false } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param limit
	 *            Starting position
	 * @param fields
	 *            The fields we want to show in our result
	 */
	public String getErrorEvents(Long offset, Long limit, String fields,
			String order) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("offset", offset);
		array.put("limit", limit);
		array.put("fields", fields);
		array.put("order", order);

		return this.callService("/queues/events/error", "get", array, null);
	}

	/**
	 * Access to url : /queues/events. Create a new event.
	 * 
	 * @param body
	 *            application/json schema : {
	 *            "$schema":"http://json-schema.org/draft-03/schema",
	 *            "properties":{ "body":{ "type":"object", "required":false },
	 *            "subjectId":{ "type":"integer", "required":true },
	 *            "subjectType":{ "type":"string", "required":true },
	 *            "sourceId":{ "type":"integer", "required":true }, "priority":{
	 *            "type":"string", "required":true }, "type":{ "type":"string",
	 *            "required":true } } }
	 * @return return value 400 : OR 500 : OR 200 : OR 201 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "properties":{ "url":{ "type":"string", "required":true } } }
	 */
	public String postEvents(String body) {
		Map<String, Object> array = new HashMap<String, Object>();

		return this.callService("/queues/events", "post", array, body);
	}

	/**
	 * Access to url : /queues/events. Get a list of events for a client.
	 * 
	 * @param offset
	 *            Number of returning items
	 * @return return value 400 : OR 404 : OR 500 : OR 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "properties":{ "body":{ "type":"object", "required":false },
	 *         "subjectId":{ "type":"integer", "required":false },
	 *         "subjectType":{ "type":"string", "required":false }, "sourceId":{
	 *         "type":"integer", "required":false }, "priority":{
	 *         "type":"string", "required":false }, "type":{ "type":"string",
	 *         "required":false }, "clientId":{ "type":"integer",
	 *         "required":false }, "status":{ "type":"string", "required":false
	 *         }, "ttl":{ "type":"integer", "required":false }, "createdAt":{
	 *         "type":"integer", "required":false }, "updatedAt":{
	 *         "type":"integer", "required":false }, "actionTime":{
	 *         "type":"integer", "required":false }, "data":{ "type":"object",
	 *         "required":false } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param limit
	 *            Starting position
	 * @param fields
	 *            The fields we want to show in our result
	 */
	public String getEvents(Long offset, Long limit, String fields, String order) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("offset", offset);
		array.put("limit", limit);
		array.put("fields", fields);
		array.put("order", order);

		return this.callService("/queues/events", "get", array, null);
	}

}