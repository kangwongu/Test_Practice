package com.jyujyu.dayonetest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories
@SpringBootApplication
class DayonetestApplication

fun main(args: Array<String>) {
    runApplication<DayonetestApplication>(*args)
}
