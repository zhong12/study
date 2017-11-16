package com.java.study.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by zhongjing on 2016/1/20 0020.
 */
public class Test {
    public static void main(String[] args){
        String inFile = "D:\\test.txt";
        String outFile = "D:\\test.sql";
        try {
            FileInputStream inputStream = new FileInputStream(inFile);
            FileOutputStream outputStream = new FileOutputStream(outFile);

            FileChannel inChannel = inputStream.getChannel();
            FileChannel outChannel = outputStream.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (true){
                byteBuffer.clear();

                int input = inChannel.write(byteBuffer);

                if (input == -1) {
                    break;
                }
                byteBuffer.flip();
                outChannel.write(byteBuffer);
            }

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }


    }
}
