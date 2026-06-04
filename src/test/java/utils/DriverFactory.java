package utils;

import config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public final class DriverFactory {

    private DriverFactory() {
    }

    public static WebDriver createDriver(String browser) {

        if (browser == null || browser.isBlank()) {
            browser = ConfigReader.get("browser");
        }

        WebDriver driver;

        if (browser.equalsIgnoreCase("firefox")) {

            WebDriverManager.firefoxdriver().setup();

            FirefoxOptions firefoxOptions =
                    new FirefoxOptions();

            firefoxOptions.addPreference(
                    "dom.webnotifications.enabled",
                    false
            );

            if (ConfigReader.getBoolean("headless")) {
                firefoxOptions.addArguments("-headless");
            }

            driver = new FirefoxDriver(firefoxOptions);

        } else {

            WebDriverManager.chromedriver().setup();

            ChromeOptions chromeOptions =
                    new ChromeOptions();

            chromeOptions.addArguments("--remote-allow-origins=*");
            chromeOptions.addArguments("--disable-notifications");
            chromeOptions.addArguments("--disable-popup-blocking");
            chromeOptions.addArguments("--disable-extensions");
            chromeOptions.addArguments("--disable-infobars");
            chromeOptions.addArguments("--start-maximized");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--disable-dev-shm-usage");

            Map<String, Object> prefs =
                    new HashMap<>();

            prefs.put(
                    "profile.default_content_setting_values.notifications",
                    2
            );

            prefs.put(
                    "credentials_enable_service",
                    false
            );

            prefs.put(
                    "profile.password_manager_enabled",
                    false
            );

            chromeOptions.setExperimentalOption(
                    "prefs",
                    prefs
            );

            chromeOptions.setExperimentalOption(
                    "excludeSwitches",
                    new String[]{"enable-automation"}
            );

            chromeOptions.setExperimentalOption(
                    "useAutomationExtension",
                    false
            );

            if (ConfigReader.getBoolean("headless")) {

                chromeOptions.addArguments("--headless=new");

                chromeOptions.addArguments(
                        "--window-size=1920,1080"
                );
            }

            driver = new ChromeDriver(chromeOptions);
        }

        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(
                        ConfigReader.getInt("implicitWait")
                )
        );

        driver.manage().timeouts().pageLoadTimeout(
                Duration.ofSeconds(
                        ConfigReader.getInt("pageLoadTimeout")
                )
        );

        driver.manage().window().maximize();

        return driver;
    }
}