package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RequestLoanPage extends BasePage {

    private final By title =
            By.cssSelector("#rightPanel h1.title");

    private final By loanAmountInput =
            By.id("amount");

    private final By downPaymentInput =
            By.id("downPayment");

    private final By fromAccountDropdown =
            By.id("fromAccountId");

    private final By applyNowButton =
            By.cssSelector("input[value='Apply Now']");

    private final By resultPanel =
            By.id("rightPanel");

    public RequestLoanPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return isDisplayed(title)
                && text(title).contains("Apply for a Loan");
    }

    public void applyForLoan(
            String loanAmount,
            String downPayment
    ) {
        type(loanAmountInput, loanAmount);

        type(downPaymentInput, downPayment);

        if (selectOptionsCount(fromAccountDropdown) > 0) {
            selectByIndex(fromAccountDropdown, 0);
        }

        click(applyNowButton);
    }

    public String getResultText() {
        return text(resultPanel);
    }

    public boolean isResultDisplayed(){
        String r=getResultText().toLowerCase();

        return r.contains("approved")
                || r.contains("loan request")
                || r.contains("processing")
                || r.contains("denied");
    }
}