package com.ecit;

import java.util.Arrays;

class Solution {
    public static int[] singleNumber(int[] nums) {
        // difference between two numbers (x and y) which were seen only once
        int bitmask = 0;
        for (int num : nums) bitmask ^= num;

        // rightmost 1-bit diff between x and y
        int diff = bitmask & (-bitmask);
        System.out.println("-bitmask:" + -bitmask);
        System.out.println("-bitmask:" + Integer.toBinaryString(-bitmask));
        System.out.println("bitmask:" + bitmask);
        System.out.println("bitmask:" + Integer.toBinaryString(bitmask));
        System.out.println("diff:" + diff);
        System.out.println("diff:" + Integer.toBinaryString(diff));

        int x = 0;
        // bitmask which will contain only x
        for (int num : nums){
            System.out.println("num:" + num + ",num:" + Integer.toBinaryString(num));
            if ((num & diff) != 0){
                System.out.println("num:" + num);
                x ^= num;
                System.out.println("x:" + x);
            }
        }

        return new int[]{x, bitmask ^ x};
    }

    public static void main(String[] args) {
        int i[] = {3, 1, 2, 1, 2, 5};
        System.out.println(Arrays.toString(Solution.singleNumber(i)));

    }
}