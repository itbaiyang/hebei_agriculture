package com.zrodo.agriculture.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class Sample {
    @ApiModelProperty(name = "id", value = "自增id", hidden = true)
    private Integer id;
    @ApiModelProperty(name = "sampleNo", value = "样品编号", hidden = true)
    private String sampleNo;
    @ApiModelProperty(name = "productId", value = "产品Id")
    private Integer productId;
    @ApiModelProperty(name = "sampleId", value = "采样部位Id（蓄水）")
    private Integer sampleId;
    @ApiModelProperty(name = "tacheId", value = "采样环节")
    private Integer tacheId;
    @ApiModelProperty(name = "cUserId", value = "采样人")
    private Integer cUserId;
    @ApiModelProperty(name = "sampleDate", value = "采样时间")
    private String sampleDate;
    @ApiModelProperty(name = "createDate", value = "创建时间", hidden = true)
    private Date createDate;
    @ApiModelProperty(name = "productTypeId", value = "产品id")
    private Integer productTypeId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getSampleId() {
        return sampleId;
    }

    public void setSampleId(Integer sampleId) {
        this.sampleId = sampleId;
    }

    public Integer getTacheId() {
        return tacheId;
    }

    public void setTacheId(Integer tacheId) {
        this.tacheId = tacheId;
    }

    public Integer getcUserId() {
        return cUserId;
    }

    public void setcUserId(Integer cUserId) {
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

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }
}
