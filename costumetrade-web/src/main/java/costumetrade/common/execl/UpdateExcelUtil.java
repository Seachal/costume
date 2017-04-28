/*
 * huirong Inc.
 * Copyright (c) 2015 All Rights Reserved.
 * Author     :liyb
 * Create Date:2015年12月14日
 */
package costumetrade.common.execl;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 读取Execl模版,更改模版数据
 * 支持2003、2007
 * @author liyb
 * @version UpdateExcelUtil.java,2015年12月14日 下午12:55:44
 */
public class UpdateExcelUtil {
    
    private static final int version2003 = 2003;
    private static final int version2007 = 2007;
    private static int version = version2003;

    /**
     * @param exlFile
     * @param sheetIndex sheet
     * @param col 列
     * @param row 行
     * @param value
     */
    public static void updateExcel(File exlFile,int sheetIndex,int col,int row,String value){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis=new FileInputStream(exlFile);
            HSSFWorkbook workbook=new HSSFWorkbook(fis);
            HSSFSheet sheet=workbook.getSheetAt(sheetIndex);

            HSSFRow r=sheet.getRow(row);
            HSSFCell cell=r.getCell(col);
//            int type=cell.getCellType();
//            String str1=cell.getStringCellValue();
            String str1=getCellValue(cell);
            cell.setCellValue(value);
            System.out.println("单元格原来值为"+str1);
            System.out.println("单元格值被更新为"+value);
            
            workbook.setForceFormulaRecalculation(true);
            
            File file = new File("D:/image/update_execl.xls");
            fos=new FileOutputStream(file);
            workbook.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(fis!=null){
                    fis.close();//关闭文件输入流
                }
                if(fos!=null){
                    fos.close();//关闭文件输出流
                }
            } catch (Exception e2) {}
        }
    }
    
    public static Workbook initWorkbook(File exlFile){
        if (exlFile.getName().endsWith(".xls")){
            version = version2003;
        }else if (exlFile.getName().endsWith(".xlsx")){
            version = version2007;
        }
        Workbook workbook=null;
        try {
            if (version == version2003) {
                workbook=new HSSFWorkbook();
            }else if (version == version2007) {
                workbook=new XSSFWorkbook();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workbook;
        
    }
    
    /**
     * 
     * @param exlFile execl模版文件
     * @param sheetIndex sheet工作薄
     * @param cellList 待修改的值集合
     */
    public static Workbook updateExcel(File exlFile,int sheetIndex,List<ExeclCell> cellList){
        if (exlFile.getName().endsWith(".xls")){
            version = version2003;
        }else if (exlFile.getName().endsWith(".xlsx")){
            version = version2007;
        }
        
        FileInputStream fis = null;
        FileOutputStream fos = null;
        Workbook workbook=null;
        try {
            fis=new FileInputStream(exlFile);
            if (version == version2003) {
                workbook=new HSSFWorkbook(fis);
            }else if (version == version2007) {
                workbook=new XSSFWorkbook(fis);
            }
            
            Sheet sheet=workbook.getSheetAt(sheetIndex);
            
            for (int i = 0; i < cellList.size(); i++) {
                ExeclCell execl = cellList.get(i);
                Row r=sheet.getRow(execl.getRow());
                if(r==null){
                    r = sheet.createRow(execl.getRow());
                }
                Cell cell=r.getCell(execl.getCol());
                if(cell==null){
                    cell = r.createCell(execl.getCol());
                    cell.setCellValue("");
                }
//                String str1=getCellValue(cell);
                try {
                    cell.setCellValue(String.valueOf(execl.getNewValue()));
                } catch (Exception e) {}
//                System.out.println("单元格原来值为:"+str1);
//                System.out.println("单元格值被更新为:"+execl.getNewValue());
            }
            workbook.setForceFormulaRecalculation(true);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(fis!=null){
                    fis.close();//关闭文件输入流
                }
                if(fos!=null){
                    fos.close();//关闭文件输出流
                }
            } catch (Exception e2) {}
        }
        return workbook;
    }

    /**
     * 设置Cell值
     * @param cell
     * @return
     */
    private static String getCellValue(Cell cell) {
        String cellValue = "";
        try {
            DecimalFormat df = new DecimalFormat("#");
            switch (cell.getCellType()) {
                case XSSFCell.CELL_TYPE_STRING:
                    cellValue = cell.getRichStringCellValue().getString().trim();
                    break;
                case XSSFCell.CELL_TYPE_NUMERIC:
                    cellValue = df.format(cell.getNumericCellValue()).toString();
                    break;
                case XSSFCell.CELL_TYPE_BOOLEAN:
                    cellValue = String.valueOf(cell.getBooleanCellValue()).trim();
                    break;
                case XSSFCell.CELL_TYPE_FORMULA:
                    cellValue = cell.getCellFormula();
                    break;
                default:
                    cellValue = "";
            }
        } catch (Exception e) {
            cellValue = "";
            e.printStackTrace();
        }
        return cellValue;
    }
    
    /**
     * 设置行列值
     * @param col 列
     * @param row 行
     * @param newValue 值
     * @param cellList
     * @return
     */
    private static List<ExeclCell> setCellValue(int col,int row,Object newValue,List<ExeclCell> cellList){
        ExeclCell execlCell = new ExeclCell();
        execlCell.setCol(col);
        execlCell.setRow(row);
        execlCell.setNewValue(newValue);
        cellList.add(execlCell);
        return cellList;
    }
    
    public static void main(String[] args) throws Exception{
         File file=new File("D:/image/ICBC_EXECL.xls");
         List<ExeclCell> cellList = new ArrayList<ExeclCell>();
         cellList = setCellValue(3,44,"420683198803166713", cellList);
         cellList = setCellValue(3,45,"1989", cellList);
         cellList = setCellValue(4,45,"03", cellList);
         cellList = setCellValue(5,45,"16", cellList);
         cellList = setCellValue(3,102,"奥迪A6L", cellList);
         
//         UpdateExcelUtil.updateExcel(file,0,3,102,"大众");
         Workbook wb = UpdateExcelUtil.updateExcel(file, 0, cellList);
         File outfile = new File("D:/image/update_execl.xls");
         
//         Workbook wb = new HSSFWorkbook();
//         Sheet sheet = wb.createSheet();
//         ExcelUtil.writeExcelDetailRow(sheet, new String[] { "导出报表发生系统异常" }, 0);
         
         FileOutputStream baos = new FileOutputStream(outfile);
         ExcelUtil.writeToResponse(wb, baos);
    }
}


