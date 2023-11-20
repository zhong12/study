package com.java.study.algorithm.arrange;

import java.util.List;

/**
 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,
 * 则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 */
public class ArrangeTest {
    public static void main(String[] args) {
        String input = "abcdefg";
        ArrangeStr recursion = new Recursion();
        List<String> recursionResult = recursion.arrangeAllStr(input);
        System.out.println(recursionResult.size() + " --->" + recursionResult);
        ArrangeStr backtrack = new Backtrack();
        List<String> backtrackResult = backtrack.arrangeAllStr(input);
        System.out.println(backtrackResult.size() + " --->" + backtrackResult);
        ArrangeStr dictionaryOrder = new DictionaryOrder();
        List<String> dictionaryOrderResult = dictionaryOrder.arrangeAllStr(input);
        System.out.println(dictionaryOrderResult.size() + " --->" + dictionaryOrderResult);
    }
}
