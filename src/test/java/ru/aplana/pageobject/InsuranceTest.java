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
//        1) открыть dns-shop
        Init.setUp();
    }

    @Test
    public void pageObjectCU() {
        BasePage main = new BasePage();
//        2) в поиске найти playstation
        SearchResultsPage results = main.search("playstation");
//        3) кликнуть по playstation 4 slim black
        ItemPage itemPage = results.chooseItem("playstation 4 slim black");
//        4) запомнить цену
//        5) Доп.гарантия - выбрать 2 года
        itemPage.changeGuaranteeAndCheck("2 года");
//        6) дождаться изменения цены и запомнить цену с гарантией
//        7) Нажать Купить
        itemPage.addToCart();
//        8) выполнить поиск Detroit
        itemPage.search("Detroit");
//        9) запомнить цену
//        10) нажать купить
        itemPage.addToCart();
//        11) проверить что цена корзины стала равна сумме покупок
        itemPage.checkCartPrice();
//        12) перейри в корзину
        CartPage cartPage = itemPage.gotoCart();
//        13) проверить, что для приставки выбрана гарантия на 2 года
        cartPage.checkGuarantee(0);
//        14) проверить цену каждого из товаров и сумму
        cartPage.checkSum();
//        15) удалить из корзины Detroit
        cartPage = cartPage.deleteItem("Игра Detroit: Стать человеком (PS4)");
//        16) проверить что Detroit нет больше в корзине и что сумма уменьшилась на цену Detroit
        cartPage.checkSum();
//        17) добавить еще 2 playstation (кнопкой +) и проверить что сумма верна (равна трем ценам playstation)
        cartPage.addItem("Игровая приставка PlayStation 4 Slim Black 1 TB + 3 игры");
        cartPage.addItem("Игровая приставка PlayStation 4 Slim Black 1 TB + 3 игры");
        cartPage.checkSum();
//        18) нажать вернуть удаленный товар, проверить что Detroit появился в корзине и сумма увеличилась на его значение
        cartPage = cartPage.returnDeleted();
        cartPage.checkSum();
    }

    @After
    public void after() {
        Init.tearDown();
    }

}
