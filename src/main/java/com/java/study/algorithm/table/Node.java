package com.java.study.algorithm.table;

import lombok.Data;

@Data
public class Node {
    private int value;
    private Node next;

    public Node(int value) {
        this.value = value;
    }
}
