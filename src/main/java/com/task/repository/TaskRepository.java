package com.task.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import com.task.entity.TaskEntity;
import com.task.entity.UserEntity;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class TaskRepository {

	private EntityManager entityManager;

	public TaskRepository(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}
	
	private static final String TASK_BY_ID = "SELECT task FROM TaskEntity task where task.id =?1 ";
	private static final String TASK_BY_USER_ID="select task FROM TaskEntity task where task.userId=?1";
	private static final String TASK_BY_COMPLEXITY="select task FROM TaskEntity task where task.complexity?1";
	private static final String TASK_BY_STATUS="select task FROM TaskEntity task where task.status=?1";

	public List<TaskEntity> getAllTasks() {
		TypedQuery<TaskEntity> query = entityManager.createNamedQuery("task.findAll", TaskEntity.class);
		System.out.println(query.toString());
		return query.getResultList();
	}
	
	public TaskEntity getTaskById(long taskId) {
		TypedQuery<TaskEntity> query = entityManager.createQuery(TASK_BY_ID, TaskEntity.class).setParameter(1,taskId);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public void addTask(TaskEntity task) {
	    entityManager.persist(task);
	}

	public TaskEntity updateTask(TaskEntity task) {
		return entityManager.merge(task);
	}

	public void deleteTask(TaskEntity task) {
		entityManager.remove(task);
	}
	
	public List<TaskEntity> getAllTaskByUserId(long userId) {
		TypedQuery<TaskEntity> query = entityManager.createQuery(TASK_BY_USER_ID, TaskEntity.class).setParameter(1, userId);
		return query.getResultList();
		}
	public List<TaskEntity> getAllTaskByComplexity(String complexity) {
		TypedQuery<TaskEntity> query = entityManager.createQuery(TASK_BY_COMPLEXITY, TaskEntity.class).setParameter(1, complexity);
		return query.getResultList();
		}
	public List<TaskEntity> getAllTaskByStatus(String status) {
		TypedQuery<TaskEntity> query = entityManager.createQuery(TASK_BY_STATUS, TaskEntity.class).setParameter(1, status);
		return query.getResultList();
		}

}

