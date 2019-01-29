package caax.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver;
    public static Properties config=new Properties();
    public static Properties OR=new Properties();
    public static FileInputStream fis;

    @BeforeSuite
    public void setUp(){

        if(driver==null)
        {

            try {
                fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\properties\\Config.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                config.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\properties\\OR.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                OR.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(config.getProperty("browser").equals("chroma"))
            {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }else if (config.getProperty("browser").equals("firefox"))
            {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            driver.get(config.getProperty("testSiteUrl"));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("impWait")), TimeUnit.SECONDS);
    //here
        }



    }

    @AfterSuite
    public void tearDown(){
        if (driver != null)
        {
            driver.close();
        }

    }

}
