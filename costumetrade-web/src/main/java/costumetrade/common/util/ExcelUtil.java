/*
 * huirong Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * Author     :liyb
 * Create Date:2016年8月31日
 */
package costumetrade.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * This is Class Description...
 * @author liyb
 * @version ExcelUtil.java,2016年8月31日 下午6:24:49
 */
public class ExcelUtil {
    
    private static final Log logger = LogFactory.getLog("ExcelUtil");
    
    /**
     * 写excel到指定的文件
     * @param workbook
     * @param fileName
     * @return
     */
    public static String writeToFile(HSSFWorkbook workbook, String fileName) {
        try {
            if (workbook == null) {
                logger.error("workbook不能为空白");
                return null;
            }
            if (StringUtils.isEmpty(fileName)) {
                logger.error("fileName不能为空白");
                return null;
            }
            File file = new File(fileName);
            if (!file.isFile()) {
                file.createNewFile();
            }
            FileOutputStream baos = new FileOutputStream(fileName);
            workbook.write(baos);
            baos.flush();
            baos.close();
        } catch (Exception e) {
            logger.error("writeToFile:" + e.getMessage());
            return null;
        }
        return fileName;
    }

    /**
     * 逐行写明细
     * @param sheet
     * @param detailValues
     * @param rowNum
     */
    @SuppressWarnings("deprecation")
    public static void writeExcelDetailRow(HSSFSheet sheet, String[] detailValues, int rowNum) {
        try {
            HSSFRow row = sheet.createRow(rowNum);
            if (detailValues == null) {
                detailValues = new String[] { "" };
            }
            if (detailValues != null && detailValues.length > 0) {
                for (int i = 0; i < detailValues.length; i++) {
                    HSSFCell cell = row.createCell((short) i);
                    cell.setCellValue(detailValues[i]);
                }
            }
        } catch (Exception e) {
            logger.error("写excel的DetailRow部分发生异常");
        }
    }
    
    /**
     * 写excel到response
     * @param workbook
     * @param baos
     */
    public static void writeToResponse(HSSFWorkbook workbook, OutputStream baos) {
        try {
            if (workbook == null) {
                logger.error("workbook不能为空白");
            }
            workbook.write(baos);
            baos.flush();
            baos.close();
        } catch (Exception e) {
            logger.error("writeToFile:" + e.getMessage());
        }
    }
}
