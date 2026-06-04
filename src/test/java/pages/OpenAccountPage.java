package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OpenAccountPage extends BasePage {

    private final By title =
            By.cssSelector("#rightPanel h1.title");

    private final By accountTypeDropdown =
            By.id("type");

    private final By fromAccountDropdown =
            By.id("fromAccountId");

    private final By openNewAccountButton =
            By.cssSelector("input[value='Open New Account']");

    private final By newAccountId =
            By.id("newAccountId");

    public OpenAccountPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {

        return isDisplayed(title)
                && text(title).contains("Open New Account");
    }

    public void openCheckingAccount() {

        selectByVisibleText(
                accountTypeDropdown,
                "CHECKING"
        );

        selectByIndex(fromAccountDropdown, 0);

        click(openNewAccountButton);
    }

    public void openSavingsAccount() {

        selectByVisibleText(
                accountTypeDropdown,
                "SAVINGS"
        );

        selectByIndex(fromAccountDropdown, 0);

        click(openNewAccountButton);
    }

    public boolean isAccountCreated() {
        return isDisplayed(newAccountId)
                || getCurrentUrl().contains("openaccount")
                || getCurrentUrl().contains("overview");
    }

    public String getNewAccountNumber() {

        return text(newAccountId);
    }
}