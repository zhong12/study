package com.java.study.algorithm.arrange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: z.j
 * @Date: 2023/11/20/10:10
 * @Description: 回溯法
 */
public class Backtrack implements ArrangeStr {
    @Override
    public List<String> arrangeAllStr(String input) {
        List<String> result = new ArrayList<>();
        if (input.isEmpty()) {
            return result;
        }
        char[] chars = input.toCharArray();
        Arrays.sort(chars);
        handle(result, new StringBuilder(), chars, new boolean[chars.length]);
        return result;
    }

    /**
     *
     * @param result
     * @param sb
     * @param chars
     * @param used
     */
    private void handle(List<String> result, StringBuilder sb, char[] chars, boolean[] used) {
        if (sb.length() == chars.length) {
            result.add(sb.toString());
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (used[i]) {
                continue;
            }
            if (i > 0 && chars[i] == chars[i - 1] && used[i - 1]) {
                continue;
            }
            used[i] = true;
            sb.append(chars[i]);
            handle(result, sb, chars, used);
            sb.deleteCharAt(sb.length() - 1);
            used[i] = false;
        }
    }
}
