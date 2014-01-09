package analyser

import org.raml.model.ActionType
import org.raml.model.Raml
import org.raml.model.Resource
import org.raml.parser.visitor.RamlDocumentBuilder
import collection.JavaConverters._
import org.raml.model.SecurityReference
import org.raml.model.Action

/**
 * Object used to extract data from raml objects and creating sdk packages.
 */
object Analyser {

	/**
	 * Takes a Raml parser output and returns a Package object representing the SDK
	 * @param raml : Raml object representing a raml document
	 * @return Package representing the sdk to be generated.
	 */
	def analyseRaml(raml: Raml): Package = {

		val baseUri = raml.getBaseUri()
		val mediaType = raml.getMediaType()
		val securedBy = raml.getSecuredBy().get(0)

		val oauth = raml.getSecuritySchemes().get(0).get("oauth_2_0").getSettings().get("baseUri").get(0)
			
		val pack = new Package(baseUri, mediaType, securedBy, oauth)

		val version = raml.getVersion()
		pack.addDoc("version", version)

		val title = raml.getTitle()
		pack.addDoc("title", title)		
		
		
		/* @TODO Should be logged*/
		println(s"Building SDK package for API version ${pack.docs("version")}")

		for { child <- raml.getResources().asScala } {
			analyseResource(child, pack)
		}

		pack
	}
	
	/**
	 * Maps raml REST method type to the one used by the analyser
	 * @param atype : ActionType enum representing the type of rest method
	 * @return enum from analyser package
	 */
	private def mapRestType(atype: ActionType): analyser.RestType.Value = {
		atype match {
			case ActionType.GET => RestType.GET
			case ActionType.POST => RestType.POST
			case ActionType.PUT => RestType.PUT
			case ActionType.PATCH => RestType.PATCH
			case ActionType.DELETE => RestType.DELETE
			case ActionType.HEAD => RestType.HEAD
			case ActionType.OPTIONS => RestType.OPTIONS
			case ActionType.TRACE => RestType.TRACE
		}
	}

	/**
	 *  Adds Method's to clazz object.
	 *  @param methods : map of actionTypes to actions with all the needed information.
	 *  @param clazz : Clazz object to which we want to add methods
	 *  @param resourceUrl : url of the resource containing the methods in map
	 *  @param resourceName : name of the resource containing the methods in map
	 */
	private def createMethods(methods: java.util.Map[ActionType, Action], clazz: Clazz, resourceUrl : String, resourceName: String) {

		/** We check each method*/
		methods.asScala.foreach {
			action_tuple =>
				{

					val actionType = action_tuple._1
					val action = action_tuple._2
				
					/** Create new method with all the needed parameters. */
					val m = new Method(mapRestType(actionType), resourceUrl, resourceName, action.getSecuredBy().asScala.toList)
					/** Add the traits */
					m.setupTraits(action.getQueryParameters().asScala.toMap)
					
					/** Add basic doc */
					m.addDoc("", action.getDescription(),DocType.OTHER) 
					
					
					/** Analyse the possible values for body **/
					val body=action.getBody					
					
					if(!body.isEmpty()) {
						var gatherBodyTypes = List[String]()
						body.asScala.foreach{
							body_type => {
								/** Add example schema to docs **/
								gatherBodyTypes =  (body_type._1 + " schema : \n" +body_type._2.getSchema()).replace("\n", "\n*")  :: gatherBodyTypes
							}
							
						}
						/** Just to show that we use body */
						m.addQueryParameter("body", "STRING")
						m.addDoc("body", gatherBodyTypes.mkString("\n OR "),DocType.PARAM)
					}
					
					/** Analyse the possible values for responses **/
					val responses = action.getResponses() 
					if (!responses.isEmpty()){
						var gatherReturn = List[String]()
						responses.asScala.foreach{
							response => {
								/** Get all mime-types of responses **/
								val typeSchema = response._2.getBody().asScala.map{ tpl => tpl._1 + " : " + tpl._2.getSchema().replace("\n", "\n *")}.mkString(" | ")
								/** Join all possible responses **/
								gatherReturn = response._1 + " : " + typeSchema :: gatherReturn 
							}
						}
						m.addDoc("return value", gatherReturn.mkString("\n* or "),DocType.RETURN)
					}	
					
										
					clazz.add(m)
				}
		}
	}

	/**
	 * Analyses resources and adds it to Package class object
	 * @param resourceTuple : tuple containing url of the resource and the resource class
	 * @param pack : Package to add all the classes to
	 */
	private def analyseResource(resourceTuple: (String, Resource), pack: Package) {

		/*Base uri*/
		val url = resourceTuple._2.getUri()
		
		/*Actual resource*/
		val resource = resourceTuple._2
		
		val methods = resource.getActions()
		val name = resource.getDisplayName()		
		
		/** create a new clazz object*/
		var clazz: Clazz = null
		if (name != null) clazz = new Clazz(url, pack.baseUri, pack.baseOauthUri, pack.docs("version"), name)
		else clazz = new Clazz(url,pack.baseUri, pack.baseOauthUri, pack.docs("version"))

		createMethods(methods, clazz , url ,name)

		pack.addClazz(clazz)

		/* Analyze the subresources */
		for { child <- resource.getResources().asScala } {
			analyzeSubresource(child, clazz)
		}
	}

	/**
	 * Analyses subresources and adds it to Clazz class object.
	 * @param resourceTuple : tuple containing url of the resource and the resource class.
	 * @param clazz : Clazz to add all the methods to.
	 */
	private def analyzeSubresource(resourceTuple: (String, Resource), clazz: Clazz) {
		
		/* base uri*/
		val url = resourceTuple._2.getUri()
		
		/* actual resource */
		val resource = resourceTuple._2
		
		/* actions for a uri */
		val methods = resource.getActions()
		createMethods(methods, clazz,url, resource.getDisplayName())

		for { child <- resource.getResources().asScala } {
			analyzeSubresource(child, clazz)
		}
	}
}