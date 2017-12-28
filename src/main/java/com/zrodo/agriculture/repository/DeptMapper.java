package com.zrodo.agriculture.repository;

import com.zrodo.agriculture.entity.SysDept;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DeptMapper {

    void addDept(SysDept sysDept);

    void deleteDept(Integer deptId);

    void hiddenDept(Integer deptId, Boolean flag);

    void updateDept(SysDept sysDept);
    public List<Map<String,Object>> queryDeptList(@Param("areaId") Integer areaId,
                                                  @Param("pId") Integer pId,
                                                  @Param("levelId") Integer levelId);

    List<Map<String,Object>> queryDeptList(@Param("areaId") Integer areaId,
                                           @Param("pId") Integer pId,
                                           @Param("deptNo") Integer deptNo);
    Map<String,Object> queryDeptById(Integer deptId);
    public Map<String,Object>queryDeptById(Integer deptId);

    public String getMaxUnderDeptNo(@Param("pId")Integer pId);

    public String getDeptNoById(@Param("deptId")Integer deptId);

    public List<Map<String,Object>> queryLevelList();
}
