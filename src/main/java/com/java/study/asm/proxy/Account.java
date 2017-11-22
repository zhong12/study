package com.java.study.asm.proxy;

/**
 * Created by zhongjing on 2017/11/17.
 */
public interface Account {
    /**
     * 若想对operation加入对 SecurityCheck.checkSecurity()调用，标准的 Decorator(装饰) 需要先定义一个Account类的接口：
     */
    void operation();
}
