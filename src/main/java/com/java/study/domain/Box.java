package com.java.study.domain;

import java.io.Serializable;

/**
 * Created by zhongjing on 2016/06/28 0028.
 */
public class Box<T> implements Serializable {
    private T data;

    public Box() {

    }

    public Box(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
