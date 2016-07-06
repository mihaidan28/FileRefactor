package FileSearch;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class WriteToWorkbook {

	String outputFilePath;
	Workbook workbook = new HSSFWorkbook();

	public WriteToWorkbook(String outputFilePath) {
		this.outputFilePath = outputFilePath;
	}

	public void createWorkbookFromPathAndTokenList(List<PathAndToken> pathAndToken) throws IOException {
		Sheet sheet = workbook.createSheet();
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFillForegroundColor(HSSFColor.RED.index);
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		for (PathAndToken newPathAndToken : pathAndToken) {
			Row row = sheet.createRow(pathAndToken.indexOf(newPathAndToken));
			Cell cell1 = row.createCell(0);
			Cell cell2 = row.createCell(1);
			Cell cell3 = row.createCell(2);
			cell1.setCellValue(newPathAndToken.getDirectory());
			cell2.setCellValue(newPathAndToken.getFirstToken());
			cell3.setCellValue(newPathAndToken.getSecondToken());
		}
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		int rows = sheet.getLastRowNum();
		for (int i = 0; i < rows; i++) {
			Cell cell1 = sheet.getRow(i).getCell(1);
			Cell cell2 = sheet.getRow(i + 1).getCell(1);
			if (cell1.getStringCellValue().equals(cell2.getStringCellValue()) == true) {
				cell1.setCellStyle(cellStyle);
				cell2.setCellStyle(cellStyle);
			}
		}
		write();

	}

	public void createWorkbookFromFileList(List<File> filePath) throws IOException {
		Sheet sheet = workbook.createSheet();
		for (File file : filePath) {
			Row row = sheet.createRow(filePath.indexOf(file));
			Cell cell1 = row.createCell(0);
			cell1.setCellValue(file.getCanonicalPath());
		}
		sheet.autoSizeColumn(0);
		write();
	}

	public void write() throws IOException {
		FileOutputStream fileOut = new FileOutputStream(outputFilePath);
		workbook.write(fileOut);
		fileOut.close();
	}
}
