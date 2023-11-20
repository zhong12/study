package com.java.study.algorithm.table;

public class TableService {
    /**
     * 单向链表反转
     * @param head 链表
     * @return 反转后的链表
     */
    public static Node reversal(Node head){
        if (null == head){
            return null;
        }
        Node node = null;
        while (head != null){
            Node next = head.getNext();
            head.setNext(node);
            node = head;
            head = next;
        }
        return node;
    }
}
