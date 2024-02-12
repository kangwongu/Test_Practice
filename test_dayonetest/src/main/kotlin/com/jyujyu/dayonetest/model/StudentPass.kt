package com.jyujyu.dayonetest.model

import jakarta.persistence.*

@Entity
class StudentPass(

    var exam: String,
    var studentName: String,
    var avgScore: Double,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_pass_id")
    val id: Long? = null
) {
}