import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class RunnerTest {

    public static AndroidDriver<MobileElement> driver;
    private final URL url;
    private static Utils utils;
    public static WebDriverWait wait;
    static ExtentSparkReporter spark;
    static ExtentReports extent;
    static ExtentTest test;

    public RunnerTest() throws MalformedURLException {
        url = new URL("http://127.0.0.1:4723/wd/hub");
    }



    public DesiredCapabilities getCapabilities() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        caps.setCapability(MobileCapabilityType.APP, "//Users//juanmi//app.apk");
        return caps;
    }

    public static String capture(AppiumDriver<MobileElement> driver) throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File Dest = new File("src/../BStackImages/" + System.currentTimeMillis() + ".png");
        String errflpath = Dest.getAbsolutePath();
        FileUtils.copyFile(scrFile, Dest);
        return errflpath;
    }
    public static void fail() throws IOException {
        test.log(Status.FAIL, (Markup) test.addScreenCaptureFromPath(capture(driver)));
    }


    @BeforeAll
    public void init() {
        try {
            driver = new AndroidDriver<>(url, getCapabilities());
            utils=new Utils(driver);
            wait = new WebDriverWait(driver, 30);
            spark=new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/STMExtentReport.html");
            extent=new ExtentReports();
            extent.attachReporter(spark);

        } catch (Exception e) {
                e.getMessage();
        }
    }

    public  WebElement find(By locator) {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

        } catch (TimeoutException e) {
            System.err.println("Timed out: " + locator);
        }
        return null;
    }
    @AfterEach
    public void close(){
       // driver.close();
        extent.flush();
    }

    public static AndroidDriver<MobileElement> getDriver() {
        return driver;
    }

    public static WebDriverWait getWait() {
        return wait;
    }

    public static Utils getUtils() {
        return utils;
    }

}
