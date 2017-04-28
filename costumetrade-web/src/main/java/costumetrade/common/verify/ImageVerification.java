/**
 * Copyright (C) 2014-2017, Hrfax and/or its affiliates. All rights reserved.
 * Hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.verify;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.apache.commons.lang3.RandomUtils;

import akka.japi.Pair;

/**
 * 验证码生成工具
 * @author zhouyq
 * @Date 2017年3月16日
 */
public class ImageVerification {
	
    // 图片的宽度。
    private int width = 160;
    // 图片的高度。
    private int height = 40;
    // 验证码字符个数
    private int codeCount = 4;
    // 验证码干扰线数
    private int lineCount = 150;


    private static final char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N',  'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z',  '1', '2', '3', '4', '5', '6', '7', '8', '9' };

    public  ImageVerification() {
    }

    /**
     *
     * @param width 图片宽
     * @param height 图片高
     */
    public  ImageVerification(int width,int height) {
        this.width=width;
        this.height=height;
    }
    /**
     *
     * @param width 图片宽
     * @param height 图片高
     * @param codeCount 字符个数
     * @param lineCount 干扰线条数
     */
    public ImageVerification(int width,int height,int codeCount,int lineCount) {
        this.width=width;
        this.height=height;
        this.codeCount=codeCount;
        this.lineCount=lineCount;
    }

    public Pair<String, BufferedImage> create() {
        int x = 0,fontHeight=0,codeY=0;
        int red = 0, green = 0, blue = 0;

        x = width / (codeCount +2);//每个字符的宽度
        fontHeight = height - 2;//字体的高度
        codeY = height - 4;

        // 图像buffer
        BufferedImage buffImg = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();
        // 将图像填充为白色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        // 创建字体
        g.setFont(new Font("Arial",Font.PLAIN, fontHeight));

        for (int i = 0; i < lineCount; i++) {
      
            int xs = RandomUtils.nextInt(0,width);
            int ys = RandomUtils.nextInt(0,height);
            int xe = xs+RandomUtils.nextInt(0,width/8);
            int ye = ys+RandomUtils.nextInt(0,height/8);
            red = RandomUtils.nextInt(0,255);
            green = RandomUtils.nextInt(0,255);
            blue = RandomUtils.nextInt(0,255);
            g.setColor(new Color(red, green, blue));
            g.drawLine(xs, ys, xe, ye);
        }

        // randomCode记录随机产生的验证码
        StringBuilder randomCode = new StringBuilder();
        // 随机产生codeCount个字符的验证码。
        for (int i = 0; i < codeCount; i++) {
            String strRand = String.valueOf(codeSequence[RandomUtils.nextInt(0,codeSequence.length)]);
            // 产生随机的颜色值，让输出的每个字符的颜色值都将不同。
            red = RandomUtils.nextInt(0,255);
            green = RandomUtils.nextInt(0,255);
            blue = RandomUtils.nextInt(0,255);
            g.setColor(new Color(red, green, blue));
            g.drawString(strRand, (i + 1) * x, codeY);
            // 将产生的四个随机数组合在一起。
            randomCode.append(strRand);
        }
        // 将四位数字的验证码保存到Session中。
        return new Pair<String, BufferedImage>(randomCode.toString(), buffImg);
    }
}
