package com.server;

import java.util.Date;

public class Log {

    private int id;

    private String user;

    private double drawMoney;

    private double drawBefore;
    private double drawAfter;

    private Date time;

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getTime() {
        return time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public double getDrawAfter() {
        return drawAfter;
    }

    public void setDrawAfter(double drawAfter) {
        this.drawAfter = drawAfter;
    }

    public double getDrawBefore() {
        return drawBefore;
    }

    public void setDrawBefore(double drawBefore) {
        this.drawBefore = drawBefore;
    }

    public double getDrawMoney() {
        return drawMoney;
    }

    public void setDrawMoney(double drawMoney) {
        this.drawMoney = drawMoney;
    }

}
