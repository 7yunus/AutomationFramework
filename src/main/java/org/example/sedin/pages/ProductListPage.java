package org.example.sedin.pages;

import io.cucumber.java.en.And;
import lombok.extern.java.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.sedin.data.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.example.sedin.configuration.DriverSetup.driver;

public class ProductListPage {
    private static final Logger LOG = LogManager.getLogger(ProductListPage.class);

    static Data data = new Data();

    @FindBy(css = ".inventory_item_name")
    List<WebElement> itemNames;
    @FindBy(xpath = "(//button[contains(text(), 'Add to cart')])[1]")
    WebElement addToCartFirstButton;
    @FindBy(xpath = "(//div[@class='inventory_item_desc'])[1]")
    WebElement firstItemDesc;
    @FindBy(xpath = "(//div[@class='inventory_item_price'])[1]")
    WebElement firstItemPrice;
    @FindBy(css = "#shopping_cart_container")
    WebElement shoppingCartIcon;

    public ProductListPage() {
        PageFactory.initElements(driver, this);
    }

    public boolean isItemNamePresent(String expectedItemName) {
        for (WebElement itemName : itemNames) {
            LOG.info(itemName.getText());
            if (itemName.getText().trim().equals(expectedItemName)) {
                return true;
            }
        }
        return false;
    }

    public void setTheProductData() {
        LOG.info(firstItemDesc.getText());
        data.setItemDescription(firstItemDesc.getText().trim());
        data.setPriceValue(Double.parseDouble(firstItemPrice.getText().replace("$","")));
    }

    public void addProductToCart() {
        addToCartFirstButton.click();
    }


    public void clickOnShoppingCartIcon() {
        LOG.info("Clicking on the cart icon to proceed to cart");
        shoppingCartIcon.click();
    }


}
