package com.example.training;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class TrainingApplicationTests {

	@Autowired
	private ApplicationController applicationController;
	@Autowired
	private UserController userController;
	@Autowired
	private TrainingController trainingController;

	@Test
	public void contextLoads() throws Exception{
		assertThat(applicationController).isNotNull();
		assertThat(userController).isNotNull();
		assertThat(trainingController).isNotNull();
	}


}
