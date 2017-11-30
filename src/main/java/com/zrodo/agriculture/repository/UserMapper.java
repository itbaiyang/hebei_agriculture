package com.zrodo.agriculture.repository;

import com.zrodo.agriculture.domain.AccountInfo;
import com.zrodo.agriculture.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {
    public int addUser(SysUser sysUser);

    public int deleteUser(Integer userId);

    public int updateUser(SysUser sysUser);

    public List<Map<String,Object>> queryUserList(@Param("companyId") Integer companyId,
                                                  @Param("deptId") Integer deptId,
                                                  @Param("startNo") Integer startNo,
                                                  @Param("pageSize") Integer pageSize);

    public Map<String,Object>queryUserById(Integer userId);

    public AccountInfo queryUserByAccount(String account);
}
