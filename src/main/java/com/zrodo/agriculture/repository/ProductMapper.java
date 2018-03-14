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

    int updateProduct(@Param("id") Integer id,
                      @Param("dutyTypeId") Integer dutyTypeId,
                      @Param("description") String description);

    List<Map<String, Object>> queryProductList(@Param("companyId") Integer companyId,
                                               @Param("productType") Integer productType,
                                               @Param("objectName") String objectName,
                                               @Param("dutyTypeId") Integer dutyTypeId,
                                               @Param("acreId") Integer acreId,
                                               @Param("resultId") Integer resultId,
                                               @Param("startDate") String startDate,
                                               @Param("endDate") String endDate,
                                               @Param("startNo") Integer startNo,
                                               @Param("pageSize") Integer pageSize);

    Integer queryProductListCount(@Param("companyId") Integer companyId,
                                  @Param("productType") Integer productType,
                                  @Param("objectName") String objectName,
                                  @Param("dutyTypeId") Integer dutyTypeId,
                                  @Param("acreId") Integer acreId,
                                  @Param("resultId") Integer resultId,
                                  @Param("startDate") String startDate,
                                  @Param("endDate") String endDate);


    Map<String, Object> queryProductById(Integer id);


    int existObject(@Param("objectName") String objectName);

    int getObjectId(@Param("objectName") String objectName);

    void insertObject(TObject tObject);

    String queryProductTypeById(@Param("id") int id);

    Integer getProductSequence(@Param("companyId") int companyId,
                               @Param("iyear") int iyear);
}
