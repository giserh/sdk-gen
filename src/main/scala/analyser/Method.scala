package analyser

import org.raml.model.SecurityReference
import org.raml.model.parameter.QueryParameter


object RestType extends Enumeration {
    val PUT = Value("put")
    val POST = Value("post")
    val GET = Value("get")
    val PATCH = Value("patch")
    val DELETE = Value("delete")
    val TRACE = Value("trace")
    val OPTIONS = Value("options")
    val HEAD = Value("head")
  }

class Method(val restType: RestType.Value, val url: String, private var _name : String = null, val securedBy : List[SecurityReference]) {

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
	
	/**
	 * Add documentation to method.
	 */
	def addDoc(name: String, description: String) {
		_docs += (name -> description)
	}

	/**
	 * Add additional parameter to method.
	 */
	def addQueryParameter(key: String, value: String) {
		_query = query + (key -> value)
	}

	/**
	 * Creates name from url
	 */
	private def createName(url: String): String = {
		
		val regexEnds = """\{[a-zA-Z0-9,]+\}$""".r
		val regex = """\{[a-zA-Z0-9,]+\}""".r
		val urlNew = regexEnds.findFirstIn(url) match{
			case Some(v) => "one" + regex.replaceAllIn(url, "#") 
			case None => regex.replaceAllIn(url, "#") 
		}				
		
		def singularize(list : List[String], remove : Boolean) : String = list match{
			case head::tail if (remove && head.endsWith("s")) => singularize(tail, false) +  head.substring(0,head.length()-1).toString().capitalize
			case "#"::tail => singularize(tail,true)
			case Nil => ""
			case head::tail => singularize(tail, false) + head.capitalize 
		}
		singularize(urlNew.split("/").toList.reverse,false)
		
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
	 * Check the traits that are available for this method
	 */
	def setupTraits(traits: Map[String,QueryParameter]) {
		
		traits.foreach{
			param => addQueryParameter(param._1 , param._2.getType().toString)
			addDoc(param._1 , "- " + param._2.getDescription())
		}			

	}

	/**
	 * Returns the name of the method.
	 */
	override def toString() = name

}