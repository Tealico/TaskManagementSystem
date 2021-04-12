package com.task.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.task.entity.StatusEntity;

@Repository
@Transactional
public class RoleRepository {
	private EntityManager entityManager;

	public RoleRepository(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}
	
	private static final String ROLE_BY_ID = "SELECT role FROM RoleEntity role where role.id =?1 ";
	private static final String ROLE_BY_NAME = "SELECT role FROM RoleEntity role where role.name =?1 ";
	
	public StatusEntity getRoleById(long roleId) {
		TypedQuery<StatusEntity> query = entityManager.createQuery(ROLE_BY_ID, StatusEntity.class).setParameter(1,roleId);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public StatusEntity getRoleByName(String name) {
		TypedQuery<StatusEntity> query = entityManager.createQuery(ROLE_BY_NAME, StatusEntity.class).setParameter(1,name);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
