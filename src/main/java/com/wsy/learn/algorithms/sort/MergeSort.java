package com.wsy.learn.algorithms.sort;

/**
 * 归并排序
 */
public class MergeSort {

    public void sort(int[] array, int first, int end) {
        if (first < end) {
            int mid = (first + end) / 2;

            sort(array, first, mid);
            sort(array, mid + 1, end);

            merge(array, first, mid, end);
        }
    }

    /**
     * case0 0-7 8个元素
     * case1 0-8 9个元素
     * case2 0-9 10个元素
     * case3 0-10 11个元素
     *
     * @param array
     * @param first
     * @param mid
     * @param end
     */
    public void merge(int[] array, int first, int mid, int end) {
        /**
         * case0 mid = 3 ,length = 4
         * case1 mid = 4 ,length = 5
         * case2 mid = 4 ,length = 5
         * case3 mid = 5 ,length = 6
         * 各加一个哨兵位
         */
        int[] left = new int[mid - first + 2];
        /**
         * case0 mid = 3 ,length = 4
         * case1 mid = 4 ,length = 4
         * case2 mid = 4 ,length = 5
         * case3 mid = 5 ,length = 5
         * 各加一个哨兵位
         */
        int[] right = new int[end - mid + 1];
        int guide = 9999;
        int i = 0, j = 0;

        for (; i < mid - first + 1; i++) {
            left[i] = array[first + i];
        }
        for (; j < end - mid; j++) {
            right[j] = array[mid + j + 1];
        }
        left[i] = guide;
        right[j] = guide;

        System.out.print("left = ");
        for(int a : left) {
            System.out.print(a + ",");
        }
        System.out.println("");
        System.out.print("right = ");
        for(int a : right) {
            System.out.print(a + ",");
        }
        System.out.println("");
        System.out.println("---------");

        i = 0;
        j = 0;
        for (int k = first; k < end + 1; k++) {
            if (left[i] >= right[j]) {
                array[k] = right[j];
                j++;
            } else {
                array[k] = left[i];
                i++;
            }
        }
    }

    public static void main(String[] args) {
        MergeSort mergeSort1 = new MergeSort();
        //int[] array = {1, 3, 5, 7, 2, 4, 6, 8};
        //int[] array = {1,5,6,9,3,7,4,8,10};
        int[] array = {33,104,67,31,5,19,66,89,106,456};
        //mergeSort1.sort(array, 0, array.length - 1);

        mergeSort1.sort(array, 0, array.length - 1);
        System.out.println("final result:------");
        for (int a : array) {
            System.out.print(a + ",");
        }
    }
}
