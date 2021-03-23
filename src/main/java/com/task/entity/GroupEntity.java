package com.task.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "group")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class GroupEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;

/*    @ManyToMany(mappedBy = "users")
    private List<UserEntity> userEntities=new ArrayList<>();

    @OneToMany(mappedBy = "task")
    private List<TaskEntity> taskEntities=new ArrayList<>();*/
    
    
    public void setCreatedAt() {
    	this.createdAt = LocalDateTime.now();  
    }
}
