package com.zrodo.agriculture.entity;

import io.swagger.annotations.ApiModelProperty;

public class SysArea {

    @ApiModelProperty(name = "id",value = "id，修改必填", required = false)
    private int areaId;
    @ApiModelProperty(name = "areaNo",value = "区划编号全国统一编码", required = false)
    private int areaNo;
    @ApiModelProperty(name = "areaName",value = "区划名称", required = false)
    private String areaName;
    @ApiModelProperty(name = "pId",value = "上级部门id", required = false)
    private int pId;
    private int levelId;

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public int getAreaNo() {
        return areaNo;
    }

    public void setAreaNo(int areaNo) {
        this.areaNo = areaNo;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }
}
