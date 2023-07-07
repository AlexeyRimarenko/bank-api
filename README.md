# Bank API

This is a Java-based API for a fake financial institution. It allows bank employees to perform basic banking operations such as creating bank accounts, transferring funds between accounts, and retrieving account balances and transfer history.

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- H2 Database (in-memory database for simplicity)
- Maven (for dependency management)
- JUnit (for testing)

## Getting Started

To run this API locally, follow these steps:

1. Clone the repository:

   ```bash
   git clone https://github.com/pro335/bank-api.git
    ```

2. Navigate to the project directory:

    ```bash
    cd bank-api
    ```

3. Build the project using Maven:

    ```bash
    mvn clean install
    ```

4. Run the application:

    ```bash
    mvn spring-boot:run
    ```

The API should now be running locally on http://localhost:8080.

## API Documentation

The API provides the following endpoints:

- <b>`POST /customers`</b>: Creates a new customer.

- <b>`POST /accounts`</b>: Creates a new bank account for a customer with an initial deposit amount.

- <b>`POST /transfers`</b>: Transfers a specified amount between two bank accounts.

- <b>`GET /accounts/{accountId}/balance`</b>: Retrieves the balance of a given bank account.

- <b>`GET /accounts/{accountId}/transfers`</b>: Retrieves the transfer history of a given bank account.


For detailed information about the request and response formats of these endpoints, please refer to the API documentation.

## Testing

The project includes unit tests to ensure the correctness of the business logic. To run the tests, use the following command:

```bash
mvn test
```

## Database Configuration
The project uses an in-memory H2 database for simplicity. The database configuration can be found in the application.properties file. By default, the application will create the necessary tables on startup and populate them with sample data.

## Future Improvements
Here are some possible improvements that can be made to the API:

Implement authentication and authorization mechanisms to secure the API endpoints.
Add more validation and error handling to the API for better user experience.
Implement pagination and filtering options for retrieving transfer history.
Support additional features such as account closure and account statements.
Feel free to explore and enhance the API based on your requirements and needs.