package com.zrodo.agriculture.repository;

import com.zrodo.agriculture.entity.TObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CacheMapper {
    List<Map<String, Object>> queryProductType();

    List<TObject> queryObjectList(@Param("type") Integer type);

    List<Map<String, Object>> queryDutyTypeList();

    List<Map<String, Object>> querySampleTacheList();

    List<Map<String, Object>> querySampleNoList();

    List<Map<String, Object>> queryResultList();

    List<Map<String, Object>> queryItemList();

    List<Map<String, Object>> queryReagentList();

    List<Map<String, Object>> querySourceList();
}
