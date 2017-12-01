package com.zrodo.agriculture.repository;

import com.zrodo.agriculture.entity.Sample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SampleMapper {

    public int addSample(Sample sample);

    public int deleteSample(Integer id);

    public int updateSample(Sample sample);

    public List<Map<String,Object>> querySampleList(@Param("areaId") Integer areaId,
                                                    @Param("companyId") Integer companyId,
                                                    @Param("objectId") Integer objectId,
                                                    @Param("flag") Integer flag,
                                                    @Param("startDate") String startDate,
                                                    @Param("endDate") String endDate,
                                                    @Param("startNo") Integer startNo,
                                                    @Param("pageSize") Integer pageSize);

    public List<Map<String,Object>> querySampleListByProduct(@Param("productId") Integer productId,
                                                             @Param("startDate") String startDate,
                                                             @Param("endDate") String endDate,
                                                             @Param("startNo") Integer startNo,
                                                             @Param("pageSize") Integer pageSize);

    public Map<String,Object>querySampleById(Integer id);

}
