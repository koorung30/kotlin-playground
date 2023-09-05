package com.koorung.kotlinplayground.atomickotlin.atom50

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

data class IntToString(
    val number: Int,
    val string: String,
)

class ListControl {

    @Test
    fun `zip() 사용법`(){

        // 컬렉션의 크기가 다를 경우 작은 컬렉션 기준으로 zip이 만들어진다.
        val first = List(10) { it }
        val second = List(5) { ('a' + it).toString() }

        // 두번째 파라미터로 람다를 받는다 (여기서는 생성자 래퍼런스를 사용했다.)
        val zip = first.zip(second, ::IntToString)
        val zip2 = first.zip(second) { f, r -> IntToString(f, r)}

        assertThat(zip).hasSize(5)
        assertThat(zip).extracting("number").containsExactlyInAnyOrder(0, 1, 2, 3, 4)
        assertThat(zip).extracting("string").containsExactlyInAnyOrder("a", "b", "c", "d", "e")

        // IntRange도 사용 가능하다.
        (0..5).zip(10..50) { f, s -> println("first :: $f / second :: $s") }

        // 한 리스트에서 인접한 원소끼리 묶을 때 zipWithNext()를 사용한다.
        (0..10).zipWithNext() { f, s -> println("before :: $f / after :: $s") }
    }

    @Test
    fun `flatten() 사용법`() {
        // 이차원 리스트를 일차원 리스트로 풀어준다.
        val `이차원 리스트` = listOf(
            List(5) { it },
            List(5) { it + 5 },
            List(5) { it + 10}
        )
        assertThat(`이차원 리스트`.flatten()).isEqualTo(List(15){ it })
    }

    @Test
    fun `flatten()과 flatMap()`(){
        // flatMap은 컬렉션에서 매우 중요한 연산이기 때문에 반드시 알아두자..!
        val intRange = 1..3

        // 이미 이차원화 된 컬렉션을 풀어줌
        val flatten = intRange.map { a -> intRange.map { b -> a to b } }.flatten()
        println("flatten ::: $flatten")

        // 이차원 컬렉션을 만드는 부분에 flatMap을 선언하여 미리 풀어줌 (즉 map() + flatten() 과 동일)
        val flatMap = intRange.flatMap { a -> intRange.map { b -> a to b } }
        println("flatMap ::: $flatMap")

        assertThat(flatten).isEqualTo(flatMap)
    }
}