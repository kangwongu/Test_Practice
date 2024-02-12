package com.jyujyu.dayonetest.repository

import com.jyujyu.dayonetest.model.StudentFail
import org.springframework.data.jpa.repository.JpaRepository

interface StudentFailRepository : JpaRepository<StudentFail, Long> {
}