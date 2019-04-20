package Support;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.NoSuchElementException;

public class SupportUtil {
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions builder;
    public Alert alert;
    public JavascriptExecutor js;

    public SupportUtil(WebDriver driver){
        this.driver = driver;
    }

    public void waitForElDisplay(By by, int timeOut) {
        wait = new WebDriverWait(driver, timeOut);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception ex) {
            wait.until(ExpectedConditions.elementSelectionStateToBe(by, true));
        }
    }

    public void hoverToEl(By by) {
        builder = new Actions(driver);
        waitForElDisplay(by, 10);
        WebElement el = driver.findElement(by);
        Action hover = builder.moveToElement(el).build();
        hover.perform();
    }

    public void hoverToEl(WebElement el) {
        builder = new Actions(driver);
        Action hover = builder.moveToElement(el).build();
        hover.perform();
    }

    public void clickEl(By by) {
        wait = new WebDriverWait(driver, 10);
        try {
            WebElement el = driver.findElement(by);
            el.click();
        } catch (NoSuchElementException ex) {
            wait.until(ExpectedConditions.elementToBeClickable(by));
            WebElement el = driver.findElement(by);
            hoverToEl(el);
            el.click();
        }
    }

    public void clickAndDrag(By firstBy, By lastBy) {
        builder = new Actions(driver);
        waitForElDisplay(firstBy, 10);
        WebElement firstEl = driver.findElement(firstBy);
        Action clickAndHold = builder.clickAndHold(firstEl).build();
        clickAndHold.perform();
        WebElement lastEl = driver.findElement(lastBy);
        Action drag = builder.moveToElement(lastEl).release().build();
        drag.perform();
    }

    public void doubleClick(By by) {
        builder = new Actions(driver);
        waitForElDisplay(by, 10);
        WebElement el = driver.findElement(by);
        Action doubleClick = builder.doubleClick(el).build();
        doubleClick.perform();
    }

    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException Ex) {
            return false;
        }
    }

    public void verifyTextAlert(String expected){
        if (isAlertPresent()) {
            alert = driver.switchTo().alert();
            String actual = alert.getText();
            if (actual.contains(expected)) {
                Assert.assertTrue(true);
            } else {
                Assert.fail("failed!!");
            }
        }
    }

    public void waitForElDisplay(WebElement el, int timeOut) {
        wait = new WebDriverWait(driver, timeOut);
        try {
            wait.until(ExpectedConditions.visibilityOf(el));
        } catch (Exception ex) {
            wait.until(ExpectedConditions.elementSelectionStateToBe(el, true));
        }
    }

    public void clickElByJS(By by) {
        js = (JavascriptExecutor) driver;
        waitForElDisplay(by, 10);
        WebElement el = driver.findElement(by);
        js.executeScript("arguments[0].click();", el);
    }

    public void clickElByJS(WebElement el) {
        js = (JavascriptExecutor) driver;
//        waitForElDisplay(el, 10);
        js.executeScript("arguments[0].click();", el);
    }

    public void checkCustomCkBox(WebElement el) {
        if (!el.isSelected()) {
            clickElByJS(el);
        }
    }

    public void unCheckCustomCkBox(WebElement el) {
        if (el.isSelected()) {
            clickElByJS(el);
        }
    }

    public void scrollToEl(WebElement el){
//        js = (JavascriptExecutor) driver;
//        js.executeScript()
//        builder = new Actions(driver);
//        Action scroll =
    }

}
