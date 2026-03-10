package compund_Interest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

public class Utilities {

    public static int getRowCount(String file, String sheet) throws IOException {

        FileInputStream fi = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(fi);
        XSSFSheet ws = wb.getSheet(sheet);

        int rows = ws.getLastRowNum();

        wb.close();
        fi.close();

        return rows;
    }

    public static String getCellData(String file, String sheet, int row, int col) throws IOException {

        FileInputStream fi = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(fi);
        XSSFSheet ws = wb.getSheet(sheet);

        String data = ws.getRow(row).getCell(col).toString();

        wb.close();
        fi.close();

        return data;
    }

    public static void setCellData(String file, String sheet, int row, int col, String value) throws IOException {

        FileInputStream fi = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(fi);
        XSSFSheet ws = wb.getSheet(sheet);

        XSSFRow r = ws.getRow(row);
        if (r == null)
            r = ws.createRow(row);

        XSSFCell cell = r.getCell(col);
        if (cell == null)
            cell = r.createCell(col);

        cell.setCellValue(value);

        FileOutputStream fo = new FileOutputStream(file);
        wb.write(fo);

        wb.close();
        fi.close();
        fo.close();
    }

    public static void fillGreenColor(String file, String sheet, int row, int col) throws IOException {

        FileInputStream fi = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(fi);
        XSSFSheet ws = wb.getSheet(sheet);

        XSSFCell cell = ws.getRow(row).getCell(col);

        CellStyle style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);

        FileOutputStream fo = new FileOutputStream(file);
        wb.write(fo);

        wb.close();
        fi.close();
        fo.close();
    }

    public static void fillRedColor(String file, String sheet, int row, int col) throws IOException {

        FileInputStream fi = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(fi);
        XSSFSheet ws = wb.getSheet(sheet);

        XSSFCell cell = ws.getRow(row).getCell(col);

        CellStyle style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);

        FileOutputStream fo = new FileOutputStream(file);
        wb.write(fo);

        wb.close();
        fi.close();
        fo.close();
    }
}