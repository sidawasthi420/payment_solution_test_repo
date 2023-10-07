Feature: Airline Batch Reversal Job Testing

  Background:
    Given Environment is "Int"
    Given Service is "Payment"
    Given Mode is "Live"
    Given Initialize
    Given Set connection debugging "on"
    Given Set assertion failure delay "on"

  @Batch1
  Scenario Outline: Airline Batch Reversal
    Given Test File
    When Tested the feature
    Then Test is passed
    Given connect to Database
    And fetch data from DB
      | TABLENAME     |  SUPPLIERID   | POSCOUNTRYCODE |  FOPCODE    |
      | <DBTableName> | <Supplier_ID> | <ISO_Country>  | <Card_Code> |
    Then disconnect from the DB
    Then Define variable "Test Description" as "<Test Description>"
    Then Define variable "Merchant_ID" as "<Merchant_ID>"
    Then Define variable "Merchant_Name" as "<Merchant_Name>"
    Then Define variable "Pseudo_City_Code" as "<Pseudo_City_Code>"
    Then Define variable "Station_Number" as "<Station_Number>"
    Then Define variable "ISO_Country" as "<ISO_Country>"
    Then Define variable "Channel_ID" as "<Channel_ID>"
    Then Define variable "IPAddress" as "<IPAddress>"
    Then Define variable "Order_Type" as "<Order_Type>"
    Then Define variable "Product_ID" as "<Product_ID>"
    Then Define variable "Unit_Price" as "<Unit_Price>"
    Then Define variable "Quantity" as "<Quantity>"
    Then Define variable "Name_InPNR" as "<Name_InPNR>"
    Then Define variable "First_Name" as "<First_Name>"
    Then Define variable "Last_Name" as "<Last_Name>"
    Then Define variable "BaseFare" as "<BaseFare>"
    Then Define variable "Doc_Type" as "<Doc_Type>"
    Then Define variable "Taxes" as "<Taxes>"
    Then Define variable "eTicket_Ind" as "<eTicket_Ind>"
    Then Define variable "Airline_Code" as "<Airline_Code>"
    Then Define variable "Flight_Number" as "<Flight_Number>"
    Then Define variable "ClassOf_Service" as "<ClassOf_Service>"
    Then Define variable "Departure_Airport" as "<Departure_Airport>"
    Then Define variable "Arrival_Airport" as "<Arrival_Airport>"
    Then Define variable "Departure_DateTime" as "<Departure_DateTime>"
    Then Define variable "Arrival_DateTime" as "<Arrival_DateTime>"
    Then Define variable "FOPType" as "<FOPType>"
    Then Define variable "Card_Code" as "<Card_Code>"
    Then Define variable "Card_Number" as "<Card_Number>"
    Then Define variable "Card_Security_Code" as "<Card_Security_Code>"
    Then Define variable "T3_DS_Ind" as "<T3_DS_Ind>"
    Then Define variable "Expire_Date" as "<Expire_Date>"
    Then Define variable "Extend_Payment" as "<Extend_Payment>"
    Then Define variable "Card_Holder_Name" as "<Card_Holder_Name>"
    Then Define variable "AddressLine_1" as "<AddressLine_1>"
    Then Define variable "AddressLine_2" as "<AddressLine_2>"
    Then Define variable "City_Name" as "<City_Name>"
    Then Define variable "Postal_Code" as "<Postal_Code>"
    Then Define variable "State_Code" as "<State_Code>"
    Then Define variable "Country_Code" as "<Country_Code>"
    Then Define variable "Amount" as "<Amount>"
    Then Define variable "With_InterestInd" as "<With_InterestInd>"
    Then Define variable "Currency_Code" as "<Currency_Code>"
    Then Define variable "CustomerDetail_Name" as "<CustomerDetail_Name>"
    Then Define variable "CustomerDetail_FirstName" as "<CustomerDetail_FirstName>"
    Then Define variable "CustomerDetail_LastName" as "<CustomerDetail_LastName>"
    Then Define variable "CustomerDetail_Email" as "<CustomerDetail_Email>"
    Then Define variable "CustomerDetail_PhoneType" as "<CustomerDetail_PhoneType>"
    Then Define variable "CustomerDetail_PhoneNumber" as "<CustomerDetail_PhoneNumber>"
    Then Define variable "Auth_Remarks1" as "<Auth_Remarks1>"
    Then Define variable "Auth_Remarks2" as "<Auth_Remarks2>"
    Then Define variable "CSCRemarks" as "<CSCRemarks>"
    Then Define variable "CSCResultCode" as "<CSCResultCode>"
    Then Define variable "AVSRemarks" as "<AVSRemarks>"
    Then Define variable "AVSResultCode" as "<AVSResultCode>"

    # !***> Variable definition for validations
    Given Define variable "SabreTrxID" as "=~/^[0-9]{20}$/"
    Given Substitute variables in "SabreTrxID"
    Given Define variable "SysDateTime" as "=~/^\d{4}-\d{2}-\d{2}[T]\d{2}:\d{2}:\d{2}/"
    Given Substitute variables in "SysDateTime"
    Given Define variable "Version" as "3.0.0"
    Given Substitute variables in "Version"
    Given Define variable "Result_Code" as "SUCCESS"
    Given Substitute variables in "Result_Code"
    Given Define variable "Result_Description" as "Successful Transaction"
    Given Substitute variables in "Result_Description"
    Given Define variable "Response_Code" as "APPROVED"
    Given Substitute variables in "Response_Code"
    Given Define variable "Supplier_ID" as "CS"
    Given Substitute variables in "Supplier_ID"
    Given Define variable "SupplierTrans_ID" as "=~/^[0-9]{15}$/"
    Given Substitute variables in "SupplierTrans_ID"
    Given Define variable "SupplierResponse_Code" as "100"
    Given Substitute variables in "SupplierResponse_Code"
    Given Define variable "Payment_Ref" as "=~/^[0-9]{20}/"
    Given Substitute variables in "Payment_Ref"
    Given Define variable "Approval_Code" as "=~/^[0-9]{4,6}$/"
    Given Substitute variables in "Approval_Code"
    Given Define variable "Request"
    """
  <PaymentRQ xmlns="http://www.opentravel.org/OTA/2003/05/beta" SystemDateTime="#{@DATE}" Version="4.0.0">
    <Action>Auth</Action>
    <POS ChannelID="RES" IP_Address="10.164.200.221" ISOCountry="US" LNIATA="39397E" LocalDateTime="#{@DATE}" PseudoCityCode="FFL" SourceID="SSW" StationNumber="95490441">
        <BrowserDetail BrowserSessionID="B356E1BBB373E145587F412570C6DA4A">
            <HttpHeaders>
                <HttpHeader Name="host">ipe.dev.sabre.com:7979</HttpHeader>
                <HttpHeader Name="connection">keep-alive</HttpHeader>
                <HttpHeader Name="content-length">290</HttpHeader>
                <HttpHeader Name="cache-control">max-age=0</HttpHeader>
                <HttpHeader Name="origin">http://ipe.dev.sabre.com:7979</HttpHeader>
                <HttpHeader Name="upgrade-insecure-requests">1</HttpHeader>
                <HttpHeader Name="user-agent">Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36</HttpHeader>
                <HttpHeader Name="content-type">application/x-www-form-urlencoded</HttpHeader>
                <HttpHeader Name="accept">text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8</HttpHeader>
                <HttpHeader Name="referer">http://ipe.dev.sabre.com:7979/pwsair/payment</HttpHeader>
                <HttpHeader Name="accept-encoding">gzip, deflate</HttpHeader>
                <HttpHeader Name="accept-language">en-US,en;q=0.8,en-GB;q=0.6</HttpHeader>
                <HttpHeader Name="cookie">JSESSIONID=B356E1BBB373E145587F412570C6DA4A; _ga=GA1.2.1208143568.1504159263; _gid=GA1.2.533874476.1504159263</HttpHeader>
            </HttpHeaders>
        </BrowserDetail>
    </POS>
    <MerchantDetail MerchantCategoryCode="" MerchantID="B6" MerchantName=""/>
    <OrderDetail DollarAmountPaid="0.00" OneWayInd="true" OrderID="#{@PNR}#{@DATE1}" OrderType="0" RecordLocator="#{@PNR}" SessionID="B356E1BBB373E145587F412570C6DA4A" ThirdPartyBookingInd="true">
        <ProductDetail CurrencyCode="USD" Fees="0" ProductID="0001" Quantity="1" Taxes="0.00" UnitPrice="12.00"/>
        <PassengerDetail FirstName="John" LastName="Smith" NameInPNR="Smith/John">
            <Document BaseFare="12" DocType="TKT" Fees="0" Taxes="5" eTicketInd="true"/>
        </PassengerDetail>
        <FlightDetail>
	      <AirlineCode>#{@Airline_Code}</AirlineCode>
         <FlightNumber>#{@Flight_Number}</FlightNumber>
         <ClassOfService>#{@ClassOf_Service}</ClassOfService>
	      <DepartureInfo DepartureAirport="ABJ" DepartureDateTime="2018-02-24T20:25:00" CurrentLocalDateTime="2018-01-10T14:56:01"/>
	      <ArrivalInfo ArrivalAirport="NBO" ArrivalDateTime="2018-02-25T05:50:00" FinalDestinationInd="false"/>
	    </FlightDetail>
        <PassengerDetail FirstName="Mary" LastName="Smith" NameInPNR="Smith/Mary">
            <Document BaseFare="7" DocType="TKT" Fees="0" Taxes="0" eTicketInd="true"/>
        </PassengerDetail>
        <FlightDetail>
	      <AirlineCode>B6</AirlineCode>
	      <FlightNumber>4053</FlightNumber>
	      <ClassOfService>V</ClassOfService>
	      <DepartureInfo DepartureAirport="ABJ" DepartureDateTime="2018-02-24T20:25:00" CurrentLocalDateTime="2018-01-10T14:56:01"/>
	      <ArrivalInfo ArrivalAirport="NBO" ArrivalDateTime="2018-02-25T05:50:00" FinalDestinationInd="false"/>
	    </FlightDetail>
    </OrderDetail>
    <PaymentDetail FOP_RPH="1">
        <FOP Type="CC"/>
        <PaymentCard CardCode="CA" CardNumber="5200000000000007" CardSecurityCode="000" ExpireDate="122027">
            <CardHolderName Name="test1 test2"/>
            <Address>
                <AddressLine1>#{@AddressLine_1}</AddressLine1>
				<AddressLine2>#{@AddressLine_2}</AddressLine2>
				<CityName>#{@City_Name}</CityName>
				<PostalCode>#{@Postal_Code}</PostalCode>
                <StateProv StateCode="#{@State_Code}" />
				<Country Code="#{@Country_Code}" />
            </Address>
            <EmailAddress>#{@CustomerDetail_Email}</EmailAddress>
            <PhoneNumber Number="1234567890" Type="HOME"/>
        </PaymentCard>
        <AmountDetail Amount="17.00" CurrencyCode="GBP"/>
        <ReturnURLs>
            <DefaultURL>http://ipe.dev.sabre.com:7979/pwsair/afop_status?JSESSIONID=B356E1BBB373E145587F412570C6DA4A&amp;airline=IT&amp;paymentSequence=1</DefaultURL>
            <ApprovedURL>http://ipe.dev.sabre.com:7979/pwsair/afop_status?JSESSIONID=B356E1BBB373E145587F412570C6DA4A&amp;airline=IT&amp;paymentSequence=1&amp;status=approved</ApprovedURL>
            <DeclinedURL>http://ipe.dev.sabre.com:7979/pwsair/afop_status?JSESSIONID=B356E1BBB373E145587F412570C6DA4A&amp;airline=IT&amp;paymentSequence=1&amp;status=declined</DeclinedURL>
            <PendingURL>http://ipe.dev.sabre.com:7979/pwsair/afop_status?JSESSIONID=B356E1BBB373E145587F412570C6DA4A&amp;airline=IT&amp;paymentSequence=1&amp;status=pending</PendingURL>
            <CancelURL>http://ipe.dev.sabre.com:7979/pwsair/afop_status?JSESSIONID=B356E1BBB373E145587F412570C6DA4A&amp;airline=IT&amp;paymentSequence=1&amp;status=canceled</CancelURL>
            <ErrorURL>http://ipe.dev.sabre.com:7979/pwsair/afop_status?JSESSIONID=B356E1BBB373E145587F412570C6DA4A&amp;airline=IT&amp;paymentSequence=1&amp;status=error</ErrorURL>
        </ReturnURLs>
        <RoutingDetail/>
    </PaymentDetail>
    <CustomerDetail Name="#{@CustomerDetail_Name}">
        <Address>
            <AddressLine1>test</AddressLine1>
            <AddressLine2>test</AddressLine2>
            <CityName>test</CityName>
            <PostalCode>23456</PostalCode>
            <StateProv StateCode="tx"/>
            <Country Code="US"/>
        </Address>
        <EmailAddress>#{@CustomerDetail_Email}</EmailAddress>
        <PhoneNumber Number="#{@CustomerDetail_PhoneNumber}" Type="#{@CustomerDetail_PhoneType}"/>
    </CustomerDetail>
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
    Then Store response in "airlineBatchReversal" file
    Then Print debug connections
    #Then Validate deferred assertions

    Examples:
      | Test Description          | DBTableName       | Merchant_ID | Supplier_ID | Merchant_Name | Pseudo_City_Code | Station_Number | ISO_Country | Channel_ID | IPAddress | Order_Type | Product_ID | Unit_Price | Quantity | Name_InPNR                 | First_Name    | Last_Name    | BaseFare | Doc_Type | Taxes | eTicket_Ind | Airline_Code | Flight_Number | ClassOf_Service | Departure_Airport | Arrival_Airport | Departure_DateTime  | Arrival_DateTime    | FOPType | Card_Code | Card_Number      | Card_Security_Code | T3_DS_Ind | Extend_Payment | Card_Holder_Name           | AddressLine_1 | AddressLine_2 | City_Name | Postal_Code | State_Code | Country_Code | PaymentCard_PhoneNumber | PaymentCard_Phonetype | Amount | Currency_Code | CustomerDetail_Name | CustomerDetail_FirstName | CustomerDetail_LastName | CustomerDetail_Email | CustomerDetail_PhoneType | CustomerDetail_PhoneNumber | Auth_Remarks1                                                    | Auth_Remarks2                                  |
      | Auth - JB - WEB           | BATCHREVERSALRULE | B6          | CS          | BahamaAir     | DFW              | 92834732       | US          | WEB        | 120.1.7.0 | O          | 0001       | 9          | 1        | TESTLASTNAME/TESTFIRSTNAME | TESTFIRSTNAME | TESTLASTNAME | 31.82    | TKT      | 3.18  | true        | B6           | 9106          | W               | DFW               | SFO             | 2016-06-06T10:10:00 | 2016-06-06T11:10:00 | CC      | JB        | 3566111111111113 | 000                | false     | 00             | TESTLASTNAME TESTFIRSTNAME | Alpha St      | Suite 4200    | Miami     | 33131       | TX         | US           |                         |                       | 10.00  | GBP           | TEST/ONE            | ONE                      | TEST                    | TEST@CUST.COM        | H                        | 2345678906                 | =~/AUTH-CS/JB1113/[0-9]{2}[A-Z]{3}/#{@SabreTransactionID}/       | =~/^  AUTH-APV/#{@approvalCode}/100/GBP10.00$/ |
#      | Auth - VI - GBP - WEB     | BATCHREVERSALRULE | B6          | CS          | BahamaAir     | DFW              | 92834732       | US          | WEB        | 120.1.7.0 | O          | 0001       | 9          | 1        | TESTLASTNAME/TESTFIRSTNAME | TESTFIRSTNAME | TESTLASTNAME | 31.82    | TKT      | 3.18  | true        | B6           | 9106          | W               | DFW               | SFO             | 2016-06-06T10:10:00 | 2016-06-06T11:10:00 | CC      | VI        | 4111111111111111 | 000                | false     | 00             | TESTLASTNAME TESTFIRSTNAME | Alpha St      | Suite 4200    | Miami     | 33131       | TX         | US           |                         |                       | 10.00  | GBP           | TEST/ONE            | ONE                      | TEST                    | TEST@CUST.COM        | H                        | 2345678906                 | =~/AUTH-CS/VI0051/[0-9]{2}[A-Z]{3}\\*3DS/#{@SabreTransactionID}/ | =~/^  AUTH-APV/#{@approvalCode}/100/GBP10.00$/ |
#      | Auth - CA - GBP- WEB      | BATCHREVERSALRULE | B6          | CS          | BahamaAir     | DFW              | 92834732       | US          | WEB        | 120.1.7.0 | O          | 0001       | 9          | 1        | TESTLASTNAME/TESTFIRSTNAME | TESTFIRSTNAME | TESTLASTNAME | 31.82    | TKT      | 3.18  | true        | B6           | 9106          | W               | DFW               | SFO             | 2016-06-06T10:10:00 | 2016-06-06T11:10:00 | CC      | CA        | 5555555555554444 | 000                | false     | 00             | TESTLASTNAME TESTFIRSTNAME | Alpha St      | Suite 4200    | Miami     | 33131       | TX         | US           |                         |                       | 10.00  | GBP           | TEST/ONE            | ONE                      | TEST                    | TEST@CUST.COM        | H                        | 2345678906                 | =~/AUTH-CS/CA4444/[0-9]{2}[A-Z]{3}\\*3DS/#{@SabreTransactionID}/ | =~/^  AUTH-APV/#{@approvalCode}/100/GBP10.00$/ |
#      | Auth - DC - GBP - WEB     | BATCHREVERSALRULE | B6          | CS          | BahamaAir     | DFW              | 92834732       | US          | WEB        | 120.1.7.0 | O          | 0001       | 9          | 1        | TESTLASTNAME/TESTFIRSTNAME | TESTFIRSTNAME | TESTLASTNAME | 31.82    | TKT      | 3.18  | true        | B6           | 9106          | W               | DFW               | SFO             | 2016-06-06T10:10:00 | 2016-06-06T11:10:00 | CC      | DC        | 3005000000007269 | 000                | false     | 00             | TESTLASTNAME TESTFIRSTNAME | Alpha St      | Suite 4200    | Miami     | 33131       | TX         | US           | 8976543213432           | M                     | 10.00  | GBP           |                     |                          |                         |                      | H                        | 122345678765               | =~/AUTH-CS/DC7269/[0-9]{2}[A-Z]{3}\\*3DS/#{@SabreTransactionID}/ | =~/^  AUTH-APV/#{@approvalCode}/100/GBP10.00$/ |
#      | Installments - GBP - WEB  | BATCHREVERSALRULE | B6          | CS          | BahamaAir     | DFW              | 92834732       | US          | WEB        | 120.1.7.0 | O          | 0001       | 9          | 1        | TESTLASTNAME/TESTFIRSTNAME | TESTFIRSTNAME | TESTLASTNAME | 31.82    | TKT      | 3.18  | true        | B6           | 9106          | W               | DFW               | SFO             | 2016-06-06T10:10:00 | 2016-06-06T11:10:00 | CC      | VI        | 4111111111111111 | 000                | false     | 06             | TESTLASTNAME TESTFIRSTNAME | Alpha St      | Suite 4200    | Miami     | 33131       | TX         | US           | 8976543213432           | M                     | 10.00  | GBP           |                     |                          |                         |                      | H                        | 122345678765               | =~/AUTH-CS/VI0051/[0-9]{2}[A-Z]{3}\\*3DS/#{@SabreTransactionID}/ | =~/^  AUTH-APV/#{@approvalCode}/100/GBP10.00$/ |
#      | Auth - CA - GBP - US- ATO | BATCHREVERSALRULE | B6          | CS          | BahamaAir     | DFW              | 92834732       | US          | ATO        |           | O          | 0001       | 9          | 1        | TESTLASTNAME/TESTFIRSTNAME | TESTFIRSTNAME | TESTLASTNAME | 31.82    | TKT      | 3.18  | true        | B6           | 9106          | W               | DFW               | SFO             | 2016-06-06T10:10:00 | 2016-06-06T11:10:00 | CC      | CA        | 5200000000000007 | 000                | false     | 00             | TESTLASTNAME TESTFIRSTNAME | Alpha St      | Suite 4200    | Miami     | 33131       | TX         | US           |                         |                       | 10.00  | GBP           | TEST/ONE            | ONE                      | TEST                    | TEST@CUST.COM        | H                        | 2345678906                 | =~/AUTH-CS/CA4444/[0-9]{2}[A-Z]{3}\\*3DS/#{@SabreTransactionID}/ | =~/^  AUTH-APV/#{@approvalCode}/100/GBP10.00$/ |
#      | Auth - IK - GBP- WEB      | BATCHREVERSALRULE | B6          | CS          | BahamaAir     | DFW              | 92834732       | US          | WEB        | 120.1.7.0 | O          | 0001       | 9          | 1        | TESTLASTNAME/TESTFIRSTNAME | TESTFIRSTNAME | TESTLASTNAME | 31.82    | TKT      | 3.18  | true        | B6           | 9106          | W               | DFW               | SFO             | 2016-06-06T10:10:00 | 2016-06-06T11:10:00 | CC      | IK        | 5555555555554444 | 000                | false     | 00             | TESTLASTNAME TESTFIRSTNAME | Alpha St      | Suite 4200    | Miami     | 33131       | TX         | US           |                         |                       | 10.00  | GBP           | TEST/ONE            | ONE                      | TEST                    | TEST@CUST.COM        | H                        | 2345678906                 | =~/AUTH-CS/CA4444/[0-9]{2}[A-Z]{3}\\*3DS/#{@SabreTransactionID}/ | =~/^  AUTH-APV/#{@approvalCode}/100/GBP10.00$/ |

    Scenario: Nubert Auth And Confirm Auth Verification
      Given  User opens the Nubert "GCPIntegration" application
      And Verify that URL got opened successfully
      And User enters username and password on Nubert loginpage
      And Verify that login is successful and Search button got displayed
      Then Select the Criteria for Search from file "airlineBatchReversal"
        | Txn ID    | SabreTransactionID  |
        | PNR       | PNR                 |
        | From Date | FromDate            |
      And Click on Search button in Nubert
      Then Verify the displayed result from file "airlineBatchReversal"
        | From Date | FromDate        |
        | Txn ID | SabreTransactionID |
        | Status | Success            |
        | PNR    | PNR                |