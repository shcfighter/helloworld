package com.ecit.sort;

/**
 * 选择排序
 */
public class SelectionSort {
    public static int[] data = {80,1,5,3,8,4,9,3,0,5,2,10,8,20};

    /**
     * @param args
     */
    public static void main(String[] args) {
        printNums("排序前：");
        int n = data.length;
        for (int i = 0; i < n - 1; i++) {
            int index = i;
            for (int j = (i + 1); j < n; j++) {
                if (data[index] > data[j]) {
                    index = j;
                }
            }

            swap(i, index);
        }
        printNums("排序后：");
    }

    public static void swap(int i, int j){
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    public static void printNums(String str){
        System.out.print(str);
        for (int num: data) {
            System.out.print(num + ", ");
        }
        System.out.println();
    }
}
