package net.dontdrinkandroot.example.angularrestspringsecurity.config;

import net.dontdrinkandroot.example.angularrestspringsecurity.entity.BlogPost;
import net.dontdrinkandroot.example.angularrestspringsecurity.entity.User;
import net.dontdrinkandroot.example.angularrestspringsecurity.repository.BlogPostRepository;
import net.dontdrinkandroot.example.angularrestspringsecurity.repository.UserRepository;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;

@Component
public class RestConfiguration extends RepositoryRestConfigurerAdapter
{
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config)
    {
        config.withEntityLookup()
                .forRepository(UserRepository.class, User::getUsername, UserRepository::findByUsername)
                .forRepository(BlogPostRepository.class, BlogPost::getSlug, BlogPostRepository::findBySlug);
    }
}
