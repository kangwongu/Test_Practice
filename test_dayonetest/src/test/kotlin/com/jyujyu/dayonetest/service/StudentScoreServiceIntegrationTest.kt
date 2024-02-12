package com.jyujyu.dayonetest.service

import com.jyujyu.dayonetest.IntegrationTest
import com.jyujyu.dayonetest.MyCalculator
import com.jyujyu.dayonetest.model.StudentScoreFixture
import jakarta.persistence.EntityManager
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import kotlin.time.toDuration

class StudentScoreServiceIntegrationTest @Autowired constructor(
    private val studentScoreService: StudentScoreService,
    private val entityManager: EntityManager,
) : IntegrationTest() {

    @Test
    @DisplayName("평균 60점 이상인 학생들은 StudentPass에 저장된다")
    fun savePassedStudentScoreTest() {
        // given
        val studentScore = StudentScoreFixture.passed()

        // when
        studentScoreService.saveScore(
            studentScore.studentName,
            studentScore.exam,
            studentScore.korScore,
            studentScore.englishScore,
            studentScore.mathScore
        )

        // then
        val passResponse = studentScoreService.getPassStudentsList(studentScore.exam)

        assertThat(passResponse).hasSize(1)
        assertThat(passResponse[0].studentName).isEqualTo(studentScore.studentName)
        assertThat(passResponse[0].avgScore).isEqualTo(
            MyCalculator()
                .add(studentScore.korScore.toDouble())
                .add(studentScore.englishScore.toDouble())
                .add(studentScore.mathScore.toDouble())
                .divide(3.0)
                .result
        )
    }

    @Test
    @DisplayName("평균 60점 미만인 학생들은 StudentFail에 저장된다")
    fun saveFailedStudentScoreTest() {
        // given
        val studentScore = StudentScoreFixture.failed()

        // when
        studentScoreService.saveScore(
            studentScore.studentName,
            studentScore.exam,
            studentScore.korScore,
            studentScore.englishScore,
            studentScore.mathScore
        )

        // then
        val passResponse = studentScoreService.getFailStudentsList(studentScore.exam)

        assertThat(passResponse).hasSize(1)
        assertThat(passResponse[0].studentName).isEqualTo(studentScore.studentName)
        assertThat(passResponse[0].avgScore).isEqualTo(
            MyCalculator()
                .add(studentScore.korScore.toDouble())
                .add(studentScore.englishScore.toDouble())
                .add(studentScore.mathScore.toDouble())
                .divide(3.0)
                .result
        )
    }
}