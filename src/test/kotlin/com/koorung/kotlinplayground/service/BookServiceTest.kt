package com.koorung.kotlinplayground.service

import com.koorung.kotlinplayground.domain.book.Book
import com.koorung.kotlinplayground.domain.book.BookType
import com.koorung.kotlinplayground.repository.BookRepository
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional
import kotlin.reflect.KClass

@Transactional
@SpringBootTest
class BookServiceTest @Autowired constructor(
    private val bookService: BookService,
    private val bookRepository: BookRepository
) {
    @Test
    fun `책 저장하기`() {
        // given
        val book = Book.fixture("책1", 10_000L)
        val book2 = Book.fixture("책2", 20_000L)

        // when
        bookService.saveBook(book)
        bookService.saveBook(book2)

        // then
        val books = bookRepository.findAll().toList()
        assertThat(books).hasSize(2)
        assertThat(books).extracting("name").containsExactlyInAnyOrder("책1", "책2")
    }
}