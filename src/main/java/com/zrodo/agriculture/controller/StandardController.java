package com.zrodo.agriculture.controller;

import com.zrodo.agriculture.domain.AccountInfo;
import com.zrodo.agriculture.entity.ProductiveStandard;
import com.zrodo.agriculture.entity.ProductiveStandardGlsq;
import com.zrodo.agriculture.entity.TObject;
import com.zrodo.agriculture.repository.ProductMapper;
import com.zrodo.agriculture.repository.ProductiveGlsqMapper;
import com.zrodo.agriculture.repository.ProductiveStandardMapper;
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
import java.util.List;
import java.util.Map;

@Api(value = "生产标准", description = "生产标准管理")
@RestController
@Component
public class StandardController {
    @Autowired
    private ProductiveStandardMapper standardMapper;
    @Autowired
    private ProductiveGlsqMapper glsqMapper;
    @Autowired
    private ProductMapper productMapper;

    @PostMapping(value = "addProductiveStandard")
    @ApiOperation(value = "新增生产标准", notes = "")
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
                int num = productMapper.existObject(productiveStandard.getObjectName());
                if (num == 0) {
                    TObject tObject = new TObject();
                    tObject.setObjectName(productiveStandard.getObjectName());
                    tObject.setOpenFlag("1");
                    if (productiveStandard.getProductTypeId() == 1) {
                        tObject.setUnit("头");
                    } else {
                        tObject.setUnit("公斤");
                    }
                    tObject.setcUserId(String.valueOf(productiveStandard.getcUserId()));
                    tObject.setProductType(productiveStandard.getProductTypeId());
                    productMapper.insertObject(tObject);
                    productiveStandard.setObjectId(tObject.getObjectId());
//                    ((Map<String, Object>)cacheData.getCacheData().get(area).get(productiveStandard.getProducttype())).remove("object");
//                    ((Map<String, Object>)cacheData.getCacheData().get(area).get(productiveStandard.getProducttype())).put("object", productMapper.getAllObjectsByProductType(productiveStandard.getProducttype()));
                }
                int count = standardMapper.queryProductiveStandardByName(productiveStandard.getCompanyId(),
                        productiveStandard.getObjectId(), productiveStandard.getName());
                if (count > 0) {
                    json = JsonStatus.validationError("该标准已经存在,如有需要,可以返回上级页面对该标准进行编辑");
                } else {
                    standardMapper.addProductiveStandard(productiveStandard);
                    Map<String, Object> map = JsonMapUtils.buildSuccessMap();
                    map.put("result", productiveStandard);
                    json = Tool.getJsonFromObect(map);
                }
            }
        } catch (Exception e) {
            json = JsonStatus.failure();
        }
        return json;
    }

    @DeleteMapping(value = "deleteProductiveStandard")
    @ApiOperation(value = "删除生产标准", notes = "")
    public String deleteProductiveStandard(HttpServletRequest request,
                                           @ApiParam(required = true, name = "standardId", value = "标准编号")
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
    @ApiOperation(value = "修改生产标准", notes = "")
    public String updateProductiveStandard(HttpServletRequest request,
                                           @ApiParam(required = true, name = "standardId", value = "标准编号")
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
                               @ApiParam(name = "objectId", value = "产品名称Id")
                               @RequestParam(value = "objectId", required = false) Integer objectId,
                               @ApiParam(name = "productType", value = "产品类型")
                               @RequestParam(value = "productType", required = false) Integer productType,
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

            List<Map<String, Object>> result = null;
            result = standardMapper.queryProductiveStandardList(companyId, objectId, productType, (pageNo - 1) * pageSize, pageSize);
            int resultCount = standardMapper.queryProductiveStandardListCount(companyId, objectId, productType);
            Page page = new Page(result, pageNo, pageSize, resultCount);
            json = Tool.getPageSuccessStr(page);
        } catch (Exception e) {
            json = JsonStatus.failure();
        }
        return json;
    }

    @GetMapping(value = "/standardListObject")
    @ApiOperation(value = "查询固定产品类型的生产标准列表", notes = "查询固定产品类型的生产标准列表")
    public String standardListObject(HttpServletRequest request,
                                     @ApiParam(name = "objectId", value = "产品名称Id")
                                     @RequestParam(value = "objectId") Integer objectId,
                                     @ApiParam(required = true, name = "pageNo", value = "页码")
                                     @RequestParam(defaultValue = "1") Integer pageNo,
                                     @ApiParam(required = true, name = "pageSize", value = "每页个数")
                                     @RequestParam(defaultValue = "20") Integer pageSize
    ) {
        String json;
        try {
//            if(companyId == null){
//                AccountInfo accountInfo = Token.getUser(request);
//                companyId = accountInfo.getCompanyId();
//            }

            List<Map<String, Object>> result = null;
            result = standardMapper.queryProductiveStandardList(null, objectId, null, (pageNo - 1) * pageSize, pageSize);
            int resultCount = standardMapper.queryProductiveStandardListCount(null, objectId, null);
            Page page = new Page(result, pageNo, pageSize, resultCount);
            json = Tool.getPageSuccessStr(page);
        } catch (Exception e) {
            json = JsonStatus.failure();
        }
        return json;
    }

    @PostMapping(value = "addProductiveGlsq")
    @ApiOperation(value = "新增管理时期", notes = "")
    public String addProductiveGlsq(HttpServletRequest request,
                                    @ModelAttribute ProductiveStandardGlsq productiveStandardGlsq) {
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
    public String deleteProductiveGlsq(HttpServletRequest request,
                                       @ApiParam(required = true, name = "glsqId", value = "管理时期编号")
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
    public String updateProductiveGlsq(HttpServletRequest request,
                                       @ApiParam(required = true, name = "glsqId", value = "管理时期编号")
                                       @RequestParam int glsqId,
                                       @ApiParam(required = true, name = "glsqName", value = "管理时期名称")
                                       @RequestParam String glsqName,
                                       @ApiParam(required = true, name = "subject", value = "操作主题")
                                       @RequestParam String subject,
                                       @ApiParam(required = true, name = "requirement", value = "标准要求")
                                       @RequestParam String requirement) {
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
}
