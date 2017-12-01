package com.zrodo.agriculture.repository;

import com.zrodo.agriculture.entity.SignIn;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SignInMapper {

    public int addSignIn(SignIn signIn);

    public List<Map<String, Object>> queryUserList(
            @Param("deptId") Integer deptId,
            @Param("userName") String userName,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate,
            @Param("startNo") Integer startNo,
            @Param("pageSize") Integer pageSize);

    public Map<String, Object> queryUserByUserId(Integer userId);
}
