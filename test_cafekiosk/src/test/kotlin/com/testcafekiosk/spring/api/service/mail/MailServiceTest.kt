package com.testcafekiosk.spring.api.service.mail

import com.testcafekiosk.spring.client.MailSendClient
import com.testcafekiosk.spring.domain.history.mail.MailSendHistory
import com.testcafekiosk.spring.domain.history.mail.MailSendHistoryRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.any
import org.mockito.Mockito.times

class MailServiceTest {

    @Test
    @DisplayName("메일을 전송한다")
    fun sendMail() {
        // given
        // Mock 객체 생성
        val mailSendClient = Mockito.mock(MailSendClient::class.java)
        val mailSendHistoryRepository = Mockito.mock(MailSendHistoryRepository::class.java)

        // Mock 객체로 Mock MailService 생성
        val mailService = MailService(mailSendClient, mailSendHistoryRepository)

        // Mock Mail Service의 행동을 정의
        Mockito.`when`(
            mailSendClient.sendEmail(
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.anyString()
            )
        )
            .thenReturn(true)

//        Mockito.`when`(
//            mailSendHistoryRepository.save(
//                any(MailSendHistory::class.java)
//            )
//        )


        // when
        val result = mailService.sendMail("", "", "", "")

        // 리포지토리의 save메소드가 1번만 불렸는지를 검증
        Mockito.verify(mailSendHistoryRepository, times(1)).save(any(MailSendHistory::class.java))

        // then
        Assertions.assertThat(result).isTrue()

    }
}