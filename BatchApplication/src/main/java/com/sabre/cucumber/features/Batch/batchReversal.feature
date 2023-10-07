@live @payment @complete @Regression @BatchJob
Feature: Batch Job Testing

  Background:
    Given Environment is "Int"
    Given Service is "Payment"
    Given Mode is "Live"
    Given Initialize
    Given Set connection debugging "on"
    Given Set assertion failure delay "on"

  Scenario Outline: Batch
    Given connect to Database
    And fetch data from DB
      | TABLENAME     |  SUPPLIERID   | POSCOUNTRYCODE |  FOPCODE    |
      | <DBTableName> | <Supplier_ID> | <ISO_Country>  | <Card_Code> |
    Then disconnect from the DB
    Then Define variable "ISO_Country" as "<ISO_Country>"
    Then Define variable "Merchant_ID" as "<Merchant_ID>"
    Then Define variable "Currency_Code" as "<Currency_Code>"
    Then Define variable "FOP_Type" as "<FOP_Type>"
    Then Define variable "Card_Code" as "<Card_Code>"
    Then Define variable "Card_Number" as "<Card_Number>"
    Then Define variable "PIN_" as "<PIN_>"
    Then Define variable "Card_Security_Code" as "<Card_Security_Code>"
    Then Define variable "Card_Holder_Name" as "<Card_Holder_Name>"
    Then Define variable "Country_Code" as "<Country_Code>"
    Then Define variable "Amount_Detail" as "<Amount_Detail>"
    Then Define variable "Currency_Code" as "<Currency_Code>"
    Then Define variable "Approval_Code" as "<Approval_Code>"
    Then Define variable "CSCResultCode" as "<CSCResultCode>"
    Then Define variable "CSCRemarks" as "<CSCRemarks>"
    Then Define variable "AVSResultCode" as "<AVSResultCode>"
    Then Define variable "AVSRemarks" as "<AVSRemarks>"
    Then Define variable "Auth_Remarks1" as "<Auth_Remarks1>"
    Then Define variable "Auth_Remarks2" as "<Auth_Remarks2>"

    Given Define variable "SabreTrxID" as "=~/^[0-9]{20}$/"
    Given Substitute variables in "SabreTrxID"
    Given Define variable "SysDateTime" as "=~/^\d{4}-\d{2}-\d{2}[T]\d{2}:\d{2}:\d{2}/"
    Given Substitute variables in "SysDateTime"
    Given Define variable "Version" as "1.1.0"
    Given Substitute variables in "Version"
    Given Define variable "Result_Code" as "SUCCESS"
    Given Substitute variables in "Result_Code"
    Given Define variable "Result_Description" as "Successful Transaction"
    Given Substitute variables in "Result_Description"
    Given Define variable "Response_Code" as "APPROVED"
    Given Substitute variables in "Response_Code"
    Given Define variable "Supplier_ID" as "WPAY"
    Given Substitute variables in "Supplier_ID"
    Given Define variable "SupplierTrans_ID" as "=~/^[0-9A-Z]+/"
    Given Substitute variables in "SupplierTrans_ID"
    Given Define variable "SupplierResponse_Code" as "0"
    Given Substitute variables in "SupplierResponse_Code"
    Given Define variable "Payment_Ref" as "=~/^[0-9]{20}/"
    Given Substitute variables in "Payment_Ref"

    Given Define variable "Request"
    """
    <PaymentRQ xmlns="http://www.opentravel.org/OTA/2003/05/beta" SystemDateTime="#{@DATE}" Version="1.1.0">
    <Action>Auth</Action>
    <POS CityCode="BOG" PseudoCityCode="N764" AgentSine="RA/" AgentDutyCode="*" LNIATA="09E909" StationNumber="96188116" ISOCountry="CO" SettlementPlan="BSP" ChannelID="AGY" LocalDateTime="#{@DATE}">
    <POS_Address>
    <CityName>BOGOTA</CityName>
    <PostalCode>00000</PostalCode>
    <StateProv StateCode="CO"/>
    <Country Code="CO"/>
    </POS_Address>
    </POS>
    <MerchantDetail MerchantID="#{@Merchant_ID}" MerchantName="AVIANCA"/>
    <OrderDetail OrderID="#{@PNR}#{@DATE1}" RecordLocator="#{@PNR}" ValidatingCarrierCode="AV" ValidatingCarrierNbr="134">
    <ProductDetail CurrencyCode="#{@Currency_Code}" UnitPrice="726578" Quantity="1"/>
    <PassengerDetail NameInPNR="TEST/ONE" NameNumberInPNR="1.1" FirstName="ONE" LastName="TEST"/>
    <FlightDetail>
    <AirlineCode>AV</AirlineCode>
    <FlightNumber>9318</FlightNumber>
    <ClassOfService>Y</ClassOfService>
    <DepartureInfo DepartureAirport="BOG" DepartureDateTime="2015-12-25T15:32:00.000"/>
    <ArrivalInfo ArrivalAirport="MDE" ArrivalDateTime="2015-12-25T16:33:00.000"/>
    </FlightDetail>
    </OrderDetail>
    <PaymentDetail>
    <FOP Type="CC"/>
    <PaymentCard CardCode="#{@Card_Code}" CardNumber="#{@Card_Number}" PIN="#{@PIN_}" CardSecurityCode="#{@Card_Security_Code}" ExpireDate="#{@EXPDT}" ExtendPayment="00">
    <CardHolderName Name="ONE TEST" FirstName="ONE" LastName="TEST"/>
    <Address>
    <AddressLine1/>
    <CityName/>
    <PostalCode/>
    <StateProv StateCode=""/>
    <Country Code=""/>
    </Address>
    <EmailAddress/>
    <VCL_Info MCC="3039" AID="433239" CAI="SABRE INC AV"/>
    </PaymentCard>
    <AmountDetail BaseAmount="578800" Taxes="119010" Fees="28768" Amount="726578" CurrencyCode="#{@Currency_Code}">
    <TaxBreak TaxType="AT" TaxAmount="13200"/>
    <TaxBreak TaxType="IVA" TaxAmount="105810"/>
    <FeeBreak FeeType="TASA" BaseFee="24800" FeeTaxes="3968">
    <TaxBreak TaxType="IVA" TaxAmount="3968"/>
    </FeeBreak>
    </AmountDetail>
    </PaymentDetail>
    </PaymentRQ>
    """
    Given Substitute variables in "Request"

    # !***> Scenario table
    Given Save current date time as variable "DATE"
    Given Format date time from variable "DATE" using format "yyyydd" as variable "DATE1"
    Given Alter date time variable "DATE" by "1" with units "year" as variable "NEWDT"
    Given Format date time from variable "NEWDT" using format "MMyyyy" as variable "EXPDT"
    Given Define variable "PNR" as random alpha length 6
    Given display value on console "PNR"
    Given Define variable "CurrentDate" with time 00:00:00
    Given display value on console "CurrentDate"
    Given Substitute variables in "Request"
    When Send request in variable "Request" save response as "Resp"
    Given Replace character 'X' in string "16121XXXXXXXX" with random digits and save in "docNumber"
    Then Define variable "SabreTransactionID" from variable "Resp" as xpath "/PaymentRS/@SabreTransactionID"
    Then Define variable "approvalCode" from variable "Resp" as xpath "/PaymentRS/AuthorizationResult/@ApprovalCode"

    Then Variable "Resp" matches xml
      | xpath                                                     | value                     |
      | /PaymentRS/@SystemDateTime                                | #{@SysDateTime}           |
      | /PaymentRS/@SabreTransactionID                            | #{@SabreTrxID}            |
      | /PaymentRS/@Version                                       | #{@Version}               |
      | /PaymentRS/Result/@ResultCode                             | #{@Result_Code}           |
      | /PaymentRS/Result/@Description                            | #{@Result_Description}    |
      | /PaymentRS/AuthorizationResult/@ResponseCode              | #{@Response_Code}         |
      | /PaymentRS/AuthorizationResult/@ApprovalCode              | #{@Approval_Code}         |
      | /PaymentRS/AuthorizationResult/@SupplierID                | #{@Supplier_ID}           |
      | /PaymentRS/AuthorizationResult/@SupplierTransID           | #{@SupplierTrans_ID}      |
      | /PaymentRS/AuthorizationResult/@SupplierResponseCode      | #{@SupplierResponse_Code} |
      | /PaymentRS/AuthorizationResult/@PaymentRef                | #{@SabreTransactionID}    |
      | /PaymentRS/AuthorizationResult/@AuthRemarks1              | #{@Auth_Remarks1}         |
      | /PaymentRS/AuthorizationResult/@AuthRemarks2              | #{@Auth_Remarks2}         |
      | /PaymentRS/AuthorizationResult/CSC_Result/@CSC_ResultCode | #{@CSCResultCode}         |
      | /PaymentRS/AuthorizationResult/CSC_Result/@CSC_Remarks    | #{@CSCRemarks}            |
      | /PaymentRS/AuthorizationResult/AVS_Result/@AVS_ResultCode | #{@AVSResultCode}         |
      | /PaymentRS/AuthorizationResult/AVS_Result/@AVS_Remarks    | #{@AVSRemarks}            |


#     User Input :
#     Input to payload: Merchant_ID, ISO_Country, Pseudo_City_Code, Station_Number, Currency_Code, Product_ID, Quantity, Unit_Price, Phone_Number, FOP_Type, Card_Code, Card_Number, Card_Security_Code, Expiry_Date, Card_Holder_Name, Country_Code, Phone_Number, Amount_Detail, Currency_Code, Approval_Code, CSCRemarks, AVSRemarks, Auth_Remarks1 ,Auth_Remarks2
#     Input for Validations: Approval_Code
    Then Store response in "batchReversal" file
    Then Print debug connections
    #Then Validate deferred assertions

    Examples:
      | Merchant_ID | ISO_Country | DBTableName       | Supplier_ID | Environment    | Currency_Code  | FOP_Type | Card_Code | Card_Number      | PIN_ | Card_Security_Code | Card_Holder_Name | Country_Code | Amount_Detail | Currency_Code | Approval_Code | AVSResultCode | CSCRemarks                         | CSCResultCode | AVSRemarks                                                           | Auth_Remarks1                                                | Auth_Remarks2                                |
      | 1S          | AR          | BATCHREVERSALRULE | WPAY        | GCPIntegration | COP            | CC       | IK        | 5555555555554444 | 123  | 123                | SIDDHANT AWASTHI | AR           | 35.00         | ARS           | =~/^[0-9]+$/  | H             | =~/^  AUTH-CVV/CVC NOT CHECKED/C$/ | C             | =~/^  AUTH-POSTCODE AND ADDRESS NOT SUPPLIED BY SHOPPER/MERCHANT/H$/ | =~/AUTH-WPAY/AX5550/[0-9]{2}[A-Z]{3}/#{@SabreTransactionID}/ | =~/^  AUTH-APV/#{@approvalCode}/0/ARS35.00$/ |

    Scenario: Nubert Auth And Confirm Auth Verification
      Given  User opens the Nubert "GCPIntegration" application
      And Verify that URL got opened successfully
      And User enters username and password on Nubert loginpage
      And Verify that login is successful and Search button got displayed
      Then Select the Criteria for Search from file "batchReversal"
        | Txn ID    | SabreTransactionID  |
        | PNR       | PNR                 |
        | From Date | FromDate            |
      And Click on Search button in Nubert
      Then Verify the displayed result from file "batchReversal"
        | From Date | FromDate        |
        | Txn ID | SabreTransactionID |
        | Status | Success            |
        | PNR    | PNR                |