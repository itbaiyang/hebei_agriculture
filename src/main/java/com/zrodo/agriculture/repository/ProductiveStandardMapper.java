package com.zrodo.agriculture.repository;

import com.zrodo.agriculture.entity.ProductiveStandard;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProductiveStandardMapper {
    public int addProductiveStandard(ProductiveStandard productiveStandard);

    public int deleteProductiveStandard(Integer standardId);

    public int updateProductiveStandard(ProductiveStandard productiveStandard);

    public List<Map<String,Object>> queryProductiveStandardList(@Param("objectId") Integer objectId,
                                                                @Param("productTypeId") Integer productTypeId,
                                                                @Param("startDate") String startDate,
                                                                @Param("endDate") String endDate,
                                                                @Param("startNo") Integer startNo,
                                                                @Param("pageSize") Integer pageSize);

    public Map<String,Object>queryProductiveStandardById(Integer standardId);
}
