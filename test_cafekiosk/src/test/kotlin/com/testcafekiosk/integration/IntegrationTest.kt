package com.testcafekiosk.integration

import com.testcafekiosk.spring.client.MailSendClient
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.junit.jupiter.Testcontainers

@ActiveProfiles("test")
@SpringBootTest
@Import(DBInitializer::class)
@Testcontainers
abstract class IntegrationTest {

    // IntegrationTest를 상속받는 모든 테스트들이 해당 Bean을 띄워야 함
    // 레이어별로 Integration abstract class를 만들어 줄 수도 있고
    // 아니면, MockBean이 없는 Integration과 MockBean을 함께 정의해놓은 Integration으로 분리
    @MockBean
    internal lateinit var mailSendClient: MailSendClient

    @Autowired
    private lateinit var dataInitializer: DBInitializer

    @BeforeEach
    fun delete() {
        dataInitializer.clear()
    }
}