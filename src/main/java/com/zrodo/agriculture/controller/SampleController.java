package com.zrodo.agriculture.controller;

import com.zrodo.agriculture.domain.AccountInfo;
import com.zrodo.agriculture.entity.Sample;
import com.zrodo.agriculture.repository.ProductMapper;
import com.zrodo.agriculture.repository.SampleMapper;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Api(value = "样品信息", description = "样品信息")
@RestController
@Component
public class SampleController {
    @Autowired
    private SampleMapper sampleMapper;

    @Autowired
    private ProductMapper productMapper;

    @PostMapping(value = "insertSample")
    @ApiOperation(value = "插入样品信息", notes = "")
    public String insertSample(HttpServletRequest request,
                               @ModelAttribute Sample sample) {
//        AccountInfo user = Token.getUser(request);
//        sample.setcUserId(user.getId());
        String json;
        try {
            int id = sampleMapper.addSample(sample);
            json = JsonStatus.success();
        } catch (Exception e) {
            e.printStackTrace();
            json = JsonStatus.failure();
        }
        return json;
    }

    @DeleteMapping(value = "deleteSample")
    @ApiOperation(value = "插入样品信息", notes = "")
    public String deleteSample(@ApiParam(required = true, name = "id", value = "样品Id")
                               @RequestParam(value = "id") int id) {
        String json;
        try {
            sampleMapper.deleteSample(id);
            json = JsonStatus.success();
        } catch (Exception e) {
            e.printStackTrace();
            json = JsonStatus.failure();
        }
        return json;
    }

//    @PutMapping(value = "updateSample")
//    @ApiOperation(value = "修改样品信息(暂时不用)", notes = "修改样品信息(暂时不用)")
//    public String updateSample(HttpServletRequest request,
//                               @ApiParam(required = true, name = "id", value = "样品Id")
//                               @RequestParam(value = "id") int id,
//                               @ApiParam(name = "sampleUrl", value = "样品Id")
//                                   @RequestParam(value = "sampleUrl", required = false) int sampleUrl
//                               ) {
//        String json;
//        try {
//            sampleMapper.updateSample(id,sampleUrl);
//            json = JsonStatus.success();
//        } catch (Exception e) {
//            e.printStackTrace();
//            json = JsonStatus.failure();
//        }
//        return json;
//    }

    @GetMapping(value = "querySampleList")
    @ApiOperation(value = "查询样品列表", notes = "查询样品列表")
    public String querySampleList(HttpServletRequest request,
                                  @ApiParam(name = "areaId", value = "区划Id")
                                  @RequestParam(value = "areaId", required = false) int areaId,
                                  @ApiParam(name = "companyId", value = "企业Id")
                                  @RequestParam(value = "companyId", required = false) int companyId,
                                  @RequestParam(value = "productTypeId", required = false) int productTypeId,
                                  @ApiParam(name = "objectId", value = "产品名称Id")
                                  @RequestParam(value = "objectId", required = false) int objectId,
                                  @ApiParam(name = "resultId", value = "检测结果id")
                                  @RequestParam(value = "resultId", required = false) Integer resultId,
                                  @ApiParam(name = "tacheId", value = "采样环节")
                                  @RequestParam(value = "tacheId", required = false) Integer tacheId,
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
            List<Map<String, Object>> result = sampleMapper.querySampleList(areaId, companyId, productTypeId, objectId,
                    resultId, tacheId, startDate, endDate, (pageNo - 1) * pageSize, pageSize);
            int resultCount = sampleMapper.querySampleListCount(areaId, companyId, productTypeId, objectId,
                    resultId, tacheId, startDate, endDate);
            Page page = new Page(result, pageNo, pageSize, resultCount);
            json = Tool.getPageSuccessStr(page);
        } catch (Exception e) {
            e.printStackTrace();
            json = JsonStatus.failure();
        }
        return json;
    }

    @GetMapping(value = "getSampleListByProduct")
    @ApiOperation(value = "查询某产品的样品", notes = "查询某产品的样品")
    public String querySampleListByProduct(@ApiParam(name = "productId", value = "产品Id")
                                           @RequestParam(value = "productId", required = false) Integer productId

    ) {
        String json;
        try {
            List<Map<String, Object>> sampleList = sampleMapper.querySampleListByProduct(productId);
            Map<String, Object> productDetail = productMapper.queryProductById(productId);
            Map<String, Object> map = JsonMapUtils.buildSuccessMap();
            map.put("sampleList", sampleList);
            map.put("productDetail", productDetail);
            map.put("sampleInfo", null);
            json = Tool.getJsonFromObect(map);
        } catch (Exception e) {
            e.printStackTrace();
            json = JsonStatus.failure();
        }
        return json;
    }

}
