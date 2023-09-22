package com.koorung.kotlinplayground.atomickotlin.atom61

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

interface Shape {
    fun draw(): String
    fun erase(): String
}

class Circle: Shape {
    override fun draw(): String = "Circle.draw"
    override fun erase(): String = "Circle.erase"
}

class Square: Shape {
    override fun draw(): String = "Square.draw"
    override fun erase(): String = "Square.erase"
    fun color() = "Square.color"
}

class Triangle: Shape {
    override fun draw(): String = "Triangle.draw"
    override fun erase(): String = "Triangle.erase"
    fun rotate() = "Triangle.rotate"
}

fun show(shape: Shape) {
    println("Show :: ${shape.draw()}")
}

class UpcastAndDowncast {

    @Test
    fun `업캐스트 테스트`(){

        // 코틀린에서 함수는 더 이상 클래스에 묶여있지 않고 독립적으로 존재할 수 있으며
        // 확장함수를 사용해서 기능을 확장할 수 있기 때문에
        // 코틀린에서 상속은 '업캐스트' 할때 의미를 가진다..!

        val circle = Circle()       // Circle
        val square = Square()       // Square
        val triangle = Triangle()   // Triangle

        // show()의 파라미터로 각 객체가 '업캐스트' 되어 Shape 타입으로 전달됨
        val listOf = listOf(circle, square, triangle) // List<Shape>
        listOf.forEach(::show)
    }

    /**
     * - 다운캐스트
     * 코틀린에서는 is (또는 as) 키워드로 다운캐스트를 지원한다.
     * if() 또는 when()과 조합하여 매우 유용하게 사용할 수 있다..!
     *
     * 스마트캐스트를 사용할경우 대상이 가변(var) 이면 제대로 동작하지 않을 수 있으므로 val을 사용하자.
     */
    @Test
    fun `코틀린에서 is 키워드를 이용하여 다운캐스트를 지원한다`() {
        val circle = Circle()       // Circle
        val square = Square()       // Square
        val triangle = Triangle()   // Triangle

        // 각 객체들이 업캐스트 되어 List<Shape>에 들어가있음
        val listOf = listOf(circle, square, triangle) // List<Shape>

        // is 이용하여 스마트캐스트 (다운캐스트)
        listOf.forEach {
            val predicate = when(it) {
                is Circle -> "원입니다."
                is Square -> "사각형입니다."
                is Triangle -> "삼각형입니다."
                else -> "아무것도 아닙니다."
            }
            println(predicate)
        }

        val foundSquare = listOf.find { it is Square } as? Square       // find로 전달받은 객체를 as? 로 안전하게 캐스팅 (Square?)
        val filterIsInstance = listOf.filterIsInstance<Triangle>() // filterIsInstance로 특정 타입의 객체 리스트를 리턴받음 List<Triangle>
    }

    @Test
    fun `as 키워드로 다운캐스트를 강제 적용할 수 있다`() {
        val circle = Circle()       // Circle
        val square = Square()       // Square
        val triangle = Triangle()   // Triangle

        // 각 객체들이 업캐스트 되어 List<Shape>에 들어가있음
        val listOf = listOf(circle, square, triangle) // List<Shape>

        // 1. 올바른 타입으로 캐스팅할 경우 정상동작
        assertDoesNotThrow {
            val actuallySquare = listOf[1] as Square
            actuallySquare.erase()
            actuallySquare.draw()
            actuallySquare.color()
        }

        // 2. as로 잘못된 캐스팅을 하는 시점에 ClassCastException 이 던져진다. (안전하지 않은 as 캐스팅)
        assertThrows<ClassCastException> {
            val actuallyCircle = listOf[0] as Square
            actuallyCircle.erase()
            actuallyCircle.draw()
            actuallyCircle.color()
        }

        // 3. as?는 잘못된 캐스팅을 해도 예외가 던져지지 않으며 null을 리턴한다. (안전한 as? 캐스팅)
        assertDoesNotThrow {
            val actuallyCircle = listOf[0] as? Square
            actuallyCircle?.erase()
            actuallyCircle?.draw()
            actuallyCircle?.color()
        }
    }
}
