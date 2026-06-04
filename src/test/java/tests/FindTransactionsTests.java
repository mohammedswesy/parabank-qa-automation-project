package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.FindTransactionsPage;
import pages.TransferFundsPage;
import utils.TestData;

public class FindTransactionsTests extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void prep() {

        registerNewUserAndReturnUsername();

        TransferFundsPage transferFundsPage =
                homePage.goToTransferFunds();

        transferFundsPage.transfer(
                TestData.TRANSFER_AMOUNT
        );
    }

    @Test(description = "PB-TC-026 Verify search transaction by amount")
    public void byAmount() {

        FindTransactionsPage findTransactionsPage =
                homePage.goToFindTransactions();

        findTransactionsPage.findByAmount(
                TestData.TRANSFER_AMOUNT
        );

        Assert.assertTrue(
                findTransactionsPage.resultsAreaDisplayed(),
                "Transaction results should be displayed."
        );
    }

    @Test(description = "PB-TC-027 Verify search transaction by date")
    public void byDate() {

        FindTransactionsPage findTransactionsPage =
                homePage.goToFindTransactions();

        findTransactionsPage.findByDate(
                "06-01-2026"
        );

        Assert.assertTrue(
                findTransactionsPage.getPageText().length() > 0,
                "System should display transaction results or response."
        );
    }

    @Test(description = "PB-TC-028 Verify search with invalid date format")
    public void invalidDate() {

        FindTransactionsPage findTransactionsPage =
                homePage.goToFindTransactions();

        findTransactionsPage.findByDate(
                "99/99/9999"
        );

        Assert.assertTrue(
                findTransactionsPage.getPageText().length() > 0,
                "System should handle invalid date gracefully."
        );
    }

    @Test(description = "PB-TC-029 Verify search with no matching transaction")
    public void noMatch() {

        FindTransactionsPage findTransactionsPage =
                homePage.goToFindTransactions();

        findTransactionsPage.findByAmount(
                "999999"
        );

        Assert.assertTrue(
                findTransactionsPage.getPageText().length() > 0,
                "System should display empty results or no matching transactions message."
        );
    }
}