package com.jyujyu.dayonetest.service

import com.jyujyu.dayonetest.repository.StudentFailRepository
import com.jyujyu.dayonetest.repository.StudentPassRepository
import com.jyujyu.dayonetest.repository.StudentScoreRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class StudentScoreServiceMockTest {

    @Test
    @DisplayName("첫번째 Mock 테스트")
    fun firstSaveScoreMockTest() {
        // given
        val studentScoreService = StudentScoreService(
            Mockito.mock(StudentScoreRepository::class.java),
            Mockito.mock(StudentPassRepository::class.java),
            Mockito.mock(StudentFailRepository::class.java)
        )
        val givenStudentName: String = "jj"
        val givenExam: String = "testexam"
        val givenKorScore = 80
        val givenEnglishScore = 100
        val givenMathScore = 60

        // when
        studentScoreService.saveScore(
            givenStudentName,
            givenExam,
            givenKorScore,
            givenEnglishScore,
            givenMathScore
        )

        // then
    }
}