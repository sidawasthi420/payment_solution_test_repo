package com.sabre.cucumber.events;

import com.sabre.cucumber.state.TestContext1;
import io.cucumber.java.After;

public class Hooks {

    private TestContext1 testContext;

    public Hooks(TestContext1 testContext) {
        this.testContext = testContext;
    }

    @After(order = 40)
    public void cucumberTearDown() {
        //Close Browser
//        if (testContext.baseTestContext.getBrowserService().driver != null) {
//            testContext.baseTestContext.getBrowserService().closeDriver();
//        }
    }

}
