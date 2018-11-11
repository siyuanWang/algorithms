package com.wsy.learn.worksediment;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 基于步长的，分批调用RPC接口的复用代码
 * 场景：某RPC接口，最多支持批量30条调用
 */
public class RecursiveTest {

    /**
     * 基于步长方式的调用
     * @param list
     * @return
     */
    public List<String> getCoupon(List<Integer> list) {
        int size = list.size();
        int fromIndex = 0;
        int step = 5;
        if (size <= step) {
            return queryByRPC(list, 0, size);
        } else {
            int times = size / step;
            int remainder = size % step;
            List<String> finalResult = new ArrayList<>();
            for (int i = 0; i < times; i++) {
                finalResult.addAll(queryByRPC(list, fromIndex, fromIndex + step));
                fromIndex += step;
            }
            finalResult.addAll(queryByRPC(list, size - remainder, size));
            return finalResult;
        }

    }

    private List<String> queryByRPC(List<Integer> list, int fromIndex, int lastIndex) {
        return rpc(list.subList(fromIndex, lastIndex));
    }

    private List<String> rpc(List<Integer> list) {
        List<String> strs = new ArrayList<>();
        for (long i : list) {
            strs.add(i + "coupon");
        }
        return strs;
    }

    public static void main(String[] args) {
        RecursiveTest test = new RecursiveTest();
        //int[] array = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18};
        int[] array = {1,2,3,4,5,6,7,8,9,10,11,12,13};
        List<Integer> list = CollectionUtils.arrayToList(array);
        List<String> result = test.getCoupon(list);
        result.stream().forEach(x -> System.out.print(x + ","));
    }
}
