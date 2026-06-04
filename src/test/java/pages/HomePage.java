package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private final By usernameInput =
            By.name("username");

    private final By passwordInput =
            By.name("password");

    private final By loginButton =
            By.cssSelector("input[value='Log In']");

    private final By registerLink =
            By.linkText("Register");

    private final By loginErrorMessage =
            By.cssSelector("#rightPanel .error");

    private final By logoutLink =
            By.linkText("Log Out");

    private final By accountsOverviewLink =
            By.linkText("Accounts Overview");

    private final By openNewAccountLink =
            By.linkText("Open New Account");

    private final By transferFundsLink =
            By.linkText("Transfer Funds");

    private final By billPayLink =
            By.linkText("Bill Pay");

    private final By findTransactionsLink =
            By.linkText("Find Transactions");

    private final By requestLoanLink =
            By.linkText("Request Loan");

    private final By contactLink =
            By.linkText("contact");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public RegisterPage clickRegister() {

        click(registerLink);

        return new RegisterPage(driver);
    }

    public AccountsOverviewPage login(
            String username,
            String password
    ) {
        type(usernameInput, username);

        type(passwordInput, password);

        click(loginButton);

        return new AccountsOverviewPage(driver);
    }

    public void loginExpectingFailure(
            String username,
            String password
    ) {
        type(usernameInput, username);

        type(passwordInput, password);

        click(loginButton);
    }

    public String getLoginError() {
        try {
            return text(loginErrorMessage);
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isLoggedIn() {

        return isDisplayed(logoutLink);
    }

    public void logout() {

        click(logoutLink);
    }

    public AccountsOverviewPage goToAccountsOverview() {

        click(accountsOverviewLink);

        return new AccountsOverviewPage(driver);
    }

    public OpenAccountPage goToOpenNewAccount() {

        click(openNewAccountLink);

        return new OpenAccountPage(driver);
    }

    public TransferFundsPage goToTransferFunds() {

        click(transferFundsLink);

        return new TransferFundsPage(driver);
    }

    public BillPayPage goToBillPay() {

        click(billPayLink);

        return new BillPayPage(driver);
    }

    public FindTransactionsPage goToFindTransactions() {

        click(findTransactionsLink);

        return new FindTransactionsPage(driver);
    }

    public RequestLoanPage goToRequestLoan() {

        click(requestLoanLink);

        return new RequestLoanPage(driver);
    }

    public ContactPage goToContact() {

        click(contactLink);

        return new ContactPage(driver);
    }
}