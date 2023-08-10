package com.koorung.kotlinplayground.domain.book

import java.time.Clock

// VO (Value Object)
// 1. 불변해야 한다
// 2. 항상 유효해야 한다.
data class BookVo(
    val name: String,
    val price: Int
) {
    init {
        assert(name.length > 3)
        assert(name.isNotBlank())
        assert(price >= 0)
    }
}


// After
class User {
    var loginedTime: Long? = null
    fun login(loginedTime: Long) {
        this.loginedTime = loginedTime
    }
}

fun main() {
    User().login(Clock.systemUTC().millis())
}