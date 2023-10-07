package com.sabre.cucumber.pages.Nubert;

import com.sabre.cucumber.state.TestContext1;
import com.sabre.ngpq.lite.services.base.BrowserService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class NubertLandingPage {

    private TestContext1 testContext;
    private WebDriver driver;
    private BrowserService browserService;

    @FindBy(xpath = "//li[contains(@class,'spark-no-animate')]//a[text()='Payment Apps']/following-sibling::button")
    private WebElement paymentApps;

    @FindBy(xpath = "//a[text()='Nubert-GCP']//following-sibling::button[@title='Expand' and not(@tabindex='-1')]")
    private WebElement nubertGCPDropdown;

    @FindBy(xpath = "//a[text()='Nubert']//following-sibling::button[@title='Expand' and not(@tabindex='-1')]")
    private WebElement nubertDropdown;

    @FindBy(xpath = "//a[text()='Nubert-GCP']//following-sibling::button[@title='Expand' and not(@tabindex='-1')]//following::a[text()='int'][1]")
    private WebElement nubertGCPInt;

    @FindBy(xpath = "//a[text()='Nubert-GCP']//following-sibling::button[@title='Expand' and not(@tabindex='-1')]//following::a[text()='dev'][1]")
    private WebElement nubertGCPDev;

    @FindBy(xpath = "//a[text()='Nubert-GCP']//following-sibling::button[@title='Expand' and not(@tabindex='-1')]//following::a[text()='cert'][1]")
    private WebElement nubertGCPCert;

    @FindBy(xpath = "//a[text()='Nubert-GCP']//following-sibling::button[@title='Expand' and not(@tabindex='-1')]//following::a[text()='prod'][1]")
    private WebElement nubertGCPProd;

    @FindBy(xpath = "//a[text()='Nubert-GCP']//following-sibling::button[@title='Expand' and not(@tabindex='-1')]//following::a[text()='local'][1]")
    private WebElement nubertGCPLocal;

    @FindBy(xpath = "//a[text()='Nubert']//following-sibling::button[@title='Expand' and not(@tabindex='-1')]//following::a[text()='int'][1]")
    private WebElement nubertInt;

    @FindBy(xpath = "//a[text()='Nubert']//following-sibling::button[@title='Expand' and not(@tabindex='-1')]//following::a[text()='dev'][1]")
    private WebElement nubertDev;

    @FindBy(xpath = "//a[text()='Nubert']//following-sibling::button[@title='Expand' and not(@tabindex='-1')]//following::a[text()='cert'][1]")
    private WebElement nubertCert;

    @FindBy(xpath = "//a[text()='Nubert']//following-sibling::button[@title='Expand' and not(@tabindex='-1')]//following::a[text()='prod'][1]")
    private WebElement nubertProd;

    @FindBy(xpath = "//a[text()='Nubert']//following-sibling::button[@title='Expand' and not(@tabindex='-1')]//following::a[text()='local'][1]")
    private WebElement nubertLocal;

    @FindBy(id = "fromDateInput")
    private WebElement fromDateInput;

    @FindBy(id = "toDateInput")
    private WebElement toDateInput;

    @FindBy(name = "transactionId")
    private WebElement sabreTxnID;

    @FindBy(name = "merchant")
    private WebElement merchantID;

    @FindBy(id = "supplierId")
    private WebElement supplier;

    @FindBy(id = "actionId")
    private WebElement action;

    @FindBy(id = "subActionId")
    private WebElement subAction;

    @FindBy(id = "channel")
    private WebElement channel;

    @FindBy(name = "pnr")
    private WebElement pnr_number;

    @FindBy(id = "serviceName")
    private WebElement serviceName;

    @FindBy(id = "appStatus")
    private WebElement txnStatus;

    @FindBy(xpath = "//button[text()='Search']")
    private WebElement searchBtn;

    @FindBy(xpath = "//table[@rules='all']//th[not(@class='ng-hide')]")
    private List<WebElement> tableLabels;

    @FindBy(xpath = "//table[@rules='all']//td[not(@class='ng-hide' or @class='ng-binding ng-hide')]")
    private List<WebElement> tableValues;


    public NubertLandingPage(TestContext1 testContext) {
        this.testContext = testContext;
        this.driver = testContext.baseTestContext.getBrowserService().driver;
        this.browserService = testContext.baseTestContext.getBrowserService();
        PageFactory.initElements(this.driver, this);
    }

    public void clkPaymentApps() {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOf(paymentApps));
        browserService.click(paymentApps);
    }

    public void clkNubertGCP() {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOf(nubertGCPDropdown));
        browserService.click(nubertGCPDropdown);
    }

    public void clkNubert() {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOf(nubertDropdown));
        browserService.click(nubertDropdown);
    }

    public void clkNubertGCPIntIcon() {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOf(nubertGCPInt));
        browserService.click(nubertGCPInt);
    }

    public void clkNubertGCPDevIcon() {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOf(nubertGCPDev));
        browserService.click(nubertGCPDev);
    }

    public void clkNubertGCPCertIcon() {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOf(nubertGCPCert));
        browserService.click(nubertGCPCert);
    }

    public void clkNubertGCPProdIcon() {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOf(nubertGCPProd));
        browserService.click(nubertGCPProd);
    }

    public void clkNubertGCPLocalIcon() {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOf(nubertGCPLocal));
        browserService.click(nubertGCPLocal);
    }

    public void clkNubertIntIcon() {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOf(nubertInt));
        browserService.click(nubertInt);
    }

    public void clkNubertDevIcon() {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOf(nubertDev));
        browserService.click(nubertDev);
    }

    public void clkNubertCertIcon() {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOf(nubertCert));
        browserService.click(nubertCert);
    }

    public void clkNubertProdIcon() {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOf(nubertProd));
        browserService.click(nubertProd);
    }

    public void clkNubertLocalIcon() {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOf(nubertLocal));
        browserService.click(nubertLocal);
    }

    public void setFromDate(String fromDate) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOf(fromDateInput));
        browserService.clearAndSetText(fromDateInput, fromDate);
    }

    public void setToDate(String toDate) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOf(toDateInput));
        browserService.clearAndSetText(toDateInput, toDate);
    }

    public void setSabreTxnID(String txnID) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOf(sabreTxnID));
        browserService.clearAndSetText(sabreTxnID, txnID);
    }

    public void setMerchant(String merchant) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOf(merchantID));
        browserService.clearAndSetText(merchantID, merchant);
    }

    public void selectSupplier(String supplier_Id) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOf(supplier));
        Select sup_id = new Select(supplier);
        sup_id.selectByVisibleText(supplier_Id);
    }

    public void selectAction(String actionName) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOf(action));
        Select action_name = new Select(action);
        action_name.selectByVisibleText(actionName);
    }

    public void selectSubAction(String subActionName) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOf(subAction));
        Select action_name = new Select(subAction);
        action_name.selectByVisibleText(subActionName);
    }

    public void selectChannel(String channelName) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOf(channel));
        Select channel_id = new Select(channel);
        channel_id.selectByVisibleText(channelName);
    }

    public void setPNR(String PNR) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOf(pnr_number));
        browserService.clearAndSetText(pnr_number, PNR);
    }

    public void selectServiceName(String service_Name) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOf(serviceName));
        Select service_name = new Select(serviceName);
        service_name.selectByVisibleText(service_Name);
    }

    public void selectTransactionStatus(String transaction_Status) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOf(txnStatus));
        Select status = new Select(txnStatus);
        status.selectByVisibleText(transaction_Status);
    }

    public void clkSearchBtn() {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOf(searchBtn));
        browserService.click(searchBtn);
    }

    public String getSearchData(String key) {
        String value = null;
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOf(tableLabels.get(0)));
        Map<String, String> table = new LinkedHashMap<String, String>();

        for (int i = 1; i < tableLabels.size(); i++) {
            String labelValue = "";
            WebElement we = tableValues.get(i);
            List<WebElement> spanTags = we.findElements(By.tagName("span"));
            for (WebElement ref : spanTags) {
                labelValue = labelValue + ref.getText();
            }
            table.put(tableLabels.get(i).getText(), labelValue);
        }

        for (Map.Entry entry : table.entrySet()) {
            if (entry.getKey().equals(key)) {
                value = (String) entry.getValue();
                break;
            }
        }
        return value;
    }

    public void getResultData() {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOf(tableLabels.get(0)));
        Map<String, String> table = new LinkedHashMap<String, String>();

        for (int i = 1; i < tableLabels.size(); i++) {
            String value = "";
            WebElement we = tableValues.get(i);

            List<WebElement> spanTags = we.findElements(By.tagName("span"));
            for (WebElement ref : spanTags) {
                value = value + ref.getText();
            }
            table.put(tableLabels.get(i).getText(), value);
        }

        for (Map.Entry entry : table.entrySet()) {
            System.out.println(entry.getKey() + "  :  " + entry.getValue());
        }
    }
}