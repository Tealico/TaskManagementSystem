package com.task.service;

import org.springframework.stereotype.Service;

import com.task.entity.GroupEntity;
import com.task.repository.GroupRepository;

@Service
public class GroupService {

	GroupRepository groupRepository;
	
	public GroupService(GroupRepository groupRepository) {
		super();
		this.groupRepository = groupRepository;
	}

	public void add(GroupEntity group) {
		groupRepository.addGroup(group);
	}
	
	public void update(GroupEntity group) {
		groupRepository.updateGroup(group);
	}
	
	public void delete(GroupEntity group) {
		groupRepository.deleteGroup(group);
	}
}
