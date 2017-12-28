package com.zrodo.agriculture.entity;

import java.util.Date;

public class SysRole {

    private int roleId;

    private String roleName;

    private String expl;

    private int level;

    private Date createDate;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getExpl() {
        return expl;
    }

    public void setExpl(String expl) {
        this.expl = expl;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
