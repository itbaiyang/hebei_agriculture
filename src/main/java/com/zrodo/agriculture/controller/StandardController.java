package com.zrodo.agriculture.controller;

import com.zrodo.agriculture.domain.AccountInfo;
import com.zrodo.agriculture.entity.ProductiveStandard;
import com.zrodo.agriculture.entity.ProductiveStandardGlsq;
import com.zrodo.agriculture.repository.ProductiveGlsqMapper;
import com.zrodo.agriculture.repository.ProductiveStandardMapper;
import com.zrodo.agriculture.service.StandardService;
import com.zrodo.agriculture.util.Page;
import com.zrodo.agriculture.util.Token;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

@Api(value = "生产标准", description = "生产标准和管理时期")
@RestController
@Component
public class StandardController {

    @Autowired
    private ProductiveStandardMapper standardMapper;
    @Autowired
    private ProductiveGlsqMapper glsqMapper;
    @Autowired
    private StandardService standardService;

    @PostMapping(value = "addProductiveStandard")
    @ApiOperation(value = "新增生产标准", notes = "新增生产标准")
    public String addProductiveStandard(HttpServletRequest request,
                                        @ModelAttribute ProductiveStandard productiveStandard) {
        String json;
        try {
            AccountInfo accountInfo = Token.getUser(request);
            productiveStandard.setcUserId(accountInfo.getId());
            productiveStandard.setCompanyId(accountInfo.getCompanyId());
            if (StringUtils.isBlank(productiveStandard.getObjectName())) {
                json = JsonStatus.paramNullError("产品名称为空");
            } else {
                json = standardService.addProductiveStandard(productiveStandard);
            }
        } catch (Exception e) {
            json = JsonStatus.failure();
        }
        return json;
    }

    @DeleteMapping(value = "deleteProductiveStandard")
    @ApiOperation(value = "删除生产标准", notes = "删除生产标准")
    public String deleteProductiveStandard(@ApiParam(required = true, name = "standardId", value = "标准编号")
                                           @RequestParam int standardId) {
        String json;
        try {
            standardMapper.deleteProductiveStandard(standardId);
            json = JsonStatus.success();
        } catch (Exception e) {
            json = JsonStatus.failure();
        }
        return json;
    }

    @PutMapping(value = "updateProductiveStandard")
    @ApiOperation(value = "修改生产标准", notes = "修改生产标准")
    public String updateProductiveStandard(@ApiParam(required = true, name = "standardId", value = "标准编号")
                                           @RequestParam int standardId,
                                           @ApiParam(required = true, name = "name", value = "标准名称")
                                           @RequestParam String name) {
        String json;
        try {
            standardMapper.updateProductiveStandard(standardId, name);
            json = JsonStatus.success();
        } catch (Exception e) {
            json = JsonStatus.failure();
        }
        return json;
    }

    @GetMapping(value = "/standardList")
    @ApiOperation(value = "查询生产标准列表", notes = "查询生产标准列表")
    public String standardList(HttpServletRequest request,
                               @ApiParam(name = "companyId", value = "企业Id", hidden = true)
                               @RequestParam(value = "companyId", required = false) Integer companyId,
                               @ApiParam(name = "standardName", value = "生产标准名称")
                               @RequestParam(value = "standardName", required = false) String standardName,
                               @ApiParam(name = "objectId", value = "产品名称Id")
                               @RequestParam(value = "objectId", required = false) Integer objectId,
                               @ApiParam(name = "productTypeId", value = "产品类型")
                               @RequestParam(value = "productTypeId", required = false) Integer productTypeId,
                               @ApiParam(required = true, name = "pageNo", value = "页码")
                               @RequestParam(defaultValue = "1") Integer pageNo,
                               @ApiParam(required = true, name = "pageSize", value = "每页个数")
                               @RequestParam(defaultValue = "20") Integer pageSize
    ) {
        String json;
        try {
            if (companyId == null) {
                AccountInfo accountInfo = Token.getUser(request);
                companyId = accountInfo.getCompanyId();
            }

            List<Map<String, Object>> result;
            result = standardMapper.queryProductiveStandardList(companyId, standardName, objectId, productTypeId, (pageNo - 1) * pageSize, pageSize);
            int resultCount = standardMapper.queryProductiveStandardListCount(companyId, standardName, objectId, productTypeId);
            Page page = new Page(result, pageNo, pageSize, resultCount);
            json = Tool.getPageSuccessStr(page);
        } catch (Exception e) {
            json = JsonStatus.failure();
        }
        return json;
    }

    @GetMapping(value = "/standardListObject")
    @ApiOperation(value = "查询固定产品类型的生产标准列表", notes = "查询固定产品类型的生产标准列表")
    public String standardListObject(@ApiParam(name = "objectId", value = "产品名称Id")
                                     @RequestParam(value = "objectId") Integer objectId,
                                     @ApiParam(required = true, name = "pageNo", value = "页码")
                                     @RequestParam(defaultValue = "1") Integer pageNo,
                                     @ApiParam(required = true, name = "pageSize", value = "每页个数")
                                     @RequestParam(defaultValue = "20") Integer pageSize
    ) {
        String json;
        try {
            List<Map<String, Object>> result;
            result = standardMapper.queryProductiveStandardList(null, null, objectId, null, (pageNo - 1) * pageSize, pageSize);
            int resultCount = standardMapper.queryProductiveStandardListCount(null, null, objectId, null);
            Page page = new Page(result, pageNo, pageSize, resultCount);
            json = Tool.getPageSuccessStr(page);
        } catch (Exception e) {
            json = JsonStatus.failure();
        }
        return json;
    }

    @PostMapping(value = "addProductiveGlsq")
    @ApiOperation(value = "新增管理时期", notes = "")
    public String addProductiveGlsq(@ModelAttribute ProductiveStandardGlsq productiveStandardGlsq) {
        String json;
        try {
            glsqMapper.addGlsq(productiveStandardGlsq);
            json = JsonStatus.success();
        } catch (Exception e) {
            json = JsonStatus.failure();
        }
        return json;
    }

    @DeleteMapping(value = "deleteProductiveGlsq")
    @ApiOperation(value = "删除管理时期", notes = "")
    public String deleteProductiveGlsq(@ApiParam(required = true, name = "glsqId", value = "管理时期编号")
                                       @RequestParam int glsqId) {
        String json;
        try {
            glsqMapper.deleteGlsq(glsqId);
            json = JsonStatus.success();
        } catch (Exception e) {
            json = JsonStatus.failure();
        }
        return json;
    }

    @PutMapping(value = "updateProductiveGlsq")
    @ApiOperation(value = "修改管理时期", notes = "")
    public String updateProductiveGlsq(@ApiParam(required = true, name = "glsqId", value = "管理时期编号")
                                       @RequestParam int glsqId,
                                       @ApiParam(name = "glsqName", value = "管理时期名称")
                                       @RequestParam(required = false) String glsqName,
                                       @ApiParam(name = "subject", value = "操作主题")
                                       @RequestParam(required = false) String subject,
                                       @ApiParam(name = "requirement", value = "标准要求")
                                       @RequestParam(required = false) String requirement) {
        String json;
        try {
            glsqMapper.updateGlsq(glsqId, glsqName, subject, requirement);
            json = JsonStatus.success();
        } catch (Exception e) {
            json = JsonStatus.failure();
        }
        return json;
    }

    @GetMapping(value = "/glsqList")
    @ApiOperation(value = "管理时期列表", notes = "查询管理时期列表")
    public String glsqList(HttpServletRequest request,
                           @ApiParam(name = "standardId", required = true, value = "标准Id")
                           @RequestParam(value = "standardId") Integer standardId) {
        String json;
        try {
            List<Map<String, Object>> result = null;
            result = glsqMapper.queryGlsqListByStandard(standardId);
            Map<String, Object> map = JsonMapUtils.buildSuccessMap();
            map.put("result", result);
            json = Tool.getJsonFromObect(map);
        } catch (Exception e) {
            json = JsonStatus.failure();
        }
        return json;
    }

    @GetMapping(value = "/glsqById")
    @ApiOperation(value = "管理时期", notes = "查询管理时期详情")
    public String glsqById(HttpServletRequest request,
                           @ApiParam(name = "glsqId", required = true, value = "管理时期Id")
                           @RequestParam(value = "glsqId") Integer glsqId) {
        String json;
        try {
            Map<String, Object> result = null;
            result = glsqMapper.queryGlsqById(glsqId);
            Map<String, Object> map = JsonMapUtils.buildSuccessMap();
            map.put("result", result);
            json = Tool.getJsonFromObect(map);
        } catch (Exception e) {
            json = JsonStatus.failure();
        }
        return json;
    }

    @GetMapping(value = "/copyStandard")
    @ApiOperation(value = "复制已有产品标准列表", notes = "复制已有产品标准列表")
    public String copyStandard(HttpServletRequest request,
                               @ApiParam(name = "standardId", required = true, value = "标准Id")
                               @RequestParam(value = "standardId") Integer standardId,
                               @ApiParam(name = "copyStandardId", required = true, value = "模板生产标准Id")
                               @RequestParam(value = "copyStandardId") Integer copyStandardId) {
        String json;
        try {
            standardService.copyStandard(standardId, copyStandardId);
            json = JsonStatus.success();
        } catch (Exception e) {
            e.printStackTrace();
            json = JsonStatus.failure();
        }
        return json;
    }
}
