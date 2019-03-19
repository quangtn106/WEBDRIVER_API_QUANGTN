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

import java.util.List;
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
    //Verify
    private By customerID = By.xpath(".//td[text()='Customer ID']/following-sibling::td");
    private By customerName = By.xpath(".//td[text()='Customer Name']/following-sibling::td");
    private By gender = By.xpath(".//td[text()='Gender']/following-sibling::td");
    private By birth = By.xpath(".//td[text()='Birthdate']/following-sibling::td");
    private By cusAddress = By.xpath(".//td[text()='Address']/following-sibling::td");
    private By customerCity = By.xpath(".//td[text()='City']/following-sibling::td");
    private By customerState = By.xpath(".//td[text()='State']/following-sibling::td");
    private By customerPin = By.xpath(".//td[text()='Pin']/following-sibling::td");
    private By customerMobile = By.xpath(".//td[text()='Mobile No.']/following-sibling::td");
    private By customerEmail = By.xpath(".//td[text()='Email']/following-sibling::td");
    //Edit
    private By editBtn = By.xpath(".//a[text()='Edit Customer']");
    private By inputCusID = By.xpath(".//input[@name='cusid']");
    private By accSubmit = By.xpath(".//input[@name='AccSubmit']");

    //Dropdown
    private By numberDropDown = By.id("number-button");
    private By dropDownOpen = By.xpath(".//div[@class='ui-selectmenu-menu ui-front ui-selectmenu-open']");
    private By verifyDropDown = By.xpath("//span[@id='number-button']/span[2]");

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
    private String cusID = "";
    private String editAdd = "123 address Edit";
    private String editCity = "ho chi minh Edit";
    private String editState = "tan binh Edit";
    private String editMobile = "0999999999";


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

    public void verifyPage(String expectedTitle) {
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleIs(expectedTitle));
        String actualTitle = driver.getTitle();
        if (actualTitle.equalsIgnoreCase(expectedTitle)) {
            Assert.assertTrue(true);
        } else {
            Assert.fail("Page is wrong, pls check: " + actualTitle);
        }
    }

    public void waitForElDisplay(By by, int timeOut) {
        wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public String generateRanEmail() {
        String random;
        Random rd = new Random();   // khai báo 1 đối tượng Random
        int number = rd.nextInt();  // trả về 1 số nguyên bất kỳ
        random = "auto" + String.valueOf(number) + "@gmail.com";
        return random;
    }

    public void verifyText(By by, String value) {
        waitForElDisplay(by, 10);
        String actual = driver.findElement(by).getText().trim();
        if (actual == null || "".equalsIgnoreCase(actual)) {
            Assert.fail("String is null, pls re-check: " + "\"" + actual + "\"");
        } else {
            if (actual.equalsIgnoreCase(value)) {
                Assert.assertTrue(true);
            } else {
                Assert.fail("String is wrong, pls re-check: " + "\"" + actual + "\"" + " \"" + "value: " + value + "\"");
            }
        }
    }

    public String getTextEl(By by){
        waitForElDisplay(by, 10);
        String text = driver.findElement(by).getText().trim();
        return text;
    }

    @Test
    public void TC001() {
        driver.get("http://demo.guru99.com/v4/");
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
        //verify
        verifyText(customerName, name);
        verifyText(gender, "male");
        verifyText(birth, "1991-06-10");
        verifyText(cusAddress, add);
        verifyText(customerCity, city);
        verifyText(customerState, state);
        verifyText(customerPin, pin);
        verifyText(customerMobile, mobile);
        verifyText(customerEmail, mail);
        cusID = getTextEl(customerID);
        //edit
        clickEl(editBtn);
        sendKeyEl(inputCusID, cusID);
        clickEl(accSubmit);
        sendKeyEl(address, editAdd);
        sendKeyEl(cityLocator, editCity);
        sendKeyEl(stateLocator, editState);
        sendKeyEl(mobileNumLocator, editMobile);
        clickEl(submitBtn);
        //verify edit
        verifyText(cusAddress, editAdd);
        verifyText(customerCity, editCity);
        verifyText(customerState, editState);
        verifyText(customerMobile, editMobile);
    }

    public void selectDropDown(By by, String value) {
        waitForElDisplay(by, 10);
        List<WebElement> options = driver.findElement(by).findElements(By.xpath("//li"));
        for (WebElement option : options) {
            String actual = option.getText().trim();
            if (actual.equalsIgnoreCase(value)) {
                option.click();
            }
        }
    }

    @Test
    public void TC002() {
        driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
        clickEl(numberDropDown);
        selectDropDown(dropDownOpen, "19");
        verifyText(verifyDropDown, "19");
    }
}
