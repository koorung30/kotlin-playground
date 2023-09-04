package com.koorung.kotlinplayground.atomickotlin.atom46

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal

class CollectionOperation {

    @Test
    fun `코틀린에서 컬렉션을 초기화하는 방법`(){

        // List(size: Int, init: Lambda) : size 크기의 List를 생성한다. 람다로 인덱스를 넘겨준다.
        assertThat(List(5){ it + 1 }).isEqualTo(listOf(1, 2, 3, 4, 5))
        assertThat(List(5){ 'a' + it }).isEqualTo(listOf('a', 'b', 'c', 'd', 'e'))
    }

    @Test
    fun `코틀린에서의 다양한 컬렉션 함수`() {

        val list = List(10){ it }   // 0..9
        val predicate: (Int) -> Boolean = { it % 5 == 0 }

        assertThat(list).isEqualTo((0..9).toList())
        assertThat(list.filter(predicate)).isEqualTo(listOf(0, 5))
        assertThat(list.filterNot(predicate)).isEqualTo(listOf(1, 2, 3, 4, 6, 7, 8, 9))

        assertThat(list.all(predicate)).isFalse()
        assertThat(list.none(predicate)).isFalse()
        assertThat(list.any(predicate)).isTrue()
        assertThat(list.find(predicate)).isEqualTo(0)   // any 와 find는 결과를 찾으면 바로 iteration을 종료한다.
        assertThat(list.count(predicate)).isEqualTo(2)

        assertThrows<NoSuchElementException> { listOf(1, 2, 3).first(predicate) } // predicate를 만족하는 원소가 없으면 NoSuchElementException 예외가 발생한다.
        assertThat(listOf(1, 2, 3).firstOrNull(predicate)).isNull()               // predicate를 만족하는 원소가 없으면 Null을 반환한다.

        assertThrows<NoSuchElementException> { listOf(1, 2, 3).last(predicate) } // predicate를 만족하는 원소가 없으면 NoSuchElementException 예외가 발생한다.
        assertThat(listOf(1, 2, 3).lastOrNull(predicate)).isNull()               // predicate를 만족하는 원소가 없으면 Null을 반환한다.

        assertThat(list.partition(predicate).first).isEqualTo(listOf(0, 5))
        assertThat(list.partition(predicate).second).isEqualTo(listOf(1, 2, 3, 4, 6, 7, 8, 9))
    }

    @Test
    fun `selector을 전달하는 컬렉션함수`(){
        val products = listOf(
            Product("test2", 2.0),
            Product("test1", 5.0)
        )

        // 람다로 selector을 전달하여 합계 구하기 (sumBy -> sumOf 사용 권장)
        val selector: (Product) -> BigDecimal = { it.price.toBigDecimal() }
        assertThat(products.sumOf(selector)).isEqualTo(7.0.toBigDecimal())
        assertThat(products.sortedBy { it.description }).isEqualTo(listOf(Product("test1", 5.0), Product("test2", 2.0)))
    }

    @Test
    fun `take, drop`(){
        // take와 drop (takeLast, dropLast)는 각각 시작지점에서 iteration을 돌면서 조건에 맞는 원소가 나오는 동안 값을 취하거나 제거한다.
        val list = listOf('a', 'a', 'c', 'D', 'E')
        assertThat(list.takeLastWhile { it.isUpperCase() }).isEqualTo(listOf('D', 'E')) // take : 첫 번째 원소를 취함
        assertThat(list.dropLastWhile { it.isUpperCase() }).isEqualTo(listOf('a', 'a', 'c')) // drop : 첫 번째 원소를 제거함
        assertThat(list.takeWhile { it == 'a' }).isEqualTo(listOf('a', 'a'))
        assertThat(list.dropLastWhile { it == 'D' || it == 'E' }).isEqualTo(listOf('a', 'a', 'c'))
    }
}

data class Product(
    val description: String,
    val price: Double = 0.0,
)