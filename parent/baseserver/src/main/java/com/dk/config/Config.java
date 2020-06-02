package com.dk.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.dk.dao.anthora"},sqlSessionFactoryRef ="sqlSessionFactory" )
public class Config {

    @Autowired
    DataSourceConfig dataSourceConfig;

    @Bean("anthora")
    public DataSource getDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
         dataSource.setDriverClassName(dataSourceConfig.getDriver());
         dataSource.setUrl(dataSourceConfig.getUrl());
         dataSource.setUsername(dataSourceConfig.getName());
         dataSource.setPassword(dataSourceConfig.getPassword());
        return dataSource;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("anthora") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        //设置扫描mapper.xml
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:mapper/anthora/*.xml");
        sqlSessionFactoryBean.setMapperLocations(resources);

        //设置扫描实体类
        sqlSessionFactoryBean.setTypeAliasesPackage("com.dk.entity");
        return sqlSessionFactoryBean.getObject();

    }
}
