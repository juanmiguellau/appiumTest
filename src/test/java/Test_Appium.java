import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.By.id;

    public class Test_Appium  {

    public AndroidDriver<MobileElement> driver;
   // private final Utils utils= RunnerTest.getUtils();
    public  RunnerTest runner;
    ExtentTest test;
    ExtentReports extent;



        @BeforeEach
    public void setup() throws MalformedURLException {
       runner=new RunnerTest();
       runner.init();
       driver= RunnerTest.getDriver();
    }
    By username=id ("com.ziviello.AutomationTest:id/username");
    By password =id("com.ziviello.AutomationTest:id/password");
    By btnLogin=id("com.ziviello.AutomationTest:id/btnLogin");
    By welcomeText=id("com.ziviello.AutomationTest:id/txtWelcome");


        @Test
        public void basicTest() throws IOException {

            // test = extent.createTest("check login access");
        try {

            //test.info("login access");
            runner.find(username).sendKeys("admin");
            runner.find(password).sendKeys("admin");
            runner.find(btnLogin).click();
            WebElement text = runner.find(welcomeText);
            Assertions.assertEquals("benvenuto: admin", text.getText());

        }catch (Exception ex) {
            Assertions.fail("test not completed");
            RunnerTest.fail();
        }

    }



    @AfterAll
    public static void teardown() {
       // runner.close();
    }
}
