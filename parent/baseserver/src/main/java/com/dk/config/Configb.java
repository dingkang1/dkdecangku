package com.dk.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.dk.dao.anthor"},sqlSessionFactoryRef = "sqlSessionFactoryb")
public class Configb {

    @Autowired
    DataSourceConfig dataSourceConfig;

    @Bean("anthorb")
    public DataSource getDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dataSourceConfig.getDriver_1());
        dataSource.setUrl(dataSourceConfig.getUrl_1());
        dataSource.setUsername(dataSourceConfig.getName_1());
        dataSource.setPassword(dataSourceConfig.getPassword_1());
        return dataSource;
    }

    @Bean(name = "sqlSessionFactoryb")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("anthorb") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        //设置扫描mapper.xml
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:mapper/anthor/*.xml");
        sqlSessionFactoryBean.setMapperLocations(resources);

        //设置扫描实体类
        sqlSessionFactoryBean.setTypeAliasesPackage("com.dk.entity");

        return sqlSessionFactoryBean.getObject();

    }
}
