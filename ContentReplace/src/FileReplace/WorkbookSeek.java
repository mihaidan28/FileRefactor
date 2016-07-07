package FileReplace;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
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

	public void javaFileSearch() throws IOException {
		String result = null;
		CellStyle cellStyleRed = workbook.createCellStyle();
		CellStyle cellStyleGreen = workbook.createCellStyle();
		cellStyleRed.setFillForegroundColor(HSSFColor.RED.index);
		cellStyleGreen.setFillForegroundColor(HSSFColor.GREEN.index);
		cellStyleRed.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cellStyleGreen.setFillPattern(CellStyle.SOLID_FOREGROUND);
		for (Row row : workbook.getSheetAt(0)) {
			Cell cell0 = row.getCell(0);
			Cell cell1 = row.getCell(1);
			Cell cell3 = row.getCell(3);
			String filePath = cell0.getStringCellValue();
			String targetToken = cell1.getStringCellValue();
			String replacement = cell3.getStringCellValue();
			SearchAndReplace search = new SearchAndReplace(filePath, targetToken, replacement);
			result = search.javaReplace();
			Cell cell4 = row.createCell(4);
			cell4.setCellValue(result);
			if (result.equals("Completed!")) {
				cell4.setCellStyle(cellStyleGreen);
			} else {
				cell4.setCellStyle(cellStyleRed);

			}
		}
		workbook.getSheetAt(0).autoSizeColumn(3);
		workbook.getSheetAt(0).autoSizeColumn(4);
		write();
	}

	public void xhtmlFileSearch(WorkbookSeek javaWorkbook) throws IOException {

		for (Row row : workbook.getSheetAt(0)) {
			Cell xhtmlCell = row.getCell(0);
			String filePath = xhtmlCell.getStringCellValue();
			for (Row javaWorkbookRow : javaWorkbook.workbook.getSheetAt(0)) {
				Cell javaWorkbookCell = javaWorkbookRow.getCell(2);
				String targetToken = javaWorkbookCell.getStringCellValue();
				SearchAndReplace search = new SearchAndReplace(filePath, targetToken, "");
				search.xhtmlReplace();
			}
		}
	}

	public void write() throws IOException {
		FileOutputStream fileOut = new FileOutputStream(pathName);
		workbook.write(fileOut);
		fileOut.close();
	}

}
