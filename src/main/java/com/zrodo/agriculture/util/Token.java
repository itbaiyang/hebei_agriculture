package com.zrodo.agriculture.util;

import com.zrodo.agriculture.domain.AccountInfo;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * token转用户实体类
 *
 * @author wangmin
 * <p>
 * 2016年7月14日
 */
public class Token {

    public static AccountInfo getUser(HttpServletRequest request) {
        AccountInfo accountInfo = null;
        try {
            String tokenKey = (String) request.getAttribute("token");
            ObjectMapper mapper = new ObjectMapper();
            accountInfo = mapper.readValue(tokenKey, AccountInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountInfo;
    }
}
