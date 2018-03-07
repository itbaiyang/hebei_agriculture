package com.zrodo.agriculture.controller;

import com.zrodo.agriculture.domain.AccountInfo;
import com.zrodo.agriculture.entity.Acre;
import com.zrodo.agriculture.repository.AcreMapper;
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

@Api(value = "地块管理", description = "地块管理")
@RestController
@Component
public class AcreController {
    @Autowired
    private AcreMapper acreMapper;

    @PostMapping(value = "insertAcre")
    @ApiOperation(value = "插入地块信息", notes = "")
    public String insertAcre(HttpServletRequest request,
                             @ModelAttribute Acre acre) {
        AccountInfo user = Token.getUser(request);
        acre.setCompanyId(user.getDeptId());
        String json;
        try {
            int id = acreMapper.insertAcre(acre);
            for (String item : acre.getImgList()) {
                acreMapper.insertAcreImg(id, item);
            }
            json = JsonStatus.success();
        } catch (Exception e) {
            e.printStackTrace();
            json = JsonStatus.failure();
        }
        return json;
    }

    @PutMapping(value = "updateAcre")
    @ApiOperation(value = "更新地块信息", notes = "")
    public String updateAcre(HttpServletRequest request,
                             @ModelAttribute Acre acre) {
        String json;
        try {
            acreMapper.updateAcre(acre);
            json = JsonStatus.success();
        } catch (Exception e) {
            e.printStackTrace();
            json = JsonStatus.failure();
        }
        return json;
    }

    @PostMapping(value = "insertAcreImg")
    @ApiOperation(value = "添加地块图片", notes = "")
    public String insertAcreImg(HttpServletRequest request,
                                @ApiParam(required = true, name = "acreId", value = "地块Id") @RequestParam(value = "acreId", required = false) int acreId,
                                @ApiParam(required = true, name = "url", value = "图片地址") @RequestParam(value = "url", required = false) String url) {
        String json = null;
        try {
            acreMapper.insertAcreImg(acreId, url);
            json = JsonStatus.success();
        } catch (Exception e) {
            e.printStackTrace();
            json = JsonStatus.failure();
        }
        return json;
    }

    @DeleteMapping(value = "deleteAcreImg")
    @ApiOperation(value = "删除地块图片", notes = "")
    public String deleteAcreImg(HttpServletRequest request,
                                @ApiParam(required = true, name = "id", value = "图片Id") @RequestParam(value = "id", required = false) int id) {
        String json = null;
        try {
            acreMapper.deleteAcreImg(id);
            json = JsonStatus.success();
        } catch (Exception e) {
            e.printStackTrace();
            json = JsonStatus.failure();
        }
        return json;
    }

    @DeleteMapping(value = "deleteAcre")
    @ApiOperation(value = "删除地块信息", notes = "")
    public String deleteAcre(HttpServletRequest request,
                             @ApiParam(required = true, name = "acreId", value = "地块Id") @RequestParam(value = "acreId", required = false) int acreId) {
        String json = null;
        try {
            acreMapper.deleteAcre(acreId);
            json = JsonStatus.success();
        } catch (Exception e) {
            e.printStackTrace();
            json = JsonStatus.failure();
        }
        return json;
    }

    @GetMapping(value = "queryAcreById")
    @ApiOperation(value = "查询地块信息", notes = "")
    public String queryAcreById(HttpServletRequest request,
                                @ApiParam(required = true, name = "acreId", value = "地块Id") @RequestParam(value = "acreId", required = false) int acreId) {
        String json = null;
        try {
            Map<String, Object> acre = acreMapper.queryAcreById(acreId);
            List<Map<String, Object>> imgList = acreMapper.queryAcreImgList(acreId);
            acre.put("imgList", imgList);
            Map<String, Object> map = JsonMapUtils.buildSuccessMap();
            map.put("result", acre);
            json = Tool.getJsonFromObect(map);
        } catch (Exception e) {
            e.printStackTrace();
            json = JsonStatus.failure();
        }
        return json;
    }

    @GetMapping(value = "queryAcreList")
    @ApiOperation(value = "地块信息列表", notes = "")
    public String queryAcreList(HttpServletRequest request,
                                @ApiParam(required = false, name = "startDate", value = "开始日期") @RequestParam(value = "startDate", required = false) String startDate,
                                @ApiParam(required = false, name = "endDate", value = "结束日期") @RequestParam(value = "endDate", required = false) String endDate,
                                @ApiParam(required = true, name = "pageNo", value = "页码") @RequestParam(value = "pageNo", required = true, defaultValue = "1") Integer pageNo,
                                @ApiParam(required = true, name = "pageSize", value = "分页条数") @RequestParam(value = "pageSize", required = true, defaultValue = "20") Integer pageSize,

                                @ApiParam(required = false, name = "companyId", value = "企业Id") @RequestParam(value = "companyId", required = false) String companyId,
                                @ApiParam(required = false, name = "typeId", value = "地块类型") @RequestParam(value = "typeId", required = false) Integer typeId,
                                @ApiParam(required = false, name = "acreName", value = "地块名称") @RequestParam(value = "acreName", required = false) String acreName) {
        AccountInfo user = Token.getUser(request);
        if (StringUtils.isBlank(companyId)) {
            companyId = Integer.toString(user.getCompanyId());
        }
        String json = null;
        try {
            List<Map<String, Object>> acreList = acreMapper.queryAcreList(startDate, endDate, (pageNo - 1) * pageSize, pageSize, companyId, acreName, typeId);
            int inputCount = acreMapper.queryAcreListCount(startDate, endDate, companyId, acreName, typeId);
            Page page = new Page(acreList, pageNo, pageSize, inputCount);
            json = Tool.getPageSuccessStr(page);
        } catch (Exception e) {
            e.printStackTrace();
            json = JsonStatus.failure();
        }
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "queryAcreTypeList", method = {RequestMethod.GET})
    @ApiOperation(value = "地块类型列表", notes = "")
    public String queryAcreTypeList(HttpServletRequest request) {
        String json = null;
        try {
            List<Map<String, Object>> acreTypeList = acreMapper.queryAcreTypeList();
            Map<String, Object> map = JsonMapUtils.buildSuccessMap();
            map.put("result", acreTypeList);
            json = Tool.getJsonFromObect(map);
        } catch (Exception e) {
            e.printStackTrace();
            json = JsonStatus.failure();
        }
        return json;
    }
}
