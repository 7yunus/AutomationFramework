package org.example.sedin.pages;

import io.cucumber.java.en.And;
import lombok.extern.java.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.sedin.data.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.example.sedin.configuration.DriverSetup.driver;

public class ProductListPage {
    private static final Logger LOG = LogManager.getLogger(ProductListPage.class);

    static Data data = new Data();

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

    public void getItemPrices() throws InterruptedException {
        for (WebElement itemPrice : itemPrices) {

            String parent = driver.getWindowHandle();

            Double listingPrice=0.0d;
                listingPrice = Double.parseDouble(itemPrice.getText().replace("$", ""));
                LOG.info(listingPrice);

            String clicklnk = Keys.chord(Keys.COMMAND, "t", Keys.ENTER);

            driver.findElement(By.xpath
                    ("//div[contains(@class,'inventory_item_price') and text()='"+listingPrice+"']//preceding::a[@id='item_4_img_link']"))
                    .click();


            // Switch to new window opened
            for(String winHandle : driver.getWindowHandles()){
                driver.switchTo().window(winHandle);
            }

            Double detailPrice = Double.parseDouble(productDetailPrice.getText().replace("$",""));
            LOG.info(detailPrice);
            LOG.info(listingPrice.equals(detailPrice));

            Thread.sleep(2000);
            driver.close();

            driver.switchTo().window(parent);
            Thread.sleep(2000);

        }

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
