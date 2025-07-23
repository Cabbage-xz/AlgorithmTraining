package org.cabbage.algorithm.sort;

/**
 * @author xzcabbage
 * @since 2025/7/23
 */
public class QuickSort {
    // 1、从列表中选择一个元素作为基准
    // 2、将列表重新排序，使得所有小于基准元素的元素都在基准的左侧，所有大于基准元素的元素都在基准右侧
    // 基准元素位置在分区完成后确定
    // 3、递归排序，对基准左侧和右侧的字列表分别递归的进行快速排序
    // 4、递归结束后即完成排序
    private void quickSort(int[] array) {
        int length = array.length;
        if (length <= 1) {
            return;
        }
        quickSortHelper(array, 0, length - 1);
    }

    private void quickSortHelper(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = partitionSinglePointer(array, left, right);
        quickSortHelper(array, left, pivot - 1);
        quickSortHelper(array, pivot + 1, right);
    }
    private int partition(int[] array, int left, int right) {
        int pivotNum = array[right];
        int i = left, j = right;
        while (i < j) {
            while (i < j && array[i] <= pivotNum) {
                i++;
            }
            while (i < j && array[j] >= pivotNum) {
                j--;
            }
            int temp = array[i];
            if (i < j) {
                array[i] = array[j];
                array[j] = temp;
            } else {
                array[i] = pivotNum;
                array[right] = temp;
            }
        }
        return i;
    }

    // 单指针
    private int partitionSinglePointer(int[] array, int left, int right) {
        int pivotNum = array[right];
        // 作为基准
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (array[j] <= pivotNum) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[right];
        array[right] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] array = new int[]{3, 4, 5, 2, 1, 6, 7, 8, 9, 0, 1};
        quickSort.quickSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
