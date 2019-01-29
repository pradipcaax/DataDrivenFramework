package caax.testcases;

import caax.base.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @Test
    public void login(){

         driver.findElement(By.xpath(OR.getProperty("userId"))).sendKeys("pradip.caax@gmail.com");
         driver.findElement(By.xpath(OR.getProperty("password"))).sendKeys("pradip123");
         driver.findElement(By.xpath(OR.getProperty("login_btn"))).click();
         //pradip



    }
}
