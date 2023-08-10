package com.koorung.kotlinplayground.domain.post

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity
class Post (
    val title: String,
    val content: String,

    @Id
    val id: UUID = UUID.randomUUID()
)