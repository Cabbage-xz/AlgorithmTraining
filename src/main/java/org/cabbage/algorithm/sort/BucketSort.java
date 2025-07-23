package org.cabbage.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author xzcabbage
 * @since 2025/7/23
 */
public class BucketSort {
    // 1、根据数据的范围和分布，创建若干个桶
    // 2、遍历待排序的列表，将每个元素分配到对应的桶中
    // 3、堆每个桶中的元素进行排序
    // 4、将所有桶中的元素按顺序合并
    private void bucketSort(int[] array, int bucketSize) {
        if (array == null || array.length <= 1) {
            return;
        }
        int max = array[0];
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            max = Math.max(max, array[i]);
            min = Math.min(min, array[i]);
        }
        int bucketCount = (max - min) / bucketSize + 1;
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }
        for (int num : array) {
            buckets.get((num - min) / bucketSize).add(num);
        }
        int index = 0;
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
            for (Integer integer : bucket) {
                array[index++] = integer;
            }
        }
    }

    public static void main(String[] args) {
        BucketSort bucketSort = new BucketSort();
        int[] array = new int[]{3, 4, 5, 2, 1, 6, 7, 8, 9, 0, 1, 12, 234};
        bucketSort.bucketSort(array, 5);
        System.out.println(Arrays.toString(array));
    }
}
