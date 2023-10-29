package com.koorung.kotlinplayground.atomickotlin.atom81

import org.junit.jupiter.api.Test


/**
 * 제네릭 타입변성
 * 1. 무공변(invarient) : 두 타입간에 아무런 상하관계가 없음
 * 2. 공변(covarient) : 제네릭의 업캐스트 방향이 동일한 경우
 * 3. 반공변(contravarient) : 제네릭의 업캐스트 방향이 반대인 경우
 *
 * 코틀린에서는 in, out 키워드를 이용하여 타입변성을 쉽게 구현할 수 있다.
 */
class Varients {

    @Test
    fun `타입변성을 제한하지 않을 경우 (무공변)`() {
        val cat = Cat()
        val dog = Dog()

        var catBox: Box<Cat> = Box<Cat>(cat)
//        val petBox: Box<Pet> = catBox           // 컴파일에러
                                                  // 코틀린이 이것을 허용하면 catBox로 초기화한 petBox에 dog를 넣는것이 가능해짐
//        petBox.put(dog)

        var petBox: Box<Pet> = Box()
//        catBox = petBox                         // 컴파일에러, catBox를 petBox로 대치할 수 없다. (역방향 업캐스팅 불가능)
//        petBox.put(cat)
//        petBox.put(dog)
    }

    @Test
    fun `out 키워드로 제한하여 put을 막는다면 정방향 업캐스트가 허용되므로 대치가 가능해진다 (공변)`() {
        val cat = Cat()
        val dog = Dog()

        val catOutBox = OutBox<Cat>(cat)
        // put()을 막았기 때문에 '고양이스러움' 유지 가능한 petOutBox
        val petOutBox: OutBox<Pet> = catOutBox   // out 키워드로 put()을 막기 때문에 타입안정성이 보장되므로 컴파일에러 X
    }

    @Test
    fun `in 키워드로 제한하여 get을 막는다면 역방향 업캐스트가 허용되므로 대입이 가능해진다 (반공변)`() {
        val cat = Cat()
        val dog = Dog()

        val petInBox: InBox<Pet> = InBox()

        // 하위타입 제네릭으로 대치하는것이 가능해진다.
        var dogInBox: InBox<Dog> = petInBox
        var catInBox: InBox<Cat> = petInBox


        // petInBox에 하위타입인 cat, dog를 넣는 것이 가능해진다..!
        petInBox.put(cat)
        petInBox.put(dog)

        // dogInBox에는 dog만 들어가는 것이 보장된다.
        dogInBox.put(dog)
//        dogInBox.put(cat)
    }

    @Test
    fun `코틀린의 List는 공변이지만 MutableList는 add() 기능이 추가되므로 무공변이다`(){
        val catList = listOf<Cat>()
        val dogList = listOf<Dog>()

        val petList: List<Pet> = catList    // List<Pet> 은 List<Cat>의 상위 타입이다. (공변)


        val catMutableList = mutableListOf<Cat>()
//        val petMutableList: MutableList<Pet> = catMutableList // 제네릭 타입간의 관련성이 없기 때문에 (무공변) 대치불가

    }

    interface Parent
    interface Child: Parent
    interface X {
        fun f(): Parent
    }

    interface Y: X {
        // 함수는 공변적이므로 오버라이드하는 함수가 더 구체적인 값을 리턴하는것이 가능함!
        override fun f(): Child = f()
    }

    @Test
    fun `함수는 공변적인 반환타입을 가진다`() {

    }
}

class Box<T> (
    private var contents: T? = null
) {
    fun put(item: T) {
        contents = item
    }

    fun get(): T? = contents
}

class InBox<in T> (
    private var contents: T? = null
) {
    fun put(item: T) {
        contents = item
    }

    // T를 안으로 집어넣을 수는 있지만, T를 리턴할 수는 없다.
//    fun get(): T = contents
}

class OutBox<out T> (
    private var contents : T? = null
) {
    // T를 리턴할 수는 있지만, T를 집어넣을 수 없다.
//    fun put(item: T) {
//        contents = item
//    }

    fun get(): T? = contents
}


open class Pet
class Cat: Pet()
class Dog: Pet()