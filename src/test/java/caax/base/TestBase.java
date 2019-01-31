package caax.base;

import caax.utilities.ExcelReader;
import caax.utilities.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver;
    public static Properties config=new Properties();
    public static Properties OR=new Properties();
    public static FileInputStream fis;
    public static Logger log= LogManager.getLogger();
    public static ExcelReader reader=new ExcelReader(System.getProperty("user.dir")+"\\src\\main\\resources\\excel\\data.xlsx");
    public static WebDriverWait wait;
    static Date d = new Date();
    static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
    public static ExtentReports extent = ExtentManager.createInstance(System.getProperty("user.dir")+"\\reports\\"+fileName);
    public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();



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
                log.debug("Config file loaded");
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
                log.debug("OR file loaded");
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(config.getProperty("browser").equals("chroma"))
            {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                log.debug("Chroma launched");
            }else if (config.getProperty("browser").equals("firefox"))
            {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            driver.get(config.getProperty("testSiteUrl"));
            log.debug("Navigate to :"+config.getProperty("testSiteUrl"));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("impWait")), TimeUnit.SECONDS);
            wait=new WebDriverWait(driver,5);
        }



    }

    public boolean isElementPresent(By by)
    {
        try{
            driver.findElement(by);
            return true;

        }catch (NoSuchElementException e){
            return false;
        }
    }

    public void type(String locatore,String value)
    {
        driver.findElement(By.xpath(OR.getProperty(locatore))).sendKeys(value);
        Markup m= MarkupHelper.createLabel("Type in:"+locatore, ExtentColor.BLUE);
        testReport.get().info(m);
    }

    public void click(String locatore){

        driver.findElement(By.xpath(OR.getProperty(locatore))).click();
        Markup m= MarkupHelper.createLabel("Click on:"+locatore, ExtentColor.BLUE);
        testReport.get().info(m);
    }


    @AfterSuite
    public void tearDown(){
        if (driver != null)
        {
            driver.close();
        }

    }

}
