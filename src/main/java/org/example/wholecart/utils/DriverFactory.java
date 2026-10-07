package org.example.wholecart.utils;

import org.example.wholecart.constants.FrameworkConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public final class DriverFactory {

    private DriverFactory() {
    }

    private static final ThreadLocal<WebDriver> DRIVER =
            new ThreadLocal<>();

    public static void initializeDriver() {

        String browser = System.getProperty(
                "browser",
                FrameworkConstants.DEFAULT_BROWSER
        );

        boolean headless = Boolean.parseBoolean(
                System.getProperty("headless", "false")
        );

        WebDriver driver;

        switch (browser.toLowerCase()) {

            case "chrome":

                ChromeOptions chromeOptions =
                        new ChromeOptions();

                if (headless) {
                    chromeOptions.addArguments(
                            "--headless=new"
                    );
                }

                chromeOptions.addArguments(
                        "--start-maximized"
                );

                chromeOptions.addArguments(
                        "--disable-notifications"
                );

                driver = new ChromeDriver(
                        chromeOptions
                );

                break;

            case "firefox":

                FirefoxOptions firefoxOptions =
                        new FirefoxOptions();

                if (headless) {
                    firefoxOptions.addArguments(
                            "--headless"
                    );
                }

                driver = new FirefoxDriver(
                        firefoxOptions
                );

                break;

            default:

                throw new IllegalArgumentException(
                        "Unsupported browser: " + browser
                );
        }

        driver.manage()
                .timeouts()
                .implicitlyWait(
                        Duration.ZERO
                );

        driver.manage()
                .timeouts()
                .pageLoadTimeout(
                        Duration.ofSeconds(
                                FrameworkConstants.PAGE_LOAD_TIMEOUT
                        )
                );

        driver.manage()
                .window()
                .maximize();

        DRIVER.set(driver);
    }

    public static WebDriver getDriver() {

        WebDriver driver = DRIVER.get();

        if (driver == null) {

            throw new IllegalStateException(
                    "WebDriver is not initialized."
            );
        }

        return driver;
    }

    public static void quitDriver() {

        WebDriver driver = DRIVER.get();

        if (driver != null) {

            driver.quit();

            DRIVER.remove();
        }
    }
}