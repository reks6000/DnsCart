package ru.aplana.pageobject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.aplana.pageobject.Cart;
import ru.aplana.pageobject.Framework;
import ru.aplana.pageobject.Init;

import static java.lang.Thread.sleep;

public class BasePage {
    WebDriver driver;
    Framework fw;
    static Cart cart = new Cart();

    @FindBy(xpath = "//nav[@id='header-search']//input")
    WebElement searchInput;

    @FindBy(xpath = "//nav[@id='header-search']//span[@class='ui-input-search__icon ui-input-search__icon_search']")
    WebElement searchButton;

    @FindBy(xpath = "//nav[@id='header-search']//span[@class='btn-cart-link__cart']")
    WebElement cartButton;

    @FindBy(xpath = "//nav[@id='header-search']//span[@data-of='totalPrice']")
    WebElement cartPrice;

    public BasePage(){
        this.driver = Init.getDriver();
        this.fw = Init.getFramework();
        PageFactory.initElements(driver, this);
    }

    public SearchResultsPage search(String searchString) {
        fw.waitAndSendKey(searchInput, searchString);
        fw.waitAndClick(searchButton);
        return new SearchResultsPage();
    }

    public void checkCartPrice() throws Exception{
        sleep(1000);
        Integer realPrice = Integer.parseInt(fw.getText(cartPrice).replace(" ", ""));
        Integer assumedPrice = cart.getPrice();
        if (!realPrice.equals(assumedPrice)) {
            System.err.println("Error: cart price not changed");
            driver.quit();
            throw new Error();
        }
    }

    public CartPage gotoCart(){
        fw.waitAndClick(cartButton);
        return new CartPage();
    }

}
