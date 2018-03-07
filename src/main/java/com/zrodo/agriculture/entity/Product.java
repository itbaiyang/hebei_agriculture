package com.zrodo.agriculture.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
public class Product {
    @ApiModelProperty(name = "id", value = "自增id（修改删除）", required = false)
    private int id;
    @ApiModelProperty(name = "productNo", value = "产品编号", hidden = true, required = false)
    private String productNo;
    @ApiModelProperty(name = "objectId", value = "产品名称id", hidden = true, required = false)
    private int objectId;
    private int productTypeId;
    private int cUserId;
    private Date createDate;
    private int CompanyId;
    private String description;
    private int dutyTypeId;
    private String processNo;
    private int standardId;
    private int acreId;
    private String serialNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }

    public int getcUserId() {
        return cUserId;
    }

    public void setcUserId(int cUserId) {
        this.cUserId = cUserId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(int companyId) {
        CompanyId = companyId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDutyTypeId() {
        return dutyTypeId;
    }

    public void setDutyTypeId(int dutyTypeId) {
        this.dutyTypeId = dutyTypeId;
    }

    public String getProcessNo() {
        return processNo;
    }

    public void setProcessNo(String processNo) {
        this.processNo = processNo;
    }

    public int getStandardId() {
        return standardId;
    }

    public void setStandardId(int standardId) {
        this.standardId = standardId;
    }

    public int getAcreId() {
        return acreId;
    }

    public void setAcreId(int acreId) {
        this.acreId = acreId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
