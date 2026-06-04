package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ScreenshotUtil {

    private ScreenshotUtil() {
    }

    public static void capture(
            WebDriver driver,
            String testName
    ) {

        try {

            if (driver == null) {
                return;
            }

            Path screenshotDirectory =
                    Path.of(
                            "test-output",
                            "screenshots"
                    );

            Files.createDirectories(
                    screenshotDirectory
            );

            File sourceFile =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(
                                    OutputType.FILE
                            );

            String timestamp =
                    LocalDateTime.now()
                            .format(
                                    DateTimeFormatter.ofPattern(
                                            "yyyyMMdd_HHmmss"
                                    )
                            );

            Path targetFile =
                    screenshotDirectory.resolve(
                            testName
                                    + "_"
                                    + timestamp
                                    + ".png"
                    );

            Files.copy(
                    sourceFile.toPath(),
                    targetFile
            );

            System.out.println(
                    "Screenshot saved: "
                            + targetFile.toAbsolutePath()
            );

        } catch (Exception e) {

            System.out.println(
                    "Screenshot capture failed: "
                            + e.getMessage()
            );
        }
    }
}