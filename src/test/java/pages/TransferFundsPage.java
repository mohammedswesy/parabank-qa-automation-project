package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TransferFundsPage extends BasePage {

    private final By title =
            By.cssSelector("#rightPanel h1.title");

    private final By amountInput =
            By.id("amount");

    private final By fromAccountDropdown =
            By.id("fromAccountId");

    private final By toAccountDropdown =
            By.id("toAccountId");

    private final By transferButton =
            By.cssSelector("input[value='Transfer']");

    private final By resultPanel =
            By.id("rightPanel");

    private final By errorMessage =
            By.cssSelector("#rightPanel .error");

    public TransferFundsPage(WebDriver driver) {
        super(driver);
    }
    private final By panel = By.id("showResult");

    public boolean isLoaded() {

        return isDisplayed(title)
                && text(title).contains("Transfer Funds");
    }

    public void transfer(String transferAmount) {

        type(amountInput, transferAmount);

        if (selectOptionsCount(fromAccountDropdown) > 0) {
            selectByIndex(fromAccountDropdown, 0);
        }

        if (selectOptionsCount(toAccountDropdown) > 1) {
            selectByIndex(toAccountDropdown, 1);

        } else if (selectOptionsCount(toAccountDropdown) > 0) {
            selectByIndex(toAccountDropdown, 0);
        }

        click(transferButton);
    }

    public boolean isTransferSuccessful(){
        return getCurrentUrl().contains("transfer")
                || text(panel).toLowerCase().contains("transfer complete")
                || text(panel).toLowerCase().contains("success");
    }

    public String getResultText() {

        return text(resultPanel);
    }

    public boolean hasError() {

        return isDisplayed(errorMessage)
                || getResultText()
                .toLowerCase()
                .contains("error");
    }
}