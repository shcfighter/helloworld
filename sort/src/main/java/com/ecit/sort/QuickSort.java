package com.ecit.sort;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = new int[] {5,3,8,4,9,3,0,80,1,5,2,10,8,20};
        System.out.println(Arrays.toString(arr));
        quickSort(arr,0,arr.length - 1);
        System.out.println(Arrays.toString(arr));

    }
    public static void quickSort(int[] arr,int low,int high) {
        int base,i,j,temp;

        if(low >= high) {
            return;
        }
        //base就是基准数,这里就是每个数组的第一个
        base = arr[low];
        i = low;
        j = high;
        while(i < j) {
            //右边当发现小于p的值时停止循环
            while(arr[j] >= base && i < j) {
                j--;
            }

            //这里一定是右边开始，上下这两个循环不能调换（下面有解析，可以先想想）

            //左边当发现大于p的值时停止循环
            while(arr[i] <= base && i < j) {
                i++;
            }

            temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }
        arr[low] = arr[i];//这里的arr[i]一定是停小于p的，经过i、j交换后i处的值一定是小于p的(j先走)
        arr[i] = base;
        quickSort(arr,low,j-1);  //对左边快排
        quickSort(arr,j+1,high); //对右边快排

    }
}
