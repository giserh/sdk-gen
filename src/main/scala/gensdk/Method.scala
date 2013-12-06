package gensdk

object Rest{
	
	class Method
	case class Get extends Method
	case class Patch extends Method
	case class Post extends Method
	case class Put extends Method
	
}

class Method(val restType: Rest.Method, val url : String, val comment : String = "") {
	
	
	private var body : Option[String] = None
	private var query : Map[String,Any] = Map()
	
	def addQueryParameter(key : String, value : Any){
		query = query + (key -> value)
	}
	
	def setBody(newBody : String) {
		body = Some(newBody)
	}
	
}