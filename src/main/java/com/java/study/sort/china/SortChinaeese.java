package com.java.study.sort.china;

import java.text.RuleBasedCollator;
import java.util.Arrays;
import java.util.Locale;

public class SortChinaeese {

    public static void main(String[] args) {
        // simpleSortChina();

        String[] chinaStrs = new String[] { "作业", "测试", "Zhang", "test", "我们",
                "凹凸曼", "李四", "科比", "xin86", "Xin86", "zhang", "ao", "凹吧" };
        java.util.Arrays.sort(chinaStrs, new ChineseGB2312Comparator());
        for (String key : chinaStrs)
            System.out.print(key + "  ");
    }

    public static void simpleSortChina() {
        String[] chinaStrs = new String[] { "作业", "测试", "Zhang", "test", "我们",
                "凹凸曼", "李四", "科比", "xin86", "Xin86", "zhang", "ao", "凹吧" };
        Arrays.sort(chinaStrs, RuleBasedCollator.getInstance(Locale.CHINA));
        for (String string : chinaStrs) {
            System.out.print(string + "  ");
        }
    }
}
