package com.sabre.cucumber.pws;

public class PwsTestContext {
    private String environment;
    private String service;
    private String transport;
    private String mode;
    private static boolean databaseLoaded;
    private static boolean serviceStarted;

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public boolean isDatabaseLoaded() {
        return databaseLoaded;
    }

    public void setDatabaseLoaded(boolean databaseLoaded) {
        this.databaseLoaded = databaseLoaded;
    }

    public boolean isServiceStarted() {
        return serviceStarted;
    }

    public void setServiceStarted(boolean serviceStarted) {
        PwsTestContext.serviceStarted = serviceStarted;
    }

}
