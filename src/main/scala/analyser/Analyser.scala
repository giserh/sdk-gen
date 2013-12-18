package analyser

import org.raml.model.ActionType
import org.raml.model.Raml
import org.raml.model.Resource
import org.raml.parser.visitor.RamlDocumentBuilder
import collection.JavaConverters._
import org.raml.model.SecurityReference
import org.raml.model.Action

object Analyser {

	/**
	 * Takes a Raml parser output and returns a Package object representing the SDK
	 */
	def analyseRaml(raml: Raml): Package = {

		val baseUri = raml.getBaseUri()
		val mediaType = raml.getMediaType()
		val securedBy = raml.getSecuredBy().get(0)

		val pack = new Package(baseUri, mediaType, securedBy)

		val version = raml.getVersion()
		pack.addDoc("version", version)

		val title = raml.getTitle()
		pack.addDoc("title", title)
		
		val oauth = raml.getSecuritySchemes().get(0).get("oauth_2_0").getSettings().get("baseUri").get(0)
		
		pack.addDoc("baseOauthUri", oauth)
		
		
		/* @TODO Should be logged*/
		println(s"Building SDK package for API version ${pack.docs("version")}")

		for { child <- raml.getResources().asScala } {
			analyseResource(child, pack)
		}

		pack
	}
	
	/**
	 * Maps raml REST method type to the one used by the analyser
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
	 *  Adds Method's to clazz object @TODO generalize the way to add docs
	 */
	def createMethods(methods: java.util.Map[ActionType, Action], clazz: Clazz, url: String, displayName: String) {

		methods.asScala.foreach {
			action_tuple =>
				{

					val actionType = action_tuple._1
					val action = action_tuple._2
				
					val m = new Method(mapRestType(actionType), url, displayName, action.getSecuredBy().asScala.toList)
					m.setupTraits(action.getQueryParameters().asScala.toMap)
					
					m.addDoc("", action.getDescription()) 
					
					
					val body=action.getBody
					
					/** Analyse the possible values for body **/
					if(!body.isEmpty()) {
						var gatherBodyTypes = List[String]()
						body.asScala.foreach{
							body_type => {
								/** Add example schema to docs **/
								gatherBodyTypes =  (body_type._1 + " schema : \n" +body_type._2.getSchema()).replace("\n", "\n*")  :: gatherBodyTypes
							}
							
						}
						m.addQueryParameter("body", gatherBodyTypes.mkString("\n OR ") )
						m.addDoc("body", gatherBodyTypes.mkString("\n OR "))
					}
					
					
					val responses = action.getResponses() 
					if (!responses.isEmpty()){
						var gatherReturn = List[String]()
						responses.asScala.foreach{
							response => {
								/** Get all mime-types of responses **/
								val typeSchema = response._2.getBody().asScala.map{ tpl => tpl._1 + " : " + tpl._2.getSchema().replace("\n", "\n *")}.mkString(" | ")
								/** join all possible responses **/
								gatherReturn = response._1 + " : " + typeSchema :: gatherReturn 
							}
						}
						m.addDoc("@return", gatherReturn.mkString("\n* OR "))
					}	
					
										
					clazz.add(m)
				}
		}
	}

	/**
	 * Analyses resources and adds it to Package class object
	 */
	private def analyseResource(resourceTuple: (String, Resource), pack: Package) {

		val url = resourceTuple._2.getUri()
		val resource = resourceTuple._2
		val methods = resource.getActions()

		val name = resource.getDisplayName()		
		
		var clazz: Clazz = null
		if (name != null) clazz = new Clazz(url, pack.baseUri, pack.docs("baseOauthUri"), pack.docs("version"), name)
		else clazz = new Clazz(url,pack.baseUri, pack.docs("baseOauthUri"),pack.docs("version"))

		createMethods(methods, clazz, url, name)

		pack.addClazz(clazz)

		/* Analyze the subresources */
		for { child <- resource.getResources().asScala } {
			analyzeSubresource(child, clazz)
		}
	}

	/**
	 * Analyses subresources and adds it to Clazz class object.
	 */
	private def analyzeSubresource(resourceTuple: (String, Resource), clazz: Clazz) {
		
		/* base uri*/
		val url = resourceTuple._2.getUri()
		
		/* actual resource */
		val resource = resourceTuple._2
		
		/* actions for a uri */
		val methods = resource.getActions()
		createMethods(methods, clazz, url, resource.getDisplayName())

		for { child <- resource.getResources().asScala } {
			analyzeSubresource(child, clazz)
		}
	}
}