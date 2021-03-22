package com.task.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "position")
    private String position;
    
    @Column(name = "email")
    private String email;
       
    @OneToMany(mappedBy = "task")
    private List<TaskEntity> tasks;
    
    @ManyToMany
    @JoinTable(name = "user_group",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<GroupEntity> groups = new ArrayList<>();

    @OneToOne
    @JoinColumn(name="role_id",referencedColumnName = "id")
    private RoleEntity roleEntity;
}
