package com.seven.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "dzy")
@ComponentScan(basePackages = "com.seven.utils")
public class ProgramUtils {

    // 图片资源的存放位置
    private String picFolder;
    // 项目根目录(根url)
    private String baseUrl;

    public String getPicFolder() {
        return picFolder;
    }

    public void setPicFolder(String picFolder) {
        this.picFolder = picFolder;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
