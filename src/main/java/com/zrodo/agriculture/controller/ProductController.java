package com.zrodo.agriculture.controller;

import com.zrodo.agriculture.domain.AccountInfo;
import com.zrodo.agriculture.entity.Product;
import com.zrodo.agriculture.entity.TObject;
import com.zrodo.agriculture.repository.ProductMapper;
import com.zrodo.agriculture.service.ProductService;
import com.zrodo.agriculture.util.Page;
import com.zrodo.agriculture.util.Token;
import com.zrodo.agriculture.util.Tool;
import com.zrodo.agriculture.util.json.JsonMapUtils;
import com.zrodo.agriculture.util.json.JsonStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Api(value = "产品", description = "产品管理")
@RestController
@Component
public class ProductController {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductService productService;

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
            productService.addProduct(product);
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
    public String updateProduct(@ApiParam(name = "id", value = "产品id")
                                @RequestParam(value = "id") Integer id,
                                @ApiParam(name = "dutyTypeId", value = "产品责任类型")
                                @RequestParam(value = "dutyTypeId", required = false) Integer dutyTypeId,
                                @ApiParam(name = "description", value = "产品描述")
                                @RequestParam(value = "description", required = false) String description) {
        String json;
        try {
            productMapper.updateProduct(id, dutyTypeId, description);
            json = JsonStatus.success();
        } catch (Exception e) {
            e.printStackTrace();
            json = JsonStatus.failure();
        }
        return json;
    }

    @DeleteMapping(value = "deleteProduct")
    @ApiOperation(value = "删除产品", notes = "删除产品")
    public String deleteProduct(@ApiParam(name = "id", value = "产品id")
                             @RequestParam(value = "id") Integer id) {
        String json;
        try {
            productMapper.deleteProduct(id);
            json = JsonStatus.success();
        } catch (Exception e) {
            e.printStackTrace();
            json = JsonStatus.failure();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return json;
    }

    @GetMapping(value = "/productList")
    @ApiOperation(value = "查询产品列表", notes = "查询产品列表")
    public String productList(HttpServletRequest request,
                              @ApiParam(name = "companyId", value = "企业Id")
                              @RequestParam(value = "companyId", required = false) Integer companyId,
                              @ApiParam(name = "productTypeId", value = "产品类型id")
                              @RequestParam(value = "productTypeId", required = false) Integer productTypeId,
                              @ApiParam(name = "objectName", value = "产品名称")
                              @RequestParam(value = "objectName", required = false) String objectName,
                              @ApiParam(name = "dutyTypeId", value = "产品质量认证类型id")
                              @RequestParam(value = "dutyTypeId", required = false) Integer dutyTypeId,
                              @ApiParam(name = "acreId", value = "生产场地id")
                              @RequestParam(value = "acreId", required = false) Integer acreId,
                              @ApiParam(name = "resultId", value = "检测结果id")
                              @RequestParam(value = "resultId", required = false) Integer resultId,
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
            result = productMapper.queryProductList(companyId, productTypeId, objectName, dutyTypeId, acreId,
                    resultId, startDate, endDate, (pageNo - 1) * pageSize, pageSize);
            int resultCount = productMapper.queryProductListCount(companyId, productTypeId, objectName,
                    dutyTypeId, acreId, resultId, startDate, endDate);
            Page page = new Page(result, pageNo, pageSize, resultCount);
            json = Tool.getPageSuccessStr(page);

        } catch (Exception e) {
            json = JsonStatus.failure();
        }
        return json;
    }

    @GetMapping(value = "/queryProductById")
    @ApiOperation(value = "查询产品详情", notes = "查询产品详情")
    public String queryProductById(HttpServletRequest request,
                                   @ApiParam(name = "productId", value = "产品Id", required = true)
                                   @RequestParam(value = "productId") Integer productId
    ) {
        String json;
        try {
            Map<String, Object> result;
            result = productMapper.queryProductById(productId);
            Map<String, Object> map = JsonMapUtils.buildSuccessMap();
            map.put("result", result);
            json = Tool.getJsonFromObect(map);

        } catch (Exception e) {
            json = JsonStatus.failure();
        }
        return json;
    }


}
