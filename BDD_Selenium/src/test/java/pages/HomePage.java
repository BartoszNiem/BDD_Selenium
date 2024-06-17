package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.BrowserDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {

    public static  String contactButtonXpath = "//div[@class=\"menu-latest-menu-container\"]//a[text() = \"Kontakt\"]";
    public static  String blogButtonXpath = "//div[@class=\"menu-latest-menu-container\"]//a[text() = \"BLOG\"]";
    public static  String homeButtonXpath = "//a[@class = 'navbar-brand logo']/img[@class ='custom-logo svg-logo-desktop']";
    public static  String regionsButtonXpath = "//li[@class = 'nav-item dropdown loactionmenu']/a";
    public static  String regionsOptionsLanguageXpath = "//div[@class = 'dropdown-menu nav-country-selector-options country-dropdown']/div/a";
    public static  String footerXpath = "//footer";
    public static  String socialLinksXpath = "//div[@class='social-icons']/a";
    public static  String redirectFooterLinksXpath = "//ul[@id='menu-footer-menu']/li/a | //ul[@id='menu-footer-menu-two']/li/a";
    public static  String footerCopyrightTagXpath = "//div[@class='copy-right only-desktop']/p";
    public static  String goToTopButtonXpath = "//button[@id='gotoTopBtn']";
    public static  String carouselSlideXpath = "//div[@class ='carousel slide']";
    public static  String navbarHeaderXpath = "//ul[@id = 'primary-menu']//a[text()='%s']";
    public static  String navbarButtonsXpath = "//ul[@id = 'primary-menu']//a[text()='%s']//..//ul//li/a";

    public static boolean isHomeButtonPresent() throws InterruptedException {
        Thread.sleep(2000);
        return driver.findElement(By.xpath(homeButtonXpath)).isDisplayed();
    }

    public static void clickOnRegionsButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(regionsButtonXpath))).click();

    }


    public static List<String> getListOfRegionsAndLanguages() {
        List<WebElement> languages = wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath(regionsOptionsLanguageXpath))));
        return languages.stream().map(el -> el.getText().trim()).toList();
    }

    public static boolean isFooterPresent() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(footerXpath))).isDisplayed();
    }

    public static boolean isOnTopOfHomePage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(carouselSlideXpath))).isDisplayed();
    }

    public static void scrollToBottomOfPage() {
        WebElement footer = driver.findElement(By.xpath(footerXpath));
        scrollToElement(footer);
    }

    public static List<String> getSocialLinks() {
        return driver.findElements(By.xpath(socialLinksXpath)).stream().map(el-> el.getAttribute("href")).toList();
    }

    public static List<String> getRedirectFooterLinks() {
        return driver.findElements(By.xpath(redirectFooterLinksXpath)).stream().map(WebElement::getText).toList();
    }

    public static String getFooterCopyrightTag() {
        return driver.findElement(By.xpath(footerCopyrightTagXpath)).getText();
    }

    public static void clickGoToTopButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(goToTopButtonXpath))).click();
    }

    public static List<WebElement> getButtonsFromNavbarAfterHover(String navbarHeader, List<String> expectedHeaderButtonNames) {
        String navbarHeaderXpathFormatted = String.format(navbarHeaderXpath, navbarHeader);
        hoverOverElement(driver.findElement(By.xpath(navbarHeaderXpathFormatted)));
        List<WebElement> buttonsToReturn = new ArrayList<>();
        for (String expectedName: expectedHeaderButtonNames) {
            String navbarHoveredHeaderXpathFormatted = String.format(navbarButtonsXpath, expectedName);
            List<WebElement> buttons = driver.findElements(By.xpath(navbarHoveredHeaderXpathFormatted));
            buttonsToReturn.addAll(buttons);
        }
        return buttonsToReturn;
    }


    public static void clickOnBlogButton() {
        driver.findElement(By.xpath(blogButtonXpath)).click();
    }
}
