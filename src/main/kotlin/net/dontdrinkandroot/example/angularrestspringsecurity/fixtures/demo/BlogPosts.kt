package net.dontdrinkandroot.example.angularrestspringsecurity.fixtures.demo

import com.github.javafaker.Faker
import net.dontdrinkandroot.example.angularrestspringsecurity.entity.BlogPost
import net.dontdrinkandroot.example.angularrestspringsecurity.entity.Comment
import net.dontdrinkandroot.example.angularrestspringsecurity.entity.User
import net.dontdrinkandroot.fixtures.Fixture
import net.dontdrinkandroot.fixtures.referencerepository.ReferenceRepository
import javax.persistence.EntityManager

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
class BlogPosts : Fixture {

    override fun getDependencies(): Collection<Class<out Fixture>> = listOf(Users::class.java, UserDefault::class.java)

    override fun load(entityManager: EntityManager, referenceRepository: ReferenceRepository) {
        val faker = Faker()
        for (i in 1..100) {
            val author = getRandomUser(referenceRepository, faker)
            val blogPost = BlogPost()
            blogPost.author = author
            blogPost.updated = System.currentTimeMillis() - faker.number().numberBetween(0, 1000L * 60 * 60 * 24 * 365)
            blogPost.created = blogPost.updated!! - faker.number().numberBetween(0, 1000L * 60 * 60 * 24 * 365)
            blogPost.title = faker.lorem().sentence()
            blogPost.slug = "blog-post-$i"
            blogPost.content = faker.lorem().paragraphs(faker.number().numberBetween(1, 11)).joinToString(separator = "\n\n")
            entityManager.persist(blogPost)

            val numComments = faker.number().numberBetween(0, 30)
            for (j in 0..numComments) {
                val comment = Comment()
                comment.author = getRandomUser(referenceRepository, faker)
                comment.blogPost = blogPost
                comment.created = faker.number().numberBetween(blogPost.created!!, System.currentTimeMillis())
                comment.content = faker.lorem().paragraphs(faker.number().numberBetween(1, 4)).joinToString(separator = "\n")
                entityManager.persist(comment)
            }
        }
    }

    private fun getRandomUser(referenceRepository: ReferenceRepository, faker: Faker) =
            referenceRepository.retrieve<User>(Users.referenceName(faker.number().numberBetween(1, 11)))
}
