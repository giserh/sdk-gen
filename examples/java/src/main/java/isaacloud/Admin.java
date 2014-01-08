
package isaacloud;
import java.util.Map;
import java.util.HashMap;

/**
 */ 
public class Admin extends Connector{

	public Admin(Map<String,String> config)
    {
        super( "http://api.isaacloud.com", "http://oauth.isaacloud.com", "0.1", config);
    }

			/**
* Accessible at url : /admin/functions/doc
*  Get a pseudo schema showing quickly how to make a proper json for functions.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String getFunctionsDoc(  ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	
	
		return this.callService( "/admin/functions/doc", "get",  array, null);
		}

			/**
* Accessible at url : /admin/functions/{id}
*  Delete a function.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteOneFunction( String id ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
		return this.callService( "/admin/functions/{id}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/functions/{id}
*  Add a function.
* body application/json schema : 
*{}
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putOneFunction( String id, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
  		return this.callService( "/admin/functions/{id}", "put",  array , body);
		}

			/**
* Accessible at url : /admin/functions/{id}
* fields - The fields we want to show in our result
*  Get one function.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
 */ 
public String getOneFunction( String id, String fields ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("fields",fields);
		
	
		return this.callService( "/admin/functions/{id}", "get",  array, null);
		}

			/**
* Accessible at url : /admin/functions
*  Add a new function.
* body application/json schema : 
*{}
* @return 400 : 
* OR 500 : 
* OR 200 : 
 */ 
public String postFunctions( String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	
	
  		return this.callService( "/admin/functions", "post",  array , body);
		}

			/**
* Accessible at url : /admin/functions
*  Get a list of functions.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* limit - Starting position
 */ 
public String getFunctions( String fields, long offset, long limit, String order ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("fields",fields);
		array.put("offset",offset);
		array.put("limit",limit);
		array.put("order",order);
		
	
		return this.callService( "/admin/functions", "get",  array, null);
		}

			/**
* Accessible at url : /admin/notifications/doc
*  Get a pseudo schema showing quickly how to make a proper json for notifications.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String getNotificationsDocs(  ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	
	
		return this.callService( "/admin/notifications/doc", "get",  array, null);
		}

			/**
* Accessible at url : /admin/notifications/types/doc
*  Get a pseudo schema showing quickly how to make a proper json for notification types.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String getNotificationTypesDocs(  ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	
	
		return this.callService( "/admin/notifications/types/doc", "get",  array, null);
		}

			/**
* Accessible at url : /admin/notifications/types/{id}
*  Delete a notification type.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteOneNotificationType( String id ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
		return this.callService( "/admin/notifications/types/{id}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/notifications/types/{id}
*  Update a notification type
* body application/json schema : 
*{}
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putOneNotificationType( String id, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
  		return this.callService( "/admin/notifications/types/{id}", "put",  array , body);
		}

			/**
* Accessible at url : /admin/notifications/types/{id}
* fields - The fields we want to show in our result
*  Get a notification type.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
 */ 
public String getOneNotificationType( String id, String fields ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("fields",fields);
		
	
		return this.callService( "/admin/notifications/types/{id}", "get",  array, null);
		}

			/**
* Accessible at url : /admin/notifications/types
*  Add a notification type.
* body application/json schema : 
*{}
* @return 400 : 
* OR 500 : 
* OR 200 : 
 */ 
public String postNotificationTypes( String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	
	
  		return this.callService( "/admin/notifications/types", "post",  array , body);
		}

			/**
* Accessible at url : /admin/notifications/types
*  Get a list of notification types.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* limit - Starting position
 */ 
public String getNotificationTypes( String fields, long offset, long limit, String order ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("fields",fields);
		array.put("offset",offset);
		array.put("limit",limit);
		array.put("order",order);
		
	
		return this.callService( "/admin/notifications/types", "get",  array, null);
		}

			/**
* Accessible at url : /admin/notifications/{id}
*  Delete a notification.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteOneNotification( String id ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
		return this.callService( "/admin/notifications/{id}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/notifications/{id}
*  Update a notification.
* body application/json schema : 
*{}
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putOneNotification( String id, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
  		return this.callService( "/admin/notifications/{id}", "put",  array , body);
		}

			/**
* Accessible at url : /admin/notifications/{id}
* fields - The fields we want to show in our result
*  Get one notification.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
 */ 
public String getOneNotification( String id, String fields ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("fields",fields);
		
	
		return this.callService( "/admin/notifications/{id}", "get",  array, null);
		}

			/**
* Accessible at url : /admin/notifications
*  Add a notification.
* body application/json schema : 
*{}
* @return 400 : 
* OR 500 : 
* OR 200 : 
 */ 
public String postNotifications( String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	
	
  		return this.callService( "/admin/notifications", "post",  array , body);
		}

			/**
* Accessible at url : /admin/notifications
*  Get a list of notifications.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* limit - Starting position
 */ 
public String getNotifications( String fields, long offset, long limit, String order ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("fields",fields);
		array.put("offset",offset);
		array.put("limit",limit);
		array.put("order",order);
		
	
		return this.callService( "/admin/notifications", "get",  array, null);
		}

			/**
* Accessible at url : /admin/transactiontypes/doc
*  Get a pseudo schema showing quickly how to make a proper json for transaction types.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String getTransactionTypesDocs(  ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	
	
		return this.callService( "/admin/transactiontypes/doc", "get",  array, null);
		}

			/**
* Accessible at url : /admin/transactiontypes/{id}/conditions/{childId}
*  Delete a condition for transaction type.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteOneTransactionTypesCondition( String id, String childId ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
		return this.callService( "/admin/transactiontypes/{id}/conditions/{childId}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/transactiontypes/{id}/conditions/{childId}
*  Update a condition for transaction type.
* body application/json schema : 
*{}
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putOneTransactionTypesCondition( String id, String childId, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
  		return this.callService( "/admin/transactiontypes/{id}/conditions/{childId}", "put",  array , body);
		}

			/**
* Accessible at url : /admin/transactiontypes/{id}
*  Remove a transaction type.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteOneTransactionType( String id ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
		return this.callService( "/admin/transactiontypes/{id}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/transactiontypes/{id}
*  Update a transaction type.
* body application/json schema : 
*{}
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putOneTransactionType( String id, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
  		return this.callService( "/admin/transactiontypes/{id}", "put",  array , body);
		}

			/**
* Accessible at url : /admin/transactiontypes/{id}
* fields - The fields we want to show in our result
*  Get a transaction type.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
 */ 
public String getOneTransactionType( String id, String fields ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("fields",fields);
		
	
		return this.callService( "/admin/transactiontypes/{id}", "get",  array, null);
		}

			/**
* Accessible at url : /admin/transactiontypes
*  Add a transaction type.
* body application/json schema : 
*{}
* @return 400 : 
* OR 500 : 
* OR 200 : 
 */ 
public String postTransactionTypes( String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	
	
  		return this.callService( "/admin/transactiontypes", "post",  array , body);
		}

			/**
* Accessible at url : /admin/transactiontypes
*  Get a list of transaction types.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* limit - Starting position
 */ 
public String getTransactionTypes( String fields, long offset, long limit, String order ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("fields",fields);
		array.put("offset",offset);
		array.put("limit",limit);
		array.put("order",order);
		
	
		return this.callService( "/admin/transactiontypes", "get",  array, null);
		}

			/**
* Accessible at url : /admin/achievements/groups/doc
*  Get a pseudo schema for group.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String getAchievementsGroupsDoc(  ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	
	
		return this.callService( "/admin/achievements/groups/doc", "get",  array, null);
		}

			/**
* Accessible at url : /admin/achievements/groups/{id}/achievements/{childId}
*  Delete one achievement from achievements group.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteOneGroupAchievement( String id, String childId ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
		return this.callService( "/admin/achievements/groups/{id}/achievements/{childId}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/achievements/groups/{id}/achievements/{childId}
*  Add one achievement to achievements group.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putOneGroupAchievement( String id, String childId ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
		return this.callService( "/admin/achievements/groups/{id}/achievements/{childId}", "put",  array, null);
		}

			/**
* Accessible at url : /admin/achievements/groups/{id}
*  Delete an achievement group.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteOneAchievementsGroup( String id ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
		return this.callService( "/admin/achievements/groups/{id}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/achievements/groups/{id}
*  Update an achievement group.
* body application/json schema : 
*{
*   "$schema":"http://json-schema.org/draft-03/schema",
*   "description":"A representation of an achievement group",
*   "type":"object",
*   "properties":{
*      "name":{
*         "required":false,
*         "type":"integer"
*      },
*      "members":{
*         "required":false,
*         "type":"array",
*         "items":{
*            "type":"integer"
*         }
*      }
*   }
*}
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putOneAchievementsGroup( String id, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
  		return this.callService( "/admin/achievements/groups/{id}", "put",  array , body);
		}

			/**
* Accessible at url : /admin/achievements/groups/{id}
* fields - The fields we want to show in our result
*  Get one achievement group.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {
 *   "$schema":"http://json-schema.org/draft-03/schema",
 *   "description":"A representation of a list of achievement groups.",
 *   "type" : "array",
 *   "items" :{
 *      "type":"object",
 *      "required":false,
 *      "properties":{
 *         "name":{
 *            "required":false,
 *            "type":"integer"
 *         },
 *         "members":{
 *            "required":false,
 *            "type":"array",
 *            "items":{
 *               "type":"integer"
 *            }
 *         }
 *      }
 *   }
 *}
 */ 
public String getOneAchievementsGroup( String id, String fields ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("fields",fields);
		
	
		return this.callService( "/admin/achievements/groups/{id}", "get",  array, null);
		}

			/**
* Accessible at url : /admin/achievements/groups
*  Create new achievement group.
* body application/json schema : 
*{
*   "$schema":"http://json-schema.org/draft-03/schema",
*   "description":"A representation of an achievement group",
*   "type":"object",
*   "properties":{
*      "name":{
*         "required":false,
*         "type":"integer"
*      },
*      "members":{
*         "required":false,
*         "type":"array",
*         "items":{
*            "type":"integer"
*         }
*      }
*   }
*}
* @return 400 : 
* OR 500 : 
* OR 200 : 
 */ 
public String postAchievementsGroups( String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	
	
  		return this.callService( "/admin/achievements/groups", "post",  array , body);
		}

			/**
* Accessible at url : /admin/achievements/groups
*  Get a list achievement groups.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {
 *   "$schema":"http://json-schema.org/draft-03/schema",
 *   "description":"A representation of a list of achievement groups.",
 *   "type" : "array",
 *   "items" :{
 *      "type":"object",
 *      "required":false,
 *      "properties":{
 *         "name":{
 *            "required":false,
 *            "type":"integer"
 *         },
 *         "members":{
 *            "required":false,
 *            "type":"array",
 *            "items":{
 *               "type":"integer"
 *            }
 *         }
 *      }
 *   }
 *}
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* limit - Starting position
 */ 
public String getAchievementsGroups( String fields, String order, long offset, long limit ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("fields",fields);
		array.put("order",order);
		array.put("offset",offset);
		array.put("limit",limit);
		
	
		return this.callService( "/admin/achievements/groups", "get",  array, null);
		}

			/**
* Accessible at url : /admin/achievements/progress/doc
*  Get a pseudo schema for progress.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String getAchievementsProgressesDoc(  ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	
	
		return this.callService( "/admin/achievements/progress/doc", "get",  array, null);
		}

			/**
* Accessible at url : /admin/achievements/progress/{id}
*  Delete an achievement progress.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteOneAchievementsProgress( String id ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
		return this.callService( "/admin/achievements/progress/{id}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/achievements/progress/{id}
*  Update an achievement progress.
* body application/json schema : 
*{
*   "$schema":"http://json-schema.org/draft-03/schema",
*   "description":"A representation of a segment",
*   "type":"object",
*   "properties":{
*      "start":{
*         "required":false,
*         "type":"integer"
*      },
*      "stop":{
*         "required":false,
*         "type":"integer"
*      },
*      "achievement":{
*         "required":false,
*         "type":"integer"
*      },
*      "counter":{
*         "required":false,
*         "type":"integer"
*      }
*   }
*}
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putOneAchievementsProgress( String id, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
  		return this.callService( "/admin/achievements/progress/{id}", "put",  array , body);
		}

			/**
* Accessible at url : /admin/achievements/progress/{id}
* fields - The fields we want to show in our result
*  Get one achievement progress.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {
 *   "$schema":"http://json-schema.org/draft-03/schema",
 *   "description":"A representation of a segment",
 *   "type":"array",
 *   "items":{
 *      "type":"object",
 *      "required":false,
 *      "properties":{
 *         "start":{
 *            "required":false,
 *            "type":"integer"
 *         },
 *         "stop":{
 *            "required":false,
 *            "type":"integer"
 *         },
 *         "achievement":{
 *            "required":false,
 *            "type":"integer"
 *         },
 *         "counter":{
 *            "required":false,
 *            "type":"integer"
 *         }
 *      }
 *   }
 *}
 */ 
public String getOneAchievementsProgress( String id, String fields ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("fields",fields);
		
	
		return this.callService( "/admin/achievements/progress/{id}", "get",  array, null);
		}

			/**
* Accessible at url : /admin/achievements/progress
*  Create new achievement progress.
* body application/json schema : 
*{
*   "$schema":"http://json-schema.org/draft-03/schema",
*   "description":"A representation of a segment",
*   "type":"object",
*   "properties":{
*      "start":{
*         "required":false,
*         "type":"integer"
*      },
*      "stop":{
*         "required":false,
*         "type":"integer"
*      },
*      "achievement":{
*         "required":false,
*         "type":"integer"
*      },
*      "counter":{
*         "required":false,
*         "type":"integer"
*      }
*   }
*}
* @return 400 : 
* OR 500 : 
* OR 200 : 
 */ 
public String postAchievementsProgresses( String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	
	
  		return this.callService( "/admin/achievements/progress", "post",  array , body);
		}

			/**
* Accessible at url : /admin/achievements/progress
*  Get achievement progress list.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {
 *   "$schema":"http://json-schema.org/draft-03/schema",
 *   "description":"A representation of a segment",
 *   "type":"array",
 *   "items":{
 *      "type":"object",
 *      "required":false,
 *      "properties":{
 *         "start":{
 *            "required":false,
 *            "type":"integer"
 *         },
 *         "stop":{
 *            "required":false,
 *            "type":"integer"
 *         },
 *         "achievement":{
 *            "required":false,
 *            "type":"integer"
 *         },
 *         "counter":{
 *            "required":false,
 *            "type":"integer"
 *         }
 *      }
 *   }
 *}
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* limit - Starting position
 */ 
public String getAchievementsProgresses( String fields, String order, long offset, long limit ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("fields",fields);
		array.put("order",order);
		array.put("offset",offset);
		array.put("limit",limit);
		
	
		return this.callService( "/admin/achievements/progress", "get",  array, null);
		}

			/**
* Accessible at url : /admin/achievements/categories/doc
*  Get a pseudo schema for category.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String getAchievementsCategoriesDoc(  ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	
	
		return this.callService( "/admin/achievements/categories/doc", "get",  array, null);
		}

			/**
* Accessible at url : /admin/achievements/categories/{id}/achievements/{childId}
*  Delete one achievement from achievements category.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteOneCategoryAchievement( String id, String childId ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
		return this.callService( "/admin/achievements/categories/{id}/achievements/{childId}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/achievements/categories/{id}/achievements/{childId}
*  Add one achievement to achievements category.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putOneCategoryAchievement( String id, String childId ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
		return this.callService( "/admin/achievements/categories/{id}/achievements/{childId}", "put",  array, null);
		}

			/**
* Accessible at url : /admin/achievements/categories/{id}
*  Delete one achievements category.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteAchievementsCategory( String id ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
		return this.callService( "/admin/achievements/categories/{id}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/achievements/categories/{id}
*  Update achievement category.
* body application/json schema : 
*{
*   "$schema":"http://json-schema.org/draft-03/schema",
*   "description":"A representation of a category",
*   "type":"object",
*   "properties":{
*      "name":{
*         "required":false,
*         "type":"integer"
*      },
*      "members":{
*         "required":false,
*         "type":"array",
*         "items":{
*            "type":"integer"
*         }
*      }
*   }
*}
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putAchievementsCategory( String id, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
  		return this.callService( "/admin/achievements/categories/{id}", "put",  array , body);
		}

			/**
* Accessible at url : /admin/achievements/categories/{id}
*  Get one category.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {
 *   "$schema":"http://json-schema.org/draft-03/schema",
 *   "description":"A representation of a category",
 *   "type":"object",
 *   "properties":{
 *      "name":{
 *         "required":false,
 *         "type":"integer"
 *      },
 *      "members":{
 *         "required":false,
 *         "type":"array",
 *         "items":{
 *            "type":"integer"
 *         }
 *      }
 *   }
 *}
 */ 
public String getAchievementsCategory( String id ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
		return this.callService( "/admin/achievements/categories/{id}", "get",  array, null);
		}

			/**
* Accessible at url : /admin/achievements/categories
*  Create new category.
* body application/json schema : 
*{
*   "$schema":"http://json-schema.org/draft-03/schema",
*   "description":"A representation of a category",
*   "type":"object",
*   "properties":{
*      "name":{
*         "required":false,
*         "type":"integer"
*      },
*      "members":{
*         "required":false,
*         "type":"array",
*         "items":{
*            "type":"integer"
*         }
*      }
*   }
*}
* @return 400 : 
* OR 500 : 
* OR 200 : 
 */ 
public String postAchievementsCategories( String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	
	
  		return this.callService( "/admin/achievements/categories", "post",  array , body);
		}

			/**
* Accessible at url : /admin/achievements/categories
*  Get list of categories.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {
 *   "$schema":"http://json-schema.org/draft-03/schema",
 *   "description":"A representation of a category",
 *   "type" : "array",
 *   "items" :{
 *      "type":"object",
 *      "required":false,
 *      "properties":{
 *         "name":{
 *            "required":false,
 *            "type":"integer"
 *         },
 *         "members":{
 *            "required":false,
 *            "type":"array",
 *            "items":{
 *               "type":"integer"
 *            }
 *         }
 *      }
 *   }
 *}
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* limit - Starting position
 */ 
public String getAchievementsCategories( String fields, String order, long offset, long limit ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("fields",fields);
		array.put("order",order);
		array.put("offset",offset);
		array.put("limit",limit);
		
	
		return this.callService( "/admin/achievements/categories", "get",  array, null);
		}

			/**
* Accessible at url : /admin/achievements/{achievementId}/segments/{childId}
*  Delete segment from achievement.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteOneAchievementSegment( String achievementId, String childId ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("achievementId",achievementId);
		array.put("childId",childId);
		
	
		return this.callService( "/admin/achievements/{achievementId}/segments/{childId}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/achievements/{achievementId}/segments/{childId}
*  Add segment to achievement.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putOneAchievementSegment( String achievementId, String childId ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("achievementId",achievementId);
		array.put("childId",childId);
		
	
		return this.callService( "/admin/achievements/{achievementId}/segments/{childId}", "put",  array, null);
		}

			/**
* Accessible at url : /admin/achievements/{achievementId}
*  Delete one achievement.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteOneAchievement( String achievementId ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("achievementId",achievementId);
		
	
		return this.callService( "/admin/achievements/{achievementId}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/achievements/{achievementId}
*  Update one achievement.
* body application/json schema : 
*{
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
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putOneAchievement( String achievementId, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("achievementId",achievementId);
		
	
  		return this.callService( "/admin/achievements/{achievementId}", "put",  array , body);
		}

			/**
* Accessible at url : /admin/achievements/{achievementId}
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
		
	
		return this.callService( "/admin/achievements/{achievementId}", "get",  array, null);
		}

			/**
* Accessible at url : /admin/achievements
*  Create new achievement.
* body application/json schema : 
*{
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
* @return 400 : 
* OR 500 : 
* OR 200 : 
 */ 
public String postAchievements( String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	
	
  		return this.callService( "/admin/achievements", "post",  array , body);
		}

			/**
* Accessible at url : /admin/achievements
*  Get a list of achievements.
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
* groups - The groups we want to narrow down our search to
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* segments - The segments we want to narrow down our search to.
* limit - Starting position
 */ 
public String getAchievements( String groups, long offset, String order, String fields, String segments, long limit ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("groups",groups);
		array.put("offset",offset);
		array.put("order",order);
		array.put("fields",fields);
		array.put("segments",segments);
		array.put("limit",limit);
		
	
		return this.callService( "/admin/achievements", "get",  array, null);
		}

			/**
* Accessible at url : /admin/games/doc
*  Get a pseudo schema showing quickly how to make a proper json for games.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String getGamesDoc(  ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	
	
		return this.callService( "/admin/games/doc", "get",  array, null);
		}

			/**
* Accessible at url : /admin/games/{id}/notifications/{childId}
*  Remove a notification from a game.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteOneGameNotification( String id, String childId ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
		return this.callService( "/admin/games/{id}/notifications/{childId}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/games/{id}/notifications/{childId}
*  Add a notification to a game.
* body application/json schema : 
*{}
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putOneGameNotification( String id, String childId, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
  		return this.callService( "/admin/games/{id}/notifications/{childId}", "put",  array , body);
		}

			/**
* Accessible at url : /admin/games/{id}/conditions/{childId}
*  Remove a condition from a game.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteOneGameCondition( String id, String childId ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
		return this.callService( "/admin/games/{id}/conditions/{childId}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/games/{id}/conditions/{childId}
*  Add a condition to a game.
* body application/json schema : 
*{}
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putOneGameCondition( String id, String childId, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
  		return this.callService( "/admin/games/{id}/conditions/{childId}", "put",  array , body);
		}

			/**
* Accessible at url : /admin/games/{id}
*  Delete a game.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteOneGame( String id ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
		return this.callService( "/admin/games/{id}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/games/{id}
*  Update a game.
* body application/json schema : 
*{}
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putOneGame( String id, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
  		return this.callService( "/admin/games/{id}", "put",  array , body);
		}

			/**
* Accessible at url : /admin/games/{id}
* fields - The fields we want to show in our result
*  Get one game.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
 */ 
public String getOneGame( String id, String fields ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("fields",fields);
		
	
		return this.callService( "/admin/games/{id}", "get",  array, null);
		}

			/**
* Accessible at url : /admin/games
*  Create a game.
* body application/json schema : 
*{}
* @return 400 : 
* OR 500 : 
* OR 200 : 
 */ 
public String postGames( String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	
	
  		return this.callService( "/admin/games", "post",  array , body);
		}

			/**
* Accessible at url : /admin/games
*  Get a list of games.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* limit - Starting position
 */ 
public String getGames( String fields, long offset, long limit, String order ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("fields",fields);
		array.put("offset",offset);
		array.put("limit",limit);
		array.put("order",order);
		
	
		return this.callService( "/admin/games", "get",  array, null);
		}

			/**
* Accessible at url : /admin/leaderboards/doc
*  Get a pseudo schema showing quickly how to make a proper json for leaderboards.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
 */ 
public String getLeaderboardsDocs(  ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	
	
		return this.callService( "/admin/leaderboards/doc", "get",  array, null);
		}

			/**
* Accessible at url : /admin/leaderboards/{id}/recalculate
*  Recalculate a leaderboard
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
 */ 
public String getOneLeaderboardRecalculate( String id ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
		return this.callService( "/admin/leaderboards/{id}/recalculate", "get",  array, null);
		}

			/**
* Accessible at url : /admin/leaderboards/{id}
*  Remove a leaderboard.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteOneLeaderboard( String id ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
		return this.callService( "/admin/leaderboards/{id}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/leaderboards/{id}
*  Add a leaderboard.
* body application/json schema : 
*{}
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putOneLeaderboard( String id, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
  		return this.callService( "/admin/leaderboards/{id}", "put",  array , body);
		}

			/**
* Accessible at url : /admin/leaderboards/{id}
* fields - The fields we want to show in our result
*  Get a leaderboard.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
 */ 
public String getOneLeaderboard( String id, String fields ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("fields",fields);
		
	
		return this.callService( "/admin/leaderboards/{id}", "get",  array, null);
		}

			/**
* Accessible at url : /admin/leaderboards
*  Add a new leaderboard.
* body application/json schema : 
*{}
* @return 400 : 
* OR 500 : 
* OR 200 : 
 */ 
public String postLeaderboards( String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	
	
  		return this.callService( "/admin/leaderboards", "post",  array , body);
		}

			/**
* Accessible at url : /admin/leaderboards
*  Get a leaderboard list.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* limit - Starting position
 */ 
public String getLeaderboards( String fields, long offset, long limit, String order ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("fields",fields);
		array.put("offset",offset);
		array.put("limit",limit);
		array.put("order",order);
		
	
		return this.callService( "/admin/leaderboards", "get",  array, null);
		}

			/**
* Accessible at url : /admin/transactionsources/doc
*  Get a pseudo schema showing quickly how to make a proper json for transaction source.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String getTransactionSourcesDocs(  ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	
	
		return this.callService( "/admin/transactionsources/doc", "get",  array, null);
		}

			/**
* Accessible at url : /admin/transactionsources/{id}/function/{childId}
*  Delete a transaction source function.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteOneTransactionSourceFunction( String id, String childId ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
		return this.callService( "/admin/transactionsources/{id}/function/{childId}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/transactionsources/{id}/function/{childId}
*  Add a transaction source function.
* body application/json schema : 
*{}
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putOneTransactionSourceFunction( String id, String childId, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
  		return this.callService( "/admin/transactionsources/{id}/function/{childId}", "put",  array , body);
		}

			/**
* Accessible at url : /admin/transactionsources/{id}
*  Delete a transaction source.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteOneTransactionSource( String id ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
		return this.callService( "/admin/transactionsources/{id}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/transactionsources/{id}
*  Update a transaction source.
* body application/json schema : 
*{}
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putOneTransactionSource( String id, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
  		return this.callService( "/admin/transactionsources/{id}", "put",  array , body);
		}

			/**
* Accessible at url : /admin/transactionsources/{id}
* fields - The fields we want to show in our result
*  Get a transaction source.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
 */ 
public String getOneTransactionSource( String id, String fields ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("fields",fields);
		
	
		return this.callService( "/admin/transactionsources/{id}", "get",  array, null);
		}

			/**
* Accessible at url : /admin/transactionsources
*  Add a transaction source.
* body application/json schema : 
*{}
* @return 400 : 
* OR 500 : 
* OR 200 : 
 */ 
public String postTransactionSources( String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	
	
  		return this.callService( "/admin/transactionsources", "post",  array , body);
		}

			/**
* Accessible at url : /admin/transactionsources
*  Get a list of transaction sources.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* limit - Starting position
 */ 
public String getTransactionSources( String fields, long offset, long limit, String order ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("fields",fields);
		array.put("offset",offset);
		array.put("limit",limit);
		array.put("order",order);
		
	
		return this.callService( "/admin/transactionsources", "get",  array, null);
		}

			/**
* Accessible at url : /admin/users/doc
*  Get a pseudo schema showing quickly how to make a proper json for users.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String getUsersDoc(  ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	
	
		return this.callService( "/admin/users/doc", "get",  array, null);
		}

			/**
* Accessible at url : /admin/users/groups/doc
*  Get a pseudo schema showing quickly how to make a proper json for user group.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String getUserGroupsDoc(  ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	
	
		return this.callService( "/admin/users/groups/doc", "get",  array, null);
		}

			/**
* Accessible at url : /admin/users/groups/{id}/gainedachievements/doc
*  Get a pseudo schema showing quickly how to make a proper json for user group gained achivements.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String getUserGroupGainedAchievementDocs( String id ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
		return this.callService( "/admin/users/groups/{id}/gainedachievements/doc", "get",  array, null);
		}

			/**
* Accessible at url : /admin/users/groups/{id}/gainedachievements/{childId}
*  Delete a gained achievement for a group by id.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteOneUserGroupGainedAchievement( String id, String childId ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
		return this.callService( "/admin/users/groups/{id}/gainedachievements/{childId}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/users/groups/{id}/gainedachievements/{childId}
*  Update a gained achievement for a group by id.
* body application/json schema : 
*{}
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putOneUserGroupGainedAchievement( String id, String childId, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
  		return this.callService( "/admin/users/groups/{id}/gainedachievements/{childId}", "put",  array , body);
		}

			/**
* Accessible at url : /admin/users/groups/{id}/gainedachievements/{childId}
*  Get a gained achievement for a group by id.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
 */ 
public String getOneUserGroupGainedAchievement( String id, String childId ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
		return this.callService( "/admin/users/groups/{id}/gainedachievements/{childId}", "get",  array, null);
		}

			/**
* Accessible at url : /admin/users/groups/{id}/gainedachievements
*  Create a gained achievement for a group.
* body application/json schema : 
*{}
* @return 400 : 
* OR 500 : 
* OR 200 : 
 */ 
public String postUserGroupGainedAchievements( String id, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
  		return this.callService( "/admin/users/groups/{id}/gainedachievements", "post",  array , body);
		}

			/**
* Accessible at url : /admin/users/groups/{id}/gainedachievements
*  Get a list of gained achievements for a group.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* limit - Starting position
 */ 
public String getUserGroupGainedAchievements( String id, long offset, String order, String fields, long limit ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("offset",offset);
		array.put("order",order);
		array.put("fields",fields);
		array.put("limit",limit);
		
	
		return this.callService( "/admin/users/groups/{id}/gainedachievements", "get",  array, null);
		}

			/**
* Accessible at url : /admin/users/groups/{id}/users/{childId}
*  Delete a friend from user group.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteOneUserGroupFriend( String id, String childId ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
		return this.callService( "/admin/users/groups/{id}/users/{childId}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/users/groups/{id}/users/{childId}
*  Add a friend to user group.
* body application/json schema : 
*{}
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putOneUserGroupFriend( String id, String childId, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
  		return this.callService( "/admin/users/groups/{id}/users/{childId}", "put",  array , body);
		}

			/**
* Accessible at url : /admin/users/groups/{id}/externalids/doc
*  Get a pseudo schema showing quickly how to make a proper json for user group external ids.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String getUserGroupExternalIdsDocs( String id ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
		return this.callService( "/admin/users/groups/{id}/externalids/doc", "get",  array, null);
		}

			/**
* Accessible at url : /admin/users/groups/{id}/externalids/{childId}
*  Remove an external group id.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteOneUserGroupExternalId( String id, String childId ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
		return this.callService( "/admin/users/groups/{id}/externalids/{childId}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/users/groups/{id}/externalids/{childId}
*  Update an external group id.
* body application/json schema : 
*{}
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putOneUserGroupExternalId( String id, String childId, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
  		return this.callService( "/admin/users/groups/{id}/externalids/{childId}", "put",  array , body);
		}

			/**
* Accessible at url : /admin/users/groups/{id}/externalids/{childId}
*  Get an external group id.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
 */ 
public String getOneUserGroupExternalId( String id, String childId ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
		return this.callService( "/admin/users/groups/{id}/externalids/{childId}", "get",  array, null);
		}

			/**
* Accessible at url : /admin/users/groups/{id}/externalids
*  Add an external group id.
* body application/json schema : 
*{}
* @return 400 : 
* OR 500 : 
* OR 200 : 
 */ 
public String postUserGroupExternalIds( String id, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
  		return this.callService( "/admin/users/groups/{id}/externalids", "post",  array , body);
		}

			/**
* Accessible at url : /admin/users/groups/{id}/externalids
*  Get a list of external ids.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* limit - Starting position
 */ 
public String getUserGroupExternalIds( String id, long offset, String order, String fields, long limit ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("offset",offset);
		array.put("order",order);
		array.put("fields",fields);
		array.put("limit",limit);
		
	
		return this.callService( "/admin/users/groups/{id}/externalids", "get",  array, null);
		}

			/**
* Accessible at url : /admin/users/groups/{id}/segments/{childId}
*  Remove a segment from a user group.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteOneUserGroupSegment( String id, String childId ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
		return this.callService( "/admin/users/groups/{id}/segments/{childId}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/users/groups/{id}/segments/{childId}
*  Add a segment to a user group.
* body application/json schema : 
*{}
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putOneUserGroupSegment( String id, String childId, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
  		return this.callService( "/admin/users/groups/{id}/segments/{childId}", "put",  array , body);
		}

			/**
* Accessible at url : /admin/users/groups/{id}/countervalues/doc
*  Get a pseudo schema showing quickly how to make a proper json for user group counter value.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String getOneUserGroupCounterValueDoc( String id ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
		return this.callService( "/admin/users/groups/{id}/countervalues/doc", "get",  array, null);
		}

			/**
* Accessible at url : /admin/users/groups/{id}/countervalues/{childId}
*  Remove one counter value from a user group.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteOneUserGroupCounterValue( String id, String childId ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
		return this.callService( "/admin/users/groups/{id}/countervalues/{childId}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/users/groups/{id}/countervalues/{childId}
*  Update one counter value for a user group.
* body application/json schema : 
*{}
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putOneUserGroupCounterValue( String id, String childId, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
  		return this.callService( "/admin/users/groups/{id}/countervalues/{childId}", "put",  array , body);
		}

			/**
* Accessible at url : /admin/users/groups/{id}/countervalues/{childId}
*  Get one counter value for a user group.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
 */ 
public String getOneUserGroupCounterValue( String id, String childId ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
		return this.callService( "/admin/users/groups/{id}/countervalues/{childId}", "get",  array, null);
		}

			/**
* Accessible at url : /admin/users/groups/{id}/countervalues
*  Create a new counter value.
* body application/json schema : 
*{}
* @return 400 : 
* OR 500 : 
* OR 200 : 
 */ 
public String postUserGroupCounterValues( String id, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
  		return this.callService( "/admin/users/groups/{id}/countervalues", "post",  array , body);
		}

			/**
* Accessible at url : /admin/users/groups/{id}/countervalues
*  Get a list of counter values fro a user group.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* limit - Starting position
 */ 
public String getUserGroupCounterValues( String id, long offset, String order, String fields, long limit ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("offset",offset);
		array.put("order",order);
		array.put("fields",fields);
		array.put("limit",limit);
		
	
		return this.callService( "/admin/users/groups/{id}/countervalues", "get",  array, null);
		}

			/**
* Accessible at url : /admin/users/groups/{id}/wongames/doc
*  Get a pseudo schema showing quickly how to make a proper json for user group won games.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String getUserGroupWonGameDoc( String id ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
		return this.callService( "/admin/users/groups/{id}/wongames/doc", "get",  array, null);
		}

			/**
* Accessible at url : /admin/users/groups/{id}/wongames/{childId}
*  Delete a won game for user group by id.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteOneUserGroupWonGame( String id, String childId ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
		return this.callService( "/admin/users/groups/{id}/wongames/{childId}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/users/groups/{id}/wongames/{childId}
*  Update a won game for user group by id.
* body application/json schema : 
*{}
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putOneUserGroupWonGame( String id, String childId, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
  		return this.callService( "/admin/users/groups/{id}/wongames/{childId}", "put",  array , body);
		}

			/**
* Accessible at url : /admin/users/groups/{id}/wongames/{childId}
*  Get a won game for user group by id.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
 */ 
public String getOneUserGroupWonGame( String id, String childId ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
		return this.callService( "/admin/users/groups/{id}/wongames/{childId}", "get",  array, null);
		}

			/**
* Accessible at url : /admin/users/groups/{id}/wongames
*  Create a won game for user group.
* body application/json schema : 
*{}
* @return 400 : 
* OR 500 : 
* OR 200 : 
 */ 
public String postUserGroupWonGames( String id, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
  		return this.callService( "/admin/users/groups/{id}/wongames", "post",  array , body);
		}

			/**
* Accessible at url : /admin/users/groups/{id}/wongames
*  Get a list of user group won games.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* limit - Starting position
 */ 
public String getUserGroupWonGames( String id, long offset, String order, String fields, long limit ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("offset",offset);
		array.put("order",order);
		array.put("fields",fields);
		array.put("limit",limit);
		
	
		return this.callService( "/admin/users/groups/{id}/wongames", "get",  array, null);
		}

			/**
* Accessible at url : /admin/users/groups/{id}
*  Delete a user group.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteOneUserGroup( String id ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
		return this.callService( "/admin/users/groups/{id}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/users/groups/{id}
*  Update a user group.
* body application/json schema : 
*{}
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putOneUserGroup( String id, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
  		return this.callService( "/admin/users/groups/{id}", "put",  array , body);
		}

			/**
* Accessible at url : /admin/users/groups/{id}
*  Get a user group.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
 */ 
public String getOneUserGroup( String id ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
		return this.callService( "/admin/users/groups/{id}", "get",  array, null);
		}

			/**
* Accessible at url : /admin/users/groups
*  Add a user group.
* body application/json schema : 
*{}
* @return 400 : 
* OR 500 : 
* OR 200 : 
 */ 
public String postUserGroups( String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	
	
  		return this.callService( "/admin/users/groups", "post",  array , body);
		}

			/**
* Accessible at url : /admin/users/groups
*  Get a list of user groups.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* segments - The segments we want to narrow down our search to.
* limit - Starting position
 */ 
public String getUserGroups( long offset, String order, String fields, String segments, long limit ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("offset",offset);
		array.put("order",order);
		array.put("fields",fields);
		array.put("segments",segments);
		array.put("limit",limit);
		
	
		return this.callService( "/admin/users/groups", "get",  array, null);
		}

			/**
* Accessible at url : /admin/users/{id}/gainedachievements/doc
*  Get a pseudo schema showing quickly how to make a proper json for user gained achievement.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String getOneUserGainedAchievementsDoc( String id ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
		return this.callService( "/admin/users/{id}/gainedachievements/doc", "get",  array, null);
		}

			/**
* Accessible at url : /admin/users/{id}/gainedachievements/{childId}
*  Update one user gained achievement.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteOneUserGainedAchievement( String id, String childId ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
		return this.callService( "/admin/users/{id}/gainedachievements/{childId}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/users/{id}/gainedachievements/{childId}
*  Update one user gained achievement.
* body application/json schema : 
*{}
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putOneUserGainedAchievement( String id, String childId, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
  		return this.callService( "/admin/users/{id}/gainedachievements/{childId}", "put",  array , body);
		}

			/**
* Accessible at url : /admin/users/{id}/gainedachievements/{childId}
*  Get one user gained achievement.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
 */ 
public String getOneUserGainedAchievement( String id, String childId ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
		return this.callService( "/admin/users/{id}/gainedachievements/{childId}", "get",  array, null);
		}

			/**
* Accessible at url : /admin/users/{id}/gainedachievements
*  Create a new gained achievement for a user.
* body application/json schema : 
*{}
* @return 400 : 
* OR 500 : 
* OR 200 : 
 */ 
public String postUserGainedAchievements( String id, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
  		return this.callService( "/admin/users/{id}/gainedachievements", "post",  array , body);
		}

			/**
* Accessible at url : /admin/users/{id}/gainedachievements
*  Get a list of gained achievements for a user.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* limit - Starting position
 */ 
public String getUserGainedAchievements( String id, long offset, String order, String fields, long limit ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("offset",offset);
		array.put("order",order);
		array.put("fields",fields);
		array.put("limit",limit);
		
	
		return this.callService( "/admin/users/{id}/gainedachievements", "get",  array, null);
		}

			/**
* Accessible at url : /admin/users/{id}/customfields/{key}
*  Delete a custom field by key.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteUserCustomField( String id, String key ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("key",key);
		
	
		return this.callService( "/admin/users/{id}/customfields/{key}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/users/{id}/customfields
*  Create a new custom field for user.
* body application/json schema : 
*{}
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putUserCustomFields( String id, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
  		return this.callService( "/admin/users/{id}/customfields", "put",  array , body);
		}

			/**
* Accessible at url : /admin/users/{id}/groups/{childId}
*  Remove a group from user.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteOneGroupUser( String id, String childId ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
		return this.callService( "/admin/users/{id}/groups/{childId}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/users/{id}/groups/{childId}
*  Add a group to user.
* body application/json schema : 
*{}
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putOneGroupUser( String id, String childId, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
  		return this.callService( "/admin/users/{id}/groups/{childId}", "put",  array , body);
		}

			/**
* Accessible at url : /admin/users/{id}/externalids/doc
*  Get a pseudo schema showing quickly how to make a proper json for user external ids.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String getUserExternalIdsDoc( String id ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
		return this.callService( "/admin/users/{id}/externalids/doc", "get",  array, null);
		}

			/**
* Accessible at url : /admin/users/{id}/externalids/{childId}
*  Delete one external id for a user.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteOneUserExternalId( String id, String childId ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
		return this.callService( "/admin/users/{id}/externalids/{childId}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/users/{id}/externalids/{childId}
*  Update one external id for a user.
* body application/json schema : 
*{}
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putOneUserExternalId( String id, String childId, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
  		return this.callService( "/admin/users/{id}/externalids/{childId}", "put",  array , body);
		}

			/**
* Accessible at url : /admin/users/{id}/externalids/{childId}
*  Get one external id for a user.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
 */ 
public String getOneUserExternalId( String id, String childId ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
		return this.callService( "/admin/users/{id}/externalids/{childId}", "get",  array, null);
		}

			/**
* Accessible at url : /admin/users/{id}/externalids
*  Create a new external id.
* body application/json schema : 
*{}
* @return 400 : 
* OR 500 : 
* OR 200 : 
 */ 
public String postUserExternalIds( String id, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
  		return this.callService( "/admin/users/{id}/externalids", "post",  array , body);
		}

			/**
* Accessible at url : /admin/users/{id}/externalids
*  Get a list of external ids.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* limit - Starting position
 */ 
public String getUserExternalIds( String id, long offset, String order, String fields, long limit ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("offset",offset);
		array.put("order",order);
		array.put("fields",fields);
		array.put("limit",limit);
		
	
		return this.callService( "/admin/users/{id}/externalids", "get",  array, null);
		}

			/**
* Accessible at url : /admin/users/{id}/segments/{childId}
*  Delete a segment from a user.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteOneUserSegment( String id, String childId ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
		return this.callService( "/admin/users/{id}/segments/{childId}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/users/{id}/segments/{childId}
*  Add a segment to a user.
* body application/json schema : 
*{}
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putOneUserSegment( String id, String childId, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
  		return this.callService( "/admin/users/{id}/segments/{childId}", "put",  array , body);
		}

			/**
* Accessible at url : /admin/users/{id}/countervalues/doc
*  Get a pseudo schema showing quickly how to make a proper json for user counter values.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String getUserCounterValueDoc( String id ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
		return this.callService( "/admin/users/{id}/countervalues/doc", "get",  array, null);
		}

			/**
* Accessible at url : /admin/users/{id}/countervalues/{childId}
*  Delete one user counter value.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteOneUserCounterValue( String id, String childId ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
		return this.callService( "/admin/users/{id}/countervalues/{childId}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/users/{id}/countervalues/{childId}
*  Update one user counter value.
* body application/json schema : 
*{}
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putOneUserCounterValue( String id, String childId, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
  		return this.callService( "/admin/users/{id}/countervalues/{childId}", "put",  array , body);
		}

			/**
* Accessible at url : /admin/users/{id}/countervalues/{childId}
*  Get one user counter value.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
 */ 
public String getOneUserCounterValue( String id, String childId ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
		return this.callService( "/admin/users/{id}/countervalues/{childId}", "get",  array, null);
		}

			/**
* Accessible at url : /admin/users/{id}/countervalues
*  Create a new counter value for a user.
* body application/json schema : 
*{}
* @return 400 : 
* OR 500 : 
* OR 200 : 
 */ 
public String postUserCounterValues( String id, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
  		return this.callService( "/admin/users/{id}/countervalues", "post",  array , body);
		}

			/**
* Accessible at url : /admin/users/{id}/countervalues
*  Get a list of counter values for a user.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* limit - Starting position
 */ 
public String getUserCounterValues( String id, long offset, String order, String fields, long limit ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("offset",offset);
		array.put("order",order);
		array.put("fields",fields);
		array.put("limit",limit);
		
	
		return this.callService( "/admin/users/{id}/countervalues", "get",  array, null);
		}

			/**
* Accessible at url : /admin/users/{id}/wongames/doc
*  Get a pseudo schema showing quickly how to make a proper json for user won game.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String getUserWonGamesDoc( String id ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
		return this.callService( "/admin/users/{id}/wongames/doc", "get",  array, null);
		}

			/**
* Accessible at url : /admin/users/{id}/wongames/{childId}
*  Delete one won game for a user.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteOneUserWonGame( String id, String childId ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
		return this.callService( "/admin/users/{id}/wongames/{childId}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/users/{id}/wongames/{childId}
*  Update one won game for a user.
* body application/json schema : 
*{}
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putOneUserWonGame( String id, String childId, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
  		return this.callService( "/admin/users/{id}/wongames/{childId}", "put",  array , body);
		}

			/**
* Accessible at url : /admin/users/{id}/wongames/{childId}
*  Get one won game for a user.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
 */ 
public String getOneUserWonGame( String id, String childId ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
		return this.callService( "/admin/users/{id}/wongames/{childId}", "get",  array, null);
		}

			/**
* Accessible at url : /admin/users/{id}/wongames
*  Create a new won game for a user.
* body application/json schema : 
*{}
* @return 400 : 
* OR 500 : 
* OR 200 : 
 */ 
public String postUserWonGames( String id, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
  		return this.callService( "/admin/users/{id}/wongames", "post",  array , body);
		}

			/**
* Accessible at url : /admin/users/{id}/wongames
*  Get a list of won games for a user.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* limit - Starting position
 */ 
public String getUserWonGames( String id, long offset, String order, String fields, long limit ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("offset",offset);
		array.put("order",order);
		array.put("fields",fields);
		array.put("limit",limit);
		
	
		return this.callService( "/admin/users/{id}/wongames", "get",  array, null);
		}

			/**
* Accessible at url : /admin/users/{id}/friends/{childId}
*  Remove a friend from user.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteOneUserFriend( String id, String childId ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
		return this.callService( "/admin/users/{id}/friends/{childId}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/users/{id}/friends/{childId}
*  Add a friend to user.
* body application/json schema : 
*{}
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putOneUserFriend( String id, String childId, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
  		return this.callService( "/admin/users/{id}/friends/{childId}", "put",  array , body);
		}

			/**
* Accessible at url : /admin/users/{id}
*  Delete one user
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteOneUser( String id ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
		return this.callService( "/admin/users/{id}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/users/{id}
*  Update one user by id.
* body application/json schema : 
*{}
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putOneUser( String id, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
  		return this.callService( "/admin/users/{id}", "put",  array , body);
		}

			/**
* Accessible at url : /admin/users/{id}
*  Get one user by id.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
 */ 
public String getOneUser( String id ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
		return this.callService( "/admin/users/{id}", "get",  array, null);
		}

			/**
* Accessible at url : /admin/users
*  Add a user.
* body application/json schema : 
*{}
* @return 400 : 
* OR 500 : 
* OR 200 : 
 */ 
public String postUsers( String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	
	
  		return this.callService( "/admin/users", "post",  array , body);
		}

			/**
* Accessible at url : /admin/users
*  Get a list of users.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
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
		
	
		return this.callService( "/admin/users", "get",  array, null);
		}

			/**
* Accessible at url : /admin/clientscripts/doc
*  Get a pseudo schema showing quickly how to make a proper json for clientscripts.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
 */ 
public String getClientScriptsDoc(  ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	
	
		return this.callService( "/admin/clientscripts/doc", "get",  array, null);
		}

			/**
* Accessible at url : /admin/clientscripts/{id}
*  Delete one client script.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteOneClientScript( String id ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
		return this.callService( "/admin/clientscripts/{id}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/clientscripts/{id}
*  Change one client script.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
 */ 
public String putOneClientScript( String id ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
		return this.callService( "/admin/clientscripts/{id}", "put",  array, null);
		}

			/**
* Accessible at url : /admin/clientscripts/{id}
* fields - The fields we want to show in our result
*  Get one client script by id.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
 */ 
public String getOneClientScript( String id, String fields ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("fields",fields);
		
	
		return this.callService( "/admin/clientscripts/{id}", "get",  array, null);
		}

			/**
* Accessible at url : /admin/clientscripts
*  Post a new client script.
* body application/json schema : 
*{}
* @return 400 : 
* OR 500 : 
* OR 200 : 
 */ 
public String postClientScripts( String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	
	
  		return this.callService( "/admin/clientscripts", "post",  array , body);
		}

			/**
* Accessible at url : /admin/clientscripts
*  Get a list of client scripts.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* limit - Starting position
 */ 
public String getClientScripts( String fields, String order, long offset, long limit ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("fields",fields);
		array.put("order",order);
		array.put("offset",offset);
		array.put("limit",limit);
		
	
		return this.callService( "/admin/clientscripts", "get",  array, null);
		}

			/**
* Accessible at url : /admin/segments/doc
*  Get a pseudo schema showing quickly how to make a proper json for segment.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String getSegmentsDocs(  ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	
	
		return this.callService( "/admin/segments/doc", "get",  array, null);
		}

			/**
* Accessible at url : /admin/segments/{id}
*  Delete a segment.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteSegment( String id ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
		return this.callService( "/admin/segments/{id}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/segments/{id}
*  Update a segment.
* body application/json schema : 
*{}
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putSegment( String id, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
  		return this.callService( "/admin/segments/{id}", "put",  array , body);
		}

			/**
* Accessible at url : /admin/segments/{id}
* fields - The fields we want to show in our result
*  Get a segment.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
 */ 
public String getSegment( String id, String fields ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("fields",fields);
		
	
		return this.callService( "/admin/segments/{id}", "get",  array, null);
		}

			/**
* Accessible at url : /admin/segments
*  Add a new segment.
* body application/json schema : 
*{}
* @return 400 : 
* OR 500 : 
* OR 200 : 
 */ 
public String postSegments( String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	
	
  		return this.callService( "/admin/segments", "post",  array , body);
		}

			/**
* Accessible at url : /admin/segments
*  Get a segment list.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* limit - Starting position
 */ 
public String getSegments( String fields, long offset, long limit, String order ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("fields",fields);
		array.put("offset",offset);
		array.put("limit",limit);
		array.put("order",order);
		
	
		return this.callService( "/admin/segments", "get",  array, null);
		}

			/**
* Accessible at url : /admin/conditions/doc
*  Get a pseudo schema showing quickly how to make a proper json for clientscripts.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
 */ 
public String getConditionsDoc(  ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	
	
		return this.callService( "/admin/conditions/doc", "get",  array, null);
		}

			/**
* Accessible at url : /admin/conditions/{id}/counters/{childId}
*  Remove a counter.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteOneConditionCounter( String id, String childId ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
		return this.callService( "/admin/conditions/{id}/counters/{childId}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/conditions/{id}/counters/{childId}
*  Add a counter.
* body application/json schema : 
*{}
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putOneConditionCounter( String id, String childId, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
  		return this.callService( "/admin/conditions/{id}/counters/{childId}", "put",  array , body);
		}

			/**
* Accessible at url : /admin/conditions/{id}
*  Delete one condition.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteOneCondition( String id ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
		return this.callService( "/admin/conditions/{id}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/conditions/{id}
*  Change one condition.
* body application/json schema : 
*{}
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putOneCondition( String id, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
  		return this.callService( "/admin/conditions/{id}", "put",  array , body);
		}

			/**
* Accessible at url : /admin/conditions/{id}
* fields - The fields we want to show in our result
*  Get one condition.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
 */ 
public String getOneCondition( String id, String fields ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("fields",fields);
		
	
		return this.callService( "/admin/conditions/{id}", "get",  array, null);
		}

			/**
* Accessible at url : /admin/conditions
*  Add a new condition.
* body application/json schema : 
*{}
* @return 400 : 
* OR 500 : 
* OR 200 : 
 */ 
public String postConditions( String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	
	
  		return this.callService( "/admin/conditions", "post",  array , body);
		}

			/**
* Accessible at url : /admin/conditions
*  Get a list of conditions.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* limit - Starting position
 */ 
public String getConditions( String fields, long offset, long limit, String order ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("fields",fields);
		array.put("offset",offset);
		array.put("limit",limit);
		array.put("order",order);
		
	
		return this.callService( "/admin/conditions", "get",  array, null);
		}

			/**
* Accessible at url : /admin/counters/doc
*  Get a pseudo schema showing quickly how to make a proper json for clientscripts.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String getCountersDoc(  ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	
	
		return this.callService( "/admin/counters/doc", "get",  array, null);
		}

			/**
* Accessible at url : /admin/counters/{id}/conditions/{childId}
*  Remove a condition from a counter.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteOneCounterCondition( String id, String childId ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
		return this.callService( "/admin/counters/{id}/conditions/{childId}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/counters/{id}/conditions/{childId}
*  Add a condition to a counter.
* body application/json schema : 
*{}
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putOneCounterCondition( String id, String childId, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("childId",childId);
		
	
  		return this.callService( "/admin/counters/{id}/conditions/{childId}", "put",  array , body);
		}

			/**
* Accessible at url : /admin/counters/{id}
*  Delete one counter.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String deleteOneCounter( String id ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
		return this.callService( "/admin/counters/{id}", "delete",  array, null);
		}

			/**
* Accessible at url : /admin/counters/{id}
*  Add a new counter.
* body application/json schema : 
*{}
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : 
 */ 
public String putOneCounter( String id, String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		
	
  		return this.callService( "/admin/counters/{id}", "put",  array , body);
		}

			/**
* Accessible at url : /admin/counters/{id}
* fields - The fields we want to show in our result
*  Get one counter.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
 */ 
public String getOneCounter( String id, String fields ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("id",id);
		array.put("fields",fields);
		
	
		return this.callService( "/admin/counters/{id}", "get",  array, null);
		}

			/**
* Accessible at url : /admin/counters
*  Add a new counter.
* body application/json schema : 
*{}
* @return 400 : 
* OR 500 : 
* OR 200 : 
 */ 
public String postCounters( String body ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	
	
  		return this.callService( "/admin/counters", "post",  array , body);
		}

			/**
* Accessible at url : /admin/counters
*  Get one counter.
* @return 400 : 
* OR 404 : 
* OR 500 : 
* OR 200 : application/json : {}
* offset - Number of returning items
* order - The fields we want to order by and the type of ordering.
* fields - The fields we want to show in our result
* limit - Starting position
 */ 
public String getCounters( long offset, long limit, String fields, String order ) { 
	Map<String,Object> array = new HashMap<String,Object>();
    	array.put("offset",offset);
		array.put("limit",limit);
		array.put("fields",fields);
		array.put("order",order);
		
	
		return this.callService( "/admin/counters", "get",  array, null);
		}

	 
 } 