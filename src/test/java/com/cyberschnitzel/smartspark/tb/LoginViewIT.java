package com.cyberschnitzel.smartspark.tb;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cyberschnitzel.smartspark.tb.pageobjects.TBLoginView;
import com.cyberschnitzel.smartspark.tb.pageobjects.TBMainView;
import com.vaadin.testbench.TestBenchTestCase;

public class LoginViewIT extends TestBenchTestCase {

    private TBLoginView loginView;

    @Before
    public void setUp() {
        loginView = TBUtils.openInitialView();
    }

    @Test
    public void testLoginLogout() {
        Assert.assertTrue(loginView.isDisplayed());

        TBMainView mainView = loginView.login();
        Assert.assertTrue(mainView.isDisplayed());

        mainView.logout();
        Assert.assertTrue(loginView.isDisplayed());
    }

    @After
    public void tearDown() {
        loginView.getDriver().quit();
    }
}
