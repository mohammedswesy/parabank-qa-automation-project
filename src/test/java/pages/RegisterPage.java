package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    private final By firstNameInput =
            By.id("customer.firstName");

    private final By lastNameInput =
            By.id("customer.lastName");

    private final By addressInput =
            By.id("customer.address.street");

    private final By cityInput =
            By.id("customer.address.city");

    private final By stateInput =
            By.id("customer.address.state");

    private final By zipCodeInput =
            By.id("customer.address.zipCode");

    private final By phoneNumberInput =
            By.id("customer.phoneNumber");

    private final By ssnInput =
            By.id("customer.ssn");

    private final By usernameInput =
            By.id("customer.username");

    private final By passwordInput =
            By.id("customer.password");

    private final By confirmPasswordInput =
            By.id("repeatedPassword");

    private final By registerButton =
            By.cssSelector("input[value='Register']");

    private final By successTitle =
            By.cssSelector("#rightPanel h1.title");

    private final By successMessage =
            By.cssSelector("#rightPanel p");

    private final By errorMessage =
            By.cssSelector("#rightPanel .error");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void register(
            String firstName,
            String lastName,
            String address,
            String city,
            String state,
            String zipCode,
            String phoneNumber,
            String ssn,
            String username,
            String password,
            String confirmPassword
    ) {

        type(firstNameInput, firstName);

        type(lastNameInput, lastName);

        type(addressInput, address);

        type(cityInput, city);

        type(stateInput, state);

        type(zipCodeInput, zipCode);

        type(phoneNumberInput, phoneNumber);

        type(ssnInput, ssn);

        type(usernameInput, username);

        type(passwordInput, password);

        type(confirmPasswordInput, confirmPassword);

        click(registerButton);
    }

    public void submitEmptyForm() {

        click(registerButton);
    }

    public boolean isRegistrationSuccessful() {

        return isDisplayed(successTitle)
                && text(successTitle).contains("Welcome");
    }

    public String getSuccessMessage() {

        return text(successMessage);
    }

    public String getErrorText() {
        try {
            return text(errorMessage);
        } catch (Exception e) {
            return "";
        }
    }
}