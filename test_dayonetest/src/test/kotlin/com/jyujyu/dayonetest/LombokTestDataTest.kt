package com.jyujyu.dayonetest

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class LombokTestDataTest {

    @Test
    fun testDataTest() {
        val testData = TestData("jyujyu")
        
        assertThat(testData.nane).isEqualTo("jyujyu")
    }
}