package com.jyujyu.dayonetest

import com.jyujyu.dayonetest.model.StudentScoreFixture
import com.jyujyu.dayonetest.repository.StudentScoreRepository
import jakarta.persistence.EntityManager
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

class DayonetestApplicationTest @Autowired constructor(
    private val studentScoreRepository: StudentScoreRepository,
    private val entityManager: EntityManager,
): IntegrationTest() {



    @Test
    fun contextxLoads() {
        val studentScore = StudentScoreFixture.passed()
        val save = studentScoreRepository.save(studentScore)

        entityManager.flush()
        entityManager.clear()

        val queryStudentScore = studentScoreRepository.findByIdOrNull(save.id) ?: throw IllegalArgumentException()

        Assertions.assertThat(queryStudentScore.id).isEqualTo(save.id)
        Assertions.assertThat(queryStudentScore.studentName).isEqualTo(save.studentName)
        Assertions.assertThat(queryStudentScore.exam).isEqualTo(save.exam)
        Assertions.assertThat(queryStudentScore.korScore).isEqualTo(save.korScore)
        Assertions.assertThat(queryStudentScore.englishScore).isEqualTo(save.englishScore)
        Assertions.assertThat(queryStudentScore.mathScore).isEqualTo(save.mathScore)
    }

}