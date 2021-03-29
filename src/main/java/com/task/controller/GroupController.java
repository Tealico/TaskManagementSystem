package com.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.task.converter.GroupConverter;
import com.task.dto.GroupDto;
import com.task.dto.GroupDtoForCreate;
import com.task.entity.GroupEntity;
import com.task.service.GroupService;


@RestController
public class GroupController {
	@Autowired
	GroupService groupService;
	
	@PostMapping("/addGroup")
	public GroupDto addGroup(@RequestBody GroupDtoForCreate group) {
		return GroupConverter.toDto(groupService.addGroup(group));
	}
	
//	@PostMapping("/updategroup")
//	public GroupEntity updateGroup(@RequestBody GroupEntity group) {
//		groupService.update(group);
//		return group;
//	}
	
	@DeleteMapping("/deleteGroup/{id}")
	public void deleteGroup(@PathVariable long id) {
		groupService.deleteGroup(id);

	}
	
}
