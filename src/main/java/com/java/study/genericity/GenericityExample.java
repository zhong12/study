package com.java.study.genericity;

import com.java.study.domain.Box;

/**
 * Created by zhongjing on 2015/11/26 0026.
 */
public class GenericityExample {

    public static void main(String[] args){
        Box<String> name = new Box<String>("corn");
        Box<Integer> age = new Box<Integer>(712);

        System.out.println("name class:" + name.getClass());      // class com.apache.domain.Box
        System.out.println("age class:" + age.getClass());        // class com.apache.domain.Box
        System.out.println(name.getClass() == age.getClass());    // true
        System.out.println("name:"+name.getData()+"---"+"age:"+age.getData());//corn
    }
}
