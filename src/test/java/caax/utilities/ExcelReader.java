package caax.utilities;



import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelReader {

	public String path;
	private FileInputStream fis;
	private XSSFWorkbook workBook;
	private XSSFSheet sheet;
	private XSSFRow row;
	private XSSFCell cell;
	DataFormatter formatter = new DataFormatter();


	public ExcelReader(String path) {
		this.path = path;
		try {
			fis = new FileInputStream(path);
			workBook = new XSSFWorkbook(fis); // Making object of workbook with input stream as argument
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getCellData(String sheetName, String colName, int rowNum) {
		try {
			int col_mum = 0;
			int sheetIndex = workBook.getSheetIndex(sheetName); // Index of sheet to be worked upon.
			sheet = workBook.getSheetAt(sheetIndex);           // Get Sheet.
			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {       // This will get you how many column excel sheet have
				if (row.getCell(i).getStringCellValue().equals(colName)) {
					int col_num = i;
				}
			}
			row = sheet.getRow(rowNum - 1);
			cell = row.getCell(col_mum);
			if (cell.getCellType() == CellType.STRING) {
				return cell.getStringCellValue();
			} else if (cell.getCellType() == CellType.NUMERIC) {
				return String.valueOf(cell.getStringCellValue());
			} else if (cell.getCellType() == CellType.BOOLEAN) {
				return String.valueOf(cell.getStringCellValue());
			} else if (cell.getCellType() == CellType.BLANK) {
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getCellData(String sheetName, int colNum, int rowNum) {
		try {
			int sheetIndex = workBook.getSheetIndex(sheetName); // Index of sheet to be worked upon.
			sheet = workBook.getSheetAt(sheetIndex);           // Get Sheet.
			row = sheet.getRow(0);
			row = sheet.getRow(rowNum - 1);
			cell = row.getCell(colNum);
			if (cell.getCellType() == CellType.STRING) {
				return cell.getStringCellValue();
			} else if (cell.getCellType() == CellType.NUMERIC) {
				return formatter.formatCellValue(cell);
			} else if (cell.getCellType() == CellType.BOOLEAN) {
				return String.valueOf(cell.getStringCellValue());
			} else if (cell.getCellType() == CellType.BLANK) {
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getRowCount(String sheetName) {
		try{
			int index = workBook.getSheetIndex(sheetName);
			if (index == -1) {
				return 0;
			} else {
				sheet = workBook.getSheetAt(index);
				int number = sheet.getLastRowNum() + 1;
				return number;
			}

		} catch(Exception e){
			e.printStackTrace();

		}
		return 0;
	}

	public int getColumnCount(String sheetName) {
		try {
			int index = workBook.getSheetIndex(sheetName);
			if (index == -1) {
				return 0;
			} else {
				sheet = workBook.getSheet(sheetName);
				row = sheet.getRow(0);
				return row.getLastCellNum();
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return 0;
	}



}