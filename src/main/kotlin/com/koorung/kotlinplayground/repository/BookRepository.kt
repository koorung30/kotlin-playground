package com.koorung.kotlinplayground.repository

import com.koorung.kotlinplayground.domain.book.Book
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface BookRepository: CrudRepository<Book, UUID>