package gensdk

class Clazz {

	var classes : Map[String,List[Method]] = Map()
	
	def addMethod( name : String , method : Method) {
		if (classes.contains(name)){
			classes = classes.updated(name, method :: method :: classes(name))
		}
		else {
			classes = classes + (name -> List(method))
		}
	}
	
	
}