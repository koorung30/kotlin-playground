package com.koorung.kotlinplayground.atomickotlin.atom50

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

data class Subject(
    val name: String,
    val difficulty: Double = 0.0
)

class MapControl {

    @Test
    fun `groupBy()`() {
        val names = listOf("국어", "영어", "수학", "사회", "과학")
        val difficulties = listOf(0.0, 3.5, 0.0, 2.0, 1.2)

        val subjects = names.zip(difficulties, ::Subject)

        // selector으로 프로퍼티 래퍼런스 사용
        val groupBy = subjects.groupBy(Subject::difficulty)
        assertThat(groupBy).hasSize(4)
        assertThat(groupBy[0.0]).hasSize(2)
        assertThat(groupBy[0.0]).isEqualTo(listOf(Subject("국어"), Subject("수학")))

        // predicate 를 기준으로 두 그룹(true, false) 으로 나눌때는 partition이 더 낫다.
        val (hard, easy) = subjects.partition { it.difficulty >= 2.0 }
        assertThat(hard).hasSize(2)
        assertThat(easy).hasSize(3)

        // associateWith(), associateBy()로 키나 벨류를 조작하여 맵을 만들 수 있다.
        val valueSelector: (Subject) -> Boolean = { it.difficulty >= 3.0 }
        println(subjects.associateWith(valueSelector))

        // associateBy() 는 람다로 keySelector을 전달받으며 유일한 값을 선택하지 않을경우 값이 누락므로 주의한다.
        val keySelector: (Subject) -> String = { it.name }
        println(subjects.associateBy(keySelector))
    }
}