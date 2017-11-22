package com.java.study.asm.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by zhongjing on 2017/11/17.
 * 
 * 普通的代理模式
 */
public class InvocationHandlerTest {


//  其不足之处在于：
//    Proxy 是面向接口的，所有使用Proxy的对象都必须定义一个接口，而且用这些对象的代码也必须是对接口编程的：Proxy生成的对象是接口一致的而不是对象一致的：例子中Proxy.newProxyInstance
//    生成的是实现Account接口的对象而不是 AccountImpl的子类。这对于软件架构设计，尤其对于既有软件系统是有一定掣肘的。
    
//    Proxy 毕竟是通过反射实现的，必须在效率上付出代价：
//              有实验数据表明，调用反射比一般的函数开销至少要大10倍。而且，从程序实现上可以看出，对proxy class的所有方法调用都要通过使用反射的invoke方法。因此，对于性能关键的应用，使
//          用 proxy class 是需要精心考虑的，以避免反射成为整个应用的瓶颈。

    public static void main(String[] args) {
        Account account = (Account) Proxy.newProxyInstance(Account.class.getClassLoader(), new Class[] { Account.class },
                new SecurityProxyInvocationHandler(new AccountImpl()));
        account.operation();
    }
}
