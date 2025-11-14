package com.customer.rewards.entity;

import java.util.List;

/**
 * A model class representing each customer transaction record.
 */
public class Customer {

	private int customerId;
	private String customerName;
	private List<Transaction> transactions;

	
	public Customer(int customerId, String customerName, List<Transaction> transactions) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.transactions = transactions;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", transactions="
				+ transactions + "]";
	}

}
