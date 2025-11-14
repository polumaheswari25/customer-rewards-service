package com.customer.rewards.service;

import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.customer.rewards.entity.Customer;
import com.customer.rewards.entity.MonthlyRewardPoints;
import com.customer.rewards.entity.CustomerRewardsResponse;
import com.customer.rewards.entity.Transaction;
import com.customer.rewards.exception.CustomerNotFoundException;
import com.customer.rewards.repository.CustomerRepository;

/*
 * Business logic for calculating customer reward points.
 */
@Service
public class RewardsServiceImpl implements RewardsService {

	private static final String CUSTOMER_NOT_FOUND = "Customer Records Not Found";

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public int getTotalRewardPointsByCustomerId(int customerId) {
		Customer customer = customerRepository.getCustomerRecordsByCustomerId(customerId);
		if (customer == null) {
			throw new CustomerNotFoundException(CUSTOMER_NOT_FOUND + customerId);
		}

		int totalPoints = customer.getTransactions().stream()
				.mapToInt(transaction -> calculateRewardPoints(transaction.getTransactionAmount())).sum();

		return totalPoints;
	}

	@Override
	public CustomerRewardsResponse getRewardPointsPerMonthByCustomerId(int customerId) {
		Customer customer = customerRepository.getCustomerRecordsByCustomerId(customerId);
		return getCustomerRewardsById(customer);

	}

	@Override
	public List<CustomerRewardsResponse> getAllCustomersRewards() {

		List<Customer> customers = customerRepository.getAllCustomerRecords();

		return customers.stream().map(this::getCustomerRewardsById).collect(Collectors.toList());
	}

	/*
	 * Get rewards for customer by ID, calculating points per month based on total
	 * monthly spend
	 */
	public CustomerRewardsResponse getCustomerRewardsById(Customer customer) {

		if (customer == null) {
			throw new CustomerNotFoundException(CUSTOMER_NOT_FOUND);
		}

		String customerName = customer.getCustomerName();
		int customerId = customer.getCustomerId();

		// Group transactions by month
		Map<Month, List<Transaction>> groupedByMonth = customer.getTransactions().stream()
				.sorted(Comparator.comparing(Transaction::getTransactionDate))
				.collect(Collectors.groupingBy(t -> t.getTransactionDate().getMonth(), LinkedHashMap::new,
						Collectors.toList()));

		List<MonthlyRewardPoints> monthlySummaries = new ArrayList<>();
		int totalPoints = 0;

		// Loop each month to calculate totals and reward points
		for (Map.Entry<Month, List<Transaction>> entry : groupedByMonth.entrySet()) {
			Month month = entry.getKey();
			List<Transaction> transactions = entry.getValue();

			double totalAmount = transactions.stream().mapToDouble(Transaction::getTransactionAmount).sum();

			int monthlyPoints = transactions.stream().mapToDouble(Transaction::getTransactionAmount)
					.mapToInt(this::calculateRewardPoints).sum();

			totalPoints += monthlyPoints;
			monthlySummaries.add(new MonthlyRewardPoints(month, totalAmount, monthlyPoints));
		}

		return new CustomerRewardsResponse(customerId, customerName, totalPoints, monthlySummaries);
	}

	/*
	 * Calculates and Returns reward points for a given transaction amount
	 */
	public int calculateRewardPoints(double transactionAmount) {
		int points = 0;

		if (transactionAmount > 100) {
			points += 2 * (int) (transactionAmount - 100);
			points += 50;
		} else if (transactionAmount > 50) {
			points += (int) (transactionAmount - 50);
		}

		return points;
	}
}
