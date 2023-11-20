package com.java.study.algorithm.other;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: z.j
 * @Date: 2023/11/20/10:10
 * @Description: 小顶堆算法
 */
public class SmallTopPile {

    public static int N = 10;           //获取最大的多少条 10
    public static int LEN = 100000000; //多少个整数
    public static int[] arr = new int[LEN];
    public static int[] result = new int[N]; //在内存维护一个长度为N的小顶堆
    public static int len = result.length;
    //堆中元素的有效元素 heapSize<=len
    public static int heapSize = len;

    public static void main(String[] args) {
        //构建小顶堆
        long start = System.currentTimeMillis();
        //生成随机数组
        for (int i = 0; i < LEN; i++) {
            arr[i] = new Random().nextInt(LEN-1);
        }
        //构建初始堆
        if (N >= 0) System.arraycopy(arr, 0, result, 0, N);
        buildMinHeap();
        for (int i = N; i < LEN; i++) {
            if (arr[i] > result[0]) {
                result[0] = arr[i];
                minHeap(0);
            }
        }
        System.out.println(LEN + "个数，求Top" + N + "，耗时" + (System.currentTimeMillis() - start) + "毫秒");
        print();
    }


    /**
     * 自底向上构建小堆
     */
    public static void buildMinHeap() {
        //最后一个非叶子节点
        int size = len / 2 - 1;
        for (int i = size; i >= 0; i--) {
            minHeap(i);
        }
    }

    /**
     * i节点为根及子树是一个小堆
     *
     * @param i 节点
     */
    public static void minHeap(int i) {
        int l = left(i);
        int r = right(i);
        int index = i;
        if (l < heapSize && result[l] < result[index]) {
            index = l;
        }
        if (r < heapSize && result[r] < result[index]) {
            index = r;
        }
        if (index != i) {
            int t = result[index];
            result[index] = result[i];
            result[i] = t;
            //递归向下构建堆
            minHeap(index);
        }
    }

    /**
     * 返回i节点的左孩子
     *
     * @param i 节点
     * @return 左孩子
     */
    public static int left(int i) {
        return 2 * i;
    }

    /**
     * 返回i节点的右孩子
     *
     * @param i 节点
     * @return 右边孩子
     */
    public static int right(int i) {
        return 2 * i + 1;
    }

    /**
     * 打印
     */
    public static void print() {
        List<Integer> intList= Arrays.stream(result).boxed().collect(Collectors.toList());
        System.out.println(intList);
    }
}
