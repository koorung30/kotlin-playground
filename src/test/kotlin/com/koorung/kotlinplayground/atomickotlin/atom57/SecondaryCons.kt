package com.koorung.kotlinplayground.atomickotlin.atom57

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * 코틀린에서는 init 블록까지의 생성자를 주생성자라고 하며,
 * 오버로드한 생성자를 부생성자라고 한다.
 * 부생성자를 만들 때 constructor 키워드를 사용해야 하며, 반드시 주생성자나 다른 부생성자의 파라미터 리스트를 넣어줘야 한다.
 */
class WithSecondary (
    var i: Int = 0,
    var s: String = "",
) {
   init {
       this.s = "$s Init"
       println("i ::: $i s ::: $s")
   } // 여기까지 주생성자

    // 1. 부생성자는 다른 생성자와 시그니쳐가 달라야한다.
    constructor (i: Int, s: String, ss: String): this(i, s) { // 2. this(...) 로 주생성자 또는 다른 부생성자를 가져와야 컴파일된다.
        this.i = i + 10
        this.s = "$s $ss Init"
        println("i ::: $i s ::: $s")
    }   // 부생성자
}

class SecondaryCons {

    @Test
    fun `부생성자 호출 테스트`(){
        val primary = WithSecondary(i = 10, s = "primary cons")
        val secondary = WithSecondary(i = 100, s = "secondary", ss = "cons")

        assertThat(primary.s).isEqualTo("primary cons Init")
        assertThat(primary.i).isEqualTo(10)
        assertThat(secondary.s).isEqualTo("secondary cons Init")
        assertThat(secondary.i).isEqualTo(110)
    }
}