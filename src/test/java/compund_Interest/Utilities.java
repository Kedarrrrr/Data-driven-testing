package compund_Interest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utilities {

	public static int rowNo;
	public static int colNo;
	public String file;
	public static XSSFWorkbook wb;
	public static String sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFSheet ws;
	
	public static int getRowNo(String file, String sheet) throws IOException {
		
		fi = new FileInputStream(file);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(sheet);
		rowNo=ws.getLastRowNum();
		fi.close();
		
		return rowNo;
	}
	public static int getColNo(String file, String sheet, int rowNO) throws IOException {
		
		fi=new FileInputStream(file);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(sheet);
		colNo=ws.getRow(1).getLastCellNum();
		
		return colNo;
		
	}
	
	public static XSSFRow getRow(String file, String sheet, int rowNo) throws IOException {
		fi=new FileInputStream(file);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(sheet);
		row=ws.getRow(rowNo);
		return row;
	}
	
	public static XSSFCell getCell(String file, String sheet, int rowNo, int colNo) throws IOException {
		fi=new FileInputStream(file);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(sheet);
		row=ws.getRow(rowNo);
		cell = row.getCell(colNo);
		
		return cell;
	}
	public static void setCell(String file, String sheet, int rowNo, int colNo, String value) throws IOException {
		
		fi=new FileInputStream(file);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(sheet);
		cell=ws.getRow(rowNo).getCell(colNo);
		cell.setCellValue(value);
		fo=new FileOutputStream(file);
		wb.write(fo);
	}
	public static void fillGreenColor(String file, String sheet, int rowNo, int colNo) throws IOException {
		
		fi=new FileInputStream(file);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(sheet);
		row=ws.getRow(rowNo);
		cell=ws.getRow(rowNo).getCell(colNo);
		
		fo=new FileOutputStream(file);
		
		style=wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		
		
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
	public static void fillRedColor(String file, String sheet, int rowNo, int colNo) throws IOException {
		
		fi=new FileInputStream(file);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(sheet);
		row=ws.getRow(rowNo);
		cell=ws.getRow(rowNo).getCell(colNo);
		fo=new FileOutputStream(file);
		style=wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		
		
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
}
