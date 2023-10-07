@Nubert_Transaction_Check_1
Feature: Test File

  Scenario Outline: Test Feature Scenario
    Given Test File
    When Tested the feature
    Then Test is passed
    Then Store response in a "<filename>" file
    Then Display the stored data from "<filename>" file

   Examples:
    |filename|
    |test |