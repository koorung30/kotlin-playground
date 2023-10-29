package com.koorung.kotlinplayground.atomickotlin.atom70

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Object
 * 클래스와 비슷하게 동작하나
 * 1. 생성자로 인스턴스를 생성할 수 없다.
 * 2. 유일하게 존재하는 인스턴스이다. (싱글톤이다)
 */
object Owner {
//    val name = "koorung"
    private const val NAME = "koorung"  // IDE에서는 const val로 선언하는 것을 추천한다..
    var age = 30

    fun greeting() = "My name is $NAME, age = $age"
}

interface Fish {
    fun swim()
}
object GoldFish: Fish {
    override fun swim() {
        println("${GoldFish::class.simpleName} is swimming!")
    }
}

class Object {

    @Test
    fun `object 테스트`(){

        // Owner 으로 바로 접근한다.
        assertThat(Owner.greeting()).isEqualTo("My name is koorung, age = 30")
        Owner.age += 1
        assertThat(Owner.greeting()).isEqualTo("My name is koorung, age = 31")

        // Final 객체이며, 생성자가 존재하지 않는다.
        val reflection = Owner::class
        assertThat(reflection.isFinal).isTrue()
        assertThat(reflection.constructors).isEmpty()

    }

    @Test
    fun `object는 클래스나 인터페이스를 상속할 수 있다`() {
        GoldFish.swim() // GoldFish is swimming!
    }
}