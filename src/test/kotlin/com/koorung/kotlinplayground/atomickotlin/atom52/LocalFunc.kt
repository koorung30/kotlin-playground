package com.koorung.kotlinplayground.atomickotlin.atom52

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LocalFunc {

    @Test
    fun `지역함수는 클로저다`(){
        var localParam = "test"
        // 테스트 안에서 지역함수 선언
        fun localFunc(): String {
            // 함수 바깥의 localParam을 캡쳐함
            localParam += "t"
            return localParam
        }

        assertThat(localFunc()).isEqualTo("testt")
    }

    @Test
    fun `확장지역함수를 선언할 수 있다`(){
        infix fun String.appendParam(param: String): String = plus(param)
        assertThat("koorung" appendParam "test").isEqualTo("koorungtest")
    }

    @Test
    fun `익명지역함수를 선언할 수 있다`(){

        // 0. 변수로 참조한 함수를 전달
        fun interesting(session: Session): Boolean = when(session.title.indexOf("A")) {
            -1 -> false
            else -> true
        }
        assertThat(Session.sessions.any(::interesting)).isFalse()
        assertThat(listOf(Session("AisGood", "test")).any(::interesting)).isTrue()

        // 1. 익명함수를 바로 전달
        assertThat(Session.sessions.any(fun(session: Session): Boolean = when(session.title.indexOf("A")) {
            -1 -> false
            else -> true
        })).isFalse()

        // 2. 람다로 전달
        assertThat(Session.sessions.any { s: Session -> when(s.title.indexOf("A")) {
            -1 -> false
            else -> true
        }}).isFalse()
    }
}

data class Session (
    val title: String,
    val speaker: String,
) {

    companion object {
        val sessions
            get() = listOf(Session("title1", "speaker1"), Session("title2", "speaker2"))
    }
}