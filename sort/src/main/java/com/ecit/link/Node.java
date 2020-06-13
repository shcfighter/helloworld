package com.ecit.link;

import java.util.Objects;

public class Node {
    public int value;
    public Node next;

    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
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
}