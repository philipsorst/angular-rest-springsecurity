package net.dontdrinkandroot.example.angularrestspringsecurity.repository

import net.dontdrinkandroot.example.angularrestspringsecurity.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@RepositoryRestResource
interface CommentRepository : JpaRepository<Comment, Long>