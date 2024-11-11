# Identity Interview Exercise

This repository contains an Interview exercise given by Identity E2E Group. A Java Automation project that includes UI test automation using Cucumber and Playwright and Java. 
It demonstrates how to automate user management tasks using REST API and test web pages with challenging and dynamic elements.

# Observation:

* the input files had a invalid car registration which required handling.


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
   git clone (https://github.com/Praneet-Srivastav/IdentityInterviewExercise.git)
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
Run the tests using the provided runner TestRunner
Execute tests using Maven:
```bash
mvn clean test
```

The test runner is configured to execute all scenarios in the feature files. Test reports will be generated in the `target` directory.

## Feature Description
The framework validates car details by:
1. Reading car registration numbers from an input file
2. Fetching actual car details from a valuation website
3. Comparing the retrieved details against expected output
4. Handling invalid car registrations gracefully

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

