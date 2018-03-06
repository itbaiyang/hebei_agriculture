package com.zrodo.agriculture.repository;

import com.zrodo.agriculture.entity.ProductiveStandardGlsq;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductiveGlsqMapper {
    public int addGlsq(ProductiveStandardGlsq productiveStandardGlsq);

    public int deleteGlsq(Integer glsqId);

    public int updateGlsq(@Param("glsqId") Integer glsqId,
                          @Param("name") String name,
                          @Param("subject") String subject,
                          @Param("requirement") String requirement);

    public List<Map<String, Object>> queryGlsqListByStandard(@Param("standardId") Integer standardId);

    public Map<String,Object>queryGlsqById(Integer glsqId);
}
