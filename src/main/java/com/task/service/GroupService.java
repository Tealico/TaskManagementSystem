package com.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.converter.GroupConverter;
import com.task.dto.GroupDtoForCreate;
import com.task.entity.GroupEntity;
import com.task.exception.GroupException;
import com.task.repository.GroupRepository;

@Service
public class GroupService {
	@Autowired
	GroupRepository groupRepository;
	
	public GroupEntity addGroup(GroupDtoForCreate group) {
		if (group != null) {
			if (group.getName() != null) {
				if (group.getDescription() != null) {
						if(group.getCreatedAt() != null) {
							GroupEntity groupToAdd = GroupConverter.toEntityForCreate(group);
							groupRepository.addGroup(groupToAdd);
						return groupToAdd;
						} else {
							throw new GroupException("Could not create group");
						}
				} else {
						System.out.println("Group created at is mandatory");
						throw new GroupException("Created at is required");

					}
			}else {
						System.out.println("Group description is mandatory");
						throw new GroupException("Description is required");

					}
		} else {
				System.out.println("Group name is mandatory");
				throw new GroupException("Name is required");
		}
	}
	
	public void deleteGroup(long id) {
		GroupEntity group = groupRepository.getGroupById(id);
		if (group != null) {
				groupRepository.deleteGroup(group);
		} else {
			System.out.println("Book not found");
			throw new GroupException(id);
		}
	}
}
