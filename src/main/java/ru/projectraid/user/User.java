package ru.projectraid.user;

import ru.projectraid.market.Product;
import ru.projectraid.market.Shop;
import ru.projectraid.market.wallet.Wallet;
import ru.projectraid.messages.commands.ACommand;

public class User {
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

    public boolean canUseCommand(ACommand command) {
        return this.userType.permissionsId >= command.getPermissionsLevel();
    }

    @Override
    public String toString() {
        return "Профиль" +
                "\nИдентификатор: " + uniqueId +
                "\nСтатус: " + userType.statusName +
                "\nБаланс: " + wallet.getActivities();
    }

}
