package com.task.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.converter.GroupConverter;
import com.task.dto.GroupDto;
import com.task.dto.GroupDtoForCreate;
import com.task.dto.GroupDtoForUpdate;
import com.task.entity.GroupEntity;
import com.task.exception.GroupException;
import com.task.repository.GroupRepository;

@Service
public class GroupService {
	@Autowired
	GroupRepository groupRepository;
	
	
	public GroupDto getById(long id) {
		GroupEntity group = groupRepository.getGroupById(id);
		System.out.println(group);
		if (group != null) {
			return GroupConverter.toDto(group);
		} else {
			System.out.println("Group not found");
			throw new GroupException("Group with id: " + id + ", does not exist");
		}
	}
	
	public List<GroupDto> getAll(){
		List<GroupEntity> groupEntities = groupRepository.getAllGroups();
		List<GroupDto> response = new ArrayList<>();
		
		for(GroupEntity gEntity: groupEntities) {
			response.add(GroupConverter.toDto(gEntity));
		}
		
		return response;
	}
	
	public GroupEntity addGroup(GroupDtoForCreate group) {
		if (group != null) {
			if (group.getName() != null) {
				GroupEntity groupToAdd = GroupConverter.toEntityForCreate(group);
				
				groupToAdd.setCreatedAt(LocalDateTime.now());
				
				groupRepository.addGroup(groupToAdd);
				return groupToAdd;
			} else {
					System.out.println("Group name is mandatory");
					throw new GroupException("Group Name is required");
			}
		}
		else {
			throw new GroupException("Could not create group");
		}
	}
	
	public GroupEntity deleteGroup(long id) {
		GroupEntity group = groupRepository.getGroupById(id);
		if (group != null) {
				groupRepository.deleteGroup(group);
				return group;
		} else {
			System.out.println("Group not found");
			throw new GroupException("Group with id: " + id + ", does not exist");
		}
	}
	
	public GroupDto updateGroup(long id, GroupDtoForUpdate group) {
		GroupEntity groupFromDb = groupRepository.getGroupById(id);
		if (groupFromDb != null) {
			if(group.getName() != null) {
				groupFromDb.setName(group.getName());
			}
			
			if(group.getDescription() != null) {
				groupFromDb.setDescription(group.getDescription());
			}
			
			GroupEntity response = groupRepository.updateGroup(groupFromDb);
			
			return GroupConverter.toDto(response);
		} else {
			System.out.println("Group not found");
			throw new GroupException("Group with id: " + id + ", does not exist");
		}
	}
	
	public List<GroupEntity> getAllGroups(){
		return groupRepository.getAllGroups();
	}
	
	public GroupEntity getGroupById(long id) {
		return groupRepository.getGroupById(id);
	}
}
