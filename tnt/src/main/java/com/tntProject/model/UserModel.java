package com.tntProject.model;

import javax.persistence.*;

/**
 * Created by 18487 on 2019/7/24.
 */

@Entity //注册为实体类
@Table(name = "user")  //表名称
public class UserModel {
    //一、私有化属性
    @Id //映射该属性为主键
    @Column(name = "id",nullable = false) //设置列表列名，不为空
    @GeneratedValue(strategy = GenerationType.AUTO)//定制主键生成规则：使用mysql自己默认的规则：自动递增
    private int id;
    @Column(name = "username",nullable = false)
    private String username;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "role",nullable = false)
    private String role = "guest"; //以默认：普通用户注册

    //二、封装get set

//    //构造器:在实例化对象之前调用，传递数据，初始化属性
//    public UserModel(String username, String password, String role) {
//        this.username = username;
//        this.password = password;
//        this.role = role;
//    }



    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
