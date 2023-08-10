package com.koorung.kotlinplayground.service.post

import com.koorung.kotlinplayground.domain.post.Post
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class PostService (
    // postService는 "동일계층"의 postRepository를 주입받아 사용한다. (DIP 준수)
    // 또한 추후 Repository의 인프라스트럭쳐가 바뀌는 경우에도 postRepository는 순수 기능을 명세한 인터페이스이기 때문에
    // 확장에는 열려있고 수정에는 닫혀있다고 할 수 있다. (OCP 준수)
    private val postRepository: PostRepository
) {
    @Transactional(readOnly = true)
    fun findById(id: UUID) = postRepository.findById(id)

    @Transactional(readOnly = true)
    fun findAll() = postRepository.findAll()

    @Transactional
    fun savePost(post: Post) {
        postRepository.save(post)
    }

    @Transactional
    fun deleteById(id: UUID) {
        postRepository.deleteById(id)
    }
}