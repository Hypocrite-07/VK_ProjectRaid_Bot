package ru.projectraid.activitys_shop.products;

import api.longpoll.bots.model.objects.additional.Image;
import ru.projectraid.user.User;

import java.util.Objects;

public abstract class AProduct {

    private String nameProduct;
    private String productDescription;
    private int permissionLevel;
    private Image image;
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void action(User user) {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AProduct product = (AProduct) o;
        return permissionLevel == product.permissionLevel && price == product.price && count == product.count && Objects.equals(nameProduct, product.nameProduct) && Objects.equals(productDescription, product.productDescription) && Objects.equals(image, product.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameProduct, productDescription, image, permissionLevel, price, count);
    }

}
