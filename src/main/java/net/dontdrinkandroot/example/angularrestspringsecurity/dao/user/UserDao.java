package net.dontdrinkandroot.example.angularrestspringsecurity.dao.user;

import net.dontdrinkandroot.example.angularrestspringsecurity.dao.Dao;
import net.dontdrinkandroot.example.angularrestspringsecurity.entity.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDao extends Dao<User, Long>
{
    User loadUserByUsername(String username) throws UsernameNotFoundException;

    User findByName(String name);
}
