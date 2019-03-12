package selenium_api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic04_EXERCISE {
    private WebDriver driver;
    private WebDriverWait wait;

    //Login
    private By userID = By.xpath(".//input[@name='uid']");
    private By password = By.xpath(".//input[@name='password']");
    private By loginBtn = By.xpath(".//input[@name='btnLogin']");
    //Add new customer
    private By newCustomerBtn = By.xpath(".//a[text()='New Customer']");
    private By cusName = By.xpath(".//input[@name='name']");
    private By maleRad = By.xpath("//input[@name='rad1' and @value='m']");
    private By dob = By.id("dob");
    private By address = By.xpath(".//textarea[@name='addr']");
    private By cityLocator = By.xpath(".//input[@name='city']");
    private By stateLocator = By.xpath(".//input[@name='state']");
    private By pinLocator = By.xpath(".//input[@name='pinno']");
    private By mobileNumLocator = By.xpath(".//input[@name='telephoneno']");
    private By emailLocator = By.xpath(".//input[@name='emailid']");
    private By submitBtn = By.xpath(".//input[@name='sub']");

    //Login
    private String user = "mngr181358";
    private String pass = "berydUp";
    private String titlePage = "Guru99 Bank Manager HomePage";
    private String name = "Sele Online";
    private String passNewCus = "123456789";
    private String birthday = "06/10/1991";
    private String add = "123 address";
    private String city = "ho chi minh";
    private String state = "tan binh";
    private String pin = "123456";
    private String mobile = "0123456789";
    private String mail = generateRanEmail();


    @BeforeTest
    public void beforeTest() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://demo.guru99.com/v4/");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    public void clickEl(By by) {
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

    public void sendKeyEl(By by, String value) {
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        WebElement el = driver.findElement(by);
        el.clear();
        el.sendKeys(value);
    }

    public void verifyPage(String expectedTitle){
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleIs(expectedTitle));
        String actualTitle = driver.getTitle();
        if (actualTitle.equalsIgnoreCase(expectedTitle)) {
            Assert.assertTrue(true);
        } else {
            Assert.fail("Page is wrong, pls check: " + actualTitle);
        }
    }

    public String generateRanEmail(){
        String random;
        Random rd = new Random();   // khai báo 1 đối tượng Random
        int number = rd.nextInt();  // trả về 1 số nguyên bất kỳ
        random = "auto" + String.valueOf(number) + "@gmail.com";
        return random;
    }

    @Test
    public void TC001() {
        //Login and verify home page
        sendKeyEl(userID, user);
        sendKeyEl(password, pass);
        clickEl(loginBtn);
        verifyPage(titlePage);
        //Create new customer
        clickEl(newCustomerBtn);
        sendKeyEl(cusName, name);
        clickEl(maleRad);
        sendKeyEl(dob, birthday);
        sendKeyEl(address, add);
        sendKeyEl(cityLocator, city);
        sendKeyEl(stateLocator, state);
        sendKeyEl(pinLocator, pin);
        sendKeyEl(mobileNumLocator, mobile);
        sendKeyEl(emailLocator, mail);
        sendKeyEl(password, passNewCus);
        clickEl(submitBtn);

    }
}
