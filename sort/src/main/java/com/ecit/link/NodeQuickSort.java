package com.ecit.link;

/**
 * 单链表快排
 */
public class NodeQuickSort {

    public static void main(String[] args) {
        Node node1 = new Node(6, null);
        Node node2 = new Node(2, node1);
        Node node3 = new Node(7, node2);
        Node node4 = new Node(10, node3);
        Node node5 = new Node(1, node4);
        Node node6 = new Node(8, node5);
        Node node7 = new Node(3, node6);
        Node node8 = new Node(5, node7);
        Node node9 = new Node(9, node8);
        Node node10 = new Node(4, node9);
        Node.print(node10);
        Node.print(sortList(node10));
    }

    public static Node sortList(Node head) {
        //采用快速排序
        quickSort(head, null);
        return head;
    }

    public static void quickSort(Node head, Node end) {
        if (head != end) {
            Node node = partition(head, end);
            quickSort(head, node);
            quickSort(node.next, end);
        }
    }

    public static Node partition(Node head, Node end) {
        Node p1 = head;
        Node p2 = head.next;

        //走到末尾才停
        while (p2 != end) {

            //大于key值时，p1向前走一步，交换p1与p2的值
            if (p2.value < head.value) {
                p1 = p1.next;

                int temp = p1.value;
                p1.value = p2.value;
                p2.value = temp;
            }
            p2 = p2.next;
        }

        //当有序时，不交换p1和key值
        if (p1 != head) {
            int temp = p1.value;
            p1.value = head.value;
            head.value = temp;
        }
        return p1;
    }
}
