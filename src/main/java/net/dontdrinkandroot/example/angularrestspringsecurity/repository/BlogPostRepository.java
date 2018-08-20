package net.dontdrinkandroot.example.angularrestspringsecurity.repository;

import net.dontdrinkandroot.example.angularrestspringsecurity.entity.BlogPost;
import net.dontdrinkandroot.example.angularrestspringsecurity.entity.BlogPostListProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RepositoryRestResource(path = "blog-posts", excerptProjection = BlogPostListProjection.class)
public interface BlogPostRepository extends PagingAndSortingRepository<BlogPost, Long>
{
    @RestResource(exported = false)
    Optional<BlogPost> findBySlug(String slug);

    @RestResource(path = "author", rel = "author")
    Page<BlogPost> findByAuthorUsername(@Param("name") String username, Pageable pageable);
}
