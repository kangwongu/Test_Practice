package com.testcafekiosk.integration

import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.junit.jupiter.Testcontainers

@ActiveProfiles("test")
@SpringBootTest
@Import(DBInitializer::class)
@Testcontainers
class IntegrationTest {

    @Autowired
    private lateinit var dataInitializer: DBInitializer

    @BeforeEach
    fun delete() {
        dataInitializer.clear()
    }
}