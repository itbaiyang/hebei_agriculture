package com.zrodo.agriculture.controller;

import com.zrodo.agriculture.entity.SysDept;
import com.zrodo.agriculture.repository.DeptMapper;
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

@Api(value = "部门", description = "部门管理")
@RestController
@Component
public class DeptController {

    @Autowired
    private DeptMapper deptMapper;

    @PostMapping(value = "/dept")
    @ApiOperation(value = "添加部门", notes = "添加部门")
    public String addDept(HttpServletRequest request, @ModelAttribute SysDept sysDept)
    {
        String json;
        try
        {
            deptMapper.addDept(sysDept);
            json = JsonStatus.success();
        }
        catch(Exception e)
        {
            json = JsonStatus.failure();
        }
        return json;
    }

    @PutMapping(value = "/dept")
    @ApiOperation(value = "修改部门", notes = "修改部门")
    public String updateDept(HttpServletRequest request, @ModelAttribute SysDept sysDept)
    {
        String json;
        try
        {
            deptMapper.updateDept(sysDept);
            json = JsonStatus.success();
        }
        catch(Exception e)
        {
            json = JsonStatus.failure();
        }
        return json;
    }

    @DeleteMapping(value = "/dept")
    @ApiOperation(value = "删除部门", notes = "删除部门")
    public String deleteDept(HttpServletRequest request,
                             @ApiParam(required = true, name = "deptId", value = "城市编号") @RequestParam int deptId)
    {
        String json;
        try
        {
            deptMapper.deleteDept(deptId);
            json = JsonStatus.success();
        }
        catch(Exception e)
        {
            json = JsonStatus.failure();
        }
        return json;
    }

    @GetMapping(value = "/deptList")
    @ApiOperation(value = "删除部门", notes = "删除部门")
    public String queryDeptList(HttpServletRequest request,
                             @ApiParam(required = false, name = "areaId", value = "城市编号")
                                @RequestParam(value="areaId",required=false) Integer areaId,
                             @ApiParam(required = false, name = "pId", value = "页码")
                                @RequestParam(value="pId",required=false) Integer pId
    ) {
        String json;
        try
        {
            List<Map<String,Object>> result = deptMapper.queryDeptList(areaId,pId);
            Map<String, Object> map = JsonMapUtils.buildSuccessMap();
            map.put("result", result);
            json = Tool.getJsonFromObect(map);
        }
        catch(Exception e)
        {
            json = JsonStatus.failure();
        }
        return json;
    }

    @GetMapping(value = "/deptItem")
    @ApiOperation(value = "删除部门", notes = "删除部门")
    public String queryDeptById(HttpServletRequest request,
                                @ApiParam(required = true, name = "deptId", value = "城市编号")
                                @RequestParam(value="deptId",required=true) int deptId
    ) {
        String json;
        try
        {
            deptMapper.queryDeptById(deptId);
            json = JsonStatus.success();
        }
        catch(Exception e)
        {
            json = JsonStatus.failure();
        }
        return json;
    }
}
