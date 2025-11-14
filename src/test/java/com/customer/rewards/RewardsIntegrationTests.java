package com.customer.rewards;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RewardsIntegrationTests {

	@Autowired
	private MockMvc mockMvc;

	// Get All Customers
	@Test
	void testGetAllCustomers_Success() throws Exception {
		mockMvc.perform(get("/api/getAllCustomers").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].customerName").value("Alice"))
				.andExpect(jsonPath("$[1].customerName").value("Bob"));
	}

	// Invalid Url for all customers
	@Test
	void testGetAllCustomers_InvalidUrl() throws Exception {
		mockMvc.perform(get("/api/getAllCustomer").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	// Get Total Reward Points per month by valid Customer ID
	@Test
	void testGetRewardPointsPerMonthByCustomerId_Success() throws Exception {
		mockMvc.perform(get("/api/getRewardPointsPerMonth/{customerId}", 1).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.customerName").value("Alice"))
				.andExpect(jsonPath("$.totalRewardPoints").value(365))
				.andExpect(jsonPath("$.monthlySummary[0].month").value("SEPTEMBER"))
				.andExpect(jsonPath("$.monthlySummary[0].totalAmountSpent").value(120))
				.andExpect(jsonPath("$.monthlySummary[0].rewardPoints").value(90))
				.andExpect(jsonPath("$.monthlySummary[1].month").value("OCTOBER"))
				.andExpect(jsonPath("$.monthlySummary[1].totalAmountSpent").value(75))
				.andExpect(jsonPath("$.monthlySummary[1].rewardPoints").value(25))
				.andExpect(jsonPath("$.monthlySummary[2].month").value("NOVEMBER"))
				.andExpect(jsonPath("$.monthlySummary[2].totalAmountSpent").value(200))
				.andExpect(jsonPath("$.monthlySummary[2].rewardPoints").value(250));

	}

	// Get Total Reward Points per month by Customer ID not exists
	@Test
	void testGetRewardPointsPerMonthByCustomerId_NotFound() throws Exception {
		mockMvc.perform(get("/api/getRewardPointsPerMonth/{customerId}", 22).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andExpect(jsonPath("$.status").value(404))
				.andExpect(jsonPath("$.error").value("Customer Not Found"))
				.andExpect(jsonPath("$.message").value("Customer Records Not Found"));
	}

	// Get Total Reward Points by valid Customer ID
	@Test
	void testGetTotalRewardPointsByCustomerId_Success() throws Exception {
		mockMvc.perform(get("/api/getTotalRewardPoints/{customerId}", 2).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().string("240"));
	}

	// Get Total Reward Points by Customer ID not exists
	@Test
	void testGetTotalRewardPointsByCustomerId_NotFound() throws Exception {
		mockMvc.perform(get("/api/getTotalRewardPoints/{customerId}", 50).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andExpect(jsonPath("$.status").value(404))
				.andExpect(jsonPath("$.error").value("Customer Not Found"))
				.andExpect(jsonPath("$.message").value("Customer Records Not Found50"));
	}
}
