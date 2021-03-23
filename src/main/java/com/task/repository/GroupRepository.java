package com.task.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.task.entity.GroupEntity;

@Repository
@Transactional
public class GroupRepository {

	private EntityManager entityManager;

	public GroupRepository(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}
	
	private static final String GROUP_BY_ID = "SELECT group FROM GroupEntity group where group.group_id =?1 ";

	public List<GroupEntity> getAllGroups() {
		TypedQuery<GroupEntity> query = entityManager.createNamedQuery("Group.findAll", GroupEntity.class);
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
		entityManager.persist(group);
	}

	public void updateGroup(GroupEntity group) {
		entityManager.merge(group);
	}

	public void deleteGroup(GroupEntity group) {
		entityManager.remove(group);
	}

}
