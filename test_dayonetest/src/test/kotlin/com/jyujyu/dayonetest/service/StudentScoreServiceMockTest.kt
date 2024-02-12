package com.jyujyu.dayonetest.service

import com.jyujyu.dayonetest.repository.StudentFailRepository
import com.jyujyu.dayonetest.repository.StudentPassRepository
import com.jyujyu.dayonetest.repository.StudentScoreRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mock
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

    // 행동 검증 (실행했는지?)
    @Test
    @DisplayName("성적 저장 로직 검증 / 평균점수가 60점 이상인 경우")
    fun saveScoreMockTest() {
        // given : 평균점수가 60점 이상인 경우
        val studentScoreRepository = Mockito.mock(StudentScoreRepository::class.java)
        val studentPassRepository = Mockito.mock(StudentPassRepository::class.java)
        val studentFailRepository = Mockito.mock(StudentFailRepository::class.java)

        val studentScoreService = StudentScoreService(
            studentScoreRepository,
            studentPassRepository,
            studentFailRepository
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
        // 60점이상이면 pass이기 때문에 Fail리포지토리는 호출되지 않아야 함을 의미하는 테스트
        Mockito.verify(studentScoreRepository, Mockito.times(1)).save(Mockito.any())
        Mockito.verify(studentPassRepository, Mockito.times(1)).save(Mockito.any())
        Mockito.verify(studentFailRepository, Mockito.times(0)).save(Mockito.any())

    }

    // 행동 검증 (실행했는지?)
    @Test
    @DisplayName("성적 저장 로직 검증 / 평균점수가 60점 미만인 경우")
    fun saveScoreMockTest2() {
        // given : 평균점수가 60점 미만인 경우
        val studentScoreRepository = Mockito.mock(StudentScoreRepository::class.java)
        val studentPassRepository = Mockito.mock(StudentPassRepository::class.java)
        val studentFailRepository = Mockito.mock(StudentFailRepository::class.java)

        val studentScoreService = StudentScoreService(
            studentScoreRepository,
            studentPassRepository,
            studentFailRepository
        )

        val givenStudentName: String = "jj"
        val givenExam: String = "testexam"
        val givenKorScore = 20
        val givenEnglishScore = 40
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
        // 60점 미만이면 fail이기 때문에 Pass리포지토리는 호출되지 않아야 함을 의미하는 테스트
        Mockito.verify(studentScoreRepository, Mockito.times(1)).save(Mockito.any())
        Mockito.verify(studentPassRepository, Mockito.times(0)).save(Mockito.any())
        Mockito.verify(studentFailRepository, Mockito.times(1)).save(Mockito.any())

    }
}