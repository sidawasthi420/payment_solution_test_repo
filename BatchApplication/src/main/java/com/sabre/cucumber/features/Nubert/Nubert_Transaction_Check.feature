@Nubert_Transaction_Check
Feature:Nubert Transaction Validation
  This feature contains test scenarios for Nubert Transaction Check

  @NubertTransactionCheckHappyPath
  Scenario Outline: Verify that transactions are visible on the basis of Transaction ID
    Given  User opens the Nubert "<Environment>" application
    Then Verify that URL got opened successfully
    And User enters username and password on Nubert loginpage
    And Verify that login is successful and Search button got displayed
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
    Then Verify the result displayed
      | Txn ID | <Sabre_Txn_ID> |
      | Status | <Txn_Status>   |

    Examples:
      | Environment    | FDate               | TDate               | Sabre_Txn_ID         | Merchant | Supplier | SearchAction            | Channel | PNR | Service_Name | Txn_Status |
      | GCPIntegration | 2022-11-24 00:00:00 | 2022-11-26 11:50:16 | 06051669357389530955 | B6       | PWS      | 3DS2FingerPrintResponse | WEB     |     | Ipe          | Success    |

  @NubertPNRCheckHappyPath
  Scenario Outline: Verify that transactions are visible on the basis of PNR
    Given  User opens the Nubert "<Environment>" application
    Then Verify that URL got opened successfully
    And User enters username and password on Nubert loginpage
    And Verify that login is successful and Search button got displayed
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
      | Environment      | FDate               | TDate               | Sabre_Txn_ID | Merchant | Supplier | SearchAction | Channel | PNR    | Service_Name | Txn_Status |
      | GCPCertification | 2022-11-24 00:00:00 | 2022-11-26 11:50:16 |              | LA       |          | ConfirmAuth  |         | ZWVATJ | Payment      | Success    |

  @NubertActionSubActionCheckHappyPath
  Scenario Outline: Verify that transactions are visible on the basis of Transaction ID
    Given  User opens the Nubert "<Environment>" application
    Then Verify that URL got opened successfully
    And User enters username and password on Nubert loginpage
    And Verify that login is successful and Search button got displayed
    Then Select the Search Criteria
      | From Date | <FDate>           |
      | To Date   | <TDate>           |
      | Txn ID    | <Sabre_Txn_ID>    |
      | Merch     | <Merchant>        |
      | Supplier  | <Supplier>        |
      | Action    | <SearchAction>    |
      | SubAction | <SearchSubAction> |
      | Channel   | <Channel>         |
      | PNR       | <PNR>             |
      | Service   | <Service_Name>    |
      | Status    | <Txn_Status>      |
    And Click on Search button in Nubert
    Then Verify the result displayed
      | Action/SubAction | <Action>     |
      | Status           | <Txn_Status> |

    Examples:
      | Environment      | FDate               | TDate               | Sabre_Txn_ID | Merchant | Supplier | Action               | SearchAction | SearchSubAction | Channel | PNR    | Service_Name | Txn_Status |
      | GCPCertification | 2022-11-24 00:00:00 | 2022-11-26 11:50:16 |              | LA       |          | ConfirmAuth/Issuance | ConfirmAuth  | Issuance        |         | ZWVATJ | Payment      | Success    |