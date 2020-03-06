package com.leaves.smalltiger.common.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

@Configuration
public class MybatisMapperScannerConfigure {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();//设置绘画工厂
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");//设置数据源
        mapperScannerConfigurer.setBasePackage("com.leaves.smalltiger.*.mapper");//设置扫描的接口，可以多个，用逗号来凝结
        Properties properties = new Properties();
        properties.setProperty("notEmpty","false");//默认时是否非空
        properties.setProperty("IDENTITY","MYSQL");//主键策略遵循的数据库标准
        properties.setProperty("mappers","com.leaves.smalltiger.common.config.BaseMapper");//设置通用父mapper
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }

    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("reasonable","true");
        pageHelper.setProperties(properties);

        return pageHelper;
    }
}
