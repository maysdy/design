package com.domain;

import java.io.Serializable;
import java.util.Date;

public class Plan implements Serializable {
    private String planId;
    private String goodsId;
    private Date startdate;
    private Date enddate;
    private int number;
    private String land;
    private String area;
    private String name;

    @Override
    public String toString() {
        return "Goods{" +
                "planId='" + planId + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", startdate='" + startdate + '\'' +
                ", enddate=" + enddate +
                ", number=" + number +
                ", land=" + land +
                ", area=" + area +
                ", name=" + name +
                '}';
    }

    public Plan(String planId,String goodsId,Date startdate,Date enddate,int number,String land,String area,String name){
        this.planId = planId;
        this.goodsId = goodsId;
        this.startdate = startdate;
        this.enddate = enddate;
        this.number = number;
        this.land = land;
        this.area = area;
        this.name = name;
    }

    public Plan(){

    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
