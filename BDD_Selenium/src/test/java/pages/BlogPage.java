package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class BlogPage extends BasePage{

    public static  String blogBreadcrumbsLinkXpath = "//section[@class='breadcrumbs']//li//a[text()='insights']";
    public static  String blogsContainerXpath = "//div[@id='filter_data']";
    public static  String blogsContainerValuesXpath = "//div[@id='filter_data']//div[@class='grid']";
    public static  String filterButtonXpath = "//span[@class='multiselect-native-select']/select[@id='%s']/following-sibling::div//button";
    public static  String filterValueSelectorXpath = "//ul[@class='multiselect-container dropdown-menu show']//input[@value='%s']";
    public static  String currentSelectedFilterValuesXpath = "//div[@class='selectedblockContainer container']/div/div";
    public static  String clearFiltersButtonXpath = "//button[@id='deselect']";
    public static  String categoryFilterValueXpath = "//button[@title='Category']//span";
    public static  String industryFilterValueXpath = "//button[@title='industry']//span";
    public static boolean isOnBlogPage() {
        return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(blogBreadcrumbsLinkXpath)))).isDisplayed();
    }

    public static boolean areBlogsAppearing() {
         if(driver.findElement(By.xpath(blogsContainerXpath)).isDisplayed()){
             return !driver.findElements(By.xpath(blogsContainerValuesXpath)).isEmpty();
         }
         return false;
    }
    public static void selectFilterValues(String filterTitle, String value1, String value2, String value3) {
        WebElement filterButton = null;
        if(filterTitle.equalsIgnoreCase("category") || filterTitle.equalsIgnoreCase("industry")){
            String formattedXpath = String.format(filterButtonXpath, filterTitle.toLowerCase());
            filterButton = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(formattedXpath))));
            try{
                filterButton.click();
            }
            catch(ElementClickInterceptedException e){
                clickJs(filterButton);
            }
        }
        else {
            throw new RuntimeException("No filter with given name");
        }
        List<String> valuesToSelect = List.of(value1,value2,value3);
        for (String value: valuesToSelect) {
            String valueToSelectXpath = String.format(filterValueSelectorXpath, value);
           // wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(valueToSelectXpath)))).click();
          //  driver.findElement(By.xpath(valueToSelectXpath)).click();
            clickJs(driver.findElement(By.xpath(valueToSelectXpath)));
        }
        filterButton.click();

    }


    public static List<String> getSelectedFilterValues() {
        return wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath(currentSelectedFilterValuesXpath))))
                .stream().map(WebElement::getText)
                .toList();
    }

    public static void clickOnClearFiltersButton() {
        driver.findElement(By.xpath(clearFiltersButtonXpath)).click();
    }

    public static String getCategoryFilterCurrentValue() {
        return driver.findElement(By.xpath(categoryFilterValueXpath)).getText();
    }

    public static String getIndustryFilterCurrentValue() {
        return driver.findElement(By.xpath(industryFilterValueXpath)).getText();
    }
}
