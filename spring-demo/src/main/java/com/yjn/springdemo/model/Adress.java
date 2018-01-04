package com.yjn.springdemo.model;

import org.springframework.stereotype.Component;

/**
 * Created by yjn on 2016/3/2.
 */
@Component("adress")
public class Adress {
    private String tel;

    public Adress() {
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
