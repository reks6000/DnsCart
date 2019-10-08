package ru.aplana.pageobject.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage{
    int guaranteePriceIndex = 0;
    ArrayList<Integer> guaranteePrices = new ArrayList<>();

    @FindBy(xpath = "//a[@class='cart-list__product-name-link']")
    List<WebElement> productsNames;

    @FindBy(xpath = "//div[@class='cart-list__product']//div[@class='item-price']/span")
    List<WebElement> prices;

    @FindBy(xpath = "//div[@class='cart-list__product-additional-warranty cart-list__product-additional-warranty_wide-screen']//div[@class='radio radio_checked']/label")
    List<WebElement> guarantees;

    @FindBy(xpath = "//i[@class='remove-button__icon']")
    List<WebElement> removeButtons;

    @FindBy(xpath = "//i[@class='count-buttons__icon-plus']")
    List<WebElement> addButtons;

    @FindBy(xpath = "//i[@class='restore-last-removed__icon']")
    WebElement returnDeletedButton;

    public void checkGuarantee(int index) {
        if (!guarantees.get(index).getText().contains(cart.getGuarantee(index))) {
            System.err.println("Error: guarantee isn't right");
            driver.quit();
            throw new Error();
        }
    }

    public void countGuaranteePrices () {
        for (WebElement item : guarantees) {
            guaranteePrices.add(Integer.parseInt(fw.getText(item).split("\\(")[1].replaceAll("[ )]", "")));
        }
    }

    public void checkSum() {
        countGuaranteePrices();
        for (int i = 0; i < prices.size(); i++) {
            Integer realPrice = Integer.parseInt(fw.getText(prices.get(i)).replace(" ", ""));
            Integer assumedPrice = cart.getItemPrice(i);
            Integer multiplier = cart.getItemQuantity(i);
            if (!assumedPrice.equals(realPrice)) {
                if (!assumedPrice.equals(realPrice+multiplier*guaranteePrices.get(guaranteePriceIndex++))) {
                    System.err.println("Error: cart price isn't right");
                    driver.quit();
                    throw new Error();
                }
            }
        }
        guaranteePriceIndex = 0;
        checkCartPrice();
    }

    public CartPage deleteItem(String name) {
        for (int i = 0; i < productsNames.size(); i++) {
            if (fw.getText(productsNames.get(i)).equals(name)) {
                if(!fw.waitForDelete(fw.waitAndClick(removeButtons.get(i)))) {
                    System.err.println("Error: element not deleted");
                    driver.quit();
                    throw new Error();
                }
            }
        }
        cart.remove(name);
        return new CartPage();
    }

    public void addItem(String name) {
        String oldValue = fw.getText(cartPrice);
        for (int i = 0; i < productsNames.size(); i++) {
            if (fw.getText(productsNames.get(i)).equals(name)) {
                fw.waitAndClick(addButtons.get(i));
            }
        }
        cart.addProduct(name);
        fw.waitForChange(cartPrice, oldValue);
    }

    public CartPage returnDeleted() {
        String oldValue = fw.getText(cartPrice);
        fw.waitAndClick(returnDeletedButton);
        cart.returnDeleted();
        fw.waitForChange(cartPrice, oldValue);
        return new CartPage();
    }
}
