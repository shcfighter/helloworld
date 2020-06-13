package com.ecit;

/*
 * 题目：在一个长度为n+1的数组里的所有数字都在1到n的范围内，所以数组中至
 * 少有一个数字是重复的。请找出数组中任意一个重复的数字，但不能修改输入的
 * 数组。例如，如果输入长度为8的数组{2, 3, 5, 4, 3, 2, 6, 7}，那么对应的
 * 输出是重复的数字2或者3。
 */
public class Demo04 {

    /**
     * 找到数组中一个重复的数字
     * 返回-1代表无重复数字或者输入无效
     */
    public int getDuplicate(int[] arr) {
        if (arr == null || arr.length <= 0) {
            System.out.println("数组输入无效！");
            return -1;
        }
        for (int a : arr) {
            if (a < 1 || a > arr.length - 1) {
                System.out.println("数字大小超出范围！");
                return -1;
            }
        }
        int low = 1;
        int high = arr.length - 1; // high即为题目的n
        int mid, count;
        while (low <= high) {
            mid = ((high - low) >> 1) + low;
            count = countRange(arr, low, mid);
            if (low == high) {
                if (count > 1)
                    return low;
                else
                    break; // 必有重复，应该不会出现这种情况吧？
            }
            if (count > mid - low + 1) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 返回在[low,high]范围中数字的个数
     */
    public int countRange(int[] arr, int low, int high) {
        if (arr == null)
            return 0;

        int count = 0;
        for (int a : arr) {
            if (a >= low && a <= high)
                count++;
        }
        return count;
    }

    // ==================================测试代码==================================

    /**
     * 数组为null
     */
    public void test1() {
        System.out.print("test1：");
        int[] a = null;
        int dup = getDuplicate(a);
        if (dup >= 0)
            System.out.println("重复数字为：" + dup);
    }

    /**
     * 数组数字越界
     */
    public void test2() {
        System.out.print("test2：");
        int[] a = {2, 3, 5, 4, 3, 2, 6, 7};
        int dup = getDuplicate(a);
        if (dup >= 0)
            System.out.println("重复数字为：" + dup);
    }

    /**
     * 数组带重复数字
     */
    public void test3() {
        System.out.print("test3：");
        int[] a = {1, 3, 2, 4, 4};
        int dup = getDuplicate(a);
        if (dup >= 0)
            System.out.println("重复数字为：" + dup);
    }

    public static void main(String[] args) {
        Demo04 f2 = new Demo04();
        //f2.test1();
        f2.test2();
        //f2.test3();
    }
}