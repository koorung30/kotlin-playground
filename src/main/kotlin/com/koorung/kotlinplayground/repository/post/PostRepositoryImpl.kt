package com.koorung.kotlinplayground.repository.post

import com.koorung.kotlinplayground.domain.post.Post
import com.koorung.kotlinplayground.service.post.PostRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.*

// Business의 interface를 구현하고
// JpaRepository를 주입받아 JPA 기능을 "위임한다"
@Repository
class PostRepositoryImpl (
    private val postJpaRepository: PostJpaRepository    // CrudRepository와 연결된 인터페이스는 주입을 받는 것으로 처리한다.
): PostRepository {
    override fun findById(id: UUID): Post? = postJpaRepository.findByIdOrNull(id)

    override fun findAll(): List<Post> = postJpaRepository.findAll().toList()

    override fun deleteById(id: UUID) {
      postJpaRepository.deleteById(id)
    }

    override fun save(post: Post) {
        postJpaRepository.save(post)
    }
}