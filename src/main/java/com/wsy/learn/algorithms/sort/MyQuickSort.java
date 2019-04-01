package com.wsy.learn.algorithms.sort;

public class MyQuickSort {

    static void quickSort(int[] array, int left, int right) {
        if(left > right) return;
        int i = left, j = right;
        int temp = array[left];
        while(i != j) {
            //顺序很重要，要先从右边开始找，右边找比轴小的
            while(array[j] >= temp && i < j)
                j--;
            //左边找比轴大的
            while(array[i] <= temp && i < j)
                i++;
            //交换 i 和 j 的位置
            if(i < j) {
                int t = array[i];
                array[i] = array[j];
                array[j] = t;
            }
        }
        //如果i 和 j 相等 则交换left 和 轴的位置
        array[left] = array[i];
        array[i] = temp;
        //轴不参与递归
        //对轴左侧进行递归
        quickSort(array, left, i - 1);
        //对轴右侧进行递归
        quickSort(array, i + 1, right);

    }

    public static void main(String[] args) {
        int[] array = {6,1,2,7,9,3,4,5,10,8};
        quickSort(array, 0, array.length - 1);
        for(int a : array) {
            System.out.print(a + ",");
        }
    }
}
