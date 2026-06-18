# PayFlow Payment Processing System

## Overview

PayFlow is a payment processing backend application built using Spring Boot and PostgreSQL. It provides wallet management, payment processing, idempotency handling, refund management, and merchant management functionalities.

## Tech Stack

* Java
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Maven

## Features

### User Management

* User Registration
* User Repository Integration

### Wallet Management

* Auto Wallet Creation
* Wallet Top-Up
* Wallet Balance Tracking
* Wallet Transaction History

### Payment Processing

* Payment Creation
* Payment Tracking
* Payment Status Management

### Idempotency Handling

* Duplicate Payment Prevention
* Same Request Returns Existing Payment

### Refund Management

* Refund Request
* Refund Approval
* Refund Rejection
* Full and Partial Refund Support
* Wallet Credit Back on Refund Approval

### Merchant Management

* Create Merchant
* View Merchant Details

## Database Tables

* users
* wallets
* wallet_transactions
* payments
* refunds
* merchants

## Running the Application

1. Create PostgreSQL database:

CREATE DATABASE payflow;

2. Configure application.properties

3. Run:

mvn clean install

mvn spring-boot:run

## API Testing

The project APIs can be tested using the included Postman Collection.

## Author

Snehal Bhasme
