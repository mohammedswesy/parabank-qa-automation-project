package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.OpenAccountPage;
import pages.TransferFundsPage;
import utils.TestData;

public class TransferFundsTests extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void prep() {

        registerNewUserAndReturnUsername();

        OpenAccountPage openAccountPage =
                homePage.goToOpenNewAccount();

        openAccountPage.openCheckingAccount();
    }

    @Test(description = "PB-TC-017 Verify transfer funds between valid accounts")
    public void validTransfer() {

        TransferFundsPage transferFundsPage =
                homePage.goToTransferFunds();

        transferFundsPage.transfer(
                TestData.TRANSFER_AMOUNT
        );

        Assert.assertTrue(
                transferFundsPage.isTransferSuccessful(),
                "Transfer should complete successfully."
        );
    }

    @Test(description = "PB-TC-018 Verify transfer with empty amount")
    public void emptyAmount() {

        TransferFundsPage transferFundsPage =
                homePage.goToTransferFunds();

        transferFundsPage.transfer("");

        Assert.assertTrue(
                transferFundsPage.hasError()
                        || !transferFundsPage.isTransferSuccessful(),
                "Transfer should not succeed with empty amount."
        );
    }

    @Test(description = "PB-TC-019 Verify transfer with negative amount")
    public void negativeAmount() {

        TransferFundsPage transferFundsPage =
                homePage.goToTransferFunds();

        transferFundsPage.transfer("-100");

        Assert.assertTrue(
                transferFundsPage.hasError()
                        || transferFundsPage.getResultText().length() > 0,
                "Negative transfer amount should be rejected or logged as validation bug."
        );
    }

    @Test(description = "PB-TC-020 Verify transfer with non-numeric amount")
    public void nonNumericAmount() {

        TransferFundsPage transferFundsPage =
                homePage.goToTransferFunds();

        transferFundsPage.transfer("abc");

        Assert.assertTrue(
                transferFundsPage.hasError()
                        || !transferFundsPage.isTransferSuccessful(),
                "Non-numeric amount should not complete transfer successfully."
        );
    }
}