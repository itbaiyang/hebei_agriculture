package com.zrodo.agriculture.controller;

import com.zrodo.agriculture.entity.SysDept;
import com.zrodo.agriculture.entity.SysUser;
import com.zrodo.agriculture.repository.DeptMapper;
import com.zrodo.agriculture.repository.UserMapper;
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

@Api(value = "用户", description = "用户管理")
@RestController
@Component
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping(value = "/user")
    @ApiOperation(value = "添加用户", notes = "添加用户")
    public String addUser(HttpServletRequest request, @ModelAttribute SysUser sysUser)
    {
        String json;
        try
        {
            userMapper.addUser(sysUser);
            json = JsonStatus.success();
        }
        catch(Exception e)
        {
            json = JsonStatus.failure();
        }
        return json;
    }

    @PutMapping(value = "/user")
    @ApiOperation(value = "修改用户", notes = "修改用户")
    public String updateUser(HttpServletRequest request, @ModelAttribute SysUser sysUser)
    {
        String json;
        try
        {
            userMapper.updateUser(sysUser);
            json = JsonStatus.success();
        }
        catch(Exception e)
        {
            json = JsonStatus.failure();
        }
        return json;
    }

    @DeleteMapping(value = "/user")
    @ApiOperation(value = "删除用户", notes = "删除用户")
    public String deleteUser(HttpServletRequest request,
                             @ApiParam(required = true, name = "userId", value = "城市编号")
                             @RequestParam int userId)
    {
        String json;
        try
        {
            userMapper.deleteUser(userId);
            json = JsonStatus.success();
        }
        catch(Exception e)
        {
            json = JsonStatus.failure();
        }
        return json;
    }

    /*通过上级部门查询，通过区划Id查询*/
    @GetMapping(value = "/userList")
    @ApiOperation(value = "查询用户列表", notes = "查询用户列表")
    public String queryDeptList(HttpServletRequest request,
                                @ApiParam(name = "companyId", value = "企业Id(企业部门二选一)")
                                    @RequestParam(value="companyId",required=false) Integer companyId,
                                @ApiParam(name = "deptId", value = "部门Id(企业部门二选一)")
                                    @RequestParam(value="deptId",required=false) Integer deptId,
                                @ApiParam(required = true, name = "pageNo", value = "页码")
                                    @RequestParam(defaultValue = "1") Integer pageNo,
                                @ApiParam(required = true, name = "pageSize", value = "每页个数")
                                    @RequestParam(defaultValue = "20") Integer pageSize
    ) {
        String json;
        try
        {
            List<Map<String,Object>> result = null;
            if(companyId != null){
                result = userMapper.queryUserListByCompany(companyId,(pageNo - 1) * pageSize, pageSize);

            }else if(deptId != null) {
                result = userMapper.queryUserListByDept(deptId,(pageNo - 1) * pageSize, pageSize);
            }
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
