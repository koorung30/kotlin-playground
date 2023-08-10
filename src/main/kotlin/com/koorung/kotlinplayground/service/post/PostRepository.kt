package com.koorung.kotlinplayground.service.post

import com.koorung.kotlinplayground.domain.post.Post
import java.util.*

// Business 계층의 Repository
interface PostRepository {

    fun findById(id: UUID): Post?
    fun findAll(): List<Post>
    fun deleteById(id: UUID)
    fun save(post: Post)
}