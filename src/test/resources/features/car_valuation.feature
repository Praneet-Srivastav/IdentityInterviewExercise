Feature: Verify Car Valuation

  @done
  Scenario Outline: Verify car details from input file against valuation website
    Given I get car registration numbers from "<InputFile>"
    And I have expected car details from "<OutputFile>"
    When I check each car's details on the valuation website
    Then the car details should match the expected output
    Examples:
      |InputFile       |OutputFile       |
      |car_input_V4.txt|car_output_V4.txt|