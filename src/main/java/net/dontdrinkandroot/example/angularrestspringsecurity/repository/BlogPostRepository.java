package net.dontdrinkandroot.example.angularrestspringsecurity.repository;

import net.dontdrinkandroot.example.angularrestspringsecurity.entity.BlogPost;
import net.dontdrinkandroot.example.angularrestspringsecurity.entity.BlogPostListProjection;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(path = "blog-posts", excerptProjection = BlogPostListProjection.class)
public interface BlogPostRepository extends PagingAndSortingRepository<BlogPost, Long>
{
    Optional<BlogPost> findBySlug(String slug);

    List<BlogPost> findByAuthorUsername(String username);
}
