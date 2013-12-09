package composer

import org.fusesource.scalate.TemplateEngine
import gensdk.Package
import gensdk.Clazz
import java.io.File
import gensdk.Method

/** Should it be abstract?**/
abstract class SDKGenerator{

	val engine = new TemplateEngine
	
	def generate(pack: Package, lang : String) {
		
		val dir = new File(".\\generated\\"+ lang);
    
		// attempt to create the directory here
		val successful = dir.mkdir();
		
		pack.getClazzes.foreach(generateClass)
	}
	
	def generateClass(clazz : Clazz)

	def generateMethod(method: Method)
}