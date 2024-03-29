package com.task.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@Getter
@Setter
@NoArgsConstructor
@ToString
@NamedQuery(name = "task.findAll", query = "SELECT task FROM TaskEntity task ")
public class TaskEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "title")
    private String title;
    
    @Column(name = "date")
    private LocalDateTime date;
    
    @Column(name = "start_time")
    private LocalDateTime startTime;
    
    @Column(name = "end_time")
    private LocalDateTime endTime;
    
    @ManyToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private UserEntity user;
    
//    @ManyToOne
//    @JoinColumn(name = "group_id", referencedColumnName = "group", nullable = false)
//    private GroupEntity group;
//    
    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    private StatusEntity status;
    
    @ManyToOne
    @JoinColumn(name = "complexity_id", referencedColumnName = "id")
    private ComplexityEntity complexity;    
 
}
