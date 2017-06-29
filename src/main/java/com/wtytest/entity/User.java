package com.wtytest.entity;

import com.wtytest.listener.ContextListener;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by tywang on 15/06/2017.
 */
@Entity
@EntityListeners({ContextListener.class})
public class User implements Serializable{
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer age;
    // 省略构造函数

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    // 省略getter和setter

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}