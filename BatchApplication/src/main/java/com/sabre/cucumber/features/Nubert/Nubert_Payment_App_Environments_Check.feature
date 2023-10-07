@Nubert_Environment_Check
Feature:Nubert Environment Login
  This feature contains test scenarios for Nubert Environment Login Check

  @NubertHappyPath
  Scenario Outline: Do authorisation for Nubert login
    Given  User opens the Nubert "<Environment>" application
    Then Verify that URL got opened successfully
    And User enters username and password on Nubert loginpage
    And Verify that login is successful and Search button got displayed

    Examples:
      | Environment      |
      | GCPCertification |

  @NubertValidateTulsaURLPaymentAppHappyPath
  Scenario Outline: Verify navigation to different Nubert environment is working
    Given  User opens the Nubert "<Environment>" application
    Then Verify that URL got opened successfully
    And User enters username and password on Nubert loginpage
    And Verify that login is successful and Search button got displayed
    Then Click on Payment Apps option
    And Click on Nubert dropdown option
    Then Click on Nubert Tulsa "<PaymentAppEnvironment>" environment option
    And Navigate to newly opened tab
    And Verify that Nubert Tulsa "<PaymentAppEnvironment>" URL got opened successfully
    And User enters username and password on Nubert loginpage

    Examples:
      | Environment      | PaymentAppEnvironment |
      | GCPCertification | int                   |
     # | Certification |         int         |
     # | Integration   |         cert        |
     # | Certification |         prod        |

  @NubertValidateGCPURLPaymentAppHappyPath
  Scenario Outline: Verify navigation to different Nubert GCP environment is working
    Given  User opens the Nubert "<Environment>" application
    Then Verify that URL got opened successfully
    And User enters username and password on Nubert loginpage
    And Verify that login is successful and Search button got displayed
    Then Click on Payment Apps option
    And Click on NubertGCP dropdown option
    Then Click on Nubert GCP "<PaymentAppEnvironment>" environment option
    And Navigate to newly opened tab
    And Verify that Nubert GCP "<PaymentAppEnvironment>" URL got opened successfully
    And User enters username and password on Nubert loginpage

    Examples:

      | Environment      | PaymentAppEnvironment |
      | GCPCertification | int                   |