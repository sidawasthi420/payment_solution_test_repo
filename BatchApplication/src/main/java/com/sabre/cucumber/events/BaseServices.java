package com.sabre.cucumber.events;

import com.sabre.ngpq.lite.services.base.*;

public class BaseServices {

    public AssertionService assertionService;
    public SubstitutorService substitutorService;
    public BrowserService browserService;
    public RestAssuredService restAssuredService;

    public BaseServices(AssertionService assertionService, SubstitutorService substitutorService, BrowserService browserService,
                        RestAssuredService restAssuredService) {
        this.assertionService = assertionService;
        this.substitutorService = substitutorService;
        this.browserService = browserService;
        this.restAssuredService = restAssuredService;
    }
}
