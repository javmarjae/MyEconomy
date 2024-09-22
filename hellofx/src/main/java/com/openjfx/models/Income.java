package com.openjfx.models;

import com.openjfx.models.Tag;

import java.util.Date;

public class Income {
    private int id;
    private String name;
    private double money;
    private Date date;
    private Tag tag;

    public Income() {
    }

    public Income(int id, String name, double money, Date date, Tag tag) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.date = date;
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Income [id=" + id + ", name=" + name + ", money=" + money + ", date=" + date + ", tag=" + tag + "]";
    }
}
