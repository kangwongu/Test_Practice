package com.testcafekiosk.spring.domain.history.mail

import org.springframework.data.jpa.repository.JpaRepository

interface MailSendHistoryRepository : JpaRepository<MailSendHistory, Long> {
}