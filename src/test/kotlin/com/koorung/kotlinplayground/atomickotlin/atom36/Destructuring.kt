package com.koorung.kotlinplayground.atomickotlin.atom36

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Destructuring {

    @Test
    fun `코틀린에서 기본으로 제공하는 튜플`() {
        val pair = "koorung" to 30
        val triple = Triple("koorung", 30, "sangbong")

        val (pname, page) = pair
        assertThat(pname).isEqualTo("koorung")
        assertThat(page).isEqualTo(30)

        val (tname, tage, taddress) = triple
        assertThat(tname).isEqualTo("koorung")
        assertThat(tage).isEqualTo(30)
        assertThat(taddress).isEqualTo("sangbong")
    }

    @Test
    fun `Pair 객체를 이용하면 Map을 빠르게 만들 수 있다`() {
        val mapOf = mapOf("koorung" to 30, "koorung2" to 32, "koorung3" to 33)

        for((name, age) in mapOf) {
            println("$name ::: $age 살입니다")
        }

        assertThat(mapOf).hasSize(3)
        assertThat(mapOf).extractingByKey("koorung2").isNotNull()
        assertThat(mapOf).extractingByKey("koorung4").isNull()
    }

    @Test
    fun `data class는 기본적으로 구조분해를 지원한다`() {
        val book = Book(
            name = "개구리",
            price = 30_000,
            author = "모옌",
            used = true,
        )

        // 구조분해에서 _ 를 사용하면 값을 가져오지 않을 수 있다.
        val (name, _, _, author) = book

        assertThat(name).isEqualTo("개구리")
        assertThat(author).isEqualTo("모옌")
    }

    @Test
    fun `코틀린에서 기본적으로 제공하는 구조분해 함수`() {
        val listOf = listOf(
            "koorung" to 30,
            "koorung2" to 31,
            "koorung3" to 32,
        )

        for((index, value) in listOf.withIndex()) {
            println("$index 번째 :::: $value")
        }

        assertThat(listOf.withIndex()).hasSize(3)
    }
}

// data class는 구조분해를 지원한다.
data class Book(
    val name: String,
    val price: Int,
    val used: Boolean,
    val author: String
)