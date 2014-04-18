package analyser

import org.raml.model.parameter.QueryParameter
import org.raml.model.MimeType
import org.raml.model.Response

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
 * Type of parameters.
 */
object ReturnType extends Enumeration {
  val STRING = Value("string")
  val STRING_LIST = Value("string_list")
  val NUMBER_LIST = Value("number_list")
  val MAP = Value("map")
  val NUMBER = Value("number")
  val BOOLEAN = Value("boolean")
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
class Method(val restType: RestType.Value, val path: String) {

  /* Name created from path and rest type*/
  val name: String = restType + createName(path)

  /* Additional information about the method */
  var docs: Map[String, (DocType.Value, String)] = Map()

  /* Optional body to be sent with the request (Example, Schema)*/
  var body: Option[(String, String)] = None

  /* List of response (Code, Example, Schema) */
  var responses: List[(Int, String, String)] = List()

  /* All needed query parameters */
  var query: Map[String, ReturnType.Value] = Map()

  // check path for ids and then add to query parameters
  addIdToQuery()

  /**
   * Add new doc
   * @param docName name
   * @param description description of doc
   * @param docType type of doc
   */
  def addDoc(docName: String, description: String, docType: DocType.Value) {
    val add = (docType, description)
    docs += (docName -> add)
  }

  /**
   * Creates a new name based on path to resource.
   * @param path path to method
   * @return new name
   */
  def createName(path: String): String = {

    val regexEnds = """\{[a-zA-Z0-9,]+\}$""".r
    val regex = """\{[a-zA-Z0-9,]+\}""".r

    val urlNew = regexEnds.findFirstIn(path) match {
      case Some(v) => "one" + regex.replaceAllIn(path, "#")
      case None => regex.replaceAllIn(path, "#")
    }

    def singularize(list: List[String], remove: Boolean): String = list match {
      case head :: tail if remove && head.endsWith("s") => singularize(tail, false) + head.substring(0, head.length() - 1).capitalize
      case "#" :: tail => singularize(tail, true)
      case Nil => ""
      case head :: tail => singularize(tail, false) + head.capitalize
    }

    singularize(urlNew.split("/").toList.reverse, false)

  }

  /**
   * Looks for ids in path and sets them as parameters
   */
  private def addIdToQuery() {
    val regex = """\{[a-zA-Z0-9,]+\}""".r
    regex.findAllIn(this.path).foreach {
      id => {
        val getId = id.replace("{", "").replace("}", "")

        if (getId.equals("eventId") || getId.equals("notificationId") || getId.equals("externalIdName") || getId.equals("externalId"))
          query += getId -> ReturnType.STRING
        else
          query += getId -> ReturnType.NUMBER

        // get the name of the object
        val start = this.path.take(this.path.indexOf(id) - 1)

        val fromUrl = start.drop(start.lastIndexOf("/") + 1)
        val name =
          if (fromUrl.endsWith("s")) fromUrl.take(fromUrl.length() - 1)
          else fromUrl

        addDoc(getId, "Id of the " + name + " we are interested in.", DocType.PARAM)
      }
    }
  }

  /**
   * Check the traits that are available for this method
   */
  def setupTraits(traits: Map[String, QueryParameter]) {

    traits.foreach {
      case ("groups", t) =>
        query += "groups" -> ReturnType.NUMBER_LIST
        addDoc("groups", t.getDescription, DocType.PARAM)
      case ("segments", t) =>
        query += "segments" -> ReturnType.NUMBER_LIST
        addDoc("segments", t.getDescription, DocType.PARAM)
      case ("fields", t) =>
        query += "fields" -> ReturnType.STRING_LIST
        addDoc("fields", t.getDescription, DocType.PARAM)
      case ("order", t) =>
        query += "order" -> ReturnType.MAP
        addDoc("order", t.getDescription, DocType.PARAM)
      case (o, t) =>
        query += o -> ReturnType.withName(t.getType.name().toLowerCase)
        addDoc(o, t.getDescription, DocType.PARAM)
    }
  }


  /**
   * Setup the body variable.
   * @param bdmap map of mime types
   */
  def setupBody(bdmap: scala.collection.mutable.Map[String, MimeType]) {

    if (!bdmap.isEmpty) {

      val appjson = bdmap("application/json")

      if (appjson != null) {

        val example = appjson.getExample

        /** Just to show that we use body */
        query += "body" -> ReturnType.STRING

        val schema = appjson.getSchema
        body = Some((example, schema))
      }

    }
  }

  /**
   * Setup the possible response
   * @param methodResponse map of responses
   */
  def setupResponses(methodResponse: scala.collection.mutable.Map[String, Response]) {

    if (!methodResponse.isEmpty) {

      methodResponse.foreach {
        tpl =>
          val code = tpl._1.toInt
          val mbody = Option(tpl._2.getBody.get("application/json"))

          if (mbody.isDefined) {
            val example = mbody.get.getExample
            val schema = mbody.get.getSchema
            responses = (code, example, schema) :: responses
          }

      }

    }
  }

  /**
   * Regular toString method
   * @return string representation of the method
   */
  override def toString = name

}