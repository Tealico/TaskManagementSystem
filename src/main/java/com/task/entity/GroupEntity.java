package com.task.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.h2.engine.User;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "group")
@Getter
@Setter
@NoArgsConstructor
@ToString
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "GroupEntity [id=" + id + ", name=" + name + ", description=" + description + ", createdAt=" + createdAt
				+ "]";
	}

	
//	public List<UserEntity> getUsers() {
//		return users;
//	}
//
//	public void setUsers(List<UserEntity> users) {
//		this.users = users;
//	}

	// @OneToMany(mappedBy = "task")
	// private List<TaskEntity> taskEntities=new ArrayList<>();
	
	

}
