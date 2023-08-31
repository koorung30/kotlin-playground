package com.koorung.kotlinplayground.atomickotlin.atom34

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * enum class는 코드 가독성을 높여주므로 자주 사용하자!
 */
enum class Level {
    OVERFLOW,
    HIGH,
    MEDIUM,
    LOW,
    EMPTY
}

/**
 * enum class는 멤버함수, 프로퍼티를 정의할 수 있다. (인스턴스의 개수가 정해져있다는 것 말고는 class와 동일)
 */
enum class LevelWithMember(
    val order: Int
){
    OVERFLOW(1),
    HIGH(2),
    MEDIUM(3),
    LOW(4),
    EMPTY(5);   // 추가 멤버를 선언할 때는 마지막 enum 뒤에 세미콜론을 붙여 구분짓는다.

    fun withPlusTen() = order + 10
}

fun transferLevel(level: Level) =
    when (level) {
        Level.OVERFLOW -> "오버플로우"
        Level.HIGH -> "하이"
        Level.MEDIUM -> "미디움"
        Level.LOW -> "로우"
        else -> "빈값"
    }

class KotlinEnum {
    @Test
    fun `ENUM은 이름에 해당하는 문자열을 돌려주는 toString()이 생성된다`() {
        // ENUM은 이름에 해당하는 문자열을 돌려주는 toString() 이 생성된다.
        assertThat(Level.HIGH).asString().isEqualTo("HIGH")
    }

    @Test
    fun `enumValues()은 ENUM의 values()과 같다`() {

        val enums = Level.values()
        assertThat(enums).hasSize(5)
        assertThat(enums).isEqualTo(enumValues<Level>())
    }

    @Test
    fun `when식을 이용하여 값을 리턴하는 함수를 선언할 수 있다`(){
        assertThat(transferLevel(Level.EMPTY)).isEqualTo("빈값")
        assertThat(transferLevel(Level.LOW)).isEqualTo("로우")
    }

    @Test
    fun `enum은 멤버함수와 프로퍼티를 가질 수 있다`() {
        val high = LevelWithMember.HIGH
        assertThat(high.order).isEqualTo(2)
        assertThat(high.withPlusTen()).isEqualTo(12)
    }
}
