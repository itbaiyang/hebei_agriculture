package com.zrodo.agriculture.repository;

import com.zrodo.agriculture.entity.SysArea;
import com.zrodo.agriculture.entity.SysCity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AreaMapper {
    public void insertArea(SysArea sysArea);

    public int deleteArea(@Param("areaId")Integer areaId);

    public void deleteAreaList(@Param("areaNo")Integer areaNo);

    public int updateArea(SysArea sysArea);

    public List<Map<String,Object>> queryAreaList(@Param("pId") Integer pId,
                                                  @Param("areaNo") Integer areaNo,
                                                  @Param("startNo") Integer startNo,
                                                  @Param("pageSize") Integer pageSize);

    public Map<String,Object> queryAreaById(Integer areaId);

    public List<SysCity> getCityListById(@Param("cityId") Integer cityId);
}
