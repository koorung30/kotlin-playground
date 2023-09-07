package com.koorung.kotlinplayground.atomickotlin.atom54

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.system.measureNanoTime

class Recursion {

    @Test
    fun `재귀로 팩토리얼 구현`() {
        // 지역확장함수 선언으로 이 테스트에서만 사용가능
        // 일반적인 재귀호출은 콜스택을 많이 쌓기 때문에 성능이 좋지 않다.
        // 또한 StackOverFlowError 이 발생할 수 있다.
        fun factorial(n: Long): Long {
            if (n <= 1L) return 1L    // early return 으로 특정 조건을 만족할 경우 리턴값 명시
            return n * factorial(n - 1)     // 재귀
        }

        // tailrec 키워드를 사용하여 재귀를 최적화 할 수 있다.
        // 단 tailrec 는 함수가 "꼬리호출" 일 경우에만 가능하다.
        // tailrec 함수는 콜스택을 최적화한다..!
        tailrec fun tailrecFactorial(n: Long, accumulator: Long = 1L): Long {
            if (n <= 1L) return accumulator

            // * 꼬리호출이란 "어떤 함수를 호출해서 그 결과를 바로 반환하는 경우" 를 의미한다..!
            // 보통 accumulator 라는 초기값을 파라미터에 같이 넘겨 꼬리호출을 구현한다.
            // 코틀린에서는 디폴트파라미터를 활용하면 더 깔끔하다
            return tailrecFactorial(n - 1, n * accumulator)     // 재귀
        }

        val millis1 = measureNanoTime { assertThat(factorial(15)).isEqualTo(1307674368000L) }
        val millis2 = measureNanoTime { assertThat(tailrecFactorial(15)).isEqualTo(1307674368000L) }

        println("millis1 ::: $millis1 ns") // 22118708 ns
        println("millis2 ::: $millis2 ns") // 6709 ns

        // 꼬리재귀를 사용하는쪽이 압도적으로 빠르다...
    }

    @Test
    fun `재귀로 피보나치 구현`() {
        // 0 1 1 2 3 5 8 ...
        fun fibonacci(n: Long): Long = when (n) {
            0L -> 0L
            1L -> 1L
            else -> fibonacci(n - 1) + fibonacci(n - 2)
        }

        // tailrec 함수는 리턴타입을 명시해줘야 컴파일러가 최적화 할 수 있다.
        tailrec fun tailFibonacci(n: Long, current: Long = 0L, next: Long = 1L): Long = when (n) {
            0L -> current
            else -> tailFibonacci(n - 1, next, current + next)
        }

        // tailrec를 외부함수로 감싸면 디폴트파라미터를 사용하지 않고도 tailrec을 구현하기위해 받은 파라미터에 잘못된 값이 입력되는 것을 방지할 수 있다.
        fun nestFibonacci(n: Long) = tailFibonacci(n, 0L, 1L)
        println("${measureNanoTime { assertThat(fibonacci(30)).isEqualTo(832040L) }}ns")        // 24351416ns
        println("${measureNanoTime { assertThat(tailFibonacci(30)).isEqualTo(832040L) }}ns")    // 19666000ns
        println("${measureNanoTime { assertThat(nestFibonacci(30)).isEqualTo(832040L) }}ns")    // 21471334ns
    }
}