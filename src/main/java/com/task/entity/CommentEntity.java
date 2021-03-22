package com.task.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name = "comment")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CommentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
    
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "users", nullable = false)
    private UserEntity user;
    
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role", nullable = false)
    private RoleEntity role;
    
    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "task", nullable = false)
    private TaskEntity task;

}
