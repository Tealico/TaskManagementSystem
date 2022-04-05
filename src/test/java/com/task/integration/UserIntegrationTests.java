package com.task.integration;

import com.task.dto.UserDto;
import com.task.entity.UserEntity;
import com.task.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.mockito.BDDMockito.given;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIntegrationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private UserService userService;

    @Test
    public void test(){
        this.testRestTemplate.getForEntity("/user/{id}", String.class, "1");
    }

    @Before
    public void setUserService(){
        given(this.userService.getById(1)).willReturn(new UserDto("Ana","annabelle","Software Engineer","ana@gmail.com"));
    }
}
