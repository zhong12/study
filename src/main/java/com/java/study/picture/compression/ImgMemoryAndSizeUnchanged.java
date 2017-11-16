package com.java.study.picture.compression;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by zhongjing on 2017/11/16.
 * 
 * 图片压缩，压缩图片质量，大小不变
 */
public class ImgMemoryAndSizeUnchanged {
    
    public static byte[] compressPic(byte[] imageByte,float quality) {
        if (quality <= 0 || quality > 1) {
            quality = 0.6F;
        }
        byte[] inByte = null;
        try {
            ByteArrayInputStream byteInput = new ByteArrayInputStream(imageByte);
            Image img = ImageIO.read(byteInput);
            float newWidth = img.getWidth(null);
            float newHeight = img.getHeight(null);
            Image image = img.getScaledInstance((int) newWidth,(int) newHeight, Image.SCALE_SMOOTH);
            // 缩放图像
            BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = tag.createGraphics();
            // 绘制缩小后的图
            g.drawImage(image, 0, 0, null); 
            g.dispose();
            ByteArrayOutputStream out = new ByteArrayOutputStream(imageByte.length);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            JPEGEncodeParam jep=JPEGCodec.getDefaultJPEGEncodeParam(tag); 
            /* 压缩质量 */
            jep.setQuality(quality, true);
            encoder.encode(tag, jep);
            inByte = out.toByteArray();
            out.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return inByte;
    }
}
