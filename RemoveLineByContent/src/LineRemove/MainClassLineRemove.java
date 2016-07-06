package LineRemove;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainClassLineRemove {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String javaPath = "D:\\Facultate 2\\Test\\Preference.java";
		String xlsPath = "D:\\Facultate 2\\Practica 2016\\Project3\\DirectoryIndex\\06.07.2016 09 23 44 Tokens Output.xls";
		WorkbookSeek workbookSeek = new WorkbookSeek(xlsPath);
		String fileContent = null;
		fileContent = readFile(javaPath, Charset.defaultCharset());
		fileContent = workbookSeek.ContentSearch(fileContent);
		outPrint(fileContent, javaPath);
	}

	static void outPrint(String content, String pathName) throws FileNotFoundException {
		PrintWriter out = new PrintWriter(pathName);
		out.println(content);
		out.close();

	}

	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
}
