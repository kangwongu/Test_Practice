package com.jyujyu.dayonetest.model

class StudentScoreFixture {

    companion object {
        fun passed(): StudentScore {
            return StudentScore(
                studentName = "defaultName",
                exam = "defaultExam",
                korScore = 80,
                englishScore = 70,
                mathScore = 100
            )
        }

        fun failed(): StudentScore {
            return StudentScore(
                studentName = "defaultName",
                exam = "defaultExam",
                korScore = 40,
                englishScore = 30,
                mathScore = 50
            )
        }
    }
}