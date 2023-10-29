package com.koorung.kotlinplayground.atomickotlin.atom75

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import kotlin.Nothing

/**
 * Nothing 타입
 * - 항상 예외를 던지는 타입
 * - 무한루프, 처리되지 않은 exception 등등...
 * - 컴파일러의 정적분석 결과가 예외를 던지지 않을 경우 Nothing 타입을 지정할 수 없다.
 * - Nothing 타입은 "모든타입"의 하위타입이다.
 * -
 */
fun infiniteLoop(): Nothing {
    while(true) {
        println("무한루프")
    }
}

data class ToStr (
    val value: String = "default value"
)

// Nothing 타입은 모든 타입의 하위타입이므로 함수의 리턴값이 String이 되는것이 가능하다.
fun checkObject(obj: Any?): String =
    if (obj is String) obj else throw IllegalArgumentException()

// 안전한 캐스팅 as?와 엘비스 연산자를 이용하여
// String으로 캐스팅 될 수 있을때 변환하고 아닐경우 예외를 던짐
fun checkObject2(obj: Any?): String {
    val cast = obj as? String

    if (cast is String) {
        println("String으로 형변환 가능")
    }
    return (obj as? String) ?: throw IllegalArgumentException()
}

// null은 Nothing? 으로 추론됨
fun nullType() = null

class Nothing {

    @Test
    fun `TODO() 함수는 Nothing 타입이다`(){
        assertThrows<NotImplementedError> {
            TODO("TODO함수 또한 Nothing 타입임")
        }
    }

    @Test
    fun `Nothing 타입은 모든 타입의 하위타입이다`(){
        assertThrows<IllegalArgumentException> { checkObject(ToStr()) }
    }

    @Test
    fun `안전한 캐스팅과 엘비스 연산자를 이용한 기능 구현`() {
        assertDoesNotThrow { checkObject2(ToStr().toString()) }
    }
}
