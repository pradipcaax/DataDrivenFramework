package caax.testcases;

import caax.base.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @Test
    public void login(){

         System.setProperty("org.uncommons.reportng.escape-output","false");
         log.debug("Inside login test");
         driver.findElement(By.xpath(OR.getProperty("userId"))).sendKeys("pradip.caax@gmail.com");

         Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("password"))));

         driver.findElement(By.xpath(OR.getProperty("password"))).sendKeys("pradip123");
         driver.findElement(By.xpath(OR.getProperty("login_btn"))).click();
         log.debug("login test done");
         driver.navigate().to("https://www.caax.co.uk/demo/sxclient/setting_personalinfo.aspx");
         Reporter.log("Login Successfull");
         Reporter.log("<a target=\"_blank\" href=\"E:\\shiv.jpg\">Screenshot</a>");

    }

}
