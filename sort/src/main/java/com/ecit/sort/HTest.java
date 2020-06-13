package com.ecit.sort;

import java.util.Arrays;

public class HTest {

    public static void main(String[] args) {
        int[] data = { 10, 9, 5, 3, 8, 1, 4, 7, 2, 6 };
        sortHeap(data);
        System.out.println(Arrays.toString(data));
    }

    public static void sortHeap(int[] data){
        int n = data.length - 1;
        for (int i = (n - 1) / 2; i >= 0; i--) {
            heapify(data, i, n);
        }
        System.out.println(Arrays.toString(data));

        for (int i = n; i >= 0; i--) {
            swap(data, 0, i);
            heapify(data, 0, i-1);
        }
    }

    public static void heapify(int[] data, int n, int len){
        if (n > len) {
            return ;
        }
        int left = 2 * n + 1;
        int right = left + 1;
        int maxIndex = n;
        if (left <= len && data[left] > data[maxIndex]) {
            maxIndex = left;
        }
        if (right <= len && data[right] > data[maxIndex]) {
            maxIndex = right;
        }
        if (maxIndex != n) {
            swap(data, n, maxIndex);
            heapify(data, maxIndex, len);
        }
    }

    public static void swap(int[] data, int i, int j){
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}
