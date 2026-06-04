package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FindTransactionsPage extends BasePage {

    private final By title =
            By.cssSelector("#rightPanel h1.title");

    private final By accountDropdown =
            By.id("accountId");

    private final By amountInput =
            By.id("criteria.amount");

    private final By dateInput =
            By.id("criteria.onDate");

    private final By findByAmountButton =
            By.cssSelector(
                    "button[ng-click='criteria.searchType = \"AMOUNT\"']"
            );

    private final By findByDateButton =
            By.cssSelector(
                    "button[ng-click='criteria.searchType = \"DATE\"']"
            );

    private final By transactionResultsTable =
            By.id("transactionTable");

    private final By resultPanel =
            By.id("rightPanel");

    public FindTransactionsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {

        return isDisplayed(title)
                && text(title).contains("Find Transactions");
    }

    public void findByAmount(String amountValue) {

        if (selectOptionsCount(accountDropdown) > 0) {
            selectByIndex(accountDropdown, 0);
        }

        type(amountInput, amountValue);

        click(findByAmountButton);
    }

    public void findByDate(String dateValue) {

        if (selectOptionsCount(accountDropdown) > 0) {
            selectByIndex(accountDropdown, 0);
        }

        type(dateInput, dateValue);

        click(findByDateButton);
    }

    public boolean resultsAreaDisplayed() {

        return isDisplayed(transactionResultsTable)
                || text(resultPanel)
                .toLowerCase()
                .contains("transaction");
    }

    public String getPageText() {

        return text(resultPanel);
    }
}