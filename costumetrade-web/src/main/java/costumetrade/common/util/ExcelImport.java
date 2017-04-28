package costumetrade.common.util;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelImport {
	public static List<Map<String, Object>> readExcel(FileInputStream excelFile,int rowNum) throws Exception {
		List<Map<String,Object>> resultList = null;
		try {
			Workbook workbook = WorkbookFactory.create(excelFile);
			if(workbook instanceof XSSFWorkbook){
				resultList = ExcelImport.getXSSFResource(workbook,rowNum);
			}else{
				resultList = ExcelImport.getHSSFResource(workbook,rowNum);
			}
		} catch (Exception e) {
				throw new Exception("解析Excel异常",e);
		}
		return resultList;
	}

	private static List<Map<String, Object>> getHSSFResource(Workbook workbook,int rowNum) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		Map<String, Object> resourceMap = null;
		HSSFSheet sheet = null;
		Row row = null;
		Cell cell = null;
		if(null != workbook){
			sheet = (HSSFSheet) workbook.getSheetAt(0);
		}
		for (int i = sheet.getFirstRowNum()+rowNum; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			if(row==null){
			    break;
			}
			resourceMap = new HashMap<String, Object>();
			for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
				cell = row.getCell(j);
			    if(null == cell) {
                    resourceMap.put(ExcelColumns.getName(j), "");
                    continue;
                }
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_NUMERIC:
                        resourceMap.put(ExcelColumns.getName(j), cell.getNumericCellValue());
                        break;
                    case Cell.CELL_TYPE_STRING:
                        resourceMap.put(ExcelColumns.getName(j), cell.getStringCellValue().trim());
                        break;
                    case Cell.CELL_TYPE_BLANK:
                        resourceMap.put(ExcelColumns.getName(j), "");
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        resourceMap.put(ExcelColumns.getName(j), cell.getBooleanCellValue());
                        break;
                    case Cell.CELL_TYPE_ERROR:
                        break;
                    default:
                        break;
                }
			}
			resultList.add(resourceMap);
		}
		return resultList;
	}

	private static List<Map<String, Object>> getXSSFResource(Workbook workbook,int rowNum) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		Map<String, Object> resourceMap = null;
		XSSFSheet sheet = null;
		Row row = null;
		Cell cell = null;
		if(null != workbook){
			sheet = (XSSFSheet) workbook.getSheetAt(0);
		}
		for (int i = sheet.getFirstRowNum()+rowNum; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			if(row==null){
                break;
            }
			resourceMap = new HashMap<String, Object>();
			for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
				cell = row.getCell(j);
				
			    if(null == cell) {
                    resourceMap.put(ExcelColumns.getName(j), "");
                    continue;
                }
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_NUMERIC:
                        resourceMap.put(ExcelColumns.getName(j), cell.getNumericCellValue());
                        break;
                    case Cell.CELL_TYPE_STRING:
                        resourceMap.put(ExcelColumns.getName(j), cell.getStringCellValue().trim());
                        break;
                    case Cell.CELL_TYPE_BLANK:
                        resourceMap.put(ExcelColumns.getName(j), "");
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        resourceMap.put(ExcelColumns.getName(j), cell.getBooleanCellValue());
                        break;
                    case Cell.CELL_TYPE_ERROR:
                        break;
                    default:
                        break;
                }
				
			}
			resultList.add(resourceMap);
		}
		return resultList;
	}

	public static void setEntity(Object entity,String name,Object value) throws Exception{
		try {
			Field field = entity.getClass().getDeclaredField(name);
			field.setAccessible(true);
			if(field.getGenericType().toString().equals("class java.lang.String")){
				field.set(entity, String.valueOf(value));
			}else if (field.getGenericType().toString().equals("class java.lang.Integer")) {
				if(value.getClass().toString().equals("class java.lang.Double")){
					field.set(entity, new Double(value.toString()).intValue());
				}else {
					field.set(entity, Integer.valueOf(value.toString()));
				}
			}else if (field.getGenericType().toString().equals("class java.util.Date")) {
				if(value.toString().length() == 10){
					field.set(entity, new SimpleDateFormat("yyyy-MM-dd").parse(value.toString()));
				}else {
					field.set(entity, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value.toString()));
				}
			}else if(field.getGenericType().toString().equals("class java.math.BigDecimal")){
				field.set(entity, new BigDecimal(value.toString()));
			}else {
				field.set(entity, value);
			}
		} catch (Exception e) {
		}
		
	}
	
	public static void setEntity(Object entity,Object resource) {
			Field[] fields = entity.getClass().getDeclaredFields();
			for(Field field : fields){
				try {
					field.setAccessible(true);
					Field resourField = resource.getClass().getDeclaredField(field.getName());
					resourField.setAccessible(true);
					field.set(entity, resourField.get(resource));
				} catch (Exception e) {
					continue;
				} 
			}
	}
}
