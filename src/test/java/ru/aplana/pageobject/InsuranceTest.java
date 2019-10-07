package ru.aplana.pageobject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.aplana.pageobject.pages.BasePage;
import ru.aplana.pageobject.pages.CartPage;
import ru.aplana.pageobject.pages.ItemPage;
import ru.aplana.pageobject.pages.SearchResultsPage;

public class InsuranceTest {

    @Before
    public void before() {
        Init.setUp();
    }

    @Test
    public void pageObjectCU() throws Exception{
        BasePage main = new BasePage();
        SearchResultsPage results = main.search("playstation");

        ItemPage itemPage = results.chooseItem("playstation 4 slim black");

        itemPage.changeGuaranteeAndCheck("2 года");
        itemPage.addToCart();
        itemPage.search("Detroit");
//        itemPage.search("Dishonored: Death of the Outsider");
        itemPage.addToCart();
        itemPage.checkCartPrice();
        CartPage cartPage = itemPage.gotoCart();
        cartPage.checkGuarantee(0);
        cartPage.checkSum();
        cartPage = cartPage.deleteItem("Игра Detroit: Стать человеком (PS4)");
        cartPage.checkSum();
        cartPage.addItem("Игровая приставка PlayStation 4 Slim Black 1 TB + 3 игры");
        cartPage.addItem("Игровая приставка PlayStation 4 Slim Black 1 TB + 3 игры");
        cartPage.checkSum();
        cartPage = cartPage.returnDeleted();
        cartPage.checkSum();
    }

    @After
    public void after() {
        Init.tearDown();
    }

}
