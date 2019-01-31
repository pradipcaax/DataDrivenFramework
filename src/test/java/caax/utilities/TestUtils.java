package caax.utilities;

import caax.base.TestBase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class TestUtils extends TestBase {

    public static String screenshotPath;
    public static String screenshotName;

    public static void CaptureScreenshot(){

        File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Date d=new Date();
        screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
        try {
            FileUtils.copyFile(srcFile,new File(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\"+screenshotName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
