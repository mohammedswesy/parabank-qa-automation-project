package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegisterPage;
import utils.TestData;

public class SecurityValidationTests extends BaseTest {

    @Test(description = "PB-TC-036 Verify unauthorized access to transfer page")
    public void unauthorizedTransfer() {
        driver.get("https://parabank.parasoft.com/parabank/transfer.htm");

        String bodyText = driver.findElement(By.tagName("body"))
                .getText()
                .toLowerCase();

        Assert.assertTrue(
                bodyText.contains("error")
                        || bodyText.contains("log in")
                        || !bodyText.contains("transfer funds"),
                "Unauthenticated user should not access transfer page."
        );
    }

    @Test(description = "PB-TC-037 Verify password field masks input")
    public void passwordMasked() {
        String passwordType = driver.findElement(By.name("password"))
                .getAttribute("type");

        Assert.assertEquals(
                passwordType,
                "password",
                "Password field should mask the input."
        );
    }

    @Test(description = "PB-TC-038 Verify leading/trailing spaces in login credentials")
    public void credentialSpaces() {
        String username = registerNewUserAndReturnUsername();

        homePage.logout();

        homePage.loginExpectingFailure(
                " " + username + " ",
                TestData.PASSWORD
        );

        Assert.assertTrue(
                homePage.getLoginError().length() > 0
                        || homePage.isLoggedIn(),
                "System should either trim spaces or show a clear login error."
        );
    }

    @Test(description = "PB-TC-039 Verify very long input in registration fields")
    public void longInput() {
        RegisterPage registerPage = homePage.clickRegister();

        String longText = "A".repeat(260);

        registerPage.register(
                longText,
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
                registerPage.isRegistrationSuccessful()
                        || registerPage.getErrorText().length() > 0,
                "System should handle long input without crashing."
        );
    }
}