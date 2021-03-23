package com.task.repository;

import com.task.entity.UserEntity;
import org.springframework.stereotype.Repository;

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
    private static final String USER_BY_USERNAME ="select user from UserEntity where user.username=?1";
    private static final String ALL_USERS_FETCHED="select user from UserEntity user Left join fetch user.roleEntity";

    public List<UserEntity>getAllUsers() {
        TypedQuery<UserEntity> query = entityManager.createQuery("User.findAll", UserEntity.class);
        return query.getResultList();
    }
    public UserEntity getAllUserFetched(){
        TypedQuery<UserEntity> query=entityManager.createQuery(ALL_USERS_FETCHED,UserEntity.class);
        return query.getSingleResult();
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
    public void addUser(UserEntity user){
        entityManager.persist(user);
    }
    public void updateUser (UserEntity user){
        entityManager.merge(user);
    }
    public void deleteUser(UserEntity user){
        entityManager.remove(user);
    }
}
