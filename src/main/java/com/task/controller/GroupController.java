package com.task.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task.converter.GroupConverter;
import com.task.dto.GroupDto;
import com.task.dto.GroupDtoForCreate;
import com.task.dto.GroupDtoForUpdate;
import com.task.dto.UserDto;
import com.task.entity.GroupEntity;
import com.task.service.GroupService;


@RestController
public class GroupController {
	Logger logger = LoggerFactory.getLogger(GroupController.class);
	@Autowired
	GroupService groupService;
	
	@GetMapping("/group")
	public List<GroupDto> getAll(@RequestParam(required=false) String name){
		
		if(name != null) {
			logger.info("Get all groups by name filter", name);
			return groupService.searchGroupByName(name);
		}
		logger.info("Get all groups");
		return groupService.getAll();
	}
	
	@GetMapping("/group/{id}")
	public GroupDto getById(@PathVariable long id){
		return groupService.getById(id);
	}
	
	@PostMapping("/group")
	public GroupDto addGroup(@RequestBody GroupDtoForCreate group) {
		return GroupConverter.toDto(groupService.addGroup(group));
	}
	
	@PostMapping("/group/{id}")
	public GroupDto updateGroup(@PathVariable long id, @RequestBody GroupDtoForUpdate group) {
		return groupService.updateGroup(id, group);
	}
	
	@DeleteMapping("/group/{id}")
	public GroupEntity deleteGroup(@PathVariable long id) {
		return groupService.deleteGroup(id);
	}
	
}
