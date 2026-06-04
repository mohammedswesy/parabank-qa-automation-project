package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationPage extends BasePage {

    private final By aboutUsLink = By.linkText("About Us");
    private final By servicesLink = By.linkText("Services");
    private final By productsLink = By.linkText("Products");
    private final By locationsLink = By.linkText("Locations");

    private final By pageBody = By.tagName("body");

    public NavigationPage(WebDriver driver) {
        super(driver);
    }

    public void clickAboutUs() {
        click(aboutUsLink);
    }

    public void clickServices() {
        click(servicesLink);
    }

    public void clickProducts() {
        click(productsLink);
    }

    public void clickLocations() {
        click(locationsLink);
    }

    public boolean pageIsNotBroken() {
        String bodyText = driver.findElement(pageBody)
                .getText()
                .toLowerCase();

        return !bodyText.contains("404")
                && !bodyText.contains("not found")
                && !bodyText.contains("server error");
    }
}