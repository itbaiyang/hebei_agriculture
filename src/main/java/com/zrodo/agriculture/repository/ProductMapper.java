package com.zrodo.agriculture.repository;

import com.zrodo.agriculture.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductMapper {

    public int addProduct(Product product);

    public int deleteProduct(Integer id);

    public int updateProduct(Product product);

    public List<Map<String, Object>> queryProductList(@Param("companyId") Integer companyId,
                                                      @Param("productType") Integer productType,
                                                      @Param("startDate") String startDate,
                                                      @Param("endDate") String endDate,
                                                      @Param("startNo") Integer startNo,
                                                      @Param("pageSize") Integer pageSize);

    public Map<String, Object> queryProductById(Integer id);

    public List<Map<String, Object>> queryProductType();
}
