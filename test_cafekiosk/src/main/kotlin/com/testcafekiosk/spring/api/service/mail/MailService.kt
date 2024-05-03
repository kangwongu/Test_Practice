package com.testcafekiosk.spring.api.service.mail

import com.testcafekiosk.spring.client.MailSendClient
import com.testcafekiosk.spring.domain.history.mail.MailSendHistory
import com.testcafekiosk.spring.domain.history.mail.MailSendHistoryRepository
import org.springframework.stereotype.Service

@Service
class MailService(
    private val mailSendClient: MailSendClient,
    private val mailSendHistoryRepository: MailSendHistoryRepository,
) {
    fun sendMail(fromEmail: String, toEmail: String, title: String, content: String): Boolean {
        val result = mailSendClient.sendEmail(fromEmail, toEmail, title, content)

        if (result) {
            mailSendHistoryRepository.save(
                MailSendHistory(
                    fromEmail = fromEmail,
                    toEmail = toEmail,
                    title = title,
                    content = content
                )
            )
            return true
        }

        return false
    }
}