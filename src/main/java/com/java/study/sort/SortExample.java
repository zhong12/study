package com.java.study.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zhongjing on 2015/12/9 0009.
 * 排序：
 */
public class SortExample {

    /**
     * 一、插入排序具体算法描述如下：
     * 1.从第一个元素开始，该元素可以认为已经被排序
     * 2.取出下一个元素，在已经排序的元素序列中从后向前扫描
     * 3.如果该元素（已排序）大于新元素，将该元素移到下一位置
     * 4.重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
     * 5.将新元素插入到该位置后
     * 6.重复步骤2~5
     */
    public static void insertionSort(int[] data) {
        for (int index = 1; index < data.length; index++) {
            int key = data[index];
            int position = index;
            // shift larger values to the right
            while (position > 0 && data[position - 1] > key) {
                data[position] = data[position - 1];
                position--;
            }
            data[position] = key;
        }
    }


    /**
     * 二、希尔排序：适合大数量排序操作。
     */
    static <E extends Comparable<? super E>> void shellSort(List<E> a) {
        int h = 1;
        while (h < a.size() / 3) h = h * 3 + 1;    // <O(n^(3/2)) by Knuth,1973>: 1, 4, 13, 40, 121, ...
        for (; h >= 1; h /= 3)
            for (int i = h; i < a.size(); i++)
                for (int j = i; j >= h && a.get(j).compareTo(a.get(j - h)) < 0; j -= h)
                    Collections.swap(a, j, j - h);
    }

    /**
     * 三、冒泡排序算法的运作如下：
     * 1.比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     * 2.对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
     * 3.针对所有的元素重复以上的步骤，除了最后一个。
     * 4.持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。[1]
     */
    public static void bubbleSort(int[] data) {
        int temp = 0;
        for (int i = data.length - 1; i > 0; --i) {
            boolean isSort = false;
            for (int j = 0; j < i; ++j) {
                if (data[j + 1] < data[j]) {
                    temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                    isSort = true;
                }
            }

            // 如果一次内循环中发生了交换，那么继续比较；如果一次内循环中没发生任何交换，则认为已经排序好了。
            if (!isSort)
                break;
        }
    }

    /**
     * 四、选择排序的基本思想是：
     * 1.遍历数组的过程中，以 i 代表当前需要排序的序号，则需要在剩余的 [i+1…n-1] 中找出其中的最小值，
     * 2.然后将找到的最小值与 i 指向的值进行交换。
     * 因为每一趟确定元素的过程中都会有一个选择最小值的子流程，所以人们形象地称之为选择排序。
     *
     * @param data
     */
    public static void selectSort(int[] data) {
        int minIndex = 0;
        int temp = 0;
        for (int i = 0; i < data.length; i++) {
            minIndex = i; // 无序区的最小数据数组下标
            for (int j = i + 1; j < data.length; j++) { // 在无序区中找到最小数据并保存其数组下标
                if (data[j] < data[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) { // 如果不是无序区的最小值位置不是默认的第一个数据，则交换之。
                temp = data[i];
                data[i] = data[minIndex];
                data[minIndex] = temp;
            }
        }
    }

    /**
     * 五、归并操作的过程如下：
     * 1.申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列
     * 2.设定两个指针，最初位置分别为两个已经排序序列的起始位置
     * 3.比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置
     * 4.重复步骤3直到某一指针达到序列尾
     * 5.将另一序列剩下的所有元素直接复制到合并序列尾
     */
    public static int[] mergeSort(int[] arr) {// 归并排序 --递归
        if (arr.length == 1) {
            return arr;
        }
        int half = arr.length / 2;
        int[] arr1 = new int[half];
        int[] arr2 = new int[arr.length - half];
        System.arraycopy(arr, 0, arr1, 0, arr1.length);
        System.arraycopy(arr, half, arr2, 0, arr2.length);
        arr1 = mergeSort(arr1);
        arr2 = mergeSort(arr2);
        return mergeSortSub(arr1, arr2);
    }

    private static int[] mergeSortSub(int[] arr1, int[] arr2) {// 归并排序子程序
        int[] result = new int[arr1.length + arr2.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while (true) {
            if (arr1[i] < arr2[j]) {
                result[k] = arr1[i];
                if (++i > arr1.length - 1) {
                    break;
                }
            } else {
                result[k] = arr2[j];
                if (++j > arr2.length - 1) {
                    break;
                }
            }
            k++;
        }
        for (; i < arr1.length; i++) {
            result[++k] = arr1[i];
        }
        for (; j < arr2.length; j++) {
            result[++k] = arr2[j];
        }
        return result;
    }

    /**
     * 输出
     *
     * @param c
     */
    private static void printArray(int[] c) {
        for (int i = 0; i < c.length; i++)
            System.out.print(c[i] + ",");
        System.out.println();
    }

    public static void main(String[] args) {
        int[] data = {10, 4, 9, 23, 1, 45, 27, 5, 2};
        System.out.println("bubbleSort...");
        int[] a = data.clone();
        printArray(a);
        bubbleSort(a);
        printArray(a);


        System.out.println("selectSort...");
        int[] b = data.clone();
        printArray(b);
        selectSort(b);
        printArray(b);


        System.out.println("insertionSort...");
        int[] c = data.clone();
        printArray(c);
        insertionSort(c);
        printArray(c);


        System.out.println("shellSort...");
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < data.length; i++)
            list.add(data[i]);
        System.out.println(list);
        shellSort(list);
        System.out.println(list);


        System.out.println("mergeSort...");
        int[] d = data.clone();
        printArray(d);
        printArray(mergeSort(d));
    }
}
