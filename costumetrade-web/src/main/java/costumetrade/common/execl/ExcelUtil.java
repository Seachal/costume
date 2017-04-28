/*
 * huirong Inc.
 * Copyright (c) 2015 All Rights Reserved.
 * Author     :liyb
 * Create Date:2015年12月14日
 */
package costumetrade.common.execl;

import java.io.OutputStream;
import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * This is Class Description...
 * @author liyb
 * @version ExcelUtil.java,2015年12月14日 下午2:40:54
 */
public class ExcelUtil {

    private static final Log logger = LogFactory.getLog("ExcelUtil");
    
    /**
     * 写excel到response
     * @param workbook
     * @param baos
     */
    public static void writeToResponse(Workbook workbook, OutputStream baos) {
        try {
            if (workbook == null) {
                throw new IllegalArgumentException("workbook不能为空白");
            }
            workbook.write(baos);
            baos.flush();
            baos.close();
        } catch (Exception e) {
            logger.error("writeToFile:" + e.getMessage());
        }
    }
    
    public static void writeExcelDetailRow(Sheet sheet, String[] detailValues, int rowNum) {
        try {
            Row row = sheet.createRow(rowNum);
            if (detailValues == null) {
                detailValues = new String[] { "" };
            }
            if (detailValues != null && detailValues.length > 0) {
                for (int i = 0; i < detailValues.length; i++) {
                    Cell cell = row.createCell((short) i);
                    cell.setCellValue(detailValues[i]);
                }
            }
        } catch (Exception e) {
            logger.error("写excel的DetailRow部分发生异常");
        }
    }

    /**
     */
    public static void setBigDecimal(Cell cell, BigDecimal bigDecimal) {
        if(bigDecimal == null){
            bigDecimal = new BigDecimal(0);
        }
        bigDecimal.setScale(2);
        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
        cell.setCellValue(bigDecimal.doubleValue());
        
    }

    /**
     */
    public static void setInteger(Cell cell, Integer count) {
        if(count==null){
            count = 0;
        }
        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
        cell.setCellValue(count);
        
    }

    /**
     */
    public static void setString(Cell cell, String val) {
        if(StringUtils.isNotBlank(val)){
            cell.setCellValue(val);
        }
        
    }

    /**
     * @param cell
     * @param avgCusOrder
     */
    public static void setFloat(Cell cell, float f) {
        if(f == (int) f){
            setInteger(cell, (int)f);
        }
        String val = String.format("%.2f", f);
        setString(cell, val);
    }
}
