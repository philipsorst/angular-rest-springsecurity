package net.dontdrinkandroot.example.angularrestspringsecurity.dao.newsentry;

import net.dontdrinkandroot.example.angularrestspringsecurity.dao.JpaDao;
import net.dontdrinkandroot.example.angularrestspringsecurity.entity.NewsEntry;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * JPA Implementation of a {@link NewsEntryDao}.
 *
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class JpaNewsEntryDao extends JpaDao<NewsEntry, Long> implements NewsEntryDao
{
    public JpaNewsEntryDao()
    {
        super(NewsEntry.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NewsEntry> findAll()
    {
        final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<NewsEntry> criteriaQuery = builder.createQuery(NewsEntry.class);

        Root<NewsEntry> root = criteriaQuery.from(NewsEntry.class);
        criteriaQuery.orderBy(builder.desc(root.get("date")));

        TypedQuery<NewsEntry> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }
}
