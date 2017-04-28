/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :liyb
 * Create Date:2015-7-23
 */
package costumetrade.common.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;

public class ImagePreProcess {
    static String path = "F://image";

    public static int isWhite(int colorInt) {
        Color color = new Color(colorInt);
        if (color.getRed() + color.getGreen() + color.getBlue() > 100) {
            return 1;
        }
        return 0;
    }

    public static int isBlack(int colorInt) {
        Color color = new Color(colorInt);
        if (color.getRed() + color.getGreen() + color.getBlue() <= 100) {
            return 1;
        }
        return 0;
    }

    public static BufferedImage removeBackgroud(String picFile) throws Exception {
        BufferedImage img = ImageIO.read(new File(picFile));
        int width = img.getWidth();
        int height = img.getHeight();
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                if (isWhite(img.getRGB(x, y)) == 1) {
                    img.setRGB(x, y, Color.WHITE.getRGB());
                } else {
                    img.setRGB(x, y, Color.BLACK.getRGB());
                }
            }
        }
        return img;
    }

    public static List<BufferedImage> splitImage(BufferedImage img) throws Exception {
        List<BufferedImage> subImgs = new ArrayList<BufferedImage>();
        subImgs.add(img.getSubimage(11, 1, 8, 10));
        subImgs.add(img.getSubimage(20, 3, 8, 10));
        subImgs.add(img.getSubimage(36, 1, 8, 10));
        subImgs.add(img.getSubimage(50, 6, 8, 10));
        return subImgs;
    }

    public static Map<BufferedImage, String> loadTrainData() throws Exception {
        Map<BufferedImage, String> map = new HashMap<BufferedImage, String>();
        File dir = new File(path);
        File[] files = dir.listFiles();
        for (File file : files) {
            map.put(ImageIO.read(file), file.getName().charAt(0) + "");
        }
        return map;
    }

    public static String getSingleCharOcr(BufferedImage img, Map<BufferedImage, String> map) {
        String result = "";
        int width = img.getWidth();
        int height = img.getHeight();
        int min = width * height;
        for (BufferedImage bi : map.keySet()) {
            int count = 0;
            Label1: for (int x = 0; x < width; ++x) {
                for (int y = 0; y < height; ++y) {
                    if (isWhite(img.getRGB(x, y)) != isWhite(bi.getRGB(x, y))) {
                        count++;
                        if (count >= min)
                            break Label1;
                    }
                }
            }
            if (count < min) {
                min = count;
                result = map.get(bi);
            }
        }
        return result;
    }

    public static String getAllOcr(String file) throws Exception {
        BufferedImage img = removeBackgroud(file);
        List<BufferedImage> listImg = splitImage(img);
        Map<BufferedImage, String> map = loadTrainData();
        String result = "";
        for (BufferedImage bi : listImg) {
            result += getSingleCharOcr(bi, map);
        }
        ImageIO.write(img, "JPG", new File(path + File.separator + result + ".jpg"));
        return result;
    }

    public static void downloadImage() {
        HttpClient httpClient = new HttpClient();
        String codeUrl = "http://www.jhga.gov.cn/WebGetImage.aspx?"+System.currentTimeMillis();
        GetMethod getMethod = new GetMethod(codeUrl);
        
//        String codeUrl = "http://wf.nbjj.gov.cn/image.jsp?";
//        int rand=(int)(Math.random()*10000);
//        PostMethod getMethod = new PostMethod(codeUrl+rand);// 验证码的相对路径 
        
        for (int i = 0; i < 1; i++) {
            try {
                // 执行getMethod
                int statusCode = httpClient.executeMethod(getMethod);
                if (statusCode != HttpStatus.SC_OK) {
                    System.err.println("Method failed: " + getMethod.getStatusLine());
                }
                // 读取内容
                String picName = path + File.separator + i + ".png";
                InputStream inputStream = getMethod.getResponseBodyAsStream();
                OutputStream outStream = new FileOutputStream(picName);
                IOUtils.copy(inputStream, outStream);
                outStream.close();
                System.out.println("OK!");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 释放连接
                getMethod.releaseConnection();
            }
        }
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        downloadImage();
//        for (int i = 0; i < 1; ++i) {
//            String text = getAllOcr(path + File.separator + i + ".jpg");
//            System.out.println(i + ".jpg = " + text);
//        }
    }
}
