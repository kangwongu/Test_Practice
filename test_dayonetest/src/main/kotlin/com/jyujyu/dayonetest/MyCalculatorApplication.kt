package com.jyujyu.dayonetest

fun main() {
    val myCalculator = MyCalculator()

    myCalculator.add(10.0)
    myCalculator.minus(2.0)
    myCalculator.multiply(2.0)
    myCalculator.divide(0.0)

    println(myCalculator.result)
}