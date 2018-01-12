package com.zrodo.agriculture.repository;

import com.zrodo.agriculture.entity.ProductiveStandard;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductiveStandardMapper {
    public int addProductiveStandard(ProductiveStandard productiveStandard);

    public int deleteProductiveStandard(Integer standardId);

    public int updateProductiveStandard(ProductiveStandard productiveStandard);

    public List<Map<String, Object>> queryProductiveStandardList(@Param("companyId") Integer companyId,
                                                                 @Param("objectId") Integer objectId,
                                                                 @Param("productType") Integer productType,
                                                                 @Param("startDate") String startDate,
                                                                 @Param("endDate") String endDate,
                                                                 @Param("startNo") Integer startNo,
                                                                 @Param("pageSize") Integer pageSize);

    public Map<String,Object>queryProductiveStandardById(Integer standardId);
}
