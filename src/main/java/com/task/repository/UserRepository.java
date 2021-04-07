package com.task.repository;

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
    private EntityManager entityManager;

    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    private static final String USER_BY_USERNAME ="select users from UserEntity where users.username=?1";
    private static final String USER_BY_ID ="select users from UserEntity where users.id=?1";
    
    public List<UserEntity>getAllUsers() {
        TypedQuery<UserEntity> query = entityManager.createQuery("User.findAll", UserEntity.class);
        return query.getResultList();
    }

    public  UserEntity getUserByUsername(String username){
        TypedQuery<UserEntity> query=entityManager.createQuery(USER_BY_USERNAME,UserEntity.class).setParameter(1,username);
        try{
            return query.getSingleResult();
        }catch (NoResultException e){
            System.out.println("No results");
            return null;
        }
    }
    public UserEntity getUserById(long userId) {
		TypedQuery<UserEntity> query = entityManager.createQuery(USER_BY_ID, UserEntity.class).setParameter(1,userId);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
    public void addUser(UserEntity user){
        entityManager.persist(user);
    }
    public UserEntity updateUser (UserEntity user){
        return entityManager.merge(user);
    }
    public void deleteUser(UserEntity user){
        entityManager.remove(user);
    }
}
