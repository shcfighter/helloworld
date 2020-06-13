package com.ecit.link;

import java.util.Objects;

/**
 * 单链表反转
 */
public class SingleLinkedReverse {

    /**
     * 1->2->3
     * 时间复杂度：O(n)O(n)，假设 nn 是列表的长度，时间复杂度是 O(n)O(n)。
     * 空间复杂度：O(1)O(1)。
     */
    public static Node reverse(Node head){
        Node pre = null;
        Node next = null;
        while(null != head){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /***
     * 递归反转
     * 时间复杂度：O(n)O(n)，假设 nn 是列表的长度，那么时间复杂度为 O(n)O(n)。
     * 空间复杂度：O(n)O(n)，由于使用递归，将会使用隐式栈空间。递归深度可能会达到 nn 层。
     * @param head
     * @return
     */
    public static Node recursionReverse(Node head){
        if (head == null || head.next == null) return head;
        Node p = recursionReverse(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    //两两反转
    public static Node doubleReverse(Node head){
// 链表头增加虚拟结点 dummy
        Node dummy = new Node(-1, head);
        dummy.next = head;
        head = dummy;
        // 循环退出条件，注意链表结点数单双的情况
        while(head.next != null && head.next.next != null){
            // 开始反转
            Node a = head.next;
            Node b = a.next;
            head.next = b; // 步骤①
            a.next = b.next; // 步骤①
            b.next = a; // 步骤②
            // dummy 指针前移
            head = a;
        }
        return dummy.next;
    }

    /**
     * 前k个节点反转
     * @param root
     * @param k
     * @return
     */
    public static Node kReverse(Node root, int k){
        Node head = root;
        Node dummy = new Node(-1, null);
        Node tail = dummy;
        while (null != root) {
            Node node1 = root;
            Node tmp = kNode(root, k);
            root = tmp.next;
            tmp.next = null;
            Node node = reverse(node1);
            tail.next = node;
            tail = node1;
        }
        return dummy.next;
    }

    public static Node kNode(Node root, int k){
        Node node = root;
        for (int i = 0; i < k -1; i++) {
            if (null == node.next) {
                break;
            }
            node = node.next;
        }
        return node;
    }

    public static void print(Node node){
        if (Objects.isNull(node)) {
            return ;
        }
        System.out.print(node.value);
        if (Objects.nonNull(node.next)) {
            System.out.print("->");
            print(node.next);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node node3 = new Node(3, null);
        Node node2 = new Node(2, node3);
        Node node1 = new Node(1, node2);
        print(node1);
        print(reverse(node1));
        //print(recursionReverse(node1));
    }

}
