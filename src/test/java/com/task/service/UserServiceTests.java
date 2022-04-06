package com.task.service;

import com.task.TaskApplicationTests;
import com.task.converter.UserConverter;
import com.task.dto.UserDto;
import com.task.entity.UserEntity;
import com.task.exception.UserException;
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

    @Test
    public void whenGettingUserById_thenCorrect() {
        long id = 1L;
        UserEntity userEntity = createUser();
        UserDto expectedUserFromService = UserConverter.toDto(userEntity);

        when(userRepository.getUserById(id)).thenReturn(userEntity);

        UserDto user = userService.getById(id);

        assertEquals(expectedUserFromService, user);
    }

    @Test
    public void whenCreatingFailedDueExistUser(){
        UserEntity userEntity = createUser();

        when(userRepository.getUserByUsername(userEntity.getUsername())).thenReturn(userEntity);

        Throwable exception = assertThrows(UserException.class, () -> userService.createUser(UserConverter.toDtoForCreate(userEntity)));
        assertEquals("User with username: " + userEntity.getUsername() + " exists", exception.getMessage());
    }

    @Test
    public void whenUserDoesNotExistForDelete(){
        long id = 1L;
        UserEntity userEntity = createUser();

        when(userRepository.getUserById(id)).thenReturn(null);

        Throwable exception = assertThrows(UserException.class, () -> userService.deleteUser(userEntity.getId()));
        assertEquals("User with id: " + userEntity.getId() + ", does not exist", exception.getMessage());
    }

    @Test
    public void whenUserExistForDelete(){
        long id = 1L;
        UserEntity userEntity = createUser();

        when(userRepository.getUserById(id)).thenReturn(userEntity);

        UserEntity user = userService.deleteUser(id);

        assertEquals(userEntity, user);
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
