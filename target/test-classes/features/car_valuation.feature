# Car Valuation Feature
# This feature validates car details by comparing information from a valuation website
# against expected data stored in test files.

Feature: Verify Car Valuation
  As a user of the car valuation system
  I want to verify car details from multiple sources
  So that I can ensure the accuracy of the valuation data

  @done
  Scenario Outline: Verify car details from input file against valuation website
    # Load test data from files
    Given I get car registration numbers from "<InputFile>"
    And I have expected car details from "<OutputFile>"
    # Process each registration and validate details
    When I check each car's details on the valuation website
    # Verify all validations were successful
    Then the car details should match the expected output

    Examples:
      | InputFile         | OutputFile         | Description                    |
      | car_input_V4.txt | car_output_V4.txt  | Validate multiple car details |
