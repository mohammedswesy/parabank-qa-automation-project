package tests;

import base.BaseTest;
import config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactPage;
import pages.NavigationPage;

public class ContactAndNavigationTests extends BaseTest {

    @Test(description = "PB-TC-032 Verify contact form submission with valid data")
    public void contactValid() {

        ContactPage contactPage = homePage.goToContact();

        contactPage.sendMessage(
                "Mohammed AlSwesy",
                "mohammed.qa@example.com",
                "0598890011",
                "Testing customer care form."
        );

        Assert.assertTrue(
                contactPage.isSubmitted(),
                "Contact form should be submitted successfully."
        );
    }

    @Test(description = "PB-TC-033 Verify contact form with invalid email")
    public void contactInvalidEmail() {

        ContactPage contactPage = homePage.goToContact();

        contactPage.sendMessage(
                "Mohammed AlSwesy",
                "test@@",
                "0598890011",
                "Invalid email validation."
        );

        Assert.assertTrue(
                contactPage.getResultText().length() > 0,
                "System should handle invalid email properly."
        );
    }

    @Test(description = "PB-TC-034 Verify main menu links are clickable")
    public void menuLinks() {

        NavigationPage navigationPage =
                new NavigationPage(driver);

        navigationPage.clickAboutUs();

        Assert.assertTrue(
                navigationPage.pageIsNotBroken(),
                "About Us page should not be broken."
        );

        driver.get(ConfigReader.get("baseUrl"));

        navigationPage.clickServices();

        Assert.assertTrue(
                navigationPage.pageIsNotBroken(),
                "Services page should not be broken."
        );

        driver.get(ConfigReader.get("baseUrl"));

        navigationPage.clickProducts();

        Assert.assertTrue(
                navigationPage.pageIsNotBroken(),
                "Products page should not be broken."
        );

        driver.get(ConfigReader.get("baseUrl"));

        navigationPage.clickLocations();

        Assert.assertTrue(
                navigationPage.pageIsNotBroken(),
                "Locations page should not be broken."
        );
    }

    @Test(description = "PB-TC-035 Verify browser refresh keeps session state")
    public void refreshSession() {

        registerNewUserAndReturnUsername();

        driver.navigate().refresh();

        Assert.assertTrue(
                homePage.isLoggedIn(),
                "User session should remain active after page refresh."
        );
    }
}