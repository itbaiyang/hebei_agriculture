package com.zrodo.agriculture.repository;

import com.zrodo.agriculture.entity.SysArea;
import com.zrodo.agriculture.entity.SysCity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AreaMapper {
    void insertArea(SysArea sysArea);

    int deleteArea(@Param("areaId") Integer areaId);

    void deleteAreaList(@Param("areaNo") Integer areaNo);

    int updateArea(SysArea sysArea);

    List<Map<String, Object>> queryAreaList(@Param("pId") Integer pId,
                                                  @Param("areaNo") Integer areaNo,
                                                  @Param("startNo") Integer startNo,
                                                  @Param("pageSize") Integer pageSize);

    List<Map<String, Object>> queryAreaListByLevel(@Param("levelId") Integer levelId);

    Map<String, Object> queryAreaById(Integer areaId);

    List<SysCity> getCityListById(@Param("cityId") Integer cityId);
}
