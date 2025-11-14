package com.customer.rewards.entity;

import java.util.List;

/**
 * POJO for a customer's rewards statement containing full details including
 * customer name, total rewards points earned and month-wise summary of spends
 * and total reward points
 */
public class CustomerRewardsResponse {
	private int customerId;
	private String customerName;
	private int totalRewardPoints;
	private List<MonthlyRewardPoints> monthlyRewardPoints;

	public CustomerRewardsResponse(int customerId, String customerName, int totalRewardPoints,
			List<MonthlyRewardPoints> monthlyRewardPoints) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.totalRewardPoints = totalRewardPoints;
		this.monthlyRewardPoints = monthlyRewardPoints;
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

	public int getTotalRewardPoints() {
		return totalRewardPoints;
	}

	public void setTotalRewardPoints(int totalRewardPoints) {
		this.totalRewardPoints = totalRewardPoints;
	}

	public List<MonthlyRewardPoints> getMonthlySummary() {
		return monthlyRewardPoints;
	}

	public void setMonthlySummary(List<MonthlyRewardPoints> monthlyRewardPoints) {
		this.monthlyRewardPoints = monthlyRewardPoints;
	}

	@Override
	public String toString() {
		return "RewardsResponse [customerId=" + customerId + ", customerName=" + customerName + ", totalRewardPoints="
				+ totalRewardPoints + ", monthlyRewardPoints=" + monthlyRewardPoints + "]";
	}
}
