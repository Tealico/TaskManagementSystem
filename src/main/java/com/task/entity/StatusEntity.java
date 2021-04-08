package com.task.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "status")
@Getter
@Setter
@NoArgsConstructor
@ToString
@NamedQuery(name = "status.findAll", query = "SELECT status FROM StatusEntity status ")
public class StatusEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "description")
    private String description;

}