package ru.projectraid.user;

import ru.projectraid.market.Product;
import ru.projectraid.market.Shop;
import ru.projectraid.market.wallet.Wallet;
import ru.projectraid.messages.commands.ACommand;

public class User<T> {
    private final int uniqueId;
    private final Wallet wallet;
    private UserType userType;

    public User(int uniqueId, UserType userType) {
        this.uniqueId = uniqueId;
        this.wallet = new Wallet();
        this.userType = userType;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public boolean canBuy(Product product) {
        return this.wallet.getActivities() >= product.price;
    }

    public <T extends ACommand> boolean canUseCommand(T command) {
        return this.userType.permissionsId >= command.getPermissionsLevel();
    }

    public <T extends ACommand> void useCommand(T command) {
        if(canUseCommand(command))
            command.action(this);
    }

    public void toBuy(Shop shop, Product product) {
        if(canBuy(product))
            wallet.buyProduct(product);
    }

    @Override
    public String toString() {
        return "Профиль" +
                "\nИдентификатор: " + uniqueId +
                "\nСтатус: " + userType.statusName +
                "\nБаланс: " + wallet.getActivities();
    }

}
