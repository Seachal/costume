/*
 * huirong Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * Author     :liyb
 * Create Date:2016年3月14日
 
package costumetrade.common.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.lang.SystemUtils;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.core.IdentifyCmd;
import org.im4java.process.ArrayListOutputConsumer;


*//**
 * img4j图片压缩处理
 * @author liyb
 * @version ImageUtil.java,2016年3月14日 下午8:08:33
 *//*
public class ImageUtil {

    private static String FILE_PATH       = "";
    public static String TMBF_NAME       = "tmbf_";

    static {
        if (SystemUtils.IS_OS_WINDOWS) {
            FILE_PATH = "C:/cardry/tmp/";
        } else {
            FILE_PATH = "/home/cardry/tmp/";
        }
        File file = new File(FILE_PATH);
        if(!file.exists()){
           file.mkdirs();
        }
    }
    
    public static void main(String[] args) {
        String path = "E:\\image\\zc-1.jpg";//
        File file = compressionFile(new File(path), "sfz1024-1024.jpg", 1151);

//        removeProfile(file.getPath(), FILE_PATH+"sss.jpg");
//        compressionFile(new File(path),"sfz960-1280.jpg",1151);
//        thumbnailFile(new File(path), "img1.jpg");
//        percentageFile(new File(path),"sfz100%.jpg");
//        System.err.println("宽度="+getWidth(path));
//        System.err.println("高度="+getHeight(path));
    }

    *//**
     * 处理图片压缩(百分比压缩 100%*100%)
     * @param fileInput 源文件对象
     * @param fileName 生成的文件名
     * @return 返回File对象
     *//*
    public static File percentageFile(File fileInput, String fileName) {
        String newPath = FILE_PATH + fileName;
        String srcPath = fileInput.getPath();
        File file = null;
        try {
            int width = 100;
            int height = 100;
            String tmpfile = cutImage(width, height, srcPath, newPath, 2, "80");
            file = new File(tmpfile);
        } catch (Exception e) {
            e.printStackTrace();
            file = null;
        }
        return file;
    }

    *//**
     * 处理图片压缩(像素压缩根据源图片的像素 如： 1024x1024)
     * @param fileInput 源文件对象
     * @param fileName 生成的文件名
     * @return 返回File对象
     *//*
    public static File compressionFile(File fileInput, String fileName, Integer materialsId) {
        String newPath = FILE_PATH + fileName;
        String srcPath = fileInput.getPath();
        File file = null;
        try {
//            int width = 1024;
//            int height = 1024;
            int width = getWidth(fileInput.getPath());
            int height = getHeight(fileInput.getPath());
            //注册登记证、货物进口证明书、进口机动车辆随车检验单 修改压缩比例为960x1280
            if (materialsId.equals(1151) || materialsId.equals(1131) || materialsId.equals(1141)) {
                width = 960;
                height = 1280;
            }
            String tmpfile = cutImage(width, height, srcPath, newPath, 1, null);
            file = new File(tmpfile);
        } catch (Exception e) {
            e.printStackTrace();
            file = null;
        }
        return file;
    }

    *//**
     * 按像素生成图片缩略图(宽300x高180)
     * @param fileInput 源文件对象
     * @param fileName 生成的文件名
     * @return 返回File对象
     *//*
    public static File thumbnailFile(File fileInput, String fileName) {
        String newPath = FILE_PATH + TMBF_NAME + fileName;
        String srcPath = fileInput.getPath();
        File file = null;
        try {
            String tmpfile = cutImage(300, 180, srcPath, newPath, 1, null);
            file = new File(tmpfile);
        } catch (Exception e) {
            e.printStackTrace();
            file = null;
        }
        return file;
    }

    *//** 
     * 根据尺寸缩放图片 
     * @param width 缩放后的图片宽度 
     * @param height 缩放后的图片高度 
     * @param srcPath 源图片路径 
     * @param newPath 缩放后图片的路径 
     * @param type 1为像素，2为百分比处理，如（像素大小：1024x1024,百分比：50%x50%） 
     * @param quality 压缩质量(默认空)
     *//*
    private static String cutImage(int width, int height, String srcPath, String newPath, int type,
                                   String quality) throws Exception {
        IMOperation op = new IMOperation();
        ConvertCmd cmd = new ConvertCmd(true);
        op.addImage();
        String raw = "";
        if (type == 1) {
            //按像素  
            raw = width + "x" + height + "^";
        } else {
            //按像素百分比  
            raw = width + "%x" + height + "%";
        }
        op.addRawArgs("-sample", raw);
        op.addRawArgs("+profile", "*");//去除Exif信息，可减小文件大小
        if ((quality != null && !quality.equals(""))) {
            op.addRawArgs("-quality", quality);
        }
        op.addImage();
        try {
            cmd.run(op, srcPath, newPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newPath;
    }

    *//**
     * 获得图片的宽度
     * @param filePath 文件路径
     * @return 图片宽度
     *//*
    public static int getWidth(String imagePath) {
        int line = 0;
        try {
            IMOperation op = new IMOperation();
            op.format("%w"); // 设置获取宽度参数
            op.addImage(1);
            IdentifyCmd identifyCmd = new IdentifyCmd(true);
            ArrayListOutputConsumer output = new ArrayListOutputConsumer();
            identifyCmd.setOutputConsumer(output);
            identifyCmd.run(op, imagePath);
            ArrayList<String> cmdOutput = output.getOutput();
            assert cmdOutput.size() == 1;
            line = Integer.parseInt(cmdOutput.get(0));
        } catch (Exception e) {
            line = 0;
            System.out.println("运行指令出错!");
        }
        return line;
    }

    *//**
     * 获得图片的高度
     * @param imagePath 文件路径
     * @return 图片高度
     *//*
    public static int getHeight(String imagePath) {
        int line = 0;
        try {
            IMOperation op = new IMOperation();
            op.format("%h"); // 设置获取高度参数
            op.addImage(1);
            IdentifyCmd identifyCmd = new IdentifyCmd(true);
            ArrayListOutputConsumer output = new ArrayListOutputConsumer();
            identifyCmd.setOutputConsumer(output);
            identifyCmd.run(op, imagePath);
            ArrayList<String> cmdOutput = output.getOutput();
            assert cmdOutput.size() == 1;
            line = Integer.parseInt(cmdOutput.get(0));
        } catch (Exception e) {
            line = 0;
            System.out.println("运行指令出错!" + e.toString());
        }
        return line;
    }

    *//**
     * 去除Exif信息，可减小文件大小
     * @param path 原文件路径
     * @param des 目标文件路径
     *//*
    public static void removeProfile(String path, String des) {
        try {
            IMOperation op = new IMOperation();
            op.addImage(path);
            op.addRawArgs("+profile", "*");
            op.addImage(des);
            ConvertCmd cmd = new ConvertCmd(true);
            cmd.run(op);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IM4JavaException e) {
            e.printStackTrace();
        }
    }

    *//**
     * 先缩放，后居中切割图片
     * @param srcPath 源图路径
     * @param desPath 目标图保存路径
     * @param rectw 待切割在宽度
     * @param recth 待切割在高度
     * @throws IM4JavaException 
     * @throws InterruptedException 
     * @throws IOException 
     *//*
    public static void cropImageCenter(String srcPath, String desPath, int rectw, int recth)
                                                                                            throws IOException,
                                                                                            InterruptedException,
                                                                                            IM4JavaException {
        IMOperation op = new IMOperation();
        op.addImage();
        op.resize(rectw, recth, '^').gravity("center").extent(rectw, recth);
        op.addImage();
        ConvertCmd convert = new ConvertCmd(true);
        convert.run(op, srcPath, desPath);
    }
}
*/