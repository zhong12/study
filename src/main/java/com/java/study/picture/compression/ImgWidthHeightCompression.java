package com.java.study.picture.compression;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by zhongjing on 2017/11/16.
 * 
 * 采用指定宽度、高度或压缩比例 的方式对图片进行压缩
 */
public class ImgWidthHeightCompression {

    /**
     * 采用指定宽度、高度或压缩比例 的方式对图片进行压缩 
     * @param imgsrc
     * @param imgdist
     * @param widthdist
     * @param heightdist
     * @param rate
     */
    public static void reduceImg(String imgsrc, String imgdist, int widthdist,
                                 int heightdist, Float rate) {
        try {
            File srcfile = new File(imgsrc);
            // 检查文件是否存在  
            if (!srcfile.exists()) {
                return;
            }
            // 如果rate不为空说明是按比例压缩  
            if (rate != null && rate > 0) {
                // 获取文件高度和宽度  
                int[] results = getImgWidth(srcfile);
                if (results == null || results[0] == 0 || results[1] == 0) {
                    return;
                } else {
                    widthdist = (int) (results[0] * rate);
                    heightdist = (int) (results[1] * rate);
                }
            }
            // 开始读取文件并进行压缩  
            Image src = javax.imageio.ImageIO.read(srcfile);
            BufferedImage tag = new BufferedImage((int) widthdist,
                    (int) heightdist, BufferedImage.TYPE_INT_RGB);

            tag.getGraphics().drawImage(
                    src.getScaledInstance(widthdist, heightdist,
                            Image.SCALE_SMOOTH), 0, 0, null);

            FileOutputStream out = new FileOutputStream(imgdist);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(tag);
            out.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * 获取图片宽度 
     * @param file 图片文件
     * @return
     */
    public static int[] getImgWidth(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int[] result = { 0, 0 };
        try {
            is = new FileInputStream(file);
            src = javax.imageio.ImageIO.read(is);
            result[0] = src.getWidth(null); // 得到源图宽  
            result[1] = src.getHeight(null); // 得到源图高  
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
