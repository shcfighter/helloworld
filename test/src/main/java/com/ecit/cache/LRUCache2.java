package com.ecit.cache;

import java.util.HashMap;
import java.util.Map;

//评测题目: 无
//实现LRU cache
public class LRUCache2 {

	public static void main(String[] args) {
		LRUCache2 lruCache = new LRUCache2(5);
		lruCache.put("1",1);
		lruCache.put("2",2);
		lruCache.put("3",3);
		lruCache.put("4",4);
		lruCache.put("5",5);
		lruCache.put("6",6);
		lruCache.put("7",7);
		System.out.println(lruCache.get("1"));
		System.out.println(lruCache.get("2"));
		System.out.println(lruCache.get("3"));
		System.out.println(lruCache.get("5"));
	}

	private static Map<String, Node> cacheMap = new HashMap();

	private Node head;
	private Node tail;
	private int capacity;
	private int size = 0;

	public LRUCache2(int capacity) {
		this.capacity = capacity;
		this.head = new Node();
		this.tail = new Node();
		head.next = tail;
		tail.pre = head;
	}

	/**
	 * 如果key在cache中存在，获取该key的value，如果不存在返回-1
	 */
	public Integer get(String key) {
		if (!cacheMap.containsKey(key)) {
			return -1;
		}
		Node node = cacheMap.get(key);
		this.remove(node);
		this.add(node);
		return node.value;
	}
	/**
	 * 在cache中设置key以及它的value, 如果cache已经满了，需要将最近最少使用的缓存淘汰
	 */
	public void put(String key, Integer value) {
		if (!cacheMap.containsKey(key)) {
			if (size >= capacity) {
				this.remove(tail.pre);
			}
			Node node = new Node(key, value);
			this.add(node);
			size++;
			return;
		}

		Node currentNode = cacheMap.get(key);
		this.remove(currentNode);
		this.add(new Node(key, value));
	}

	private void add(Node node){
		node.pre = head;
		node.next = head.next;
		head.next.pre = node;
		head.next = node;

		cacheMap.put(node.key, node);
	}

	private void remove(Node node){
		Node preNode = node.pre;
		Node nextNode = node.next;
		preNode.next = nextNode;
		nextNode.pre = preNode;

		cacheMap.remove(node.key);
	}

	class Node {
		private String key;
		private int value;
		private Node pre;
		private Node next;

		public Node() {
		}

		public Node(String key, int value) {
			this.key = key;
			this.value = value;
		}
	}
}

