package com.customer.rewards;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.customer.rewards.entity.CustomerRewardsResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RewardsControllerTest {
	@Autowired
	private TestRestTemplate restTemplate;

	/*
	 * valid case for when a customer record exists
	 */
	@Test
	void testGetTotalRewardPointsByCustomerId_CustomerExists() {
		ResponseEntity<Integer> response = restTemplate.getForEntity("/api/getTotalRewardPoints/1", Integer.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(365, response.getBody());
	}

	/*
	 * invalid case for when a customer records don't exist
	 */
	@Test
	void testGetTotalRewardPointsByCustomerId_CustomerRecordsNotFound() {
		ResponseEntity<Object> response = restTemplate.getForEntity("/api/getTotalRewardPoints/22", Object.class);
		System.out.println("RESPONSE: " + response.getBody());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	/*
	 * valid case for when a customer record exists
	 */
	@Test
	void testGetRewardPointsPerMonthByCustomerId_CustomerExists() {
		ResponseEntity<CustomerRewardsResponse> response = restTemplate.getForEntity("/api/getRewardPointsPerMonth/1",
				CustomerRewardsResponse.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(365, response.getBody().getTotalRewardPoints());
	}

	/*
	 * invalid case for when a customer records don't exist
	 */
	@Test
	void testGetRewardPointsPerMonthByCustomerId_CustomerRecordsNotFound() {
		ResponseEntity<Object> response = restTemplate.getForEntity("/api/getRewardPointsPerMonth/22", Object.class);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	/*
	 * valid case for when a customer record exists
	 */
	@Test
	void getAllCustomers() {
		ResponseEntity<CustomerRewardsResponse[]> response = restTemplate.getForEntity("/api/getAllCustomers",
				CustomerRewardsResponse[].class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().length).isGreaterThan(0);

		CustomerRewardsResponse first = response.getBody()[0];
		assertThat(first.getCustomerName()).isEqualTo("Alice");
		assertThat(first.getTotalRewardPoints()).isGreaterThan(0);
	}

	/*
	 * GetAllCustomeres Invalid url Returns ResourceNotFound
	 */
	@Test
	void testInvalidGetAllCustomeresURL() {
		ResponseEntity<Object> response = restTemplate.getForEntity("/api/getAllCustomer", Object.class);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
}
