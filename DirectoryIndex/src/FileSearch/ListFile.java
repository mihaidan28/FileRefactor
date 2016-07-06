package FileSearch;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class ListFile {
	String path, extension;

	public ListFile(String path, String extension) {
		this.path = path;
		this.extension = extension;
	}

	public List<File> searchFile() throws IOException {
		File dir = new File(path);
		String[] extensions = new String[] { extension };
		System.out.println("Getting all ." + extension + " files in " + dir.getCanonicalPath()
				+ " including those in subdirectories");
		List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true);
		return files;
	}
}
