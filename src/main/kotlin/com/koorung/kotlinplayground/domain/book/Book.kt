package com.koorung.kotlinplayground.domain.book

import jakarta.persistence.*
import java.util.*

@Entity
class Book(

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val price: Long,

    val author: String?,

    @Enumerated(EnumType.STRING)
    val type: BookType,

    @Id
    val id: UUID? = UUID.randomUUID(),
) {
    companion object {
        fun fixture(
            name: String = "디폴트책",
            price: Long = 0L,
            author: String? = null,
            type: BookType = BookType.COMPUTER
        ) = Book(name, price, author, type)
    }
}

enum class BookType(name: String) {
    SCIENCE("과학"),
    FICTION("문학"),
    NON_FICTION("비문학"),
    EDUCATION("교육"),
    COMPUTER("컴퓨터");
}