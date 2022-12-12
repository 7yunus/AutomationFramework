package org.example.sedin.stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.sedin.pages.SearchPage;
import org.testng.Assert;

public class SearchSteps {

    SearchPage searchPage = new SearchPage();

    @Given("^user searches '(.*)' in search bar$")
    public void userSearchesInSearchBar(String searchTerm) {
        searchPage.search(searchTerm);
    }

    @When("^user changes the Search category to '(.*)'$")
    public void userChangesTheSearchCategoryTo(String category) {
        searchPage.selectCategory(category);
    }

    @Then("user waits for page to load completely")
    public void userWaitsForPageToLoadCompletely() {
        searchPage.waitForPageLoad();
    }

    @And("^first result name matches with the searched term '(.*)'$")
    public void firstResultNameMatchesWithTheSearchedTerm(String category) {
        Assert.assertTrue(searchPage.isSearchSuccess(category));
    }
}
