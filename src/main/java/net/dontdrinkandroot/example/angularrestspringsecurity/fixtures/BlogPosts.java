package net.dontdrinkandroot.example.angularrestspringsecurity.fixtures;

import com.github.javafaker.Faker;
import net.dontdrinkandroot.example.angularrestspringsecurity.entity.BlogPost;
import net.dontdrinkandroot.example.angularrestspringsecurity.entity.Comment;
import net.dontdrinkandroot.fixtures.Fixture;
import net.dontdrinkandroot.fixtures.referencerepository.ReferenceRepository;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BlogPosts implements Fixture
{
    @Override
    public Collection<Class<? extends Fixture>> getDependencies()
    {
        return Arrays.asList(Users.class);
    }

    @Override
    public void load(EntityManager entityManager, ReferenceRepository referenceRepository)
    {
        Faker faker = new Faker(new Random(345823057823534L));

        for (int i = 0; i < 500; i++) {
            BlogPost blogPost = new BlogPost();
            blogPost.setAuthor(referenceRepository.retrieve("user-" + faker.number().numberBetween(0, 50)));
            blogPost.setTitle(faker.lorem().sentence(faker.number().numberBetween(1, 6)));
            blogPost.setSlug("blog-post-" + i);
            blogPost.setContent(StringUtils.join(
                    faker.lorem().paragraphs(faker.number().numberBetween(1, 11)),
                    "\n\n"
            ));

            Date createdDate = faker.date().past(365 * 3, TimeUnit.DAYS);
            blogPost.setCreated(createdDate.getTime());
            blogPost.setLastModified(createdDate.getTime());

            entityManager.persist(blogPost);

            Date lastDate = createdDate;
            Date now = new Date();
            int numComments = faker.number().numberBetween(0, 11);
            for (int j = 0; j < numComments; j++) {
                Comment comment = new Comment();
                comment.setBlogPost(blogPost);
                comment.setAuthor(referenceRepository.retrieve("user-" + faker.number().numberBetween(0, 50)));
                comment.setContent(StringUtils.join(
                        faker.lorem().paragraphs(faker.number().numberBetween(1, 4)),
                        "\n\n"
                ));
                lastDate = faker.date().between(lastDate, now);
                comment.setCreated(lastDate.getTime());

                entityManager.persist(comment);
            }
        }
    }
}
