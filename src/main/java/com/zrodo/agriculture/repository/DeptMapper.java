package com.zrodo.agriculture.repository;

import com.zrodo.agriculture.entity.SysDept;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DeptMapper {

    public int addDept(SysDept sysDept);

    public int deleteDept(Integer deptId);

    public int updateDept(SysDept sysDept);

    public List<Map<String,Object>> queryDeptList(@Param("areaId") Integer areaId,
                                                  @Param("pId") Integer pId,
                                                  @Param("startNo") Integer startNo,
                                                  @Param("pageSize") Integer pageSize);

    public Map<String,Object>queryDeptById(Integer deptId);
}
