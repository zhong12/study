package com.java.study.algorithm.arrange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: z.j
 * @Date: 2023/11/20/10:10
 * @Description: 字典序法(不用递归)
 */
public class DictionaryOrder implements ArrangeStr{
    @Override
    public List<String> arrangeAllStr(String input) {
        List<String> list = new ArrayList<>();
        if (input.isEmpty()) {
            return list;
        }
        char[] chars = input.toCharArray();
        Arrays.sort(chars); //将"bac"调整为"abc" ，需要按字典序打印，所以此时要sort
        list.add(String.valueOf(chars));
        while (true) {
            //1.从右到左找到第一个P(i)<P(i+1)的位置，记为firstSmall
            int firstSmall = -1;
            for (int i = chars.length - 2; i >= 0; i--) {
                if (chars[i] < chars[i + 1]) {
                    firstSmall = i;
                    break;
                }
            }
            //每循环一次输出的是（按字典序）比当前数值大的下一种排序
            //所以如果firstSmall == -1，表示当前已经是最大的了，没有下一种了，所以break
            if (firstSmall == -1) {
                break;
            }

            //2.从右到左找到第一个比P(i)大的数的位置，记为firstLarge
            int firstLarge = -1;
            for (int i = chars.length - 1; i >= 0; i--) {
                if (chars[i] > chars[firstSmall]) {
                    firstLarge = i;
                    break;
                }
            }

            //3.交换firstSmall和firstLarge位置的值
            swap(chars, firstSmall, firstLarge);
            //4.把 firstSmall + 1 及它后面的数逆序
            reverse(chars, firstSmall + 1, chars.length - 1);
            list.add(String.valueOf(chars));
        }
        return list;
    }

    private void swap(char[] chars, int i, int j) {
        char temp;
        temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    private void reverse(char[] chars, int i, int j) {
        while (i < j) {
            swap(chars, i++, j--);
        }
    }
}
