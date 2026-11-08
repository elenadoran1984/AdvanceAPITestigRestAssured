# Advanced API Testing with REST Assured

## About the Project

This project demonstrates API automation testing using **Java, REST Assured, and TestNG**.

The purpose of this project is to validate REST API responses through different testing approaches, focusing on response deserialization, data validation, and assertion strategies.

The project uses the Zippopotam.us API to verify response data, including country information, postal codes, and geographical details.

## Technologies & Tools

- **Java 21** – Programming language
- **Maven** – Build and dependency management
- **REST Assured 6.3.0** – REST API automation testing
- **TestNG 7.11.0** – Test execution and assertions
- **Jackson Databind 2.22.4** – JSON deserialization
- **Hamcrest** – Expressive assertions

## Testing Approaches

The project demonstrates different approaches to API response validation:

### 1. Deserialization-Based Response Validation

- Deserialize JSON responses into Java objects (POJOs).
- Validate response fields using Java object getters.
- Verify country information, postal codes, place names, geographical coordinates, and state information.
- Validate that the response contains a non-empty collection of places.

### 2. Hamcrest-Based Assertions

- Use Hamcrest matchers to validate expected response data.
- Verify that expected place names are present in the response.
- Validate collection size.
- Verify that unexpected values are not present.

## API Testing Techniques

The project demonstrates different REST Assured API testing techniques using the Zippopotam.us API.

### 1. Request and Response Specifications

- Create request specifications using `RequestSpecBuilder`.
- Configure the base URI.
- Create response specifications using `ResponseSpecBuilder`.
- Validate HTTP status codes and response content types.
- Reuse specifications where applicable to keep test configuration consistent.

### 2. REST API Response Validation

- Validate HTTP status codes.
- Validate response content types.
- Verify JSON response fields using REST Assured's `body()` method.
- Use Hamcrest matchers for expressive assertions.
- Validate nested JSON objects and arrays.
- Verify collection contents and collection sizes.

### 3. Response Logging

- Log outgoing request details.
- Log response bodies for easier debugging and troubleshooting.

### 4. Response Extraction and JSON Validation

- Extract response data using REST Assured's `Response` interface.
- Retrieve individual values using `path()`.
- Extract collections using `jsonPath().getList()`.
- Validate extracted values using TestNG assertions.

### 5. Data-Driven Testing

- Use TestNG `@DataProvider` to execute the same test with multiple input combinations.
- Parameterize country codes, state codes, city names, and expected place names.
- Reduce test duplication and improve test maintainability.

### 6. API Scenarios Covered

- Retrieve location information using country and postal code.
- Retrieve location information using country, state, and city.
- Validate country, state, city, and postal code information.
- Validate geographical coordinates (latitude and longitude).
- Verify the number and contents of returned locations.
- Execute the same validation scenario against multiple cities using parameterized tests.

## Test Coverage

The current test scenarios focus on:

- Retrieving location information using a country abbreviation and postal code.
- Validating country name and country abbreviation.
- Validating postal code information.
- Verifying place names.
- Validating longitude and latitude.
- Validating state names and state abbreviations.
- Checking the number of returned locations.

## How to Run the Tests

**Prerequisites:**

- Java 21 or higher
- Maven installed
- Git installed

**Clone the repository:**

```bash
git clone https://github.com/elenadoran1984/AdvanceAPITestigRestAssured.git
```

**Navigate to the project directory:**

```bash
cd AdvanceAPITestigRestAssured
```

**Execute the tests:**

```bash
mvn clean test
```

## Project Goals

This project is part of my continuous learning journey in API automation testing. It focuses on developing practical skills in REST API validation, Java-based response handling, and writing maintainable automated tests using industry-standard tools.