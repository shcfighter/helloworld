package com.ecit;

import java.util.Arrays;

public class QTest {

    public static void main(String[] args) throws Exception {
        int[] data = {4, 9, 5, 3, 8, 1, 10, 7, 2, 6};
        quickSort(data, 0, data.length - 1);
        System.out.println(Arrays.toString(data));
    }

    public static void quickSort(int[] data, int start, int end) {
        if (data.length <= 1 || start >= end) {
            return;
        }
        int middle = partition(data, start, end);
        quickSort(data, start, middle);
        quickSort(data, middle + 1, end);
    }

    public static int partition(int[] data, int start, int end) {
        int base = start;
        int low = start;
        int high = end;
        while (high > low) {
            while (high > low && data[base] <= data[high]) {
                high--;
            }
            while (high > low && data[base] >= data[low]) {
                low++;
            }
            swap(data, low, high);
        }
        swap(data, base, low);
        return low;
    }


    public static void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}
