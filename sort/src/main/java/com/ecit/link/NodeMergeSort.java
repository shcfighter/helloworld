package com.ecit.link;

/**
 * 单链表归并排序
 */
public class NodeMergeSort {

    public static void main(String[] args) {
        Node node3 = new Node(3, null);
        Node node2 = new Node(1, node3);
        Node node1 = new Node(2, node2);
        Node.print(node1);
        Node.print(sortList(node1));
    }

    public static Node sortList(Node head) {
        //采用归并排序
        if (head == null || head.next == null) {
            return head;
        }
        //获取中间结点
        Node mid = getMid(head);
        Node right = mid.next;
        mid.next = null;
        //合并
        return mergeSort(sortList(head), sortList(right));
    }

    /**
     * 获取链表的中间结点,偶数时取中间第一个
     *
     * @param head
     * @return
     */
    private static Node getMid(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        //快慢指针
        Node slow = head, quick = head;
        //快2步，慢一步
        while (quick.next != null && quick.next.next != null) {
            slow = slow.next;
            quick = quick.next.next;
        }
        return slow;
    }

    /**
     * 归并两个有序的链表
     *
     * @param head1
     * @param head2
     * @return
     */
    private static Node mergeSort(Node head1, Node head2) {
        Node p1 = head1;
        Node p2 = head2;
        Node head;
        //得到头节点的指向
        if (head1.value < head2.value) {
            head = head1;
            p1 = p1.next;
        } else {
            head = head2;
            p2 = p2.next;
        }

        Node p = head;
        //比较链表中的值
        while (p1 != null && p2 != null) {

            if (p1.value <= p2.value) {
                p.next = p1;
                p1 = p1.next;
                p = p.next;
            } else {
                p.next = p2;
                p2 = p2.next;
                p = p.next;
            }
        }
        //第二条链表空了
        if (p1 != null) {
            p.next = p1;
        }
        //第一条链表空了
        if (p2 != null) {
            p.next = p2;
        }
        return head;
    }
}
