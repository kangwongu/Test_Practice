package com.jyujyu.dayonetest.controller

import com.jyujyu.dayonetest.controller.request.SaveExamScoreRequest
import com.jyujyu.dayonetest.controller.response.ExamFailStudentResponse
import com.jyujyu.dayonetest.controller.response.ExamPassStudentResponse
import com.jyujyu.dayonetest.service.StudentScoreService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ScoreApi(
    private val studentScoreService: StudentScoreService,
) {

    @PutMapping("/exam/{exam}/score")
    fun save(@PathVariable("exam") exam: String, @RequestBody request: SaveExamScoreRequest) {
        studentScoreService.saveScore(
            request.studentName,
            exam,
            request.korScore,
            request.englishScore,
            request.mathScore)
    }

    @GetMapping("/exam/{exam}/pass")
    fun pass(@PathVariable("exam") exam: String): List<ExamPassStudentResponse> {
        return studentScoreService.getPassStudentsList(exam)
    }

    @GetMapping("/exam/{exam}/fail")
    fun fail(@PathVariable("exam") exam: String): List<ExamFailStudentResponse> {
        return studentScoreService.getFailStudentsList(exam)
    }

}