package gensdk

package Rest {

	abstract class Method
	case class Get extends Method {
		override def toString(): String = {
			"get"
		}
	}
	case class Patch extends Method {
		override def toString(): String = {
			"patch"
		}
	}
	case class Post extends Method {
		override def toString(): String = {
			"post"
		}
	}
	case class Put extends Method {
		override def toString(): String = {
			"put"
		}
	}

}

class Method(val restType: Rest.Method, val url: String) {

	val name: String = restType.toString() + createName(url)
	private var docs: Map[String, String] = Map()

	private var body: Option[String] = None
	var query: Map[String, Any] = Map()

	def addDoc(name: String, description: String) {
		docs += (name -> description)
	}

	def addQueryParameter(key: String, value: Any) {
		query = query + (key -> value)
	}

	def setBody(newBody: String) {
		body = Some(newBody)
	}

	/**
	 * Creates name from url
	 */
	def createName(url: String): String = {
		val regex = """\{[a-zA-Z0-9,]+\}""".r
		val urlNew = regex.replaceAllIn(url, "One")
		urlNew.split("/").toList.map { s => s.capitalize }.mkString("")
	}
	
	/**
	 * Check the traits that are available for this method
	 */
	def setupTraits( traits : List[String]){
		traits match {
			case "paginator"::tail => {
				addQueryParameter("limit",classOf[Long])
				addQueryParameter("offset",classOf[Long])
				setupTraits(tail)
			}
			case "ordering"::tail => {
				addQueryParameter("order",classOf[List[(String,String)]])
				setupTraits(tail)
			}
			case "fields"::tail => {
				addQueryParameter("fields",classOf[List[String]])
				setupTraits(tail)
			}
			case "groups"::tail => {
				addQueryParameter("groups",classOf[List[Long]])
				setupTraits(tail)
			}
			case "segments"::tail => {
				addQueryParameter("segments",classOf[List[Long]])
				setupTraits(tail)
			}
			case Nil =>
			case e => throw new NoSuchFieldException("Unexpected trait name : "+e) 
		}
		
	}
	
	/**
	 * Returns the name of the method.
	 */
	override def toString() = name

}