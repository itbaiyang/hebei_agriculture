package com.zrodo.agriculture.controller;

import com.zrodo.agriculture.entity.SysDept;
import com.zrodo.agriculture.repository.DeptMapper;
import com.zrodo.agriculture.repository.ProductMapper;
import com.zrodo.agriculture.util.Tool;
import com.zrodo.agriculture.util.json.JsonMapUtils;
import com.zrodo.agriculture.util.json.JsonStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Api(value = "部门", description = "部门管理")
@RestController
@Component
public class ProductController {
    @Autowired
    private ProductMapper productMapper;

    @GetMapping(value = "/productType")
    @ApiOperation(value = "产品类型列表", notes = "产品类型列表")
    public String queryProductType() {
        String json;
        try {
            List<Map<String, Object>> result = productMapper.queryProductType();
            Map<String, Object> map = JsonMapUtils.buildSuccessMap();
            map.put("result", result);
            json = Tool.getJsonFromObect(map);
        } catch (Exception e) {
            json = JsonStatus.failure();
        }
        return json;
    }

}
