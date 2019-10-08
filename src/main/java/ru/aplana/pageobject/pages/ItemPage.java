package ru.aplana.pageobject.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.aplana.pageobject.Product;

public class ItemPage extends BasePage {

    @FindBy(xpath = "//h1[@class='page-title price-item-title']")
    WebElement nameField;

    @FindBy(xpath = "//span[@class='current-price-value']")
    WebElement priceField;

    @FindBy(xpath = "//select[@class='form-control select']")
    WebElement guaranteeField;

    @FindBy(xpath = "//button[@class='btn btn-cart btn-lg']")
    WebElement addToCartButton;

    public void changeGuaranteeAndCheck(String key) {
        if (fw.verify(guaranteeField)) {
            String oldValue = fw.getText(priceField);
            fw.select(guaranteeField, key);
            fw.waitForChange(priceField, oldValue);
        } else {
            System.err.println("Error: no guarantee field");
            driver.quit();
            throw new Error();
        }
    }

    public void addToCart(){
        String oldValue = fw.getText(cartPrice);
        String name = fw.getText(nameField);
        int price = Integer.parseInt(fw.getText(priceField).replace(" ", ""));
        String guarantee = "";
        if (fw.verify(guaranteeField)) {
            guarantee = fw.getSelectText(guaranteeField).trim();
        }
        fw.waitAndClick(addToCartButton);
        Product product = new Product(name, price, guarantee);
        cart.add(product);
        fw.waitForChange(cartPrice, oldValue);
    }
}
