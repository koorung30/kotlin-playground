package com.koorung.kotlinplayground.atomickotlin.atom64

import org.junit.jupiter.api.Test

class Heater {
    fun heat(temperature: Int) = "heating to $temperature"
}

fun warm(heater: Heater) {
    println(heater.heat(70))
}

// 확장함수로 Heater에 기능추가
fun Heater.cool(temperature: Int) = "cooling to $temperature"
fun warmAndCool(heater: Heater) {
    println(heater.heat(70))
    println(heater.cool(30))
}

interface UsefulInterface {
    fun usefulFunction1()
}

fun utility1(u: UsefulInterface) {
    u.usefulFunction1()
}

// 기존에 존재하는 MyClassThatExists 클래스에 UsefulInterface 기능을 붙인다고 해보자
open class MyClassThatExists {
    fun existFunction1() {
        println("existFunction1")
    }
}

// 1. Adaptor 클래스를 만들어 두 기능을 상속 or 구현하여 이어붙인다.
class MyClassThatExistsAdaptor: MyClassThatExists(), UsefulInterface {
    override fun usefulFunction1() = existFunction1()
}

// open 되어있지 않다면?
class MyClassThatExists2 {
    fun existFunction1() {
        println("existFunction1")
    }
}

class MyClassThatExistsAdaptor2: UsefulInterface {
    private val exists2 = MyClassThatExists2()  // open 되지 않은 MyClassThatExists2 를 합성으로 가져옴
    override fun usefulFunction1() = exists2.existFunction1()
}

/**
 * 코틀린의 Sequence :: 정말 필수적인 메소드를 멤버로 정의하고, 나머지는 모두 확장함수로 정의했다..!
 * 멤버함수는 그 타입의 (클래스의) 핵심을 반영할 때 즉, 그 타입의 정의에 해당할 때 사용한다.
 * 확장함수는 그 타입의 존재에 필수적이지 않는 기능을 붙일 때 사용한다.
 * 확장함수로 정의한 함수는 '다형성이 동작하지 않기 때문에' 이 함수가 하위클래스에서 오버라이딩 될 가능성이 없을 때 확장함수로 정의한다...
 * (Sequences.kt 참조)
 */
class InheritanceAndExtension {

    @Test
    fun `코틀린은 상속 대신 확장함수로 기능을 확장할 수 있다`() {
        val heater = Heater()
        warmAndCool(heater)
    }

    @Test
    fun `상속을 이용한 어댑터 패턴`() {
        val thatExists = MyClassThatExists()
        val thatExistsAdaptor = MyClassThatExistsAdaptor()

        thatExists.existFunction1()
//        utility1(thatExists)
//        thatExists.usefulFunction1()    // 컴파일에러

        thatExistsAdaptor.existFunction1()
        thatExistsAdaptor.usefulFunction1() // 상속으로 어댑팅하여 UsefulInterface 기능 추가
        utility1(thatExistsAdaptor)

        // 단, MyClassThatExistsAdaptor는 "어댑터 역할을 하는 것" 이외의 의미를 가지지 않는다...
        // ('상속' 이라는 비싼 비용을 치르는 것에 비해 애매..)
    }

    @Test
    fun `합성을 이용한 어댑터 패턴`() {
        val thatExists2 = MyClassThatExists2()
        val thatExistsAdaptor2 = MyClassThatExistsAdaptor2()

        thatExistsAdaptor2.usefulFunction1()    // 합성으로 어댑팅
        utility1(thatExistsAdaptor2)            // UsefulInterface 타입을 받는 함수에도 정상적으로 전달됨
    }

    // 오히려 확장함수는 어댑터패턴을 구현할 때 사용할 수 없다...
    // (확장함수를 모아서 인터페이스를 구현할 수는 없기 때문에)
}