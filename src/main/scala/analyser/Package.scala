package analyser

import org.raml.model.Raml
import org.raml.parser.visitor.RamlDocumentBuilder
import collection.JavaConverters._
import org.raml.model.Resource
import org.raml.model.ActionType

class Package {

	private var docs: Map[String, String] = Map()
	private var clazzes: List[Clazz] = List()

	private def addDoc(name: String, description: String) {
		docs += (name -> description)
	}

	private def addClazz(clazz: Clazz) {
		clazzes = clazz :: clazzes
	}

	def getClazzes() : List[Clazz] = {
		clazzes
	}
	
	def parse(raml: Raml) {

		val version = raml.getVersion()
		println(s"Building SDK package for API version $version")

		val traits = analyseTraits(raml.getTraits())

		for { child <- raml.getResources().asScala } {
			analyseResource(child)
		}

		println(clazzes)
	}

	private def analyseTraits(traits: java.util.List[java.util.Map[String, org.raml.model.Template]]): Map[String, Any] = {
		var map: Map[String, Any] = Map()
		traits.asScala.foreach {
			m =>
				m.asScala.foreach {
					kv => map += (kv._2.getDisplayName() -> classOf[String])
				}
		}
		map

	}

	private def mapRestType(atype: ActionType): analyser.RestType= {
		atype match {
			case ActionType.GET => new Get
			case ActionType.POST => new Post
			case ActionType.PUT => new Put
			case ActionType.PATCH => new Patch
		}
	}

	private def analyseResource(resourceTuple: (String, Resource)) {
		val url = resourceTuple._2.getUri()
		val resource = resourceTuple._2
		val methods = resource.getActions()

		val clazz: Clazz = new Clazz(url)
		methods.asScala.foreach {
			action =>
				{
					//private val regex = """\{[a-zA-Z0-9,]+\}""".r
					//		regex.findAllIn(url)
					val atype=action._1
					val act=action._2
					val m = new Method(mapRestType(atype), url)
					m.setupTraits(act.getIs().asScala.toList)
					// get secured by
					m.addDoc("desciption", act.getDescription())
					clazz.addMethod(m)
				}
		}

		this.addClazz(clazz)

		for { child <- resource.getResources().asScala } {
			analyseResource(child)
		}
	}
}