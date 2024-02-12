package com.jyujyu.dayonetest.model

import com.jyujyu.dayonetest.MyCalculator

class StudentFailFixture {

    companion object {
        fun create(studentScore: StudentScore): StudentFail {
            return StudentFail(
                exam = studentScore.exam,
                studentName = studentScore.studentName,
                avgScore = MyCalculator()
                    .add(studentScore.korScore.toDouble())
                    .add(studentScore.englishScore.toDouble())
                    .add(studentScore.mathScore.toDouble())
                    .divide(3.0)
                    .result
            )
        }

        fun create(studentName: String, exam: String): StudentFail {
            return StudentFail(
                exam = exam,
                studentName = studentName,
                avgScore = 40.0
            )
        }
    }
}