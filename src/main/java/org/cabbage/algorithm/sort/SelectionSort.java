package org.cabbage.algorithm.sort;

/**
 * @author xzcabbage
 * @since 2025/7/22
 */
public class SelectionSort {
    // 选择排序
    // 1、将数组分为已排序部分和未排序部分；初始时已排序部分为空，未排序部分为整个列表
    // 2、将找到的最小元素与未排序部分的第一个元素交换位置
    // 3、将未排序部分的起始位置向后移动一位，扩大已排序部分的范围
    // 4、重复上述操作直到未排序部分为空

    private void selectionSort(int[] arr) {
        int len = arr.length;
        // 区分排序下标
        for (int i = 0; i < len; i++) {
            int minIndex = i;
            for (int j = i; j < len; j++) {
                // 找到未排序部分的最小值
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    public static void main(String[] args) {
        SelectionSort selectionSort = new SelectionSort();
        int[] array = new int[]{3, 4, 5, 2, 1, 6, 7, 8, 9, 0, 1};
        selectionSort.selectionSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
