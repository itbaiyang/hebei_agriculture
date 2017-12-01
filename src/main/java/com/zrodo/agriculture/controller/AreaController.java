package com.zrodo.agriculture.controller;


import com.zrodo.agriculture.service.AreaService;
import com.zrodo.agriculture.util.json.JsonStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(value = "区划", description = "区划增删改查")
@RestController
@Component
//@RequestMapping(value = "/area", produces = "application/json;charset=UTF-8")
public class AreaController {

    @Autowired
    private AreaService areaService;

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
