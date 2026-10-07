package org.example.wholecart.actions;

import org.example.wholecart.constants.FrameworkConstants;
import org.example.wholecart.utils.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonActions {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public CommonActions() {

        this.driver = DriverFactory.getDriver();

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(
                        FrameworkConstants.DEFAULT_TIMEOUT
                )
        );
    }

    public void click(By locator) {

        WebElement element =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                locator
                        )
                );

        element.click();
    }

    public void enterText(By locator, String value) {

        WebElement element =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                locator
                        )
                );

        element.clear();
        element.sendKeys(value);
    }

    public String getText(By locator) {

        WebElement element =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                locator
                        )
                );

        return element.getText().trim();
    }

    public boolean isDisplayed(By locator) {

        try {

            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            locator
                    )
            ).isDisplayed();

        } catch (TimeoutException e) {

            return false;
        }
    }

    public boolean isEnabled(By locator) {

        try {

            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            locator
                    )
            ).isEnabled();

        } catch (TimeoutException e) {

            return false;
        }
    }

    public void selectByVisibleText(
            By locator,
            String visibleText) {

        WebElement element =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                locator
                        )
                );

        element.findElements(By.tagName("option"))
                .stream()
                .filter(option ->
                        option.getText()
                                .trim()
                                .equalsIgnoreCase(visibleText))
                .findFirst()
                .orElseThrow(
                        () -> new NoSuchElementException(
                                "Option not found: "
                                        + visibleText
                        )
                )
                .click();
    }

    public void waitForPageTitle(String title) {

        wait.until(
                ExpectedConditions.titleContains(title)
        );
    }

    public void waitForUrlContains(String urlPart) {

        wait.until(
                ExpectedConditions.urlContains(urlPart)
        );
    }

    public void waitForVisible(By locator) {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        locator
                )
        );
    }

    public void waitForClickable(By locator) {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        locator
                )
        );
    }

    public void scrollIntoView(By locator) {

        WebElement element =
                wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                locator
                        )
                );

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        element
                );
    }

    public void clickWithJavaScript(By locator) {

        WebElement element =
                wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                locator
                        )
                );

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].click();",
                        element
                );
    }

    public void clearAndEnterText(
            By locator,
            String value) {

        WebElement element =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                locator
                        )
                );

        element.click();
        element.sendKeys(Keys.COMMAND, "a");
        element.sendKeys(Keys.BACK_SPACE);
        element.sendKeys(value);
    }
}