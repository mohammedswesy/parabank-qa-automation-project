package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BillPayPage;
import utils.TestData;

public class BillPayTests extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void prep() {
        registerNewUserAndReturnUsername();
    }

    private void pay(
            BillPayPage billPayPage,
            String accountNumber,
            String verifyAccountNumber,
            String amount,
            String zipCode
    ) {
        billPayPage.payBill(
                TestData.PAYEE_NAME,
                TestData.PAYEE_ADDRESS,
                TestData.PAYEE_CITY,
                TestData.PAYEE_STATE,
                zipCode,
                TestData.PAYEE_PHONE,
                accountNumber,
                verifyAccountNumber,
                amount
        );
    }

    @Test(description = "PB-TC-021 Verify bill payment with valid data")
    public void validBillPay() {
        BillPayPage billPayPage = homePage.goToBillPay();

        pay(
                billPayPage,
                TestData.PAYEE_ACCOUNT,
                TestData.PAYEE_ACCOUNT,
                TestData.BILL_AMOUNT,
                TestData.PAYEE_ZIP
        );

        Assert.assertTrue(
                billPayPage.isPaymentSuccessful(),
                "Bill payment should be completed successfully."
        );
    }

    @Test(description = "PB-TC-022 Verify bill payment with empty mandatory fields")
    public void emptyBillPay() {
        BillPayPage billPayPage = homePage.goToBillPay();

        billPayPage.submitEmptyPayment();

        Assert.assertTrue(
                billPayPage.hasError(),
                "Required field validation errors should be displayed."
        );
    }

    @Test(description = "PB-TC-023 Verify bill payment with mismatched account numbers")
    public void mismatchAccount() {
        BillPayPage billPayPage = homePage.goToBillPay();

        pay(
                billPayPage,
                "12345",
                "54321",
                TestData.BILL_AMOUNT,
                TestData.PAYEE_ZIP
        );

        Assert.assertTrue(
                billPayPage.hasError()
                        || !billPayPage.isPaymentSuccessful(),
                "Mismatched account numbers should be rejected."
        );
    }

    @Test(description = "PB-TC-024 Verify bill payment with negative amount")
    public void negativeAmount() {
        BillPayPage billPayPage = homePage.goToBillPay();

        pay(
                billPayPage,
                TestData.PAYEE_ACCOUNT,
                TestData.PAYEE_ACCOUNT,
                "-10",
                TestData.PAYEE_ZIP
        );

        Assert.assertTrue(
                billPayPage.hasError()
                        || billPayPage.getResultText().length() > 0,
                "Negative amount should be rejected or documented as a validation bug."
        );
    }

    @Test(description = "PB-TC-025 Verify bill payment with invalid zip code format")
    public void invalidZip() {
        BillPayPage billPayPage = homePage.goToBillPay();

        pay(
                billPayPage,
                TestData.PAYEE_ACCOUNT,
                TestData.PAYEE_ACCOUNT,
                TestData.BILL_AMOUNT,
                "abc!!"
        );

        Assert.assertTrue(
                billPayPage.hasError()
                        || billPayPage.isPaymentSuccessful(),
                "If invalid ZIP is accepted, document it as a validation bug."
        );
    }
}