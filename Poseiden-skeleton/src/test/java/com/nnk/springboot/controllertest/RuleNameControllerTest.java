package com.nnk.springboot.controllertest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RuleNameControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	@WithUserDetails("user")
	public void postRuleName() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/ruleName/add").contentType(MediaType.APPLICATION_JSON)
				.content(
						"{\"name\":\"name\",\"description\":\"description\",\"json\":\"json\", \"template\":\"template\",\"sqlStr\":\"sqlStr\",\"sqlPart\":\"sqlPart\"}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isMethodNotAllowed());
	}

	@Test
	@WithUserDetails("user")
	public void getRuleName() throws Exception {
		this.postRuleName();
		this.mockMvc.perform(MockMvcRequestBuilders.get("/ruleName/list").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	@WithUserDetails("user")
	public void updateRuleName() throws Exception {
		this.postRuleName();
		this.mockMvc.perform(MockMvcRequestBuilders.put("/ruleName/update/1").contentType(MediaType.APPLICATION_JSON)
				.content(
						"{\"name\":\"name1\",\"description\":\"description\",\"json\":\"json\",\"template\":\"template\",\"sqlStr\":\"sqlStr\",\"sqlPart\":\"sqlPart\"}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isMethodNotAllowed());
	}

	@Test
	@WithUserDetails("user")
	public void deleteRuleName() throws Exception {
		this.postRuleName();
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/ruleName/delete/1").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isMethodNotAllowed());
	}

}
