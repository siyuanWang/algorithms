package com.wsy.learn.algorithms.sort;

public class TestMaoPaoSort {
    static void sort(int[] array) {
        //比较length - 1次
        for (int i = 1; i < array.length; i++) {
            //每次比较最后的元素都不需要再次比较了，所以每次的比较次数会依次减1
            for (int j = 0; j < array.length - i; j++) {
                if (array[j] < array[j + 1]) {
                    int t = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = t;
                }
            }
        }
    }

    static void quickSort(int[] array, int left, int right) {
        if(left > right) {
            return;
        }
        int i = left, j = right, temp = array[left];
        while (i != j) {
            while (array[j] >= temp && i < j)
                j--;
            while (array[i] <= temp && i < j)
                i++;
            if (i < j) {
                int t = array[i];
                array[i] = array[j];
                array[j] = t;
            }
        }
        array[left] = array[i];
        array[i] = temp;

        quickSort(array, left, i - 1);
        quickSort(array, i + 1, right);
    }

    static void quickDescSort(int[] array, int left, int right) {
        if(left > right) {
            return;
        }
        int i = left, j = right, temp = array[left];
        while (i != j) {
            while (array[j] <= temp && i < j)
                j--;
            while (array[i] >= temp && i < j)
                i++;
            if (i < j) {
                int t = array[i];
                array[i] = array[j];
                array[j] = t;
            }
        }
        array[left] = array[i];
        array[i] = temp;

        quickDescSort(array, left, i - 1);
        quickDescSort(array, i + 1, right);
    }

    public static void main(String[] args) {
        int[] array = {2, 4, 5, 1, 3};
        quickDescSort(array, 0, array.length - 1);
        for (int a : array) {
            System.out.print(a + ",");
        }
    }
}
