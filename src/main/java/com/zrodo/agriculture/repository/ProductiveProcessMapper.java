package com.zrodo.agriculture.repository;

import com.zrodo.agriculture.entity.ProductiveProcess;
import com.zrodo.agriculture.entity.ProductiveProcessAccessory;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProductiveProcessMapper {

    public int addProductiveProcess(ProductiveProcess productiveProcess);

    public int deleteProductiveProcess(Integer processId);

    public int updateProductiveProcess(ProductiveProcess productiveProcess);

    public List<Map<String,Object>> queryProductiveProcessList(@Param("productId") Integer productId);

    public Map<String,Object>queryProductiveProcessById(Integer processId);


    public int addProductiveProcessAccessory(ProductiveProcessAccessory productiveProcessAccessory);

    public int deleteProductiveProcessAccessory(Integer accessoryId);

    public int updateProductiveProcessAccessory(ProductiveProcessAccessory productiveProcessAccessory);

}
