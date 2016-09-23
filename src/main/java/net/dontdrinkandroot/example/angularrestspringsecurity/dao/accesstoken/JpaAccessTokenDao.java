package net.dontdrinkandroot.example.angularrestspringsecurity.dao.accesstoken;

import net.dontdrinkandroot.example.angularrestspringsecurity.dao.JpaDao;
import net.dontdrinkandroot.example.angularrestspringsecurity.entity.AccessToken;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class JpaAccessTokenDao extends JpaDao<AccessToken, Long> implements AccessTokenDao
{
    public JpaAccessTokenDao()
    {
        super(AccessToken.class);
    }
}
