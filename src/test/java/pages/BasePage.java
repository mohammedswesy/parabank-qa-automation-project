package pages;

import config.ConfigReader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected BasePage(WebDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(
                        ConfigReader.getInt("explicitWait")
                )
        );
    }

    protected WebElement visible(By locator) {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    protected WebElement clickable(By locator) {

        return wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        );
    }

    protected void click(By locator) {

        closeUnexpectedAlerts();

        WebElement element = clickable(locator);

        scrollTo(element);

        element.click();
    }

    protected void type(By locator, String value) {

        WebElement element = visible(locator);

        scrollTo(element);

        element.clear();

        element.sendKeys(value == null ? "" : value);
    }

    protected String text(By locator) {

        return visible(locator)
                .getText()
                .trim();
    }

    protected boolean isDisplayed(By locator) {

        try {
            return visible(locator).isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }

    protected void selectByVisibleText(By locator, String value) {

        new Select(
                visible(locator)
        ).selectByVisibleText(value);
    }

    protected void selectByIndex(By locator, int index) {

        new Select(
                visible(locator)
        ).selectByIndex(index);
    }

    protected int selectOptionsCount(By locator) {

        return new Select(
                visible(locator)
        ).getOptions().size();
    }

    protected void scrollTo(WebElement element) {

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        element
                );
    }

    protected void closeUnexpectedAlerts() {

        try {

            Alert alert = driver.switchTo().alert();

            alert.accept();

        } catch (NoAlertPresentException ignored) {
        }
    }

    public String getCurrentUrl() {

        return driver.getCurrentUrl();
    }
}