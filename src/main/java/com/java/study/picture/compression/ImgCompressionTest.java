package com.java.study.picture.compression;

import java.io.File;

/**
 * Created by zhongjing on 2017/11/16.
 */
public class ImgCompressionTest {
    
    public static void main(String[] args) {
//        imgWidthHeightCompression();
        imgMemoryAndSizeUnchanged();
    }

    /**
     * 图片压缩 根据宽和长压缩
     */
    public static void imgWidthHeightCompression(){
        /**
         * d://3.jpg 源图片 
         * d://31.jpg 目标图片 
         * 压缩宽度和高度都是1000 
         *
         */
        System.out.println("压缩图片开始...");
        File srcFile = new File("D:\\3.png");
        System.out.println("压缩前srcFile size:" + srcFile.length());
        ImgWidthHeightCompression.reduceImg("D:\\3.png", "D:\\4.png", 1000, 1000,null);
        File distFile = new File("D:\\4.png");
        System.out.println("压缩后distFile size:" + distFile.length());
    }



    /**
     * 图片压缩 压缩图片质量，大小不变
     */
    public static void imgMemoryAndSizeUnchanged() {
        byte[] buffer = FileUtils.fileTransformationByte("D:\\3.png");
        byte[] bytes = ImgMemoryAndSizeUnchanged.compressPic(buffer,0.5F);
        FileUtils.byteTransformationFile(bytes,"D:\\","8.png");
    }
}
