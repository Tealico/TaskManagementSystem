package com.task.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.task.entity.CommentEntity;

@Repository
@Transactional
public class CommentRepository {
	private EntityManager entityManager;

	public CommentRepository(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}
	
	private static final String COMMENT_BY_ID = "SELECT comment FROM CommentEntity comment where comment.id =?1 ";

	public List<CommentEntity> getAllComment() {
		TypedQuery<CommentEntity> query = entityManager.createNamedQuery("comment.findAll", CommentEntity.class);
		System.out.println(query.toString());
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
