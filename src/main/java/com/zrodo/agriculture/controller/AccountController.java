package com.zrodo.agriculture.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zrodo.agriculture.domain.AccountInfo;
import com.zrodo.agriculture.repository.UserMapper;
import com.zrodo.agriculture.util.Tool;
import com.zrodo.agriculture.util.json.JsonMapUtils;
import com.zrodo.agriculture.util.json.JsonStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Api(value = "账号", description = "登录，登出，密码修改")
@RestController
@Component
public class AccountController {

    @Value("${token.expire.time}")
    private Long expireTime;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Value("${application.message:Hello World}")
    private String message = "Hello World";

    @GetMapping(value = "/")
    @ApiOperation(value = "入口", notes = "入口")
    public String home(Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", this.message);
        return "login3";
    }


    @PostMapping(value = "/login")
    @ApiOperation(value = "登录", notes = "登录")
    public String login(@ApiParam(required = true, name = "account", value = "用户名") @RequestParam String account,
                        @ApiParam(required = true, name = "password", value = "密码") @RequestParam String password)
    {
        String json;
        try
        {
//            account = Tool.getStrFromBase64(account);
//            password = Tool.getStrFromBase64(password);
            AccountInfo accountInfo = userMapper.queryUserByAccount(account);
            if (accountInfo == null)
            {
                json = JsonStatus.userNull();
            } else if(!accountInfo.getPassword().equals(Tool.MD5(password)))
            {
                json = JsonStatus.passwdError();
            }
            else
            {
                accountInfo.setPassword(null);
                Map<String, Object> map = JsonMapUtils.buildSuccessMap();
                map.put("resultList", accountInfo);
                String token = Tool.getUUID();
                map.put("token", token);
                ObjectMapper mapper = new ObjectMapper();
                json = mapper.writeValueAsString(map);
                redisTemplate.boundValueOps(token)
                        .set(mapper.writeValueAsString(accountInfo), expireTime, TimeUnit.MINUTES);
            }
        }
        catch(Exception e)
        {
            json = JsonStatus.failure();
        }
        return json;
    }

    @ResponseBody
    @PostMapping(value = "/logout")
    @ApiOperation(value = "退出", notes = "退出登录")
    public String hello(HttpServletRequest request,
                        @ApiParam(required = true, name = "token", value = "token") @RequestHeader String token)
    {
        try {
            String tokenValue = redisTemplate.boundValueOps(token).get();
            if (tokenValue != null) {
                redisTemplate.delete(token);
                return JsonStatus.success();
            } else {
                return JsonStatus.tokenExpire();
            }
        } catch (Exception e) {
            return JsonStatus.failure();
        }
    }
}
