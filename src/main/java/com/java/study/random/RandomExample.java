package com.java.study.random;

import java.util.Random;

/**
 * Created by zhongjing on 2016/1/20 0020.
 */
public class RandomExample {
    public static void main(String[] args){
        Random random = new Random();
        //获取0~33之间的随机数字
        int a = random.nextInt(33);
        System.out.println(a);
    }
}
