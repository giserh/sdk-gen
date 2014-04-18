package analyser

/**
 * Class containing all needed information to create a class.
 */
class Clazz(val url: String, val baseUrl : String, val oauthUrl : String,val version : String) {

	//create a name for generated class
	val name = createName(url)
	var methods : List[Method] = List()
	var docs: Map[String,String] = Map()

  /**
   * Creates name for class
   * @param path path identifying the resource
   * @return
   */
	private def createName(path: String): String = {
		
		val regexEnds = """\{[a-zA-Z0-9,]+\}$""".r
		val regex = """\{[a-zA-Z0-9,]+\}""".r
		val urlNew = regexEnds.findFirstIn(path) match{
			case Some(v) => "one" + regex.replaceAllIn(path, "#")
			case None => regex.replaceAllIn(path, "#")
		}				
		
		def singularize(list : List[String], remove : Boolean) : String = list match{
			case head::tail if remove && head.endsWith("s") => singularize(tail, false) +  head.substring(0,head.length()-1).capitalize
			case "#"::tail => singularize(tail,true)
			case Nil => ""
			case head::tail => singularize(tail, false) + head.capitalize 
		}
		singularize(urlNew.split("/").toList.reverse,false)
		
	}
	
	override def toString = name	
	
}