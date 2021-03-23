package com.task.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.task.entity.GroupEntity;
import com.task.service.GroupService;


@RestController
public class GroupController {
	private GroupService groupService;

	public GroupController(GroupService groupService) {
		super();
		this.groupService = groupService;
	}
	
	@PostMapping("/addgroup")
	public GroupEntity addGroup(@RequestBody GroupEntity group) {
		System.out.println(group);
		groupService.add(group);
		// return input object
		return group;
	}
	
	@PostMapping("/updategroup")
	public GroupEntity updateGroup(@RequestBody GroupEntity group) {
		groupService.update(group);
		return group;
	}
	
	@PostMapping("/deletegroup")
	public GroupEntity deleteGroup(@RequestBody GroupEntity group) {
		groupService.delete(group);
		return group;
	}
	
}
