package ru.aplana.pageobject;

public class Product {
    private String name;
    private int price;
    private String guarantee;
    private int quantity;

    public Product(String name, int price, String guarantee) {
        this.name = name;
        this.price = price;
        this.guarantee = guarantee;
        this.quantity = 1;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getGuarantee() {
        return guarantee;
    }

    public int getQuantity() {
        return quantity;
    }

    public int addProduct() {
        return ++this.quantity;
    }
}
