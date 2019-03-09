package selenium_api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic03_WebElement {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void beforeTest() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://daominhdam.github.io/basic-form/index.html");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void TC001() {
        /**
         * Test Script 01: Kiểm tra phần tử hiển thị trên trang
         * Step 01 - Truy cập vào trang: https://daominhdam.github.io/basic-form/index.html
         * Step 02 - Kiểm tra các phần tử sau hiển thị trên trang: Email/ Age (Under 18)/ Education
         * Step 03 - Nếu có nhập giá trị: Automation Testing vào 2 field Email/ Education và chọn Age = Under 18
         */
        WebElement emailLabel = driver.findElement(By.xpath("//label[contains(text(),'Email:')]"));
        if (emailLabel.isDisplayed()) {
            WebElement email = driver.findElement(By.id("mail"));
            email.clear();
            email.sendKeys("Automation Testing");
        } else {
            Assert.fail("email does not exist");
        }

        WebElement ageLabel = driver.findElement(By.xpath("//label[contains(text(),'Under 18')]"));
        if (ageLabel.isDisplayed()) {
            WebElement under18 = driver.findElement(By.id("under_18"));
            under18.click();
        } else {
            Assert.fail("age under18 does not exist");
        }

        WebElement eduLabel = driver.findElement(By.xpath("//label[contains(text(),'Education:')]"));
        if (eduLabel.isDisplayed()) {
            WebElement edu = driver.findElement(By.id("edu"));
            edu.clear();
            edu.sendKeys("Automation Testing");
        }
    }

    @Test
    public void TC002(){
        /**
         * Test Script 02: Kiểm tra phần tử enable/ disable trên trang
         * Step 01 - Truy cập vào trang: https://daominhdam.github.io/basic-form/index.html
         * Step 02 - Kiểm tra các phần tử sau enable trên trang: Email/ Age (Under 18)/ Education/ Job Role 01/ Interests (Development)/ Slider 01/ Button is enabled
         * Step 03 - Kiểm tra các phần tử sau disable trên trang: Password / Age (Radiobutton is disabled)/ Biography/ Job Role 02/ Interests (Checkbox is disabled)/ Slider 02/ Button is disabled
         * Step 04 - Nếu có in ra Element is enabled/ ngược lại Element is disabled
         */
        WebElement email = driver.findElement(By.id("mail"));
        if (email.isEnabled()){
            System.out.println("email is enabled");
        } else {
            System.err.println("email is not enabled");
        }

        WebElement edu = driver.findElement(By.id("edu"));
        if (edu.isEnabled()){
            System.out.println("edu is enabled");
        } else {
            System.err.println("edu is not enabled");
        }

        WebElement under18 = driver.findElement(By.id("under_18"));
        if (under18.isEnabled()){
            System.out.println("under18 is enabled");
        } else {
            System.err.println("under18 is not enabled");
        }

        WebElement job1 = driver.findElement(By.id("job1"));
        if (job1.isEnabled()){
            System.out.println("job1 is enabled");
        } else {
            System.err.println("job1 is not enabled");
        }

        WebElement development = driver.findElement(By.id("development"));
        if (development.isEnabled()){
            System.out.println("development is enabled");
        } else {
            System.err.println("development is not enabled");
        }

        WebElement slider1 = driver.findElement(By.id("slider-1"));
        if (slider1.isEnabled()){
            System.out.println("slider1 is enabled");
        } else {
            System.err.println("slider1 is not enabled");
        }

        WebElement button_enabled = driver.findElement(By.id("button-enabled"));
        if (button_enabled.isEnabled()){
            System.out.println("button_enabled is enabled");
        } else {
            System.err.println("button_enabled is not enabled");
        }

        WebElement password = driver.findElement(By.id("password"));
        if (!password.isEnabled()){
            System.out.println("password is not enabled");
        } else {
            System.err.println("password is enabled");
        }

        WebElement radio_disabled = driver.findElement(By.id("radio-disabled"));
        if (!radio_disabled.isEnabled()){
            System.out.println("radio_disabled is not enabled");
        } else {
            System.err.println("radio_disabled is enabled");
        }

        WebElement bio = driver.findElement(By.id("bio"));
        if (!bio.isEnabled()){
            System.out.println("bio is not enabled");
        } else {
            System.err.println("bio is enabled");
        }

        WebElement job2 = driver.findElement(By.id("job2"));
        if (!job2.isEnabled()){
            System.out.println("job2 is not enabled");
        } else {
            System.err.println("job2 is enabled");
        }

        WebElement check_disbaled = driver.findElement(By.id("check-disbaled"));
        if (!check_disbaled.isEnabled()){
            System.out.println("check_disbaled is not enabled");
        } else {
            System.err.println("check_disbaled is enabled");
        }

        WebElement slider2 = driver.findElement(By.id("slider-2"));
        if (!slider2.isEnabled()){
            System.out.println("slider2 is not enabled");
        } else {
            System.err.println("slider2 is enabled");
        }

        WebElement button_disabled = driver.findElement(By.id("button-disabled"));
        if (!button_disabled.isEnabled()){
            System.out.println("button_disabled is not enabled");
        } else {
            System.err.println("button_disabled is enabled");
        }
    }

    @Test
    public void TC003(){
        /**
         * Test Script 03: Kiểm tra phần tử được chọn trên trang
         * Step 01 - Truy cập vào trang: https://daominhdam.github.io/basic-form/index.html
         * Step 02 - Click chọn Age (Under 18)/ Interests (Development)
         * Step 03 - Kiểm tra các phần tử tại Step 02 đã được chọn
         * Step 04 - Nếu chưa được chọn thì cho phép chọn lại 1 lần nữa
         */
        WebElement under18 = driver.findElement(By.id("under_18"));
        under18.click();
        if (under18.isSelected()){
            System.out.println("element is selected");
        } else {
            under18.click();
        }

        WebElement development = driver.findElement(By.id("development"));
        development.click();
        if (development.isSelected()){
            System.out.println("element is selected");
        } else {
            development.click();
        }
    }
}
