/*
 * huirong Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * Author     :liyb
 * Create Date:2016年10月20日
 */
package costumetrade.common.wenqian.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This is Class Description...
 * @author liyb
 * @version FormatConversion.java,2016年10月20日 下午6:14:46
 */
public class FormatConversion {
    public static final String JPG = "jpg";
    public static final String GIF = "gif";
    public static final String PNG = "png";
    public static final String BMP = "bmp";

    public void Conversion(String inputFormat, String outputFormat, String src) {
        try {
            File input = new File(src + inputFormat);
            BufferedImage bim = ImageIO.read(input);
            File output = new File(src + outputFormat);
            ImageIO.write(bim, outputFormat, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws IOException {
        String src = "C:\\Users\\aa\\Desktop\\huirong-stamp.";
        new FormatConversion().Conversion(JPG,PNG,src);//JPG转成PNG
//        new FormatConversion().Conversion(JPG,GIF,src);//JPG转成GIF
//        new FormatConversion().Conversion(JPG,BMP,src);//JPG转成BMP
        //其余格式转化只要调用Conversion函数即可
        
        int width = 400;
        int height = 300;
        // 创建BufferedImage对象
        BufferedImage image = new BufferedImage(width, height,     BufferedImage.TYPE_INT_RGB);
        // 获取Graphics2D
        Graphics2D g2d = image.createGraphics();

        // ----------  增加下面的代码使得背景透明  -----------------
        image = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        g2d.dispose();
        g2d = image.createGraphics();
        // ----------  背景透明代码结束  -----------------
        // 画图
        g2d.setColor(new Color(255,0,0));
        g2d.setStroke(new BasicStroke(1));
//        g2d.draw(s);
        //释放对象
        g2d.dispose();
        // 保存文件    
        ImageIO.write(image, "png", new File("C:\\Users\\aa\\Desktop\\huirong-stamp.png"));
    }
}
