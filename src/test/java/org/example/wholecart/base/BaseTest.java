package org.example.wholecart.base;

import org.example.wholecart.pages.PageManager;
import org.example.wholecart.utils.DriverFactory;
import org.example.wholecart.utils.ExtentReportManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest implements IHookable {

    protected WebDriver driver;

    protected PageManager pages;

    private WebDriver originalDriver;

    @BeforeSuite(alwaysRun = true)
    public void startReport() {

        ExtentReportManager.startReport();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        DriverFactory.initializeDriver();
        originalDriver = DriverFactory.getDriver();
        WebDriverListener listener =
                new WebDriverListener() {

                    @Override
                    public void afterAnyWebDriverCall(
                            WebDriver driver,
                            Method method,
                            Object[] args,
                            Object result) {

                        String methodName = method.getName();

                        if (methodName.equals("get")) {
                            captureAction("Open application");

                        } else if (methodName.equals("navigate")) {
                            captureAction("Navigate");

                        } else if (methodName.equals("back")) {
                            captureAction("Navigate back");

                        } else if (methodName.equals("refresh")) {
                            captureAction("Refresh page");
                        }
                    }

                    @Override
                    public void afterAnyWebElementCall(
                            WebElement element,
                            Method method,
                            Object[] args,
                            Object result) {

                        String methodName = method.getName();

                        if (methodName.equals("click")) {
                            captureAction("Click element");

                        } else if (methodName.equals("sendKeys")) {
                            captureAction("Enter text");

                        } else if (methodName.equals("clear")) {
                            captureAction("Clear field");

                        } else if (methodName.equals("submit")) {
                            captureAction("Submit form");
                        }
                    }
                };

        driver = new EventFiringDecorator<>(listener).decorate(originalDriver);
        pages = new PageManager();
    }

    @Override
    public void run(
            IHookCallBack callBack,
            ITestResult testResult) {

        ExtentReportManager.createTest(
                testResult
                        .getMethod()
                        .getMethodName()
        );

        callBack.runTestMethod(testResult);

        String status =
                getStatus(testResult);

        String screenshotPath = takeScreenshot(testResult.getMethod().getMethodName() + "_" + status);

        if (testResult.getStatus()
                == ITestResult.SUCCESS) {

            ExtentReportManager.pass("PASS", screenshotPath);

        } else if (testResult.getStatus()
                == ITestResult.FAILURE) {

            ExtentReportManager.fail("FAIL", screenshotPath);

        } else {

            ExtentReportManager.fail("SKIPPED", screenshotPath);
        }
    }

    private void captureAction(
            String action) {

        if (originalDriver == null) {
            return;
        }

        String screenshotPath = takeScreenshot(action);
        ExtentReportManager.logStep(action, screenshotPath);
    }

    private String takeScreenshot(
            String name) {

        try {

            File source = ((TakesScreenshot) originalDriver).getScreenshotAs(OutputType.FILE);

            String timestamp =
                    new SimpleDateFormat(
                            "yyyyMMdd_HHmmss_SSS"
                    ).format(new Date());

            String safeName =
                    name.replaceAll(
                            "[^a-zA-Z0-9_-]",
                            "_"
                    );

            Path screenshotDirectory =
                    Paths.get(
                            "reports",
                            "screenshots"
                    );

            Files.createDirectories(
                    screenshotDirectory
            );

            String fileName =
                    safeName
                            + "_"
                            + timestamp
                            + ".png";

            Path destination =
                    screenshotDirectory.resolve(
                            fileName
                    );

            Files.copy(
                    source.toPath(),
                    destination,
                    StandardCopyOption.REPLACE_EXISTING
            );

            return "screenshots/" + fileName;

        } catch (IOException |
                 RuntimeException e) {

            return null;
        }
    }

    private String getStatus(
            ITestResult result) {

        if (result.getStatus()
                == ITestResult.SUCCESS) {

            return "PASS";

        } else if (result.getStatus()
                == ITestResult.FAILURE) {

            return "FAIL";
        }

        return "SKIPPED";
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        DriverFactory.quitDriver();
    }

    @AfterSuite(alwaysRun = true)
    public void closeReport() {

        ExtentReportManager.endReport();
    }
}