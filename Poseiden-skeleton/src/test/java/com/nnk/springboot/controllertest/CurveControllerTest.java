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
public class CurveControllerTest {

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
	public void postCurvePoint() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/curvePoint/add").contentType(MediaType.APPLICATION_JSON)
				.content(
						"{\"id\":1,\"curveId\":10,\"asOfDate\":null,\"term\":10.0,\"value\":10.0,\"creationDate\":null}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isMethodNotAllowed());

	}

	@Test
	@WithUserDetails("user")
	public void getCurvePoint() throws Exception {
		this.postCurvePoint();
		this.mockMvc.perform(MockMvcRequestBuilders.get("/curvePoint/list").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	@WithUserDetails("user")
	public void updateCurvePoint() throws Exception {
		this.postCurvePoint();
		this.mockMvc
				.perform(MockMvcRequestBuilders.put("/curvePoint/update/1").contentType(MediaType.APPLICATION_JSON)
						.content("{\"curveId\":10,\"term\":10.0,\"value\":100.0}").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isMethodNotAllowed());
	}

	@Test
	@WithUserDetails("user")
	public void deleteCurvePoint() throws Exception {
		this.postCurvePoint();
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/curvePoint/delete/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isMethodNotAllowed());
	}

}
