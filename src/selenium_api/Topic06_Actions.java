package selenium_api;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Topic06_Actions {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions builder;
    private Alert alert;

    //TC001
    private By profile = By.xpath("//div[@class='desktop-user']");
    private By signUp = By.xpath("//a[@data-track='signup']");
    private By signUpForm = By.xpath("//div[@class='register-box']");
    //TC002
    private By allClick = By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']");
    private By firstClick = By.xpath("//li[text()='1']");
    private By lastClick = By.xpath("//li[text()='4']");
    //TC003
    private By doubleClick = By.xpath("//button[text()='Double-Click Me!']");
    //TC004
    private By rightClick = By.xpath("//span[text()='right click me']");
    private By quit = By.xpath("//li[contains(@class,'context-menu-icon-quit')]");
    private By quitHover = By.xpath("//li[contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]/span[text()='Quit']");
    //TC005
    private By dragAble = By.id("draggable");
    private By target = By.id("droptarget");

    @BeforeTest
    public void beforeTest() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    private void waitForElDisplay(By by, int timeOut) {
        wait = new WebDriverWait(driver, timeOut);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception ex) {
            wait.until(ExpectedConditions.elementSelectionStateToBe(by, true));
        }
    }

    private void hoverToEl(By by) {
        builder = new Actions(driver);
        waitForElDisplay(by, 10);
        WebElement el = driver.findElement(by);
        Action hover = builder.moveToElement(el).build();
        hover.perform();
    }

    private void clickEl(By by) {
        wait = new WebDriverWait(driver, 10);
        try {
            WebElement el = driver.findElement(by);
            el.click();
        } catch (NoSuchElementException ex) {
            wait.until(ExpectedConditions.elementToBeClickable(by));
            WebElement el = driver.findElement(by);
            el.click();
        }
    }

    private void clickAndDrag(By firstBy, By lastBy) {
        builder = new Actions(driver);
        waitForElDisplay(firstBy, 10);
        WebElement firstEl = driver.findElement(firstBy);
        Action clickAndHold = builder.clickAndHold(firstEl).build();
        clickAndHold.perform();
        WebElement lastEl = driver.findElement(lastBy);
        Action drag = builder.moveToElement(lastEl).release().build();
        drag.perform();
    }

    private void doubleClick(By by) {
        builder = new Actions(driver);
        waitForElDisplay(by, 10);
        WebElement el = driver.findElement(by);
        Action doubleClick = builder.doubleClick(el).build();
        doubleClick.perform();
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException Ex) {
            return false;
        }
    }

    private void verifyTextAlert(String expected){
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

    private void rightClick(By by) {
        builder = new Actions(driver);
        waitForElDisplay(by, 10);
        WebElement el = driver.findElement(by);
        Action rightClick = builder.contextClick(el).build();
        rightClick.perform();
    }

    @Test
    private void TC001() {
        driver.get("http://www.myntra.com/");
        hoverToEl(profile);
        clickEl(signUp);
        waitForElDisplay(signUpForm, 10);
        WebElement form = driver.findElement(signUpForm);
        if (form.isDisplayed()) {
            Assert.assertTrue(true);
        } else {
            Assert.fail("Sign up form does not display");
        }
    }

    @Test
    private void TC002() {
        driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
        clickAndDrag(firstClick, lastClick);
        List<WebElement> els = driver.findElements(allClick);
        if (els.size() == 4) {
            Assert.assertTrue(true);
        } else {
            Assert.fail("fail!!!!");
        }
    }

    @Test
    private void TC003() {
        driver.get("http://www.seleniumlearn.com/double-click");
        doubleClick(doubleClick);
        String expected = "The Button was double-clicked.";
        verifyTextAlert(expected);
    }

    @Test
    private void TC004() {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        rightClick(rightClick);
        hoverToEl(quit);
        WebElement quit = driver.findElement(quitHover);
        if (quit.isDisplayed()) {
            Assert.assertTrue(true);
        } else {
            Assert.fail("Element does not display");
        }
    }

    @Test
    private void TC005(){
        driver.get("https://demos.telerik.com/kendo-ui/dragdrop/angular");
        clickAndDrag(dragAble, target);
        String actualStr = driver.findElement(target).getText().trim();
        Assert.assertEquals(actualStr, "You did great!");
    }
}
