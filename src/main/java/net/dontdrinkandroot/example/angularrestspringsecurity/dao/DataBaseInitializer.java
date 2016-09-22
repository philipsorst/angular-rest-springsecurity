package net.dontdrinkandroot.example.angularrestspringsecurity.dao;

import net.dontdrinkandroot.example.angularrestspringsecurity.dao.newsentry.NewsEntryDao;
import net.dontdrinkandroot.example.angularrestspringsecurity.dao.user.UserDao;
import net.dontdrinkandroot.example.angularrestspringsecurity.entity.NewsEntry;
import net.dontdrinkandroot.example.angularrestspringsecurity.entity.Role;
import net.dontdrinkandroot.example.angularrestspringsecurity.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

/**
 * Initialize the database with some test entries.
 *
 * @author Philip W. Sorst <philip@sorst.net>
 */
public class DataBaseInitializer
{
    private NewsEntryDao newsEntryDao;

    private UserDao userDao;

    private PasswordEncoder passwordEncoder;

    protected DataBaseInitializer()
    {
        /* Default constructor for reflection instantiation */
    }

    public DataBaseInitializer(UserDao userDao, NewsEntryDao newsEntryDao, PasswordEncoder passwordEncoder)
    {
        this.userDao = userDao;
        this.newsEntryDao = newsEntryDao;
        this.passwordEncoder = passwordEncoder;
    }

    public void initDataBase()
    {
        User userUser = new User("user", this.passwordEncoder.encode("user"));
        userUser.addRole(Role.USER);
        this.userDao.save(userUser);

        User adminUser = new User("admin", this.passwordEncoder.encode("admin"));
        adminUser.addRole(Role.USER);
        adminUser.addRole(Role.ADMIN);
        this.userDao.save(adminUser);

        long timestamp = System.currentTimeMillis() - (1000 * 60 * 60 * 24);
        for (int i = 0; i < 10; i++) {
            NewsEntry newsEntry = new NewsEntry();
            newsEntry.setContent("This is example content " + i);
            newsEntry.setDate(new Date(timestamp));
            this.newsEntryDao.save(newsEntry);
            timestamp += 1000 * 60 * 60;
        }
    }
}
