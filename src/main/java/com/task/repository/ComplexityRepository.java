package com.task.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.task.entity.ComplexityEntity;


@Repository
@Transactional
public class ComplexityRepository {

	private EntityManager entityManager;

	public ComplexityRepository(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}
	
	private static final String COMPLEXITY_BY_ID = "SELECT complexity FROM ComplexityEntity complexity where complexity.id =?1 ";
	private static final String COMPLEXITY_BY_NAME = "SELECT complexity FROM ComplexityEntity complexity where complexity.name =?1 ";
	private static final String COMPLEXITY_BY_POINT = "SELECT complexity FROM ComplexityEntity complexity where complexity.point =?1 ";
	
	public ComplexityEntity getComplexityById(long complexityId) {
		TypedQuery<ComplexityEntity> query = entityManager.createQuery(COMPLEXITY_BY_ID, ComplexityEntity.class).setParameter(1,complexityId);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public ComplexityEntity getComplexityByName(String name) {
		TypedQuery<ComplexityEntity> query = entityManager.createQuery(COMPLEXITY_BY_NAME, ComplexityEntity.class).setParameter(1,name);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<ComplexityEntity> getAllComplexities() {
		TypedQuery<ComplexityEntity> query = entityManager.createNamedQuery("complexity.findAll", ComplexityEntity.class);
		System.out.println(query.toString());
		return query.getResultList();
	}

}
