package com.task.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "groups")
@Getter
@Setter
@NoArgsConstructor
@ToString
@NamedQuery(name = "groups.findAll", query = "SELECT groups FROM GroupEntity groups ")
public class GroupEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "group_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

//	@ManyToMany
//	@JoinTable(name = "user_group", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
//	private List<UserEntity> users = new ArrayList<>();


	// @OneToMany(mappedBy = "task")
	// private List<TaskEntity> taskEntities=new ArrayList<>();
	
	

}
