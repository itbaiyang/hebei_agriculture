package com.zrodo.agriculture.controller;

import com.zrodo.agriculture.repository.ProductiveStandardMapper;
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

@Api(value = "生产标准", description = "生产标准管理的问题")
@RestController
@Component
public class StandardController {
    @Autowired
    private ProductiveStandardMapper standardMapper;

    @GetMapping(value = "/standardList")
    @ApiOperation(value = "查询用户列表", notes = "查询用户列表")
    public String standardList(HttpServletRequest request,
                               @ApiParam(name = "companyId", value = "企业Id")
                               @RequestParam(value = "companyId", required = false) Integer companyId,
                               @ApiParam(name = "objectId", value = "产品名称Id")
                               @RequestParam(value = "objectId", required = false) Integer objectId,
                               @ApiParam(name = "productType", value = "产品类型")
                               @RequestParam(value = "productType", required = false) Integer productType,
                               @ApiParam(name = "startDate", value = "起始时间")
                               @RequestParam(value = "startDate", required = false) String startDate,
                               @ApiParam(name = "endDate", value = "结束时间")
                               @RequestParam(value = "endDate", required = false) String endDate,
                               @ApiParam(required = true, name = "pageNo", value = "页码")
                               @RequestParam(defaultValue = "1") Integer pageNo,
                               @ApiParam(required = true, name = "pageSize", value = "每页个数")
                               @RequestParam(defaultValue = "20") Integer pageSize
    ) {
        String json;
        try {
            List<Map<String, Object>> result = null;
            result = standardMapper.queryProductiveStandardList(companyId, objectId, productType, startDate, endDate, (pageNo - 1) * pageSize, pageSize);
            Map<String, Object> map = JsonMapUtils.buildSuccessMap();
            map.put("result", result);
            json = Tool.getJsonFromObect(map);

        } catch (Exception e) {
            json = JsonStatus.failure();
        }
        return json;
    }
}
