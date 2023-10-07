package com.sabre.cucumber.stepdefs.CommandCenter.OnlineCapture;

import com.sabre.cucumber.lib.CommonLib;
import com.sabre.cucumber.state.TestContext1;
import com.sabre.ngpq.lite.services.base.AssertionService;
import com.sabre.ngpq.lite.services.base.LiteHelperService;
import com.sabre.ngpq.lite.services.base.SubstitutorService;
import com.sabre.ngpq.platform.services.OracleService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.hamcrest.CoreMatchers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Formatter;
import java.util.List;
import java.util.Map;

public class OracleDBStepdef {

    private TestContext1 testContext;
    private SubstitutorService substitutorService;
    private AssertionService assertionService;
    private CommonLib commonLib;
    private OracleService oracleService;
    private LiteHelperService liteHelperService = new LiteHelperService();


    public OracleDBStepdef(TestContext1 testContext) {
        this.testContext = testContext;
        this.substitutorService = testContext.baseTestContext.getSubstitutorService();
        this.assertionService = testContext.baseTestContext.getAssertionService();
        this.commonLib = new CommonLib(testContext);
        this.oracleService = testContext.baseTestContext.getOracleService();
    }

    @Given("^connect to Database$")
    public void connect_to_Database() {
        String url = commonLib.loadFromResources("OnlineCaptureConfig.properties").get("DBURL");
        String port = commonLib.loadFromResources("OnlineCaptureConfig.properties").get("DBPort");
        String username = commonLib.loadFromResources("OnlineCaptureConfig.properties").get("DBUsername");
        String password = commonLib.loadFromResources("OnlineCaptureConfig.properties").get("DBPassword");
        String servicename = commonLib.loadFromResources("OnlineCaptureConfig.properties").get("DBServicename");
        oracleService.connect(url, port, username, password, servicename, false);
    }

    @Then("^disconnect from the DB$")
    public void disconnectFromTheDB() {
        oracleService.closeConnection();
        System.out.println("Database disconnected");
    }

    @And("fetch data from DB")
    public void fetchDataFromDB(List<Map<String, String>> dataTable) throws SQLException {
        Map<String, String> data = dataTable.get(0);
        String tableName = data.get("TABLENAME");
        String supplierId = data.get("SUPPLIERID");
        String countryCode = data.get("POSCOUNTRYCODE");
        String fopCode = data.get("FOPCODE");
        String fetchBatchReversalRules = "SELECT * FROM "+tableName+" WHERE SUPPLIERID='"+supplierId+"' AND FOPCODE='"+fopCode+"' AND POSCOUNTRYCODE='"+countryCode+"'";
        ResultSet result = oracleService.executeQuery(fetchBatchReversalRules);
        oracleService.executeQuery("commit");

        Formatter fmt = new Formatter();
        fmt.format("%15s %12s %15s %13s %13s %15s %12s\n", "BatchReversalRuleID", "SupplierID", "POSCountryCode", "FOPCode", "FOPSubCode", "AuthValidityPeriod", "BufferPeriod");
        while (result.next())
        {
            fmt.format("%14s %14s %14s %14s %14s %14s %14s\n", result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getString(8));
        }
        System.out.println(fmt);
    }

    @And("insert data into DB")
    public void insertDataIntoDB(List<Map<String, String>> dataTable) {
        Map<String, String> data = dataTable.get(0);
        String auditLogId = data.get("AUDITLOGID");
        String updateUserId = data.get("UPDATEUSERID");
        String description = data.get("DESCRIPTION");
        String sourceId = data.get("SOURCEID");
        String auditDomainId = data.get("AUDITDOMAINID");
        String auditLogDetailId = data.get("AUDITLOGDETAILID");
        String columnName = data.get("COLUMNNAME");
        String afterValue = data.get("AFTERVALUE");
        String fopCode = data.get("ColumnName");
        String auditLogDetailIdFopCode = data.get("Auditlogdetailid");
        String auditLogDetailIdMerchant = data.get("AuditLogdetailid");
        String auditLogDetailIdUserId = data.get("AuditLogDetailid");
        String merchantId = data.get("Columnname");
        String lastUpdatedUserId = data.get("columnName");
        String afterValueFop = data.get("Aftervalue");
        String afterValueUserId = data.get("AfterValue");
        String afterValueMerchant = data.get("afterValue");
        String date = liteHelperService.getCurrentDateTime("dd/MM/yyyy");
        String insertAuditLog = "INSERT INTO AUDITLOG (AUDITLOGID,UPDATEUSERID,UPDATETIMESTAMP,ACTION,DESCRIPTION,SOURCEID,AUDITDOMAINID) VALUES ('" + auditLogId + "','" + updateUserId + "', TO_DATE('" + date + "','DD/MM/YYYY'), 'ADD', '" + description + "', '" + sourceId + "', '" + auditDomainId + "')";
        oracleService.executeQuery(insertAuditLog);
        String insertAuditLogDetail = "INSERT INTO AUDITLOGDETAIL (AUDITLOGDETAILID,COLUMNNAME,BEFOREVALUE,AFTERVALUE,AUDITLOGID,BEFOREPAYLOAD,AFTERPAYLOAD) VALUES ('" + auditLogDetailId + "','" + columnName + "', null, '" + afterValue + "' ,'" + auditLogId + "',null,null) ";
        oracleService.executeQuery(insertAuditLogDetail);
        String insertAuditLogDetailFop = "INSERT INTO AUDITLOGDETAIL (Auditlogdetailid,ColumnName,BEFOREVALUE,Aftervalue,AUDITLOGID,BEFOREPAYLOAD,AFTERPAYLOAD) VALUES ('" + auditLogDetailIdFopCode + "','" + fopCode + "', null, '" + afterValueFop + "' ,'" + auditLogId + "',null,null) ";
        oracleService.executeQuery(insertAuditLogDetailFop);
        String insertAuditLogDetailMerchant = "INSERT INTO AUDITLOGDETAIL (AuditLogdetailid,Columnname,BEFOREVALUE,afterValue,AUDITLOGID,BEFOREPAYLOAD,AFTERPAYLOAD) VALUES ('" + auditLogDetailIdMerchant + "','" + merchantId + "', null, '" + afterValueMerchant + "' ,'" + auditLogId + "',null,null) ";
        oracleService.executeQuery(insertAuditLogDetailMerchant);
        String insertAuditLogDetailUserId = "INSERT INTO AUDITLOGDETAIL (AuditLogDetailid,columnName,BEFOREVALUE,AfterValue,AUDITLOGID,BEFOREPAYLOAD,AFTERPAYLOAD) VALUES ('" + auditLogDetailIdUserId + "','" + lastUpdatedUserId + "', null, '" + afterValueUserId + "' ,'" + auditLogId + "',null,null) ";
        oracleService.executeQuery(insertAuditLogDetailUserId);
        oracleService.executeQuery("commit");
    }

    @And("Update the inserted data into DB")
    public void updateTheInsertedDataIntoDB(List<Map<String, String>> dataTable) {
        Map<String, String> data = dataTable.get(0);
        String auditLogId = data.get("AUDITLOGID");
        String updateUserId = data.get("UPDATEUSERID");
        String description = data.get("DESCRIPTION");
        String sourceId = data.get("SOURCEID");
        String auditDomainId = data.get("AUDITDOMAINID");
        String auditLogDetailId = data.get("AUDITLOGDETAILID");
        String columnName = data.get("COLUMNNAME");
        String beforeValue = data.get("BEFOREVALUE");
        String afterValue = data.get("AFTERVALUE");
        String fopCode = data.get("ColumnName");
        String auditLogDetailIdFopCode = data.get("Auditlogdetailid");
        String auditLogDetailIdMerchant = data.get("AuditLogdetailid");
        String auditLogDetailIdUserId = data.get("AuditLogDetailid");
        String merchantId = data.get("Columnname");
        String afterValueFop = data.get("Aftervalue");
        String afterValueUserId = data.get("AfterValue");
        String afterValueMerchant = data.get("afterValue");
        String lastUpdatedUserId = data.get("columnName");
        String date = liteHelperService.getCurrentDateTime("dd/MM/yyyy");
        String insertAuditLog = "INSERT INTO AUDITLOG (AUDITLOGID,UPDATEUSERID,UPDATETIMESTAMP,ACTION,DESCRIPTION,SOURCEID,AUDITDOMAINID) VALUES ('" + auditLogId + "','" + updateUserId + "', TO_DATE('" + date + "','DD/MM/YYYY'), 'UPDATE', '" + description + "', '" + sourceId + "', '" + auditDomainId + "')";
        oracleService.executeQuery(insertAuditLog);
        String insertAuditLogDetail = "INSERT INTO AUDITLOGDETAIL (AUDITLOGDETAILID,COLUMNNAME,BEFOREVALUE,AFTERVALUE,AUDITLOGID,BEFOREPAYLOAD,AFTERPAYLOAD) VALUES ('" + auditLogDetailId + "','" + columnName + "', '" + beforeValue + "', '" + afterValue + "' ,'" + auditLogId + "',null,null) ";
        oracleService.executeQuery(insertAuditLogDetail);
        String insertAuditLogDetailFop = "INSERT INTO AUDITLOGDETAIL (Auditlogdetailid,ColumnName,BEFOREVALUE,Aftervalue,AUDITLOGID,BEFOREPAYLOAD,AFTERPAYLOAD) VALUES ('" + auditLogDetailIdFopCode + "','" + fopCode + "', null, '" + afterValueFop + "' ,'" + auditLogId + "',null,null) ";
        oracleService.executeQuery(insertAuditLogDetailFop);
        String insertAuditLogDetailMerchant = "INSERT INTO AUDITLOGDETAIL (AuditLogdetailid,Columnname,BEFOREVALUE,afterValue,AUDITLOGID,BEFOREPAYLOAD,AFTERPAYLOAD) VALUES ('" + auditLogDetailIdMerchant + "','" + merchantId + "', null, '" + afterValueMerchant + "' ,'" + auditLogId + "',null,null) ";
        oracleService.executeQuery(insertAuditLogDetailMerchant);
        String insertAuditLogDetailUserId = "INSERT INTO AUDITLOGDETAIL (AuditLogDetailid,columnName,BEFOREVALUE,AfterValue,AUDITLOGID,BEFOREPAYLOAD,AFTERPAYLOAD) VALUES ('" + auditLogDetailIdUserId + "','" + lastUpdatedUserId + "', null, '" + afterValueUserId + "' ,'" + auditLogId + "',null,null) ";
        oracleService.executeQuery(insertAuditLogDetailUserId);
        oracleService.executeQuery("commit");
    }

    @And("Delete the inserted data into DB")
    public void deleteTheInsertedDataIntoDB(List<Map<String, String>> dataTable) {
        Map<String, String> data = dataTable.get(0);
        String auditLogId = data.get("AUDITLOGID");
        String auditLogDetailId = data.get("AUDITLOGDETAILID");
        String auditLogDetailIdFopCode = data.get("Auditlogdetailid");
        String auditLogDetailIdMerchant = data.get("AuditLogdetailid");
        String auditLogDetailIdUserId = data.get("AuditLogDetailid");
        String deleteAuditLogDetail = "delete  FROM AUDITLOGDETAIL where AUDITLOGDETAILID = '" + auditLogDetailId + "'";
        oracleService.executeQuery(deleteAuditLogDetail);
        String deleteAuditLogDetailFopCode = "delete  FROM AUDITLOGDETAIL where Auditlogdetailid = '" + auditLogDetailIdFopCode + "'";
        oracleService.executeQuery(deleteAuditLogDetailFopCode);
        String deleteAuditLogDetailMerchant = "delete  FROM AUDITLOGDETAIL where AuditLogdetailid = '" + auditLogDetailIdMerchant + "'";
        oracleService.executeQuery(deleteAuditLogDetailMerchant);
        String deleteAuditLogDetailIdUserId = "delete  FROM AUDITLOGDETAIL where AuditLogDetailid = '" + auditLogDetailIdUserId + "'";
        oracleService.executeQuery(deleteAuditLogDetailIdUserId);
        String deleteAuditLog = "delete  FROM AUDITLOG where auditlogId = '" + auditLogId + "'";
        oracleService.executeQuery(deleteAuditLog);
        oracleService.executeQuery("commit");


    }

    @And("insert data into DB with ranges")
    public void insertDataIntoDBEndRange(List<Map<String, String>> dataTable) {
        Map<String, String> data = dataTable.get(0);
        String auditLogId = data.get("AUDITLOGID");
        String updateUserId = data.get("UPDATEUSERID");
        String description = data.get("DESCRIPTION");
        String sourceId = data.get("SOURCEID");
        String auditDomainId = data.get("AUDITDOMAINID");
        String auditLogDetailId = data.get("auditlogdetailid");
        String columnName = data.get("columnname");
        String afterValue = data.get("aftervalue");
        String auditLogDetailIdEndRange = data.get("AUDITLOGDETAILID");
        String columnNameEndRange = data.get("COLUMNNAME");
        String afterValueEndRange = data.get("AFTERVALUE");
        String fopCode = data.get("ColumnName");
        String auditLogDetailIdFopCode = data.get("Auditlogdetailid");
        String auditLogDetailIdMerchant = data.get("AuditLogdetailid");
        String auditLogDetailIdUserId = data.get("AuditLogDetailid");
        String merchantId = data.get("Columnname");
        String lastUpdatedUserId = data.get("columnName");
        String afterValueFop = data.get("Aftervalue");
        String afterValueUserId = data.get("AfterValue");
        String afterValueMerchant = data.get("afterValue");
        String date = liteHelperService.getCurrentDateTime("dd/MM/yyyy");
        String insertAuditLog = "INSERT INTO AUDITLOG (AUDITLOGID,UPDATEUSERID,UPDATETIMESTAMP,ACTION,DESCRIPTION,SOURCEID,AUDITDOMAINID) VALUES ('" + auditLogId + "','" + updateUserId + "', TO_DATE('" + date + "','DD/MM/YYYY'), 'ADD', '" + description + "', '" + sourceId + "', '" + auditDomainId + "')";
        oracleService.executeQuery(insertAuditLog);
        String insertAuditLogDetailStartRange = "INSERT INTO AUDITLOGDETAIL (auditlogdetailid,columnname,BEFOREVALUE,aftervalue,AUDITLOGID,BEFOREPAYLOAD,AFTERPAYLOAD) VALUES ('" + auditLogDetailId + "','" + columnName + "', null, '" + afterValue + "' ,'" + auditLogId + "',null,null) ";
        oracleService.executeQuery(insertAuditLogDetailStartRange);
        String insertAuditLogDetailEndRange = "INSERT INTO AUDITLOGDETAIL (AUDITLOGDETAILID,COLUMNNAME,BEFOREVALUE,AFTERVALUE,AUDITLOGID,BEFOREPAYLOAD,AFTERPAYLOAD) VALUES ('" + auditLogDetailIdEndRange + "','" + columnNameEndRange + "', null, '" + afterValueEndRange + "' ,'" + auditLogId + "',null,null) ";
        oracleService.executeQuery(insertAuditLogDetailEndRange);
        String insertAuditLogDetailFop = "INSERT INTO AUDITLOGDETAIL (Auditlogdetailid,ColumnName,BEFOREVALUE,Aftervalue,AUDITLOGID,BEFOREPAYLOAD,AFTERPAYLOAD) VALUES ('" + auditLogDetailIdFopCode + "','" + fopCode + "', null, '" + afterValueFop + "' ,'" + auditLogId + "',null,null) ";
        oracleService.executeQuery(insertAuditLogDetailFop);
        String insertAuditLogDetailMerchant = "INSERT INTO AUDITLOGDETAIL (AuditLogdetailid,Columnname,BEFOREVALUE,afterValue,AUDITLOGID,BEFOREPAYLOAD,AFTERPAYLOAD) VALUES ('" + auditLogDetailIdMerchant + "','" + merchantId + "', null, '" + afterValueMerchant + "' ,'" + auditLogId + "',null,null) ";
        oracleService.executeQuery(insertAuditLogDetailMerchant);
        String insertAuditLogDetailUserId = "INSERT INTO AUDITLOGDETAIL (AuditLogDetailid,columnName,BEFOREVALUE,AfterValue,AUDITLOGID,BEFOREPAYLOAD,AFTERPAYLOAD) VALUES ('" + auditLogDetailIdUserId + "','" + lastUpdatedUserId + "', null, '" + afterValueUserId + "' ,'" + auditLogId + "',null,null) ";
        oracleService.executeQuery(insertAuditLogDetailUserId);
        oracleService.executeQuery("commit");

    }

    @And("Delete the data into DB with multiple ranges")
    public void deleteDataIntoDBWithMultipleRanges(List<Map<String, String>> dataTable) {
        Map<String, String> data = dataTable.get(0);
        String auditLogId = data.get("AUDITLOGID");
        String updateUserId = data.get("UPDATEUSERID");
        String description = data.get("DESCRIPTION");
        String sourceId = data.get("SOURCEID");
        String auditDomainId = data.get("AUDITDOMAINID");
        String updatedAuditLogDetailId = data.get("auditlogdetailid");
        String columnName = data.get("columnname");
        String beforeValue = data.get("beforevalue");
        String afterValue = data.get("aftervalue");
        String columnNameEndRange = data.get("COLUMNNAME");
        String beforeValueEndRange = data.get("BEFOREVALUE");
        String afterValueEndRange = data.get("AFTERVALUE");
        String fopCode = data.get("ColumnName");
        String auditLogDetailIdFopCode = data.get("Auditlogdetailid");
        String auditLogDetailIdMerchant = data.get("AuditLogdetailid");
        String auditLogDetailIdUserId = data.get("AuditLogDetailid");
        String merchantId = data.get("Columnname");
        String lastUpdatedUserId = data.get("columnName");
        String afterValueFop = data.get("Aftervalue");
        String afterValueUserId = data.get("AfterValue");
        String afterValueMerchant = data.get("afterValue");
        String auditLogDetailIdUpdatedEndRange = data.get("AUDITLOGDETAILID");
        String date = liteHelperService.getCurrentDateTime("dd/MM/yyyy");
        String insertAuditLog = "INSERT INTO AUDITLOG (AUDITLOGID,UPDATEUSERID,UPDATETIMESTAMP,ACTION,DESCRIPTION,SOURCEID,AUDITDOMAINID) VALUES ('" + auditLogId + "','" + updateUserId + "', TO_DATE('" + date + "','DD/MM/YYYY'), 'DELETE', '" + description + "', '" + sourceId + "', '" + auditDomainId + "')";
        oracleService.executeQuery(insertAuditLog);
        String insertAuditLogDetail = "INSERT INTO AUDITLOGDETAIL (auditlogdetailid,columnname,beforevalue,aftervalue,AUDITLOGID,BEFOREPAYLOAD,AFTERPAYLOAD) VALUES ('" + updatedAuditLogDetailId + "','" + columnName + "', '" + beforeValue + "', '" + afterValue + "' ,'" + auditLogId + "',null,null) ";
        oracleService.executeQuery(insertAuditLogDetail);
        String insertAuditLogDetailEndRange = "INSERT INTO AUDITLOGDETAIL (AUDITLOGDETAILID,COLUMNNAME,BEFOREVALUE,AFTERVALUE,AUDITLOGID,BEFOREPAYLOAD,AFTERPAYLOAD) VALUES ('" + auditLogDetailIdUpdatedEndRange + "','" + columnNameEndRange + "', '" + beforeValueEndRange + "', '" + afterValueEndRange + "' ,'" + auditLogId + "',null,null) ";
        oracleService.executeQuery(insertAuditLogDetailEndRange);
        String insertAuditLogDetailFop = "INSERT INTO AUDITLOGDETAIL (Auditlogdetailid,ColumnName,BEFOREVALUE,Aftervalue,AUDITLOGID,BEFOREPAYLOAD,AFTERPAYLOAD) VALUES ('" + auditLogDetailIdFopCode + "','" + fopCode + "', null, '" + afterValueFop + "' ,'" + auditLogId + "',null,null) ";
        oracleService.executeQuery(insertAuditLogDetailFop);
        String insertAuditLogDetailMerchant = "INSERT INTO AUDITLOGDETAIL (AuditLogdetailid,Columnname,BEFOREVALUE,afterValue,AUDITLOGID,BEFOREPAYLOAD,AFTERPAYLOAD) VALUES ('" + auditLogDetailIdMerchant + "','" + merchantId + "', null, '" + afterValueMerchant + "' ,'" + auditLogId + "',null,null) ";
        oracleService.executeQuery(insertAuditLogDetailMerchant);
        String insertAuditLogDetailUserId = "INSERT INTO AUDITLOGDETAIL (AuditLogDetailid,columnName,BEFOREVALUE,AfterValue,AUDITLOGID,BEFOREPAYLOAD,AFTERPAYLOAD) VALUES ('" + auditLogDetailIdUserId + "','" + lastUpdatedUserId + "', null, '" + afterValueUserId + "' ,'" + auditLogId + "',null,null) ";
        oracleService.executeQuery(insertAuditLogDetailUserId);
        oracleService.executeQuery("commit");
    }


    @And("add the Delete records in to the DB")
    public void addTheDeleteRecordsInToTheDB(List<Map<String, String>> dataTable) {
        Map<String, String> data = dataTable.get(0);
        String auditLogId = data.get("AUDITLOGID");
        String updateUserId = data.get("LASTUPDATEUSERID");
        String description = data.get("DESCRIPTION");
        String sourceId = data.get("SOURCEID");
        String auditDomainId = data.get("AUDITDOMAINID");
        String auditLogDetailId = data.get("AUDITLOGDETAILID");
        String columnName = data.get("COLUMNNAME");
        String beforeValue = data.get("BEFOREVALUE");
        String afterValue = data.get("AFTERVALUE");
        String fopCode = data.get("ColumnName");
        String auditLogDetailIdFopCode = data.get("Auditlogdetailid");
        String date = liteHelperService.getCurrentDateTime("dd/MM/yyyy");
        String insertAuditLog = "INSERT INTO AUDITLOG (AUDITLOGID,UPDATEUSERID,UPDATETIMESTAMP,ACTION,DESCRIPTION,SOURCEID,AUDITDOMAINID) VALUES ('" + auditLogId + "','" + updateUserId + "', TO_DATE('" + date + "','DD/MM/YYYY'), 'DELETE', '" + description + "', '" + sourceId + "', '" + auditDomainId + "')";
        oracleService.executeQuery(insertAuditLog);
        String insertAuditLogDetail = "INSERT INTO AUDITLOGDETAIL (AUDITLOGDETAILID,COLUMNNAME,BEFOREVALUE,AFTERVALUE,AUDITLOGID,BEFOREPAYLOAD,AFTERPAYLOAD) VALUES ('" + auditLogDetailId + "','" + columnName + "', '" + beforeValue + "', '" + afterValue + "' ,'" + auditLogId + "',null,null) ";
        oracleService.executeQuery(insertAuditLogDetail);
        oracleService.executeQuery("commit");
    }

    @And("update data into DB with ranges")
    public void updateDataIntoDBWithRanges(List<Map<String, String>> dataTable) {
        Map<String, String> data = dataTable.get(0);
        String auditLogId = data.get("AUDITLOGID");
        String updateUserId = data.get("UPDATEUSERID");
        String description = data.get("DESCRIPTION");
        String sourceId = data.get("SOURCEID");
        String auditDomainId = data.get("AUDITDOMAINID");
        String auditLogDetailId = data.get("auditlogdetailid");
        String columnName = data.get("columnname");
        String beforeValue = data.get("beforevalue");
        String afterValue = data.get("aftervalue");
        String auditLogDetailIdEndRange = data.get("AUDITLOGDETAILID");
        String columnNameEndRange = data.get("COLUMNNAME");
        String beforeValueEndRange = data.get("BEFOREVALUE");
        String afterValueEndRange = data.get("AFTERVALUE");
        String fopCode = data.get("ColumnName");
        String auditLogDetailIdFopCode = data.get("Auditlogdetailid");
        String auditLogDetailIdMerchant = data.get("AuditLogdetailid");
        String auditLogDetailIdUserId = data.get("AuditLogDetailid");
        String merchantId = data.get("Columnname");
        String afterValueFop = data.get("Aftervalue");
        String afterValueUserId = data.get("AfterValue");
        String afterValueMerchant = data.get("afterValue");
        String lastUpdatedUserId = data.get("columnName");
        String date = liteHelperService.getCurrentDateTime("dd/MM/yyyy");
        String insertAuditLog = "INSERT INTO AUDITLOG (AUDITLOGID,UPDATEUSERID,UPDATETIMESTAMP,ACTION,DESCRIPTION,SOURCEID,AUDITDOMAINID) VALUES ('" + auditLogId + "','" + updateUserId + "', TO_DATE('" + date + "','DD/MM/YYYY'), 'UPDATE', '" + description + "', '" + sourceId + "', '" + auditDomainId + "')";
        oracleService.executeQuery(insertAuditLog);
        String insertAuditLogDetailStartRange = "INSERT INTO AUDITLOGDETAIL (auditlogdetailid,columnname,beforevalue,aftervalue,AUDITLOGID,BEFOREPAYLOAD,AFTERPAYLOAD) VALUES ('" + auditLogDetailId + "','" + columnName + "', '" + beforeValue + "', '" + afterValue + "' ,'" + auditLogId + "',null,null) ";
        oracleService.executeQuery(insertAuditLogDetailStartRange);
        String insertAuditLogDetailEndRange = "INSERT INTO AUDITLOGDETAIL (AUDITLOGDETAILID,COLUMNNAME,BEFOREVALUE,AFTERVALUE,AUDITLOGID,BEFOREPAYLOAD,AFTERPAYLOAD) VALUES ('" + auditLogDetailIdEndRange + "','" + columnNameEndRange + "', '" + beforeValueEndRange + "', '" + afterValueEndRange + "' ,'" + auditLogId + "',null,null) ";
        oracleService.executeQuery(insertAuditLogDetailEndRange);
        String insertAuditLogDetailFop = "INSERT INTO AUDITLOGDETAIL (Auditlogdetailid,ColumnName,BEFOREVALUE,Aftervalue,AUDITLOGID,BEFOREPAYLOAD,AFTERPAYLOAD) VALUES ('" + auditLogDetailIdFopCode + "','" + fopCode + "', null, '" + afterValueFop + "' ,'" + auditLogId + "',null,null) ";
        oracleService.executeQuery(insertAuditLogDetailFop);
        String insertAuditLogDetailMerchant = "INSERT INTO AUDITLOGDETAIL (AuditLogdetailid,Columnname,BEFOREVALUE,afterValue,AUDITLOGID,BEFOREPAYLOAD,AFTERPAYLOAD) VALUES ('" + auditLogDetailIdMerchant + "','" + merchantId + "', null, '" + afterValueMerchant + "' ,'" + auditLogId + "',null,null) ";
        oracleService.executeQuery(insertAuditLogDetailMerchant);
        String insertAuditLogDetailUserId = "INSERT INTO AUDITLOGDETAIL (AuditLogDetailid,columnName,BEFOREVALUE,AfterValue,AUDITLOGID,BEFOREPAYLOAD,AFTERPAYLOAD) VALUES ('" + auditLogDetailIdUserId + "','" + lastUpdatedUserId + "', null, '" + afterValueUserId + "' ,'" + auditLogId + "',null,null) ";
        oracleService.executeQuery(insertAuditLogDetailUserId);
        oracleService.executeQuery("commit");
    }

    @And("Delete the inserted data into DB for multiple ranges for ADD")
    public void deleteTheInsertedDataIntoDBMultipleRangesForADD(List<Map<String, String>> dataTable) {
        Map<String, String> data = dataTable.get(0);
        String auditLogDetailId = data.get("auditlogdetailid");
        String auditLogId = data.get("AUDITLOGID");
        String auditLogDetailIdEndRange = data.get("AUDITLOGDETAILID");
        String auditLogDetailIdFopCode = data.get("Auditlogdetailid");
        String auditLogDetailIdMerchant = data.get("AuditLogdetailid");
        String auditLogDetailIdUserId = data.get("AuditLogDetailid");
        String deleteAuditLogDetail = "delete  FROM AUDITLOGDETAIL where auditlogdetailid = '" + auditLogDetailId + "'";
        oracleService.executeQuery(deleteAuditLogDetail);
        String deleteAuditLogDetailEndRange = "delete  FROM AUDITLOGDETAIL where AUDITLOGDETAILID = '" + auditLogDetailIdEndRange + "'";
        oracleService.executeQuery(deleteAuditLogDetailEndRange);
        String deleteAuditLogDetailFopCode = "delete  FROM AUDITLOGDETAIL where Auditlogdetailid = '" + auditLogDetailIdFopCode + "'";
        oracleService.executeQuery(deleteAuditLogDetailFopCode);
        String deleteAuditLogDetailMerchant = "delete  FROM AUDITLOGDETAIL where AuditLogdetailid = '" + auditLogDetailIdMerchant + "'";
        oracleService.executeQuery(deleteAuditLogDetailMerchant);
        String deleteAuditLogDetailIdUserId = "delete  FROM AUDITLOGDETAIL where AuditLogDetailid = '" + auditLogDetailIdUserId + "'";
        oracleService.executeQuery(deleteAuditLogDetailIdUserId);
        String deleteAuditLog = "delete  FROM AUDITLOG where auditlogId = '" + auditLogId + "'";
        oracleService.executeQuery(deleteAuditLog);
        oracleService.executeQuery("commit");

    }

    @And("Delete the inserted data into DB for Single card")
    public void deleteTheInsertedDataIntoDBForSingleCard(List<Map<String, String>> dataTable) {
        Map<String, String> data = dataTable.get(0);
        String auditLogId = data.get("AUDITLOGID");
        String updateUserId = data.get("UPDATEUSERID");
        String description = data.get("DESCRIPTION");
        String sourceId = data.get("SOURCEID");
        String auditDomainId = data.get("AUDITDOMAINID");
        String auditLogDetailId = data.get("AUDITLOGDETAILID");
        String columnName = data.get("COLUMNNAME");
        String beforeValue = data.get("BEFOREVALUE");
        String afterValue = data.get("AFTERVALUE");
        String fopCode = data.get("ColumnName");
        String auditLogDetailIdFopCode = data.get("Auditlogdetailid");
        String auditLogDetailIdMerchant = data.get("AuditLogdetailid");
        String auditLogDetailIdUserId = data.get("AuditLogDetailid");
        String merchantId = data.get("Columnname");
        String lastUpdatedUserId = data.get("columnName");
        String afterValueFop = data.get("Aftervalue");
        String afterValueUserId = data.get("AfterValue");
        String afterValueMerchant = data.get("afterValue");
        String date = liteHelperService.getCurrentDateTime("dd/MM/yyyy");
        String insertAuditLog = "INSERT INTO AUDITLOG (AUDITLOGID,UPDATEUSERID,UPDATETIMESTAMP,ACTION,DESCRIPTION,SOURCEID,AUDITDOMAINID) VALUES ('" + auditLogId + "','" + updateUserId + "', TO_DATE('" + date + "','DD/MM/YYYY'), 'DELETE', '" + description + "', '" + sourceId + "', '" + auditDomainId + "')";
        oracleService.executeQuery(insertAuditLog);
        String insertAuditLogDetail = "INSERT INTO AUDITLOGDETAIL (AUDITLOGDETAILID,COLUMNNAME,BEFOREVALUE,AFTERVALUE,AUDITLOGID,BEFOREPAYLOAD,AFTERPAYLOAD) VALUES ('" + auditLogDetailId + "','" + columnName + "', '" + beforeValue + "', '" + afterValue + "' ,'" + auditLogId + "',null,null) ";
        oracleService.executeQuery(insertAuditLogDetail);
        String insertAuditLogDetailFop = "INSERT INTO AUDITLOGDETAIL (Auditlogdetailid,ColumnName,BEFOREVALUE,Aftervalue,AUDITLOGID,BEFOREPAYLOAD,AFTERPAYLOAD) VALUES ('" + auditLogDetailIdFopCode + "','" + fopCode + "', null, '" + afterValueFop + "' ,'" + auditLogId + "',null,null) ";
        oracleService.executeQuery(insertAuditLogDetailFop);
        String insertAuditLogDetailMerchant = "INSERT INTO AUDITLOGDETAIL (AuditLogdetailid,Columnname,BEFOREVALUE,afterValue,AUDITLOGID,BEFOREPAYLOAD,AFTERPAYLOAD) VALUES ('" + auditLogDetailIdMerchant + "','" + merchantId + "', null, '" + afterValueMerchant + "' ,'" + auditLogId + "',null,null) ";
        oracleService.executeQuery(insertAuditLogDetailMerchant);
        String insertAuditLogDetailUserId = "INSERT INTO AUDITLOGDETAIL (AuditLogDetailid,columnName,BEFOREVALUE,AfterValue,AUDITLOGID,BEFOREPAYLOAD,AFTERPAYLOAD) VALUES ('" + auditLogDetailIdUserId + "','" + lastUpdatedUserId + "', null, '" + afterValueUserId + "' ,'" + auditLogId + "',null,null) ";
        oracleService.executeQuery(insertAuditLogDetailUserId);
        oracleService.executeQuery("commit");
    }
}
