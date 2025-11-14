# Customer-rewards-api

## Overview

For a given dataset of customer records containing transaction details, the project provides api endpoints to calculate and return the monthly reward points and total rewardpoints earned for each customer . It can generate complete statement of all customeres .

- The project follows the standard guidelines for Rest APIs built using Spring Boot. The API endpoints are described in the `RewardsController` class.

- The business logic is written in `RewardsServiceImpl` class which implements the `RewardsService` interface for loose coupling.

- For the sample dataset `CustomerRepository` class emulates our database/repository using Java collections framework.

- The entities/POJO are - 
  
  - **Customer** - Stores each customer record containing information about customer id, customer name and list of transactions.
  
  - **MonthlyRewardPoints** - Stores month-wise stats like total amount spent per month and reward points earned that month.
  
  - **CustomereRewarsResponse** - Stores a customer's statement containing full details including customer name, total rewards points earned and month-wise summary of spends and reward points.
    
  - **Transaction** - Stores a customer's transaction date and transaction Amount.

- Exception Handling is performed in the `GlobalExceptionHandler` class which also handles the custom exception `CustomerNotFoundException`.

- Unit Tests are written in `RewardsServiceImplTest` and `RewardsControllerTest` class.

## Stack

1. Java 17

2. Spring Boot 3.4.4

3. Spring Boot Test 3.4.4

4. Mockito 5.14.2

5. Java Collections Framework for Emulating Database

## Project Structure
```pre
src
├── main
│   ├── java
│   │   └── com.customer.rewards
│   │           ├── controller
│   │           │    └── RewardsController.java   
│   │           ├── service           
│   │           │    └── RewardsService.java
│   │           │    └── RewardsServiceImpl.java
│   │           ├── repository
│   │           │    └── CustomerRepository.java
│   │           ├── entity  
│   │           │   └── Customer.java
│   │           │   └── MonthlyRewardPOints.java
│   │           │   └── CustomerRewardsResponse.java
│   │           │   └──Transaction.java
│   │           └── exception          
│   │               └── CustomerNotFoundException.java
│   │               └── GlobalExceptionHandler.java
│   └── resources
│       └── application.properties
│
├── test
│   └── java
│       └── com.rewardsapi
│           └── RewardsControllerTest.java
│           └── RewardsServiceImplTest.java
│            └──RewardsIntegrationTests.java
```

## API Endpoints

1. **GET** `/api/getRewardPointsPerMonth/{customerId}`
   
   Returns the total number of reward points and each month reward points of a given customer .
   
   E.g. /api/getRewardPointsPerMonth/1
   
   ```json
   {
     "customerId": 1,
     "customerName": "Alice",
     "totalRewardPoints": 365,
     "monthlySummary": [
       {
         "month": "SEPTEMBER",
         "totalAmountSpent": 120,
         "rewardPoints": 90
       },
       {
         "month": "OCTOBER",
         "totalAmountSpent": 75,
         "rewardPoints": 25
       },
       {
         "month": "NOVEMBER",
         "totalAmountSpent": 200,
         "rewardPoints": 250
       }
     ]
   }
   ```
   1.1  `/api/getRewardPointsPerMonth/{customerId}`
   
   Returns the error message if the given customer not found .
   
   E.g. /api/getRewardPointsPerMonth/11
   
 ```json
   {
     "error": "Customer Not Found",
     "message": "Customer Records Not Found",
     "timestamp": "2025-11-14T16:02:19.8991744",
     "status": 404
   }
  ```


2. **GET** `/api/getAllCustomersRewardPoints`
   
   Returns the reward points for all customers per month and total .
   
   E.g. /api/getAllCustomersRewardPoints
   
   ```json
   {
     "customerId": 1,
     "customerName": "Alice",
     "totalRewardPoints": 365,
     "monthlySummary": [
       {
         "month": "SEPTEMBER",
         "totalAmountSpent": 120,
         "rewardPoints": 90
       },
       {
         "month": "OCTOBER",
         "totalAmountSpent": 75,
         "rewardPoints": 25
       },
       {
         "month": "NOVEMBER",
         "totalAmountSpent": 200,
         "rewardPoints": 250
       }
     ]
   },
   {
     "customerId": 2,
     "customerName": "Bob",
     "totalRewardPoints": 240,
     "monthlySummary": [
       {
         "month": "SEPTEMBER",
         "totalAmountSpent": 130,
         "rewardPoints": 110
       },
       {
         "month": "OCTOBER",
         "totalAmountSpent": 120,
         "rewardPoints": 90
       },
       {
         "month": "NOVEMBER",
         "totalAmountSpent": 90,
         "rewardPoints": 40
       }
     ]
   },
   {
     "customerId": 3,
     "customerName": "Carlos",
     "totalRewardPoints": 2167,
     "monthlySummary": [
       {
         "month": "SEPTEMBER",
         "totalAmountSpent": 786.99,
         "rewardPoints": 1422
       },
       {
         "month": "OCTOBER",
         "totalAmountSpent": 439.29,
         "rewardPoints": 728
       },
       {
         "month": "NOVEMBER",
         "totalAmountSpent": 67.99,
         "rewardPoints": 17
       }
     ]
   }
   ```
  
3. **GET** `/api/getTotalRewardPoints/{customerId}`
   
   Returns the complete statement of a given customer with month-wise spends and reward points received.
   
   E.g. /api/getTotalRewardPoints/1
   
   ```json
   365
   ```
