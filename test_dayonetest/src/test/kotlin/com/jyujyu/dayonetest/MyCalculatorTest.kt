package com.jyujyu.dayonetest

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DisplayNameGeneration
import org.junit.jupiter.api.DisplayNameGenerator

// 언더바를 공백으로
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores::class)
class MyCalculatorTest {

    @Test
    fun add_test() {
        // given
        val myCalculator = MyCalculator()

        // when
        myCalculator.add(10.0)

        // then
        assertThat(myCalculator.result).isEqualTo(10.0)
    }

    @Test
    @DisplayName("뺄셈 테스트")
    fun minus() {

    }

    @Test
    fun multiply() {
    }

    @Test
    fun divide() {
    }
}