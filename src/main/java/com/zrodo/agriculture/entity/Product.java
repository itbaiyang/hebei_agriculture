package com.zrodo.agriculture.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
public class Product {
    @ApiModelProperty(name = "id", value = "自增id（修改删除）", hidden = true)
    private int id;
    @ApiModelProperty(name = "productNo", value = "产品编号", hidden = true)
    private String productNo;
    @ApiModelProperty(name = "objectId", value = "产品名称id", required = true)
    private int objectId;
    @ApiModelProperty(name = "productTypeId", value = "产品类型id", required = true)
    private int productTypeId;
    @ApiModelProperty(name = "cUserId", value = "创建人", hidden = true)
    private int cUserId;
    @ApiModelProperty(name = "createDate", value = "创建时间", hidden = true)
    private Date createDate;
    @ApiModelProperty(name = "companyId", value = "企业Id", hidden = true)
    private int companyId;
    @ApiModelProperty(name = "description", value = "产品描述")
    private String description;
    @ApiModelProperty(name = "dutyTypeId", value = "责任类型", required = true)
    private int dutyTypeId;
    @ApiModelProperty(name = "processNo", value = "产品日期类编号", hidden = true)
    private String processNo;
    @ApiModelProperty(name = "standardId", value = "标准id", required = true)
    private int standardId;
    @ApiModelProperty(name = "owner", value = "责任人", required = true)
    private String owner;
    @ApiModelProperty(name = "acreId", value = "地块Id", required = true)
    private int acreId;
    @ApiModelProperty(name = "productDate", value = "预计产品生产时间", required = true)
    private String productDate;
    @ApiModelProperty(name = "serialNumber", value = "产品创建日序号", hidden = true)
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
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getAcreId() {
        return acreId;
    }

    public void setAcreId(int acreId) {
        this.acreId = acreId;
    }

    public String getProductDate() {
        return productDate;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
