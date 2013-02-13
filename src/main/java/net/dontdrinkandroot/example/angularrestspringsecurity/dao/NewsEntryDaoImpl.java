package net.dontdrinkandroot.example.angularrestspringsecurity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import net.dontdrinkandroot.example.angularrestspringsecurity.entity.NewsEntry;

import org.springframework.transaction.annotation.Transactional;


public class NewsEntryDaoImpl implements NewsEntryDao {

	private EntityManager entityManager;


	public EntityManager getEntityManager() {

		return this.entityManager;
	}


	@PersistenceContext
	public void setEntityManager(final EntityManager entityManager) {

		this.entityManager = entityManager;
	}


	@Override
	@Transactional(readOnly = true)
	public List<NewsEntry> findAll() {

		final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<NewsEntry> criteriaQuery = builder.createQuery(NewsEntry.class);
		criteriaQuery.from(NewsEntry.class);

		TypedQuery<NewsEntry> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}


	@Override
	@Transactional(readOnly = true)
	public NewsEntry find(Long id) {

		return this.getEntityManager().find(NewsEntry.class, id);
	}


	@Override
	@Transactional
	public NewsEntry save(NewsEntry newsEntry) {

		return this.getEntityManager().merge(newsEntry);
	}


	@Override
	@Transactional
	public void delete(Long id) {

		if (id == null) {
			return;
		}

		NewsEntry newsEntry = this.find(id);
		if (newsEntry == null) {
			return;
		}

		this.getEntityManager().remove(newsEntry);

	}
}
