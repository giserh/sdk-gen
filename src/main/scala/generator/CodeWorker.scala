package generator

import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.channels.FileChannel
import java.io.File
import java.util.Date

class CodeWorkerException(message: String = null, cause: Throwable = null) extends Exception(message, cause)

/**
 * Class used to setup all the needed operations.
 */
class CodeWorker(context: Context) {

	/**All operations need to be done in context of */
	val generator = context.generator
	val resourcePath = context.resourcePath
	val raml = context.raml
	var tmpDir: File = null
	var outputDir: File = null

	def run() {
		
		/** Prepare temporary and output directories */
		prepareTmp()
		prepareOutput()

		/**Invoke adapter*/
		generator.generate(raml, resourcePath, context.baseUrl, tmpDir.getAbsolutePath())

		/**Copy tmp file to new output location*/
		copyDir(tmpDir, outputDir)
		
		if (context.includePath != null)
			copyDir(new File(context.includePath), outputDir)
		/**Remove old files */
		removeTmp()
	}
	
	private def copyDir(source: File, dest: File): Unit = {

		val files = source.listFiles();

		for { file <- files } {
			if (file.isDirectory()) {
				copyDir(file, new File(dest, file.getName()));
			} else {
				copyFile(file, new File(dest, file.getName()));
			}
		}
	}

	private def copyFile(source: File, dest: File): Unit = {
		var sourceChannel: FileChannel = null;
		var destChannel: FileChannel = null;
		try {
			sourceChannel = new FileInputStream(source).getChannel();
			destChannel = new FileOutputStream(dest).getChannel();
			destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
		} catch {
			case e: Throwable => e.printStackTrace()
		} finally {
			sourceChannel.close();
			destChannel.close();
		}
	}

	private def prepareTmp(): Unit = {

		/**Temporary directory name*/
		var tmpFolderName = "package_" + new Date().getTime()

		/**Create temporary folder*/
		new File(context.tempDirectory).mkdir()

		var tmpFolder = context.tempDirectory + tmpFolderName;
		this.tmpDir = new File(tmpFolder);
		if (tmpDir.mkdir()) {
			println(s"Created tmp directory in ${tmpDir.getAbsolutePath()}");
		} else {
			throw new CodeWorkerException(s"Failed to create temporary folder in: $tmpFolder ")
		}

	}
	private def prepareOutput(): Unit = {

		/**Prepare output directory*/
		this.outputDir = new File(context.outputDirectory)
		if (!outputDir.exists()) {
			if (outputDir.mkdir()) {
				println(s"Output directory has been created in ${outputDir.getAbsolutePath()}");
			} else {
				throw new CodeWorkerException(s"Failed trying to create output folder in: ${context.outputDirectory} ")
			}
		} else {
			println(s"Output directory is ${outputDir.getAbsolutePath()}");
		}
	}

	private def deleteDir(toDelete: File): Boolean = {
		val files = toDelete.listFiles();
		var deleted = true

		for { file <- files } {
			if (file.isDirectory()) {
				val ne = deleteDir(file)
				if (!ne) deleted = false

			} else {
				val ne = deleteFile(file)
				if (!ne) deleted = false
			}
		}
		if (!toDelete.delete()) deleted = false
		deleted
	}

	private def deleteFile(toDelete: File): Boolean = {
		try {
			toDelete.delete()
		} catch {
			case e: Throwable => /* @TODO Should be logged*/ false
		}
	}
	private def removeTmp(): Unit = deleteDir(tmpDir) match {
		case true => println("Deleted tmp directory")
		case false => throw new CodeWorkerException(s"Failed trying to delete temporary folder")
	}

}
