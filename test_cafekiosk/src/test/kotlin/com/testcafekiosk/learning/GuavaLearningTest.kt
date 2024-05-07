package com.testcafekiosk.learning

import com.google.common.collect.ArrayListMultimap
import com.google.common.collect.Lists
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class GuavaLearningTest {

    @Test
    @DisplayName("주어진 개수만큼 List를 파티셔닝한다")
    fun partitionLearningTest1() {
        // given
        val integers = listOf(1, 2, 3, 4, 5, 6)

        // when
        val partition = Lists.partition(integers, 3)

        // then
        assertThat(partition).hasSize(2)
            .isEqualTo(listOf(
                listOf(1,2,3), listOf(4,5,6)
            ))
    }

    @Test
    @DisplayName("주어진 개수만큼 List를 파티셔닝한다")
    fun partitionLearningTest2() {
        // given
        val integers = listOf(1, 2, 3, 4, 5, 6)

        // when
        val partition = Lists.partition(integers, 4)

        // then
        assertThat(partition).hasSize(2)
            .isEqualTo(listOf(
                listOf(1,2,3,4), listOf(5, 6)
            ))
    }

    @Test
    @DisplayName("멀티맵 기능 확인")
    fun multiMapLearningTest1() {
        // given
        val multimap = ArrayListMultimap.create<String, String>()
        multimap.put("커피", "아메리카노")
        multimap.put("커피", "카푸치노")
        multimap.put("커피", "바닐라 라떼")
        multimap.put("베이커리", "크루아상")
        multimap.put("베이커리", "휘낭시에")

        // when
        val strings = multimap.get("커피")

        // then
        assertThat(strings).hasSize(3)
            .isEqualTo(listOf("아메리카노", "카푸치노", "바닐라 라떼"))

    }

    @DisplayName("멀티맵 기능 확인")
    @TestFactory
    fun multiMapLearningTest2(): Collection<DynamicTest> {
        val multimap = ArrayListMultimap.create<String, String>()
        multimap.put("커피", "아메리카노")
        multimap.put("커피", "카푸치노")
        multimap.put("커피", "바닐라 라떼")
        multimap.put("베이커리", "크루아상")
        multimap.put("베이커리", "휘낭시에")

        return listOf(
            DynamicTest.dynamicTest("1개 value 삭제") {
                // when
                multimap.remove("커피", "카푸치노")

                // then
                val result = multimap.get("커피")
                assertThat(result).hasSize(2)
                    .isEqualTo(listOf("아메리카노", "바닐라 라떼"))
            },
            DynamicTest.dynamicTest("1개 key 삭제") {
                // when
                multimap.removeAll("커피")

                // then
                val result = multimap.get("커피")
                assertThat(result).isEmpty()
            }
        )
    }

}