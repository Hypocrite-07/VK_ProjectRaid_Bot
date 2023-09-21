package ru.projectraid.market.wallet;

import ru.projectraid.market.Product;

import java.util.ArrayList;

public class Wallet {
    private int activities;
    private ArrayList<Product> buyedProducts;

    public int getActivities() {
        return activities;
    }

    public ArrayList<Product> isBuyedProduct(Product product) {
        return buyedProducts;
    }

    public void buyProduct(Product product) {
        this.activities -= product.price;
        buyedProducts.add(product);
    }
}
