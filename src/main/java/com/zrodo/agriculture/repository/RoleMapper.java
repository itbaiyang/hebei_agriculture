package com.zrodo.agriculture.repository;

import com.zrodo.agriculture.entity.SysRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface RoleMapper {

    public int addRole(SysRole sysRole);

    public int deleteRole(Integer roleId);

    public int updateRole(SysRole sysRole);

    public List<Map<String,Object>> queryRoleList(@Param("startNo") Integer startNo,
                                                  @Param("pageSize") Integer pageSize);

    public List<Map<String,Object>> queryRoleListByLevel(@Param("levelId") Integer levelId,
                                                         @Param("type") Integer type,
                                                         @Param("startNo") Integer startNo,
                                                         @Param("pageSize") Integer pageSize);

    public Map<String,Object>queryRolerById(Integer roleId);
}
