package org.example.sedin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.sedin.configuration.DriverSetup.driver;

public class FilterPage {

    public final String selectCategory = "//a[text()='${subCategoryValue}']/..//following-sibling::ul//a[text()='${category}']";
    public final String selectFinalSubCategory = "//a[text()='${finalSubCategory}']";
    public final String shopByOption = "//span[contains(text(), '${shopByOption}')]//preceding-sibling::span[text()='See All']";
    public final String filterOption = "//div/span[text()='${filterOption}']";
    @FindBy(xpath = "//button[@id='gh-shop-a']")
    public WebElement categoryDropdown;
    @FindBy(xpath = "(//input[@role='checkbox']/..//following-sibling::label/span)[1]")
    public WebElement screenSizeFilterCheckbox;
    @FindBy(xpath = "//input[@class='x-textrange__input x-textrange__input--from']")
    public WebElement priceFromInputFilter;
    @FindBy(xpath = "//input[@class='x-textrange__input x-textrange__input--to']")
    public WebElement priceToInputFilter;
    @FindBy(xpath = "(//span[@class='field'])[2]/span/input")
    public WebElement itemLocationFilterRadioOption;
    @FindBy(xpath = "//button[text()='Apply']")
    public WebElement applyButton;
    @FindBy(xpath = "//button/span[contains(text(),'filters applied')]")
    public WebElement appliedFilterDropdown;
    @FindBy(xpath = "//li[contains(@class,'brm__aspect-item brm__aspect-item--applied')]/a/span[1]")
    public List<WebElement> appliedFilterTags;
    public FilterPage() {
        PageFactory.initElements(driver, this);
    }

    public void navigateToCategory(String category, String subCategory) {
        categoryDropdown.click();
        driver.findElement(By.xpath(selectCategory.replace("${category}", category)
                .replace("${subCategoryValue}", subCategory))).click();
    }

    public void navigateToFinalSubCategory(String finalSubCategory) {
        driver.findElement(By.xpath(selectFinalSubCategory.replace("${finalSubCategory}",
                finalSubCategory))).click();
    }

    public void clickSeeAllOption(String shopByOptionString) {
        driver.findElement(By.xpath(shopByOption.replace("${shopByOption}", shopByOptionString))).click();
    }

    public void applyFilters(String filter1, String filter2, String filter3) {
        driver.findElement(By.xpath(filterOption.replace("${filterOption}", filter1))).click();
        screenSizeFilterCheckbox.click();
        driver.findElement(By.xpath(filterOption.replace("${filterOption}", filter2))).click();
        priceFromInputFilter.sendKeys("100");
        priceToInputFilter.sendKeys("1000");
        driver.findElement(By.xpath(filterOption.replace("${filterOption}", filter3))).click();
        itemLocationFilterRadioOption.click();
        applyButton.click();
    }

    public boolean isFiltersApplied(ArrayList<String> expectedFilterTags) {
        appliedFilterDropdown.click();
        ArrayList<String> appliedFilters = new ArrayList<>();
        for (WebElement filter : appliedFilterTags) {
            appliedFilters.add(filter.getText());
        }

        List<String> actualFilterTags = appliedFilters.stream()
                .map(x -> x.replaceAll(":[^:]*$", ""))
                .collect(Collectors.toList());
        return actualFilterTags.equals(expectedFilterTags);
    }
}
