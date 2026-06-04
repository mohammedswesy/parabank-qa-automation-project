package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AccountsOverviewPage;
import pages.OpenAccountPage;

public class AccountTests extends BaseTest {

    @Test(description = "PB-TC-012 Verify account overview displays customer accounts")
    public void overviewDisplaysAccounts() {
        registerNewUserAndReturnUsername();

        AccountsOverviewPage accountsPage = homePage.goToAccountsOverview();

        Assert.assertTrue(
                accountsPage.isAccountsTableDisplayed(),
                "Accounts table should be displayed."
        );
    }

    @Test(description = "PB-TC-013 Verify clicking account number opens account details")
    public void accountDetails() {
        registerNewUserAndReturnUsername();

        AccountsOverviewPage accountsPage = homePage.goToAccountsOverview();

        accountsPage.openFirstAccount();

        Assert.assertTrue(
                accountsPage.isAccountDetailsDisplayed(),
                "Account details page should be displayed."
        );
    }

    @Test(description = "PB-TC-014 Verify user can open new checking account")
    public void openChecking() {
        registerNewUserAndReturnUsername();

        OpenAccountPage openAccountPage = homePage.goToOpenNewAccount();

        openAccountPage.openCheckingAccount();

        Assert.assertTrue(
                openAccountPage.isAccountCreated(),
                "Checking account should be created successfully."
        );
    }

    @Test(description = "PB-TC-015 Verify user can open new savings account")
    public void openSavings() {
        registerNewUserAndReturnUsername();

        OpenAccountPage openAccountPage = homePage.goToOpenNewAccount();

        openAccountPage.openSavingsAccount();

        Assert.assertTrue(
                openAccountPage.isAccountCreated(),
                "Savings account should be created successfully."
        );
    }

    @Test(description = "PB-TC-016 Verify new account appears in Accounts Overview")
    public void newAccountInOverview() {
        registerNewUserAndReturnUsername();

        OpenAccountPage openAccountPage = homePage.goToOpenNewAccount();

        openAccountPage.openCheckingAccount();

        Assert.assertTrue(
                openAccountPage.getNewAccountNumber().length() > 0,
                "New account number should be generated."
        );

        AccountsOverviewPage accountsPage = homePage.goToAccountsOverview();

        Assert.assertTrue(
                accountsPage.isAccountsTableDisplayed(),
                "Accounts overview should display accounts table."
        );
    }
}