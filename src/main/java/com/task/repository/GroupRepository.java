package com.task.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.task.entity.GroupEntity;
import com.task.entity.UserEntity;

@Repository
@Transactional
public class GroupRepository {

	private EntityManager entityManager;

	public GroupRepository(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}
	
	private static final String GROUP_BY_ID = "SELECT groups FROM GroupEntity groups where groups.id =?1 ";
	private static final String GROUP_BY_NAME="select groups FROM GroupEntity groups where groups.name=?1";

	public List<GroupEntity> getAllGroups() {
		TypedQuery<GroupEntity> query = entityManager.createNamedQuery("groups.findAll", GroupEntity.class);
		System.out.println(query.toString());
		return query.getResultList();
	}
	
	public GroupEntity getGroupById(long groupId) {
		TypedQuery<GroupEntity> query = entityManager.createQuery(GROUP_BY_ID, GroupEntity.class).setParameter(1,groupId);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public void addGroup(GroupEntity group) {
		System.out.println(group.toString());
		entityManager.persist(group);
	}

	public GroupEntity updateGroup(GroupEntity group) {
		return entityManager.merge(group);
	}

	public void deleteGroup(GroupEntity group) {
		entityManager.remove(group);
	}
	
	public List<GroupEntity> searchByName(String name) {
		TypedQuery<GroupEntity> query = entityManager.createQuery(GROUP_BY_NAME, GroupEntity.class).setParameter(1, name);
		return query.getResultList();
		}

}
