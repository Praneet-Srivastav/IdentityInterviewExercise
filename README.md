# Identity Interview Exercise

## Overview
This project is an automated testing framework for car valuation verification. It demonstrates the implementation of UI test automation using Playwright with Java and Cucumber for BDD (Behavior Driven Development). The framework automates the process of validating car details against a valuation website.

## Technologies
- Java 11
- Playwright 1.41.0 (Web Automation)
- Cucumber 7.14.0 (BDD Framework)
- JUnit 4.13.2 (Testing Framework)
- Maven (Build Tool)

## Project Structure
```
src/
├── main/java/com/identity/
│   ├── model/
│   │   └── CarDetails.java         # Car details data model
│   ├── pages/
│   │   └── CarValuationPage.java   # Page object for car valuation website
│   └── utils/
│       └── FileParser.java         # Utility for parsing input/output files
└── test/
    ├── java/com/identity/
    │   ├── hooks/
    │   │   └── Hooks.java          # Test setup and teardown
    │   ├── runners/
    │   │   └── TestRunner.java     # Cucumber test runner
    │   └── steps/
    │       └── CarValuationSteps.java  # Step definitions
    └── resources/
        ├── features/
        │   └── car_valuation.feature   # Test scenarios
        └── testdata/
            ├── car_input_V4.txt        # Input test data
            └── car_output_V4.txt       # Expected output data
```

## Prerequisites
- Java JDK 11 or higher
- Maven 3.x
- Git

## Setup Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/Praneet-Srivastav/IdentityInterviewExercise.git
   ```
2. Navigate to project directory:
   ```bash
   cd IdentityInterviewExercise
   ```
3. Install dependencies:
   ```bash
   mvn clean install
   ```

## Running Tests
Execute tests using Maven:
```bash
mvn clean test
```

The test runner is configured to execute all scenarios tag as @done in the feature files. Test reports will be generated in the `target` directory.

## Feature Description
The framework validates car details by:
1. Reading car registration numbers from an input file
2. Fetching actual car details from a valuation website
3. Comparing the retrieved details against expected output
4. Handling invalid car registrations gracefully

### Test Execution Results
Latest test execution successfully validated:
- KT17DLX (2017 SKODA SUPERB DIESEL ESTATE)
- AD58VNF (2008 BMW 1 SERIES DIESEL COUPE)
- Correctly identified non-existent registrations (BW57BOW, SG18HTN)
- All assertions passed with 100% success rate
- Total execution time: ~90 seconds

### Reliability Features
The framework includes several reliability improvements:
- Configurable timeouts (DEFAULT_TIMEOUT: 5s, ERROR_WAIT_TIMEOUT: 10s)
- Network state monitoring with waitForLoadState
- Robust error handling for both found and not-found scenarios
- Cookie consent handling
- Random mileage generation within realistic ranges

## Test Data
- Input data: `src/test/resources/testdata/car_input_V4.txt`
- Expected output: `src/test/resources/testdata/car_output_V4.txt`

## Configuration
- Maven configuration in `pom.xml`
- Cucumber properties in `src/test/resources/cucumber.properties`
- Test configuration in `src/test/resources/config.properties`

## Reports
Test execution reports are generated in HTML format and can be found at:
```
target/cucumber.html
```

## Car Validation Process
The framework follows these steps for each registration:
1. Navigates to the valuation website
2. Handles any cookie consent prompts
3. Enters the registration number
4. Provides a random mileage between 10,000 and 60,000
5. Submits the form and waits for response
6. Validates the returned car details against expected data:
   - Registration number
   - Make
   - Model
   - Year
7. Handles cases where the car is not found with appropriate logging
