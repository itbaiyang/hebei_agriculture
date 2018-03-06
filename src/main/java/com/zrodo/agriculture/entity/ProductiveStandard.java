package com.zrodo.agriculture.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class ProductiveStandard {
    @ApiModelProperty(name = "standardId", value = "标准id（删除修改用）", hidden = true)
    private int standardId;
    @ApiModelProperty(name = "name", value = "标准名称", required = true)
    private String name;
    @ApiModelProperty(name = "objectId", value = "产品名称id", required = true)
    private int objectId;
    @ApiModelProperty(name = "productTypeId", value = "产品类型id", required = true)
    private int productTypeId;
    @ApiModelProperty(name = "objectName", value = "产品名称", required = true)
    private String objectName;
    @ApiModelProperty(name = "cUserId", value = "创建人id", hidden = true)
    private int cUserId;
    @ApiModelProperty(name = "companyId", value = "企业Id", hidden = true)
    private int companyId;
    @ApiModelProperty(name = "createDare", value = "创建时间", hidden = true)
    private Date createDare;

    public int getStandardId() {
        return standardId;
    }

    public void setStandardId(int standardId) {
        this.standardId = standardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public int getcUserId() {
        return cUserId;
    }

    public void setcUserId(int cUserId) {
        this.cUserId = cUserId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public Date getCreateDare() {
        return createDare;
    }

    public void setCreateDare(Date createDare) {
        this.createDare = createDare;
    }
}
