package analyser

import org.raml.model.SecurityReference

/**Color strategy*/
object RestType extends Enumeration {
    val PUT = Value("put")
    val POST = Value("post")
    val GET = Value("get")
    val PATCH = Value("patch")
    val DELETE = Value("delete")
  }

class Method(val restType: RestType.Value, val url: String, private var _name : String = null, val securedBy : List[SecurityReference]) {

	/* @TODO add One to method name if display name was given*/
	if (_name == null) _name = restType + createName(url)
	else _name = restType.toString() + _name	
	
	private var _docs: Map[String, String] = Map()
	private var _body: Option[String] = None
	private var _query: Map[String, String] = Map()

	addIdToQuery()
	
	def name = _name
	def query = _query
	def body = _body
	def body_= (value:String): Unit = _body = Some(value)
	def docs = _docs
	
	def addDoc(name: String, description: String) {
		_docs += (name -> description)
	}

	def addQueryParameter(key: String, value: String) {
		_query = query + (key -> value)
	}

	/**
	 * Creates name from url
	 */
	private def createName(url: String): String = {
		val regex = """\{[a-zA-Z0-9,]+\}""".r
		val urlNew = regex.findFirstIn(url) match{
			case Some(v) => "one" + regex.replaceAllIn(url, "") 
			case None => url
		}		
		
		val names = urlNew.split("/").toList
		
		
		names.map { s => s.capitalize }.mkString("")
	}
	
	private def addIdToQuery(){
		val regex = """\{[a-zA-Z0-9,]+\}""".r
		regex.findAllIn(this.url).foreach{
			id => {				
				var getId = id.replace("{", "")				
				getId = getId.replace("}", "")
				addQueryParameter(getId, "String")
			}
		}
	}

	/**
	 * @TODO make it generic
	 * Check the traits that are available for this method
	 */
	def setupTraits(traits: List[String]) {
		traits match {
			case "paginator" :: tail => {
				addQueryParameter("limit", "Long")
				addQueryParameter("offset", "Long")
				setupTraits(tail)
			}
			case "ordering" :: tail => {
				addQueryParameter("order", "List[(String, String)]]")
				setupTraits(tail)
			}
			case "fields" :: tail => {
				addQueryParameter("fields", "List[String]]")
				setupTraits(tail)
			}
			case "groups" :: tail => {
				addQueryParameter("groups", "List[Long]]")
				setupTraits(tail)
			}
			case "segments" :: tail => {
				addQueryParameter("segments", "List[Long]")
				setupTraits(tail)
			}
			case Nil =>
			case e => throw new NoSuchFieldException("Unexpected trait name : " + e)
		}

	}

	/**
	 * Returns the name of the method.
	 */
	override def toString() = name

}