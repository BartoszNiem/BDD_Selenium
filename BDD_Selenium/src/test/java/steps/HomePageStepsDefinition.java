package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.HomePage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static pages.HomePage.*;

public class HomePageStepsDefinition {


    @Given("User opens HomePage")
    public void user_opens_home_page() throws InterruptedException {
        assertThat(HomePage.isHomeButtonPresent()).isTrue();
        if(isCookiesOnScreen()){
            acceptCookies();
        }
    }

    @When("User clicks on language selector")
    public void userClicksOnLanguageSelector() {
        HomePage.clickOnRegionsButton();
    }

    @Then("User can see all the available regions and languages")
    public void userCanSeeAllTheAvailableRegions(List<String> expectedRegionsAndLanguages) {
        List<String> actualRegionsAndLanguages = HomePage.getListOfRegionsAndLanguages();
        assertThat(actualRegionsAndLanguages).containsExactlyElementsOf(expectedRegionsAndLanguages);
    }

    @When("User scrolls to the bottom of the page")
    public void userScrollsToTheBottomOfThePage() {
        HomePage.scrollToBottomOfPage();
    }

    @Then("User can see the footer of the page")
    public void userCanSeeTheFooterOfThePage() {
        assertThat(HomePage.isFooterPresent()).isTrue();
    }

    @And("User verifies social links are present")
    public void userVerifiesSocialLinksArePresent(List<String> expectedSocialLinks) {
        List<String> actualSocialLinks = HomePage.getSocialLinks();
        for (int i = 0; i < actualSocialLinks.size(); i++) {
            assertThat(actualSocialLinks.get(i)).contains(expectedSocialLinks.get(i).toLowerCase());
        }
    }

    @And("User verifies redirection links are present")
    public void userVerifiesRedirectionLinksArePresent(List<String> expectedLinks) {
        List<String> actualLinks = HomePage.getRedirectFooterLinks();
        assertThat(actualLinks).containsExactlyElementsOf(expectedLinks);
    }

    @And("User verifies copyright tag: {string}")
    public void userVerifiesCopyrightTag(String tag) {
        String actualCopyrightTag = HomePage.getFooterCopyrightTag();
        assertThat(actualCopyrightTag).isEqualTo(tag);
    }

    @When("User clicks on {string} button")
    public void userClicksOnButton(String buttonName) {
        if(buttonName.equals("Go to top")){
            HomePage.clickGoToTopButton();
        }
    }

    @Then("User is on top of the Home Page")
    public void userIsOnTopOfTheHomePage() {
        assertThat(HomePage.isOnTopOfHomePage()).isTrue();
    }

    @Then("User hovers over {string} on navbar and checks if hovered navbar buttons from {string} and their buttons from file {string} are enabled")
    public void userHoversOverAndChecksIfButtonsFromFileAreEnabled(String navbarHeader, String hoveredNavbarHeader, String fileName) throws IOException {
        //get data from files
        final String filePathHeaderButtons = System.getProperty("user.dir") + "/src/test/resources/files/" + hoveredNavbarHeader;
        final String filePathButtons = System.getProperty("user.dir") + "/src/test/resources/files/" + fileName;

        List<String> expectedHeaderButtonNames = parseCSVFile(filePathHeaderButtons);
        List<String> expectedButtonNames = parseCSVFile(filePathButtons);

        // get buttons from page

        List<WebElement> actualButtons = HomePage.getButtonsFromNavbarAfterHover(navbarHeader, expectedHeaderButtonNames);

        //check if names are matching
        assertThat(actualButtons.stream().map(WebElement::getText).toList()).containsExactlyElementsOf(expectedButtonNames);

        //check if buttons are enabled
        for (WebElement button: actualButtons) {
            assertThat(button.isEnabled()).isTrue();
        }


    }

    private static List<String> parseCSVFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        List<String> lines = new ArrayList<>();
        String line = null;
        while ((line = reader.readLine()) != null) {
            String deletedCommaAtEndString = line.substring(0, line.length()-1);
            lines.add(deletedCommaAtEndString);
        }
        return lines;
    }

    @When("User clicks on Blog button")
    public void userClicksOnBlogButton() {
        HomePage.clickOnBlogButton();
    }
}
