package com.zrodo.agriculture.config;

import com.zrodo.agriculture.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailService userDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers( "/css/**","/webjars/**","/image/**").permitAll()
//                .antMatchers("/setting").authenticated()
                .antMatchers("/setting").hasAnyRole("MEMBER","SUPER_ADMIN")
                .anyRequest().authenticated()
                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
                .logout()
                .permitAll();
        http
                .csrf().disable();
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",//swagger api json
                "/swagger-resources/configuration/ui",//用来获取支持的动作
                "/swagger-resources",//用来获取api-docs的URI
                "/swagger-resources/configuration/security",//安全选项
                "/webjars/**",//安全选项
                "/swagger-ui.html");
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);//配置自定义userDetailService
    }
}