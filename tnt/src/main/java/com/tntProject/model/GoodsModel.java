package com.tntProject.model;

import javax.persistence.*;

/**
 * Created by 18487 on 2019/7/25.
 */
@Entity //注册成实体类
@Table(name="goods")
public class GoodsModel {


    @Id //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增
    @Column(name = "id",nullable = false)
    private int id;
    @Column(name = "gname",nullable = false) //货品名
    private String gname;
    @Column(name = "date",nullable = false) // 过期时间
    private String date;
    @Column(name = "stock",nullable = false) //库存
    private String stock;
    @Column(name = "price",nullable = false)  //售价
    private String price;
    @Column(name = "uid",nullable = false)
    private String uid;  //字段关联、约束、索引（不推荐使用外键）
    @Column(name = "number",nullable = false)  //number
    private String number;

    @Override
    public String toString() {
        return "GoodsModel{" +
                "id=" + id +
                ", gname='" + gname + '\'' +
                ", date='" + date + '\'' +
                ", stock='" + stock + '\'' +
                ", price='" + price + '\'' +
                ", uid='" + uid + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
