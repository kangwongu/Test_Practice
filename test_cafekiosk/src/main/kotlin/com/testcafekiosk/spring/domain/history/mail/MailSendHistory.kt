package com.testcafekiosk.spring.domain.history.mail

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id


@Entity
class MailSendHistory(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val fromEmail: String,
    val toEmail: String,
    val title: String,
    val content: String,
) {
}