package org.cabbage.algorithm.sort;

/**
 * @author xzcabbage
 * @since 2025/7/22
 */
public class ShellSort {
    // 1、选择一个增量序列，用于将列表分成若干个字列表。常见的增量序列有希尔增量(n/2，n/4……1）
    // 2、按照增量序列将列表分成若干子列表，对每个子列表进行插入排序
    // 3、逐步缩小增量，重复上述分组和排序的过程，直到增量为1
    // 4、当增量为1时，对整个列表进行一次插入排序，完成排序

    private void shellSort(int[] arr) {
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // 对每个子序列进行插入排序
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j = i;
                while (j >= gap && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        ShellSort shellSort = new ShellSort();
        int[] array = new int[]{3, 4, 5, 2, 1, 6, 7, 8, 9, 0, 1, 12, 234};
        shellSort.shellSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
