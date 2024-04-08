package com.msa.template.inventory.config.database;

import com.querydsl.core.dml.DeleteClause;
import com.querydsl.core.dml.UpdateClause;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.PathBuilderFactory;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAUpdateClause;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.annotation.Nullable;

/**
 * Base class for implementing repositories using Querydsl library.
 *
 * @author Oliver Gierke
 * @author Mark Paluch
 */
@Repository
public abstract class QueryDslRepositorySupport {

	private final PathBuilder<?> builder;
	private @Nullable EntityManager entityManager;
	private @Nullable Querydsl querydsl;

	public QueryDslRepositorySupport(Class<?> domainClass) {

		Assert.notNull(domainClass, "Domain class must not be null!");
		this.builder = new PathBuilderFactory().create(domainClass);
	}

	@Autowired
	@PersistenceContext(unitName = DataBaseConfiguration.DOMAIN_PERSIST_UNIT)
	public void setEntityManager(
		@Qualifier("catalogDomainEntityManagerFactory") EntityManager entityManager) {

		Assert.notNull(entityManager, "EntityManager must not be null!");
		this.querydsl = new Querydsl(entityManager, builder);
		this.entityManager = entityManager;
	}

	@PostConstruct
	public void validate() {
		Assert.notNull(entityManager, "EntityManager must not be null!");
		Assert.notNull(querydsl, "Querydsl must not be null!");
	}

	@Nullable
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	protected JPQLQuery<Object> from(EntityPath<?>... paths) {
		return getRequiredQuerydsl().createQuery(paths);
	}

	protected <T> JPQLQuery<T> from(EntityPath<T> path) {
		return getRequiredQuerydsl().createQuery(path).select(path);
	}

	protected DeleteClause<JPADeleteClause> delete(EntityPath<?> path) {
		return new JPADeleteClause(getRequiredEntityManager(), path);
	}

	protected UpdateClause<JPAUpdateClause> update(EntityPath<?> path) {
		return new JPAUpdateClause(getRequiredEntityManager(), path);
	}

	@SuppressWarnings("unchecked")
	protected <T> PathBuilder<T> getBuilder() {
		return (PathBuilder<T>) builder;
	}

	@Nullable
	protected Querydsl getQuerydsl() {
		return this.querydsl;
	}

	private Querydsl getRequiredQuerydsl() {

		if (querydsl == null) {
			throw new IllegalStateException("Querydsl is null!");
		}

		return querydsl;
	}

	private EntityManager getRequiredEntityManager() {

		if (entityManager == null) {
			throw new IllegalStateException("EntityManager is null!");
		}

		return entityManager;
	}
}
