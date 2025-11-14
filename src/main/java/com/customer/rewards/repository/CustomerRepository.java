package com.customer.rewards.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.customer.rewards.entity.Customer;
import com.customer.rewards.entity.Transaction;

/*
 * Simulated repository for managing customer transaction records
 */
@Repository
public class CustomerRepository {

	private final List<Customer> allCustomerRecords = new ArrayList<>();;

	// add dummy data
	public CustomerRepository() {

		allCustomerRecords.add(new Customer(1, "Alice", Arrays.asList(new Transaction(LocalDate.of(2025, 9, 1), 120),
				new Transaction(LocalDate.of(2025, 10, 15), 75), new Transaction(LocalDate.of(2025, 11, 5), 200))));

		allCustomerRecords.add(new Customer(2, "Bob", Arrays.asList(new Transaction(LocalDate.of(2025, 9, 5), 130),
				new Transaction(LocalDate.of(2025, 10, 5), 120), new Transaction(LocalDate.of(2025, 11, 10), 90))));

		allCustomerRecords.add(new Customer(3, "Carlos",
				Arrays.asList(new Transaction(LocalDate.of(2025, 8, 8), 786.99),
						new Transaction(LocalDate.of(2025, 9, 16), 439.29),
						new Transaction(LocalDate.of(2025, 10, 15), 67.99))));

	}

	public Customer getCustomerRecordsByCustomerId(int customerId) {
		return allCustomerRecords.stream().filter(c -> c.getCustomerId() == customerId).findFirst().orElse(null);
	}

	public List<Customer> getAllCustomerRecords() {
		return allCustomerRecords;
	}
}
