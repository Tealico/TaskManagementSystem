package com.task.service;

import com.task.TaskApplicationTests;
import com.task.converter.UserConverter;
import com.task.dto.UserDto;
import com.task.entity.UserEntity;
import com.task.repository.GroupRepository;
import com.task.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class UserServiceTests extends TaskApplicationTests {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private GroupRepository groupRepository;

    @Autowired
    private UserService userService;

    @Test
    public void whenGettingAllUsers_thenCorrect() {
        List<UserEntity> userEntityList = new ArrayList<UserEntity>();
        List<UserDto> expectedUserListFromService = new ArrayList<>();

        UserEntity userEntity = createUser();
        userEntityList.add(userEntity);

        expectedUserListFromService.add(UserConverter.toDto(userEntity));

        when(userRepository.getAllUsers()).thenReturn(userEntityList);

        List<UserDto> users = userService.getAll();

        assertArrayEquals(expectedUserListFromService.toArray(), users.toArray());
    }

    public static UserEntity createUser(){
        UserEntity result = new UserEntity();
        result.setFirstName("first name");
        result.setUsername("username");
        result.setEmail("firstlast@gmail.com");
        result.setId(1L);
        result.setPosition("ROLE_EMPLOYEE");
        return result;
    }
}
