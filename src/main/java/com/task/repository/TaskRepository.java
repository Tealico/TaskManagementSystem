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
	private static final String TASK_BY_USER_ID="select task FROM TaskEntity task where task.user.id=?1";
	private static final String TASK_BY_GROUP_ID="select task FROM TaskEntity task where task.user.groups.id=?1";
	private static final String TASK_BY_COMPLEXITY="select task FROM TaskEntity task where task.complexity.name=?1";
	private static final String TASK_BY_STATUS="select task FROM TaskEntity task where task.status.description=?1";
	private static final String TASK_BY_COMPLEXITY_AND_STATUS="select task FROM TaskEntity task "
			+ "where task.complexity.name=?1 and task.status.description=?2";
	private static final String TASK_BY_USER_ID_AND_COMPLEXITY="select task FROM TaskEntity task "
			+ "where task.user.id=?1 and task.complexity.name=?2";
	private static final String TASK_BY_USER_ID_AND_STATUS="select task FROM TaskEntity task "
			+ "where task.user.id=?1 and task.status.description=?2";
	private static final String TASK_BY_USER_ID_AND_COMPLEXITY_AND_STATUS="select task FROM TaskEntity task "
			+ "where task.user.id=?1 and task.complexity.name=?2 and task.status.description=?3";
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
	
	public List<TaskEntity> getAllTasksByUserId(long userId) {
		TypedQuery<TaskEntity> query = entityManager.createQuery(TASK_BY_USER_ID, TaskEntity.class).setParameter(1, userId);
		return query.getResultList();
		}
	public List<TaskEntity> getAllTasksByGroupId(long groupId) {
		TypedQuery<TaskEntity> query = entityManager.createQuery(TASK_BY_GROUP_ID, TaskEntity.class).setParameter(1, groupId);
		return query.getResultList();
		}
	public List<TaskEntity> getAllTasksByComplexity(String complexity) {
		TypedQuery<TaskEntity> query = entityManager.createQuery(TASK_BY_COMPLEXITY, TaskEntity.class).setParameter(1, complexity);
		return query.getResultList();
		}
	public List<TaskEntity> getAllTasksByStatus(String status) {
		TypedQuery<TaskEntity> query = entityManager.createQuery(TASK_BY_STATUS, TaskEntity.class).setParameter(1, status);
		return query.getResultList();
		}
	public List<TaskEntity> getAllTasksByComplexityAndStatus(String status,String complexity) {
		TypedQuery<TaskEntity> query = entityManager.createQuery(TASK_BY_COMPLEXITY_AND_STATUS, TaskEntity.class)
				.setParameter(1, complexity)
				.setParameter(2, status);
		return query.getResultList();
		}
	public List<TaskEntity> getAllTasksByUserAndStatus(long userId,String status) {
		TypedQuery<TaskEntity> query = entityManager.createQuery(TASK_BY_USER_ID_AND_STATUS, TaskEntity.class)
				.setParameter(1, userId)
				.setParameter(2, status);
		return query.getResultList();
		}
	public List<TaskEntity> getAllTasksByUserAndComplexity(long userId,String complexity) {
		TypedQuery<TaskEntity> query = entityManager.createQuery(TASK_BY_USER_ID_AND_COMPLEXITY, TaskEntity.class)
				.setParameter(1, userId)
				.setParameter(2, complexity);
		return query.getResultList();
		}
	public List<TaskEntity> getAllTasksByUserIdAndComplexityAndStatus(long userId,String status,String complexity) {
		TypedQuery<TaskEntity> query = entityManager.createQuery(TASK_BY_USER_ID_AND_COMPLEXITY_AND_STATUS, TaskEntity.class)
				.setParameter(1, userId)
				.setParameter(2, complexity)
				.setParameter(3, status);
		return query.getResultList();
		}
}

