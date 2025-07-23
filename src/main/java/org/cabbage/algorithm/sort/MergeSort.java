package org.cabbage.algorithm.sort;

/**
 * @author xzcabbage
 * @since 2025/7/23
 */
public class MergeSort {
    // 1、将待排序的数组分成两个子数组
    // 2、递归地对每个子数组进行排序
    // 3、将两个已排序的子数组合并成一个有序的数组
    private void mergeSort(int[] arr) {
        int length = arr.length;
        if (length <= 1) {
            return;
        }
        mergeSortHelper(arr, 0, length - 1);
    }
    private void mergeSortHelper(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSortHelper(arr, left, mid);
            mergeSortHelper(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1 , k = 0;
        // 合并两个子数组
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        System.arraycopy(temp, 0, arr, left, temp.length);
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] array = new int[]{3, 4, 5, 2, 1, 6, 7, 8, 9, 0, 1};
        mergeSort.mergeSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
