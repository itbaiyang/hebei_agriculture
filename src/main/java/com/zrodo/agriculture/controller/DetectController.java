package com.zrodo.agriculture.controller;

import com.zrodo.agriculture.domain.AccountInfo;
import com.zrodo.agriculture.entity.DetectReport;
import com.zrodo.agriculture.entity.Sample;
import com.zrodo.agriculture.repository.DetectReportMapper;
import com.zrodo.agriculture.repository.SampleMapper;
import com.zrodo.agriculture.service.DetectService;
import com.zrodo.agriculture.util.Token;
import com.zrodo.agriculture.util.json.JsonStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(value = "检测信息", description = "检测信息")
@RestController
@Component
public class DetectController {
    @Autowired
    private DetectReportMapper detectReportMapper;

    @Autowired
    private DetectService detectService;

    @PostMapping(value = "insertDetectReport")
    @ApiOperation(value = "插入检测记录", notes = "插入检测记录")
    public String insertDetectReport(HttpServletRequest request,
                                     @ApiParam(name = "sampleDate", value = "采样日期")
                                     @RequestParam(value = "productId", required = false) String sampleDate,
                                     @ApiParam(name = "tacheId", value = "采样环节")
                                     @RequestParam(value = "productId", required = false) Integer tacheId,
                                     @ApiParam(name = "sampleNoId", value = "采样样本")
                                     @RequestParam(value = "sampleNoId", required = false) Integer sampleNoId,
                                     @ModelAttribute DetectReport detectReport) {
        AccountInfo user = Token.getUser(request);
        detectReport.setDetectUserId(user.getId());
        String json;
        try {
            List<String> img = com.alibaba.fastjson.JSONArray.parseArray(detectReport.getImageUrl(), String.class);
            detectReport.setImageUrls(img);
            detectService.insertDetectReport(sampleDate, tacheId, sampleNoId, detectReport);
            json = JsonStatus.success();
        } catch (Exception e) {
            e.printStackTrace();
            json = JsonStatus.failure();
        }
        return json;
    }

    @PostMapping(value = "deleteDetectReport")
    @ApiOperation(value = "删除检测记录", notes = "删除检测记录")
    public String insertDetectReport(@ApiParam(name = "detectId", value = "采样日期", required = true)
                                     @RequestParam(value = "detectId") Integer detectId) {
        String json;
        try {
            detectService.deleteDetectReport(detectId);
            json = JsonStatus.success();
        } catch (Exception e) {
            e.printStackTrace();
            json = JsonStatus.failure();
        }
        return json;
    }
}
