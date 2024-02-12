package com.jyujyu.dayonetest.model

import com.jyujyu.dayonetest.MyCalculator

class StudentPassFixture {

    companion object {
        fun create(studentScore: StudentScore): StudentPass {
            return StudentPass(
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

        fun create(studentName: String, exam: String): StudentPass {
            return StudentPass(
                studentName = studentName,
                exam = exam,
                avgScore = 80.0
            )
        }
    }
}