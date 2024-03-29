package com.task.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.task.entity.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional

public class UserRepository {
	Logger logger = LoggerFactory.getLogger(UserRepository.class);
	private EntityManager entityManager;

	public UserRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	private static final String USER_BY_USERNAME = "select users from UserEntity users where users.username=?1";
	private static final String USER_BY_ID = "select users from UserEntity users where users.id=?1";
	private static final String USER_BY_NAME = "select users from UserEntity users where users.firstName like ?1";
	private static final String USERS_BY_GROUP_ID = "select users FROM UserEntity users join users.groups gr where gr.id=?1";

	public List<UserEntity> getAllUsers() {
		TypedQuery<UserEntity> query = entityManager.createNamedQuery("users.findAll", UserEntity.class);
		return query.getResultList();
	}

	public UserEntity getUserByUsername(String username) {
		TypedQuery<UserEntity> query = entityManager.createQuery(USER_BY_USERNAME, UserEntity.class).setParameter(1,
				username);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			logger.error("No Result");
			return null;
		}
	}

	public UserEntity getUserById(long userId) {
		TypedQuery<UserEntity> query = entityManager.createQuery(USER_BY_ID, UserEntity.class).setParameter(1, userId);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public void addUser(UserEntity user) {
		entityManager.persist(user);
	}

	public UserEntity updateUser(UserEntity user) {
		return entityManager.merge(user);
	}

	public void deleteUser(UserEntity user) {
		entityManager.remove(user);
	}

	public List<UserEntity> searchByName(String firstName) {
		firstName = "%" + firstName + "%";
		TypedQuery<UserEntity> query = entityManager.createQuery(USER_BY_NAME, UserEntity.class).setParameter(1,
				firstName);
		return query.getResultList();
	}

	public List<UserEntity> getAllUsersByGroupId(long groupId) {
		TypedQuery<UserEntity> query = entityManager.createQuery(USERS_BY_GROUP_ID, UserEntity.class).setParameter(1,
				groupId);

		return query.getResultList();
	}
}
