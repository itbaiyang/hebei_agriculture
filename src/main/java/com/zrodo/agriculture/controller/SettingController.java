
package com.zrodo.agriculture.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


@Api(value = "设置", description = "设置")
@RestController
@Component
public class SettingController {
    @ResponseBody
    @GetMapping(value = "/setting")
    @ApiOperation(value = "设置", notes = "设置")
    public String hello()
    {
        return "hello";
    }
}