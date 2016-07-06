package FileSearch;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class MainClassSearch {

	public static void main(String[] args) throws IOException {
		String javaPath = "D:\\Facultate 2\\Test";
		String javaExtension = "java";
		String xhtmlPath = "D:\\Facultate 2\\Test";
		String xhtmlExtension = "xhtml";

		// Cautarea in directorul javaPath fisiere cu extensia javaExtension,
		// cautarea target-ului in fiecare fisier
		// si salvarea acestora (javaPath si target) intr-un fisier .xls (txt)

		String target = "@ConfigurableAttribute\\s*\\(.*\\s*(preference\\s*=\\s*Preference\\.(\\s*\\w+))\\s*[,\\)]";
		// String target =
		// "(@ConfigurableAttribute\\s*\\()\\s*(preference\\s*=\\s*)(Preference\\.)(\\w+)(,)";
		ListFile searchJavaFiles = new ListFile(javaPath, javaExtension);
		List<File> javaFiles = searchJavaFiles.searchFile();
		if (!javaFiles.isEmpty()) {
			ContentSearch contentSearch = new ContentSearch(target);
			List<PathAndToken> foundTokens = contentSearch.search(javaFiles);
			Calendar cal = Calendar.getInstance();
			DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH mm ss");
			// PrintWriter out = new
			// PrintWriter(dateFormat.format(cal.getTime()) + " Tokens
			// Output.txt");
			if (foundTokens.size() != 0) {
				for (PathAndToken token : foundTokens) {
					System.out.println(
							token.getDirectory() + ", " + token.getFirstToken() + ", " + token.getSecondToken());
					// out.println(token.getDirectory() + ", " +
					// token.getFirstToken() + ", " + token.getSecondToken());
				}
				// out.close();
				String outputFilePath = dateFormat.format(cal.getTime()) + " Tokens Output.xls";
				WriteToWorkbook writeToWorkbook = new WriteToWorkbook(outputFilePath);
				writeToWorkbook.createWorkbookFromPathAndTokenList(foundTokens);
			} else {
				System.out.println("No tokens found!");
			}
		} else {
			System.out.println("No ." + javaExtension + " files found in " + javaPath + "!");
		}

		// Cautarea in directorul xhtmlPath fisiere cu extensia xhtmlExtension
		// si salvarea acestora intr-un fisier .xls (si/sau txt)

		ListFile searchXhtmlFiles = new ListFile(xhtmlPath, xhtmlExtension);
		List<File> xhtmlFiles = searchXhtmlFiles.searchFile();
		if (!xhtmlFiles.isEmpty()) {
			Calendar cal = Calendar.getInstance();
			DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH mm ss");
			// PrintWriter out = new
			// PrintWriter(dateFormat.format(cal.getTime()) + " " +
			// xhtmlExtension + ".txt");
			for (File xhtmlFile : xhtmlFiles) {
				System.out.println(xhtmlFile.getCanonicalPath());
				// out.println(xhtmlFile.getCanonicalPath());
			}
			// out.close();
			String outputFilePath = dateFormat.format(cal.getTime()) + " " + xhtmlExtension + ".xls";
			WriteToWorkbook writeToWorkbook = new WriteToWorkbook(outputFilePath);
			writeToWorkbook.createWorkbookFromFileList(xhtmlFiles);
		} else

		{
			System.out.println("No ." + xhtmlExtension + " files found in " + xhtmlPath + "!");
		}
	}

}
