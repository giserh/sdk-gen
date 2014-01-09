package isaacloud;

import java.util.Map;
import java.util.HashMap;

/**
 */
public class Cache extends Connector {

	public Cache(Map<String, String> config) {
		super("http://api.isaacloud.com", "http://oauth.isaacloud.com", "0.1",
				config);
	}

	/**
	 * Access to url : /cache/notifications/{notificationId}.
	 * 
	 * @param fields
	 *            The fields we want to show in our result Get one notification.
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
	public String getOneNotification(String notificationId, String fields) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("notificationId", notificationId);
		array.put("fields", fields);

		return this.callService("/cache/notifications/{notificationId}", "get",
				array, null);
	}

	/**
	 * Access to url : /cache/notifications. Get notification collection.
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

		return this.callService("/cache/notifications", "get", array, null);
	}

	/**
	 * Access to url : /cache/leaderboards/{leaderboardId}/groups/{groupId}. Get
	 * user's with a group Id in one of the leaderboards.
	 * 
	 * @param groups
	 *            The groups we want to narrow down our search to
	 * @param offset
	 *            Number of returning items
	 * @return return value 400 : OR 404 : OR 500 : OR 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a user", "type":"object",
	 *         "properties":{ "lb":{ "type":"array", "items":{ "type":"object",
	 *         "properties":{ "sc":{ "type":"integer", "required":true }, "i":{
	 *         "type":"integer", "required":true }, "id":{ "type":"integer",
	 *         "required":true } } }, "required":true } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param segments
	 *            The segments we want to narrow down our search to.
	 * @param limit
	 *            Starting position
	 * @param fields
	 *            The fields we want to show in our result
	 */
	public String getOneLeaderboardGroup(String groups, String groupId,
			Long offset, String order, String fields, String segments,
			String leaderboardId, Long limit) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("groups", groups);
		array.put("groupId", groupId);
		array.put("offset", offset);
		array.put("order", order);
		array.put("fields", fields);
		array.put("segments", segments);
		array.put("leaderboardId", leaderboardId);
		array.put("limit", limit);

		return this.callService(
				"/cache/leaderboards/{leaderboardId}/groups/{groupId}", "get",
				array, null);
	}

	/**
	 * Access to url :
	 * /cache/leaderboards/{leaderboardId}/users/{userId}/friends. Get user's
	 * friends with one of the leaderboards.
	 * 
	 * @param groups
	 *            The groups we want to narrow down our search to
	 * @param offset
	 *            Number of returning items
	 * @return return value 400 : OR 404 : OR 500 : OR 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a user", "type":"object",
	 *         "properties":{ "lb":{ "type":"array", "items":{ "type":"object",
	 *         "properties":{ "sc":{ "type":"integer", "required":true }, "i":{
	 *         "type":"integer", "required":true }, "id":{ "type":"integer",
	 *         "required":true } } }, "required":true } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param segments
	 *            The segments we want to narrow down our search to.
	 * @param limit
	 *            Starting position
	 * @param fields
	 *            The fields we want to show in our result
	 */
	public String getLeaderboardUserFriends(String groups, Long offset,
			String order, String fields, String segments, String leaderboardId,
			String userId, Long limit) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("groups", groups);
		array.put("offset", offset);
		array.put("order", order);
		array.put("fields", fields);
		array.put("segments", segments);
		array.put("leaderboardId", leaderboardId);
		array.put("userId", userId);
		array.put("limit", limit);

		return this.callService(
				"/cache/leaderboards/{leaderboardId}/users/{userId}/friends",
				"get", array, null);
	}

	/**
	 * Access to url : /cache/leaderboards/{leaderboardId}/segments/{segmentId}.
	 * Get user's with a segment Id in one of the leaderboards.
	 * 
	 * @param groups
	 *            The groups we want to narrow down our search to
	 * @param offset
	 *            Number of returning items
	 * @return return value 400 : OR 404 : OR 500 : OR 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a user", "type":"object",
	 *         "properties":{ "lb":{ "type":"array", "items":{ "type":"object",
	 *         "properties":{ "sc":{ "type":"integer", "required":true }, "i":{
	 *         "type":"integer", "required":true }, "id":{ "type":"integer",
	 *         "required":true } } }, "required":true } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param segments
	 *            The segments we want to narrow down our search to.
	 * @param limit
	 *            Starting position
	 * @param fields
	 *            The fields we want to show in our result
	 */
	public String getOneLeaderboardSegment(String segmentId, String groups,
			Long offset, String order, String fields, String segments,
			String leaderboardId, Long limit) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("segmentId", segmentId);
		array.put("groups", groups);
		array.put("offset", offset);
		array.put("order", order);
		array.put("fields", fields);
		array.put("segments", segments);
		array.put("leaderboardId", leaderboardId);
		array.put("limit", limit);

		return this.callService(
				"/cache/leaderboards/{leaderboardId}/segments/{segmentId}",
				"get", array, null);
	}

	/**
	 * Access to url : /cache/leaderboards/{leaderboardId}. Get users with one
	 * of the leaderboards.
	 * 
	 * @param groups
	 *            The groups we want to narrow down our search to
	 * @param offset
	 *            Number of returning items
	 * @return return value 400 : OR 404 : OR 500 : OR 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a user", "type":"object",
	 *         "properties":{ "lb":{ "type":"array", "items":{ "type":"object",
	 *         "properties":{ "sc":{ "type":"integer", "required":true }, "i":{
	 *         "type":"integer", "required":true }, "id":{ "type":"integer",
	 *         "required":true } } }, "required":true } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param segments
	 *            The segments we want to narrow down our search to.
	 * @param limit
	 *            Starting position
	 * @param fields
	 *            The fields we want to show in our result
	 */
	public String getOneLeaderboard(String groups, Long offset, String order,
			String fields, String segments, String leaderboardId, Long limit) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("groups", groups);
		array.put("offset", offset);
		array.put("order", order);
		array.put("fields", fields);
		array.put("segments", segments);
		array.put("leaderboardId", leaderboardId);
		array.put("limit", limit);

		return this.callService("/cache/leaderboards/{leaderboardId}", "get",
				array, null);
	}

	/**
	 * Access to url : /cache/games/{gameId}.
	 * 
	 * @param fields
	 *            The fields we want to show in our result Get one game.
	 * @return return value 400 : OR 404 : OR 500 : OR 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a game", "type":"object",
	 *         "properties":{ "id":{ "required":false, "type":"integer" },
	 *         "createdAt":{ "required":false, "type":"integer" }, "updatedAt":{
	 *         "required":false, "type":"integer" } } }
	 */
	public String getOneGame(String gameId, String fields) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("gameId", gameId);
		array.put("fields", fields);

		return this.callService("/cache/games/{gameId}", "get", array, null);
	}

	/**
	 * Access to url : /cache/games.
	 * 
	 * @param offset
	 *            Number of returning items
	 * @return return value 400 : OR 404 : OR 500 : OR 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a game", "type":"object",
	 *         "properties":{ "id":{ "required":false, "type":"integer" },
	 *         "createdAt":{ "required":false, "type":"integer" }, "updatedAt":{
	 *         "required":false, "type":"integer" } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param segments
	 *            The segments we want to narrow down our search to.
	 * @param limit
	 *            Starting position
	 * @param fields
	 *            The fields we want to show in our result
	 */
	public String getGames(Long offset, String order, String fields,
			String segments, Long limit) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("offset", offset);
		array.put("order", order);
		array.put("fields", fields);
		array.put("segments", segments);
		array.put("limit", limit);

		return this.callService("/cache/games", "get", array, null);
	}

	/**
	 * Access to url : /cache/achievements/{achievementId}.
	 * 
	 * @param fields
	 *            The fields we want to show in our result Get one achievement.
	 * @return return value 400 : OR 404 : OR 500 : OR 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of an achievement",
	 *         "type":"object", "properties":{ "id":{ "required":false,
	 *         "type":"integer" }, "createdAt":{ "required":false,
	 *         "type":"integer" }, "updatedAt":{ "required":false,
	 *         "type":"integer" }, "name":{ "required":false, "type":"string" },
	 *         "description":{ "required":false, "type":"string" }, "segments":{
	 *         "type":"array", "items":{ "type":"integer" }, "required":false }
	 *         } }
	 */
	public String getOneAchievement(String achievementId, String fields) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("achievementId", achievementId);
		array.put("fields", fields);

		return this.callService("/cache/achievements/{achievementId}", "get",
				array, null);
	}

	/**
	 * Access to url : /cache/achievements. Get achievements collection.
	 * 
	 * @param offset
	 *            Number of returning items
	 * @return return value 400 : OR 404 : OR 500 : OR 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a list of achievements",
	 *         "type":"array", "items":{ "type":"object", "required":false,
	 *         "properties":{ "id":{ "required":false, "type":"integer" },
	 *         "createdAt":{ "required":false, "type":"integer" }, "updatedAt":{
	 *         "required":false, "type":"integer" }, "name":{ "required":false,
	 *         "type":"string" }, "description":{ "required":false,
	 *         "type":"string" }, "segments":{ "type":"array", "items":{
	 *         "type":"integer" }, "required":false } } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param segments
	 *            The segments we want to narrow down our search to.
	 * @param limit
	 *            Starting position
	 * @param fields
	 *            The fields we want to show in our result
	 */
	public String getAchievements(Long offset, String order, String fields,
			String segments, Long limit) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("offset", offset);
		array.put("order", order);
		array.put("fields", fields);
		array.put("segments", segments);
		array.put("limit", limit);

		return this.callService("/cache/achievements", "get", array, null);
	}

	/**
	 * Access to url : /cache/users/groups/{groupId}.
	 * 
	 * @param fields
	 *            The fields we want to show in our result Get one group.
	 * @return return value 400 : OR 404 : OR 500 : OR 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a segment", "type":"object",
	 *         "properties":{ "id":{ "required":false, "type":"integer" },
	 *         "createdAt":{ "required":false, "type":"integer" }, "updatedAt":{
	 *         "required":false, "type":"integer" }, "name":{ "required":false,
	 *         "type":"string" }, "description":{ "required":false,
	 *         "type":"string" }, "status":{ "required":false, "type":"string" }
	 *         } }
	 */
	public String getOneGroup(String groupId, String fields) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("groupId", groupId);
		array.put("fields", fields);

		return this.callService("/cache/users/groups/{groupId}", "get", array,
				null);
	}

	/**
	 * Access to url : /cache/users/groups. Get groups collection.
	 * 
	 * @param offset
	 *            Number of returning items
	 * @return return value 400 : OR 404 : OR 500 : OR 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a segment", "type":"object",
	 *         "properties":{ "id":{ "required":false, "type":"integer" },
	 *         "createdAt":{ "required":false, "type":"integer" }, "updatedAt":{
	 *         "required":false, "type":"integer" }, "name":{ "required":false,
	 *         "type":"string" }, "description":{ "required":false,
	 *         "type":"string" }, "status":{ "required":false, "type":"string" }
	 *         } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param limit
	 *            Starting position
	 * @param fields
	 *            The fields we want to show in our result
	 */
	public String getGroups(Long offset, Long limit, String fields, String order) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("offset", offset);
		array.put("limit", limit);
		array.put("fields", fields);
		array.put("order", order);

		return this.callService("/cache/users/groups", "get", array, null);
	}

	/**
	 * Access to url : /cache/users/segments. Get users' segment list.
	 * 
	 * @param offset
	 *            Number of returning items
	 * @return return value 400 : OR 404 : OR 500 : OR 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a segment", "type":"object",
	 *         "properties":{ "id":{ "required":false, "type":"integer" },
	 *         "createdAt":{ "required":false, "type":"integer" }, "updatedAt":{
	 *         "required":false, "type":"integer" }, "name":{ "required":false,
	 *         "type":"string" }, "description":{ "required":false,
	 *         "type":"string" } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param limit
	 *            Starting position
	 * @param fields
	 *            The fields we want to show in our result
	 */
	public String getUsersSegments(String fields, String order, Long offset,
			Long limit) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("fields", fields);
		array.put("order", order);
		array.put("offset", offset);
		array.put("limit", limit);

		return this.callService("/cache/users/segments", "get", array, null);
	}

	/**
	 * Access to url : /cache/users/{userId}/achievements. Get one user's
	 * achievements.
	 * 
	 * @param offset
	 *            Number of returning items
	 * @return return value 400 : OR 404 : OR 500 : OR 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of an achievement",
	 *         "type":"object", "properties":{ "id":{ "required":false,
	 *         "type":"integer" }, "createdAt":{ "required":false,
	 *         "type":"integer" }, "updatedAt":{ "required":false,
	 *         "type":"integer" }, "name":{ "required":false, "type":"string" },
	 *         "description":{ "required":false, "type":"string" }, "segments":{
	 *         "type":"array", "items":{ "type":"integer" }, "required":false }
	 *         } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param segments
	 *            The segments we want to narrow down our search to.
	 * @param limit
	 *            Starting position
	 * @param fields
	 *            The fields we want to show in our result
	 */
	public String getUserAchievements(Long offset, String order, String fields,
			String segments, String userId, Long limit) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("offset", offset);
		array.put("order", order);
		array.put("fields", fields);
		array.put("segments", segments);
		array.put("userId", userId);
		array.put("limit", limit);

		return this.callService("/cache/users/{userId}/achievements", "get",
				array, null);
	}

	/**
	 * Access to url : /cache/users/{userId}/groups. Get one user's segments.
	 * 
	 * @param offset
	 *            Number of returning items
	 * @return return value 400 : OR 404 : OR 500 : OR 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a segment", "type":"object",
	 *         "properties":{ "id":{ "required":false, "type":"integer" },
	 *         "createdAt":{ "required":false, "type":"integer" }, "updatedAt":{
	 *         "required":false, "type":"integer" }, "name":{ "required":false,
	 *         "type":"string" }, "description":{ "required":false,
	 *         "type":"string" }, "status":{ "required":false, "type":"string" }
	 *         } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param limit
	 *            Starting position
	 * @param fields
	 *            The fields we want to show in our result
	 */
	public String getUserGroups(Long offset, String order, String fields,
			String userId, Long limit) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("offset", offset);
		array.put("order", order);
		array.put("fields", fields);
		array.put("userId", userId);
		array.put("limit", limit);

		return this.callService("/cache/users/{userId}/groups", "get", array,
				null);
	}

	/**
	 * Access to url : /cache/users/{userId}/segments. Get one user's segments.
	 * 
	 * @param offset
	 *            Number of returning items
	 * @return return value 400 : OR 404 : OR 500 : OR 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a segment", "type":"object",
	 *         "properties":{ "id":{ "required":false, "type":"integer" },
	 *         "createdAt":{ "required":false, "type":"integer" }, "updatedAt":{
	 *         "required":false, "type":"integer" }, "name":{ "required":false,
	 *         "type":"string" }, "description":{ "required":false,
	 *         "type":"string" } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param limit
	 *            Starting position
	 * @param fields
	 *            The fields we want to show in our result
	 */
	public String getUserSegments(Long offset, String order, String fields,
			String userId, Long limit) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("offset", offset);
		array.put("order", order);
		array.put("fields", fields);
		array.put("userId", userId);
		array.put("limit", limit);

		return this.callService("/cache/users/{userId}/segments", "get", array,
				null);
	}

	/**
	 * Access to url : /cache/users/{userId}/friends. Get one user's friends.
	 * 
	 * @param groups
	 *            The groups we want to narrow down our search to
	 * @param offset
	 *            Number of returning items
	 * @return return value 400 : OR 404 : OR 500 : OR 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a user", "type":"object",
	 *         "properties":{ "id":{ "required":false, "type":"integer" },
	 *         "level":{ "required":false, "type":"integer" }, "email":{
	 *         "required":false, "type":"string" }, "password":{
	 *         "required":false, "type":"string" }, "firstName":{
	 *         "required":false, "type":"string" }, "lastName":{
	 *         "required":false, "type":"string" }, "gender":{ "required":false,
	 *         "type":"string" }, "birthDate":{ "required":false,
	 *         "type":"string" }, "status":{ "required":false, "type":"string"
	 *         }, "role":{ "required":false, "type":"string" }, "friends":{
	 *         "type":"array", "items":{ "type":"integer" }, "required":false },
	 *         "segments":{ "type":"array", "items":{ "type":"integer" },
	 *         "required":false }, "groups":{ "type":"array", "items":{
	 *         "type":"integer" }, "required":false }, "externalIds":{
	 *         "type":"array", "items":{ "type":"integer" }, "required":false },
	 *         "gainedAchievements":{ "required":false, "type":"array",
	 *         "items":{ "type":"object", "properties":{ "aid":"integer",
	 *         "st":"integer", "o":"integer", "amount":"integer",
	 *         "cat":"integer", "uat":"integer" } } }, "wonGames":{
	 *         "required":false, "type":"array", "items":{ "type":"object",
	 *         "properties":{ "gid":"integer", "v":"integer", "cat":"integer",
	 *         "uat":"integer" } } }, "counterValues":{ "required":false,
	 *         "type":"array", "items":{ "type":"object", "properties":{
	 *         "cid":"integer", "v":"integer", "cat":"integer", "uat":"integer"
	 *         } } }, "createdAt":{ "required":false, "type":"integer" },
	 *         "updatedAt":{ "required":false, "type":"integer" } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param segments
	 *            The segments we want to narrow down our search to.
	 * @param limit
	 *            Starting position
	 * @param fields
	 *            The fields we want to show in our result
	 */
	public String getUserFriends(String groups, Long offset, String order,
			String fields, String segments, String userId, Long limit) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("groups", groups);
		array.put("offset", offset);
		array.put("order", order);
		array.put("fields", fields);
		array.put("segments", segments);
		array.put("userId", userId);
		array.put("limit", limit);

		return this.callService("/cache/users/{userId}/friends", "get", array,
				null);
	}

	/**
	 * Access to url : /cache/users/{userId}/history. Get one user's history.
	 * 
	 * @param offset
	 *            Number of returning items
	 * @return return value 400 : OR 404 : OR 500 : OR 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of an achievement",
	 *         "type":"object", "properties":{ "id":{ "required":false,
	 *         "type":"integer" }, "createdAt":{ "required":false,
	 *         "type":"integer" }, "updatedAt":{ "required":false,
	 *         "type":"integer" } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param limit
	 *            Starting position
	 * @param fields
	 *            The fields we want to show in our result
	 */
	public String getUserHistory(Long offset, String order, String fields,
			String userId, Long limit) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("offset", offset);
		array.put("order", order);
		array.put("fields", fields);
		array.put("userId", userId);
		array.put("limit", limit);

		return this.callService("/cache/users/{userId}/history", "get", array,
				null);
	}

	/**
	 * Access to url : /cache/users/{userId}.
	 * 
	 * @param fields
	 *            The fields we want to show in our result Get one user by id.
	 * @return return value 400 : OR 404 : OR 500 : OR 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a user", "type":"object",
	 *         "properties":{ "id":{ "required":false, "type":"integer" },
	 *         "level":{ "required":false, "type":"integer" }, "email":{
	 *         "required":false, "type":"string" }, "password":{
	 *         "required":false, "type":"string" }, "firstName":{
	 *         "required":false, "type":"string" }, "lastName":{
	 *         "required":false, "type":"string" }, "gender":{ "required":false,
	 *         "type":"string" }, "birthDate":{ "required":false,
	 *         "type":"string" }, "status":{ "required":false, "type":"string"
	 *         }, "role":{ "required":false, "type":"string" }, "friends":{
	 *         "type":"array", "items":{ "type":"integer" }, "required":false },
	 *         "segments":{ "type":"array", "items":{ "type":"integer" },
	 *         "required":false }, "groups":{ "type":"array", "items":{
	 *         "type":"integer" }, "required":false }, "externalIds":{
	 *         "type":"array", "items":{ "type":"integer" }, "required":false },
	 *         "gainedAchievements":{ "required":false, "type":"array",
	 *         "items":{ "type":"object", "properties":{ "aid":"integer",
	 *         "st":"integer", "o":"integer", "amount":"integer",
	 *         "cat":"integer", "uat":"integer" } } }, "wonGames":{
	 *         "required":false, "type":"array", "items":{ "type":"object",
	 *         "properties":{ "gid":"integer", "v":"integer", "cat":"integer",
	 *         "uat":"integer" } } }, "counterValues":{ "required":false,
	 *         "type":"array", "items":{ "type":"object", "properties":{
	 *         "cid":"integer", "v":"integer", "cat":"integer", "uat":"integer"
	 *         } } }, "createdAt":{ "required":false, "type":"integer" },
	 *         "updatedAt":{ "required":false, "type":"integer" } } }
	 */
	public String getOneUser(String userId, String fields) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("userId", userId);
		array.put("fields", fields);

		return this.callService("/cache/users/{userId}", "get", array, null);
	}

	/**
	 * Access to url : /cache/users. Get users collection.
	 * 
	 * @param groups
	 *            The groups we want to narrow down our search to
	 * @param offset
	 *            Number of returning items
	 * @return return value 400 : OR 404 : OR 500 : OR 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a user", "type":"object",
	 *         "properties":{ "id":{ "required":false, "type":"integer" },
	 *         "level":{ "required":false, "type":"integer" }, "email":{
	 *         "required":false, "type":"string" }, "password":{
	 *         "required":false, "type":"string" }, "firstName":{
	 *         "required":false, "type":"string" }, "lastName":{
	 *         "required":false, "type":"string" }, "gender":{ "required":false,
	 *         "type":"string" }, "birthDate":{ "required":false,
	 *         "type":"string" }, "status":{ "required":false, "type":"string"
	 *         }, "role":{ "required":false, "type":"string" }, "friends":{
	 *         "type":"array", "items":{ "type":"integer" }, "required":false },
	 *         "segments":{ "type":"array", "items":{ "type":"integer" },
	 *         "required":false }, "groups":{ "type":"array", "items":{
	 *         "type":"integer" }, "required":false }, "externalIds":{
	 *         "type":"array", "items":{ "type":"integer" }, "required":false },
	 *         "gainedAchievements":{ "required":false, "type":"array",
	 *         "items":{ "type":"object", "properties":{ "aid":"integer",
	 *         "st":"integer", "o":"integer", "amount":"integer",
	 *         "cat":"integer", "uat":"integer" } } }, "wonGames":{
	 *         "required":false, "type":"array", "items":{ "type":"object",
	 *         "properties":{ "gid":"integer", "v":"integer", "cat":"integer",
	 *         "uat":"integer" } } }, "counterValues":{ "required":false,
	 *         "type":"array", "items":{ "type":"object", "properties":{
	 *         "cid":"integer", "v":"integer", "cat":"integer", "uat":"integer"
	 *         } } }, "createdAt":{ "required":false, "type":"integer" },
	 *         "updatedAt":{ "required":false, "type":"integer" } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param segments
	 *            The segments we want to narrow down our search to.
	 * @param limit
	 *            Starting position
	 * @param fields
	 *            The fields we want to show in our result
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

		return this.callService("/cache/users", "get", array, null);
	}

	/**
	 * Access to url : /cache/segments/{segmentId}.
	 * 
	 * @param fields
	 *            The fields we want to show in our result Get segment by id.
	 * @return return value 400 : OR 404 : OR 500 : OR 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a segment", "type":"object",
	 *         "properties":{ "id":{ "required":false, "type":"integer" },
	 *         "createdAt":{ "required":false, "type":"integer" }, "updatedAt":{
	 *         "required":false, "type":"integer" }, "name":{ "required":false,
	 *         "type":"string" }, "description":{ "required":false,
	 *         "type":"string" } } }
	 */
	public String getOneSegment(String segmentId, String fields) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("segmentId", segmentId);
		array.put("fields", fields);

		return this.callService("/cache/segments/{segmentId}", "get", array,
				null);
	}

	/**
	 * Access to url : /cache/segments. Get segments list.
	 * 
	 * @param offset
	 *            Number of returning items
	 * @return return value 400 : OR 404 : OR 500 : OR 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of a segment", "type":"object",
	 *         "properties":{ "id":{ "required":false, "type":"integer" },
	 *         "createdAt":{ "required":false, "type":"integer" }, "updatedAt":{
	 *         "required":false, "type":"integer" }, "name":{ "required":false,
	 *         "type":"string" }, "description":{ "required":false,
	 *         "type":"string" } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param limit
	 *            Starting position
	 * @param fields
	 *            The fields we want to show in our result
	 */
	public String getSegments(Long offset, Long limit, String fields,
			String order) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("offset", offset);
		array.put("limit", limit);
		array.put("fields", fields);
		array.put("order", order);

		return this.callService("/cache/segments", "get", array, null);
	}

	/**
	 * Access to url : /cache/events/{eventId}.
	 * 
	 * @param fields
	 *            The fields we want to show in our result Get one done event.
	 * @return return value 200 : application/json : {
	 *         "$schema":"http://json-schema.org/draft-03/schema",
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
	public String getOneEvent(String eventId, String fields) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("eventId", eventId);
		array.put("fields", fields);

		return this.callService("/cache/events/{eventId}", "get", array, null);
	}

	/**
	 * Access to url : /cache/events. Get done events collection.
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

		return this.callService("/cache/events", "get", array, null);
	}

	/**
	 * Access to url : /cache/history. Get history list.
	 * 
	 * @param offset
	 *            Number of returning items
	 * @return return value 400 : OR 404 : OR 500 : OR 200 : application/json :
	 *         { "$schema":"http://json-schema.org/draft-03/schema",
	 *         "description":"A representation of an achievement",
	 *         "type":"object", "properties":{ "id":{ "required":false,
	 *         "type":"integer" }, "createdAt":{ "required":false,
	 *         "type":"integer" }, "updatedAt":{ "required":false,
	 *         "type":"integer" } } }
	 * @param order
	 *            The fields we want to order by and the type of ordering.
	 * @param limit
	 *            Starting position
	 * @param fields
	 *            The fields we want to show in our result
	 */
	public String getHistory(Long offset, Long limit, String fields,
			String order) {
		Map<String, Object> array = new HashMap<String, Object>();
		array.put("offset", offset);
		array.put("limit", limit);
		array.put("fields", fields);
		array.put("order", order);

		return this.callService("/cache/history", "get", array, null);
	}

}