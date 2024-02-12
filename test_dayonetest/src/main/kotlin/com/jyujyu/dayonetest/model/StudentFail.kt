package com.jyujyu.dayonetest.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class StudentFail(
    var exam: String,
    var studentName: String,
    var avgScore: Double,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_fail_id")
    val id: Long? = null
) {
}