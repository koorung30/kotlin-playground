package com.koorung.kotlinplayground.atomickotlin.atom44

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

// 제네릭타입 T를 전달받아 람다를 실행하고 그 결과를 리턴하는 함수
fun <T> executeAndReturnThis(t: T, l: (T) -> T): T = l(t)

fun dontUseThisParam(a: String, b: String, l: (String, String) -> Unit) {
    l(a, b)
}

/**
 * 람다
 *
 * 이름없이 사용 가능한 함수
 * { 파라미터목록 -> 동작 } 의 형태이며
 * 중괄호 안의 맨 마지막 줄의 값을 리턴한다.
 * 파라미터가 하나일 경우 "파라미터목록 -> " 부분을 생략하고
 * { it.동작 } 의 형태로 작성 가능하다.
 */
class Lambda {

    @Test
    fun `코틀린 - 람다의 사용법`(){
        // 1. 10을 전달받아 println()한 뒤 it을 리턴
        val result1 = executeAndReturnThis(10) {
            println(it)
            it
        }

        // 2. "koorung"을 전달받아 30을 plus한 뒤 리턴
        val result2 = executeAndReturnThis("koorung") { it.plus(30) }

        assertThat(result1).isEqualTo(10)
        assertThat(result2).isEqualTo("koorung30")
    }

    @Test
    fun `람다에서 사용하지 않는 파라미터는 _ 으로 선언한다`() {
        dontUseThisParam("강원도", "원주") { a, _ -> println(a) }
    }

    @Test
    fun `람다를 사용하여 컬렉션 조작`(){
        // map, filter 등을 사용하여 항상 같은 값을 리턴하는 것이 함수형 프로그래밍의 특징
        val list = listOf(1, 2, 3, 4, 5, 6)
        val evenList = list.filter { it % 2 == 0 }
        val gt2List = list.filter { it > 2 }
        assertThat(evenList).isEqualTo(listOf(2, 4, 6))
        assertThat(gt2List).isEqualTo(listOf(3, 4, 5, 6))
    }

    @Test
    fun `람다를 변수에 초기화하여 사용할 수 있다`() {
        val list = listOf(1, 2, 3, 4, 5, 6)
        val list2 = listOf(7, 8, 9, 10, 11, 12)

//        val evenLambda = { number: Int -> number % 2 == 0 }
        val evenLambda: (Int) -> Boolean = { it % 2 == 0 }      // 람다를 초기화하는법 두가지...

        assertThat(list.filter(evenLambda)).isEqualTo(listOf(2, 4, 6))
        assertThat(list2.filter(evenLambda)).isEqualTo(listOf(8, 10, 12))
    }

    @Test
    // Closure : 함수가 자신이 속한 환경의 요소를 포획(capture) 또는 닫아버리는(close up) 것
    // "람다가 클로저일 수 있는 것이지" "람다 == 클로저" 가 아니다..!
    fun `람다는 클로저일 수 있다`() {
        val list = listOf(1, 2, 3, 4, 5, 6)
        val divider = 5

        val sum = list.filter { it % divider == 0 } // 블록 바깥의 divider를 포획하여 사용
            .sum()

        assertThat(sum).isEqualTo(5)
    }
}