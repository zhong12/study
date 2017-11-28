package com.java.study.asm.tree;

/**
 * Created by zhongjing on 2017/11/27.
 */
public class ForReadClass {
    final int init = 110;
    private final Integer intField = 120;
    public final String stringField = "Public Final Strng Value";
    public static String commStr = "Common String value";
    String str = "Just a string value";
//    String str1 = new String("Just a new string value");
    final double d = 1.1;
    final Double D = 1.2;

//    public ForReadClass() {
//    }

    public void methodA() {
        System.out.println(intField);
    }
}
