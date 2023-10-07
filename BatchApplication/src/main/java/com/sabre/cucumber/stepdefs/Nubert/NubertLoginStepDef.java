package com.sabre.cucumber.stepdefs.Nubert;

import com.sabre.cucumber.lib.CommonLib;
import com.sabre.cucumber.pages.Batch.Batch;
import com.sabre.cucumber.pages.Nubert.NubertLogin;
import com.sabre.cucumber.state.TestContext1;
import com.sabre.ngpq.cucumber.state.BaseTestContext;
import com.sabre.ngpq.lite.services.base.AssertionService;
import com.sabre.ngpq.lite.services.base.BrowserService;
import com.sabre.ngpq.lite.services.base.SubstitutorService;
import com.sabre.payment.cucumber.TestContext;
import io.cucumber.java.en.And;
import org.hamcrest.CoreMatchers;

import java.io.IOException;

public class NubertLoginStepDef {

    private TestContext testContext;
    private BrowserService browserService;
    private SubstitutorService substitutorService;
    private CommonLib commonLib;
    private NubertLogin nubertLogin;
    private AssertionService assertionService;
    private TestContext1 testContext1;
    private Batch batch;

    public NubertLoginStepDef(TestContext testContext, TestContext1 testContext1) {
        this.testContext = testContext;
        this.testContext1 = testContext1;
        this.browserService = testContext1.baseTestContext.getBrowserService();
        this.assertionService = testContext1.baseTestContext.getAssertionService();
        this.substitutorService = testContext1.baseTestContext.getSubstitutorService();
        this.commonLib = new CommonLib(testContext1);
        nubertLogin = new NubertLogin(testContext1);
        batch = new Batch(testContext1, testContext);
    }

    @And("^User enters username and password on Nubert loginpage$")
    public void user_enters_username_and_password_on_Nubert_loginpage() throws Exception {
        String NubertUsername = commonLib.getSubstitutedValue("NubertConfig", "username");
        String NubertPassword = commonLib.getSubstitutedValue("NubertConfig", "password");
        nubertLogin.signIn(NubertUsername, NubertPassword);
    }

    @And("^Verify that login is successful and Search button got displayed$")
    public void verify_login_successful_on_Nubert_loginpage() throws Exception {
        assertionService.assertThat("Verify Login successful & Search Button displayed", nubertLogin.verifySearchButtonDisplayed(), CoreMatchers.containsString("true"));
    }

    @And("^display value on console \"([^\"]+)\"$")
    public void display_loginpage(String sout) throws Exception {
        System.out.println(testContext.getVariables().get(sout).toString());
    }

    @And("^Store response in \"([^\"]+)\" file$")
    public void store_response_in_file(String filename) throws IOException {
        String file = filename + ".properties";
        String fileLocation = System.getProperty("user.dir")+"/src/main/resources/JobsData/";
        System.out.println(fileLocation);
        batch.createAndClearPropertiesFile(file, fileLocation);
        batch.updatingPropertiesFile(file, fileLocation);
    }
}
