package com.jyujyu.dayonetest.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.context.annotation.Configuration

@Entity
@Table(name = "student_score")
class StudentScore(

    var exam: String,
    var studentName: String,
    var korScore: Int,
    var englishScore: Int,
    var mathScore: Int,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_score_id")
    val id: Long? = null
) {
}