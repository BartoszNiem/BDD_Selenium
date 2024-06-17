package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.BrowserDriver;

public class BasePage extends BrowserDriver {

    public static  String acceptCookiesXpath = "//div[@id='CybotCookiebotDialog']//a[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll']";

    public static void hoverOverElement(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
    }

    public static void scrollToElement(WebElement element){
        new Actions(driver).scrollToElement(element).perform();
    }

    public static void  acceptCookies() throws InterruptedException {
        driver.findElement(By.xpath(acceptCookiesXpath)).click();
        Thread.sleep(1000);
    }

    public static boolean isCookiesOnScreen(){
        return driver.findElement(By.xpath(acceptCookiesXpath)).isDisplayed();
    }
    public static void clickJs(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", element);
    }

}
