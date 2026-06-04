package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountsOverviewPage extends BasePage {

    private final By title = By.cssSelector("#rightPanel h1.title");
    private final By accountTable = By.id("accountTable");
    private final By firstAccountLink = By.cssSelector("#accountTable a");
    private final By accountDetails = By.id("accountDetails");

    public AccountsOverviewPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return isDisplayed(title)
                && text(title).contains("Accounts Overview");
    }

    public boolean isAccountsTableDisplayed() {
        return isDisplayed(accountTable);
    }

    public String getFirstAccountNumber() {
        return text(firstAccountLink);
    }

    public void openFirstAccount() {
        click(firstAccountLink);
    }

    public boolean isAccountDetailsDisplayed() {
        return getCurrentUrl().contains("activity.htm")
                || isDisplayed(accountDetails);
    }
}