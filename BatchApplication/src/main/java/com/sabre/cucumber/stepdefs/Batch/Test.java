package com.sabre.cucumber.stepdefs.Batch;

import com.sabre.cucumber.lib.CommonLib;
import com.sabre.cucumber.pages.Batch.Batch;
import com.sabre.cucumber.state.TestContext1;
import com.sabre.payment.cucumber.ConfigurationStep;
import com.sabre.payment.cucumber.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Test {

   private TestContext testContext;
   private Batch batch;
   private Properties properties;
   private CommonLib commonLib;

   public Test(TestContext testContext, TestContext1 testContext1) {
      this.testContext = testContext;
      batch = new Batch(testContext1, testContext);
      this.commonLib = new CommonLib(testContext1);
   }

   @Given("^Test File$")
   public void TestFile() {
      System.out.println("Test File");
   }

   @When("^Tested the feature$")
   public void TestFile1() {
      System.out.println("Tested the feature");
   }

   @Then("^Test is passed$")
   public void TestFile2() {
      System.out.println("Test is passed");
   }

   @And("^Store response in a \"([^\"]+)\" file$")
   public void store_response_in_file(String filename) throws IOException {
      String file = filename + ".properties";
      String fileLocation = System.getProperty("user.dir")+"/src/main/resources/JobsData/";
      System.out.println(fileLocation);
      batch.createAndClearPropertiesFile(file, fileLocation);

      String pnr = "TEST1A";
      String txnID = "84357464623462323";
      String currentDate = "03-10-2023";
      properties = new Properties();
      properties.put("SabreTransactionID", txnID);
      properties.put("RecordLocator", pnr);
      properties.put("CurrentDate", currentDate);
      FileOutputStream fr = new FileOutputStream(fileLocation+file);
      properties.store(fr,"Properties");
   }

   @Then("^Display the stored data from \"([^\"]+)\" file$")
   public void display_stored_data_from_file(String filename) throws IOException {
      String file = filename + ".properties";
      String PNR = commonLib.loadFromBatchResources(file).get("RecordLocator");
      String TransactionID = commonLib.loadFromBatchResources(file).get("SabreTransactionID");
      String Date = commonLib.loadFromBatchResources(file).get("CurrentDate");
      System.out.println("Record Locator is :- "+ PNR);
      System.out.println("TransactionID is :- "+ TransactionID);
      System.out.println("Date Locator is :- "+ Date);
   }
}