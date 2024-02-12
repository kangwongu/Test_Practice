package com.jyujyu.dayonetest.repository

import com.jyujyu.dayonetest.model.StudentScore
import org.springframework.data.jpa.repository.JpaRepository

interface StudentScoreRepository : JpaRepository<StudentScore, Long> {
}