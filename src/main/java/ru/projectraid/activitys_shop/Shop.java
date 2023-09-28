package ru.projectraid.activitys_shop;

import ru.projectraid.activitys_shop.products.AProduct;
import ru.projectraid.activitys_shop.products.BetaTestProduct;

import java.util.ArrayList;

public class Shop {

    public static Shop mainShop;
    private ArrayList<AProduct> productList;

    private Shop(){
        initializeShopProduct();

        mainShop = this;
    }

    private void initializeShopProduct() {
        productList.add(new BetaTestProduct());
    }


}
