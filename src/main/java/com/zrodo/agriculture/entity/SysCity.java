package com.zrodo.agriculture.entity;

public class SysCity {

    private int id;
    private int cityId;
    private String cityName;
    private int pId;
    private int Zone;
    private int root;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public int getZone() {
        return Zone;
    }

    public void setZone(int zone) {
        Zone = zone;
    }

    public int getRoot() {
        return root;
    }

    public void setRoot(int root) {
        this.root = root;
    }
}
