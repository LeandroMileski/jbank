# JBank - Banking System

[![Java](https://img.shields.io/badge/Java-17+-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-3.x-green.svg)](https://spring.io/projects/spring-data-jpa)
[![Hibernate](https://img.shields.io/badge/Hibernate-6.x-blue.svg)](https://hibernate.org/)

A comprehensive banking system built with Spring Boot that provides secure wallet management, transaction processing, and comprehensive audit logging. This project demonstrates enterprise-level backend development practices and financial transaction handling.

## 🚀 Features

### Core Banking Operations
- **Wallet Management**: Create and close bank wallets with complete validation
- **Deposit Processing**: Secure money deposits with transaction history tracking
- **Money Transfers**: Inter-wallet transfers with balance verification and ACID compliance
- **Statement Generation**: Detailed transaction history with timestamps and IP tracking

### Security & Audit
- **IP Tracking Filter**: Captures and logs user IP addresses for security audit
- **Request/Response Interceptor**: Comprehensive logging of all API interactions
- **Transaction Auditing**: Complete audit trail for all banking operations
- **ACID Compliance**: Ensures data consistency for concurrent transactions

## 🏗️ Architecture

### Database Design
The system implements a robust relational database structure:

**Wallet Entity**
- UUID-based account identification
- Unique CPF and email constraints
- Real-time balance tracking
- Account holder information management

**Transfer Entity**
- Complete transfer transaction records
- Source and destination wallet references
- Timestamp and amount tracking
- Many-to-one relationships for audit trails

**Deposit Entity**
- Deposit transaction logging
- IP address tracking for security
- Wallet destination references
- Comprehensive transaction metadata

### Technology Stack
- **Backend Framework**: Spring Boot 3.x
- **Data Access**: Spring Data JPA with Hibernate ORM
- **Validation**: Hibernate Validator for request validation
- **Database**: JPA-compatible database (PostgreSQL/MySQL)
- **Architecture Pattern**: RESTful API with MVC pattern

## 🔧 Technical Implementation

### Business Logic & Validation
- **Sufficient Balance Verification**: Pre-transaction balance checks
- **Account Closure Rules**: Zero-balance requirement validation
- **Concurrent Transaction Handling**: Thread-safe operations with transaction management
- **Data Integrity**: UUID-based primary keys and unique constraints

### Logging & Monitoring
- **Request Filtering**: Custom filter implementation for IP capture
- **Audit Interceptors**: Comprehensive request/response logging
- **Transaction Logging**: Detailed audit trails for financial operations
- **Security Headers**: IP address inclusion in response headers

### API Design
RESTful endpoints following industry standards:
- `POST /wallets` - Create new wallet
- `DELETE /wallets/{id}` - Close wallet (zero balance required)
- `POST /deposits` - Process deposit transactions
- `POST /transfers` - Execute inter-wallet transfers
- `GET /wallets/{id}/statement` - Generate transaction history

## 💼 Professional Skills Demonstrated

### Backend Development
- **Spring Boot Ecosystem**: Advanced configuration and dependency management
- **JPA/Hibernate**: Complex entity relationships and transaction management
- **RESTful API Design**: Industry-standard endpoint design and HTTP methods
- **Data Validation**: Comprehensive input validation and business rule enforcement

### Financial Technology
- **Banking Operations**: Real-world banking transaction processing
- **ACID Transactions**: Database consistency and concurrent operation handling
- **Audit Requirements**: Financial industry compliance and logging standards
- **Security Practices**: IP tracking and request monitoring

### Software Engineering
- **Clean Architecture**: Separation of concerns and maintainable code structure
- **Error Handling**: Robust exception management and user feedback
- **Performance**: Optimized database queries and transaction processing
- **Testing**: Unit and integration testing for financial operations

## 🎯 Business Value

This project showcases the ability to build enterprise-grade financial applications with:
- **Regulatory Compliance**: Comprehensive audit logging for financial regulations
- **Scalability**: Designed to handle concurrent banking operations
- **Security**: IP tracking and request monitoring for fraud prevention
- **Reliability**: ACID transactions ensure data integrity and consistency

## 🛠️ Setup & Installation

```bash
# Clone the repository
git clone https://github.com/LeandroMileski/jbank.git

# Navigate to project directory
cd jbank

# Run with Maven
./mvnw spring-boot:run

# Or build and run JAR
./mvnw clean package
java -jar target/jbank-1.0.0.jar
```

## 📊 Database Schema

The application uses a normalized database schema optimized for:
- **Data Integrity**: Foreign key constraints and unique indexes
- **Performance**: Optimized queries for balance calculations and statement generation
- **Audit Trail**: Complete transaction history with metadata
- **Scalability**: UUID-based primary keys for distributed systems

## 🔍 Key Learning Outcomes

- **Spring Boot Mastery**: Advanced framework configuration and best practices
- **Financial System Design**: Understanding of banking operation requirements
- **Security Implementation**: Audit logging and IP tracking mechanisms
- **Database Design**: Relational modeling for financial transactions
- **API Development**: RESTful service design and implementation
- **Transaction Management**: ACID compliance and concurrent operation handling

---

*This project demonstrates comprehensive backend development skills suitable for financial technology and enterprise applications. The implementation follows industry best practices for security, auditing, and data consistency.*
