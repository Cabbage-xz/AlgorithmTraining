package org.cabbage.algorithm.sort;

/**
 * @author xzcabbage
 * @since 2025/7/23
 */
public class HeapSort {
    // 1、创建一个堆
    // 2、把堆首（最大值）和堆尾互换
    // 3、把堆的尺寸缩小1，并重新调整堆结构
    // 4、重复步骤2 直到堆的尺寸为1
    private void heapSort(int[] array) {
        int length = array.length;
        // 从后往前第一个非叶子节点
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            // 构建堆
            heapify(array, length, i);
        }
        for (int i = length - 1; i >= 0; i--) {
            int temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            heapify(array, i, 0);
        }
    }

    private void heapify(int[] array, int length, int i) {
        // 默认其为最大值
        int largest = i;
        // 左右节点
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < length && array[left] > array[largest]) {
            largest = left;
        }
        if (right < length && array[right] > array[largest]) {
            largest = right;
        }
        if (largest != i) {
            int temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;
            // 递归修改受影响的子shu
            heapify(array, length, largest);
        }
    }
    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();
        int[] array = new int[]{3, 4, 5, 2, 1, 6, 7, 8, 9, 0, 1};
        heapSort.heapSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

}
