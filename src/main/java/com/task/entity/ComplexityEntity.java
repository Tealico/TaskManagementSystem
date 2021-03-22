package com.task.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "complexity")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ComplexityEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "point")
    private int point;
    
    @OneToMany(mappedBy = "complexity")
    private List<TaskEntity> task;

}
