package com.wsy.learn.algorithms;

/**
 * 给40亿个不重复的无符号整数，没排过序。给一个无符号整数，如何快速判断一个数是否在这40亿个数中。
 */
public class BitMap {
    byte[] array = null;

    public BitMap(int length) {
        array = new byte[getIndex(length) + 1];
    }

    /**
     * 获取bitMap的索引值
     * n / 8
     *
     * @param value
     * @return
     */
    public int getIndex(int value) {
        return value >> 3;
    }

    /**
     * 获得数组中的坐标位置
     * n % 8
     * @param value
     * @return
     */
    public int getOffset(int value) {
        return value & 0x07;
    }

    public void set(int value) {
        int index = getIndex(value);
//        System.out.println("索引位置" + index);
        int offset = getOffset(value);
//        System.out.println("坐标位置" + offset);
        array[index] |= 1 << offset;

        System.out.println(array[index]);
    }

    public boolean get(int value) {
        int index = getIndex(value);
        int offset = getOffset(value);
        return (array[index] & (1 << offset)) != 0;
    }

    public static void main(String[] args) {
        BitMap bitMap = new BitMap(100);
        bitMap.set(20);
        bitMap.set(21);
        bitMap.set(22);

        System.out.println("exist=" + bitMap.get(20));
    }
}
