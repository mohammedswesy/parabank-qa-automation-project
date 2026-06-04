package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.RequestLoanPage;

public class RequestLoanTests extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void prep() {
        registerNewUserAndReturnUsername();
    }

    @Test(description = "PB-TC-030 Verify loan request with valid amount and down payment")
    public void validLoan() {
        RequestLoanPage requestLoanPage = homePage.goToRequestLoan();

        requestLoanPage.applyForLoan("1000", "100");

        Assert.assertTrue(
                requestLoanPage.isResultDisplayed(),
                "Loan request result should be displayed."
        );
    }

    @Test(description = "PB-TC-031 Verify loan request with empty fields")
    public void emptyLoan() {
        RequestLoanPage requestLoanPage = homePage.goToRequestLoan();

        requestLoanPage.applyForLoan("", "");

        Assert.assertTrue(
                requestLoanPage.getResultText().length() > 0,
                "System should display validation or response message."
        );
    }
}