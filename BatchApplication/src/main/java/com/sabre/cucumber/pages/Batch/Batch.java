package com.sabre.cucumber.pages.Batch;

import com.sabre.cucumber.state.TestContext1;
import com.sabre.ngpq.lite.services.base.BrowserService;
import com.sabre.payment.cucumber.TestContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Batch {

    private TestContext1 testContext1;
    private TestContext testContext;
    private WebDriver driver;
    private BrowserService browserService;
    private Properties properties;

    public Batch(TestContext1 testContext1, TestContext testContext) {
        this.testContext1 = testContext1;
        this.testContext = testContext;
        this.driver = testContext1.baseTestContext.getBrowserService().driver;
        this.browserService = testContext1.baseTestContext.getBrowserService();
        PageFactory.initElements(this.driver, this);
    }

   public void createAndClearPropertiesFile(String fileName, String filePath) throws IOException {
       File folder = new File(filePath);
       File[] files = folder.listFiles();
       for (File file : files) {
           if (file.isFile() && file.getName().contains(fileName)) {
               file.delete();
           }
       }
       File file = new File(filePath+fileName);
       file.createNewFile();
   }

    public void updatingPropertiesFile(String fileName, String filePath) throws IOException {
        String pnr = testContext.getVariables().get("PNR").toString();
        String txnID = testContext.getVariables().get("SabreTransactionID").toString();
        String currentDate = testContext.getVariables().get("CurrentDate").toString();
        properties = new Properties();
        properties.put("SabreTransactionID", txnID);
        properties.put("RecordLocator", pnr);
        properties.put("FromDate", currentDate);
        FileOutputStream fr = new FileOutputStream(filePath+fileName);
        properties.store(fr,"Properties");
    }
}