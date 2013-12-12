package analyser

import org.raml.model.Raml
import org.raml.parser.visitor.RamlDocumentBuilder
import collection.JavaConverters._
import org.raml.model.Resource
import org.raml.model.ActionType
import org.raml.model.SecurityReference

class Package(
	val baseUri: String,
	val mediaType: String,
	val securedBy: SecurityReference
) {

	private var _docs: Map[String, String] = Map()
	private var _clazzes: List[Clazz] = List()

	def docs = _docs
	def clazzes = _clazzes

	def addDoc(name: String, description: String) {
		_docs += (name -> description)
	}

	def addClazz(clazz: Clazz) {
		_clazzes = clazz :: clazzes

	}

}