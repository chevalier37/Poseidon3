package com.nnk.springboot.controllertest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
public class BidListControllerTest {

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
	public void getBidList() throws Exception {
		this.mockMvc.perform(get("/bidList/list")).andExpect(status().isOk());
	}

	@Test
	@WithUserDetails("user")
	public void postBidList() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/bidList/add").contentType(MediaType.APPLICATION_JSON)
				.content("{\"account\":\"testAccount\",\"type\":\"testType\",\"bidQuantity\":5}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isMethodNotAllowed());
	}

	@Test
	@WithUserDetails("user")
	public void updateBidList() throws Exception {
		this.postBidList();
		this.mockMvc.perform(MockMvcRequestBuilders.put("/bidList/update/1").contentType(MediaType.APPLICATION_JSON)
				.content("{\"account\":\"testAccount\",\"type\":\"testType\",\"bidQuantity\":50}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isMethodNotAllowed());
	}

	@Test
	@WithUserDetails("user")
	public void deleteBidList() throws Exception {
		this.postBidList();
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/bidList/delete/1").contentType(MediaType.APPLICATION_JSON)
				.content("{\"account\":\"testAccount\",\"type\":\"testType\",\"bidQuantity\":50}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isMethodNotAllowed());
	}

}
