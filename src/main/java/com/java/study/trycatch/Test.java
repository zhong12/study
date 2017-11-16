package com.java.study.trycatch;

/**
 * Created by zhongjing on 2016/1/27 0027.
 */
public class Test {
    public static void main(String[] args) {
        String[] str = {"12","22","23"};
        try {
            System.out.println(str[3]);
            try {
                System.out.println(str[4]);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }finally {
                System.out.println(123);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
