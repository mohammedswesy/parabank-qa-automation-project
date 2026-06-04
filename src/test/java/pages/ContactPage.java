package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactPage extends BasePage {

    private final By fullNameInput = By.id("name");

    private final By emailInput = By.id("email");

    private final By phoneInput = By.id("phone");

    private final By messageInput = By.id("message");

    private final By sendButton =
            By.cssSelector("input[value='Send to Customer Care']");

    private final By resultPanel = By.id("rightPanel");

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    public void sendMessage(
            String fullName,
            String email,
            String phone,
            String message
    ) {
        type(fullNameInput, fullName);

        type(emailInput, email);

        type(phoneInput, phone);

        type(messageInput, message);

        click(sendButton);
    }

    public String getResultText() {
        return text(resultPanel);
    }

    public boolean isSubmitted() {

        String result = getResultText().toLowerCase();

        return result.contains("thank you")
                || result.contains("customer care");
    }
}