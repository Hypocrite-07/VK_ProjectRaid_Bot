package ru.projectraid.activitys_shop.products;

public class BetaTestProduct extends  AProduct{

    @Override
    public String getNameProduct() {
        return "Close Beta-Test";
    }

    @Override
    public String getProductDescription() {
        return "It's a Beta-Test";
    }

    @Override
    public int getPrice() {
        return 1;
    }

    @Override
    public int getCount() {
        return 1;
    }


}
