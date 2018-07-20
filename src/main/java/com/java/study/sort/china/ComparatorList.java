package com.java.study.sort.china;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class ComparatorList implements Comparator<Object> {

    public int compare(Object value1, Object value2) {
        // http://j2ee-yohn.javaeye.com/blog/272006 此帖子关于中文拼音排序很详细。
        // TODO Auto-generated method stub
        if (value1.getClass().getName()
                .equals("org.openjweb.core.util.CodeNameBean")) {
            String s1 = ((CodeNameBean) value1).getName().toString();
            String s2 = ((CodeNameBean) value2).getName().toString();
            return Collator.getInstance(Locale.CHINESE).compare(s1, s2);
        } else if (value1.getClass().getName().equals("java.lang.String")) {
            String s1 = value1.toString();
            String s2 = value2.toString();
            return Collator.getInstance(Locale.CHINESE).compare(s1, s2);
        }
        return 0; // 0表示相同。
    }

    public static List<?> sort(List<?> strList) {
        ComparatorList comp = new ComparatorList();
        Collections.sort(strList, comp);
        return strList; // 返回排序后的列表
    }

}
