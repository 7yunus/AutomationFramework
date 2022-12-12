package org.example.sedin.stepDefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.sedin.data.Data;
import org.example.sedin.pages.FilterPage;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;

public class FilterSteps {

    FilterPage filterPage = new FilterPage();
    Data data = new Data();

    @Given("user navigates to sub category page {string} from {string} category")
    public void userNavigatesToSubCategoryPageFromCategory(String category, String subCategory) {
        filterPage.navigateToCategory(category, subCategory);
    }

    @When("user clicks the final sub category {string} in the left hand side of navigation section")
    public void userClicksTheFinalSubCategoryInTheLeftHandSideOfNavigationSection(String finalSubCategory) {
        filterPage.navigateToFinalSubCategory(finalSubCategory);
    }

    @And("user clicks See All option that appears under {string}")
    public void userClicksSeeAllOptionThatAppearsUnder(String shopByOption) {
        filterPage.clickSeeAllOption(shopByOption);
    }

    @And("applies filters appearing in the pop-up")
    public void appliesFiltersAppearingInThePopUp(DataTable filterOptions) {
        List<String> filters = filterOptions.asList(String.class);
        data.setAppliedFilterTags(new ArrayList<>(filters));
        filterPage.applyFilters(filters.get(0), filters.get(1), filters.get(2));
    }

    @Then("the filter tags should be applied")
    public void theFilterTagsShouldBeApplied() {
        Assert.assertTrue(filterPage.isFiltersApplied(data.getAppliedFilterTags()));
    }
}
