package com.wsy.learn.algorithms.middle;

import java.util.HashSet;
import java.util.Set;

/**
 * 无重复字符串的最长子串
 * 滑动窗口算法
 */
public class LongestSubString {
    public static void main(String[] args) {
        String s = "abcca";
        myMethod(s);
        System.out.println(lengthOfLongestSubstring(s));
    }

    public static int myMethod(String s) {
        char[] chars = s.toCharArray();
        HashSet<Character> set = new HashSet<>();
        int length = chars.length;
        int i = 0, j = 0;
        int max = 1;
        int finalMax = 0;
        while (i < length && j < length) {
            if (set.contains(chars[j])) {
                set.remove(chars[j]);
                i++;
                max = 1;
                finalMax = Math.max(j - i + 1, max);
            } else {
                max++;
                set.add(chars[j++]);
            }
        }

        System.out.println(finalMax);
        return finalMax;
    }

    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }
}

