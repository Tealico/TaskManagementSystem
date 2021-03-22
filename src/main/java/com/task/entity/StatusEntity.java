package com.task.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "status")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class StatusEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "description")
    private String description;
    
    @OneToMany(mappedBy = "status")
    private List<TaskEntity> task;


}