package com.testcafekiosk.spring.client

import org.springframework.stereotype.Component

@Component
class MailSendClient {

    fun sendEmail(fromEmail: String, toEmail: String, title: String, content: String): Boolean {
        // 메일전송
        println("메일 전송")
        throw IllegalStateException("메일 전송")
//        return true
    }
}