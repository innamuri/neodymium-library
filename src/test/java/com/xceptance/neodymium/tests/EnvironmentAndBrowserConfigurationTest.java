package com.xceptance.neodymium.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import com.xceptance.neodymium.module.statement.browser.multibrowser.configuration.BadProxyEnvironmentConfiguration;
import com.xceptance.neodymium.testclasses.multibrowser.BrowserWithoutAvailableEnvironment;
import com.xceptance.neodymium.testclasses.multibrowser.EnvironmentAndBrowserConfiguration;

public class EnvironmentAndBrowserConfigurationTest extends NeodymiumTest
{
    public static final String URL = "urltomylittleselenium.grid";

    public static final String USERNAME = "username";

    public static final String PASSWORD = "password";

    public static final String URL2 = "urltomysecondselenium.grid";

    public static final String USERNAME2 = "anotherUsername";

    public static final String PASSWORD2 = "anotherPassword";

    public static final String BROWSERNAME = "My new name for Samsung S3";

    public static final String ENVIRONMENTNAME = "someEnvrionment";

    public static final Boolean USEPROXY1 = true;

    public static final Boolean USEPROXY2 = false;

    public static final String PROXYHOST1 = "127.0.0.1";

    public static final Integer PROXYPORT1 = 4242;

    public static final String PROXYUSERNAME1 = "user1";

    public static final String PROXYPASSWORD1 = "password1";

    public static final String PROXYUSERNAME2 = "user2";

    public static final String PROXYPASSWORD2 = "password2";

    @BeforeClass
    public static void beforeClass() throws IOException
    {
        Map<String, String> properties1 = new HashMap<>();
        properties1.put("browserprofile.testEnvironment.unittest.url", URL);
        properties1.put("browserprofile.testEnvironment.unittest.username", USERNAME);
        properties1.put("browserprofile.testEnvironment.unittest.password", PASSWORD);
        properties1.put("browserprofile.testEnvironment.unittest.proxy", USEPROXY1.toString());
        properties1.put("browserprofile.testEnvironment.unittest.proxy.host", PROXYHOST1);
        properties1.put("browserprofile.testEnvironment.unittest.proxy.port", PROXYPORT1.toString());
        properties1.put("browserprofile.testEnvironment.unittest.proxy.username", PROXYUSERNAME1);
        properties1.put("browserprofile.testEnvironment.unittest.proxy.password", PROXYPASSWORD1);
        properties1.put("browserprofile.testEnvironment.override.url", URL);
        properties1.put("browserprofile.testEnvironment.override.username", USERNAME);
        properties1.put("browserprofile.testEnvironment.override.password", PASSWORD);
        properties1.put("browserprofile.testEnvironment.override.proxy", USEPROXY1.toString());
        properties1.put("browserprofile.testEnvironment.override.proxy.host", PROXYHOST1);
        properties1.put("browserprofile.testEnvironment.override.proxy.port", PROXYPORT1.toString());
        properties1.put("browserprofile.testEnvironment.override.proxy.username", PROXYUSERNAME1);
        properties1.put("browserprofile.testEnvironment.override.proxy.password", PROXYPASSWORD1);
        properties1.put("browserprofile.testEnvironment.noProxy.url", URL);
        properties1.put("browserprofile.testEnvironment.noProxy.username", USERNAME);
        properties1.put("browserprofile.testEnvironment.noProxy.password", PASSWORD);
        properties1.put("browserprofile.testEnvironment.noProxy.proxy", USEPROXY2.toString());
        properties1.put("browserprofile.testEnvironment.noProxy.proxy.username", PROXYUSERNAME1);
        properties1.put("browserprofile.testEnvironment.noProxy.proxy.password", PROXYPASSWORD1);
        File tempConfigFile1 = new File("./config/credentials.properties");
        writeMapToPropertiesFile(properties1, tempConfigFile1);
        tempFiles.add(tempConfigFile1);

        Map<String, String> properties2 = new HashMap<>();
        properties2.put("browserprofile.testEnvironment.override.url", URL2);
        properties2.put("browserprofile.testEnvironment.override.username", USERNAME2);
        properties2.put("browserprofile.testEnvironment.override.password", PASSWORD2);
        properties2.put("browserprofile.testEnvironment.override.proxy.username", PROXYUSERNAME2);
        properties2.put("browserprofile.testEnvironment.override.proxy.password", PROXYPASSWORD2);
        File tempConfigFile2 = new File("./config/dev-credentials.properties");
        writeMapToPropertiesFile(properties2, tempConfigFile2);
        tempFiles.add(tempConfigFile2);

        Map<String, String> properties3 = new HashMap<>();
        properties3.put("browserprofile.Galaxy_Note3_Emulation.name", BROWSERNAME);
        properties3.put("browserprofile.Galaxy_Note3_Emulation.testEnvironment", ENVIRONMENTNAME);
        File tempConfigFile3 = new File("./config/dev-browser.properties");
        writeMapToPropertiesFile(properties3, tempConfigFile3);
        tempFiles.add(tempConfigFile3);
    }

    @Test
    public void testOverridingEnvironmentsAndBrowsers()
    {
        // test environment configuration
        Result result = JUnitCore.runClasses(EnvironmentAndBrowserConfiguration.class);
        checkPass(result, 5, 0, 0);
    }

    @Test
    public void testConfigureBadEnvironmentProxies()
    {
        // test environment configuration
        Result result = JUnitCore.runClasses(BadProxyEnvironmentConfiguration.class);
        checkPass(result, 3, 0, 0);
    }

    @Test
    public void testRunningABrowserWithoutAEnvironmentConfiguration()
    {
        // test environment configuration
        Result result = JUnitCore.runClasses(BrowserWithoutAvailableEnvironment.class);
        checkFail(result, 1, 0, 1, "No properties found for test environment: \"" + ENVIRONMENTNAME + "\"");
    }
}
