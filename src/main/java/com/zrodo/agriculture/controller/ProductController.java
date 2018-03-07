package com.zrodo.agriculture.controller;

import com.zrodo.agriculture.domain.AccountInfo;
import com.zrodo.agriculture.entity.Product;
import com.zrodo.agriculture.entity.TObject;
import com.zrodo.agriculture.repository.ProductMapper;
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
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Api(value = "产品", description = "产品管理")
@RestController
@Component
public class ProductController {

    @Autowired
    private ProductMapper productMapper;

    @PostMapping(value = "addProduct")
    @ApiOperation(value = "添加产品", notes = "添加产品")
    public String addProduct(HttpServletRequest request,
                             @ModelAttribute Product product) {
        AccountInfo user = Token.getUser(request);
        product.setCompanyId(user.getCompanyId());
        product.setcUserId(user.getId());

        String json;

        try {
            if (product.getAcreId() == 0) {
                return JsonStatus.paramNullError("请选择地块信息");
            }
            String productNo = UUID.randomUUID().toString();
            product.setProductNo(productNo);
            int productTypeId = product.getProductTypeId();

            Calendar a = Calendar.getInstance();
            int nextval = productMapper.getProductSequence(product.getCompanyId(), a.get(Calendar.YEAR));
            String result = String.format("%03d", nextval) + Tool.getRandomNumber(2);
            String year = "" + a.get(Calendar.YEAR);
            String processNo = year.substring(year.length() - 2) + String.format("%02d", a.get(Calendar.MONTH) + 1) + productTypeId + result;
            product.setProcessNo(processNo);
            product.setSerialNumber(String.format("%03d", nextval));
            productMapper.addProduct(product);
            json = JsonStatus.success();
        } catch (Exception e) {
            e.printStackTrace();
            json = JsonStatus.failure();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return json;
    }

    @PutMapping(value = "updateProduct")
    @ApiOperation(value = "添加产品", notes = "添加产品")
    public String updateProduct(HttpServletRequest request,
                                @ModelAttribute Product product) {
        String json;

        try {
            productMapper.updateProduct(product);
            json = JsonStatus.success();
        } catch (Exception e) {
            e.printStackTrace();
            json = JsonStatus.failure();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return json;
    }

    @DeleteMapping(value = "deleteProduct")
    @ApiOperation(value = "删除产品", notes = "删除产品")
    public int deleteProduct(HttpServletRequest request,
                             @ApiParam(name = "id", value = "产品id")
                             @RequestParam(value = "id") Integer id) {
        return productMapper.deleteProduct(id);
    }

    @GetMapping(value = "/productList")
    @ApiOperation(value = "查询产品列表", notes = "查询产品列表")
    public String productList(HttpServletRequest request,
                              @ApiParam(name = "companyId", value = "企业Id")
                              @RequestParam(value = "companyId", required = false) Integer companyId,
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
            List<Map<String, Object>> result;
            result = productMapper.queryProductList(companyId, productType, startDate, endDate, (pageNo - 1) * pageSize, pageSize);
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
            result = productMapper.queryObjectList(productTypeId);
            Map<String, Object> map = JsonMapUtils.buildSuccessMap();
            map.put("result", result);
            json = Tool.getJsonFromObect(map);

        } catch (Exception e) {
            json = JsonStatus.failure();
        }
        return json;
    }
}
