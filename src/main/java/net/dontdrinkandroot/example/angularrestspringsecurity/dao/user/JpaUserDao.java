package net.dontdrinkandroot.example.angularrestspringsecurity.dao.user;

import net.dontdrinkandroot.example.angularrestspringsecurity.dao.JpaDao;
import net.dontdrinkandroot.example.angularrestspringsecurity.entity.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaUserDao extends JpaDao<User, Long> implements UserDao
{
    public JpaUserDao()
    {
        super(User.class);
    }

    @Override
    @Transactional(readOnly = true)
    public User loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = this.findByName(username);
        if (null == user) {
            throw new UsernameNotFoundException("The user with name " + username + " was not found");
        }

        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public User findByName(String name)
    {
        final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<User> criteriaQuery = builder.createQuery(this.entityClass);

        Root<User> root = criteriaQuery.from(this.entityClass);
        Path<String> namePath = root.get("name");
        criteriaQuery.where(builder.equal(namePath, name));

        TypedQuery<User> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
        List<User> users = typedQuery.getResultList();

        if (users.isEmpty()) {
            return null;
        }

        return users.iterator().next();
    }
}
