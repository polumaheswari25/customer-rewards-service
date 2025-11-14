package com.customer.rewards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.customer.rewards.entity.CustomerRewardsResponse;

import com.customer.rewards.service.RewardsService;

/**
 * Controller for handling customer reward API's.
 */
@RestController
@RequestMapping("/api")
public class RewardsController {

	@Autowired
	private RewardsService rewardsService;

	/**
	 * Fetching total And each month reward points by customerId
	 */
	@GetMapping("/getRewardPointsPerMonth/{customerId}")
	public ResponseEntity<CustomerRewardsResponse> getRewardPointsPerMonthByCustomerId(@PathVariable int customerId) {
		return new ResponseEntity<>(rewardsService.getRewardPointsPerMonthByCustomerId(customerId), HttpStatus.OK);
	}

	/**
	 * Fetching total And each month reward points for all customers
	 */
	@GetMapping("/getAllCustomersRewardPoints")
	public ResponseEntity<List<CustomerRewardsResponse>> getAllCustomersRewards() {
		List<CustomerRewardsResponse> rewards = rewardsService.getAllCustomersRewards();
		return ResponseEntity.ok(rewards);
	}

	/**
	 * Fetching total reward points by customerId
	 */
	@GetMapping("/getTotalRewardPoints/{customerId}")
	public int getTotalRewardPointsByCustomerId(@PathVariable int customerId) {
		return rewardsService.getTotalRewardPointsByCustomerId(customerId);
	}

}
