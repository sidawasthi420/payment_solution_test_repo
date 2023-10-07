package com.sabre.cucumber.model;

public class TestCase {


    private String TestCasePath;
    private String Product;
    private String ProductArea;
    private String Functionality;
    private String TestCaseId;
    private String TestCaseName;
    private String ComponentName;

    public String getTestCaseId() {
        return TestCaseId;
    }

    public void setTestCaseId(String testCaseId) {
        this.TestCaseId = testCaseId;
    }

    public String getTestCaseName() {
        return TestCaseName;
    }

    public void setTestCaseName(String testCaseName) {
        this.TestCaseName = testCaseName;
    }

    public String getTestCasePath() {
        return TestCasePath;
    }

    public void setTestCasePath(String testCasePath) {
        this.TestCasePath = testCasePath;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String product) {
        this.Product = product;
    }

    public String getProductArea() {
        return ProductArea;
    }

    public void setProductArea(String productArea) {
        this.ProductArea = productArea;
    }

    public String getFunctionality() {
        return Functionality;
    }

    public void setFunctionality(String functionality) {
        this.Functionality = functionality;
    }

    public String getComponentName() {
        return ComponentName;
    }

    public void setComponentName(String componentName) {
        ComponentName = componentName;
    }
}
