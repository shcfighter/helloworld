package com.ecit.sort;

import java.util.Arrays;

public class Qtest {

    public static void main(String[] args) {
        int[] arr = { 10, 9, 5, 3, 8, 1, 4, 7, 2, 6 };
        System.out.println("排序之前：");
        System.out.println(Arrays.toString(arr));

        // 排序
        quickSort(arr, 0, arr.length - 1);

        System.out.println();
        System.out.println("排序之后：");
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] data, int start, int end){
        if (start > end) {
            return ;
        }
        int middle = partition(data, start, end);
        quickSort(data, start, middle - 1);
        quickSort(data, middle + 1, end);
    }

    public static int partition(int[] data, int start, int end) {
        int base = data[start];
        int left = start;
        int right = end;
        while (left < right) {
            while (right > left && data[right] >= base) {
                right--;
            }
            while (right > left && data[left] <= base) {
                left++;
            }
            swap(data, right, left);
        }
        swap(data, start, left);

        return left;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
