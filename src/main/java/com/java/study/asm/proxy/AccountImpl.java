package com.java.study.asm.proxy;

/**
 * Created by zhongjing on 2017/11/17.
 */
public class AccountImpl implements Account {
    @Override
    public void operation() {
        System.out.println("operation...");
    }
}
