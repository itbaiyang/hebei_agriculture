package com.zrodo.agriculture.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

@ApiModel(value = "sysClientRole")
public class SysClientRole
{
	@ApiModelProperty(name = "roleId",value = "角色id，修改删除必填", required = false)
	private int roleId ;
	@ApiModelProperty(name = "roleName",value = "角色名称，增加修改必填", required = false)
	private String roleName ;
	@ApiModelProperty(name = "roleExpl",value = "角色说明", required = false)
	private String roleExpl ;
	@ApiModelProperty(name = "roleLevel",value = "角色等级，增加修改必填", required = false)
	private String roleLevel ;
	private String cdeptId ;
	private int cuserId ;
	private Timestamp cdate ;
	public int getRoleId()
	{
		return roleId;
	}
	public void setRoleId(int roleId)
	{
		this.roleId = roleId;
	}
	public String getRoleName()
	{
		return roleName;
	}
	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}
	public String getRoleExpl()
	{
		return roleExpl;
	}
	public void setRoleExpl(String roleExpl)
	{
		this.roleExpl = roleExpl;
	}
	public String getRoleLevel()
	{
		return roleLevel;
	}
	public void setRoleLevel(String roleLevel)
	{
		this.roleLevel = roleLevel;
	}
	public String getCdeptId()
	{
		return cdeptId;
	}
	public void setCdeptId(String cdeptId)
	{
		this.cdeptId = cdeptId;
	}
	public int getCuserId()
	{
		return cuserId;
	}
	public void setCuserId(int cuserId)
	{
		this.cuserId = cuserId;
	}
	public Timestamp getCdate()
	{
		return cdate;
	}
	public void setCdate(Timestamp cdate)
	{
		this.cdate = cdate;
	}
}
