package com.ecit.sort;

/**
 * 冒泡排序
 */
public class BubbleSort {
    public static int[] data = {80,1,5,3,8,4,9,3,0,5,2,10,8,20};

    /**
     * @param args
     */
    public static void main(String[] args) {
        printNums("排序前：");
        int n = data.length;
        for (int i = 0; i < data.length - 1; i++) {// 控制趟数
            for (int j = 0; j < data.length - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    int tmp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = tmp;
                }
            }
        }
        printNums("排序后：");
    }

    public static void printNums(String str){
        System.out.print(str);
        for (int num: data) {
            System.out.print(num + ", ");
        }
        System.out.println();
    }
}
