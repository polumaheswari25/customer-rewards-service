
package com.customer.rewards.entity;

import java.time.LocalDate;

public class Transaction {

	private LocalDate transactionDate;

	private double transactionAmount;

	public Transaction(LocalDate transactionDate, double transactionAmount) {
		this.transactionDate = transactionDate;
		this.transactionAmount = transactionAmount;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	@Override
	public String toString() {
		return "Transaction [transactionDate=" + transactionDate + ", transactionAmount=" + transactionAmount + "]";
	}

}
