package selenium_api;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class test {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    //TC001
    private By myAccount = By.xpath(".//div[@class='footer']//a[@title='My Account']");
    private By createAccount = By.xpath(".//span[text()='Create an Account']");
    //TC002
    private By ckBox1 = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
    //TC003
    private By rad1 = By.xpath("//input[@id='engine3']");


    @BeforeTest
    private void beforeTest() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterTest
    private void tearDown() {
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

    private void waitForElDisplay(WebElement el, int timeOut) {
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

    private void clickElByJS(WebElement el) {
        js = (JavascriptExecutor) driver;
//        waitForElDisplay(el, 10);
        js.executeScript("arguments[0].click();", el);
    }

    private void checkCustomCkBox(WebElement el) {
        if (!el.isSelected()) {
            clickElByJS(el);
        }
    }

    private void unCheckCustomCkBox(WebElement el) {
        if (el.isSelected()) {
            clickElByJS(el);
        }
    }

    @Test
    private void TC001() {
        driver.get("http://live.guru99.com/");
        clickElByJS(myAccount);
        String currentUrl = driver.getCurrentUrl().trim();
        if (currentUrl.equalsIgnoreCase("http://live.guru99.com/index.php/customer/account/login/")) {
            Assert.assertTrue(true);
        } else {
            Assert.fail("currentUrl is wrong, pls re-check: " + currentUrl);
        }
        clickElByJS(createAccount);
        currentUrl = driver.getCurrentUrl().trim();
        if (currentUrl.equalsIgnoreCase("http://live.guru99.com/index.php/customer/account/create/")) {
            Assert.assertTrue(true);
        } else {
            Assert.fail("currentUrl is wrong, pls re-check: " + currentUrl);
        }
    }
}
