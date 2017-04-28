/*
 * huirong Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * Author     :liyb
 * Create Date:2016年4月14日
 */
package costumetrade.common.zip;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.SystemUtils;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

/**
 * 解压zip文件工具类
 * @author liyb
 * @version UnzipUtil.java,2016年4月14日 下午7:57:31
 */
public class ZipUtil {
    
	protected static String extension = ".zip";
	
    static String zipFilepath="";
    static {
        if(SystemUtils.IS_OS_WINDOWS){
        	zipFilepath = "C:/cardry/zip";
        }else{
        	zipFilepath = "/home/cardry/zip";
        }
        File file = new File(zipFilepath);
    }
    
    /**
     * 
     * @param fileName
     * @return
     */
    public static String getZipTempPath(String fileName){
    	return zipFilepath + File.separator + fileName + extension;
    }
    
    /**
     * 解压文件
     * @param zipFile 要解压的文件
     * @param deskPath 解压后的文件目录
     * @return listFiles 返回解压后的文件集合
     */
    public static List<String> unzipFiles(String zipFile,String deskPath){
        List<String> listFiles = new ArrayList<String>();
        File file = null;
        File parentFile = null;
        try {
            ZipFile zFile = new ZipFile(zipFile);
            zFile.setFileNameCharset("GBK");
            file = new File(zipFile);
            parentFile = file.getParentFile();
            zFile.extractAll(deskPath);
            List<?> fileHeader = zFile.getFileHeaders();
            for (int i = 0; i < fileHeader.size(); i++) {
                FileHeader header = (FileHeader) fileHeader.get(i);
                listFiles.add(header.getFileName());
            }
        } catch (ZipException e) {
            e.printStackTrace();
            listFiles = null;
        } finally{
            //删除临时文件
            if(file!=null){
                file.delete();
            }
            //删除空目录
            if(parentFile!=null){
                parentFile.delete();
            }
        }
        return listFiles;
    }
    
    /**
	 * 文件打包成.zip压缩包
	 * @param sourceFilePathList 待压缩的文件路径
	 * @param zipFilePath	 压缩后存放路径
	 * @param fileName		 压缩后文件的名称
	 * @return flag
	 */
	public static boolean zipFiles(List<String> sourceFilePathList, String fileName) {
		boolean flag = false;
		try{
			if(sourceFilePathList == null){
				flag = false;
			}else{
				ArrayList list = new ArrayList();
				
				for (String sourceFilePath : sourceFilePathList) {
					list.add(new File(sourceFilePath));
				}
				ZipFile  zipFile = new ZipFile(zipFilepath + "/" + fileName + extension); //  + "-" + DateUtil.getCurrentDateStamp()
				ZipParameters parameters = new ZipParameters();
				parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
				parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL); 
				zipFile.addFiles(list, parameters);
				
				flag = true;
			}
		}catch(ZipException e){
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	 public static void main(String[] args) {
//       String zipFile = "D:\\image\\zipTemp\\00112015120812103527\\csnfdbpz.zip";
//       List<String> listFiles = unzipFiles(zipFile,"D:\\image\\20160501\\00112015120812103527");
//       if(listFiles!=null){
//           for(String file : listFiles){
//               System.err.println(file);
//           }
//       }
   	
		List<String> list = new ArrayList<String>();
		list.add("F:\\材料图片\\_GPNAHV3S()EMH@DD0TXC1A.png");
		list.add("F:\\材料图片\\购车发票.jpg");
		list.add("F:\\材料图片\\客户数据查询授权书.png");
		list.add("F:\\材料图片\\14633776002841439257580.jpg");
		list.add("F:\\材料图片\\身份证正面.jpg");
		
		System.out.println(zipFiles(list, "test01"));
   }
}
