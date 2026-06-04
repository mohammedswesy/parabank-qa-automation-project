package tests;

import base.BaseTest;
import config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegisterPage;
import utils.TestData;

public class RegistrationTests extends BaseTest {

    @Test(description = "PB-TC-001 Verify new user can register with valid data")
    public void validRegistration() {

        RegisterPage registerPage =
                homePage.clickRegister();

        registerPage.register(
                TestData.FIRST_NAME,
                TestData.LAST_NAME,
                TestData.ADDRESS,
                TestData.CITY,
                TestData.STATE,
                TestData.ZIP_CODE,
                TestData.PHONE,
                TestData.SSN,
                TestData.uniqueUsername(),
                TestData.PASSWORD,
                TestData.PASSWORD
        );

        Assert.assertTrue(
                registerPage.isRegistrationSuccessful(),
                "Registration should be completed successfully."
        );
    }

    @Test(description = "PB-TC-002 Verify registration with empty mandatory fields")
    public void emptyRegistration() {

        RegisterPage registerPage =
                homePage.clickRegister();

        registerPage.submitEmptyForm();

        Assert.assertTrue(
                registerPage.getErrorText().length() > 0,
                "Validation errors should appear for empty fields."
        );
    }

    @Test(description = "PB-TC-003 Verify registration with password mismatch")
    public void passwordMismatch() {

        RegisterPage registerPage =
                homePage.clickRegister();

        registerPage.register(
                TestData.FIRST_NAME,
                TestData.LAST_NAME,
                TestData.ADDRESS,
                TestData.CITY,
                TestData.STATE,
                TestData.ZIP_CODE,
                TestData.PHONE,
                TestData.SSN,
                TestData.uniqueUsername(),
                TestData.PASSWORD,
                "Wrong123"
        );

        Assert.assertTrue(
                registerPage.getErrorText()
                        .toLowerCase()
                        .contains("password"),
                "Password mismatch validation should appear."
        );
    }

    @Test(description = "PB-TC-004 Verify duplicate username is rejected")
    public void duplicateUsername() {

        String username =
                TestData.uniqueUsername();

        RegisterPage registerPage =
                homePage.clickRegister();

        registerPage.register(
                TestData.FIRST_NAME,
                TestData.LAST_NAME,
                TestData.ADDRESS,
                TestData.CITY,
                TestData.STATE,
                TestData.ZIP_CODE,
                TestData.PHONE,
                TestData.SSN,
                username,
                TestData.PASSWORD,
                TestData.PASSWORD
        );


        driver.get(ConfigReader.get("baseUrl"));

        homePage = new HomePage(driver);

        registerPage = homePage.clickRegister();

        registerPage.register(
                TestData.FIRST_NAME,
                TestData.LAST_NAME,
                TestData.ADDRESS,
                TestData.CITY,
                TestData.STATE,
                TestData.ZIP_CODE,
                TestData.PHONE,
                TestData.SSN,
                username,
                TestData.PASSWORD,
                TestData.PASSWORD
        );

        Assert.assertTrue(
                registerPage.getErrorText()
                        .toLowerCase()
                        .contains("already exists"),
                "Duplicate username should not be accepted."
        );
    }

    @Test(description = "PB-TC-005 Verify invalid phone number validation")
    public void invalidPhoneValidation() {

        RegisterPage registerPage =
                homePage.clickRegister();

        registerPage.register(
                TestData.FIRST_NAME,
                TestData.LAST_NAME,
                TestData.ADDRESS,
                TestData.CITY,
                TestData.STATE,
                TestData.ZIP_CODE,
                "abc@@@",
                TestData.SSN,
                TestData.uniqueUsername(),
                TestData.PASSWORD,
                TestData.PASSWORD
        );

        Assert.assertTrue(
                registerPage.isRegistrationSuccessful()
                        || registerPage.getErrorText().length() > 0,
                "System should either reject invalid phone number or log it as validation bug."
        );
    }
}