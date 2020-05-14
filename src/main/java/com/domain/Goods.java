package com.domain;

import java.io.Serializable;
import java.util.Date;

public class Goods implements Serializable {
    private String userId;
    private String goodsId;
    private String dscrip;
    private double price;
    private double goodsLevel;
    private int upStatus = 1;
    private int useStatus = 1;
    private double hot = 0;
    private String name;

    private String origin;
    private int type;
    private Date date;

    @Override
    public String toString() {
        return "Goods{" +
                "userId='" + userId + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", dscrip='" + dscrip + '\'' +
                ", price=" + price +
                ", goodsLevel=" + goodsLevel +
                ", upStatus=" + upStatus +
                ", useStatus=" + useStatus +
                ", hot=" + hot +
                ", name='" + name + '\'' +
                ", origin='" + origin + '\'' +
                ", type='" + type + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public Goods(String userId, String goodsId, String dscrip, double price, double goodsLevel, int upStatus, int useStatus, double hot, String name, String origin, int type, Date date) {
        this.userId = userId;
        this.goodsId = goodsId;
        this.dscrip = dscrip;
        this.price = price;
        this.goodsLevel = goodsLevel;
        this.upStatus = upStatus;
        this.useStatus = useStatus;
        this.hot = hot;
        this.name = name;
        this.origin = origin;
        this.type = type;
        this. date = date;
    }

    public Goods() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getDscrip() {
        return dscrip;
    }

    public void setDscrip(String dscrip) {
        this.dscrip = dscrip;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getGoodsLevel() {
        return goodsLevel;
    }

    public void setGoodsLevel(double goodsLevel) {
        this.goodsLevel = goodsLevel;
    }

    public int getUpStatus() {
        return upStatus;
    }

    public void setUpStatus(int upStatus) {
        this.upStatus = upStatus;
    }

    public int getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(int useStatus) {
        this.useStatus = useStatus;
    }

    public double getHot() {
        return hot;
    }

    public void setHot(double hot) {
        this.hot = hot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
