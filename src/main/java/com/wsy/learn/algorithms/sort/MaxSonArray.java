package com.wsy.learn.algorithms.sort;

/**
 * 最大子数组问题
 */
public class MaxSonArray {

    /**
     * 暴力法，两层循环, 时间复杂度 O(n方)
     */
    public int[] bestSimple(int[] arr) {

        //定义最大值的数组
        int[] max = new int[arr.length];
        //定义卖出日期数组
        int[] num = new int[arr.length];
        for (int i = 0, length = arr.length; i < length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                arr[i] += arr[j];
                if (max[i] < arr[i]) {
                    max[i] = arr[i];
                    num[i] = j;
                }
            }
        }
        int[] best = new int[3];
        for (int i = 0; i < num.length; i++) {
            if (max[i] > best[0]) {
                best[0] = max[i];
                best[1] = i;
                best[2] = num[i];
            }
        }

        return best;
    }

    /**
     * 分治法
     * 下标中位数 mid，开始下标start, 结束下标end, 最大子数组左侧下标left, 最大子数组右侧下标right
     * 最大子数组情况：
     * 1、最大子数组在左侧 start <= left <=right <= mid<= end
     * 2、最大子数组在右侧 start <= mid <=left <=right <= end
     * 3、mid在最大子数组中 start <= left <= mid <=right <= end
     *
     * @param arr
     * @return
     */
//    public int[] divideMerge(int[] arr, int mid, int start, int end) {
//        return
//    }

    public static void main(String[] args) {
        int[] arr = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        MaxSonArray maxson = new MaxSonArray();
        int[] max = maxson.bestSimple(arr);
        System.out.println("最大的利润：" + max[0] + ";买入日期：" + max[1] + "卖出日期：" + max[2]);
    }
}
