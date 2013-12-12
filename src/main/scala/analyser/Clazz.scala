package analyser

class Clazz(val url: String, private var _name : String = null) {

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
	
	private def createName(url: String): String = {
		val regex = """\{[a-zA-Z0-9,]+\}""".r
		val urlNew = regex.replaceAllIn(url, "One")
		 urlNew.split("/").toList.map { s => s.capitalize }.mkString("")		

	}
	
	override def toString = name
	
	
	
}