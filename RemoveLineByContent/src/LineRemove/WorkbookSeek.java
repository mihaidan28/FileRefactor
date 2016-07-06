package LineRemove;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WorkbookSeek {
	Workbook workbook = new HSSFWorkbook();
	String pathName;

	public WorkbookSeek(String pathName) {
		this.pathName = pathName;
		InputStream input = null;
		try {
			input = new FileInputStream(this.pathName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			workbook = WorkbookFactory.create(input);
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String ContentSearch(String fileContent) throws IOException {

		for (Row row : workbook.getSheetAt(0)) {
			Cell cell = row.getCell(2);
			String targetToken = cell.getStringCellValue();
			fileContent = searchAndRemove(fileContent, targetToken);
		}
		return fileContent;
	}

	private String searchAndRemove(String fileContent, String targetToken) {
		String newFile = fileContent;
		String replacement = "";
		Pattern pattern = Pattern.compile(targetToken + "s*\\(s*.*s*\\)s*,(s*+)");
		Matcher matcher = pattern.matcher(fileContent);
		while (matcher.find() == true) {
			String stringToReplace = matcher.group(0);
			System.out.println(stringToReplace);
			newFile = newFile.replace(stringToReplace, replacement);
			if(newFile.equals(fileContent)) {
				System.out.println("Error! " + replacement + " - " + stringToReplace);
			}
		}
		return newFile;
	}
}
