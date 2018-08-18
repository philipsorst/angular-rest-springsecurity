package net.dontdrinkandroot.example.angularrestspringsecurity.entity;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "list", types = {BlogPost.class})
public interface BlogPostListProjection
{
    String getTitle();

    User getAuthor();

    Long getCreated();

    String getSlug();
}
