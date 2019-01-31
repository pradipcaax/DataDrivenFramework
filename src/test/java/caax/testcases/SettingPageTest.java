package caax.testcases;

import caax.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SettingPageTest extends TestBase {

    @Test(dataProvider="getData")
    public void data_fill(String fname,String lname,String job ){

        WebElement f_name=driver.findElement(By.id("MainContent_txtfname"));
        f_name.clear();
        f_name.sendKeys(fname);

        WebElement l_name=driver.findElement(By.id("MainContent_txtlname"));
        l_name.clear();
        l_name.sendKeys(lname);

        WebElement job_title=driver.findElement(By.id("MainContent_txtJobTitle"));
        job_title.clear();
        job_title.sendKeys(job);
        Reporter.log("Login Passed");


    }

    @DataProvider
    public Object[][] getData(){

        //String sheetName = m.getName();
        String sheetName="one";
        int rows = reader.getRowCount(sheetName);
        System.out.println("Total rows is:"+rows);
        int cols = reader.getColumnCount(sheetName);
        System.out.println("Total collums is :"+cols);

        Object[][] data = new Object[rows-1][cols];

        for (int rowNum = 2; rowNum <= rows; rowNum++)
        {
            for (int colNum = 0; colNum < cols; colNum++)
            {
                //data[0][0] first data store in 0,0
                //we take rowNum=2 so its like 2,0 and its invalid to store data
                //so rowNum-2 :-> 2-2=0 so store in 0,0
                data[rowNum-2][colNum]=reader.getCellData(sheetName, colNum, rowNum);
            }
        }
        return data;


    }
}
