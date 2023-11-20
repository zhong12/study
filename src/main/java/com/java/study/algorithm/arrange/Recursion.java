package com.java.study.algorithm.arrange;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: z.j
 * @Date: 2023/11/20/10:10
 * @Description: 递归方式
 */
public class Recursion implements ArrangeStr {

    /**
     * 利用递归的方式获取
     *
     * @param input 字符串
     * @return 排列的所有字符串
     */
    @Override
    public List<String> arrangeAllStr(String input) {
        List<String> result = new ArrayList<>();
        if (StringUtils.isBlank(input)) {
            return result;
        }
        if (input.length() == 1) {
            result.add(input);
            return result;
        }
        for (int i = 0; i < input.length(); i++) {
            //获取第一个元素
            char first = input.charAt(i);
            //获取除第一个元素外的其他元素
            String end = input.substring(0, i) + input.substring(i + 1);
            //迭代，获取不同的排列组合
            List<String> restPerms = arrangeAllStr(end);
            for (String param : restPerms) {
                result.add(first + param);
            }
        }
        return result;
    }
}
