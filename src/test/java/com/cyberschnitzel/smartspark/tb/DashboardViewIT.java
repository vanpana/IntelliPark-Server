package com.cyberschnitzel.smartspark.tb;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cyberschnitzel.smartspark.tb.pageobjects.TBDashboardEdit;
import com.cyberschnitzel.smartspark.tb.pageobjects.TBDashboardView;
import com.cyberschnitzel.smartspark.tb.pageobjects.TBLoginView;
import com.cyberschnitzel.smartspark.tb.pageobjects.TBMainView;
import com.vaadin.testbench.TestBenchTestCase;

public class DashboardViewIT extends TestBenchTestCase {

    private TBLoginView loginView;
    private TBMainView mainView;

    @Before
    public void setUp() {
        loginView = TBUtils.openInitialView();
        mainView = loginView.login();
    }

    @Test
    public void testEditDashboardTitle() {
        TBDashboardView dashboardView = mainView.openDashboardView();
        String newTitle = "New Dashboard";
        TBDashboardEdit edit = dashboardView.openDashboardEdit();
        edit.setDashboardTitle(newTitle);
        edit.save();
        Assert.assertEquals(newTitle, dashboardView.getDashboardTitle());
    }

    @Test
    public void testReadNotifications() {
        TBDashboardView dashboardView = mainView.openDashboardView();
        Assert.assertEquals(mainView.getUnreadNotificationsCount(),
                dashboardView.getUnreadNotificationsCount());
        dashboardView.openNotifications();
        Assert.assertEquals(mainView.getUnreadNotificationsCount(),
                dashboardView.getUnreadNotificationsCount());
    }

    @After
    public void tearDown() {
        loginView.getDriver().quit();
    }
}
