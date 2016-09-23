package net.dontdrinkandroot.example.angularrestspringsecurity.service;

import net.dontdrinkandroot.example.angularrestspringsecurity.entity.AccessToken;
import net.dontdrinkandroot.example.angularrestspringsecurity.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public interface UserService extends UserDetailsService
{
    User findUserByAccessToken(String accessToken);

    AccessToken createAccessToken(User user);
}
