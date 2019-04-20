package TestNG;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNG_04_Parameter {

    @Test
    @Parameters({"sUsername", "sPassword"})
    public void test(String sUsername, String sPassword) {
        System.out.println("sUsername: " + sUsername);
        System.out.println("sPassword: " + sPassword);
    }

}
