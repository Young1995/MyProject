package org.svtcc.online.management.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 读取excel
 * 
 * @author troyyang
 * 
 */
public class ExcelUtil {
	private static final Log LOG = LogFactory.getLog(ExcelUtil.class);

	/**
	 * 读取excel，返回所有的row
	 * @param filePath
	 * @return
	 */
	@SuppressWarnings("resource")
	public Iterator<Row> readExcel(String filePath) {
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format("读取文件，从：%s", filePath));
		}

		boolean isXLSX = false;
		if (filePath.endsWith("xlsx")) {
			isXLSX = true;
		}

		try {
			InputStream input = new FileInputStream(filePath);
			Workbook wb = null;

			// 根据文件类型创建 workbook
			if (isXLSX) {
				wb = new XSSFWorkbook(input);
			} else {
				wb = new HSSFWorkbook(input);
			}
			Sheet sheet = wb.getSheetAt(0);
			Iterator<Row> rows = sheet.iterator();
			return rows;

		} catch (FileNotFoundException e) {
			LOG.error(e.getStackTrace());
		} catch (IOException e) {
			LOG.error(e.getStackTrace());
		}

		return null;
	}
	
	/**
	 * 根据每行的index读取值
	 * @param row
	 * @param index
	 * @return
	 */
	public static Object getCellValue(Row row, int index) {
		Object value = null;
		Cell cell = row.getCell(index);
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC:
			value = cell.getNumericCellValue();
			break;
		case HSSFCell.CELL_TYPE_STRING:
			value = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			value = cell.getBooleanCellValue();
			break;
		case HSSFCell.CELL_TYPE_FORMULA:
			value = cell.getCellFormula();
			break;
		default:
			break;
		}
		return value;
	}
}
