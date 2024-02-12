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
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
class StudentScoreService(
    private val studentScoreRepository: StudentScoreRepository,
    private val studentPassRepository: StudentPassRepository,
    private val studentFailRepository: StudentFailRepository
) {

    fun saveScore(studentName: String, exam: String, korScore: Int, englishScore: Int, mathScore: Int) {
        val studentScore = StudentScore(
            exam = exam,
            studentName = studentName,
            korScore = korScore,
            englishScore = englishScore,
            mathScore = mathScore
        )

        studentScoreRepository.save(studentScore)

        val calculator = MyCalculator()
        val avgScore = calculator
            .add(korScore.toDouble())
            .add(englishScore.toDouble())
            .add(mathScore.toDouble())
            .divide(3.0)
            .result

        if (avgScore >= 60) {
            val studentPass = StudentPass(
                exam = exam,
                studentName = studentName,
                avgScore = avgScore
            )
            studentPassRepository.save(studentPass)
        } else {
            val studentFail = StudentFail(
                exam = exam,
                studentName = studentName,
                avgScore = avgScore
            )
            studentFailRepository.save(studentFail)
        }
    }

    fun getPassStudentsList(exam: String): List<ExamPassStudentResponse> {
        val studentPasses = studentPassRepository.findAll()

        return studentPasses
            .filter { pass -> pass.exam == exam }
            .map { pass -> ExamPassStudentResponse(pass.studentName, pass.avgScore) }
            .toList()
    }

    fun getFailStudentsList(exam: String): List<ExamFailStudentResponse> {
        val studentFails = studentFailRepository.findAll()

        return studentFails
            .filter { fail -> fail.exam == exam }
            .map { fail -> ExamFailStudentResponse(fail.studentName, fail.avgScore) }
            .toList()
    }
}