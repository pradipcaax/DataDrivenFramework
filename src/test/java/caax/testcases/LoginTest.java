package caax.testcases;

import caax.base.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @Test
    public void login(){


         log.debug("Inside login test");

         type("userId","pradip.caax@gmail.com");

         Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("password"))));

         type("password","pradip123");
         click("login_btn");
         log.debug("login test done");

         driver.navigate().to("https://www.caax.co.uk/demo/sxclient/setting_personalinfo.aspx");
         Reporter.log("Login Successfull");

         Assert.fail("Uncommon failed");

    }

}
