package com.sabre.cucumber.runner;

//import io.cucumber.junit.Cucumber;
import io.cucumber.testng.*;
//import org.junit.runner.RunWith;
import org.testng.annotations.*;

//@RunWith(Cucumber.class)
@CucumberOptions(
        features="src/main/java/com/sabre/cucumber/features/Batch/airlineBatchReversal.feature",
        glue={"stepdefs"},
        plugin= {"html:target/report.html",
                 "json:target/report.json"}
)
public class test_runner3 extends AbstractTestNGCucumberTests {
    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

//    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
//    public void scenario(PickleWrapper pickle, FeatureWrapper cucumberFeature) {
//        testNGCucumberRunner.runScenario(pickle.getPickle());
//    }

    @DataProvider
    public Object[][] scenarios() {
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        testNGCucumberRunner.finish();
    }

}