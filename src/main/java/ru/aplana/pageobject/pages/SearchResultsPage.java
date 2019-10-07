package ru.aplana.pageobject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultsPage extends BasePage {

    @FindBy(xpath = "//div[@class='catalog-item']")
    List<WebElement> products;

    public static final By productTitle = By.xpath("//div[@class='catalog-item']//a[@class='ui-link']");


    public ItemPage chooseItem(final String itemTitle){
        fw.wait(products.get(0));
        WebElement item = products.stream()
                .map(element->element.findElement(productTitle))
                .filter(element->element.getText().toLowerCase().contains(itemTitle))
                .findFirst().orElseThrow(()->new RuntimeException("Error: no element with title "+itemTitle));
        item.click();
        return new ItemPage();
    }
}
