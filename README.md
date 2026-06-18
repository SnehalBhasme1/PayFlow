# PayFlow Payment Processing System

## Overview

PayFlow is a payment processing backend application built using Spring Boot and PostgreSQL. The system simulates a digital wallet platform where users can maintain wallet balances, make payments to merchants, request refunds, and track transactions.

The primary goal of this project is to demonstrate backend engineering concepts such as transaction management, idempotency handling, layered architecture, and relational database design.

---

## Tech Stack

* Java 17
* Spring Boot
* Spring Data JPA (Hibernate)
* PostgreSQL
* Maven
* Lombok
* Postman

---

## Architecture

The project follows a layered architecture:

Controller → Service → Repository → Database

### Why this structure?

* Controllers handle HTTP requests and responses.
* Services contain business logic.
* Repositories handle database access.
* Separation of concerns improves maintainability and testability.

---

## Features

### User Management

* User Registration
* User Repository Integration

### Wallet Management

* Automatic wallet creation during user registration
* Wallet top-up functionality
* Wallet balance tracking
* Wallet transaction history

### Payment Processing

* Payment creation
* Payment status tracking
* Payment lifecycle management

### Idempotency Handling

One of the key requirements of payment systems is preventing duplicate transactions.

The application stores and validates idempotency keys so that repeated requests return the original payment instead of creating duplicate payments.

### Refund Management

* Refund request creation
* Full refund support
* Partial refund support
* Refund approval and rejection workflow
* Automatic wallet credit after approval

### Merchant Management

* Merchant creation
* Merchant information retrieval
* Payment tracking

---

## Design Decisions

### Why PostgreSQL?

PostgreSQL was selected because it was specified in the assignment and provides strong transactional support suitable for financial systems.

### Why BigDecimal?

Monetary values are stored using BigDecimal to avoid precision issues associated with floating-point calculations.

### Why Idempotency?

Network retries can cause duplicate payment requests. Idempotency ensures that the same payment is processed only once.

### Why Spring Data JPA?

Spring Data JPA reduces boilerplate code and simplifies database interaction while maintaining clean repository patterns.

---

## Database Tables

* users
* wallets
* wallet_transactions
* payments
* refunds
* merchants

---

## Assumptions

* Each user owns exactly one wallet.
* Wallet currency is INR.
* Refunds are allowed only for successful payments.
* Refund approval credits money back to the user's wallet.
* Merchant settlement is simulated within the application.

---

## Running the Application

### Create Database

```sql
CREATE DATABASE payflow;
```

### Configure application.properties

Update:

* PostgreSQL username
* PostgreSQL password
* Database URL

### Build Project

```bash
mvn clean install
```

### Run Application

```bash
mvn spring-boot:run
```

---

## API Testing

A Postman collection is included in the repository.

Import:

PayFlow.postman_collection.json

into Postman and test all endpoints directly.

---

## Challenges Faced

* Learning PostgreSQL setup and configuration
* Designing idempotency handling
* Managing payment and refund workflows
* Maintaining data consistency across wallet and payment operations

---

## Resources Used

* Spring Boot Documentation
* PostgreSQL Documentation
* Baeldung Articles
* Stack Overflow (for debugging and troubleshooting)

---

## Author

Snehal Bhasme
