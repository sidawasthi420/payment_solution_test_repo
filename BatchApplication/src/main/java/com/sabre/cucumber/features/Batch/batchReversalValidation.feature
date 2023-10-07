@live @payment @complete @Regression @BatchJob
Feature: Batch Job Testing

  Scenario: Nubert Batch Reversal Verification
    Given  User opens the Nubert "GCPIntegration" application
    And Verify that URL got opened successfully
    And User enters username and password on Nubert loginpage
    And Verify that login is successful and Search button got displayed
    Then Select the Criteria for Search from file "batchReversal"
      | PNR       | PNR                 |
      | Action    | CancelAuth          |
      | SubAction | Batch               |
      | From Date | FromDate            |
    And Click on Search button in Nubert
    Then Verify the displayed result from file "batchReversal"
      | From Date           | FromDate           |
      | Status              | Success            |
      | PNR                 | PNR                |
      | Action/SubAction    | CancelAuth/Batch   |