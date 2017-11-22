package com.java.study.asm.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by zhongjing on 2017/11/17.
 */
public class SecurityProxyInvocationHandler implements InvocationHandler {

    private Object proxyObject;
    
    public SecurityProxyInvocationHandler(Object o) {
        proxyObject = o;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (proxy instanceof Account && method.getName().equals("operation")) {
            SecurityChecker.checkSecurity();
        }
        return method.invoke(proxyObject, args);
    }
}
