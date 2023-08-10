package com.koorung.kotlinplayground.repository.post

import com.koorung.kotlinplayground.domain.post.Post
import org.springframework.data.repository.CrudRepository
import java.util.*

interface PostJpaRepository: CrudRepository<Post, UUID>