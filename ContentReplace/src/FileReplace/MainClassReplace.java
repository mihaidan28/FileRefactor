package FileReplace;

import java.io.IOException;

public class MainClassReplace {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String javaOutputFilePath = "D:\\Facultate 2\\Practica 2016\\Project3\\DirectoryIndex\\06.07.2016 09 23 44 Tokens Output.xls";
		WorkbookSeek javaWorkbookSeek = new WorkbookSeek(javaOutputFilePath);
		javaWorkbookSeek.javaFileSearch();
		String xhtmlOutputFilePath = "D:\\Facultate 2\\Practica 2016\\Project3\\DirectoryIndex\\06.07.2016 09 23 52 xhtml.xls";
		WorkbookSeek xhtmlWorkbookSeek = new WorkbookSeek(xhtmlOutputFilePath);
		xhtmlWorkbookSeek.xhtmlFileSearch(javaWorkbookSeek);
		
	}

}
