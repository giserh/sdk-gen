package generator

import org.fusesource.scalate.TemplateEngine
import gensdk.Package
import org.fusesource.scalate.DefaultRenderContext
import java.io.PrintWriter
import gensdk.Clazz
import java.io.File

/** Should it be abstract?**/
trait SDKGenerator {

	def generate(pack : Package)

}