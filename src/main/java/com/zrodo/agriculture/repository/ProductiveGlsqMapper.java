package com.zrodo.agriculture.repository;

import com.zrodo.agriculture.entity.ProductiveStandardGlsq;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductiveGlsqMapper {
    int addGlsq(ProductiveStandardGlsq productiveStandardGlsq);

    int deleteGlsq(Integer glsqId);

    int updateGlsq(@Param("glsqId") Integer glsqId,
                   @Param("glsqName") String glsqName,
                          @Param("subject") String subject,
                          @Param("requirement") String requirement);

    List<Map<String, Object>> queryGlsqListByStandard(@Param("standardId") Integer standardId);

    Map<String, Object> queryGlsqById(Integer glsqId);
}
