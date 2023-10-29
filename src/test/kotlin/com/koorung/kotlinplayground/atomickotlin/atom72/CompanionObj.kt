package com.koorung.kotlinplayground.atomickotlin.atom72

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * - 동반객체
 * class 안에 companion object 키워드로 선언한다.
 * 클래스의 원소는 동반객체의 원소에 접근할 수 있으나, 동반객체의 원소는 클래스의 원소에 접근할 수 없다.
 * 동반객체는 클래스당 하나만 허용되며, 동반객체의 원소는 "메모리상에 단 하나만 존재" 하게 된다.
 */

interface Introduce {
    val name: String
    val age: Int
    fun introduce(): String
}

class Student(
    private val id: Int = 0,
) {
    // 동반객체 선언
    // 동반객체는 클래스나 인터페이스를 상속할 수 있다
    companion object: Introduce {
        override val name: String = "koorung"
        override val age: Int = 30
        override fun introduce() = "My name is :: $name and age is :: $age"
    }

    // 동반객체의 원소 참조
    fun info() = "[Student $id] [INFO] :::: ${introduce()}"
}

// 동반객체의 확장함수를 정의
fun Student.Companion.address() = "Seoul"

// 동반객체는 팩토리 메소드 패턴에 주로 활용된다.
class Subject private constructor(
    val id: Long = 0L,
    val name: String = "",
) {
    companion object Factory {
        var init = 0

        init {
            init += 10
        }

        fun create(id: Long, name: String): Subject = Subject(id, name)
    }
}

class CompanionObj {

    @Test
    fun `클래스는 동반객체의 원소를 참조할 수 있다`() {
        val student = Student(200)

        // java의 static과 비슷하게 Companion에 접근한다.
        assertThat(Student.introduce()).isEqualTo("My name is :: koorung and age is :: 30")
        assertThat(Student.address()).isEqualTo("Seoul")
        assertThat(student.info()).isEqualTo("[Student 200] [INFO] :::: My name is :: koorung and age is :: 30")
    }

    @Test
    fun `동반객체는 팩토리메소드 패턴에 주로 활용된다`(){
//        Subject(10L, "koorung")   // 생성자가 private 이므로 컴파일 에러
        val koorung = Subject.create(10L, "koorung")

        assertThat(koorung).extracting("id").isEqualTo(10L)
        assertThat(koorung).extracting("name").isEqualTo("koorung")
    }

    @Test
    fun `동반객체의 생성자는 동반객체를 둘러싼 클래스가 최초로 프로그램에 적재될 때 한번만 호출된다`() {
        // Subject.create(...) 10번 호출
        List(10) { it }.forEach {
            Subject.create(it.toLong(), "Name${it}")
        }

        // 생성자의 init += 10이 단 한번만 호출되는 것 확인
        assertThat(Subject.init).isEqualTo(10)
    }
}