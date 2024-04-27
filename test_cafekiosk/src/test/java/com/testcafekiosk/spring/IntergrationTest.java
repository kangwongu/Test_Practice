package com.testcafekiosk.spring;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;

@ActiveProfiles("test")
@SpringBootTest
@Import(DBInitializer.class)
public class IntergrationTest {

//    private static final String ROOT = "test";
//    private static final String ROOT_PASSWORD = "1234";

    @Autowired
    private DBInitializer dataInitializer;

//    @Container
//    protected static MySQLContainer container;
//
//    @DynamicPropertySource
//    private static void configureProperties(final DynamicPropertyRegistry registry) {
//        registry.add("spring.datasource.url", container::getJdbcUrl);
//        registry.add("spring.datasource.username", () -> ROOT);
//        registry.add("spring.datasource.password", () -> ROOT_PASSWORD);
//    }
//
//    static {
//        container = new MySQLContainer("mysql:8")
//            .withDatabaseName("test")
//            .withUsername(ROOT)
//            .withPassword(ROOT_PASSWORD)
//            .withInitScript("resources/schema.sql");
//        container.start();
//    }

    @BeforeEach
    void delete() {
        dataInitializer.clear();
    }

}
