# Banking Application

The Banking Application is a comprehensive solution designed to enable users to manage their banking transactions effectively. It allows users to register, and once registered, their data such as transaction, account, loan, and role information is securely stored. Users can perform various operations like creating, updating, deleting, and retrieving these records.

## Features

- **User Registration**: Users can create accounts and manage their profiles.
- **Transaction Management**: Users can perform various banking transactions such as deposits, withdrawals, and transfers.
- **Account Management**: Manage user accounts, including balance updates and account details.
- **Loan Management**: Users can view available loan options, apply for loans, and check loan status.
- **Role-based Access Control**: Different roles (e.g., User, Admin) are provided with different levels of access and permissions.

## Entity Relationship Diagram (ERD)

The following is the Entity Relationship diagram illustrating the relationships between the system's models:

![ERD](https://github.com/user-attachments/assets/ada0592e-41d3-4b4b-b49f-4fcad35cd2be)

The system contains six main models:

- **User**
- **Account**
- **Transaction**
- **Loan**
- **Role**
- **Refresh Token**

### Relationships Between Models

- **User & Account**: One-to-Many relationship (A user can have multiple accounts).
- **Account & Transaction**: One-to-Many relationship (An account can have multiple transactions).
- **User & Loan**: One-to-Many relationship (A user can have multiple loans).
- **User & Role**: Many-to-One relationship (One role can be associated with multiple users).

## Technologies Used

- **Backend**: Java with Spring Boot
- **Database**: MySQL
- **Security**: JWT (JSON Web Tokens) for authentication
- **Testing**: JUnit
- **Swagger**: Used for API documentation and testing interface

## API Endpoints

The application provides the following API endpoints:

### User:

- `POST /api/users/save` – Creates a new user.
- `GET /api/users` – Retrieves details of all users.
- `GET /api/users/{id}` – Retrieves details of a specific user.
- `PUT /api/users/{id}` – Updates user information.
- `DELETE /api/users/{id}` – Deletes a specific user.

### Account:

- `POST /api/accounts` – Creates a new account.
- `GET /api/accounts/{id}` – Retrieves details of a specific account.
- `GET /api/accounts` – Retrieves details of all accounts.
- `PUT /api/accounts/{id}` – Updates account information.
- `DELETE /api/accounts/{id}` – Deletes a specific account.

### Transaction:

- `POST /api/transactions` – Creates a new transaction.
- `GET /api/transactions/{id}` – Retrieves details of a specific transaction.
- `GET /api/transactions` – Retrieves details of all transactions.
- `PUT /api/transactions/{id}` – Updates transaction details.
- `DELETE /api/transactions/{id}` – Deletes a specific transaction.

### Loan:

- `POST /api/loans` – Creates a new loan application.
- `GET /api/loans/{id}` – Retrieves details of a specific loan.
- `GET /api/loans` – Retrieves details of all loans.
- `PUT /api/loans/{id}` – Updates loan information.
- `DELETE /api/loans/{id}` – Deletes a specific loan.

### Role:

- `POST /api/roles` – Creates a new role.
- `GET /api/roles/{id}` – Retrieves a specific role.
- `GET /api/roles` – Retrieves all roles.
- `PUT /api/roles/{id}` – Updates role information.
- `DELETE /api/roles/{id}` – Deletes a specific role.

## Authentication and Security

The application uses JWT (JSON Web Tokens) for secure authentication and authorization. The flow is as follows:

- **User Login**: Users log in by providing their credentials (e.g., username and password). Upon successful login, a JWT token is generated and returned to the client.
- **Token Usage**: Two types of tokens are created: an access token and a refresh token. The access token is valid for 2 hours, while the refresh token is valid for 4 hours. If the refresh token expires, the user is logged out.
- **Protected Endpoints**: Endpoints requiring authentication validate the JWT token before processing the request.

## Swagger API Documentation

The application uses Swagger to provide API documentation and a test interface. Swagger allows you to explore all the available endpoints and directly test them.

## Pagination and Sorting

The application supports pagination and sorting for endpoints that return lists of data (e.g., users, accounts, transactions, loans). This feature improves performance and user experience when working with large datasets.

### Pagination

Pagination allows you to fetch data in smaller chunks (pages) instead of loading the entire dataset at once. The following query parameters are used for pagination:

- **page**: Specifies the page number (default value is 1).
- **limit**: Specifies the number of items per page (default value is 10).

### Sorting

Sorting allows you to order the results based on specific fields. The following query parameters are used for sorting:

- **sortBy**: Specifies the field to sort by (e.g., `createdAt`, `name`).
- **order**: Specifies the sorting order (`asc` for ascending, `desc` for descending).
