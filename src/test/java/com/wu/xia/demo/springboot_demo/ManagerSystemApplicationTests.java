package com.wu.xia.demo.springboot_demo;

import com.wu.xia.demo.springboot_demo.entity.User;
import com.wu.xia.demo.springboot_demo.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

@SpringBootTest
@AutoConfigureMockMvc
class ManagerSystemApplicationTests {

	@Autowired
	UserService userService;

	@Autowired
	MockMvc mockMvc;
	@Test
	void addUser() throws Exception {
		User userInfo = new User();
		userInfo.setUserId(1234);
		userInfo.setEndpoint(Arrays.asList("A","C"));
		Assertions.assertThat(userService.addUser(userInfo)).isNotNull();
	}

	@Test
	void get() throws Exception {
		Assertions.assertThat(userService.get(1234, "AA")).isNotNull();
	}


	@Test
	public void mockAddUser() throws Exception {
		String auth="{\n" +
				"\"userId\":123456,\n" +
				"\"accountName\": \"XXXXXXX\",\n" +
				"\"role\": \"admin\"\n" +
				"}";
		String body="{\n" +
				"\"userId\":1234,\n" +
				"\"endpoint\":[\"A\"]\n" +
				"}";
		String resp = mockMvc.perform(MockMvcRequestBuilders
						.post("/admin/addUser")
						.contentType(MediaType.APPLICATION_JSON)
						.content(body)
						.characterEncoding(StandardCharsets.UTF_8)
						.header("Authorization", Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8))))
				.andReturn().getResponse().getContentAsString();
		System.out.println(resp);
	}

	@Test
	public void authTest() throws Exception {
		String auth="{\n" +
				"\"userId\":123456,\n" +
				"\"accountName\": \"XXXXXXX\",\n" +
				"\"role\": \"admin\"\n" +
				"}";
		String body="{\n" +
				"\"userId\":12345,\n" +
				"\"endpoint\":[\"resource A\",\"resource B\",\"resource C\"\n" +
				"]\n" +
				"}";
		mockMvc.perform(MockMvcRequestBuilders
						.post("/admin/addUser")
						.contentType(MediaType.APPLICATION_JSON)
						.content(body)
						.characterEncoding(StandardCharsets.UTF_8)
						.header("Authorization", Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8))))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void authGetTest() throws Exception {
		String auth="{\n" +
				"\"userId\":1234,\n" +
				"\"accountName\": \"wwww\",\n" +
				"\"role\": \"user\"\n" +
				"}";
		mockMvc.perform(MockMvcRequestBuilders
						.get("/user/A")
						.contentType(MediaType.APPLICATION_JSON)
						.characterEncoding(StandardCharsets.UTF_8)
						.header("Authorization", Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8))))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}


	@Test
	public void getTest() throws Exception {
		String auth="{\n" +
				"\"userId\":1234,\n" +
				"\"accountName\": \"wwww\",\n" +
				"\"role\": \"user\"\n" +
				"}";
		String resp = mockMvc.perform(MockMvcRequestBuilders
						.get("/user/A")
						.contentType(MediaType.APPLICATION_JSON)
						.characterEncoding(StandardCharsets.UTF_8)
						.header("Authorization", Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8))))
				.andReturn().getResponse().getContentAsString();
		System.out.println(resp);
	}



}
