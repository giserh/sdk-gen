
package isaacloud;
import java.util.Map;
import java.util.HashMap;

/**
 */ 
public class Cache extends Connector{

	public Cache(Map<String,String> config)
    {
        super( "http://api.isaacloud.com", "http://oauth.isaacloud.com", "0.1", config);
    }

			/**
* Accessible at url : /cache/notifications/{notificationId}
* fields - The fields we want to show in our result
*  Get one notification.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {
 *   "$schema":"http://json-schema.org/draft-03/schema",
 *   "properties":{
 *      "clientId":{
 *         "type":"integer",
 *         "required":false
 *      },
 *      "priority":{
 *         "type":"string",
 *         "required":false
 *      },
 *      "status":{
 *         "type":"string",
 *         "required":false
 *      },
 *      "source":{
 *         "type":"integer",
 *         "required":false
 *      },
 *      "createdAt":{
 *         "type":"integer",
 *         "required":false
 *      },
 *      "updatedAt":{
 *         "type":"integer",
 *         "required":false
 *      },
 *      "data":{
 *         "type":"object",
 *         "required":false
 *      },
 *      "subjectId":{
 *         "type":"integer",
 *         "required":false
 *      },
 *      "subjectType":{
 *         "type":"string",
 *         "required":false
 *      },
 *      "typeId":{
 *         "type":"integer",
 *         "required":false
 *      },
 *      "action":{
 *         "type":"object",
 *         "required":false
 *      },
 *      "ttl":{
 *         "type":"integer",
 *         "required":false
 *      }
 *   }
 *}
 */ 
public String getOneNotification( String notificationId, String fields ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("notificationId",notificationId);
		array.put("fields",fields);
		
	
		return this.callService( "/cache/notifications/{notificationId}", "get",  array, null);
		}

			/**
* Accessible at url : /cache/notifications
*  Get notification collection.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {
 *   "$schema":"http://json-schema.org/draft-03/schema",
 *   "properties":{
 *      "clientId":{
 *         "type":"integer",
 *         "required":false
 *      },
 *      "priority":{
 *         "type":"string",
 *         "required":false
 *      },
 *      "status":{
 *         "type":"string",
 *         "required":false
 *      },
 *      "source":{
 *         "type":"integer",
 *         "required":false
 *      },
 *      "createdAt":{
 *         "type":"integer",
 *         "required":false
 *      },
 *      "updatedAt":{
 *         "type":"integer",
 *         "required":false
 *      },
 *      "data":{
 *         "type":"object",
 *         "required":false
 *      },
 *      "subjectId":{
 *         "type":"integer",
 *         "required":false
 *      },
 *      "subjectType":{
 *         "type":"string",
 *         "required":false
 *      },
 *      "typeId":{
 *         "type":"integer",
 *         "required":false
 *      },
 *      "action":{
 *         "type":"object",
 *         "required":false
 *      },
 *      "ttl":{
 *         "type":"integer",
 *         "required":false
 *      }
 *   }
 *}
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* limit - Starting position
 */ 
public String getNotifications( long offset, long limit, String fields, String order ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("offset",offset);
		array.put("limit",limit);
		array.put("fields",fields);
		array.put("order",order);
		
	
		return this.callService( "/cache/notifications", "get",  array, null);
		}

			/**
* Accessible at url : /cache/leaderboards/{leaderboardId}/groups/{groupId}
*  Get user's with a group Id in one of the leaderboards.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {
 *   "$schema":"http://json-schema.org/draft-03/schema",
 *   "description":"A representation of a user",
 *   "type":"object",
 *   "properties":{
 *      "lb":{
 *         "type":"array",
 *         "items":{
 *            "type":"object",
 *            "properties":{
 *               "sc":{
 *                  "type":"integer",
 *                  "required":true
 *               },
 *               "i":{
 *                  "type":"integer",
 *                  "required":true
 *               },
 *               "id":{
 *                  "type":"integer",
 *                  "required":true
 *               }
 *            }
 *         },
 *         "required":true
 *      }
 *   }
 *}
* groups - The groups we want to narrow down our search to
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* segments - The segments we want to narrow down our search to.
* limit - Starting position
 */ 
public String getOneLeaderboardGroup( String groups, String groupId, long offset, String order, String fields, String segments, String leaderboardId, long limit ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("groups",groups);
		array.put("groupId",groupId);
		array.put("offset",offset);
		array.put("order",order);
		array.put("fields",fields);
		array.put("segments",segments);
		array.put("leaderboardId",leaderboardId);
		array.put("limit",limit);
		
	
		return this.callService( "/cache/leaderboards/{leaderboardId}/groups/{groupId}", "get",  array, null);
		}

			/**
* Accessible at url : /cache/leaderboards/{leaderboardId}/users/{userId}/friends
*  Get user's friends with one of the leaderboards.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {
 *   "$schema":"http://json-schema.org/draft-03/schema",
 *   "description":"A representation of a user",
 *   "type":"object",
 *   "properties":{
 *      "lb":{
 *         "type":"array",
 *         "items":{
 *            "type":"object",
 *            "properties":{
 *               "sc":{
 *                  "type":"integer",
 *                  "required":true
 *               },
 *               "i":{
 *                  "type":"integer",
 *                  "required":true
 *               },
 *               "id":{
 *                  "type":"integer",
 *                  "required":true
 *               }
 *            }
 *         },
 *         "required":true
 *      }
 *   }
 *}
* groups - The groups we want to narrow down our search to
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* segments - The segments we want to narrow down our search to.
* limit - Starting position
 */ 
public String getLeaderboardUserFriends( String groups, long offset, String order, String fields, String segments, String leaderboardId, String userId, long limit ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("groups",groups);
		array.put("offset",offset);
		array.put("order",order);
		array.put("fields",fields);
		array.put("segments",segments);
		array.put("leaderboardId",leaderboardId);
		array.put("userId",userId);
		array.put("limit",limit);
		
	
		return this.callService( "/cache/leaderboards/{leaderboardId}/users/{userId}/friends", "get",  array, null);
		}

			/**
* Accessible at url : /cache/leaderboards/{leaderboardId}/segments/{segmentId}
*  Get user's with a segment Id in one of the leaderboards.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {
 *   "$schema":"http://json-schema.org/draft-03/schema",
 *   "description":"A representation of a user",
 *   "type":"object",
 *   "properties":{
 *      "lb":{
 *         "type":"array",
 *         "items":{
 *            "type":"object",
 *            "properties":{
 *               "sc":{
 *                  "type":"integer",
 *                  "required":true
 *               },
 *               "i":{
 *                  "type":"integer",
 *                  "required":true
 *               },
 *               "id":{
 *                  "type":"integer",
 *                  "required":true
 *               }
 *            }
 *         },
 *         "required":true
 *      }
 *   }
 *}
* groups - The groups we want to narrow down our search to
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* segments - The segments we want to narrow down our search to.
* limit - Starting position
 */ 
public String getOneLeaderboardSegment( String segmentId, String groups, long offset, String order, String fields, String segments, String leaderboardId, long limit ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("segmentId",segmentId);
		array.put("groups",groups);
		array.put("offset",offset);
		array.put("order",order);
		array.put("fields",fields);
		array.put("segments",segments);
		array.put("leaderboardId",leaderboardId);
		array.put("limit",limit);
		
	
		return this.callService( "/cache/leaderboards/{leaderboardId}/segments/{segmentId}", "get",  array, null);
		}

			/**
* Accessible at url : /cache/leaderboards/{leaderboardId}
*  Get users with one of the leaderboards.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {
 *   "$schema":"http://json-schema.org/draft-03/schema",
 *   "description":"A representation of a user",
 *   "type":"object",
 *   "properties":{
 *      "lb":{
 *         "type":"array",
 *         "items":{
 *            "type":"object",
 *            "properties":{
 *               "sc":{
 *                  "type":"integer",
 *                  "required":true
 *               },
 *               "i":{
 *                  "type":"integer",
 *                  "required":true
 *               },
 *               "id":{
 *                  "type":"integer",
 *                  "required":true
 *               }
 *            }
 *         },
 *         "required":true
 *      }
 *   }
 *}
* groups - The groups we want to narrow down our search to
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* segments - The segments we want to narrow down our search to.
* limit - Starting position
 */ 
public String getOneLeaderboard( String groups, long offset, String order, String fields, String segments, String leaderboardId, long limit ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("groups",groups);
		array.put("offset",offset);
		array.put("order",order);
		array.put("fields",fields);
		array.put("segments",segments);
		array.put("leaderboardId",leaderboardId);
		array.put("limit",limit);
		
	
		return this.callService( "/cache/leaderboards/{leaderboardId}", "get",  array, null);
		}

			/**
* Accessible at url : /cache/games/{gameId}
* fields - The fields we want to show in our result
*  Get one game.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {
 *   "$schema":"http://json-schema.org/draft-03/schema",
 *   "description":"A representation of a game",
 *   "type":"object",
 *   "properties":{
 *      "id":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "createdAt":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "updatedAt":{
 *         "required":false,
 *         "type":"integer"
 *      }
 *   }
 *}
 */ 
public String getOneGame( String gameId, String fields ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("gameId",gameId);
		array.put("fields",fields);
		
	
		return this.callService( "/cache/games/{gameId}", "get",  array, null);
		}

			/**
* Accessible at url : /cache/games
*  
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {
 *   "$schema":"http://json-schema.org/draft-03/schema",
 *   "description":"A representation of a game",
 *   "type":"object",
 *   "properties":{
 *      "id":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "createdAt":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "updatedAt":{
 *         "required":false,
 *         "type":"integer"
 *      }
 *   }
 *}
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* segments - The segments we want to narrow down our search to.
* limit - Starting position
 */ 
public String getGames( long offset, String order, String fields, String segments, long limit ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("offset",offset);
		array.put("order",order);
		array.put("fields",fields);
		array.put("segments",segments);
		array.put("limit",limit);
		
	
		return this.callService( "/cache/games", "get",  array, null);
		}

			/**
* Accessible at url : /cache/achievements/{achievementId}
* fields - The fields we want to show in our result
*  Get one achievement.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {
 *   "$schema":"http://json-schema.org/draft-03/schema",
 *   "description":"A representation of an achievement",
 *   "type":"object",
 *   "properties":{
 *      "id":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "createdAt":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "updatedAt":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "name":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "description":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "segments":{
 *         "type":"array",
 *         "items":{
 *            "type":"integer"
 *         },
 *         "required":false
 *      }
 *   }
 *}
 */ 
public String getOneAchievement( String achievementId, String fields ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("achievementId",achievementId);
		array.put("fields",fields);
		
	
		return this.callService( "/cache/achievements/{achievementId}", "get",  array, null);
		}

			/**
* Accessible at url : /cache/achievements
*  Get achievements collection.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {
 *   "$schema":"http://json-schema.org/draft-03/schema",
 *   "description":"A representation of a list of achievements",
 *   "type":"array",
 *   "items":{
 *      "type":"object",
 *      "required":false,
 *      "properties":{
 *         "id":{
 *            "required":false,
 *            "type":"integer"
 *         },
 *         "createdAt":{
 *            "required":false,
 *            "type":"integer"
 *         },
 *         "updatedAt":{
 *            "required":false,
 *            "type":"integer"
 *         },
 *         "name":{
 *            "required":false,
 *            "type":"string"
 *         },
 *         "description":{
 *            "required":false,
 *            "type":"string"
 *         },
 *         "segments":{
 *            "type":"array",
 *            "items":{
 *               "type":"integer"
 *            },
 *            "required":false
 *         }
 *      }
 *   }
 *}
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* segments - The segments we want to narrow down our search to.
* limit - Starting position
 */ 
public String getAchievements( long offset, String order, String fields, String segments, long limit ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("offset",offset);
		array.put("order",order);
		array.put("fields",fields);
		array.put("segments",segments);
		array.put("limit",limit);
		
	
		return this.callService( "/cache/achievements", "get",  array, null);
		}

			/**
* Accessible at url : /cache/users/groups/{groupId}
* fields - The fields we want to show in our result
*  Get one group.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {
 *   "$schema":"http://json-schema.org/draft-03/schema",
 *   "description":"A representation of a segment",
 *   "type":"object",
 *   "properties":{
 *      "id":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "createdAt":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "updatedAt":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "name":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "description":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "status":{
 *         "required":false,
 *         "type":"string"
 *      }
 *   }
 *}
 */ 
public String getOneGroup( String groupId, String fields ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("groupId",groupId);
		array.put("fields",fields);
		
	
		return this.callService( "/cache/users/groups/{groupId}", "get",  array, null);
		}

			/**
* Accessible at url : /cache/users/groups
*  Get groups collection.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {
 *   "$schema":"http://json-schema.org/draft-03/schema",
 *   "description":"A representation of a segment",
 *   "type":"object",
 *   "properties":{
 *      "id":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "createdAt":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "updatedAt":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "name":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "description":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "status":{
 *         "required":false,
 *         "type":"string"
 *      }
 *   }
 *}
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* limit - Starting position
 */ 
public String getGroups( long offset, long limit, String fields, String order ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("offset",offset);
		array.put("limit",limit);
		array.put("fields",fields);
		array.put("order",order);
		
	
		return this.callService( "/cache/users/groups", "get",  array, null);
		}

			/**
* Accessible at url : /cache/users/segments
*  Get users' segment list.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {
 *   "$schema":"http://json-schema.org/draft-03/schema",
 *   "description":"A representation of a segment",
 *   "type":"object",
 *   "properties":{
 *      "id":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "createdAt":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "updatedAt":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "name":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "description":{
 *         "required":false,
 *         "type":"string"
 *      }
 *   }
 *}
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* limit - Starting position
 */ 
public String getUsersSegments( String fields, String order, long offset, long limit ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("fields",fields);
		array.put("order",order);
		array.put("offset",offset);
		array.put("limit",limit);
		
	
		return this.callService( "/cache/users/segments", "get",  array, null);
		}

			/**
* Accessible at url : /cache/users/{userId}/achievements
*  Get one user's achievements.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {
 *   "$schema":"http://json-schema.org/draft-03/schema",
 *   "description":"A representation of an achievement",
 *   "type":"object",
 *   "properties":{
 *      "id":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "createdAt":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "updatedAt":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "name":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "description":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "segments":{
 *         "type":"array",
 *         "items":{
 *            "type":"integer"
 *         },
 *         "required":false
 *      }
 *   }
 *}
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* segments - The segments we want to narrow down our search to.
* limit - Starting position
 */ 
public String getUserAchievements( long offset, String order, String fields, String segments, String userId, long limit ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("offset",offset);
		array.put("order",order);
		array.put("fields",fields);
		array.put("segments",segments);
		array.put("userId",userId);
		array.put("limit",limit);
		
	
		return this.callService( "/cache/users/{userId}/achievements", "get",  array, null);
		}

			/**
* Accessible at url : /cache/users/{userId}/groups
*  Get one user's segments.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {
 *   "$schema":"http://json-schema.org/draft-03/schema",
 *   "description":"A representation of a segment",
 *   "type":"object",
 *   "properties":{
 *      "id":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "createdAt":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "updatedAt":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "name":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "description":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "status":{
 *         "required":false,
 *         "type":"string"
 *      }
 *   }
 *}
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* limit - Starting position
 */ 
public String getUserGroups( long offset, String order, String fields, String userId, long limit ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("offset",offset);
		array.put("order",order);
		array.put("fields",fields);
		array.put("userId",userId);
		array.put("limit",limit);
		
	
		return this.callService( "/cache/users/{userId}/groups", "get",  array, null);
		}

			/**
* Accessible at url : /cache/users/{userId}/segments
*  Get one user's segments.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {
 *   "$schema":"http://json-schema.org/draft-03/schema",
 *   "description":"A representation of a segment",
 *   "type":"object",
 *   "properties":{
 *      "id":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "createdAt":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "updatedAt":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "name":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "description":{
 *         "required":false,
 *         "type":"string"
 *      }
 *   }
 *}
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* limit - Starting position
 */ 
public String getUserSegments( long offset, String order, String fields, String userId, long limit ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("offset",offset);
		array.put("order",order);
		array.put("fields",fields);
		array.put("userId",userId);
		array.put("limit",limit);
		
	
		return this.callService( "/cache/users/{userId}/segments", "get",  array, null);
		}

			/**
* Accessible at url : /cache/users/{userId}/friends
*  Get one user's friends.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {
 *   "$schema":"http://json-schema.org/draft-03/schema",
 *   "description":"A representation of a user",
 *   "type":"object",
 *   "properties":{
 *      "id":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "level":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "email":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "password":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "firstName":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "lastName":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "gender":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "birthDate":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "status":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "role":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "friends":{
 *         "type":"array",
 *         "items":{
 *            "type":"integer"
 *         },
 *         "required":false
 *      },
 *      "segments":{
 *         "type":"array",
 *         "items":{
 *            "type":"integer"
 *         },
 *         "required":false
 *      },
 *      "groups":{
 *         "type":"array",
 *         "items":{
 *            "type":"integer"
 *         },
 *         "required":false
 *      },
 *      "externalIds":{
 *         "type":"array",
 *         "items":{
 *            "type":"integer"
 *         },
 *         "required":false
 *      },
 *      "gainedAchievements":{
 *         "required":false,
 *         "type":"array",
 *         "items":{
 *            "type":"object",
 *            "properties":{
 *               "aid":"integer",
 *               "st":"integer",
 *               "o":"integer",
 *               "amount":"integer",
 *               "cat":"integer",
 *               "uat":"integer"
 *            }
 *         }
 *      },
 *      "wonGames":{
 *         "required":false,
 *         "type":"array",
 *         "items":{
 *            "type":"object",
 *            "properties":{
 *               "gid":"integer",
 *               "v":"integer",
 *               "cat":"integer",
 *               "uat":"integer"
 *            }
 *         }
 *      },
 *      "counterValues":{
 *         "required":false,
 *         "type":"array",
 *         "items":{
 *            "type":"object",
 *            "properties":{
 *               "cid":"integer",
 *               "v":"integer",
 *               "cat":"integer",
 *               "uat":"integer"
 *            }
 *         }
 *      },
 *      "createdAt":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "updatedAt":{
 *         "required":false,
 *         "type":"integer"
 *      }
 *   }
 *}
* groups - The groups we want to narrow down our search to
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* segments - The segments we want to narrow down our search to.
* limit - Starting position
 */ 
public String getUserFriends( String groups, long offset, String order, String fields, String segments, String userId, long limit ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("groups",groups);
		array.put("offset",offset);
		array.put("order",order);
		array.put("fields",fields);
		array.put("segments",segments);
		array.put("userId",userId);
		array.put("limit",limit);
		
	
		return this.callService( "/cache/users/{userId}/friends", "get",  array, null);
		}

			/**
* Accessible at url : /cache/users/{userId}/history
*  Get one user's history.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {
 *   "$schema":"http://json-schema.org/draft-03/schema",
 *   "description":"A representation of an achievement",
 *   "type":"object",
 *   "properties":{
 *      "id":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "createdAt":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "updatedAt":{
 *         "required":false,
 *         "type":"integer"
 *      }
 *   }
 *}
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* limit - Starting position
 */ 
public String getUserHistory( long offset, String order, String fields, String userId, long limit ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("offset",offset);
		array.put("order",order);
		array.put("fields",fields);
		array.put("userId",userId);
		array.put("limit",limit);
		
	
		return this.callService( "/cache/users/{userId}/history", "get",  array, null);
		}

			/**
* Accessible at url : /cache/users/{userId}
* fields - The fields we want to show in our result
*  Get one user by id.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {
 *   "$schema":"http://json-schema.org/draft-03/schema",
 *   "description":"A representation of a user",
 *   "type":"object",
 *   "properties":{
 *      "id":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "level":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "email":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "password":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "firstName":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "lastName":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "gender":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "birthDate":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "status":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "role":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "friends":{
 *         "type":"array",
 *         "items":{
 *            "type":"integer"
 *         },
 *         "required":false
 *      },
 *      "segments":{
 *         "type":"array",
 *         "items":{
 *            "type":"integer"
 *         },
 *         "required":false
 *      },
 *      "groups":{
 *         "type":"array",
 *         "items":{
 *            "type":"integer"
 *         },
 *         "required":false
 *      },
 *      "externalIds":{
 *         "type":"array",
 *         "items":{
 *            "type":"integer"
 *         },
 *         "required":false
 *      },
 *      "gainedAchievements":{
 *         "required":false,
 *         "type":"array",
 *         "items":{
 *            "type":"object",
 *            "properties":{
 *               "aid":"integer",
 *               "st":"integer",
 *               "o":"integer",
 *               "amount":"integer",
 *               "cat":"integer",
 *               "uat":"integer"
 *            }
 *         }
 *      },
 *      "wonGames":{
 *         "required":false,
 *         "type":"array",
 *         "items":{
 *            "type":"object",
 *            "properties":{
 *               "gid":"integer",
 *               "v":"integer",
 *               "cat":"integer",
 *               "uat":"integer"
 *            }
 *         }
 *      },
 *      "counterValues":{
 *         "required":false,
 *         "type":"array",
 *         "items":{
 *            "type":"object",
 *            "properties":{
 *               "cid":"integer",
 *               "v":"integer",
 *               "cat":"integer",
 *               "uat":"integer"
 *            }
 *         }
 *      },
 *      "createdAt":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "updatedAt":{
 *         "required":false,
 *         "type":"integer"
 *      }
 *   }
 *}
 */ 
public String getOneUser( String userId, String fields ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("userId",userId);
		array.put("fields",fields);
		
	
		return this.callService( "/cache/users/{userId}", "get",  array, null);
		}

			/**
* Accessible at url : /cache/users
*  Get users collection.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {
 *   "$schema":"http://json-schema.org/draft-03/schema",
 *   "description":"A representation of a user",
 *   "type":"object",
 *   "properties":{
 *      "id":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "level":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "email":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "password":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "firstName":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "lastName":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "gender":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "birthDate":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "status":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "role":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "friends":{
 *         "type":"array",
 *         "items":{
 *            "type":"integer"
 *         },
 *         "required":false
 *      },
 *      "segments":{
 *         "type":"array",
 *         "items":{
 *            "type":"integer"
 *         },
 *         "required":false
 *      },
 *      "groups":{
 *         "type":"array",
 *         "items":{
 *            "type":"integer"
 *         },
 *         "required":false
 *      },
 *      "externalIds":{
 *         "type":"array",
 *         "items":{
 *            "type":"integer"
 *         },
 *         "required":false
 *      },
 *      "gainedAchievements":{
 *         "required":false,
 *         "type":"array",
 *         "items":{
 *            "type":"object",
 *            "properties":{
 *               "aid":"integer",
 *               "st":"integer",
 *               "o":"integer",
 *               "amount":"integer",
 *               "cat":"integer",
 *               "uat":"integer"
 *            }
 *         }
 *      },
 *      "wonGames":{
 *         "required":false,
 *         "type":"array",
 *         "items":{
 *            "type":"object",
 *            "properties":{
 *               "gid":"integer",
 *               "v":"integer",
 *               "cat":"integer",
 *               "uat":"integer"
 *            }
 *         }
 *      },
 *      "counterValues":{
 *         "required":false,
 *         "type":"array",
 *         "items":{
 *            "type":"object",
 *            "properties":{
 *               "cid":"integer",
 *               "v":"integer",
 *               "cat":"integer",
 *               "uat":"integer"
 *            }
 *         }
 *      },
 *      "createdAt":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "updatedAt":{
 *         "required":false,
 *         "type":"integer"
 *      }
 *   }
 *}
* groups - The groups we want to narrow down our search to
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* segments - The segments we want to narrow down our search to.
* limit - Starting position
 */ 
public String getUsers( String groups, long offset, String order, String fields, String segments, long limit ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("groups",groups);
		array.put("offset",offset);
		array.put("order",order);
		array.put("fields",fields);
		array.put("segments",segments);
		array.put("limit",limit);
		
	
		return this.callService( "/cache/users", "get",  array, null);
		}

			/**
* Accessible at url : /cache/segments/{segmentId}
* fields - The fields we want to show in our result
*  Get segment by id.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {
 *   "$schema":"http://json-schema.org/draft-03/schema",
 *   "description":"A representation of a segment",
 *   "type":"object",
 *   "properties":{
 *      "id":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "createdAt":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "updatedAt":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "name":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "description":{
 *         "required":false,
 *         "type":"string"
 *      }
 *   }
 *}
 */ 
public String getOneSegment( String segmentId, String fields ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("segmentId",segmentId);
		array.put("fields",fields);
		
	
		return this.callService( "/cache/segments/{segmentId}", "get",  array, null);
		}

			/**
* Accessible at url : /cache/segments
*  Get segments list.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {
 *   "$schema":"http://json-schema.org/draft-03/schema",
 *   "description":"A representation of a segment",
 *   "type":"object",
 *   "properties":{
 *      "id":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "createdAt":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "updatedAt":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "name":{
 *         "required":false,
 *         "type":"string"
 *      },
 *      "description":{
 *         "required":false,
 *         "type":"string"
 *      }
 *   }
 *}
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* limit - Starting position
 */ 
public String getSegments( long offset, long limit, String fields, String order ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("offset",offset);
		array.put("limit",limit);
		array.put("fields",fields);
		array.put("order",order);
		
	
		return this.callService( "/cache/segments", "get",  array, null);
		}

			/**
* Accessible at url : /cache/events/{eventId}
* fields - The fields we want to show in our result
*  Get one done event.
* @return 200 : application/json : {
 *   "$schema":"http://json-schema.org/draft-03/schema",
 *   "properties":{
 *      "body":{
 *         "type":"object",
 *         "required":false
 *      },
 *      "subjectId":{
 *         "type":"integer",
 *         "required":false
 *      },
 *      "subjectType":{
 *         "type":"string",
 *         "required":false
 *      },
 *      "sourceId":{
 *         "type":"integer",
 *         "required":false
 *      },
 *      "priority":{
 *         "type":"string",
 *         "required":false
 *      },
 *      "type":{
 *         "type":"string",
 *         "required":false
 *      },
 *      "clientId":{
 *         "type":"integer",
 *         "required":false
 *      },
 *      "status":{
 *         "type":"string",
 *         "required":false
 *      },
 *      "ttl":{
 *         "type":"integer",
 *         "required":false
 *      },
 *      "createdAt":{
 *         "type":"integer",
 *         "required":false
 *      },
 *      "updatedAt":{
 *         "type":"integer",
 *         "required":false
 *      },
 *      "actionTime":{
 *         "type":"integer",
 *         "required":false
 *      },
 *      "data":{
 *         "type":"object",
 *         "required":false
 *      }
 *   }
 *}
 */ 
public String getOneEvent( String eventId, String fields ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("eventId",eventId);
		array.put("fields",fields);
		
	
		return this.callService( "/cache/events/{eventId}", "get",  array, null);
		}

			/**
* Accessible at url : /cache/events
*  Get done events collection.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {
 *   "$schema":"http://json-schema.org/draft-03/schema",
 *   "properties":{
 *      "body":{
 *         "type":"object",
 *         "required":false
 *      },
 *      "subjectId":{
 *         "type":"integer",
 *         "required":false
 *      },
 *      "subjectType":{
 *         "type":"string",
 *         "required":false
 *      },
 *      "sourceId":{
 *         "type":"integer",
 *         "required":false
 *      },
 *      "priority":{
 *         "type":"string",
 *         "required":false
 *      },
 *      "type":{
 *         "type":"string",
 *         "required":false
 *      },
 *      "clientId":{
 *         "type":"integer",
 *         "required":false
 *      },
 *      "status":{
 *         "type":"string",
 *         "required":false
 *      },
 *      "ttl":{
 *         "type":"integer",
 *         "required":false
 *      },
 *      "createdAt":{
 *         "type":"integer",
 *         "required":false
 *      },
 *      "updatedAt":{
 *         "type":"integer",
 *         "required":false
 *      },
 *      "actionTime":{
 *         "type":"integer",
 *         "required":false
 *      },
 *      "data":{
 *         "type":"object",
 *         "required":false
 *      }
 *   }
 *}
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* limit - Starting position
 */ 
public String getEvents( long offset, long limit, String fields, String order ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("offset",offset);
		array.put("limit",limit);
		array.put("fields",fields);
		array.put("order",order);
		
	
		return this.callService( "/cache/events", "get",  array, null);
		}

			/**
* Accessible at url : /cache/history
*  Get history list.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {
 *   "$schema":"http://json-schema.org/draft-03/schema",
 *   "description":"A representation of an achievement",
 *   "type":"object",
 *   "properties":{
 *      "id":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "createdAt":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "updatedAt":{
 *         "required":false,
 *         "type":"integer"
 *      }
 *   }
 *}
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* limit - Starting position
 */ 
public String getHistory( long offset, long limit, String fields, String order ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("offset",offset);
		array.put("limit",limit);
		array.put("fields",fields);
		array.put("order",order);
		
	
		return this.callService( "/cache/history", "get",  array, null);
		}

	 
 } 