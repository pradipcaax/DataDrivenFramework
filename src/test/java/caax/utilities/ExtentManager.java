package caax.utilities;

import caax.base.TestBase;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ExtentManager extends TestBase {

    private static ExtentReports extent;

    public static ExtentReports getInstance(String fileName) {

        if (extent == null)
            createInstance(fileName);

        return extent;
    }

    public static ExtentReports createInstance(String fileName) {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(fileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(fileName);

        extent = new ExtentReports();
        extent.config();
        extent.attachReporter(htmlReporter);

        extent.setSystemInfo("Automation Tester", "Yogi Pradip");
        extent.setSystemInfo("Organization", "Caax");
        extent.setSystemInfo("Win", "8 Pro Build");

        return extent;
    }

    public static String screenshotPath;
    public static String screenshotName;

    public static void captureScreenshot() {

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        Date d = new Date();
        screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
        screenshotPath=System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\"+screenshotName;
        try {
            FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\"+screenshotName));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}
