package com.koorung.kotlinplayground.atomickotlin.atom48

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

fun <T> List<T>.executeLambda(lambda: (T) -> Unit) {
    forEach { lambda(it) }
}

data class Ref(
    val name: String,
    val age: Int,
    val isNull: Boolean,
)

data class UseRef(
    val index: Int,
    val ref: Ref,
)

fun Ref.isKoorung(): Boolean = name == "koorung"

fun returnRefName(ref: Ref) = ref.name

class HigherOrderFunc {

    @Test
    fun `코틀린의 래퍼런스문법`() {
        // 1. 멤버 래퍼런스
        val lists = listOf(Ref("koorung", 30, true), Ref("test", 0, false))
        assertThat(lists.filter(Ref::isNull)).isEqualTo(listOf(Ref("koorung", 30, true)))

        // 2. 함수 래퍼런스
        assertThat(lists.filter(Ref::isKoorung)).isEqualTo(listOf(Ref("koorung", 30, true)))

        // 3. 생성자 래퍼런스
        // 람다에 전달되는 파라미터가 생성자의 시그니쳐와 같은 경우 생성자 래퍼런스를 사용할 수 있다!
        assertThat(lists.mapIndexed(::UseRef)).isEqualTo(
            listOf(UseRef(0, Ref("koorung", 30, true)), UseRef(1, Ref("test", 0, false)))
        )

        // 4. 최상위 함수 래퍼런스
        assertThat(lists.map(::returnRefName)).isEqualTo(listOf("koorung", "test"))
    }

    @Test
    fun `코틀린의 함수타입`(){
        // 함수타입은 함수, 람다에서 사용할 수 있다. (사실 람다는 함수의 일종)
        var funcType: (Int) -> Unit

        funcType = { println(it)}       // 1. 람다로 초기화
        funcType = fun(number: Int) {   // 2. 함수로 초기화
            println(number)
        }
    }

    @Test
    fun `확장함수의 람다 호출`() {
        listOf("a", "B", "c", "D").executeLambda { print(it) }    // aBcD

        // repeat: kotlin의 표준 라이브러리 함수
        kotlin.repeat(5) {
            print("a") // aaaa
        }
    }
}