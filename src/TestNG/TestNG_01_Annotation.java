package TestNG;

import org.testng.annotations.*;

public class TestNG_01_Annotation {
    @BeforeTest
    private void beforeTest() {
        System.out.println("beforeTest");
    }

    @AfterTest
    private void afterTest() {
        System.out.println("AfterTest");
    }

    @BeforeClass
    private void beforeClass() {
        System.out.println("BeforeClass");
    }

    @AfterClass
    private void afterClass() {
        System.out.println("AfterClass");
    }

    @BeforeSuite
    private void beforeSuite() {
        System.out.println("BeforeSuite");
    }

    @AfterSuite
    private void afterSuite() {
        System.out.println("AfterSuite");
    }

    @BeforeMethod
    private void beforeMethod() {
        System.out.println("BeforeMethod");
    }

    @AfterMethod
    private void afterMethod() {
        System.out.println("AfterMethod");
    }

    @Test
    private void test01() {
        System.out.println("Test");
    }

    @Test
    private void test02() {
        System.out.println("Test02");
    }
}
