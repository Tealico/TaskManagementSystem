package com.task.entity;

import lombok.*;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "complexity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@NamedQuery(name = "complexity.findAll", query = "SELECT complexity FROM ComplexityEntity complexity ")
public class ComplexityEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "point")
    private int point;

}
