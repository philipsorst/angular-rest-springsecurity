package net.dontdrinkandroot.example.angularrestspringsecurity.entity;

import com.fasterxml.jackson.annotation.JsonView;
import net.dontdrinkandroot.example.angularrestspringsecurity.JsonViews;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * JPA Annotated Pojo that represents a blog post.
 *
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@javax.persistence.Entity
public class BlogPost implements Entity
{
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Date date;

    @Column
    private String content;

    public BlogPost()
    {
        this.date = new Date();
    }

    @JsonView(JsonViews.Admin.class)
    public Long getId()
    {
        return this.id;
    }

    @JsonView(JsonViews.User.class)
    public Date getDate()
    {
        return this.date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    @JsonView(JsonViews.User.class)
    public String getContent()
    {
        return this.content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    @Override
    public String toString()
    {
        return String.format("BlogPost[%d, %s]", this.id, this.content);
    }
}
