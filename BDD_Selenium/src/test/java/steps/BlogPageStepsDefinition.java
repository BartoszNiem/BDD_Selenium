package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BlogPage;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
public class BlogPageStepsDefinition {

    @Then("User verifies they are on Blog page")
    public void verifyUserOnBlogPage(){
        assertThat(BlogPage.isOnBlogPage()).isTrue();
    }

    @And("User verifies there are blogs present on site")
    public void userVerifiesThereAreBlogsPresentOnSite() {
        assertThat(BlogPage.areBlogsAppearing()).isTrue();
    }
    @And("User tries to select on filter {string} values: {string}, {string}, {string}")
    public void userTriesToSelectOnFilterValues(String filterTitle, String value1, String value2, String value3) {
        BlogPage.selectFilterValues(filterTitle, value1, value2, value3);
    }

    @Then("User verifies selected filter values appear on page")
    public void userVerifiesSelectedFilterValuesAppearOnPage(List<String> values) {
        List<String> actualSelectedFilterValues = BlogPage.getSelectedFilterValues();
        assertThat(actualSelectedFilterValues).containsExactlyElementsOf(values);
    }

    @When("User clicks on Clear filters button")
    public void userClicksOnClearFiltersButton() {
        BlogPage.clickOnClearFiltersButton();
    }

    @Then("filters will display their default values: {string} and {string}")
    public void filtersWillDisplayTheirDefaultValuesAnd(String defaultValueCategoryFilter, String defaultValueIndustryFilter) {
        String actualDefualtCategoryFilterValue = BlogPage.getCategoryFilterCurrentValue();
        String actualDefualtIndustryFilterValue = BlogPage.getIndustryFilterCurrentValue();

        assertThat(actualDefualtCategoryFilterValue).isEqualTo(defaultValueCategoryFilter);
        assertThat(actualDefualtIndustryFilterValue).isEqualTo(defaultValueIndustryFilter);
    }


}
