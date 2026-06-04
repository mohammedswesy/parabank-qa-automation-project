package base;

import config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.HomePage;
import pages.RegisterPage;
import utils.DriverFactory;
import utils.ScreenshotUtil;
import utils.TestData;

public abstract class BaseTest {

    protected WebDriver driver;
    protected HomePage homePage;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setup(@Optional("") String browser) {
        driver = DriverFactory.createDriver(browser);
        driver.get(ConfigReader.get("baseUrl"));
        homePage = new HomePage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (!result.isSuccess()) {
            ScreenshotUtil.capture(driver, result.getMethod().getMethodName());
        }

        if (driver != null) {
            driver.quit();
        }
    }

    protected String registerNewUserAndReturnUsername() {
        String username = TestData.uniqueUsername();

        RegisterPage registerPage = homePage.clickRegister();

        registerPage.register(
                TestData.FIRST_NAME,
                TestData.LAST_NAME,
                TestData.ADDRESS,
                TestData.CITY,
                TestData.STATE,
                TestData.ZIP_CODE,
                TestData.PHONE,
                TestData.SSN,
                username,
                TestData.PASSWORD,
                TestData.PASSWORD
        );

        return username;
    }
}