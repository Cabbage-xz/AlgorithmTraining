package org.cabbage.algorithm.sort;

/**
 * @author xzcabbage
 * @since 2025/7/22
 */
public class BubbleSort {
    // 冒泡排序
    // 1、从列表的第一个元素开始 比较相邻的两个元素
    // 2、如果前一个比后一个元素大，则交换他们的位置
    // 3、对列表中每一对相邻元素重复上述步骤，直到列表末尾；最大的元素会被冒泡到最后一位
    // 4、忽略已经排序好的最后一个元素 重复上述位置
    private void bubbleSort(int[] array) {
        int n = array.length;
        // 需要将n-1个元素排到最后位置
        for (int i = n - 1; i > 0; i--) {
            boolean swapped = false;
            for (int j = 0; j <= i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        int[] array = new int[]{3, 4, 5, 2, 1, 6, 7, 8, 9, 0, 1, 12, 234};
        bubbleSort.bubbleSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
