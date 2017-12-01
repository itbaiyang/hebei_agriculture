package com.zrodo.agriculture.repository;

import com.zrodo.agriculture.entity.ProductiveStandardGlsq;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProductiveGlsqMapper {
    public int addGlsq(ProductiveStandardGlsq productiveStandardGlsq);

    public int deleteGlsq(Integer glsqId);

    public int updateGlsq(ProductiveStandardGlsq productiveStandardGlsq);

    public List<Map<String,Object>> queryGlsqListByStandard(@Param("standardId") Integer standardId,
                                                            @Param("startNo") Integer startNo,
                                                            @Param("pageSize") Integer pageSize);

    public Map<String,Object>queryGlsqById(Integer glsqId);
}
