package com.zrodo.agriculture.repository;

import com.zrodo.agriculture.entity.Sample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SampleMapper {

    int addSample(Sample sample);

    void deleteSample(Integer id);

    int updateSample(@Param("id") Integer id,
                     @Param("sampleUrl") Integer sampleUrl);

    List<Map<String, Object>> querySampleList(@Param("areaId") Integer areaId,
                                              @Param("companyId") Integer companyId,
                                              @Param("productTypeId") Integer productTypeId,
                                              @Param("objectId") Integer objectId,
                                              @Param("resultId") Integer resultId,
                                              @Param("tacheId") Integer tacheId,
                                              @Param("startDate") String startDate,
                                              @Param("endDate") String endDate,
                                              @Param("startNo") Integer startNo,
                                              @Param("pageSize") Integer pageSize);

    int querySampleListCount(@Param("areaId") Integer areaId,
                             @Param("companyId") Integer companyId,
                             @Param("productTypeId") Integer productTypeId,
                             @Param("objectId") Integer objectId,
                             @Param("resultId") Integer resultId,
                             @Param("tacheId") Integer tacheId,
                             @Param("startDate") String startDate,
                             @Param("endDate") String endDate);

    List<Map<String, Object>> querySampleListByProduct(@Param("productId") Integer productId);

    Map<String, Object> querySampleById(Integer id);

    String getSampleNos(@Param("sampleNo") String sampleNo);

}
