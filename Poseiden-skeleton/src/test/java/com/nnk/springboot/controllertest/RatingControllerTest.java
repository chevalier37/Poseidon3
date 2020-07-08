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
public class RatingControllerTest {

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
	public void postRating() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/rating/add").contentType(MediaType.APPLICATION_JSON).content(
				"{\"moodysRating\":\"moodysRating\",\"sandPRating\":\"sandPRating\",\"fitchRating\":\"fitchRating\", \"orderNumber\":10}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isMethodNotAllowed());
	}

	@Test
	@WithUserDetails("user")
	public void getRating() throws Exception {
		this.postRating();
		this.mockMvc.perform(MockMvcRequestBuilders.get("/rating/list").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	@WithUserDetails("user")
	public void updateRating() throws Exception {
		this.postRating();
		this.mockMvc.perform(MockMvcRequestBuilders.put("/rating/update/1").contentType(MediaType.APPLICATION_JSON)
				.content(
						"{\"moodysRating\":\"moodysRating\",\"sandPRating\":\"sandPRating\",\"fitchRating\":\"fitchRating\", \"orderNumber\":100}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isMethodNotAllowed());
	}

	@Test
	@WithUserDetails("user")
	public void deleteRating() throws Exception {
		this.postRating();
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/rating/delete/1").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isMethodNotAllowed());
	}

}
