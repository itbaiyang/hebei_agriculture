package com.zrodo.agriculture.controller;

import com.zrodo.agriculture.util.json.JsonStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(value = "部门", description = "部门管理")
@RestController
@Component
public class DeptController {
    @PostMapping(value = "/dept")
    @ApiOperation(value = "添加部门", notes = "添加部门")
    public String insertAreaFromCity(HttpServletRequest request,
                                     @ApiParam(required = true, name = "cityId", value = "城市编号") @RequestParam int cityId)
    {
        String json;
        try
        {
//            areaService.insertAreaFromCity(cityId);
            json = JsonStatus.success();
        }
        catch(Exception e)
        {
            json = JsonStatus.failure();
        }
        return json;
    }
}
