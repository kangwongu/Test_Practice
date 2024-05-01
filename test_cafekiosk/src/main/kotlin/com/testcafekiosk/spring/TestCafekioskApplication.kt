package com.testcafekiosk.spring

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
class TestCafekioskApplication

fun main(args: Array<String>) {
    runApplication<TestCafekioskApplication>(*args)
}

