package com.zrodo.agriculture.controller;

import com.zrodo.agriculture.entity.TObject;
import com.zrodo.agriculture.repository.CacheMapper;
import com.zrodo.agriculture.util.Tool;
import com.zrodo.agriculture.util.json.JsonMapUtils;
import com.zrodo.agriculture.util.json.JsonStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Api(value = "静态数据", description = "静态数据缓存")
@RestController
@Component
public class StaticCacheController {

    @Autowired
    private CacheMapper cacheMapper;

    @GetMapping(value = "/queryProductType")
    @ApiOperation(value = "查询产品类型列表", notes = "查询产品类型列表")
    public String queryProductType() {
        String json;
        try {
            List<Map<String, Object>> result;
            result = cacheMapper.queryProductType();
            Map<String, Object> map = JsonMapUtils.buildSuccessMap();
            map.put("result", result);
            json = Tool.getJsonFromObect(map);

        } catch (Exception e) {
            json = JsonStatus.failure();
        }
        return json;
    }

    @GetMapping(value = "/queryObjectList")
    @ApiOperation(value = "查询产品名称列表", notes = "查询产品名称列表")
    public String queryObjectList(HttpServletRequest request,
                                  @ApiParam(name = "productTypeId", value = "产品类型")
                                  @RequestParam(value = "productTypeId", required = false) Integer productTypeId
    ) {
        String json;
        try {
            List<TObject> result;
            result = cacheMapper.queryObjectList(productTypeId);
            Map<String, Object> map = JsonMapUtils.buildSuccessMap();
            map.put("result", result);
            json = Tool.getJsonFromObect(map);

        } catch (Exception e) {
            json = JsonStatus.failure();
        }
        return json;
    }

    @GetMapping(value = "/queryDutyTypeList")
    @ApiOperation(value = "查询产品质量认证名称列表", notes = "查询产品质量认证名称列表")
    public String queryDutyTypeList() {
        String json;
        try {
            List<Map<String, Object>> result;
            result = cacheMapper.queryDutyTypeList();
            Map<String, Object> map = JsonMapUtils.buildSuccessMap();
            map.put("result", result);
            json = Tool.getJsonFromObect(map);

        } catch (Exception e) {
            json = JsonStatus.failure();
        }
        return json;
    }

    @GetMapping(value = "/querySampleTacheList")
    @ApiOperation(value = "查询采样环节列表", notes = "查询采样环节列表")
    public String querySampleTacheList() {
        String json;
        try {
            List<Map<String, Object>> result;
            result = cacheMapper.querySampleTacheList();
            Map<String, Object> map = JsonMapUtils.buildSuccessMap();
            map.put("result", result);
            json = Tool.getJsonFromObect(map);

        } catch (Exception e) {
            json = JsonStatus.failure();
        }
        return json;
    }

    @GetMapping(value = "/querySampleNoList")
    @ApiOperation(value = "查询采样样本列表", notes = "查询采样样本列表")
    public String querySampleNoList() {
        String json;
        try {
            List<Map<String, Object>> result;
            result = cacheMapper.querySampleNoList();
            Map<String, Object> map = JsonMapUtils.buildSuccessMap();
            map.put("result", result);
            json = Tool.getJsonFromObect(map);

        } catch (Exception e) {
            json = JsonStatus.failure();
        }
        return json;
    }

    @GetMapping(value = "/queryResultList")
    @ApiOperation(value = "查询检测结果列表", notes = "查询检测结果列表")
    public String queryResultList() {
        String json;
        try {
            List<Map<String, Object>> result;
            result = cacheMapper.queryResultList();
            Map<String, Object> map = JsonMapUtils.buildSuccessMap();
            map.put("result", result);
            json = Tool.getJsonFromObect(map);

        } catch (Exception e) {
            json = JsonStatus.failure();
        }
        return json;
    }

    @GetMapping(value = "/queryItemList")
    @ApiOperation(value = "查询检测项目列表", notes = "查询检测项目列表")
    public String queryItemList() {
        String json;
        try {
            List<Map<String, Object>> result;
            result = cacheMapper.queryItemList();
            Map<String, Object> map = JsonMapUtils.buildSuccessMap();
            map.put("result", result);
            json = Tool.getJsonFromObect(map);

        } catch (Exception e) {
            json = JsonStatus.failure();
        }
        return json;
    }

    @GetMapping(value = "/queryReagentList")
    @ApiOperation(value = "查询检测方法列表", notes = "查询检测方法列表")
    public String queryReagentList() {
        String json;
        try {
            List<Map<String, Object>> result;
            result = cacheMapper.queryReagentList();
            Map<String, Object> map = JsonMapUtils.buildSuccessMap();
            map.put("result", result);
            json = Tool.getJsonFromObect(map);

        } catch (Exception e) {
            json = JsonStatus.failure();
        }
        return json;
    }

    @GetMapping(value = "/querySourceList")
    @ApiOperation(value = "查询检测来源列表", notes = "查询检测来源列表")
    public String querySourceList() {
        String json;
        try {
            List<Map<String, Object>> result;
            result = cacheMapper.querySourceList();
            Map<String, Object> map = JsonMapUtils.buildSuccessMap();
            map.put("result", result);
            json = Tool.getJsonFromObect(map);

        } catch (Exception e) {
            json = JsonStatus.failure();
        }
        return json;
    }
}
