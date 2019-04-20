package selenium_api;

import Support.SupportUtil;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic07 {
    private WebDriver driver;
    //    private SupportUtil sp = new SupportUtil(driver);
    private WebDriverWait wait;
    private Actions builder;
    private Alert alert;

    //TC02
    private By clickHere = By.xpath("//a[text()='Click Here']");

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

    @Test
    public void TC02() {
        driver.get("https://daominhdam.github.io/basic-form/index.html");
        clickEl(clickHere);
//        ArrayList<String> windowHandles = new ArrayList<String>(driver.getWindowHandles());
//        for (int i = 0; i < windowHandles.size(); i++) {
//
//        }
        String MainWindow = driver.getWindowHandle();
        // To handle all new opened window.
        Set<String> s1 = driver.getWindowHandles();
        Iterator<String> i1 = s1.iterator();
        while (i1.hasNext()) {
            String ChildWindow = i1.next();
            if (!MainWindow.equalsIgnoreCase(ChildWindow)) {
                // Switching to Child window
                driver.switchTo().window(ChildWindow);
                String title = driver.getTitle();
                if (title.equalsIgnoreCase("google")){
                    Assert.assertTrue(true);
                } else {
                    Assert.fail("check again");
                }
                // Closing the Child Window.
                driver.close();
            }
        }
        // Switching to Parent window i.e Main Window.
        driver.switchTo().window(MainWindow);
        String title = driver.getTitle();
        if (title.equalsIgnoreCase("SELENIUM WEBDRIVER FORM DEMO")){
            Assert.assertTrue(true);
        } else {
            Assert.fail("check again");
        }
    }
}
