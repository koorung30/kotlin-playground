package com.koorung.kotlinplayground.atomickotlin.atom71

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class Hotel (
    private val reception: String,
) {
    // 1. open 내부 클래스 Room
    open inner class Room (
        val id: Int = 0,
        val reception: String = "default"
    ) {
        // 외부의 private val reception을 참조할 수 있다.
        fun callReception() = "Room $id Calling $reception"

        // 코틀린에서 한정된 this를 사용할 수 있다
        fun callThis() = "Hotel this :: ${this@Hotel.reception}, Room this :: ${this@Room.reception}"
    }

    // 2. private 내부 클래스 Closet
    private inner class Closet: Room()

    // 3. (외부 클래스)Hotel의 멤버함수
    fun closet(): Room = Closet()
}
/**
 * 내부 클래스
 * 내부 클래스의 객체는 자신을 둘러싼 클래스의 인스턴스에 대한 참조를 유지한다.
 */
class InnerClass {

    @Test
    fun `내부 클래스의 인스턴스를 생성하려면 내부 클래스를 둘러싼 클래스의 인스턴스가 필요하다`(){
        val hotel = Hotel("koorung")
        val room = hotel.Room(300)
//        val closet = hotel.Closet() // 컴파일 오류
        val closet = hotel.closet()

        assertThat(room.callReception()).isEqualTo("Room 300 Calling koorung")
    }

    @Test
    fun `코틀린에서 한정된 this를 사용할 수 있다`(){
        val hotel = Hotel("koorung")
        val room = hotel.Room(300)
        assertThat(room.callThis()).isEqualTo("Hotel this :: koorung, Room this :: default")
    }
}