package com.customer.rewards.service;

import java.util.List;

import com.customer.rewards.entity.CustomerRewardsResponse;

/*
 * Service Interface exposing operations to calculate rewards.
 */
public interface RewardsService {

	public int getTotalRewardPointsByCustomerId(int customerId);

	public List<CustomerRewardsResponse> getAllCustomersRewards();

	public CustomerRewardsResponse getRewardPointsPerMonthByCustomerId(int customerId);

}
