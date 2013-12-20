package analyser

class Clazz(val url: String, val baseUrl : String, val oauthUrl : String,val version : String, private var _name : String = null) {

	if (_name == null) _name = createName(url)
	private var _methods : List[Method] = List()
	private var _docs: Map[String,String] = Map()
	
	def name = _name
	def docs = _docs	
	def methods = _methods
	
	def add( method : Method) {
		_methods = method :: _methods
	}
	
	def addDoc(name : String, description: String){
		_docs += (name -> description)
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
	
	override def toString = name
	
	
	
}