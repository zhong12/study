package com.java.study.sort.china;

import java.text.RuleBasedCollator;
import java.util.Comparator;


/**
 * ChineseGB2312Comparator 类同时实现了 Comparator, Comparable 接口，这样以后能够使用 compare,
 * compareTo 方法的时候都可以使用这个类
 */
public class ChineseGB2312Comparator implements Comparator<String>, Comparable<String> {

    private RuleBasedCollator GB2312Collator = ChineseGB2312Collator
            .getFixedGB2312Collator();
    private String str1;

    /**
     * @param str1
     */
    public ChineseGB2312Comparator(String str1) {
        this.str1 = str1;
    }

    /**
     *
     */
    public ChineseGB2312Comparator() {
        this.str1 = "";
    }

    /**
     * @param str1
     * @param str2
     * @return an integer indicatint the comparison result
     * @see java.util.Comparator#compare(Object, Object)
     */
    public int compare(String str1, String str2) {
        return GB2312Collator.compare(str1, str2);
    }

    /**
     * @param str2
     * @return an integer indicatint the comparison result
     * @see java.lang.Comparable#compareTo(Object)
     */
    public int compareTo(String str2) {
        return GB2312Collator.compare(str1, str2);
    }
}
