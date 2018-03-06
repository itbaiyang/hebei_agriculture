package com.zrodo.agriculture.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class ProductiveStandardGlsq {
    @ApiModelProperty(name = "glsqId", value = "管理时期id（删除修改用）", hidden = true)
    private int glsqId;
    @ApiModelProperty(name = "glsqName", value = "管理时期名称", required = true)
    private String glsqName;
    @ApiModelProperty(name = "standardId", value = "标准id")
    private int standardId;
    @ApiModelProperty(name = "subject", value = "操作主题", required = true)
    private String subject;
    @ApiModelProperty(name = "requirement", value = "标准要求", required = true)
    private String requirement;
    @ApiModelProperty(name = "createDare", value = "创建时间", hidden = true)
    private Date createDate;

    public int getGlsqId() {
        return glsqId;
    }

    public void setGlsqId(int glsqId) {
        this.glsqId = glsqId;
    }

    public String getGlsqName() {
        return glsqName;
    }

    public void setGlsqName(String glsqName) {
        this.glsqName = glsqName;
    }

    public int getStandardId() {
        return standardId;
    }

    public void setStandardId(int standardId) {
        this.standardId = standardId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
