package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_02_Locator {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void beforeTest() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://live.guru99.com/");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void TC001() {
        /**
         * Step 01 - Truy cập vào trang: http://live.guru99.com/ Step 02 - Click vào
         * link "My Account" để tới trang đăng nhập Step 03 - Để trống Username/
         * Password Step 04 - Click Login button Step 05 - Verify error message xuất
         * hiện tại 2 field: This is a required field.
         */
        WebElement myAccountBtn = driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"));
        myAccountBtn.click();

        WebElement submitBtn = driver.findElement(By.id("send2"));
        submitBtn.click();

        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("advice-required-entry-email")));

        String expected = "This is a required field.";

        String errMsgEmail = driver.findElement(By.id("advice-required-entry-email")).getText().trim();
        Assert.assertEquals(errMsgEmail, expected);

        String errMsgPass = driver.findElement(By.id("advice-required-entry-pass")).getText().trim();
        Assert.assertEquals(errMsgPass, expected);
    }

    @Test
    public void TC002() {
        /**
         * Step 01 - Truy cập vào trang: http://live.guru99.com/
         * Step 02 - Click vào link "My Account" để tới trang đăng nhập
         * Step 03 - Nhập email invalid: 123434234@12312.123123
         * Step 04 - Click Login button
         * Step 05 - Verify error message xuất hiện:  Please enter a valid email address. For example johndoe@domain.com.
         */
        WebElement myAccountBtn = driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"));
        myAccountBtn.click();

        WebElement email = driver.findElement(By.id("email"));
        email.clear();
        email.sendKeys("123434234@12312.123123");

        WebElement submitBtn = driver.findElement(By.id("send2"));
        submitBtn.click();

        String expected = "Please enter a valid email address. For example johndoe@domain.com.";

        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("advice-validate-email-email")));

        String errMsg = driver.findElement(By.id("advice-validate-email-email")).getText().trim();
        Assert.assertEquals(errMsg, expected);
    }

    @Test
    public void TC003() {
        /**
         * Test Script 03: Login with Password < 6 character
         * Step 01 - Truy cập vào trang: http://live.guru99.com/
         * Step 02 - Click vào link "My Account" để tới trang đăng nhập
         * Step 03 - Nhập email correct and password incorrect: automation@gmail.com/ 123
         * Step 04 - Click Login button
         * Step 05 - Verify error message xuất hiện: Please enter 6 or more characters without leading or trailing spaces.
         */
        WebElement myAccountBtn = driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"));
        myAccountBtn.click();

        WebElement email = driver.findElement(By.id("email"));
        email.clear();
        email.sendKeys("automation@gmail.com");

        WebElement pass = driver.findElement(By.id("pass"));
        pass.clear();
        pass.sendKeys("123");

        WebElement submitBtn = driver.findElement(By.id("send2"));
        submitBtn.click();

        String expected = "Please enter 6 or more characters without leading or trailing spaces.";

        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("advice-validate-password-pass")));

        String errMsg = driver.findElement(By.id("advice-validate-password-pass")).getText().trim();
        Assert.assertEquals(errMsg, expected);
    }

    @Test
    public void TC004() {
        /**
         * Step 01 - Truy cập vào trang: http://live.guru99.com/
         * Step 02 - Click vào link "My Account" để tới trang đăng nhập
         * Step 03 - Nhập email correct and password incorrect: automation@gmail.com/ 123123123
         * Step 04 - Click Login button
         * Step 05 - Verify error message xuất hiện: Invalid login or password.
         */
        WebElement myAccountBtn = driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"));
        myAccountBtn.click();

        WebElement email = driver.findElement(By.id("email"));
        email.clear();
        email.sendKeys("automation@gmail.com");

        WebElement pass = driver.findElement(By.id("pass"));
        pass.clear();
        pass.sendKeys("123123123");

        WebElement submitBtn = driver.findElement(By.id("send2"));
        submitBtn.click();

        String expected = "Invalid login or password.";

        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@class='error-msg']//span")));

        String errMsg = driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText().trim();
        Assert.assertEquals(errMsg, expected);
    }

    @Test
    public void TC005() {
        /**
         * Step 01 - Truy cập vào trang: http://live.guru99.com/
         * Step 02 - Click vào link "My Account" để tới trang đăng nhập
         * Step 03 - Click CREATE AN ACCOUNT button để tới trang đăng kí tài khoản
         * Step 04 - Nhập thông tin hợp lệ vào tất cả các field: First Name/ Last Name/ Email Address/ Password/ Confirm Password
         * (Lưu ý: Tạo random cho dữ liệu tại field Email Address)
         * Step 05 - Click REGISTER button
         * Step 06 - Verify message xuất hiện khi đăng kí thành công: Thank you for registering with Main Website Store.
         * Step 07 - Logout khỏi hệ thống
         * Step 08 - Kiểm tra hệ thống navigate về Home page sau khi logout thành công
         */
        WebElement myAccountBtn = driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"));
        myAccountBtn.click();

        WebElement createAccBtn = driver.findElement(By.xpath("//a[@title='Create an Account']"));
        createAccBtn.click();

        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("firstname")));

        WebElement firstName = driver.findElement(By.id("firstname"));
        firstName.clear();
        firstName.sendKeys("abc");

        WebElement middleName = driver.findElement(By.id("middlename"));
        middleName.clear();
        middleName.sendKeys("abc");

        WebElement lastName = driver.findElement(By.id("lastname"));
        lastName.clear();
        lastName.sendKeys("abc");

        Random rd = new Random();   // khai báo 1 đối tượng Random
        int number = rd.nextInt();  // trả về 1 số nguyên bất kỳ
        String mail = "auto" + String.valueOf(number) + "@gmail.com";
        WebElement email = driver.findElement(By.id("email_address"));
        email.clear();
        email.sendKeys(mail);

        WebElement password = driver.findElement(By.id("password"));
        password.clear();
        password.sendKeys("123456");

        WebElement confirmation = driver.findElement(By.id("confirmation"));
        confirmation.clear();
        confirmation.sendKeys("123456");

        WebElement registerBtn = driver.findElement(By.xpath("//div[@class='buttons-set']//button/span"));
        registerBtn.click();

        String expected = "Thank you for registering with Main Website Store.";

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@class='success-msg']//span")));

        String successMsg = driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText().trim();
        Assert.assertEquals(successMsg, expected);

        //logout
        WebElement accountBtn = driver.findElement(By.xpath("//a[contains(@class,'skip-account')]/span[2]"));
        accountBtn.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='Log Out']")));

        WebElement logOutBtn = driver.findElement(By.xpath("//a[@title='Log Out']"));
        logOutBtn.click();

        wait.until(ExpectedConditions.titleIs("Home page"));

        String tilePage = driver.getTitle();
        Assert.assertEquals(tilePage, "Home page");
    }
}
