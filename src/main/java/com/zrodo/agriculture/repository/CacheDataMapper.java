package com.zrodo.agriculture.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CacheDataMapper {
    public List<Map<String, Object>> getCity();

    public List<Map<String, Object>> getReagent();

    public List<Map<String, Object>> getItem();

    public List<Map<String, Object>> getSample();

    public List<Map<String, Object>> getDeptLevel();

    public List<Map<String, Object>> getObject();

    public List<Map<String, Object>> getSource();

    public List<Map<String, Object>> getResult();

    public List<Map<String, Object>> getTache();

    public Map<String, Object> getVideoServer(Integer serverId);

//	public List<Map<String,Object>> getSensitivity(@Param("product") String product);

    public List<Map<String, Object>> getDetReason();

    public List<Map<String, Object>> getCardBrand();

    public List<Map<String, Object>> getTaskGrade();

    public List<Map<String, Object>> getWeightUnit();

    public List<Map<String, Object>> getWeightMainUnit();

    public List<Map<String, Object>> getSingleWeightUnit();

    public List<Map<String, Object>> getProductDutyType();

    public List<Map<String, Object>> getSupervisionStatus();

    public List<Map<String, Object>> getSupervisionSubjects();

//	public List<Map<String,Object>> getProcessTaches(@Param("product") String product);
}
