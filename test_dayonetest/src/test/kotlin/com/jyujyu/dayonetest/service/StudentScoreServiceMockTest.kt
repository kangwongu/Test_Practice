package com.jyujyu.dayonetest.service

import com.jyujyu.dayonetest.MyCalculator
import com.jyujyu.dayonetest.controller.response.ExamFailStudentResponse
import com.jyujyu.dayonetest.controller.response.ExamPassStudentResponse
import com.jyujyu.dayonetest.model.StudentFail
import com.jyujyu.dayonetest.model.StudentPass
import com.jyujyu.dayonetest.model.StudentScore
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
        val givenStudentName: String = "jj"
        val givenExam: String = "testexam"
        val givenKorScore = 80
        val givenEnglishScore = 100
        val givenMathScore = 60

        //
        val expectStudentScore = StudentScore(
            studentName = givenStudentName,
            exam = givenExam,
            korScore = givenKorScore,
            englishScore = givenEnglishScore,
            mathScore = givenMathScore
        )

        val expectStudentPass = StudentPass(
            studentName = givenStudentName,
            exam = givenExam,
            avgScore = (
                    MyCalculator()
                        .add(givenKorScore.toDouble())
                        .add(givenEnglishScore.toDouble())
                        .add(givenMathScore.toDouble())
                        .divide(3.0)
                        .result
                    )
        )

        val studentScoreArgumentCaptor = ArgumentCaptor.forClass(StudentScore::class.java)
        val studentPassArgumentCaptor = ArgumentCaptor.forClass(StudentPass::class.java)

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

        val expectStudentScore = StudentScore(
            studentName = givenStudentName,
            exam = givenExam,
            korScore = givenKorScore,
            englishScore = givenEnglishScore,
            mathScore = givenMathScore
        )

        val expectStudentFail = StudentPass(
            studentName = givenStudentName,
            exam = givenExam,
            avgScore = (
                    MyCalculator()
                        .add(givenKorScore.toDouble())
                        .add(givenEnglishScore.toDouble())
                        .add(givenMathScore.toDouble())
                        .divide(3.0)
                        .result
                    )
        )

        val studentScoreArgumentCaptor = ArgumentCaptor.forClass(StudentScore::class.java)
        val studentFailArgumentCaptor = ArgumentCaptor.forClass(StudentFail::class.java)

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
        val studentScoreRepository = Mockito.mock(StudentScoreRepository::class.java)
        val studentPassRepository = Mockito.mock(StudentPassRepository::class.java)
        val studentFailRepository = Mockito.mock(StudentFailRepository::class.java)

        val givenTestExam = "testexam"
        val expectStudent1 = StudentPass(id = 1, studentName = "jj", avgScore = 70.0, exam = givenTestExam)
        val expectStudent2 = StudentPass(id = 2, studentName = "kk", avgScore = 80.0, exam = givenTestExam)
        val nonExpectStudent3 = StudentPass(id = 3, studentName = "ll", avgScore = 90.0, exam = "secondExam")

        // studentPassRepository.findAll()을 호출하면, thenReturn절을 반환한다
        Mockito
            .`when`(studentPassRepository.findAll())
            .thenReturn(listOf(
                expectStudent1,
                expectStudent2,
                nonExpectStudent3,
        ))

        val studentScoreService = StudentScoreService(
            studentScoreRepository,
            studentPassRepository,
            studentFailRepository
        )

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
        val studentScoreRepository = Mockito.mock(StudentScoreRepository::class.java)
        val studentPassRepository = Mockito.mock(StudentPassRepository::class.java)
        val studentFailRepository = Mockito.mock(StudentFailRepository::class.java)

        val givenTestExam = "testexam"
        val expectStudent1 = StudentFail(id = 1, studentName = "jj", avgScore = 40.0, exam = givenTestExam)
        val expectStudent2 = StudentFail(id = 2, studentName = "kk", avgScore = 55.0, exam = givenTestExam)
        val nonExpectStudent3 = StudentFail(id = 3, studentName = "ll", avgScore = 90.0, exam = "secondExam")

        // studentPassRepository.findAll()을 호출하면, thenReturn절을 반환한다
        Mockito
            .`when`(studentFailRepository.findAll())
            .thenReturn(listOf(
                expectStudent1,
                expectStudent2,
                nonExpectStudent3,
            ))

        val studentScoreService = StudentScoreService(
            studentScoreRepository,
            studentPassRepository,
            studentFailRepository
        )

        // when
        val expectResponses = listOf(expectStudent1, expectStudent2)
            .map { es -> ExamFailStudentResponse(es.studentName, es.avgScore) }
            .toList()
        val responses = studentScoreService.getFailStudentsList(givenTestExam)

        // then
        assertThat(responses).isEqualTo(expectResponses)
    }

}