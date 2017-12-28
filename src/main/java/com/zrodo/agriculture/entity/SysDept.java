package com.zrodo.agriculture.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class SysDept {
    @ApiModelProperty(name = "deptId",value = "部门自增id（修改删除）")
    private Integer deptId;
    @ApiModelProperty(name = "deptNo",value = "部门编号（不用）", hidden = true)
    private String deptNo;
    @ApiModelProperty(name = "deptName",value = "部门名称", required = true)
    private String deptName;
    @ApiModelProperty(name = "areaId",value = "区划Id", required = true)
    private Integer areaId;
    @ApiModelProperty(name = "pId",value = "上级部门Id", required = true)
    private Integer pId;
    @ApiModelProperty(name = "level",value = "部门级别", required = true)
    private Integer level;
    @ApiModelProperty(name = "address",value = "部门地址", required = true)
    private String address;
    @ApiModelProperty(name = "contact",value = "部门联系人", required = true)
    private String contact;
    @ApiModelProperty(name = "ContactNumber",value = "部门联系人电话", required = true)
    private String ContactNumber;
    @ApiModelProperty(name = "createDate",value = "创建时间", hidden = true)
    private Date createDate;
    @ApiModelProperty(name = "updateDate",value = "修改时间", hidden = true)
    private Date updateDate;
    @ApiModelProperty(name = "principal",value = "部门名称（不用）", hidden = true)
    private String principal;
    @ApiModelProperty(name = "principalNumber",value = "部门名称（不用）", hidden = true)
    private String principalNumber;

    private Integer[] productList;

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getPrincipalNumber() {
        return principalNumber;
    }

    public void setPrincipalNumber(String principalNumber) {
        this.principalNumber = principalNumber;
    }

    public Integer[] getProductList() {
        return productList;
    }

    public void setProductList(Integer[] productList) {
        this.productList = productList;
    }
}
