package ru.projectraid.activitys_shop;

import ru.projectraid.activitys_shop.products.AProduct;
import ru.projectraid.activitys_shop.products.BetaTestProduct;
import ru.projectraid.user.User;

import java.util.ArrayList;

public class Shop {

    public static Shop mainShop;
    private static ArrayList<AProduct> productList = new ArrayList<>();

    public Shop(){
        mainShop = this;
    }

    public static void addProduct(AProduct product) {
        productList.add(product);
    }

    public static boolean buyProduct(AProduct product, User user) {
        if(user.canBuyProduct(product))
        {
            product.action(user);
            return true;
        }
        return false;
    }

    public static AProduct getProduct(String str) {
        for (AProduct product: productList) {
            if(product.getNameProduct().equalsIgnoreCase(str)) {
                return product;
            }
        }
        return null;
    }


}
