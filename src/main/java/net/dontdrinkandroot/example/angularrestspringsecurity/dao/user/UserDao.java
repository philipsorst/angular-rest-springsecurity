package net.dontdrinkandroot.example.angularrestspringsecurity.dao.user;

import net.dontdrinkandroot.example.angularrestspringsecurity.dao.Dao;
import net.dontdrinkandroot.example.angularrestspringsecurity.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserDao extends Dao<User, Long>, UserDetailsService
{
    User findByName(String name);
}
