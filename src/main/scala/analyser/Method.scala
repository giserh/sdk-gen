package analyser

import org.raml.model.SecurityReference
import org.raml.model.parameter.QueryParameter
import org.raml.model.MimeType
import org.raml.model.Response
import collection.JavaConverters._

/**
 * Type of REST method.
 */
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

/**
 * Represents the type of documentation
 */
object DocType extends Enumeration {
	val PARAM = Value("param")
	val RETURN = Value("return")
	val DESCRIPTION = Value("")
	val OTHER = Value("")
}

/**
 * Represents a method to be generated.
 */
class Method(val restType: RestType.Value, val url: String, private var _name: String = null, val securedBy: List[SecurityReference]) {

	// Create a simple name
	if (_name == null) _name = restType + createName(url)
	else _name = restType.toString() + _name

	// Documents representing description of the method.
	private var _docs: Map[String, (DocType.Value, String)] = Map()
	// Optional body to be sent with the request.
	private var _body: Option[String] = None
	// All needed query parameters
	private var _query: Map[String, String] = Map()

	// check url for ids and then add to query parameters
	addIdToQuery()

	def name = _name
	def query = _query
	def body = _body
	def body_=(value: String): Unit = _body = Some(value)
	def docs = _docs

	/**
	 * Add documentation to method.
	 */
	def addDoc(docName: String, description: String, docType: DocType.Value) {
		val add = (docType, description)
		_docs += (docName -> add)
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
		val urlNew = regexEnds.findFirstIn(url) match {
			case Some(v) => "one" + regex.replaceAllIn(url, "#")
			case None => regex.replaceAllIn(url, "#")
		}

		def singularize(list: List[String], remove: Boolean): String = list match {
			case head :: tail if (remove && head.endsWith("s")) => singularize(tail, false) + head.substring(0, head.length() - 1).toString().capitalize
			case "#" :: tail => singularize(tail, true)
			case Nil => ""
			case head :: tail => singularize(tail, false) + head.capitalize
		}
		singularize(urlNew.split("/").toList.reverse, false)

	}

	private def addIdToQuery() {
		val regex = """\{[a-zA-Z0-9,]+\}""".r
		regex.findAllIn(this.url).foreach {
			id =>
				{
					var getId = id.replace("{", "")
					getId = getId.replace("}", "")
					
					if( getId.equals("eventId") || getId.equals("notificationId") || getId.equals("externalIdName") || getId.equals("externalId"))
						addQueryParameter(getId, "STRING")
					else 
						addQueryParameter(getId, "NUMBER")
						
					// get the name of the object
					val start = this.url.take(this.url.indexOf(id) - 1)
					var name = start.drop(start.lastIndexOf("/") + 1)
					if (name.endsWith("s")) name = name.take(name.length() - 1)

					addDoc(getId, "Id of the " + name + " we are interested in.", DocType.PARAM)
				}
		}
	}

	/**
	 * Check the traits that are available for this method
	 */
	def setupTraits(traits: Map[String, QueryParameter]) {

		traits.foreach {
			param =>
				addQueryParameter(param._1, param._2.getType().toString)
				addDoc(param._1, param._2.getDescription(), DocType.PARAM)
		}

	}

	/**
	 * Setup body for docs
	 */
	def setupBody(body: scala.collection.mutable.Map[String, MimeType]) {
		if (!body.isEmpty) {
			// setup example body
			val appjson = body("application/json")
			if (appjson != null) {
				if (appjson.getExample() != null)
					this.addDoc("example_body", appjson.getExample(), DocType.OTHER)

				/** Just to show that we use body */
				this.addQueryParameter("body", "STRING")
				this.addDoc("body", appjson.getSchema(), DocType.PARAM)
			}

		}
	}

	/**
	 * Setup responses examples
	 */
	def setupResponses(responses: scala.collection.mutable.Map[String, Response]) {
		if (!responses.isEmpty) {

			// getting a successful result - 200			
			if (responses.contains("200")) {
				val res200 = responses("200")
				val example = res200.getBody().get("application/json")
				if (example != null) {
					if (example.getExample() != null)
						this.addDoc("example", example.getExample(), DocType.OTHER)
				}
			}

			// getting a successful result - 201			
			if (responses.contains("201")) {
				val res201 = responses("201")
				val example = res201.getBody().get("application/json")
				if (example != null) {
					if (example.getExample() != null)
						this.addDoc("example", example.getExample(), DocType.OTHER)
				}
			}

			// get return values schemas
			var gatherReturn = List[String]()
			responses.foreach {
				response =>
					{
						/** Get all mime-types of responses **/
						val typeSchema = response._2.getBody().asScala.map { tpl => tpl._1 + " : " + tpl._2.getSchema() /*.replace("\n", "\n *")*/ }.mkString(" | ")
						/** Join all possible responses **/
						gatherReturn = response._1 + " : " + typeSchema :: gatherReturn
					}
			}
			this.addDoc("return value", gatherReturn.mkString("\n or "), DocType.RETURN)
		}
	}
	
	
	/**
	 * Returns the name of the method.
	 */
	override def toString() = name

}