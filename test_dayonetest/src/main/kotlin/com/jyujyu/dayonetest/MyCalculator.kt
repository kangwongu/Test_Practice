package com.jyujyu.dayonetest

class MyCalculator(
    var result: Double = 0.0
) {

    fun add(number: Double): MyCalculator {
        result += number
        return this
    }

    fun minus(number: Double): MyCalculator {
        result -= number
        return this
    }

    fun multiply(number: Double): MyCalculator {
        result *= number
        return this
    }

    fun divide(number: Double): MyCalculator {
        if (number == 0.0) {
            throw ZeroDivisionException()
        }
        result /= number
        return this
    }

    inner class ZeroDivisionException : RuntimeException()
}