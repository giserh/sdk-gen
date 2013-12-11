package analyser

import org.raml.model.Raml
import org.raml.parser.visitor.RamlDocumentBuilder
import collection.JavaConverters._
import org.raml.model.Resource
import org.raml.model.ActionType
import org.raml.model.SecurityReference

class Package(
	private var _baseUri: String,
	private var _mediaType: String,
	private var _securedBy: SecurityReference
	) {

	private var _docs: Map[String, String] = Map()
	private var _clazzes: List[Clazz] = List()

	def docs = _docs
	def clazzes = _clazzes
	
	def baseUri = _baseUri
	def mediaType = _mediaType
	def securedBy = _securedBy

	def addDoc(name: String, description: String) {
		_docs += (name -> description)
	}

	def addClazz(clazz: Clazz) {
		_clazzes = clazz :: clazzes
	}

}