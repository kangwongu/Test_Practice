package com.jyujyu.dayonetest.controller.request

class SaveExamScoreRequest(
    val studentName: String,
    val korScore: Int,
    val englishScore: Int,
    val mathScore: Int
)