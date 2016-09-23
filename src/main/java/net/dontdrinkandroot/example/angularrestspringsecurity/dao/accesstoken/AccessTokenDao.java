package net.dontdrinkandroot.example.angularrestspringsecurity.dao.accesstoken;

import net.dontdrinkandroot.example.angularrestspringsecurity.dao.Dao;
import net.dontdrinkandroot.example.angularrestspringsecurity.entity.AccessToken;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public interface AccessTokenDao extends Dao<AccessToken, Long>
{
    AccessToken findByToken(String accessTokenString);
}
