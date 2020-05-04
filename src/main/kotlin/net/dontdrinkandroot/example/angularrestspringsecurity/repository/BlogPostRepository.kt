package net.dontdrinkandroot.example.angularrestspringsecurity.repository

import net.dontdrinkandroot.example.angularrestspringsecurity.entity.BlogPost
import net.dontdrinkandroot.example.angularrestspringsecurity.entity.BlogPostListProjection
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.data.rest.core.annotation.RestResource

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@RepositoryRestResource(path = "blog-posts", excerptProjection = BlogPostListProjection::class)
interface BlogPostRepository : JpaRepository<BlogPost, Long> {

    @RestResource(exported = false)
    fun findBySlug(slug: String): BlogPost?

    @RestResource(path = "author", rel = "author")
    fun findByAuthorUsername(@Param("name") username: String, pageable: Pageable): Page<BlogPost>
}
