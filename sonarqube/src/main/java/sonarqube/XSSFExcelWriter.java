package sonarqube;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XSSFExcelWriter {
	private static final Logger LOG = LoggerFactory.getLogger(XSSFExcelWriter.class);

	private XSSFWorkbook workbook;

	private int sheetIndex = 0;

	private List<Column> columns = new ArrayList<Column>();

	private XSSFCellStyle cellStyle;

	private static final String EMPTY_STRING = "";

	public XSSFExcelWriter() {
		this.workbook = new XSSFWorkbook();
	}

	public XSSFCellStyle getCellStyle() {
		if (cellStyle == null)
			cellStyle = this.createCellStyle();
		return cellStyle;
	}

	public void setCellStyle(XSSFCellStyle cellStyle) {
		this.cellStyle = cellStyle;
	}

	public XSSFWorkbook getWorkbook() {
		return workbook;
	}

	public int getSheetIndex() {
		return sheetIndex;
	}

	public void setSheetIndex(int sheetIndex) {
		this.sheetIndex = sheetIndex;
	}

	public void setWorkbook(XSSFWorkbook workbook) {
		this.workbook = workbook;
	}

	public void createSheet(String name) {
		XSSFSheet sheet = this.workbook.createSheet(name);
		this.sheetIndex = workbook.getSheetIndex(sheet);
	}

	public XSSFRow addRow(int rownum) {
		XSSFSheet sheet = getSheetAt(getSheetIndex());
		return sheet.createRow(rownum);
	}

	public XSSFCell addCell(int rownum, int column, XSSFCellStyle cellStyle) {
		XSSFCell cell = getRow(rownum).createCell(column);
		if (cellStyle != null)
			cell.setCellStyle(cellStyle);

		return cell;
	}

	public XSSFSheet getSheetAt(int sheetIndex) {
		return workbook.getSheetAt(sheetIndex);
	}

	public int getFirstRowNum() {
		return getSheetAt(getSheetIndex()).getFirstRowNum();
	}

	public XSSFRow getRow(int rownum) {
		return getSheetAt(getSheetIndex()).getRow(rownum);
	}

	private XSSFCellStyle createCellStyle() {
		XSSFCellStyle style = workbook.createCellStyle();
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.index);
		style.setBorderLeft(BorderStyle.THIN);
		style.setLeftBorderColor(IndexedColors.GREEN.index);
		style.setBorderRight(BorderStyle.THIN);
		style.setRightBorderColor(IndexedColors.BLUE.index);
		style.setBorderTop(BorderStyle.THIN);
		style.setTopBorderColor(IndexedColors.BLACK.index);
		return style;
	}

	public void setHeaderToFirstRow() {
		int rowNum = getFirstRowNum();
		XSSFRow row = addRow(rowNum);
		XSSFCellStyle cellStyle = createCellStyle();
		cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		cellStyle.setFont(font);
		for (Column c : columns) {
			XSSFCell cell = getRow(row.getRowNum()).createCell(c.columnIndex);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(c.name);
		}
	}

	public int getLastRowNum() {
		return getSheetAt(getSheetIndex()).getLastRowNum();
	}

	public void setData(List<Map<String, Object>> items) {
		if (items != null) {
			for (Map<String, Object> item : items) {
				setDataToRow(item);
			}
		}
	}

	public void setDataToRow(Map<String, Object> data) {
		XSSFRow row = addRow(getLastRowNum() + 1);
		for (Column c : columns) {
			XSSFCell cell = addCell(row.getRowNum(), c.columnIndex, getCellStyle());
			Object value = data.get(c.valueKey);
			cell.setCellType(CellType.STRING);
			if (value != null)
				cell.setCellValue(value.toString().trim());
			else
				cell.setCellValue(EMPTY_STRING);
		}
	}

	public void write(File file) {
		FileOutputStream fs = null;
		try {
			fs = new FileOutputStream(file);
			workbook.write(fs);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		} finally {
			if (fs != null)
				try {
					fs.close();
				} catch (IOException e) {
					LOG.error(e.getMessage(), e);
				}
		}
	}

	public void write(OutputStream output) {
		try {
			workbook.write(output);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		} finally {
			if (output != null)
				try {
					output.close();
				} catch (IOException e) {
					LOG.error(e.getMessage(), e);
				}
		}
	}

	public void setColumn(int idx, String name, String dataKey) {
		this.columns.add(new Column(idx, name, dataKey));
	}

	public void setColumn(int idx, String name, String dataKey, int width) {
		this.columns.add(new Column(idx, name, dataKey, width));
	}

	public void setColumnsWidth() {
		for (Column c : columns) {
			if (c.width > 0) {
				getSheetAt(getSheetIndex()).setColumnWidth(c.columnIndex, c.width);
			}
		}
	}

	public static class Column {
		String name;
		String valueKey;
		int columnIndex;
		int width = 0;

		public Column(int columnIndex, String name, String valueKey) {
			super();
			this.columnIndex = columnIndex;
			this.name = name;
			this.valueKey = valueKey;
		}

		public Column(int columnIndex, String name, String valueKey, int width) {
			super();
			this.columnIndex = columnIndex;
			this.name = name;
			this.valueKey = valueKey;
			this.width = width;
		}

	}
}
