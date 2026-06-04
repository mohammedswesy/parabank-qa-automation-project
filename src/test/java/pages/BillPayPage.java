package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BillPayPage extends BasePage {

    private final By title = By.cssSelector("#rightPanel h1.title");

    private final By payeeName = By.name("payee.name");
    private final By payeeAddress = By.name("payee.address.street");
    private final By payeeCity = By.name("payee.address.city");
    private final By payeeState = By.name("payee.address.state");
    private final By payeeZipCode = By.name("payee.address.zipCode");
    private final By payeePhone = By.name("payee.phoneNumber");

    private final By accountNumber = By.name("payee.accountNumber");
    private final By verifyAccountNumber = By.name("verifyAccount");
    private final By amount = By.name("amount");
    private final By fromAccount = By.name("fromAccountId");

    private final By sendPaymentButton =
            By.cssSelector("input[value='Send Payment']");

    private final By resultPanel = By.id("rightPanel");
    private final By errorMessage = By.cssSelector("#rightPanel .error");

    public BillPayPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return isDisplayed(title)
                && text(title).contains("Bill Payment Service");
    }

    public void payBill(
            String name,
            String address,
            String city,
            String state,
            String zipCode,
            String phone,
            String account,
            String verifyAccount,
            String paymentAmount
    ) {
        type(payeeName, name);
        type(payeeAddress, address);
        type(payeeCity, city);
        type(payeeState, state);
        type(payeeZipCode, zipCode);
        type(payeePhone, phone);
        type(accountNumber, account);
        type(verifyAccountNumber, verifyAccount);
        type(amount, paymentAmount);

        if (selectOptionsCount(fromAccount) > 0) {
            selectByIndex(fromAccount, 0);
        }

        click(sendPaymentButton);
    }

    public void submitEmptyPayment() {
        click(sendPaymentButton);
    }

    public boolean isPaymentSuccessful(){
        String t = getResultText().toLowerCase();

        return t.contains("bill payment complete")
                || t.contains("success")
                || t.contains("payment has been sent");
    }

    public boolean hasError() {
        return isDisplayed(errorMessage)
                || getResultText().toLowerCase().contains("error")
                || getResultText().toLowerCase().contains("required")
                || getResultText().toLowerCase().contains("invalid");
    }

    public String getResultText() {
        return text(resultPanel);
    }
}