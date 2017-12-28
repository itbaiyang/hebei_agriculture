package com.zrodo.agriculture.controller;

import com.zrodo.agriculture.entity.SysRole;
import com.zrodo.agriculture.repository.RoleMapper;
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

@Api(value = "角色", description = "角色管理")
@RestController
@Component
public class RoleController {

    @Autowired
    private RoleMapper roleMapper;

    @PostMapping(value = "/role")
    @ApiOperation(value = "添加角色", notes = "添加角色")
    public String addRole(HttpServletRequest request, @ModelAttribute SysRole sysRole)
    {
        String json;
        try
        {
            roleMapper.addRole(sysRole);
            json = JsonStatus.success();
        }
        catch(Exception e)
        {
            json = JsonStatus.failure();
        }
        return json;
    }

    @PutMapping(value = "/role")
    @ApiOperation(value = "修改角色", notes = "修改角色")
    public String updateRole(HttpServletRequest request, @ModelAttribute SysRole sysRole)
    {
        String json;
        try
        {
            roleMapper.updateRole(sysRole);
            json = JsonStatus.success();
        }
        catch(Exception e)
        {
            json = JsonStatus.failure();
        }
        return json;
    }

    @DeleteMapping(value = "/role")
    @ApiOperation(value = "删除角色", notes = "删除角色")
    public String deleteRole(HttpServletRequest request,
                             @ApiParam(required = true, name = "roleId", value = "角色Id")
                             @RequestParam int roleId)
    {
        String json;
        try
        {
            roleMapper.deleteRole(roleId);
            json = JsonStatus.success();
        }
        catch(Exception e)
        {
            json = JsonStatus.failure();
        }
        return json;
    }

    /*通过上级部门查询，通过区划Id查询*/
    @GetMapping(value = "/roleList")
    @ApiOperation(value = "查询角色列表", notes = "查询角色列表")
    public String queryDeptList(HttpServletRequest request,
                                @ApiParam(name = "levelId", value = "级别Id")
                                @RequestParam(value="levelId",required=false) Integer levelId,
                                @ApiParam(name = "type", value = "角色类型")
                                    @RequestParam(value="type",required=false) Integer type,
                                @ApiParam(required = true, name = "pageNo", value = "页码")
                                @RequestParam(defaultValue = "1") Integer pageNo,
                                @ApiParam(required = true, name = "pageSize", value = "每页个数")
                                @RequestParam(defaultValue = "20") Integer pageSize
    ) {
        String json;
        try
        {
            List<Map<String,Object>> result = roleMapper.queryRoleListByLevel(levelId,type,(pageNo - 1) * pageSize, pageSize);
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

}
