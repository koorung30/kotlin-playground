package com.koorung.kotlinplayground.atomickotlin.atom56

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class InitTest(
    text: String,
    var age: Int
) {
    private val name: String
    init {
        age += 2            // init 시점에 주 생성자에서 선언된 프로퍼티 age에 2를 더한다.
        name = "init $text" // 바디에 선언된 name 프로퍼티를 초기화한다.
    }

    fun info() = "$name ::: ${age}살"
}

class InitConstructor {

    @Test
    fun `코틀린에서 init 블록으로 객체 초기화 시점의 동작을 정의할 수 있다` () {
        assertThat(InitTest("koorung", 30).info()).isEqualTo("init koorung ::: 32살")
    }
}