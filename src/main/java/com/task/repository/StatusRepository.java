package com.task.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.task.entity.StatusEntity;
import com.task.entity.TaskEntity;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class StatusRepository {

	private EntityManager entityManager;

	public StatusRepository(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}
	
	private static final String STATUS_BY_ID = "SELECT status FROM StatusEntity status where status.id =?1 ";
	private static final String STATUS_BY_DESC = "SELECT status FROM StatusEntity status where status.description =?1 ";
	
	public StatusEntity getStatusById(long statusId) {
		TypedQuery<StatusEntity> query = entityManager.createQuery(STATUS_BY_ID, StatusEntity.class).setParameter(1,statusId);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public StatusEntity getStatusByDescription(String description) {
		TypedQuery<StatusEntity> query = entityManager.createQuery(STATUS_BY_DESC, StatusEntity.class).setParameter(1,description);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<StatusEntity> getAllStatuses() {
		TypedQuery<StatusEntity> query = entityManager.createNamedQuery("status.findAll", StatusEntity.class);
		System.out.println(query.toString());
		return query.getResultList();
	}
}