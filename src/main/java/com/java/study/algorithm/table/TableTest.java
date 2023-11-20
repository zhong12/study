package com.java.study.algorithm.table;

public class TableTest {

    public static void main(String[] args) {
        Node node0 = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node0.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        System.out.println("反转前=" + node0);
        Node reversalNode = TableService.reversal(node0);
        System.out.println("反转后=" + reversalNode);
    }



}
