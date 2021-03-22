package com.task.repository;

import javax.persistence.EntityManager;

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
	
	public String addGroup(GroupEntity group) {
		System.out.println("Erdhi tek repository.");
		entityManager.persist(group);
		return "success";
	}

}
