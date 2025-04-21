package GenericUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excel_Utility {

	public String FetchDataFromExcelFile(String sheetname, int rowindex, int cellindex)
			throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetname);
		Row r = sh.getRow(rowindex);
		Cell c = r.getCell(cellindex);
		String data = c.toString();
		wb.close();
		return data;
	}

	// public void FetchMultipleDataFromExcel() {
	// FileInputStream fis = new
	// FileInputStream("./src/test/resources/TestData.xlsx");
	// Workbook wb = WorkbookFactory.create(fis);
	// Sheet sh = wb.getSheet(sheetname);
	// String data = null;
	// for (int i = 0; i <= sh.getLastRowNum(); i++) {
	// for (int j = 0; j < sh.getRow(i).getLastCellNum(); j++) {
	// data = sh.getRow(i).getCell(j).toString();

	// }
	// }

	public void WriteBackDataToExcel(String sheetname, int rowindex, int cellindex,String data)
			throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetname);
		Row r = sh.getRow(rowindex);
		Cell c = r.getCell(cellindex);
		c.setCellValue(data);

		FileOutputStream fos = new FileOutputStream("./src/test/resources/TestData.xlsx");
		wb.write(fos);
		wb.close();

	}
}