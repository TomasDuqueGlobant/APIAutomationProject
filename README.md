# API Testing project from TAE Academy

## By Tomás Duque

## Project Description

This project is an automated testing framework aimed at validating REST API endpoints. It leverages Java with RestAssured, Cucumber, and JUnit for creating and executing test cases. The framework supports various HTTP methods, making it flexible and adaptable for testing diverse APIs.

## Technologies Used

- Java 8
- Maven
- RestAssured 5.3.1
- Cucumber 7.13.0
- JUnit 5.10.0
- Lombok 1.18.28
- Gson 2.10.1
- Log4j 2.20.0

## Requirements

- Java JDK 8 or higher
- Maven 3.6.3 or higher

## Setup and Installation

To set up the project on your local machine, follow these steps:

1. Clone the repository:
```cmd  
git clone https://github.com/TomasDuqueGlobant/APIAutomationProject.git
```

2. Navigate to the project directory:
```cmd  
cd APIAutomationProject
```

3. Use Maven to compile the project and install dependencies:
```cmd  
mvn clean install
```

## How to Run the Tests

Execute all tests by running the following command in the terminal:
```cmd  
mvn test
```

## Project Structure

- `src/main/java`: Contains core functionality and utilities for the testing framework as Client and Resource structure, Request etc.
- `src/test/java`: Hosts the test cases, step definitions, and supporting code.
- `src/test/resources`: Includes Cucumber feature files and other test-related resources.
```
APIAutomationProject

└── src
    ├── main
    │   ├── java
    │   │   └── com.testing.api
    │   │       ├── models
    │   │       │   ├── Client.java
    │   │       │   └── Resource.java
    │   │       ├── requests
    │   │       │   ├── BaseRequest.java
    │   │       │   ├── ClientRequest.java
    │   │       │   └── ResourceRequest.java
    │   │       └── utils
    │   │           ├── Constants.java
    │   │           └── JsonFileReader.java
    │   └── resources
    │       └── data
    │           ├── defaultClient.json
    │           └── defaultResource.json
    └── test
        ├── java
        │   └── com.testing.api
        │       ├── runner
        │       │   └── TestRunner.java
        │       └── stepDefinitions
        │           ├── ClientSteps.java
        │           ├── CommonSteps.java
        │           ├── Hooks.java
        │           └── ResourceSteps.java
        └── resources
            ├── features
            │   ├── client_testing_crud.feature
            │   └── resource_testing_crud.feature
            └── schemas
                ├── clientListSchema.json
                ├── clientSchema.json
                ├── resourceListSchema.json
                └── resourceSchema.json
```
