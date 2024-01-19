import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Utils {
    WebDriverWait wait;


    public Utils(AndroidDriver<MobileElement> driver) {
        wait = new WebDriverWait(driver, 30);
    }
    public WebElement find(By locator) {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

        } catch (TimeoutException e) {
            System.err.println("Timed out: " + locator);
        }
        return null;
    }


}
