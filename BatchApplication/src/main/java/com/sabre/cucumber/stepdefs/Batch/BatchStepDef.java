package com.sabre.cucumber.stepdefs.Batch;

import com.sabre.cucumber.lib.CommonLib;
import com.sabre.cucumber.pages.Batch.Batch;
import com.sabre.cucumber.pages.Nubert.NubertLogin;
import com.sabre.cucumber.state.TestContext1;
import com.sabre.ngpq.lite.services.base.AssertionService;
import com.sabre.ngpq.lite.services.base.BrowserService;
import com.sabre.ngpq.lite.services.base.SubstitutorService;
import com.sabre.ngpq.platform.services.DBService;
import com.sabre.payment.cucumber.TestContext;
import io.cucumber.java.en.And;
import org.hamcrest.CoreMatchers;

import java.io.IOException;

public class BatchStepDef {

    private TestContext1 testContext;
    private Batch batch;

    public BatchStepDef(TestContext1 testContext1, TestContext testContext) {
        this.testContext = testContext1;
         batch = new Batch(testContext1, testContext);
    }

  /*  @And("^Store response in \"([^\"]+)\" file$")
    public void store_response_in_file(String filename) throws IOException {
      String file = filename + ".properties";
      String fileLocation = System.getProperty("user.dir")+"src/main/resources/JobsData/";
      batch.createAndClearPropertiesFile(file, fileLocation);
      batch.updatingPropertiesFile(file, fileLocation);
    }*/
}
