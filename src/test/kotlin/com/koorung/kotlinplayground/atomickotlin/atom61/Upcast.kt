package com.koorung.kotlinplayground.atomickotlin.atom61

import org.junit.jupiter.api.Test


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

class Upcast {

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
}
