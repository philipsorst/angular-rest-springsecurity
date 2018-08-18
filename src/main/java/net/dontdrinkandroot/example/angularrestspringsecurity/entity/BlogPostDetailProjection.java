package net.dontdrinkandroot.example.angularrestspringsecurity.entity;

import org.springframework.data.rest.core.config.Projection;

import java.util.Collection;

@Projection(name = "detail", types = {BlogPost.class})
public interface BlogPostDetailProjection
{
    String getTitle();

    User getAuthor();

    Long getCreated();

    Long getLastModified();

    String getSlug();

    String getContent();

    Collection<Comment> getComments();
}
