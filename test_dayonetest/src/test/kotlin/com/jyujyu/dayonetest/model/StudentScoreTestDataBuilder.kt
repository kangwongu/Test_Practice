package com.jyujyu.dayonetest.model

// Test Data Builder 패턴
class StudentScoreTestDataBuilder {

    companion object {
        fun passed(
            studentName: String = "defaultName",
            exam: String = "defaultExam",
            korScore: Int = 80,
            englishScore: Int = 100,
            mathScore: Int = 90
        ): StudentScore {
            return StudentScore(
                studentName = studentName,
                exam = exam,
                korScore = korScore,
                englishScore = englishScore,
                mathScore = mathScore
            )
        }

        fun failed(
            studentName: String = "defaultName",
            exam: String = "defaultExam",
            korScore: Int = 80,
            englishScore: Int = 100,
            mathScore: Int = 90
        ): StudentScore {
            return StudentScore(
                studentName = studentName,
                exam = exam,
                korScore = korScore,
                englishScore = englishScore,
                mathScore = mathScore
            )
        }
    }
}