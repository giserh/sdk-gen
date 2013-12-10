package generator

import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.channels.FileChannel
import java.io.File

class CodeWorkerException(message: String = null, cause: Throwable = null) extends Exception(message, cause)

class CodeWorker(context: Context) {
	/**all operations need to be done */

	val generator = context.generator
	val resourcePath = context.resourcePath
	val raml = context.raml
	var tmpDir: File = null
	var outputDir: File = null

	/** Prepare temporary directory**/
	prepareTmp()

	/**Invoke adapter*/
	println(tmpDir)
	generator.generate(raml, resourcePath, context.baseUrl, tmpDir.getAbsolutePath())

	/**Copy tmp file to new output location @TODO*/
	copyDir(tmpDir, outputDir)

	/**Remove old files */
	removeTmp()

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
			case e => e.printStackTrace()
		} finally {
			sourceChannel.close();
			destChannel.close();
		}
	}
	private def prepareTmp(): Unit = {

		/**Temporary directory name*/
		var tmpFolderName = "package_" + context.hashCode()

		/**Create temporary folder*/
		new File(context.tempDirectory).mkdir()

		var tmpFolder = context.tempDirectory + tmpFolderName;
		this.tmpDir= new File(tmpFolder);
		if (tmpDir.mkdir()) {
			println(s"Created tmp directory in ${tmpDir.getAbsolutePath()}");
		} else {
			throw new CodeWorkerException(s"Failed to create temporary folder in: $tmpFolder ")
		}

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
	private def prepareOutput(): Unit = {
	}
	private def removeTmp(): Unit = tmpDir.delete() match {
		case true => println("Deleted tmp directory")
		case false => throw new CodeWorkerException(s"Failed trying to delete temporary folder")
	}

}
