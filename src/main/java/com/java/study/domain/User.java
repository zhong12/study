package com.java.study.domain;

import java.io.Serializable;

/**
 * Created by zhongjing on 2016/06/28 0028.
 */
public class User implements Serializable {
    private Integer age;
    private String grade;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
