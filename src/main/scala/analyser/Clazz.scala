package analyser

class Clazz(val url: String) {

	private val regex = """\{[a-zA-Z0-9,]+\}""".r
	val name : String = createName(url)
	println(name)
	
	private var methods : List[Method] = List()
	var docs: Map[String,String] = Map()
	
	def addMethod( method : Method) {
		methods = method :: methods
	}
	
	def addDoc(name : String, description: String){
		docs += (name -> description)
	}
	
	def createName(url: String): String = {

		val urlNew = regex.replaceAllIn(url, "One")
		 urlNew.split("/").toList.map { s => s.capitalize }.mkString("")		

	}
	
	override def toString() = name
	
	def getMethods() : List[Method] ={
		methods
	}
}