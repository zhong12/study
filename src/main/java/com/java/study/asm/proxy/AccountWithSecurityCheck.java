package com.java.study.asm.proxy;

/**
 * Created by zhongjing on 2017/11/17.
 */
public class AccountWithSecurityCheck implements Account {


    private  Account account;
    public AccountWithSecurityCheck (Account account) {
        this.account = account;
    }
    
    @Override
    public void operation() {
        SecurityChecker.checkSecurity();
        account.operation();
    }
}
