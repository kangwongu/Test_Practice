package com.jyujyu.dayonetest

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class MyCalculatorRepeatableTest {

    // 5번 반복
    @RepeatedTest(5)
    fun repeatedAddTest() {
        // given
        val myCalculator = MyCalculator()

        // when
        myCalculator.add(10.0)

        // then
        Assertions.assertThat(myCalculator.result).isEqualTo(10.0)
    }

    // 여러 파라미터를 넣어서 테스트
    @DisplayName("덧셈을 5번 파라미터를 넣어주며 반복하며 테스트")
    @ParameterizedTest
    @MethodSource("parameterizedTestParameter")
    fun parameterizedTest(addValue: Double, expectValue: Double) {
        // given
        val myCalculator = MyCalculator()

        // when
        myCalculator.add(addValue)

        // then
        Assertions.assertThat(myCalculator.result).isEqualTo(expectValue)
    }

    companion object {
        @JvmStatic
        fun parameterizedTestParameter(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(10.0, 10.0),
                Arguments.of(8.0, 8.0),
                Arguments.of(4.0, 4.0),
                Arguments.of(15.0, 15.0),
                Arguments.of(99.0, 99.0),
            )
        }
    }

}