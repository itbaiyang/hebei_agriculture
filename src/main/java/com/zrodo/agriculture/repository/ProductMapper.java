package com.zrodo.agriculture.repository;

import com.zrodo.agriculture.entity.Product;
import com.zrodo.agriculture.entity.TObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductMapper {

    int addProduct(Product product);

    int deleteProduct(Integer id);

    int updateProduct(Product product);

    List<Map<String, Object>> queryProductList(@Param("companyId") Integer companyId,
                                                      @Param("productType") Integer productType,
                                                      @Param("startDate") String startDate,
                                                      @Param("endDate") String endDate,
                                                      @Param("startNo") Integer startNo,
                                                      @Param("pageSize") Integer pageSize);

    Map<String, Object> queryProductById(Integer id);

    List<Map<String, Object>> queryProductType();

    int existObject(@Param("objectName") String objectName);

    int getObjectId(@Param("objectName") String objectName);

    void insertObject(TObject tObject);

    List<TObject> queryObjectList(@Param("type") Integer type);

    Integer getProductSequence(@Param("companyId") int companyId,
                               @Param("iyear") int iyear);
}
