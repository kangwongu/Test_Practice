package com.jyujyu.dayonetest.service

import com.jyujyu.dayonetest.MyCalculator
import com.jyujyu.dayonetest.controller.response.ExamFailStudentResponse
import com.jyujyu.dayonetest.controller.response.ExamPassStudentResponse
import com.jyujyu.dayonetest.model.*
import com.jyujyu.dayonetest.repository.StudentFailRepository
import com.jyujyu.dayonetest.repository.StudentPassRepository
import com.jyujyu.dayonetest.repository.StudentScoreRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito

class StudentScoreServiceMockTest{

    private lateinit var studentScoreService: StudentScoreService
    private lateinit var studentScoreRepository: StudentScoreRepository
    private lateinit var studentPassRepository: StudentPassRepository
    private lateinit var studentFailRepository: StudentFailRepository

    @BeforeEach
    fun beforeEach() {
        studentScoreRepository = Mockito.mock(StudentScoreRepository::class.java)
        studentPassRepository = Mockito.mock(StudentPassRepository::class.java)
        studentFailRepository = Mockito.mock(StudentFailRepository::class.java)

        studentScoreService = StudentScoreService(
            studentScoreRepository,
            studentPassRepository,
            studentFailRepository
        )
    }

    // 행동 검증 (실행했는지?)
    @Test
    @DisplayName("성적 저장 로직 검증 / 평균점수가 60점 이상인 경우")
    fun saveScoreMockTest() {
        // given : 평균점수가 60점 이상인 경우
        val expectStudentScore = StudentScoreTestDataBuilder.passed()
        val expectStudentPass = StudentPassFixture.create(expectStudentScore)

        val studentScoreArgumentCaptor = ArgumentCaptor.forClass(StudentScore::class.java)
        val studentPassArgumentCaptor = ArgumentCaptor.forClass(StudentPass::class.java)

        // when
        studentScoreService.saveScore(
            expectStudentScore.studentName,
            expectStudentScore.exam,
            expectStudentScore.korScore,
            expectStudentScore.englishScore,
            expectStudentScore.mathScore
        )

        // then
        // 60점이상이면 pass이기 때문에 Fail리포지토리는 호출되지 않아야 함을 의미하는 테스트
        Mockito.verify(studentScoreRepository, Mockito.times(1)).save(studentScoreArgumentCaptor.capture())

        // 메소드 호출 시, 인자값 검증
        val capturedStudentScore = studentScoreArgumentCaptor.value
        assertThat(capturedStudentScore.studentName).isEqualTo(expectStudentScore.studentName)
        assertThat(capturedStudentScore.exam).isEqualTo(expectStudentScore.exam)
        assertThat(capturedStudentScore.korScore).isEqualTo(expectStudentScore.korScore)
        assertThat(capturedStudentScore.englishScore).isEqualTo(expectStudentScore.englishScore)
        assertThat(capturedStudentScore.mathScore).isEqualTo(expectStudentScore.mathScore)

        Mockito.verify(studentPassRepository, Mockito.times(1)).save(studentPassArgumentCaptor.capture())

        // 메소드 호출 시, 인자값 검증
        val capturedStudentPass = studentPassArgumentCaptor.value
        assertThat(capturedStudentPass.studentName).isEqualTo(expectStudentPass.studentName)
        assertThat(capturedStudentPass.exam).isEqualTo(expectStudentPass.exam)
        assertThat(capturedStudentPass.avgScore).isEqualTo(expectStudentPass.avgScore)

        Mockito.verify(studentFailRepository, Mockito.times(0)).save(Mockito.any())

    }

    // 행동 검증 (실행했는지?)
    @Test
    @DisplayName("성적 저장 로직 검증 / 평균점수가 60점 미만인 경우")
    fun saveScoreMockTest2() {
        // given : 평균점수가 60점 미만인 경우
        val expectStudentScore = StudentScoreFixture.failed()
        val expectStudentFail = StudentFailFixture.create(expectStudentScore)

        val studentScoreArgumentCaptor = ArgumentCaptor.forClass(StudentScore::class.java)
        val studentFailArgumentCaptor = ArgumentCaptor.forClass(StudentFail::class.java)

        // when
        studentScoreService.saveScore(
            expectStudentScore.studentName,
            expectStudentScore.exam,
            expectStudentScore.korScore,
            expectStudentScore.englishScore,
            expectStudentScore.mathScore
        )

        // then
        // 60점 미만이면 fail이기 때문에 Pass리포지토리는 호출되지 않아야 함을 의미하는 테스트
        Mockito.verify(studentScoreRepository, Mockito.times(1)).save(studentScoreArgumentCaptor.capture())

        // 메소드 호출 시, 인자값 검증
        val capturedStudentScore = studentScoreArgumentCaptor.value
        assertThat(capturedStudentScore.studentName).isEqualTo(expectStudentScore.studentName)
        assertThat(capturedStudentScore.exam).isEqualTo(expectStudentScore.exam)
        assertThat(capturedStudentScore.korScore).isEqualTo(expectStudentScore.korScore)
        assertThat(capturedStudentScore.englishScore).isEqualTo(expectStudentScore.englishScore)
        assertThat(capturedStudentScore.mathScore).isEqualTo(expectStudentScore.mathScore)

        Mockito.verify(studentPassRepository, Mockito.times(0)).save(Mockito.any())
        Mockito.verify(studentFailRepository, Mockito.times(1)).save(studentFailArgumentCaptor.capture())

        // 메소드 호출 시, 인자값 검증
        val capturedStudentFail = studentFailArgumentCaptor.value
        assertThat(capturedStudentFail.studentName).isEqualTo(expectStudentFail.studentName)
        assertThat(capturedStudentFail.exam).isEqualTo(expectStudentFail.exam)
        assertThat(capturedStudentFail.avgScore).isEqualTo(expectStudentFail.avgScore)
    }

    // 가짜 데이터 검증하기 (stub)
    @Test
    @DisplayName("합격자 명단 가져오기 검증")
    fun getPassStudentListTest() {
        // given
        val givenTestExam = "testexam"
        val expectStudent1 = StudentPassFixture.create("jj", givenTestExam)
        val expectStudent2 = StudentPassFixture.create("kk", givenTestExam)
        val nonExpectStudent3 = StudentPassFixture.create("ll", "secondExam")

        // studentPassRepository.findAll()을 호출하면, thenReturn절을 반환한다
        Mockito
            .`when`(studentPassRepository.findAll())
            .thenReturn(listOf(
                expectStudent1,
                expectStudent2,
                nonExpectStudent3,
        ))

        // when
        val expectResponses = listOf(expectStudent1, expectStudent2)
            .map { es -> ExamPassStudentResponse(es.studentName, es.avgScore) }
            .toList()
        val responses = studentScoreService.getPassStudentsList(givenTestExam)

        // then
        assertThat(responses).isEqualTo(expectResponses)
    }

    // 가짜 데이터 검증하기 (stub)
    @Test
    @DisplayName("불합격자 명단 가져오기 검증")
    fun getFailStudentListTest() {
        // given
        val givenTestExam = "testexam"
        val expectStudent1 = StudentFailFixture.create("jj", givenTestExam)
        val expectStudent2 = StudentFailFixture.create("kk", givenTestExam)
        val nonExpectStudent3 = StudentFailFixture.create("ll", "secondExam")

        // studentPassRepository.findAll()을 호출하면, thenReturn절을 반환한다
        Mockito
            .`when`(studentFailRepository.findAll())
            .thenReturn(listOf(
                expectStudent1,
                expectStudent2,
                nonExpectStudent3,
            ))

        // when
        val expectResponses = listOf(expectStudent1, expectStudent2)
            .map { es -> ExamFailStudentResponse(es.studentName, es.avgScore) }
            .toList()
        val responses = studentScoreService.getFailStudentsList(givenTestExam)

        // then
        assertThat(responses).isEqualTo(expectResponses)
    }

}