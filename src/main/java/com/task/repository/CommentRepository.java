package com.task.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.task.entity.CommentEntity;

@Repository
@Transactional
public class CommentRepository {

	Logger logger = LoggerFactory.getLogger(CommentRepository.class);
	private EntityManager entityManager;


	public CommentRepository(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}
	
	private static final String COMMENT_BY_ID = "SELECT comment FROM CommentEntity comment where comment.id =?1 ";
	private static final String COMMENTS_BY_TASK_ID="select comment FROM CommentEntity comment where comment.task.id=?1";

	public List<CommentEntity> getAllComment() {
		TypedQuery<CommentEntity> query = entityManager.createNamedQuery("comment.findAll", CommentEntity.class);
		return query.getResultList();
	}
	
	public CommentEntity getCommentById(long commentId) {
		TypedQuery<CommentEntity> query = entityManager.createQuery(COMMENT_BY_ID, CommentEntity.class).setParameter(1,commentId);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	public List<CommentEntity> getAllCommentsByTaskId(long taskId) {
		TypedQuery<CommentEntity> query = entityManager.createQuery(COMMENTS_BY_TASK_ID, CommentEntity.class).setParameter(1, taskId);
		return query.getResultList();
		}

	public void addComment(CommentEntity comment) {
	    entityManager.persist(comment);
	}

	public CommentEntity updateComment(CommentEntity comment) {
		return entityManager.merge(comment);
	}

	public void deleteComment(CommentEntity comment) {
		entityManager.remove(comment);
	}

}
