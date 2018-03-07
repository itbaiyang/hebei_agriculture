package com.zrodo.agriculture.entity;

import io.swagger.annotations.ApiModelProperty;

public class Acre {

    @ApiModelProperty(name = "acreId", value = "地块id（修改删除）", required = false)
    private int acreId;
    @ApiModelProperty(name = "acreName", value = "地块名称（可修改）", required = false)
    private String acreName;
    @ApiModelProperty(name = "companyId", value = "公司id（可修改）", required = false)
    private String companyId;
    @ApiModelProperty(name = "size", value = "地块大小（可修改）", required = false)
    private float size;
    @ApiModelProperty(name = "typeId", value = "地块类型id", required = false)
    private int typeId;

    @ApiModelProperty(name = "address", value = "地块位置", required = false)
    private String address;
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

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
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

    public String[] getImgList() {
        return imgList;
    }

    public void setImgList(String[] imgList) {
        this.imgList = imgList;
    }

}
