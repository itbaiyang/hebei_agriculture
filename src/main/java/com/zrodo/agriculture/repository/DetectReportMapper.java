package com.zrodo.agriculture.repository;

import com.zrodo.agriculture.entity.DetectReport;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DetectReportMapper {

    public int addDetectReport(DetectReport detectReport);

    public int deleteDetectReport(Integer detectId);

    public int updateDetectReport(DetectReport detectReport);

    public List<Map<String,Object>> queryDetectReportList(@Param("areaId") Integer areaId,
                                                          @Param("companyId") Integer companyId,
                                                          @Param("objectId") Integer objectId,
                                                          @Param("itemId") Integer itemId,
                                                          @Param("resultId") Integer resultId,
                                                          @Param("startDate") String startDate,
                                                          @Param("endDate") String endDate,
                                                          @Param("startNo") Integer startNo,
                                                          @Param("pageSize") Integer pageSize);

//    public List<Map<String,Object>> queryDetectReportListByProduct(@Param("productId")Integer productId,
//                                                                  @Param("startNo")Integer startNo,
//                                                                  @Param("pageSize")Integer pageSize);

    public List<Map<String,Object>> queryDetectReportListBySample(@Param("sampleId") Integer sampleId,
                                                                  @Param("startNo") Integer startNo,
                                                                  @Param("pageSize") Integer pageSize);

    public Map<String,Object>queryDetectReportById(Integer detectId);

}
