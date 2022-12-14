package org.example.sedin.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.sedin.data.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.example.sedin.configuration.DriverManager.DRIVER;
import static org.example.sedin.configuration.DriverManager.getDriver;


public class ProductListPage {
    private static final Logger LOG = LogManager.getLogger(ProductListPage.class);
    Data data = new Data();
    @FindBy(css = ".inventory_item_name")
    List<WebElement> itemNames;
    @FindBy(xpath = "//div[@class='pricebar']/div")
    List<WebElement> itemPrices;
    @FindBy(xpath = "(//button[contains(text(), 'Add to cart')])[1]")
    WebElement addToCartFirstButton;
    @FindBy(xpath = "(//div[@class='inventory_item_desc'])[1]")
    WebElement firstItemDesc;
    @FindBy(xpath = "(//div[@class='inventory_item_price'])[1]")
    WebElement firstItemPrice;
    @FindBy(css = "#shopping_cart_container")
    WebElement shoppingCartIcon;
    @FindBy(css = ".inventory_details_price")
    WebElement productDetailPrice;
    public ProductListPage() {
        PageFactory.initElements(DRIVER.get(), this);
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

    public boolean getItemPrices() {
        List<WebElement> productPrices = getDriver().findElements(By.xpath("//div[@class='pricebar']/div"));
        int size = productPrices.size();
        for (int i = 1; i < size; i++) {
            WebElement productPrice = getDriver().findElement(By.xpath("(//div[@class='pricebar']/div)[" + i + "]"));
            Double listingPrice = Double.parseDouble(productPrice.getText().replace("$", "")); //$29.99
            getDriver().findElement(By.xpath
                    ("(//div[contains(@class,'inventory_item_price') and" +
                            " text()='" + listingPrice + "']//preceding::a/img)[" + i + "]")).click();
            Double detailPrice = Double.parseDouble(productDetailPrice.getText().replace("$", ""));
            LOG.info("Listing Price:" + listingPrice + " Details Price: " + detailPrice);
            if (!listingPrice.equals(detailPrice)) {
                return false;
            }
            getDriver().navigate().back();
        }
        return true;
    }

    public void setTheProductData() {
        data.setItemDescription(firstItemDesc.getText().trim());
        data.setPriceValue(Double.parseDouble(firstItemPrice.getText().replace("$", "")));
    }

    public void addProductToCart() {
        addToCartFirstButton.click();
    }

    public void clickOnShoppingCartIcon() {
        LOG.info("Clicking on the cart icon to proceed to cart");
        shoppingCartIcon.click();
    }
}
