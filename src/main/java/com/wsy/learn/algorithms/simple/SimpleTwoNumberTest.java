package com.wsy.learn.algorithms.simple;

import java.util.HashMap;

public class SimpleTwoNumberTest {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 26;
        int[] result = twoSum(nums, target);
        if(result != null) {
            for(int i : result) {
                System.out.println(i);
            }
        } else {
            System.out.println("没有结果");
        }
    }

    static int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for(int i = 0; i < nums.length; i++) {
            Integer index = map.get(target - nums[i]);
            if(index != null) {
                return new int[]{i, index};
            }
        }

        return null;
    }
}
