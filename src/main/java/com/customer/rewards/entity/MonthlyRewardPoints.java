package com.customer.rewards.entity;

import java.time.Month;

/**
 * DTO for storing monthly reward summary
 */
public class MonthlyRewardPoints {
	private Month month;
	private double totalAmountSpent;
	private int rewardPoints;

	public MonthlyRewardPoints(Month month, double totalAmountSpent, int rewardPoints) {
		this.month = month;
		this.totalAmountSpent = totalAmountSpent;
		this.rewardPoints = rewardPoints;
	}

	public Month getMonth() {
		return month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

	public double getTotalAmountSpent() {
		return totalAmountSpent;
	}

	public void setTotalAmountSpent(double totalAmountSpent) {
		this.totalAmountSpent = totalAmountSpent;
	}

	public int getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(int rewardPoints) {
		this.rewardPoints = rewardPoints;
	}

	@Override
	public String toString() {
		return "MonthlyRewardPoints [month=" + month + ", totalAmount=" + totalAmountSpent + ", rewardPoints="
				+ rewardPoints + "]";
	}
}
