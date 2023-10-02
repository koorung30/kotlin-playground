package com.koorung.kotlinplayground.atomickotlin.atom73

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

/**
 * 예외처리
 * 동시성 프로그래밍이 확대되면서 예외처리의 중요성이 늘어남 (오류가 발생해도 계속 동작해야한다..!)
 * 예외는 던져진 곳으로부터 콜스택을 따라 적절한 핸들러가 예외를 처리될 때 까지 bubble up 한다.
 * (예외가 처리되지 않으면 콘솔에 printStackTrace())
 *
 */

// Exception(message) 상속
class Exception1(
    val value: Int
): Exception("wrong value :: $value")

// Exception(description) 상속
open class Exception2(
    description: String
): Exception(description)

// Exception2 상속
class Exception3(
    description: String
): Exception2(description)

// 인자에 따라 다른 exception을 던지는 함수
// 1, 2, 3이 오는 경우 Exception을 던지고 아닐 경우 "OK" 라는 String을 던진다
// 따라서 리턴값이 String이 된다...!
fun toss(which: Int) = when(which) {
    1 -> throw Exception1(1)
    2 -> throw Exception2("Exception 2")
    3 -> throw Exception3("Exception 3")
    else -> "OK"
}

fun test(which: Int): Any? =
    try { toss(which) }                 // (String)
    catch (e: Exception1) { e.value }   // Exception1의 value 라는 property (Int)
    catch (e: Exception2) { "Handled at Exception2 ::: ${e.message}" } // Exception을 상속하여 접근가능한 message property (String?)
    catch (e: Exception3) { "Handled at Exception3 ::: ${e.message}" }

class ExceptionHandling {

    @Test
    fun `코틀린의 exception 처리 테스트`() {
        assertThat(test(1)).isEqualTo(1)
        assertThat(test(2)).isEqualTo("Handled at Exception2 ::: Exception 2")
        assertThat(test(3)).isEqualTo("Handled at Exception2 ::: Exception 3")  // Exception2에서 처리되는 것 확인 (하위 타입이므로)
//        assertThat(test(3)).isEqualTo("Handled at Exception3 ::: Exception 3")
        assertThat(test(4)).isEqualTo("OK")
    }

    @Test
    fun `코틀린의 exception 처리 테스트2`() {

        assertThrows<Exception1> {
            val toss = toss(1)
        }

        assertThrows<Exception2> {
            val toss = toss(2)
        }

        // Exception3은 Exception2를 상속했기 때문에 처리 가능
        assertThrows<Exception2> {
            val toss = toss(3)
        }

        assertThrows<Exception3> {
            val toss = toss(3)
        }

        assertDoesNotThrow {
            val toss = toss(4)
            assertThat(toss).isEqualTo("OK")
        }
    }
}