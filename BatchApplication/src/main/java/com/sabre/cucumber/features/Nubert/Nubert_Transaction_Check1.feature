@Nubert_Transaction_Check_1
Feature:Nubert Transaction Validation
  This feature contains test scenarios for Nubert Transaction Check

  @NubertPNRCheckHappyPath_1
  Scenario Outline: Verify that transactions are visible on the basis of PNR
    Given  User opens the Nubert "<Environment>" application
    Then Verify that URL got opened successfully
    And User enters username and password on Nubert loginpage
    And Verify that login is successful and Search button got displayed
    Then Display the stored data from "test" file
    Then Select the Search Criteria
      | From Date | <FDate>        |
      | To Date   | <TDate>        |
      | Txn ID    | <Sabre_Txn_ID> |
      | Merch     | <Merchant>     |
      | Supplier  | <Supplier>     |
      | Action    | <SearchAction> |
      | Channel   | <Channel>      |
      | PNR       | <PNR>          |
      | Service   | <Service_Name> |
      | Status    | <Txn_Status>   |
    And Click on Search button in Nubert
    Then Display all values
    Then Verify the result displayed
      | PNR    | <PNR>        |
      | Status | <Txn_Status> |

    Examples:
      | Environment      | FDate               | TDate               | Sabre_Txn_ID           | Merchant | Supplier | SearchAction | Channel | PNR    | Service_Name | Txn_Status|
      | GCPIntegration   | 2023-10-02 05:00:00 | 2023-10-03 06:55:30 |  01521696319730696414  | X0       |          | ConfirmAuth  |         | KUKYYO | Payment      | Success   |