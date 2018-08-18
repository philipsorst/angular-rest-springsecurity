package net.dontdrinkandroot.example.angularrestspringsecurity.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class BlogPost
{
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(nullable = false)
    @Lob
    private String content;

    @ManyToOne(optional = false)
    private User author;

    @Column(nullable = false)
    @CreatedDate
    private Long created;

    @Column(nullable = false)
    @LastModifiedDate
    private Long lastModified;

    @OneToMany(mappedBy = "blogPost")
    private Collection<Comment> comments;

    public Long getId()
    {
        return this.id;
    }

    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getSlug()
    {
        return this.slug;
    }

    public void setSlug(String slug)
    {
        this.slug = slug;
    }

    public String getContent()
    {
        return this.content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public User getAuthor()
    {
        return this.author;
    }

    public void setAuthor(User author)
    {
        this.author = author;
    }

    public Long getCreated()
    {
        return this.created;
    }

    public void setCreated(Long created)
    {
        this.created = created;
    }

    public Long getLastModified()
    {
        return this.lastModified;
    }

    public void setLastModified(Long lastModified)
    {
        this.lastModified = lastModified;
    }

    public Collection<Comment> getComments()
    {
        return this.comments;
    }
}
