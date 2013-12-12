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

		/* @TODO Should be logged*/
		println(s"Building SDK package for API version ${pack.docs("version")}")

		/* @TODO we should be able to use this traits, not have them written manually in Method class */
		val traits = analyseTraits(raml.getTraits())

		for { child <- raml.getResources().asScala } {
			analyseResource(child, pack)
		}

		pack
	}

	/**
	 * Analyses traits, @TODO : get all the info about traits
	 */
	private def analyseTraits(traits: java.util.List[java.util.Map[String, org.raml.model.Template]]): Map[String, Any] = {

		var map: Map[String, Any] = Map()

		if (traits != null) {
			traits.asScala.foreach {
				m =>
					m.asScala.foreach {
						kv => map += (kv._2.getDisplayName() -> classOf[String])
					}
			}
		}
		map

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
		}
	}

	/**
	 *  Adds Method's to clazz object
	 */
	def createMethods(methods: java.util.Map[ActionType, Action], clazz: Clazz, url: String, displayName: String) {

		methods.asScala.foreach {
			action_tuple =>
				{

					val actionType = action_tuple._1
					val action = action_tuple._2

					/* @TODO : use responses and body parameters.**/
					//action.getResponses()
					//action.getBody
					val m = new Method(mapRestType(actionType), url, displayName, action.getSecuredBy().asScala.toList)
					m.setupTraits(action.getIs().asScala.toList)

					// get secured by
					m.addDoc("desciption", action.getDescription())
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
		if (name != null) clazz = new Clazz(url, name)
		else clazz = new Clazz(url)

		createMethods(methods, clazz, url, name)

		pack.addClazz(clazz)

		/* Analyze the subresources */
		for { child <- resource.getResources().asScala } {
			analyzeSubresource(child, clazz)
		}
	}

	/**
	 * Analyses subresources and adds it to Clazz class object
	 */
	private def analyzeSubresource(resourceTuple: (String, Resource), clazz: Clazz) {

		val url = resourceTuple._2.getUri()
		val resource = resourceTuple._2
		val methods = resource.getActions()

		createMethods(methods, clazz, url, resource.getDisplayName())

		for { child <- resource.getResources().asScala } {
			analyzeSubresource(child, clazz)
		}
	}
}