package com.sabre.cucumber.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
       features = {"src/main/java/com/sabre/cucumber/features/Batch/airlineBatchReversal.feature"},
        glue = "com.sabre.cucumber.stepdefs"
)

public class testrunner { }