package com.sabre.cucumber.stepdefs.Nubert;

import com.sabre.cucumber.lib.CommonLib;
import com.sabre.cucumber.pages.Nubert.NubertLogin;
import com.sabre.cucumber.state.TestContext1;
import com.sabre.ngpq.lite.services.base.BrowserService;
import com.sabre.ngpq.lite.services.base.SubstitutorService;
import com.sabre.payment.cucumber.EnhancedOutput;
import com.sabre.payment.cucumber.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NubertLaunchStepDef {

    private final WebDriver driver;
    private TestContext1 testContext;
    private TestContext testContext1;
    private BrowserService browserService;
    private SubstitutorService substitutorService;
    private CommonLib commonLib;
    private NubertLogin nubert;

    public NubertLaunchStepDef(TestContext1 testContext, TestContext testContext1) {
        this.testContext = testContext;
        this.testContext1 = testContext1;
        this.driver = testContext.baseTestContext.getBrowserService().driver;
        this.browserService = testContext.baseTestContext.getBrowserService();
        this.substitutorService = testContext.baseTestContext.getSubstitutorService();
        this.commonLib = new CommonLib(testContext);
        nubert = new NubertLogin(testContext);
    }

    @Then("^Wait for \"([^\"]*)\" seconds$")
    public void waitForSeconds(String seconds) throws InterruptedException {
        String totalSeconds = seconds + "000";
        Thread.sleep(Long.parseLong(totalSeconds));
    }

    @Given("^Define variable \"([^\"]+)\" with time 00:00:00$")
    public void defineDateVariable(String varName) {
        String date = String.valueOf(java.time.LocalDate.now());
        String dateTime = date + " 00:00:00";
        this.testContext1.getVariables().put(varName, dateTime);
        this.testContext1.writeEnhancedOutput(EnhancedOutput.assignVariable(varName, dateTime));
    }

    @Then("^User opens the Nubert \"([^\"]*)\" application$")
    public void userOpensTheNubertApplication(String environment) {
        BrowserService.init();
        String env = environment.toUpperCase();
        if (env.contains("TULSADEVELOPMENT")) {
            String OCURL = commonLib.loadFromResources("NubertConfig.properties").get("URL_Tulsa_Dev");
            browserService.gotoURL(OCURL);
        } else if (env.contains("TULSAINTEGRATION")) {
            String OCURL = commonLib.loadFromResources("NubertConfig.properties").get("URL_Tulsa_Int");
            browserService.gotoURL(OCURL);
        } else if (env.contains("TULSACERTIFICATION")) {
            String OCURL = commonLib.loadFromResources("NubertConfig.properties").get("URL_Tulsa_Cert");
            browserService.gotoURL(OCURL);
        } else if (env.contains("TULSAPRODUCTION")) {
            String OCURL = commonLib.loadFromResources("NubertConfig.properties").get("URL_Tulsa_Prod");
            browserService.gotoURL(OCURL);
        } else if (env.contains("TULSALOCAL")) {
            String OCURL = commonLib.loadFromResources("NubertConfig.properties").get("URL_Tulsa_Local");
            browserService.gotoURL(OCURL);
        } else if (env.contains("GCPDEVELOPMENT")) {
            String OCURL = commonLib.loadFromResources("NubertConfig.properties").get("URL_GCP_Dev");
            browserService.gotoURL(OCURL);
        } else if (env.contains("GCPINTEGRATION")) {
            String OCURL = commonLib.loadFromResources("NubertConfig.properties").get("URL_GCP_Int");
            browserService.gotoURL(OCURL);
        } else if (env.contains("GCPCERTIFICATION")) {
            String OCURL = commonLib.loadFromResources("NubertConfig.properties").get("URL_GCP_Cert");
            browserService.gotoURL(OCURL);
        } else if (env.contains("GCPPRODUCTION")) {
            String OCURL = commonLib.loadFromResources("NubertConfig.properties").get("URL_GCP_Prod");
            browserService.gotoURL(OCURL);
        } else if (env.contains("GCPALOCAL")) {
            String OCURL = commonLib.loadFromResources("NubertConfig.properties").get("URL_GCP_Local");
            browserService.gotoURL(OCURL);
        }
    }

    @Then("^Verify that URL got opened successfully$")
    public void verifyURLGotOpenedSuccessfullyForNubert() {
        String actualTitle = testContext.baseTestContext.getBrowserService().driver.getTitle();
        String expectedTitle = "Nubert Login";

        if (actualTitle.equals(expectedTitle) && nubert.verifyUsernameFieldDisplayed()) {
            System.out.println("URL got opened successfully");
        } else {
            System.out.println("Issue in opening the URL");
        }
    }
}