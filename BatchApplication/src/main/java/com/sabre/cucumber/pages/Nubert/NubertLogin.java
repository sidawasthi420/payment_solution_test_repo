package com.sabre.cucumber.pages.Nubert;

import com.sabre.cucumber.state.TestContext1;
import com.sabre.ngpq.lite.services.base.BrowserService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NubertLogin {

    private TestContext1 testContext;
    private WebDriver driver;
    private BrowserService browserService;

    @FindBy(name = "username")
    private WebElement username;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(id = "submit")
    private WebElement login;

    @FindBy(xpath = "//button[text()='Search']")
    private WebElement searchButton;

    public NubertLogin(TestContext1 testContext) {
        this.testContext = testContext;
        this.driver = testContext.baseTestContext.getBrowserService().driver;
        this.browserService = testContext.baseTestContext.getBrowserService();
        PageFactory.initElements(this.driver, this);
    }

    public void signIn(String username, String password) {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOf(login));
        browserService.setText(this.username, username);
        browserService.setText(this.password, password);
        browserService.click(login);
    }

    public boolean verifyUsernameFieldDisplayed() {
        boolean element = false;
        try {
            element = browserService.isElementPresent(username);
        } catch (Exception ex) {
        }

        return element;
    }

    public String verifySearchButtonDisplayed() throws InterruptedException {
        String status = "";
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOf(searchButton));
        if (searchButton.isDisplayed()) {
            status = "true";
        } else {
            status = "false";
        }
        return status;
    }
}