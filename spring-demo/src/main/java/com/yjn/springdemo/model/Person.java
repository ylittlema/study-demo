package com.yjn.springdemo.model;

import org.springframework.stereotype.Component;

/**
 * <p>类的说明</p>
 *
 * @author YuanJunNan
 * @author 其他作者姓名
 * @version 1.00  2015/12/8 YuanJunNan 创建
 *          <p>1.01 2015/12/8 修改者姓名 修改内容说明</p>
 */
@Component("person")
public class Person {
    private String name;
    private int age;
    private Adress adress;

    public Person() {
    }

    public Person(String name, int age, Adress adress) {
        this.name = name;
        this.age = age;
        this.adress = adress;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Person setAge(int age) {
        this.age = age;
        return this;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", adress=" + adress +
                '}';
    }
}
