package com.zrodo.agriculture.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class Sample {
    @ApiModelProperty(name = "id", value = "自增id", hidden = true)
    private int id;
    @ApiModelProperty(name = "sampleNo", value = "样品编号", hidden = true)
    private int sampleNo;
    @ApiModelProperty(name = "productId", value = "产品Id")
    private int productId;
    @ApiModelProperty(name = "sampleId", value = "采样部位Id（蓄水）")
    private int sampleId;
    @ApiModelProperty(name = "tacheId", value = "采样环节")
    private int tacheId;
    @ApiModelProperty(name = "cUserId", value = "采样人")
    private int cUserId;
    @ApiModelProperty(name = "sampleDate", value = "采样时间")
    private String sampleDate;
    @ApiModelProperty(name = "createDate", value = "创建时间", hidden = true)
    private Date createDate;
//    @ApiModelProperty(name = "sampleUrl", value = "采样图片")
//    private String sampleUrl;
//    @ApiModelProperty(name = "sampleVideoUrl", value = "采样视频")
//    private String sampleVideoUrl;
//    @ApiModelProperty(name = "sampleSource", value = "采样来源")
//    private String sampleSource;
//    @ApiModelProperty(name = "sampleTemp", value = "采样温度")
//    private String sampleTemp;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(int sampleNo) {
        this.sampleNo = sampleNo;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getSampleId() {
        return sampleId;
    }

    public void setSampleId(int sampleId) {
        this.sampleId = sampleId;
    }

    public int getTacheId() {
        return tacheId;
    }

    public void setTacheId(int tacheId) {
        this.tacheId = tacheId;
    }

    public int getcUserId() {
        return cUserId;
    }

    public void setcUserId(int cUserId) {
        this.cUserId = cUserId;
    }


    public String getSampleDate() {
        return sampleDate;
    }

    public void setSampleDate(String sampleDate) {
        this.sampleDate = sampleDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
