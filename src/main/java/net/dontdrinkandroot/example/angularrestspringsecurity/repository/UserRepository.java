package net.dontdrinkandroot.example.angularrestspringsecurity.repository;

import net.dontdrinkandroot.example.angularrestspringsecurity.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RepositoryRestResource()
public interface UserRepository extends PagingAndSortingRepository<User, Long>
{
    @RestResource(exported = false)
    Optional<User> findByUsername(String username);
}
