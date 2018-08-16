package net.dontdrinkandroot.example.angularrestspringsecurity.fixtures;

import com.github.javafaker.Faker;
import net.dontdrinkandroot.example.angularrestspringsecurity.entity.User;
import net.dontdrinkandroot.fixtures.Fixture;
import net.dontdrinkandroot.fixtures.referencerepository.ReferenceRepository;

import javax.persistence.EntityManager;
import java.util.Random;

public class Users implements Fixture
{
    @Override
    public void load(EntityManager entityManager, ReferenceRepository referenceRepository)
    {
        Faker faker = new Faker(new Random(32589730958345L));

        for (int i = 0; i < 50; i++) {
            User user = new User();
            user.setUsername(faker.name().username());
            entityManager.persist(user);

            referenceRepository.store("user-" + i, user);
        }
    }
}
