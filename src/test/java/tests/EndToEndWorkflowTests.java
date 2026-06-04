package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BillPayPage;
import pages.OpenAccountPage;
import pages.TransferFundsPage;
import utils.TestData;

public class EndToEndWorkflowTests extends BaseTest {

    @Test(description = "PB-TC-040 Verify core workflow end-to-end")
    public void coreWorkflow() {

        registerNewUserAndReturnUsername();

        Assert.assertTrue(
                homePage.isLoggedIn(),
                "User should be logged in after registration."
        );

        OpenAccountPage openAccountPage =
                homePage.goToOpenNewAccount();

        openAccountPage.openCheckingAccount();

        Assert.assertTrue(
                openAccountPage.isAccountCreated(),
                "New checking account should be created successfully."
        );

        TransferFundsPage transferFundsPage =
                homePage.goToTransferFunds();

        transferFundsPage.transfer(
                TestData.TRANSFER_AMOUNT
        );

        Assert.assertTrue(
                transferFundsPage.isTransferSuccessful(),
                "Fund transfer should be completed successfully."
        );

        BillPayPage billPayPage =
                homePage.goToBillPay();

        billPayPage.payBill(
                TestData.PAYEE_NAME,
                TestData.PAYEE_ADDRESS,
                TestData.PAYEE_CITY,
                TestData.PAYEE_STATE,
                TestData.PAYEE_ZIP,
                TestData.PAYEE_PHONE,
                TestData.PAYEE_ACCOUNT,
                TestData.PAYEE_ACCOUNT,
                TestData.BILL_AMOUNT
        );

        Assert.assertTrue(
                billPayPage.isPaymentSuccessful(),
                "Bill payment should be completed successfully."
        );

        homePage.logout();

        Assert.assertFalse(
                homePage.isLoggedIn(),
                "User should be logged out successfully."
        );
    }
}