package org.example.sedin.stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.example.sedin.pages.ProductListPage;
import org.testng.Assert;

public class ProductListSteps {

    ProductListPage productListPage = new ProductListPage();

    @Then("product {string} should be displayed in product listing page")
    public void productShouldBeDisplayedInProductListingPage(String productName) {
        Assert.assertTrue(productListPage.isItemNamePresent(productName));
    }

    @And("adds product to cart")
    public void addsProductToCart() {
        productListPage.addProductToCart();
    }

    @And("opens the shopping cart")
    public void opensTheShoppingCart() {
        productListPage.clickOnShoppingCartIcon();
    }

    @And("User checks the product details and adds product to cart")
    public void userChecksTheProductDetailsAndAddsProductToCart() {
        productListPage.setTheProductData();
        productListPage.addProductToCart();
    }
}
