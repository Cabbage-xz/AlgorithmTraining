package org.cabbage.algorithm.sort;

import java.util.Arrays;

/**
 * @author xzcabbage
 * @since 2025/7/23
 */
public class RadixSort {

    // 1、找到数组中最大数字的位数，确定需要排序的轮数
    // 2、从最低位开始，一次堆每一位进行排序
    // 3、每一轮排序后更新数组顺序 直到所有位数排序完成
    private void radixSort(int[] array) {
        int len = array.length;
        int max = array[0];
        for (int i = 1; i < len; i++) {
            max = Math.max(max, array[i]);
        }
        for (int exp = 1; max / exp > 0 ; exp *= 10) {
            // 计数排序
            countSortByDigit(array, exp);
        }
    }
    
    private void countSortByDigit(int[] array, int exp) {
        int len = array.length;
        int[] output = new int[len];
        int[] count = new int[10];
        
        for (int num : array) {
            count[num / exp]++;
        }
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // 从后往前构建输出数组
        for (int i = len - 1; i >= 0; i--) {
            output[count[(array[i] / exp) % 10] - 1] = array[i];
            count[(array[i] / exp) % 10]--;
        }
        System.arraycopy(output, 0, array, 0, len);
    }

    public static void main(String[] args) {
        RadixSort radixSort = new RadixSort();
        int[] array = new int[]{3, 4, 5, 2, 1, 6, 7, 8, 9, 0, 1};
        radixSort.radixSort(array);
        System.out.println(Arrays.toString(array));
    }
}
