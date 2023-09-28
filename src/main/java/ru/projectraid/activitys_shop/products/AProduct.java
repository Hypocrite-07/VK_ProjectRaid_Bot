package ru.projectraid.activitys_shop.products;

public abstract class AProduct {

    private String nameProduct;
    private String productDescription;
    private int permissionLevel;

    private int price;
    private int count;

    public String getNameProduct() {
        return nameProduct;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public int getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(int permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

}
