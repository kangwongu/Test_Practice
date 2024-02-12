package com.jyujyu.dayonetest.repository

import com.jyujyu.dayonetest.model.StudentPass
import org.springframework.data.jpa.repository.JpaRepository

interface StudentPassRepository : JpaRepository<StudentPass, Long> {
}