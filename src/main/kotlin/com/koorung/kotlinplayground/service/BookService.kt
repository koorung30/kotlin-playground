package com.koorung.kotlinplayground.service

import com.koorung.kotlinplayground.domain.book.Book
import com.koorung.kotlinplayground.repository.BookRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class BookService(
    private val bookRepository: BookRepository
) {

    @Transactional
    fun saveBook(book: Book) {
        val book = bookRepository.save(book)
    }

    fun findBooks() = bookRepository.findAll().toList()
    fun findBookById(id: UUID) = bookRepository.findByIdOrNull(id) ?: throw IllegalArgumentException("존재하지 않는 책입니다.")
}