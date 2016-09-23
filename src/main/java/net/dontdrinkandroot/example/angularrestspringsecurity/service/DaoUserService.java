package net.dontdrinkandroot.example.angularrestspringsecurity.service;

import net.dontdrinkandroot.example.angularrestspringsecurity.dao.accesstoken.AccessTokenDao;
import net.dontdrinkandroot.example.angularrestspringsecurity.dao.user.UserDao;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class DaoUserService implements UserService
{
    private UserDao userDao;

    private AccessTokenDao accessTokenDao;

    protected DaoUserService()
    {
        /* Reflection instantiation */
    }

    public DaoUserService(UserDao userDao, AccessTokenDao accessTokenDao)
    {
        this.userDao = userDao;
        this.accessTokenDao = accessTokenDao;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        return this.userDao.loadUserByUsername(username);
    }
}
