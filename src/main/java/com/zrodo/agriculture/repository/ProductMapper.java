package com.zrodo.agriculture.repository;

import com.zrodo.agriculture.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProductMapper {

    public int addProduct(Product product);

    public int deleteProduct(Integer id);

    public int updateProduct(Product product);

    public List<Map<String,Object>> queryProductList(@Param("areaId") Integer areaId,
                                                     @Param("companyId") Integer companyId,
                                                     @Param("startDate") String startDate,
                                                     @Param("endDate") String endDate,
                                                     @Param("startNo") Integer startNo,
                                                     @Param("pageSize") Integer pageSize);

    public Map<String,Object>queryProductById(Integer id);
}
