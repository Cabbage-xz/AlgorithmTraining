package org.cabbage.algorithm.sort;

/**
 * @author xzcabbage
 * @since 2025/7/22
 */
public class InsertionSort {
    // 1、将列表分为已排序部分和未排序部分。初始时，已排序部分只包含第一个元素
    // 2、从未排序部分中取出第一个元素
    // 3、将该元素与已排序部分的元素从后向前一次比较，找到合适的位置插入
    // 4、重复上述步骤，直到未排序的部分为空，列表完全有序
    private void insertionSort(int[] arr) {
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            int key = arr[i];
            int index = i - 1;
            while (index >= 0 && arr[index] > key) {
                arr[index + 1] = arr[index];
                index--;
            }
            arr[index + 1] = key;
        }
    }

    public static void main(String[] args) {
        InsertionSort insertionSort = new InsertionSort();
        int[] array = new int[]{3, 4, 5, 2, 1, 6, 7, 8, 9, 0, 1, 12, 234};
        insertionSort.insertionSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
