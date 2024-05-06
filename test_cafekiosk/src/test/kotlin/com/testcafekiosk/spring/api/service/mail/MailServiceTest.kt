package com.testcafekiosk.spring.api.service.mail

import com.testcafekiosk.spring.client.MailSendClient
import com.testcafekiosk.spring.domain.history.mail.MailSendHistory
import com.testcafekiosk.spring.domain.history.mail.MailSendHistoryRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.any
import org.mockito.Mockito.times
import org.mockito.Spy
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class MailServiceTest {

    // Mockito.mock으로 생성하지 않고 어노테이션으로도 생성가능
    @Mock
    private lateinit var mailSendClient: MailSendClient

    @Mock
    private lateinit var mailSendHistoryRepository: MailSendHistoryRepository

    // 해당 객체의 생성자를 보고, Mock객체들을 Inject해주려는 객체에 Inject해줌 (DI)
    @InjectMocks
    private lateinit var mailService: MailService

    @Test
    @DisplayName("메일을 전송한다")
    fun sendMail() {
        // given
        // Mock Mail Service의 행동을 정의
//        Mockito.`when`(
//            mailSendClient.sendEmail(
//                Mockito.anyString(),
//                Mockito.anyString(),
//                Mockito.anyString(),
//                Mockito.anyString()
//            )
//        )
//            .thenReturn(true)

        BDDMockito.given(mailSendClient.sendEmail(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
            .willReturn(true)

        // when
        val result = mailService.sendMail("", "", "", "")

        // 리포지토리의 save메소드가 1번만 불렸는지를 검증
        Mockito.verify(mailSendHistoryRepository, times(1)).save(any(MailSendHistory::class.java))

        // then
        Assertions.assertThat(result).isTrue()

    }
}