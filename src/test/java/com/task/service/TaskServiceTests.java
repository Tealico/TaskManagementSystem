package com.task.service;


import com.task.TaskApplicationTests;
import com.task.converter.TaskConverter;
import com.task.dto.TaskDto;
import com.task.entity.ComplexityEntity;
import com.task.entity.StatusEntity;
import com.task.entity.TaskEntity;
import com.task.entity.UserEntity;
import com.task.exception.TaskException;
import com.task.repository.ComplexityRepository;
import com.task.repository.StatusRepository;
import com.task.repository.TaskRepository;
import com.task.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class TaskServiceTests extends TaskApplicationTests {

    @MockBean
    public TaskRepository taskRepository;

    @MockBean
    public StatusRepository statusRepository;

    @MockBean
    public ComplexityRepository complexityRepository;

    @MockBean
    public UserRepository userRepository;

    @Autowired
    public TaskService taskService;

    @Test
    public void whenGetTaskById(){
        TaskEntity taskEntity = createTask();
        TaskDto expectedTaskDto = TaskConverter.toDto(taskEntity);

        when(taskRepository.getTaskById(taskEntity.getId())).thenReturn(taskEntity);

        TaskDto task = taskService.getById(taskEntity.getId());

        assertEquals(expectedTaskDto , task);
    }

    @Test
    public void whenGettingAllTasks_thenCorrect() {
        List<TaskEntity> taskEntityList = new ArrayList<TaskEntity>();
        List<TaskDto> expectedTaskListFromService = new ArrayList<>();

        TaskEntity taskEntity = createTask();
        taskEntityList.add(taskEntity);

        expectedTaskListFromService.add(TaskConverter.toDto(taskEntity));

        when(taskRepository.getAllTasks()).thenReturn(taskEntityList);
        List<TaskDto> task = taskService.getAll();

        assertArrayEquals(expectedTaskListFromService.toArray(), task.toArray());
    }

    @Test
    public void create_whenStatusNotPresent(){
        TaskEntity taskEntity = createTask();
        taskEntity.setStatus(null);

        Throwable exception = assertThrows(TaskException.class , () -> taskService.addTask(TaskConverter.toDtoForCreate(taskEntity)));
        assertEquals(exception.getMessage(), "Status is required");
    }

    @Test
    public void create_whenComplexityNotPresent(){
        TaskEntity taskEntity = createTask();
        taskEntity.setComplexity(null);

        Throwable exception = assertThrows(TaskException.class , () -> taskService.addTask(TaskConverter.toDtoForCreate(taskEntity)));
        assertEquals(exception.getMessage(), "Complexity is required");
    }

    @Test
    public void create_whenStatusDoesntExistInDb(){
        TaskEntity taskEntity = createTask();
        StatusEntity status = taskEntity.getStatus();

        when(statusRepository.getStatusByDescription(status.getDescription())).thenReturn(null);

        Throwable exception = assertThrows(TaskException.class , () -> taskService.addTask(TaskConverter.toDtoForCreate(taskEntity)));
        assertEquals(exception.getMessage(), "Status: " + status.getDescription() + " does not exist");
    }

    @Test
    public void create_whenComplexityDoesntExistInDb(){
        TaskEntity taskEntity = createTask();
        ComplexityEntity complexity = taskEntity.getComplexity();
        StatusEntity status = taskEntity.getStatus();

        when(statusRepository.getStatusByDescription(status.getDescription())).thenReturn(status);
        when(complexityRepository.getComplexityByName(complexity.getName())).thenReturn(null);

        Throwable exception = assertThrows(TaskException.class , () -> taskService.addTask(TaskConverter.toDtoForCreate(taskEntity)));
        assertEquals(exception.getMessage(), "Complexity: " + complexity.getName() + " does not exist");
    }

    @Test
    public void create_whenUserDoesntExistInDb(){
        TaskEntity taskEntity = createTask();
        UserEntity user = taskEntity.getUser();
        ComplexityEntity complexity = taskEntity.getComplexity();
        StatusEntity status = taskEntity.getStatus();

        when(statusRepository.getStatusByDescription(status.getDescription())).thenReturn(status);
        when(complexityRepository.getComplexityByName(complexity.getName())).thenReturn(complexity);
        when(userRepository.getUserById(user.getId())).thenReturn(null);

        Throwable exception = assertThrows(TaskException.class , () -> taskService.addTask(TaskConverter.toDtoForCreate(taskEntity)));
        assertEquals(exception.getMessage(), "User with id: " + user.getId() + " does not exist");
    }

    @Test
    public void delete_whenTaskNotExist(){
        TaskEntity taskEntity = createTask();

        when(taskRepository.getTaskById(taskEntity.getId())).thenReturn(null);

        Throwable exception = assertThrows(TaskException.class, ()-> taskService.deleteTask(taskEntity.getId()));
        assertEquals(exception.getMessage(), "Task with id: " + taskEntity.getId() + ", does not exist");
    }

    @Test
    public void delete_deleteWhenStatusIsUndone(){
        TaskEntity taskEntity = createTask();

        when(taskRepository.getTaskById(taskEntity.getId())).thenReturn(taskEntity);

        Throwable exception = assertThrows(TaskException.class, ()-> taskService.deleteTask(taskEntity.getId()));
        assertEquals(exception.getMessage(), "Can not delete Undone tasks.");
    }

    public static TaskEntity createTask(){
        TaskEntity result = new TaskEntity();
        result.setTitle("title");
        result.setDate(LocalDateTime.now());
        result.setStartTime(LocalDateTime.now());
        result.setEndTime(LocalDateTime.now());
        result.setUser(createUser());
        result.setStatus(new StatusEntity(1L, "UNDONE"));
        result.setComplexity(new ComplexityEntity(1L, "HARD" ,3 ));
        result.setId(1L);
        return result;
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
