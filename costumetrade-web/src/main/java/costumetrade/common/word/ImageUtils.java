/*
 * huirong Inc.
 * Copyright (c) 2015 All Rights Reserved.
 * Author     :liyb
 * Create Date:2015年9月15日
 */
package costumetrade.common.word;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.commons.lang3.StringUtils;


/**
 * 文件上传处理类,图片压缩
 * @author liyb
 * @version ImageUtils.java,2016年8月15日 下午4:12:43
 */
public class ImageUtils {

    private static String FILE_PATH = "";
    private static String TMP="tmp_";
    private static int commitSize = 300;

    static {
        if (SystemUtils.IS_OS_WINDOWS) {
            FILE_PATH = "C:/cardry/tmp/";
        } else {
            FILE_PATH = "/home/cardry/tmp/";
        }
        commitSize = 300;
        File file = new File(FILE_PATH);
        if(!file.exists()){
            file.mkdirs();
        }
    }
    
    public static void main(String[] args) {
        String path = "E:\\线上备份\\nfdb2016081815061060\\1471504382143-1465733138.jpg";
        compressionFile(new File(path),"1471504382143-1465733138.jpg");
    }

    /**
     * 处理出图压缩
     * @param fileInput
     * @param fileName
     * @param materialsId
     * @return
     */
    public static File compressionFile(File fileInput, String fileName){
//        System.err.println("------------------->>>>>>>开始处理文件压缩");
        commitSize=300;
        File file=null;
        fileName = TMP+fileName;
        // 判断文件大小是否小于默认上传的文件大小
        if (fileInput.length() < getFileSize()) {
            String tmpFile = copyFilePEGTemp(fileInput,fileName);
            file = new File(tmpFile);
        }else{
            try {
                String tmpFile = writeFileJPEGTemp(fileInput,fileName);
                if(StringUtils.isNotEmpty(tmpFile)){
                    File f = new File(tmpFile);
                    FileOutputStream out = null;
//                    System.err.println("初始大小："+f.length());
                    while (f.length() > commitSize * 1000l) {
                        try {
                            Image img = ImageIO.read(f);
                            BufferedImage tag = new BufferedImage(img.getWidth(null) * 9 / 10,
                                img.getHeight(null) * 9 / 10, BufferedImage.TYPE_INT_RGB);
                            tag.getGraphics().drawImage(
                                img.getScaledInstance(img.getWidth(null) * 9 / 10,
                                    img.getHeight(null) * 9 / 10, Image.SCALE_SMOOTH), 0, 0, null);
                            ImageOutputStream ios = ImageIO.createImageOutputStream(new FileOutputStream(tmpFile));
                            // JPEGImageEncoder可适用于其他图片类型的转换 
                            ImageIO.write(tag, "jpeg", ios);
                        } catch (IOException e) {
                            file=null;
                            e.printStackTrace();
                        } finally {
                            if (out != null) {
                                try {
                                    out.close();
                                } catch (IOException e) {file=null;e.printStackTrace();}
                            }
                        }
                        f = new File(tmpFile);
//                        System.err.println("处理后>>>"+f.length());
                    }
//                    System.err.println("---------》》》》》》》》》》》》处理完成"+tmpFile);
                    file = new File(tmpFile);
                }
            } catch (Exception e) {file=null;e.printStackTrace();}
        }
        return file;
    }
    
    /**
     * 把文件转换为JPEG格式到临时目录
     * @param fileInput
     * @return
     */
    private static String writeFileJPEGTemp(File fileInput, String fileName) {
        try {
            writeFile(fileInput, FILE_PATH, fileName);
            return FILE_PATH + fileName;
        } catch (Exception e) {
            System.err.println("上传临时文件失败");
            e.printStackTrace();
            return "";
        }
    }
    
    private static String copyFilePEGTemp(File fileInput, String fileName) {
        try {
            String path = FILE_PATH + fileName;
            File file = new File(path);
            FileUtils.copyFile(fileInput, file);
            return path;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 创建文件
     * @param uploadPath
     * @return
     */
    private static File createFile(String uploadPath) {
        File file = new File(uploadPath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {}
        }
        return file;
    }

    /**
     * 写入临时文件
     * @param fileInput
     * @param uploadPath
     * @param fileName
     */
    private static void writeFile(File fileInput, String uploadPath, String fileName) {
        String extension = "";
        if (StringUtils.isNotEmpty(fileName)) {
            if (fileName.lastIndexOf(".") != -1) {
                extension = fileName.substring(fileName.lastIndexOf(".") + 1);
            }
        }
        uploadPath = uploadPath + fileName;//上传保存的路径+文件名 
        File file = createFile(uploadPath);//生成新的文件 
        
        //图片 
        BufferedImage src = null;
        if (!fileName.matches(".*\\.(?i)jpg")) {
            try {
                src = javax.imageio.ImageIO.read(new FileInputStream(fileInput));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            File srcImageFileGood = new File(uploadPath);  
            
            try {
                BufferedImage image =  ImageIO.read(fileInput);
                ImageIO.write(image, "JPEG", srcImageFileGood);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } 
            
            try {
                src = javax.imageio.ImageIO.read(srcImageFileGood);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        Image image = null;
        BufferedImage oimage = null;
        image = src.getScaledInstance(src.getWidth(), src.getHeight(), Image.SCALE_DEFAULT);
        oimage = new BufferedImage(src.getWidth(), src.getHeight(), Image.SCALE_DEFAULT);
        Graphics g = oimage.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ImageIO.write(oimage, extension, bos);
        } catch (IOException e) {}// 输出到bos   

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            out.write(bos.toByteArray());//写文件   
        } catch (IOException e) {} 
        finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {}
        }
    }
    
    /** 
     * 文件默认大小 
     */
     public static Long getFileSize() {
         return commitSize * 1000l;
     }
}
