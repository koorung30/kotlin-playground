package com.koorung.kotlinplayground.atomickotlin.atom81

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * 제네릭 = 정해지지 않은 타입
 * - 코틀린에서의 제네릭스 활용
 * - 제네릭 함수를 정의할 때는 fun <T> functionName(arg: T): T { ... }
 * - 제네릭 클래스를 정의할 때는 class GClass<T> (val x: T) { ... }
 * - 제네릭 인터페이스를 정의할 때는 interface GInterface<T> (val x: T) { ... }
 */
class Generics {
    @Test
    fun `제네릭 클래스와 확장함수 사용하기`() {
        val crate = Crate<Int>()
        crate.put(0, 1, 2, 3)
        assertThat(crate.map { it.toString() + "0"}).isEqualTo(listOf("00", "10", "20", "30"))
    }
}

// Crate<T>의 확장함수 선언
// 인자로 (T) -> R 람다를 받는다.
// get() 함수로 리턴되는 T를 람다 f가 받고 이 list를 리턴함.
fun <T, R> Crate<T>.map(f: (T) -> R): List<R> = get().map { f(it) }

open class Crate<T>(
    private var contents: MutableList<T> = mutableListOf()
) {
    fun put(vararg item: T) {
        contents.addAll(item)
    }

    fun get(): List<T> = contents
}