package FileReplace;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SearchAndReplace {
	String pathName, targetToken, replacement, actionResult = null;

	public SearchAndReplace(String pathName, String targetToken, String replacement) {
		this.pathName = pathName;
		this.targetToken = targetToken;
		this.replacement = replacement;
	}

	public String javaReplace() throws IOException {
		String content = null;
		content = readFile(pathName, Charset.defaultCharset());
		String modifiedContent = content.replaceFirst(targetToken, replacement);
		if (!content.equals(modifiedContent)) {
			outPrint(modifiedContent);
			System.out.println(targetToken + " found in file " + pathName + " was replaced with " + replacement);
			actionResult = "Completed!";
		} else {
			System.out.println(targetToken + " was not found in file " + pathName);
			actionResult = "Token not found!";
		}
		return actionResult;
	}

	public void xhtmlReplace() throws IOException {
		String content = null;
		content = readFile(pathName, Charset.defaultCharset());
		String modifiedContent = content.replaceAll(targetToken, replacement);
		if (!content.equals(modifiedContent)) {
			outPrint(modifiedContent);
			System.out.println(targetToken + " found in file " + pathName + " was deleted!");
		}
	}

	private void outPrint(String content) throws FileNotFoundException {
		PrintWriter out = new PrintWriter(pathName);
		out.println(content);
		out.close();

	}

	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

}
