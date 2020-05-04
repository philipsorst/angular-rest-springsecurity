package net.dontdrinkandroot.example.angularrestspringsecurity.entity

import org.springframework.data.rest.core.config.Projection

@Projection(name = "list", types = [BlogPost::class])
interface BlogPostListProjection {
    val title: String
    val author: User
    val created: Long
    val slug: String
}

@Projection(name = "detail", types = [BlogPost::class])
interface BlogPostDetailProjection {
    val title: String
    val author: User
    val created: Long
    val updated: Long
    val slug: String
    val content: String
    val comments: Collection<Comment>
}
