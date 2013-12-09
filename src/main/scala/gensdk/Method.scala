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
	println(name)
	private var docs: Map[String, String] = Map()

	private var body: Option[String] = None
	private var query: Map[String, Any] = Map()

	def addDoc(name: String, description: String) {
		docs += (name -> description)
	}

	def addQueryParameter(key: String, value: Any) {
		query = query + (key -> value)
	}

	def setBody(newBody: String) {
		body = Some(newBody)
	}

	def createName(url: String): String = {

		val regex = """\{[a-zA-Z0-9,]+\}""".r
		val urlNew = regex.replaceAllIn(url, "One")
		urlNew.split("/").toList.map { s => s.capitalize }.mkString("")

	}
	
	override def toString() = name

}