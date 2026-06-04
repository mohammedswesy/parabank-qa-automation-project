package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginLogoutTests extends BaseTest {

    @Test(description = "PB-TC-006 Verify login with valid credentials")
    public void validLogin() {
        registerNewUserAndReturnUsername();

        Assert.assertTrue(
                homePage.isLoggedIn(),
                "User should be logged in successfully."
        );
    }

    @Test(description = "PB-TC-007 Verify login with invalid password")
    public void invalidPassword() {
        String username = registerNewUserAndReturnUsername();

        homePage.logout();

        homePage.loginExpectingFailure(
                username,
                "WrongPassword"
        );

        Assert.assertTrue(
                homePage.getLoginError().length() > 0,
                "Invalid password error should be displayed."
        );
    }

    @Test(description = "PB-TC-008 Verify login with empty username and password")
    public void emptyLogin() {
        homePage.loginExpectingFailure("", "");

        Assert.assertTrue(
                homePage.getLoginError().length() > 0,
                "Login error should be displayed for empty credentials."
        );
    }

    @Test(description = "PB-TC-009 Verify login with SQL injection-like input")
    public void sqlInjectionLogin() {
        homePage.loginExpectingFailure(
                "' OR '1'='1",
                "' OR '1'='1"
        );

        Assert.assertTrue(
                homePage.getLoginError().length() > 0,
                "SQL injection-like input should not authenticate the user."
        );
    }

    @Test(description = "PB-TC-010 Verify user can logout successfully")
    public void logout() {
        registerNewUserAndReturnUsername();

        homePage.logout();

        Assert.assertFalse(
                homePage.isLoggedIn(),
                "User should be logged out successfully."
        );
    }

    @Test(description = "PB-TC-011 Verify protected pages cannot be accessed after logout")
    public void backAfterLogout() {
        registerNewUserAndReturnUsername();

        homePage.goToAccountsOverview();

        homePage.logout();

        driver.navigate().back();

        Assert.assertFalse(
                homePage.isLoggedIn(),
                "User should not access protected pages after logout."
        );
    }
}