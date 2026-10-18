package com.jay.gulimall.product.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan("com.jay.gulimall.product.dao")
public class MybatisConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        // 1. 创建分页插件
        PaginationInnerInterceptor paginationInterceptor = new PaginationInnerInterceptor();

        // ===================== 分页高级配置 开始 =====================
        // 设置数据库类型（必须）
        paginationInterceptor.setDbType(DbType.MYSQL);

        // 溢出总页数后自动处理（比如查第100页，但只有10页，自动跳最后一页）
        paginationInterceptor.setOverflow(true);

        // 单页限制最大条数（防止一次性查10000条导致慢查询）
        paginationInterceptor.setMaxLimit(1000L);

        // ===================== 分页高级配置 结束 =====================

        interceptor.addInnerInterceptor(paginationInterceptor);

        return interceptor;
    }
}
