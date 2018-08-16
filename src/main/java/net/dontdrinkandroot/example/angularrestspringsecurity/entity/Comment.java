package net.dontdrinkandroot.example.angularrestspringsecurity.entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

@Entity
public class Comment
{
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    private BlogPost blogPost;

    @ManyToOne(optional = false)
    private User author;

    @Column(nullable = false)
    @Lob
    private String content;

    @Column(nullable = false)
    @CreatedDate
    private Long created;

    public Long getId()
    {
        return this.id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public BlogPost getBlogPost()
    {
        return this.blogPost;
    }

    public void setBlogPost(BlogPost blogPost)
    {
        this.blogPost = blogPost;
    }

    public User getAuthor()
    {
        return this.author;
    }

    public void setAuthor(User author)
    {
        this.author = author;
    }

    public String getContent()
    {
        return this.content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Long getCreated()
    {
        return this.created;
    }

    public void setCreated(Long created)
    {
        this.created = created;
    }
}
