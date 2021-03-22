package com.task.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.entity.GroupEntity;
import com.task.service.GroupService;

@RestController
@RequestMapping("/group")
public class GroupController {
	private GroupService groupService;

	public GroupController(GroupService groupService) {
		super();
		this.groupService = groupService;
	}
	
	@PostMapping
	public GroupEntity addGroup(@RequestBody GroupEntity group) {
		System.out.println(group);
		groupService.add(group);
		// return input object
		return group;
	}
	
}
