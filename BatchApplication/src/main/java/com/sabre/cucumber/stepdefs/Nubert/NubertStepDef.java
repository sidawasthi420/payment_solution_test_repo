package com.sabre.cucumber.stepdefs.Nubert;

import com.sabre.cucumber.lib.CommonLib;
import com.sabre.cucumber.pages.Nubert.NubertLandingPage;
import com.sabre.cucumber.pages.Nubert.NubertLogin;
import com.sabre.cucumber.state.TestContext1;
import com.sabre.ngpq.lite.services.base.AssertionService;
import com.sabre.ngpq.lite.services.base.BrowserService;
import com.sabre.ngpq.lite.services.base.SubstitutorService;
import com.sabre.payment.cucumber.TestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.hamcrest.CoreMatchers;

import java.util.Map;
import java.util.Set;

public class NubertStepDef {

    private TestContext1 testContext1;
    private TestContext testContext;
    private BrowserService browserService;
    private SubstitutorService substitutorService;
    private CommonLib commonLib;
    private NubertLandingPage nubertPage;
    private NubertLogin nubert;
    private AssertionService assertionService;

    public NubertStepDef(TestContext1 testContext1, TestContext testContext) {
        this.testContext1 = testContext1;
        this.testContext = testContext;
        this.browserService = testContext1.baseTestContext.getBrowserService();
        this.substitutorService = testContext1.baseTestContext.getSubstitutorService();
        this.assertionService = testContext1.baseTestContext.getAssertionService();
        this.commonLib = new CommonLib(testContext1);
        nubertPage = new NubertLandingPage(testContext1);
        nubert = new NubertLogin(testContext1);
    }

    @Then("^Click on Payment Apps option$")
    public void click_On_Payment_Apps_Options() throws Exception {
        nubertPage.clkPaymentApps();
    }

    @And("^Click on NubertGCP dropdown option$")
    public void click_On_Nubert_GCP_Dropdown_Option() throws Exception {
        nubertPage.clkNubertGCP();
    }

    @And("^Click on Nubert dropdown option$")
    public void click_On_Nubert_Dropdown_Option() throws Exception {
        nubertPage.clkNubert();
    }

    @Then("^Click on \"([^\"]*)\" GCP environment option$")
    public void clickOnGCPEnvironmentFromList(String environment) {
        String env = environment.toUpperCase();
        if (env.contains("DEV")) {
            nubertPage.clkNubertGCPDevIcon();
        } else if (env.contains("INT")) {
            nubertPage.clkNubertGCPIntIcon();
        } else if (env.contains("CERT")) {
            nubertPage.clkNubertGCPCertIcon();
        } else if (env.contains("PROD")) {
            nubertPage.clkNubertGCPProdIcon();
        } else if (env.contains("LOCAL")) {
            nubertPage.clkNubertGCPLocalIcon();
        }
    }

    @Then("^Click on Nubert Tulsa \"([^\"]*)\" environment option$")
    public void clickOnNubertEnvironmentFromList(String environment) {
        String env = environment.toUpperCase();
        if (env.contains("DEV")) {
            nubertPage.clkNubertDevIcon();
        } else if (env.contains("INT")) {
            nubertPage.clkNubertIntIcon();
        } else if (env.contains("CERT")) {
            nubertPage.clkNubertCertIcon();
        } else if (env.contains("PROD")) {
            nubertPage.clkNubertProdIcon();
        } else if (env.contains("LOCAL")) {
            nubertPage.clkNubertLocalIcon();
        }
    }

    @Then("^Click on Nubert GCP \"([^\"]*)\" environment option$")
    public void clickOnNubertGCPEnvironmentFromList(String environment) {
        String env = environment.toUpperCase();
        if (env.contains("DEV")) {
            nubertPage.clkNubertGCPDevIcon();
        } else if (env.contains("INT")) {
            nubertPage.clkNubertGCPIntIcon();
        } else if (env.contains("CERT")) {
            nubertPage.clkNubertGCPCertIcon();
        } else if (env.contains("PROD")) {
            nubertPage.clkNubertGCPProdIcon();
        } else if (env.contains("LOCAL")) {
            nubertPage.clkNubertGCPLocalIcon();
        }
    }

    @Then("^Verify that Nubert Tulsa \"([^\"]*)\" URL got opened successfully$")
    public void verifyNubertTulsaURLGotOpened(String environment) {
        String env = environment.toUpperCase();
        if (env.equals("DEV")) {
            String expected_URL = commonLib.loadFromResources("NubertConfig.properties").get("URL_Tulsa_Dev");
            String actual_URL = testContext1.baseTestContext.getBrowserService().driver.getCurrentUrl();

            if (actual_URL.equals(expected_URL) && nubert.verifyUsernameFieldDisplayed()) {
                System.out.println("Nubert Tulsa Dev URL got opened successfully");
            } else {
                System.out.println("Nubert Tulsa Dev URL did not opened successfully");
            }
        } else if (env.equals("INT")) {
            String expected_URL = commonLib.loadFromResources("NubertConfig.properties").get("URL_Tulsa_Int");
            String actual_URL = testContext1.baseTestContext.getBrowserService().driver.getCurrentUrl();

            if (actual_URL.equals(expected_URL) && nubert.verifyUsernameFieldDisplayed()) {
                System.out.println("Nubert Tulsa Int URL got opened successfully");
            } else {
                System.out.println("Nubert Tulsa Int URL did not opened successfully");
            }
        } else if (env.equals("CERT")) {
            String expected_URL = commonLib.loadFromResources("NubertConfig.properties").get("URL_Tulsa_Cert");
            String actual_URL = testContext1.baseTestContext.getBrowserService().driver.getCurrentUrl();

            if (actual_URL.equals(expected_URL) && nubert.verifyUsernameFieldDisplayed()) {
                System.out.println("Nubert Tulsa Cert URL got opened successfully");
            } else {
                System.out.println("Nubert Tulsa Cert URL did not opened successfully");
            }
        } else if (env.equals("PROD")) {
            String expected_URL = commonLib.loadFromResources("NubertConfig.properties").get("URL_Tulsa_Prod");
            String actual_URL = testContext1.baseTestContext.getBrowserService().driver.getCurrentUrl();

            if (actual_URL.equals(expected_URL) && nubert.verifyUsernameFieldDisplayed()) {
                System.out.println("Nubert Tulsa Prod URL got opened successfully");
            } else {
                System.out.println("Nubert Tulsa Prod URL did not opened successfully");
            }
        } else if (env.equals("LOCAL")) {
            String expected_URL = commonLib.loadFromResources("NubertConfig.properties").get("URL_Tulsa_Local");
                System.out.println("Nubert Tulsa Local URL did not opened successfully");
            }
        }

    @Then("^Verify that Nubert GCP \"([^\"]*)\" URL got opened successfully$")
    public void verifyNubertGCPURLGotOpened(String environment) {
        String env = environment.toUpperCase();
        if (env.equals("DEV")) {
            String expected_URL = commonLib.loadFromResources("NubertConfig.properties").get("URL_GCP_Dev");
            String actual_URL = testContext1.baseTestContext.getBrowserService().driver.getCurrentUrl();

            if (actual_URL.equals(expected_URL) && nubert.verifyUsernameFieldDisplayed()) {
                System.out.println("Nubert GCP Dev URL got opened successfully");
            } else {
                System.out.println("Nubert GCP Dev URL did not opened successfully");
            }
        } else if (env.equals("INT")) {
            String expected_URL = commonLib.loadFromResources("NubertConfig.properties").get("URL_GCP_Int");
            String actual_URL = testContext1.baseTestContext.getBrowserService().driver.getCurrentUrl();

            if (actual_URL.equals(expected_URL) && nubert.verifyUsernameFieldDisplayed()) {
                System.out.println("Nubert GCP Int URL got opened successfully");
            } else {
                System.out.println("Nubert GCP Int URL did not opened successfully");
            }
        } else if (env.equals("CERT")) {
            String expected_URL = commonLib.loadFromResources("NubertConfig.properties").get("URL_GCP_Cert");
            String actual_URL = testContext1.baseTestContext.getBrowserService().driver.getCurrentUrl();

            if (actual_URL.equals(expected_URL) && nubert.verifyUsernameFieldDisplayed()) {
                System.out.println("Nubert GCP Cert URL got opened successfully");
            } else {
                System.out.println("Nubert GCP Cert URL did not opened successfully");
            }
        } else if (env.equals("PROD")) {
            String expected_URL = commonLib.loadFromResources("NubertConfig.properties").get("URL_GCP_Prod");
            String actual_URL = testContext1.baseTestContext.getBrowserService().driver.getCurrentUrl();

            if (actual_URL.equals(expected_URL) && nubert.verifyUsernameFieldDisplayed()) {
                System.out.println("Nubert GCP Prod URL got opened successfully");
            } else {
                System.out.println("Nubert GCP Prod URL did not opened successfully");
            }
        } else if (env.equals("LOCAL")) {
            String expected_URL = commonLib.loadFromResources("NubertConfig.properties").get("URL_GCP_Local");
            String actual_URL = testContext1.baseTestContext.getBrowserService().driver.getCurrentUrl();

            if (actual_URL.equals(expected_URL) && nubert.verifyUsernameFieldDisplayed()) {
                System.out.println("Nubert GCP Local URL got opened successfully");
            } else {
                System.out.println("Nubert GCP Local URL did not opened successfully");
            }
        }
    }

    @And("^Navigate to newly opened tab$")
    public void navigateToOpenedTab() throws Exception {
        String currentWindowHandle = testContext1.baseTestContext.getBrowserService().driver.getWindowHandle();
        Set<String> allWindowHandles = testContext1.baseTestContext.getBrowserService().driver.getWindowHandles();
        try {
            for (String window : allWindowHandles) {
                if (!window.equals(currentWindowHandle)) {
                    testContext1.baseTestContext.getBrowserService().driver.switchTo().window(window);
                    System.out.println("Window switched successfully");
                }
            }
        } catch (Exception ex) {
            System.out.println("Issue will switching window");
        }
    }

    @Then("^Select the Search Criteria$")
    public void selectTheSearchCriteria(DataTable dataTable) {

        Map<String, String> map = dataTable.asMap(String.class, String.class);
        for (Map.Entry entry : map.entrySet()) {
            String fieldName = (String) entry.getKey();
            String fieldValue = (String) entry.getValue();

            switch (fieldName.toUpperCase()) {
                case "FROM DATE":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    nubertPage.setFromDate(fieldValue);
                    break;
                case "TO DATE":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    nubertPage.setToDate(fieldValue);
                    break;
                case "TXN ID":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    nubertPage.setSabreTxnID(fieldValue);
                    break;
                case "MERCH":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    nubertPage.setMerchant(fieldValue);
                    break;
                case "SUPPLIER":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    nubertPage.selectSupplier(fieldValue);
                    break;
                case "ACTION":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    nubertPage.selectAction(fieldValue);
                    break;
                case "SUBACTION":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    nubertPage.selectSubAction(fieldValue);
                    break;
                case "CHANNEL":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    nubertPage.selectChannel(fieldValue);
                    break;
                case "PNR":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    nubertPage.setPNR(fieldValue);
                    break;
                case "SERVICE":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    nubertPage.selectServiceName(fieldValue);
                    break;
                case "STATUS":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    nubertPage.selectTransactionStatus(fieldValue);
                    break;
                default:
            }
        }
    }

    @Then("^Select the Criteria for Search from file \"([^\"]*)\"$")
    public void selectSearchCriteria(String fileName, DataTable dataTable) throws InterruptedException {

        Map<String, String> map = dataTable.asMap(String.class, String.class);
        for (Map.Entry entry : map.entrySet()) {
            String fieldName = (String) entry.getKey();
            String fieldValue = (String) entry.getValue();

            switch (fieldName.toUpperCase()) {
                case "FROM DATE":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    Thread.sleep(5000);
                    String fromDate = commonLib.getSubstitutedValueBatchFile(fileName, "FromDate");
                    nubertPage.setFromDate(fromDate);
                    break;
                case "TO DATE":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    nubertPage.setToDate(fieldValue);
                    break;
                case "TXN ID":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    Thread.sleep(5000);
                    String txnID = commonLib.getSubstitutedValueBatchFile(fileName, "SabreTransactionID");
                    nubertPage.setSabreTxnID(txnID);
                    break;
                case "MERCH":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    nubertPage.setMerchant(fieldValue);
                    break;
                case "SUPPLIER":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    nubertPage.selectSupplier(fieldValue);
                    break;
                case "ACTION":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    nubertPage.selectAction(fieldValue);
                    break;
                case "SUBACTION":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    nubertPage.selectSubAction(fieldValue);
                    break;
                case "CHANNEL":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    nubertPage.selectChannel(fieldValue);
                    break;
                case "PNR":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    Thread.sleep(5000);
                    String pnr = commonLib.getSubstitutedValueBatchFile(fileName, "RecordLocator");
                    nubertPage.setPNR(pnr);
                    break;
                case "SERVICE":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    nubertPage.selectServiceName(fieldValue);
                    break;
                case "STATUS":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    nubertPage.selectTransactionStatus(fieldValue);
                    break;
                default:
            }
        }
    }

    @And("^Click on Search button in Nubert$")
    public void click_On_Search_button_in_Nubert() throws Exception {
        Thread.sleep(15000);
        nubertPage.clkSearchBtn();
        Thread.sleep(5000);
    }

    @Then("^Verify the displayed result from file \"([^\"]*)\"$")
    public void readTheSearchData(String fileName, DataTable dataTable) {
        Map<String, String> map = dataTable.asMap(String.class, String.class);
        for (Map.Entry entry : map.entrySet()) {
            String fieldName = (String) entry.getKey();
            String fieldValue = (String) entry.getValue();

            switch (fieldName.toUpperCase()) {
                case "TXN ID":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    String txnID = commonLib.getSubstitutedValueBatchFile(fileName, "SabreTransactionID");
                    assertionService.assertThat("Verify Sabre Transaction ID matches", nubertPage.getSearchData(fieldName), CoreMatchers.containsString(txnID));
                    break;
                case "MERCH":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    assertionService.assertThat("Verify Merchant matches", nubertPage.getSearchData(fieldName), CoreMatchers.containsString(fieldValue));
                    break;
                case "SUPPLIER":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    assertionService.assertThat("Verify Supplier matches", nubertPage.getSearchData(fieldName), CoreMatchers.containsString(fieldValue));
                    break;
                case "ACTION/SUBACTION":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    assertionService.assertThat("Verify Action/SubAction matches", nubertPage.getSearchData(fieldName), CoreMatchers.containsString(fieldValue));
                    break;
                case "CHANNEL":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    assertionService.assertThat("Verify Channel matches", nubertPage.getSearchData(fieldName), CoreMatchers.containsString(fieldValue));
                    break;
                case "PNR":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    String pnr = commonLib.getSubstitutedValueBatchFile(fileName, "RecordLocator");
                    assertionService.assertThat("Verify PNR matches", nubertPage.getSearchData(fieldName), CoreMatchers.containsString(pnr));
                    break;
                case "SERVICE":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    assertionService.assertThat("Verify Service matches", nubertPage.getSearchData(fieldName), CoreMatchers.containsString(fieldValue));
                    break;
                case "STATUS":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    assertionService.assertThat("Verify Status matches", nubertPage.getSearchData(fieldName), CoreMatchers.containsString(fieldValue));
                    break;
                default:
            }
        }
    }

    @Then("^Verify the result displayed$")
    public void readSearchData(DataTable dataTable) {
        Map<String, String> map = dataTable.asMap(String.class, String.class);
        for (Map.Entry entry : map.entrySet()) {
            String fieldName = (String) entry.getKey();
            String fieldValue = (String) entry.getValue();

            switch (fieldName.toUpperCase()) {
                case "TXN ID":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    assertionService.assertThat("Verify Sabre Transaction ID matches", nubertPage.getSearchData(fieldName), CoreMatchers.containsString(fieldValue));
                    break;
                case "MERCH":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    assertionService.assertThat("Verify Merchant matches", nubertPage.getSearchData(fieldName), CoreMatchers.containsString(fieldValue));
                    break;
                case "SUPPLIER":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    assertionService.assertThat("Verify Supplier matches", nubertPage.getSearchData(fieldName), CoreMatchers.containsString(fieldValue));
                    break;
                case "ACTION/SUBACTION":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    assertionService.assertThat("Verify Action/SubAction matches", nubertPage.getSearchData(fieldName), CoreMatchers.containsString(fieldValue));
                    break;
                case "CHANNEL":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    assertionService.assertThat("Verify Channel matches", nubertPage.getSearchData(fieldName), CoreMatchers.containsString(fieldValue));
                    break;
                case "PNR":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    assertionService.assertThat("Verify PNR matches", nubertPage.getSearchData(fieldName), CoreMatchers.containsString(fieldValue));
                    break;
                case "SERVICE":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    assertionService.assertThat("Verify Service matches", nubertPage.getSearchData(fieldName), CoreMatchers.containsString(fieldValue));
                    break;
                case "STATUS":
                    if (fieldValue == null) {
                        fieldValue = "";
                    }
                    assertionService.assertThat("Verify Status matches", nubertPage.getSearchData(fieldName), CoreMatchers.containsString(fieldValue));
                    break;
                default:
            }
        }
    }

    @Then("^Display all values$")
    public void displayAllValues() {
        nubertPage.getResultData();
    }
}