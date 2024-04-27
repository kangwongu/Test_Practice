package com.testcafekiosk.spring.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter

@Configuration
class HibernateConfig {
    private val hibernateJpaVendorAdapter = HibernateJpaVendorAdapter()

    @Bean
    fun hibernateJpaVendorAdapter(): HibernateJpaVendorAdapter {
        hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect")
        return hibernateJpaVendorAdapter
    }
}