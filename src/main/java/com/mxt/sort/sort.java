package com.mxt.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 */
public class sort {
    private static int[] array = {2,5,12,57,34,1,3};

    /**
     * 冒泡排序
     * 比较前一个元素与后一个元素的大小，交换位置
     */
    private static void bubbleSort() {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                int n = array[i];
                int m = array[j];
                if (m > n) {
                    array[i] = m;
                    array[j] = n;
                }
            }
        }
    }

    /**
     * 快速排序
     * 1、选择第一个数为p，小于p的数放在左边，大于p的数放在右边。
     * 2、递归的将p左边和右边的数都按照第一步进行，直到不能递归。
     */
    private static void quickSort(int[] array, int start, int end) {
        int length = array.length;
        int mid = length / 2;
        int a = 0;
        int base = array[length / 2];
        for (int i = 0; i < mid; i++) {


        }

    }


    public static void main(String[] args) {
        bubbleSort();
        System.out.println(Arrays.toString(array));
    }
}
