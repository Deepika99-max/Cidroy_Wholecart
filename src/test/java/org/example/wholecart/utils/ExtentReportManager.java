package org.example.wholecart.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extent;

    private static final ThreadLocal<ExtentTest> extentTest =
            new ThreadLocal<>();

    public static void startReport() {

        ExtentSparkReporter sparkReporter =
                new ExtentSparkReporter(
                        "reports/WholeCartTestReport.html"
                );

        extent = new ExtentReports();

        extent.attachReporter(sparkReporter);

        extent.setSystemInfo(
                "Application",
                "WholeCart Marketplace"
        );

        extent.setSystemInfo(
                "Tester",
                "Deepika"
        );

        extent.setSystemInfo(
                "Framework",
                "Java + Selenium + TestNG"
        );
    }

    public static void createTest(String testName) {

        ExtentTest test = extent.createTest(testName);

        extentTest.set(test);
    }

    public static void logStep(
            String step,
            String screenshotRelativePath) {

        ExtentTest test = extentTest.get();

        if (test == null) {
            return;
        }

        attachScreenshot(
                test,
                step,
                screenshotRelativePath,
                false
        );
    }

    public static void pass(
            String message,
            String screenshotRelativePath) {

        ExtentTest test = extentTest.get();

        if (test == null) {
            return;
        }

        attachScreenshot(
                test,
                message,
                screenshotRelativePath,
                true
        );
    }

    public static void fail(
            String message,
            String screenshotRelativePath) {

        ExtentTest test = extentTest.get();

        if (test == null) {
            return;
        }

        attachScreenshot(
                test,
                message,
                screenshotRelativePath,
                false
        );
    }

    private static void attachScreenshot(
            ExtentTest test,
            String message,
            String screenshotRelativePath,
            boolean isPass) {

        try {

            if (screenshotRelativePath != null) {

                if (isPass) {

                    test.pass(
                            message,
                            MediaEntityBuilder
                                    .createScreenCaptureFromPath(
                                            screenshotRelativePath
                                    )
                                    .build()
                    );

                } else {

                    test.info(
                            message,
                            MediaEntityBuilder
                                    .createScreenCaptureFromPath(
                                            screenshotRelativePath
                                    )
                                    .build()
                    );
                }

            } else {

                if (isPass) {
                    test.pass(message);
                } else {
                    test.info(message);
                }
            }

        } catch (Exception e) {

            if (isPass) {
                test.pass(message);
            } else {
                test.info(message);
            }
        }
    }

    public static void endReport() {

        if (extent != null) {

            extent.flush();
        }
    }
}
