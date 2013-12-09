package gensdk

class Clazz {

	private var methods : List[Method] = List()
	var docs: Map[String,String] = Map()
	def addMethod( method : Method) {
		methods = method :: methods
	}
	
	def addDoc(name : String, description: String){
		docs += (name -> description)
	}
	
	
}