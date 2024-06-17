package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.TemporalUnit;

public class BrowserDriver {

    public static WebDriver driver;

    public static WebDriverWait wait;

    public static ChromeOptions options;
    public BrowserDriver(){
        options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver.exe");
        this.driver = new ChromeDriver(options);
        driver.get("https://www.globallogic.com/pl");
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void close(){
        this.driver.close();
    }
}
