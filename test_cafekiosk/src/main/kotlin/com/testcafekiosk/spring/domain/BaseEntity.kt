package com.testcafekiosk.spring.domain

import com.testcafekiosk.spring.config.annotation.AllOpen
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@AllOpen
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity {
    @CreatedDate
    lateinit var createdDateTime: LocalDateTime

    @LastModifiedDate
    lateinit var modifiedDateTime: LocalDateTime
}