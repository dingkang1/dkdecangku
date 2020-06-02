package com.dk.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;



@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceConfig {
    private String url;
    private String driver;
    private String name;
    private String password;

    private String url_1;
    private String driver_1;
    private String name_1;
    private String password_1;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl_1() {
        return url_1;
    }

    public void setUrl_1(String url_1) {
        this.url_1 = url_1;
    }

    public String getDriver_1() {
        return driver_1;
    }

    public void setDriver_1(String driver_1) {
        this.driver_1 = driver_1;
    }

    public String getName_1() {
        return name_1;
    }

    public void setName_1(String name_1) {
        this.name_1 = name_1;
    }

    public String getPassword_1() {
        return password_1;
    }

    public void setPassword_1(String password_1) {
        this.password_1 = password_1;
    }
}
