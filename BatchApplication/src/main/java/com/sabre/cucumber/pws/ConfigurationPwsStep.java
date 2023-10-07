package com.sabre.cucumber.pws;

import com.sabre.payment.cucumber.*;
import com.sabre.payment.cucumber.builder.ConfigurationBuilder;
import com.sabre.payment.cucumber.fitnesse.core.Configuration;
import com.sabre.payment.cucumber.fitnesse.core.Configuration.Mode;
import com.sabre.payment.cucumber.fitnesse.core.Configuration.TransportType;
import com.sabre.payment.cucumber.fitnesse.core.ConfigurationFixture;
import com.sabre.payment.pss.codegen.generator.ClassGenerator;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import org.hamcrest.Matchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;

public class ConfigurationPwsStep {
    private static final Logger logger = LoggerFactory.getLogger(ConfigurationPwsStep.class);
    private static final String PAYMENT_TRANSPORT = "Payment";
    private static final String REMOTE_PAYMENT_TRANSPORT = "RemotePayment";
    private static final String FRAUD_CHECK_TRANSPORT = "FraudCheck";
    private static final String PSS_PAYMENT_TRANSPORT = "PssPayment";
    private static final String PAYMENT_QUERY_TRANSPORT = "PaymentQuery";
    private static final String PAYMENT_USG_ACTION = "PaymentRQ";
    private static final String REMOTE_PAYMENT_USG_ACTION = "ePaymentRQ";
    private static final String FRAUD_CHECK_USG_ACTION = "FraudCheckRQ";
    private static final String PAYMENT_QUERY_USG_ACTION = "PaymentQueryRQ";
    private static final String PAYMENT_DEV_MOMCONFIG_XML = "payment-dev-momconfig.xml";
    private static final String PAYMENT_USG_SERVICE = "PaymentSvc-USG-Service";
    private static final String REMOTE_PAYMENT_USG_SERVICE = "RemotePaymentSvc-USG-Service";
    private static final String FRAUD_CHECK_USG_SERVICE = "FraudCheckSvc-USG-Service";
    private static final String PSS_PAYMENT_USG_SERVICE = "PSSPaymentSvc-PSS-Service";
    private static final String PAYMENT_QUERY_USG_SERVICE = "PaymentQuerySvc-USG-Service";

    private static final String PAYMENT_INT_MOMCONFIG_XML = "payment-int-momconfig.xml";
    private static final String PAYMENT_CERT_MOMCONFIG_XML = "payment-cert-momconfig.xml";

    private static String DEV_USG_URL = "http://sws-dev.sabre.com";
//    private static String INT_USG_URL = "http://sws-sts.internal.cert.sabre.com";
//    private static String CERT_USG_URL = "http://sws-crt.internal.cert.sabre.com";

    private static String INT_GCP = "https://webservices-int.global.apps.certpci.sabre-gcp.com";
    private static String CERT_GCP = "https://webservices.global.apps.certpci.sabre-gcp.com";

    public ConfigurationFixture configuration;
    private TestContext<PwsTestContext> testContext;
    private PwsTestContext pwsTestContext;

    public ConfigurationPwsStep(TestContext<PwsTestContext> testContext) {
        this.testContext = testContext;
        pwsTestContext = new PwsTestContext();
        testContext.setApplicationTestContext(pwsTestContext);
        configuration = new ConfigurationFixture();
    }

    @Before
    public void setupCucumberConfiguration() {
        if (ConfigurationFixture.getConfiguration() == null) {
            logger.info("Building pws configuration");
            ConfigurationBuilder builder = new ConfigurationBuilder();
            builder.addEnvironment("dev")
                    .addTransport(PAYMENT_TRANSPORT).setUsgConfig(DEV_USG_URL, PAYMENT_USG_ACTION).setMomConfig(PAYMENT_DEV_MOMCONFIG_XML, PAYMENT_USG_SERVICE).endTransport()
                    .addTransport(REMOTE_PAYMENT_TRANSPORT).setUsgConfig(DEV_USG_URL, REMOTE_PAYMENT_USG_ACTION).setMomConfig(PAYMENT_DEV_MOMCONFIG_XML, REMOTE_PAYMENT_USG_SERVICE).endTransport()
                    .addTransport(FRAUD_CHECK_TRANSPORT).setUsgConfig(DEV_USG_URL, FRAUD_CHECK_USG_ACTION).setMomConfig(PAYMENT_DEV_MOMCONFIG_XML, FRAUD_CHECK_USG_SERVICE).endTransport()
                    .addTransport(PSS_PAYMENT_TRANSPORT).setMomConfig(PAYMENT_DEV_MOMCONFIG_XML, PSS_PAYMENT_USG_SERVICE).endTransport()
                    .addTransport(PAYMENT_QUERY_TRANSPORT).setUsgConfig(DEV_USG_URL, PAYMENT_QUERY_USG_ACTION).setMomConfig(PAYMENT_DEV_MOMCONFIG_XML, PAYMENT_QUERY_USG_SERVICE).endTransport()
                    .addTransport("ssg").setUsgConfig(INT_GCP, PAYMENT_USG_ACTION).setMomConfig(PAYMENT_DEV_MOMCONFIG_XML, "PaymentSvc-REALSSGPYMTDEV-Service").endTransport()
                    .setPssDataSource("access.pkge.sabre.com", "724919", "PAY2006", "HDQTSG", "79440", "AAO", "LSC")
                    .endEnvironment()
                    .addEnvironment("int")
                    .addTransport(PAYMENT_TRANSPORT).setUsgConfig(INT_GCP, PAYMENT_USG_ACTION).setMomConfig(PAYMENT_INT_MOMCONFIG_XML, PAYMENT_USG_SERVICE).endTransport()
                    .addTransport(REMOTE_PAYMENT_TRANSPORT).setUsgConfig(INT_GCP, REMOTE_PAYMENT_USG_ACTION).setMomConfig(PAYMENT_INT_MOMCONFIG_XML, REMOTE_PAYMENT_USG_SERVICE).endTransport()
                    .addTransport(FRAUD_CHECK_TRANSPORT).setUsgConfig(INT_GCP, FRAUD_CHECK_USG_ACTION).setMomConfig(PAYMENT_INT_MOMCONFIG_XML, FRAUD_CHECK_USG_SERVICE).endTransport()
                    .addTransport(PSS_PAYMENT_TRANSPORT).setMomConfig(PAYMENT_INT_MOMCONFIG_XML, PSS_PAYMENT_USG_SERVICE).endTransport()
                    .addTransport(PAYMENT_QUERY_TRANSPORT).setUsgConfig(INT_GCP, PAYMENT_QUERY_USG_ACTION).setMomConfig(PAYMENT_INT_MOMCONFIG_XML, PAYMENT_QUERY_USG_SERVICE).endTransport()
                    .addTransport("ssg").setUsgConfig(INT_GCP, PAYMENT_USG_ACTION).setMomConfig(PAYMENT_INT_MOMCONFIG_XML, "PaymentSvc-REALSSGPYMTDEV-Service").endTransport()
                    .setPssDataSource("access.tsts.sabre.com", "724919", "PAY2006", "HDQTSG", "79440", "AAO", "LSC")
                    .endEnvironment()
                    .addEnvironment("cert")
                    .addTransport(PAYMENT_TRANSPORT).setUsgConfig(CERT_GCP, PAYMENT_USG_ACTION).setMomConfig(PAYMENT_CERT_MOMCONFIG_XML, PAYMENT_USG_SERVICE).endTransport()
                    .addTransport(REMOTE_PAYMENT_TRANSPORT).setUsgConfig(CERT_GCP, REMOTE_PAYMENT_USG_ACTION).setMomConfig(PAYMENT_CERT_MOMCONFIG_XML, REMOTE_PAYMENT_USG_SERVICE).endTransport()
                    .addTransport(FRAUD_CHECK_TRANSPORT).setUsgConfig(CERT_GCP, FRAUD_CHECK_USG_ACTION).setMomConfig(PAYMENT_CERT_MOMCONFIG_XML, FRAUD_CHECK_USG_SERVICE).endTransport()
                    .addTransport(PSS_PAYMENT_TRANSPORT).setMomConfig(PAYMENT_CERT_MOMCONFIG_XML, PSS_PAYMENT_USG_SERVICE).endTransport()
                    .addTransport(PAYMENT_QUERY_TRANSPORT).setUsgConfig(CERT_GCP, PAYMENT_QUERY_USG_ACTION).setMomConfig(PAYMENT_CERT_MOMCONFIG_XML, PAYMENT_QUERY_USG_SERVICE).endTransport()
                    .addTransport("ssg").setUsgConfig(CERT_GCP, PAYMENT_USG_ACTION).setMomConfig(PAYMENT_CERT_MOMCONFIG_XML, "PaymentSvc-REALSSGPYMTDEV-Service").endTransport()
                    .setPssDataSource("access.cert.sabre.com", "724919", "PAY2006", "HDQTSG", "79440", "AAO", "LSC")
                    .endEnvironment()
                    // Services needed for live tests
                    .addService("Payment", "com.sabre.payment.pws.pymt.PaymentServiceProvider", "paymentProvider", "classpath:beans/cucumber-mock-payment-beans.xml", "cucumber/beans/cucumber-live-payment-beans.xml")
                    .addService("RemotePayment", "com.sabre.payment.pws.pymt.PaymentServiceProvider", "remotePaymentProvider", "classpath:beans/cucumber-mock-remotepayment-beans.xml", "cucumber/beans/cucumber-live-remotepayment-beans.xml")
                    .addService("PssPayment", "com.sabre.payment.pws.pymt.PaymentServiceProvider", "pssPaymentProvider", "classpath:beans/cucumber-mock-pssPayment-beans.xml", "cucumber/beans/cucumber-live-pssPayment-beans.xml")
                    .addService("FraudCheck", "com.sabre.payment.pws.pymt.PaymentServiceProvider", "fraudCheckProvider", "classpath:beans/cucumber-mock-fraudCheck-beans.xml", "cucumber/beans/cucumber-live-fraudCheck-beans.xml")
                    .addService("PaymentQuery", "com.sabre.payment.pws.pymt.PaymentServiceProvider", "queryProvider", "classpath:beans/cucumber-mock-paymentQuery-beans.xml", "cucumber/beans/cucumber-live-paymentQuery-beans.xml")
                    .addService("Ssg", "com.sabre.payment.pws.pymt.PaymentServiceProvider", "paymentProvider", "cucumber/beans/cucumber-mock-payment-beans.xml", "cucumber/beans/cucumber-live-payment-beans.xml")
                    .addClassGenerator("paymentQueryRequestStructClassGenerator", "Payment_Query_Request_Structv1.xml")
                    .addClassGenerator("paymentDocumentStructClassGenerator", "PaymentDocumentStructv2.xml")
                    .addClassGenerator("paymentRequestStructClassGenerator", "Payment_Request_Structv2.xml")
                    .addClassGenerator("authorizationLoggingClassGenerator", "Authorization_Logging_Structv2.xml")
                    .addClassGenerator("nonAirAuthorizationLoggingClassGenerator", "NonAirAuthorization_Logging_Structv2.xml")
                    .addClassGenerator("refundLoggingClassGenerator", "Refund_Logging_Structv2.xml")
                    .defaultEnvironment("int")
                    .defaultTransport("usg")
            ;
            Configuration conf = builder.build();
            ConfigurationFixture.setConfiguration(conf);
        }
    }

    @After
    public void tearDown() throws SQLException {
//        !|script|metrics analyzer fixture|
//        |show|get error events|
//        |show|get comm events|
//        |show|get debug events|
//        |reset|
//        |reset debug events|

        // Ensure that there are no unused mock responses
        if (configuration.getConfiguration().getCurrentMode() == Mode.Mock) {
            DatabaseStep dbStep = new DatabaseStep(testContext);
            testContext.getBaseServices().getAssertionService().assertThat("Error deleting all rows from table ORCHESTRATIONRULE", dbStep.deleteAllFrom("ORCHESTRATIONRULE"), Matchers.not(-1));
            testContext.getBaseServices().getAssertionService().assertThat("Error deleting all rows from table ORCHESTRATIONSTATE", dbStep.deleteAllFrom("ORCHESTRATIONSTATE"), Matchers.not(-1));
            testContext.getBaseServices().getAssertionService().assertThat("Error deleting all rows from table FAILEDTRANSACTIONDOCUMENT", dbStep.deleteAllFrom("FAILEDTRANSACTIONDOCUMENT"), Matchers.not(-1));
            testContext.getBaseServices().getAssertionService().assertThat("Error deleting all rows from table FAILEDPAYMENTTRANSACTION", dbStep.deleteAllFrom("FAILEDPAYMENTTRANSACTION"), Matchers.not(-1));
            testContext.getBaseServices().getAssertionService().assertThat("Error deleting all rows from table VIRTUALPAYMENTCREDENTIALS", dbStep.deleteAllFrom("VIRTUALPAYMENTCREDENTIALS"), Matchers.not(-1));
            testContext.getBaseServices().getAssertionService().assertThat("Error deleting all rows from table AUTHREVERSALCANDIDATE", dbStep.deleteAllFrom("AUTHREVERSALCANDIDATE"), Matchers.not(-1));
            testContext.getBaseServices().getAssertionService().assertThat("Error deleting all rows from table DOCUMENTREFUNDMAPPING", dbStep.deleteAllFrom("DOCUMENTREFUNDMAPPING"), Matchers.not(-1));
            testContext.getBaseServices().getAssertionService().assertThat("Error deleting all rows from table DOCUMENTPAYMENTMAPPING", dbStep.deleteAllFrom("DOCUMENTPAYMENTMAPPING"), Matchers.not(-1));
            testContext.getBaseServices().getAssertionService().assertThat("Error deleting all rows from table DOCUMENTDETAIL", dbStep.deleteAllFrom("DOCUMENTDETAIL"), Matchers.not(-1));
            testContext.getBaseServices().getAssertionService().assertThat("Error deleting all rows from table PAYMENTMODIFICATIONDETAIL", dbStep.deleteAllFrom("PAYMENTMODIFICATIONDETAIL"), Matchers.not(-1));
            testContext.getBaseServices().getAssertionService().assertThat("Error deleting all rows from table REFUNDCANCELLATIONDETAIL", dbStep.deleteAllFrom("REFUNDCANCELLATIONDETAIL"), Matchers.not(-1));
            testContext.getBaseServices().getAssertionService().assertThat("Error deleting all rows from table REFUNDDETAIL", dbStep.deleteAllFrom("REFUNDDETAIL"), Matchers.not(-1));
            testContext.getBaseServices().getAssertionService().assertThat("Error deleting all rows from table FRAUDCHECKDETAIL", dbStep.deleteAllFrom("FRAUDCHECKDETAIL"), Matchers.not(-1));
            testContext.getBaseServices().getAssertionService().assertThat("Error deleting all rows from table PAYMENTRECEIPTDETAIL", dbStep.deleteAllFrom("PAYMENTRECEIPTDETAIL"), Matchers.not(-1));
            testContext.getBaseServices().getAssertionService().assertThat("Error deleting all rows from table CAPTURESOURCERECORD", dbStep.deleteAllFrom("CAPTURESOURCERECORD"), Matchers.not(-1));
            testContext.getBaseServices().getAssertionService().assertThat("Error deleting all rows from table PAYMENTDETAIL", dbStep.deleteAllFrom("PAYMENTDETAIL"), Matchers.not(-1));
            testContext.getBaseServices().getAssertionService().assertThat("Error deleting all rows from table PAYMENTTRANSACTION", dbStep.deleteAllFrom("PAYMENTTRANSACTION"), Matchers.not(-1));
            testContext.getBaseServices().getAssertionService().assertThat("Error deleting all rows from table PAYMENTACCOUNTMAPPING", dbStep.deleteAllFrom("PAYMENTACCOUNTMAPPING"), Matchers.not(-1));
            testContext.getBaseServices().getAssertionService().assertThat("Error deleting all rows from table DCCLOOKUP", dbStep.deleteAllFrom("DCCLOOKUP"), Matchers.not(-1));
            testContext.getBaseServices().getAssertionService().assertThat("Error deleting all rows from table VIRTUALPAYMENTDETAIL", dbStep.deleteAllFrom("VIRTUALPAYMENTDETAIL"), Matchers.not(-1));
            testContext.getBaseServices().getAssertionService().assertThat("Error deleting all rows from table PAYMENTACCOUNTDETAIL", dbStep.deleteAllFrom("PAYMENTACCOUNTDETAIL"), Matchers.not(-1));
            testContext.getBaseServices().getAssertionService().assertThat("Error deleting all rows from table PAYMENTDETAIL", dbStep.deleteAllFrom("PAYMENTDETAIL"), Matchers.not(-1));
            testContext.getBaseServices().getAssertionService().assertThat("Error deleting all rows from table PAYMENTTRANSACTION", dbStep.deleteAllFrom("PAYMENTTRANSACTION"), Matchers.not(-1));
            testContext.getBaseServices().getAssertionService().assertThat("Error deleting all rows from table PAYMENTACCOUNT", dbStep.deleteAllFrom("PAYMENTACCOUNT"), Matchers.not(-1));
            testContext.getBaseServices().getAssertionService().assertThat("Error deleting all rows from table MERCHANTPAYMENTPROFILE", dbStep.deleteAllFrom("MERCHANTPAYMENTPROFILE"), Matchers.not(-1));
            testContext.getBaseServices().getAssertionService().assertThat("Error deleting all rows from table PAYMENTPROFILECOMBINATION", dbStep.deleteAllFrom("PAYMENTPROFILECOMBINATION"), Matchers.not(-1));
            testContext.getBaseServices().getAssertionService().assertThat("Error deleting all rows from table TNPAYMENTPROFILE", dbStep.deleteAllFrom("TNPAYMENTPROFILE"), Matchers.not(-1));
            testContext.getBaseServices().getAssertionService().assertThat("Error deleting all rows from table TNPAYMENTPROFILECOMBINATION", dbStep.deleteAllFrom("TNPAYMENTPROFILECOMBINATION"), Matchers.not(-1));
            testContext.getBaseServices().getAssertionService().assertThat("Error deleting all rows from table AUDITLOGDETAIL", dbStep.deleteAllFrom("AUDITLOGDETAIL"), Matchers.not(-1));
            testContext.getBaseServices().getAssertionService().assertThat("Error deleting all rows from table AUDITLOG", dbStep.deleteAllFrom("AUDITLOG"), Matchers.not(-1));
            testContext.getBaseServices().getAssertionService().assertThat("Error deleting all rows from table DEFERREDPAYMENTACCOUNTDETAIL", dbStep.deleteAllFrom("DEFERREDPAYMENTACCOUNTDETAIL"), Matchers.not(-1));
            testContext.getBaseServices().getAssertionService().assertThat("Error deleting all rows from table DEFERREDPAYMENTACCOUNT", dbStep.deleteAllFrom("DEFERREDPAYMENTACCOUNT"), Matchers.not(-1));
            testContext.getBaseServices().getAssertionService().assertThat("Error deleting all rows from table DEFERREDPAYMENTDETAIL", dbStep.deleteAllFrom("DEFERREDPAYMENTDETAIL"), Matchers.not(-1));
            testContext.getBaseServices().getAssertionService().assertThat("Error deleting all rows from table DEFERREDPAYMENTTRANSACTION", dbStep.deleteAllFrom("DEFERREDPAYMENTTRANSACTION"), Matchers.not(-1));
            testContext.getBaseServices().getAssertionService().assertThat("Error deleting all rows from table AUTHREQUESTPAYLOAD", dbStep.deleteAllFrom("AUTHREQUESTPAYLOAD"), Matchers.not(-1));

            resetFromPreviousTests(null);

            ConfigurationStep confStep = new ConfigurationStep(testContext);
            confStep.clearDatabaseCache();
            try {
                new DateTimeStep(testContext).reset();
            } catch (Exception ex) {
                // TODO Try catch block not needed
                ex.printStackTrace();
            }
        }
    }

    public void environmentAndTransport(String env, String trans) {
        pwsTestContext.setEnvironment(env);
        pwsTestContext.setTransport(trans);
        configuration.setEnvironment(env);
        configuration.setTransportType(trans);
        mode("Live");
    }

    @Given("^Environment is dev via mom$")
    public void environmentDevMom() {
        environmentAndTransport("dev", "Mom");
    }

    @Given("^Environment is int via usg$")
    public void environmentIntUsg() {
        environmentAndTransport("int", "Usg");
    }

    @Given("^Environment is cert via usg$")
    public void environmentCertUsg() {
        environmentAndTransport("cert", "Usg");
    }

    public void service(String serv) {
        pwsTestContext.setService(serv);
        configuration.setService(serv);
    }

    @Given("^Service is Payment$")
    public void servicePayment() {
        service(PAYMENT_TRANSPORT);
    }

    @Given("^Service is RemotePayment$")
    public void serviceRemotePayment() {
        service("RemotePayment");
    }

    @Given("^Service is FraudCheck$")
    public void serviceFraudCheck() {
        service("FraudCheck");
    }

    @Given("^Service is PaymentQuery$")
    public void servicePaymentQuery() {
        service("PaymentQuery");
    }

    @Given("^Service is PssPayment$")
    public void servicePssPayment() {
        service("PssPayment");
    }

    public void mode(String mode) {
        pwsTestContext.setMode(mode);
        configuration.setMode(mode);
    }

//    @Given("^Mock test$")
//    public void mockTest() {
//        mode("Mock");
//    }

    @SuppressWarnings("static-access")
    @Given("^Initialize$")
    public void initialize() throws IOException, SQLException {
        logger.info("Initializing");
        System.setProperty("eiapi.configuration", "eiapi-log4j.xml");
        if (configuration.getConfiguration().getCurrentMode() == Mode.Mock) {
            configuration.getConfiguration().setCurrentTransportType(TransportType.internalserver);
            configuration.getConfiguration().setCurrentEnvironment(configuration.getConfiguration().getConfigurations().get("dev"));
            System.setProperty("SHARED_DIR", "./src/test/resources");
            System.setProperty("SYSTEM_CONFIG_DIR", "../pwscommon/src/testFixtures/resources/database/conf");
            System.setProperty("APP_BASE_DIR", ".");
            System.setProperty("useHikariDataSource", "false");
            System.setProperty("usePwsHsqlDatabaseConnection", "false");

            // TODO Change this to enable switchover in cucumber
//            System.setProperty("usePwsUserDatabaseConnection", "false");
//            System.setProperty("useMetricsUserDatabaseConnection", "false");
            System.setProperty("usePwsUserDatabaseConnection", "true");
            System.setProperty("useMetricsUserDatabaseConnection", "true");

            System.setProperty("metricsSessionFactoryName", "metricsUserSessionFactory");
            System.setProperty("metricsHibernateTransactionManagerName", "metricsUserHibernateTransactionManager");
            System.setProperty("metricsDataSourceName", "metricsUserDataSource");
            System.setProperty("pwsSessionFactoryName", "pwsUserSessionFactory");
            System.setProperty("pwsHibernateTransactionManagerName", "pwsUserHibernateTransactionManager");
            System.setProperty("pwsDataSourceName", "pwsUserDataSource");
            System.setProperty("pwsUserHibernateConfigFilename", "pws.cucumber.hibernate.properties");
            System.setProperty("metricsUserHibernateConfigFilename", "metrics.cucumber.hibernate.properties");
            System.setProperty("useDatabaseResiliencyCache", "false");

            // TODO Change this to enable switchover in cucumber
            // TODO Also probably need to start as a spring boot application
            //System.setProperty("spring.profiles.active", "dev," + configuration.getConfiguration().getCurrentService().getName().toLowerCase() + ",cucumber,switchover");
            System.setProperty("spring.profiles.active", "dev," + configuration.getConfiguration().getCurrentService().getName().toLowerCase() + ",cucumber");

            //enableJacocoFitnesse
            System.setProperty("log4j.ignoreTCL", "true");
            System.setProperty("JMX_PORT", "12345");
            System.setProperty("host.env", "ctovm1631");
            System.setProperty("instance.env", "d1631pws1");
            System.setProperty("EiapiTransactionsEnabled", "false");
            System.setProperty("EiapiMetricsEnabled", "false");
//            System.setProperty("log4j.configuration", "${LOG4J_CONFIGURATION_FILE}");
            System.setProperty("processLogDir", "./logs");
            System.setProperty("processLogName", "cucumber");
            // Initialize data sources
//            configuration.getConfiguration().addDataSourceName("metricsUserDataSource");
//            configuration.getConfiguration().addDataSourceName("pwsUserDataSource");
            configuration.getConfiguration().setDefaultDataSourceName("pwsUserDataSource");
            ConfigurationStep configurationStep = new ConfigurationStep(testContext);
//            configuration.getConfiguration().setDefaultDataSourceName("pwsUserDataSource"); // Not necessary once we've confirmed that changes work.
            if (!configuration.getConfiguration().isServerStarted()) {
//            if (configuration.getConfiguration().getCurrentTransport().getInternalServerConfiguration().getServer(configuration.getConfiguration().getCurrentService().getName()) == null) {
                logger.info("Starting service");
                if (!pwsTestContext.isServiceStarted()) {
                    pwsTestContext.setServiceStarted(true);
                    configurationStep.startService();
                }
                if (!pwsTestContext.isDatabaseLoaded()) {
                    logger.info("Loading database");
                    pwsTestContext.setDatabaseLoaded(true);
                    preloadDatabase(configuration.getConfiguration().getCurrentService().getName().toLowerCase());
                }
            }
            ConfigurableApplicationContext ctx = configuration.getConfiguration().getCtx();
            if (ctx.containsBean("authorizationLoggingClassGenerator")) {
                testContext.addClassGenerator("AuthorizationLoggingStruct", (ClassGenerator<?>) ctx.getBean("authorizationLoggingClassGenerator"));
            }
            if (ctx.containsBean("refundLoggingClassGenerator")) {
                testContext.addClassGenerator("RefundLoggingStruct", (ClassGenerator<?>) ctx.getBean("refundLoggingClassGenerator"));
            }
            if (ctx.containsBean("nonAirAuthorizationLoggingClassGenerator")) {
                testContext.addClassGenerator("NonAirAuthorizationLoggingStruct", (ClassGenerator<?>) ctx.getBean("nonAirAuthorizationLoggingClassGenerator"));
            }
            if (ctx.containsBean("paymentRequestStructClassGenerator")) {
                testContext.addClassGenerator("PaymentRequestStruct", (ClassGenerator<?>) ctx.getBean("paymentRequestStructClassGenerator"));
            }
            if (ctx.containsBean("paymentQueryRequestStructClassGenerator")) {
                testContext.addClassGenerator("PaymentQueryRequestStruct", (ClassGenerator<?>) ctx.getBean("paymentQueryRequestStructClassGenerator"));
            }
            if (ctx.containsBean("paymentDocumentStructClassGenerator")) {
                testContext.addClassGenerator("PaymentDocumentStruct", (ClassGenerator<?>) ctx.getBean("paymentDocumentStructClassGenerator"));
            }
            if (testContext.getManagedTables().isEmpty()) {
                setManagedTables();
            }
            resetFromPreviousTests(configuration.getConfiguration().getCurrentService().getName());
        } else {
            // Live
            if (configuration.getConfiguration().getCurrentService().getName().equalsIgnoreCase("ssg")) {
                configuration.getConfiguration().setCurrentEnvironment(configuration.getConfiguration().getConfigurations().get("dev"));
            }
            if (configuration.getConfiguration().getCurrentTransportType() == null) {
                configuration.getConfiguration().setCurrentTransportType(TransportType.usg);
            }
        }
        configuration.getConfiguration().setCurrentTransport(configuration.getConfiguration().getCurrentEnvironment().getTransports().get(configuration.getConfiguration().getCurrentService().getName()));
    }

    private void setManagedTables() {
        testContext.addManagedTable(new HashSet<>(Arrays.asList(
                "BATCHREVERSALMERCHANT",
                "BATCHREVERSALRULE",
                "BATCHREVERSALRULEMAPPING",
                "BLOCKEDCARD",
                "CARDBASEDCURRENCYEXCEPTION",
                "CARDBINRANGE",
                "CARDPLAUSIBILITYRULE",
                "COUNTRY",
                "CURRENCY",
                "DSTADJMINUTES",
                "DSTGROUP",
                "FOPAVLFACTOR",
                "FOPDEFINITION",
                "INSTALLMENTALLOWCARDBINRANGE",
                "INSTALLMENTOPTION",
                "MARKET",
                "MERCHANTCARDISSUERDETAIL",
                "MERCHANTCURRENCYMAPPING",
                "MERCHANTFOP",
                "MERCHANTFOPATTRIBUTE",
                "MERCHANTFOPAVLFACTOR",
                "MERCHANTFOPAVLRULE",
                "MERCHANTFOPCOMBINABILITYRULE",
                "MERCHANTFOPFOPAVLMAPPING",
                "MERCHANTINSTALLMENTRULE",
                "MERCHANTSTATIONMAPPING",
                "MERCHANT",
                "MERCHANTTBLUPDTIMESTAMP",
                "EC_PASSENGERAIRLINE",
                "PSEUDOCARDOVERRIDE",
                "PSSQUEUECONFIG",
                "SUPPLIERENDPOINTMAPPING",
                "SUPPLIERFOPDEFINITIONMAPPING",
                "SUPPLIERFOPDEFINITION",
                "SUPPLIERRESPONSE",
                "SUPPLIERRESPONSEDESCOVERRIDE"
        )));

    }

//    @Given("^Live test$")
//    public void liveTest() {
//        mode("Live");
//    }

    //    @Given("^Reset from previous \"([^\"]+)\" tests$")
    public void resetFromPreviousTests(String service) {
        RequestSendingStep reqStep = new RequestSendingStep(testContext);
        reqStep.resetResponsesForWithNoneUnused("ssgConnection");
        reqStep.resetResponsesForWithNoneUnused("pssConnection");
        reqStep.resetResponsesForWithNoneUnused("usgConnection");
        reqStep.resetResponsesForWithNoneUnused("paymentMomConnection");
        reqStep.resetResponsesForWithNoneUnused("fraudMomConnection");
        reqStep.resetResponsesForWithNoneUnused("tknConnection");
        reqStep.resetResponsesForWithNoneUnused("restConnection");
        reqStep.resetResponsesForWithNoneUnused("paymentTktMomConnection");
        reqStep.resetResponsesForWithNoneUnused("paymentTknMomConnection");
        reqStep.resetResponsesForWithNoneUnused("kafkaConnection");
        reqStep.resetResponsesForWithNoneUnused("essmConnection");
    }

    //    @Given("^Preload database for \"([^\"]+)\" service$")
    public void preloadDatabase(String service) throws IOException, SQLException {
        DatabaseStep dbStep = new DatabaseStep(testContext);
        dbStep.execute("ALTER SEQUENCE HIBERNATE_SEQUENCE RESTART WITH -99999999");
        switch (service) {
            case "payment":
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/SupplierEndPointMapping/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/CurrencyTable/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/CountryTable/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/CardPlausibilityRule/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/CardBinRange/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/SupplierResponse/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/SupplierResponseDescOverride/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/MerchantTable/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/CardBasedCurrencyException/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/BlockedCard/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/DstGroup/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/DstAdjMinutes/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/MarketTable/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/PassengerAirlineTable/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/FopDefinitionTable/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/PseudoCardOverrideTable/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/SupplierFopDefinitionTable/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/SupplierFopDefinitionMappingTable/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/PssQueueConfigTable/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/BatchReversalMerchant/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/BatchReversalRule/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/BatchReversalRuleMapping/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/FopAvailabilityFactorTable/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/MerchantFop/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/MerchantFopAttribute/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/MerchantFopAvailabilityFactor/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/MerchantFopAvailabilityRule/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/MerchantFopAvailabilityMapping/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/MerchantFopCombinabilityRule/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/IpeTrafficSegmentationRule/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/UrlConfig/content.txt");
                break;
            case "remotepayment":
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/SupplierEndPointMapping/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/FopDefinitionTable/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/PssQueueConfigTable/content.txt");

                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/CardPlausibilityRule/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/CardBinRange/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/DstGroup/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/DstAdjMinutes/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/MarketTable/content.txt");
                break;
            case "fraudcheck":
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/SupplierEndPointMapping/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/CardPlausibilityRule/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/CardBinRange/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/DstGroup/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/DstAdjMinutes/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/MarketTable/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/PassengerAirlineTable/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/SupplierResponse/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/SupplierResponseDescOverride/content.txt");
                break;
            case "psspayment":
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/SupplierEndPointMapping/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/BatchReversalMerchant/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/BatchReversalRule/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/BatchReversalRuleMapping/content.txt");
                break;
            case "paymentquery":
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/DstGroup/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/MarketTable/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/FopAvailabilityFactorTable/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/FopDefinitionTable/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/SupplierFopDefinitionTable/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/SupplierFopDefinitionMappingTable/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/MerchantTable/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/MerchantFop/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/MerchantFopAttribute/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/MerchantFopAvailabilityFactor/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/MerchantFopAvailabilityRule/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/MerchantFopAvailabilityMapping/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/MerchantFopCombinabilityRule/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/MerchantStationMapping/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/CurrencyTable/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/CountryTable/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/MerchantCurrencyMapping/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/MerchantTblUpdTimeStamp/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/InstallmentAllowCardBinRange/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/MerchantInstallmentRule/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/InstallmentOption/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/MerchantCardIssuerDetail/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/FopFeeRule/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/CardPlausibilityRule/content.txt");
                dbStep.loadCache("../pwscommon/src/test/resources/databaseCache/CardBinRange/content.txt");

                break;
        }
    }
}
