package com.zrodo.agriculture.entity;

import io.swagger.annotations.ApiModelProperty;

public class Acre {

    @ApiModelProperty(name = "acreId", value = "地块id", hidden = true)
    private int acreId;
    @ApiModelProperty(name = "acreName", value = "地块名称（可修改）", required = false)
    private String acreName;
    @ApiModelProperty(name = "companyId", value = "公司id", hidden = true)
    private int companyId;
    @ApiModelProperty(name = "size", value = "地块大小（可修改）", required = false)
    private float size;
    @ApiModelProperty(name = "typeId", value = "地块类型id", required = false)
    private int typeId;
    @ApiModelProperty(name = "address", value = "地块位置", required = false)
    private String address;
    @ApiModelProperty(name = "longitude", value = "经度", required = false)
    private String longitude;
    @ApiModelProperty(name = "latitude", value = "纬度", required = false)
    private String latitude;
    @ApiModelProperty(name = "imgList", value = "地块图片", required = false)
    private String[] imgList;

    public int getAcreId() {
        return acreId;
    }

    public void setAcreId(int acreId) {
        this.acreId = acreId;
    }

    public String getAcreName() {
        return acreName;
    }

    public void setAcreName(String acreName) {
        this.acreName = acreName;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }


    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String[] getImgList() {
        return imgList;
    }

    public void setImgList(String[] imgList) {
        this.imgList = imgList;
    }

}
