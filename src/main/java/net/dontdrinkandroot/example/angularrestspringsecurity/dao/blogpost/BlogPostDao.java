package net.dontdrinkandroot.example.angularrestspringsecurity.dao.blogpost;

import net.dontdrinkandroot.example.angularrestspringsecurity.dao.Dao;
import net.dontdrinkandroot.example.angularrestspringsecurity.entity.BlogPost;

/**
 * Definition of a Data Access Object that can perform CRUD Operations for {@link BlogPost}s.
 *
 * @author Philip W. Sorst <philip@sorst.net>
 */
public interface BlogPostDao extends Dao<BlogPost, Long>
{
}
