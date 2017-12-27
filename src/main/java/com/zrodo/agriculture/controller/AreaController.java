package com.zrodo.agriculture.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.zrodo.agriculture.repository.AreaMapper;
import com.zrodo.agriculture.service.AreaService;
import com.zrodo.agriculture.util.Tool;
import com.zrodo.agriculture.util.json.JsonMapUtils;
import com.zrodo.agriculture.util.json.JsonStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Api(value = "区划", description = "区划增删改查")
@RestController
@Component
public class AreaController {

    @Autowired
    private AreaService areaService;

    @Autowired
    private AreaMapper areaMapper;

    @GetMapping(value = "/areaManage")
    @ApiOperation(value = "添加区划", notes = "从city表复制（后台专用）")
    public String insertAreaFromCity(HttpServletRequest request,
                        @ApiParam(required = true, name = "cityId", value = "城市编号") @RequestParam int cityId)
    {
        String json;
        try
        {
            areaService.insertAreaFromCity(cityId);
            json = JsonStatus.success();
        }
        catch(Exception e)
        {
            json = JsonStatus.failure();
        }
        return json;
    }

    @DeleteMapping(value = "/areaManage")
    @ApiOperation(value = "删除区划", notes = "批量删除（后台专用）")
    public String deleteAreaList(HttpServletRequest request,
                        @ApiParam(required = true, name = "areaNo", value = "城市编号") @RequestParam int areaNo)
    {
        String json;
        try
        {
            areaService.deleteAreaById(areaNo);
            json = JsonStatus.success();
        }
        catch(Exception e)
        {
            json = JsonStatus.failure();
        }
        return json;
    }

    @GetMapping(value = "/area")
    @ApiOperation(value = "添加区划", notes = "从city表复制（后台专用）")
    public String area(HttpServletRequest request,
                       @ApiParam(name = "pId", value = "城市编号")
                           @RequestParam(required = false) Integer pId,
                       @ApiParam(name = "areaNo", value = "城市编号")
                           @RequestParam(required = false) Integer areaNo,
                       @ApiParam(required = true, name = "pageNo", value = "城市编号")
                           @RequestParam(defaultValue = "1") Integer pageNo,
                       @ApiParam(required = true, name = "pageSize", value = "城市编号")
                           @RequestParam(defaultValue = "20") Integer pageSize)
    {
        String json;
        try
        {
            List<Map<String,Object>> list = areaMapper.queryAreaList(pId,areaNo,(pageNo-1)*pageSize,pageSize);
            Map<String, Object> map = JsonMapUtils.buildSuccessMap();
            map.put("list", list);
            json = Tool.getJsonFromObect(map);
        }
        catch(Exception e)
        {
            json = JsonStatus.failure();
        }
        return json;
    }

    @GetMapping(value = "/areaList")
    @ApiOperation(value = "区划列表", notes = "从city表复制（后台专用）")
    public String areaListByLevel(
                       @ApiParam(name = "levelId", value = "级别Id")
                       @RequestParam(required = true)Integer levelId)
    {
        String json;
        try
        {
            List<Map<String,Object>> result = areaMapper.queryAreaListByLevel(levelId);
            Map<String, Object> map = JsonMapUtils.buildSuccessMap();
            map.put("result", result);
            json = Tool.getJsonFromObect(map);
        }
        catch(Exception e)
        {
            json = JsonStatus.failure();
        }
        return json;
    }
//    @GetMapping(value = "/area")
//    @ApiOperation(value = "添加区划", notes = "从city表复制（后台专用）")
//    public String insertArea(HttpServletRequest request,
//                             @ModelAttribute SysArea sysAre)
//    {
//        String json;
//        try
//        {
//            json = JsonStatus.success();
//        }
//        catch(Exception e)
//        {
//            json = JsonStatus.failure();
//        }
//        return json;
//    }
}
