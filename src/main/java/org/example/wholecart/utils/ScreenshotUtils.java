package org.example.wholecart.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ScreenshotUtils {

    private ScreenshotUtils() {
    }

    public static String captureScreenshot(String testName) {

        try {

            File source = ((TakesScreenshot) DriverFactory.getDriver())
                    .getScreenshotAs(OutputType.FILE);
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

            String filePath = "build/screenshots/" + testName + "_" + timestamp + ".png";

            File destination = new File(filePath);
            FileUtils.copyFile(source, destination);
            return destination.getAbsolutePath();

        } catch (Exception e) {

            return "Screenshot failed: " + e.getMessage();
        }
    }
}