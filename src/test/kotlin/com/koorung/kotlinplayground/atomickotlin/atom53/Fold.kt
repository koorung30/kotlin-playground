package com.koorung.kotlinplayground.atomickotlin.atom53

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

// 코틀린에서 fold() 함수를 통해 리스트를 접을 수 있다!
// 매우 중요한 연산...
class Fold {

    /**
     * fold는 루프를 돌면서
     * 초기값에
     * 전달된 람다를
     * 컬렉션마다 연산한 값을 적용한다.
     */
    @Test
    fun `fold()를 사용하여 컬렉션의 합계 구현`() {
        val list = List(10) { it + 1 }
        assertThat(list.fold(0) {
                sum, n -> sum + n
        }).isEqualTo(55)
    }

    @Test
    fun `fold()를 사용하여 팩토리얼 구현`() {
        fun factorial(n: Int) = List(n) { it + 1 }.fold(1) { accumulator, i ->
            accumulator * i
        }

        assertThat(factorial(5)).isEqualTo(120)

        // foldRight() 는 오른쪽에서 왼쪽으로 돌면서 처리한다.
        fun factorialRight(n: Int) = List(n) { it + 1 }.foldRight(1) { i, accumulator ->
            accumulator * i
        }

        assertThat(factorialRight(5)).isEqualTo(120)
    }

    @Test
    fun `reduce는 컬렉션의 첫번째 원소를 초기값으로 사용한다` () {
        val list = List(5) { ('a' + it).toString() }
        assertThat(list.reduce { accumulator, string -> accumulator + string }).isEqualTo("abcde")

        // 인자 순서를 주의해라 (reduceRight의 경우 오른쪽이 accumulator)
        assertThat(list.reduceRight { string, accumulator -> accumulator + string }).isEqualTo("edcba")
    }

    @Test
    fun `runningFold는 중간값을 포함하는 리스트를 리턴한다` () {
        val list = List(5) { ('a' + it).toString() }
        val runningReduce = list.runningReduce() { accumulator, string -> accumulator + string }
        assertThat(runningReduce).hasSize(5)
        assertThat(runningReduce).containsExactlyInAnyOrder("a", "ab", "abc" ,"abcd", "abcde")
    }
}