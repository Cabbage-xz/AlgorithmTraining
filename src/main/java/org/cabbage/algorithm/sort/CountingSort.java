package org.cabbage.algorithm.sort;

import java.util.Arrays;

/**
 * @author xzcabbage
 * @since 2025/7/23
 */
public class CountingSort {
    // 1、遍历待排序的列表，统计每个元素出现的次数，存储在一个计数数组中
    // 2、将计数数组中的值累计，得到每个元素在排序后列表中的最后一个位置
    // 3、遍历待排序的列表，根据计数数组中的位置信息，将元素放到正确的位置
    private void countingSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        int max = array[0];
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            max = Math.max(max, array[i]);
            min = Math.min(min, array[i]);
        }
        int range = max - min + 1;
        int[] counts = new int[range];
        Arrays.fill(counts, 0);
        for (int j : array) {
            counts[j - min]++;
        }
        int arrIdx = 0, cntIdx = 0;
        while (cntIdx < counts.length) {
            while (counts[cntIdx]-- > 0) {
                array[arrIdx++] = cntIdx + min;
            }
            cntIdx++;
        }
    }

    public static void main(String[] args) {
        CountingSort countingSort = new CountingSort();
        int[] array = new int[]{3, 4, 5, 2, 1, 6, 7, 8, 9, 0, 1};
        countingSort.countingSort(array);
        System.out.println(Arrays.toString(array));
    }
}
