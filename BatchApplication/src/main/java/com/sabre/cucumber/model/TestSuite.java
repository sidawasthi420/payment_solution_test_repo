package com.sabre.cucumber.model;

import java.util.List;

public class TestSuite {

    private String testSuiteName;
    private List<TestCase> _testCaseDetails;

    public String getTestSuiteName() {
        return testSuiteName;
    }

    public void setTestSuiteName(String testSuiteName) {
        this.testSuiteName = testSuiteName;
    }

    public List<TestCase> get_testCaseDetails() {
        return _testCaseDetails;
    }

    public void set_testCaseDetails(List<TestCase> _testCaseDetails) {
        this._testCaseDetails = _testCaseDetails;
    }
}
