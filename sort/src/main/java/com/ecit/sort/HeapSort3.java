package com.ecit.sort;

import java.util.Arrays;

public class HeapSort3 {

	public static void main(String[] args) {
		int n[] = {5,3,8,4,9,3,0,80,1,5,2,10,8,20};
		heapsort(n);
		System.out.print("堆排序结果：");
		System.out.println(Arrays.toString(n));
	}

	/**
	 * 堆排序
	 *
	 * @param n 待排序数组
	 */
	public static void heapsort(int n[]) {
		for (int i = n.length - 1; i >= 1; i--) {
			buildHeap(n, i);
			swap(n, 0, i);
		}
	}

	/**
	 * @param n   待排序数组
	 * @param end 待排序数组末位下标
	 */
	public static void buildHeap(int n[], int end) {
		int len = end + 1;
		for (int i = len / 2 - 1; i >= 0; i--) {
			//堆中i节点对应的左右子节点l和r
			int l = 2 * i + 1;
			int r = l + 1;
			//指向较大数节点的指针
			int p = l;
			if (r <= len - 1 && n[l] < n[r]) {
				p = r;
			}
			if (n[i] < n[p]) {
				swap(n, i, p);
			}
		}
	}

	/**
	 * @param n 待排序数组
	 * @param i 待交换数字数组下标
	 * @param j 待交换数字数组下标
	 */
	private static void swap(int n[], int i, int j) {
		n[i] ^= n[j];
		n[j] ^= n[i];
		n[i] ^= n[j];
	}
}