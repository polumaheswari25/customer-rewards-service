package com.customer.rewards;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.customer.rewards.entity.Customer;
import com.customer.rewards.entity.CustomerRewardsResponse;
import com.customer.rewards.entity.MonthlyRewardPoints;
import com.customer.rewards.entity.Transaction;
import com.customer.rewards.exception.CustomerNotFoundException;
import com.customer.rewards.repository.CustomerRepository;
import com.customer.rewards.service.RewardsServiceImpl;

/* 
 * Unit tests for RewardsServiceImpl class
 */
@SpringBootTest
public class RewardsServiceImplTest {
	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private RewardsServiceImpl rewardsService;

	private static Customer customer;

	@BeforeAll
	static void beforeAll() {
		customer = new Customer(101, "Alice", Arrays.asList(new Transaction(LocalDate.of(2025, 9, 1), 120),
				new Transaction(LocalDate.of(2025, 10, 15), 75), new Transaction(LocalDate.of(2025, 11, 5), 200)));
	}

	@BeforeEach
	void beforeEach() {
		MockitoAnnotations.openMocks(this);
	}

	/*
	 * valid case for when a customer record exists, the function should return the
	 * correct total reward points for that customer
	 */
	@Test
	void testGetTotalRewardPointsByCustomerId_CustomerExists() {
		when(customerRepository.getCustomerRecordsByCustomerId(101)).thenReturn(customer);
		int totalPoints = rewardsService.getTotalRewardPointsByCustomerId(101);

		assertEquals(365, totalPoints);
	}

	/*
	 * invalid case for when a customer records don't exist, the function should
	 * throw CustomerNotFoundException
	 */
	@Test
	void testGetTotalRewardPointsByCustomerId_CustomerRecordsNotFound() {
		when(customerRepository.getCustomerRecordsByCustomerId(203)).thenReturn(null);

		CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class,
				() -> rewardsService.getTotalRewardPointsByCustomerId(203));

		assertEquals("Customer Records Not Found203", exception.getMessage());
	}

	/*
	 * valid case for when a customer record exists, the function should return the
	 * statement for the customer with the right values for customer name, total
	 * reward points, total amount spent and reward points for any month
	 */
	@Test
	void testGetRewardPointsPerMonth_CustomerExists() {

		when(customerRepository.getCustomerRecordsByCustomerId(101)).thenReturn(customer);

		CustomerRewardsResponse statement = rewardsService.getRewardPointsPerMonthByCustomerId(101);

		assertEquals(3, statement.getMonthlySummary().size());
		assertEquals("Alice", statement.getCustomerName());
		assertEquals(365, statement.getTotalRewardPoints());

		// get monthly summary of January
		MonthlyRewardPoints summary = null;
		for (MonthlyRewardPoints summary1 : statement.getMonthlySummary()) {
			if (summary1.getMonth().equals(Month.SEPTEMBER)) {
				summary = summary1;
				break;
			}
		}

		assertEquals(120.0, summary.getTotalAmountSpent());
		assertEquals(90, summary.getRewardPoints());
	}

	/*
	 * invalid case for when a customer records don't exist, the function should
	 * throw CustomerNotFoundException
	 */
	@Test
	void testGetRewardPointsPerMonth_CustomerRecordsNotFound() {
		when(customerRepository.getCustomerRecordsByCustomerId(203)).thenReturn(null);
		CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class,
				() -> rewardsService.getRewardPointsPerMonthByCustomerId(203));
		assertEquals("Customer Records Not Found", exception.getMessage());
	}

	/*
	 * test the helper function business logic for calculation of reward points for
	 * a given amount in dollars
	 */
	@Test
	void testCalculateRewardPoints() {
		assertEquals(0, rewardsService.calculateRewardPoints(50));
		assertEquals(10, rewardsService.calculateRewardPoints(60));
		assertEquals(50, rewardsService.calculateRewardPoints(100));
		assertEquals(90, rewardsService.calculateRewardPoints(120));
	}
}
