package com.koorung.kotlinplayground.atomickotlin.atom55

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

interface InterfaceTest {
    // 1. 인터페이스는 동작을 선언하거나
    fun operation()
    fun returnString(str: String): String

    // 2. 변수를 선언할 수 있다.
    val str: String
}

class ImplementTest: InterfaceTest {
    // 인터페이스를 구현할 때 반드시 override 키워드를 붙인다.
    // 구현 클래스에서는 상세 동작을 정의한다.
    override fun operation() {
        println("인터페이스 구현")
    }

    override fun returnString(str: String): String = str.plus(" Implements")

    // 1. 오버라이드한 프로퍼티를 직접 교체
//    override val str = "ImplementTest"

    // 2. 커스텀 게터 사용
    override val str: String
        get() = "ImplementTest"
}

class ImplementTestWithParam(
    override val str: String    // 3. 생성자에서 바로 오버라이드
): InterfaceTest {
    override fun operation() {
    }

    override fun returnString(str: String): String = str.plus(" Implements With Param")
}

// 이넘에서도 인터페이스를 구현할 수 있다.
// 인터페이스를 구현할 때 각각의 이넘 멤버가 인터페이스를 구현해야 한다.
enum class ImplementsEnum: InterfaceTest {
    KOORUNG {
        override fun operation() {
        }

        override fun returnString(str: String): String = str + "KOORUNG"

        override val str: String
            get() = "KOORUNG"
    },

    TEST {
        override fun operation() {
        }

        override fun returnString(str: String): String = str + "TEST"

        override val str: String
            get() = "TEST"
    }
}

// SAM (Single Abstract Method): 단일 추상 메소드
// 코틀린에서는 "fun interface" 라는 SAM 대응 문법이 존재한다.
fun interface NoArg {
    fun f(): Int
}

class ImplNoArg: NoArg {
    override fun f(): Int = 0
}

fun interface OneArg {
    fun f(a: Int): Int
}

class ImplOneArg: OneArg {
    override fun f(a: Int): Int = a
}

fun interface TwoArgs {
    fun f(a: Int, b: Int): Int
}

class ImplTwoArgs: TwoArgs {
    override fun f(a: Int, b: Int): Int = a + b
}

fun useTwoArgs(a: Int, b:Int, twoArgs: TwoArgs) = twoArgs.f(a, b)

class Interface {
    @Test
    fun `코틀린에서 인터페이스를 구현하기 위해서는 콜론을 사용한다`(){
        val implementTest = ImplementTest()
        val implementTestWithParam = ImplementTestWithParam("param")

        assertThat(implementTest.returnString("koorung")).isEqualTo("koorung Implements")
        assertThat(implementTestWithParam.returnString("koorung")).isEqualTo("koorung Implements With Param")
    }

    @Test
    fun `이넘(Enum)도 인터페이스를 구현할 수 있다`(){
        assertThat(ImplementsEnum.KOORUNG.returnString(ImplementsEnum.KOORUNG.str)).isEqualTo("KOORUNGKOORUNG")
        assertThat(ImplementsEnum.TEST.returnString(ImplementsEnum.TEST.str)).isEqualTo("TESTTEST")
    }

    @Test
    fun `코틀린에서의 SAM 변환 문법`() {
        val implNoArg = ImplNoArg() // 클래스는 구현한 값을 리턴한다.
        val noArg = NoArg { 11 }    // 인터페이스는 전달받은 람다를 리턴한다.
        assertThat(implNoArg.f()).isEqualTo(0)
        assertThat(noArg.f()).isEqualTo(11)

        val implOneArg = ImplOneArg()
        val oneArg = OneArg { it + 10 }
        assertThat(implOneArg.f(3)).isEqualTo(3)
        assertThat(oneArg.f(3)).isEqualTo(13)

        val implTwoArgs = ImplTwoArgs()
        val twoArgs = TwoArgs { a: Int, b: Int -> a * b }

        assertThat(implTwoArgs.f(3, 5)).isEqualTo(8)
        assertThat(twoArgs.f(3, 5)).isEqualTo(15)
    }

    @Test
    fun `SAM 인터페이스를 파라미터로 받는 함수에서도 바로 람다로 전달할 수 있다`(){
        assertThat(useTwoArgs(3, 10) { a, b -> a * b + 50 }).isEqualTo(80)
    }
}