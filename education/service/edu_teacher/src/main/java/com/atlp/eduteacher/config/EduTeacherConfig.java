package com.atlp.eduteacher.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@MapperScan("com.atlp.eduteacher.mapper")
public class EduTeacherConfig {

    // 配置逻辑删除插件
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    // 配置分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    // 配置SQL执行性能分析插件
    @Bean
    @Profile({"dev", "test"}) // 设置监控的执行环境
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        // 超过设置的时间(毫秒)SQL不执行
        performanceInterceptor.setMaxTime(100);
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }
}
