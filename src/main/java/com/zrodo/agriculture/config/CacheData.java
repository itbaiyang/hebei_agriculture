package com.zrodo.agriculture.config;

import com.zrodo.agriculture.repository.CacheDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据库不常变数据缓存类
 *
 * @author wangmin
 * <p>
 * 2016年7月19日
 */
@Service
public class CacheData {
    private static Map<String, Map<String, Object>> totalMap = new ConcurrentHashMap<String, Map<String, Object>>();

    @Autowired
    private CacheDataMapper cacheDataMapper;

    // 缓存数据源名称和地区关系
    private static Map<String, String> dataSourceMap = new HashMap<String, String>();

    @SuppressWarnings("unchecked")
    public void init() {
        String areaName = "cache";
        String product = "product";

        // 城市
        totalMap.get(areaName).put("city", cacheDataMapper.getCity());

        totalMap.get(areaName).put("deptlevel", cacheDataMapper.getDeptLevel());

        // 数据来源
        totalMap.get(areaName).put("source", cacheDataMapper.getSource());

        // 检测结果
        totalMap.get(areaName).put("result", cacheDataMapper.getResult());

        // 检测环节
        totalMap.get(areaName).put("huanjie", cacheDataMapper.getTache());
        //复核原因
        totalMap.get(areaName).put("detReason", cacheDataMapper.getDetReason());

        totalMap.get(areaName).put("videoServer", cacheDataMapper.getVideoServer(1));

        totalMap.get(areaName).put("cardbrand", cacheDataMapper.getCardBrand());

        totalMap.get(areaName).put("taskgrade", cacheDataMapper.getTaskGrade());

        totalMap.get(areaName).put("weightunit", cacheDataMapper.getWeightUnit());

        totalMap.get(areaName).put("weightmainunit", cacheDataMapper.getWeightMainUnit());

        totalMap.get(areaName).put("productdutytype", cacheDataMapper.getProductDutyType());

        totalMap.get(areaName).put("supervisionsubjects", cacheDataMapper.getSupervisionSubjects());

        totalMap.get(areaName).put("supervisionstatus", cacheDataMapper.getSupervisionStatus());

        // 检测方法
        List<Map<String, Object>> reagent = cacheDataMapper.getReagent();
        ((Map<String, Object>) totalMap.get(areaName).get(product)).put("reagent", reagent);

        // 检测项目
        List<Map<String, Object>> item = cacheDataMapper.getItem();
        ((Map<String, Object>) totalMap.get(areaName).get(product)).put("item", item);

        //单体重量单位
        List<Map<String, Object>> singleWeightUnit = cacheDataMapper.getSingleWeightUnit();
        ((Map<String, Object>) totalMap.get(areaName).get(product)).put("singleweightunit", singleWeightUnit);

        // 样品名称

        List<Map<String, Object>> sample = cacheDataMapper.getSample();
        ((Map<String, Object>) totalMap.get(areaName).get(product)).put("sample", sample);

        // 样品分类
        List<Map<String, Object>> object = cacheDataMapper.getObject();
        ((Map<String, Object>) totalMap.get(areaName).get(product)).put("object", object);

//        List<Map<String, Object>> taches = cacheDataMapper.getProcessTaches(product);
//        ((Map<String, Object>)totalMap.get(areaName).get(product)).put("taches", taches);

        //检测灵敏度，农产品没有
// 				if(!"produce".equals(product)){
// 					List<Map<String, Object>> sensitivity = cacheDataMapper.getSensitivity(product);
// 	 				((Map<String, Object>)totalMap.get(areaName).get(product)).put("sensitivity", sensitivity);
// 				}
    }
//		}

//		System.out.println(totalMap);
//    }

    public void change() {
        CacheData.totalMap.clear();
        init();
        // 切面缺少产品类型
    }
//
    public static Map<String, Map<String, Object>> getCacheData() {
        return totalMap;
    }

}
