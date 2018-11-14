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
            if (max[i] > best[2]) {
                best[0] = i;
                best[1] = num[i];
                best[2] = max[i];
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
     * @param A
     * @param low  数组的左边界
     * @param high 数组的右边界
     * @return
     */
    public int[] divideMerge(int[] A, int low, int high) {
        if (low == high)
            return new int[]{low, high, A[low]};
        else {
            int mid = (low + high) / 2;
            int[] resultLeft = divideMerge(A, low, mid);
            int left_sum = resultLeft[2];

            int[] resultRight = divideMerge(A, mid + 1, high);
            int right_sum = resultRight[2];

            int[] resultMid = accrossMiddleOfArray(A, low, high, mid);
            int mid_sum = resultMid[2];
            if (left_sum >= right_sum && left_sum >= mid_sum) {
                return resultLeft;
            } else if (right_sum >= left_sum && right_sum >= mid_sum) {
                return resultRight;
            }
            return resultMid;
        }

    }

    /**
     * 跨越数据A中线的可能
     *
     * @param A    数组
     * @param low  数组左边界
     * @param high 数组右边界
     * @param mid  中线
     * @return
     */
    private int[] accrossMiddleOfArray(int[] A, int low, int high, int mid) {
        int left = 0, right = 0, sumLeft = Integer.MIN_VALUE, sumRight = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= low; i--) {
            sum += A[i];
            if (sum > sumLeft) {
                sumLeft = sum;
                left = i;
            }
        }

        sum = 0;
        for (int j = mid + 1; j <= high; j++) {
            sum += A[j];
            if (sum > sumRight) {
                sumRight = sum;
                right = j;
            }
        }

        return new int[]{left, right, sumLeft + sumRight};
    }

    public static void main(String[] args) {
        //int[] arr = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        //int[] arr = {-1, 13, -30, 45, -10, -60};
        int[] arr = {-1, 13, -30};
        MaxSonArray maxson = new MaxSonArray();
        //int[] max = maxson.bestSimple(arr);
        int[] max = maxson.divideMerge(arr, 0, arr.length - 1);
        System.out.println("买入日期：" + max[0] + ";卖出日期：" + max[1] + "最大的利润：" + max[2]);
    }
}
