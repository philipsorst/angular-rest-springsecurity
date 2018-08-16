package net.dontdrinkandroot.example.angularrestspringsecurity.repository;

import net.dontdrinkandroot.example.angularrestspringsecurity.entity.BlogPost;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "blog-posts")
public interface BlogPostRepository extends PagingAndSortingRepository<BlogPost, Long>
{
}
