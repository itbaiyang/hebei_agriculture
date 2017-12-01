package com.zrodo.agriculture.repository;

import com.zrodo.agriculture.entity.ReviewReport;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ReviewReportMapper {

    public int addReviewReport(ReviewReport reviewReport);

    public int deleteReviewReport(Integer id);

    public int updateReviewReport(ReviewReport reviewReport);

    public List<Map<String,Object>> queryReviewReportList(@Param("areaId") Integer areaId,
                                                          @Param("pId") Integer pId,
                                                          @Param("startNo") Integer startNo,
                                                          @Param("pageSize") Integer pageSize);

    public Map<String,Object>queryReviewReportById(Integer id);
    public Map<String,Object>queryReviewReportByDetectId(Integer detectId);
}
