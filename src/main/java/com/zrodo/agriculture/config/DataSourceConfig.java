package com.zrodo.agriculture.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * Created by admin on 2017/10/31.
 */
@Configuration
@MapperScan(basePackages = "com.zrodo.agriculture.repository",sqlSessionFactoryRef = "priSqlSessionFactory")
public class DataSourceConfig {

    @Bean(name = "priDataSource")
    @Primary
    @ConfigurationProperties("spring.datasource.primary")
    public DataSource primaryDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "priSqlSessionFactory")
    @Primary
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("priDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean ();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return bean.getObject();
    }
}
