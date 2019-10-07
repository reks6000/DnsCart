package ru.aplana.pageobject;

import java.util.ArrayList;

public class Cart {
    ArrayList<Product> products = new ArrayList<>();
    ArrayList<Product> deleted = new ArrayList<>();

    public int getItemPrice(int index) {
        return products.get(index).getPrice()*products.get(index).getQuantity();
    }

    public int getItemQuantity(int index) {
        return products.get(index).getQuantity();
    }

    public Integer getPrice() {
        Integer res = 0;
        for (Product i : products) {
            res += i.getPrice()*i.getQuantity();
        }
        return res;
    }

    public String getGuarantee(int index) {
        ArrayList<String> guarantees = new ArrayList<>();
        for (Product i : products) {
            if (!i.getGuarantee().equals("")) {
                guarantees.add(i.getGuarantee());
            }
        }
        return guarantees.get(index);
    }

    public void add(Product item) {
        boolean existFlag = false;
        for (Product i : products) {
            if (item.getName().equals(i.getName())) {
                existFlag = true;
                break;
            }
        }
        if (existFlag) {
            item.addProduct();
        } else {
            products.add(item);
        }
    }

    public void addProduct(String name) {
        for (Product i : products) {
            if (name.equals(i.getName())) {
                i.addProduct();
                break;
            }
        }
    }

    public void remove(String name) {
        for (Product i : products) {
            if (i.getName().equals(name)) {
                products.remove(i);
                deleted.add(i);
                break;
            }
        }
    }

    public void returnDeleted() {
        for (Product i : deleted) {
            products.add(i);
        }
        deleted.clear();
    }
}
