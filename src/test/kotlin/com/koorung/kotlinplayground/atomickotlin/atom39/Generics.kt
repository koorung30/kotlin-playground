package com.koorung.kotlinplayground.atomickotlin.atom39

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

// 클래스에서 제네릭은 클래스명 뒤에 선언한다.
data class GenericsTest<T>(
    val t1: T,
    val t2: T? = null
)

// 함수에서 제네릭은 함수명 앞에 <T>와 같이 선언한다.
fun <T, R> genericsFunc(t: T, r: R): Map<T, R> = mapOf(t to r)

class Generics {

    @Test
    fun `제네릭 클래스 테스트`(){
        val strTest = GenericsTest("스트링") // 인자를 보고 컴파일러에서 타입추론
        assertThat(strTest.t1).isEqualTo("스트링")
        assertThat(strTest.t2).isNull()

        val intTest = GenericsTest(10, 3)
        assertThat(intTest.t1).isEqualTo(10)
        assertThat(intTest.t2).isEqualTo(3)
    }

    @Test
    fun `제네릭 함수 테스트`() {
        val func = genericsFunc(10, "10")
        assertThat(func).isEqualTo(mapOf(10 to "10"))
    }
}