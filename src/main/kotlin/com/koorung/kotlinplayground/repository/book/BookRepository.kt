package com.koorung.kotlinplayground.repository.book

import com.koorung.kotlinplayground.domain.book.Book
import org.springframework.data.repository.CrudRepository
import java.util.*

interface BookRepository: CrudRepository<Book, UUID>