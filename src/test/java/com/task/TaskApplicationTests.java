package com.task;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TaskApplication.class},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
public class TaskApplicationTests {

	@Test
	void contextLoads() {
	}

	@LocalServerPort
	int port;

	@Before
	public void setupUrl(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
		RestAssured.basePath = "/";
		RestAssured.defaultParser = Parser.JSON;
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}

}
